<%@ include file="/common/taglibs.jsp"%>

<head>
<title><fmt:message key="booking.form.title" /></title>
<meta name="menu" content="Booking" />
</head>

<div class="col-sm-12">
	<h2>
		<fmt:message key="booking.form.heading" />
	</h2>
	<p>
		<fmt:message key="booking.form.message" />
	</p>
</div>
<div class="col-sm-12">
	<s:form action="saveBranchBooking" enctype="multipart/form-data"
		method="post" validate="true" id="bookingForm" cssClass="well">
		<s:hidden key="booking.id" />

		<div class="row col-sm-12">
			<s:textfield key="booking.username" required="true"
				autofocus="true" cssClass="form-control" />
		</div>

		<div class="row col-sm-6">
			<s:textfield key="booking.phoneNumber" required="true"
				autofocus="true" cssClass="form-control" />
		</div>
		
		<div class="row col-sm-6">
			<s:textfield key="booking.bookingDate"
				autofocus="true" cssClass="form-control" />
		</div>
		
		<div class="row table-responsive">
			<table id="bookingDetails" class="table table-striped">
				<tr>
					<th class="text-center col-sm-3">Products</th>
					<th class="text-center col-sm-3">Time</th>
					<th class="text-center col-sm-2">Price</th>
					<th class="text-center col-sm-2">Discount</th>
					<th class="text-center col-sm-2">Net Price</th>
					<th class="text-center"><input type="button" id="addRow"
						name="addRow" value="Add Row" class="btn btn-default col-sm-2" /></th>
				</tr>
				<tr>
					<td class="text-center"><select id="product1" name="bookingDetails[0].product.id"
						class="form-control">
							<c:forEach items="${branchProductPrices}" var="productPrice">
								<option value="${productPrice.product.id}">${productPrice.product.productName}</option>
							</c:forEach>
					</select></td>
					<td class="text-center"><select id="branchAvailableTime1"
						name="bookingDetails[0].bookingTime" class="form-control">
							<c:forEach items="${branchAvailableTimes}" var="branchAvailableTimes">
								<option value="${branchAvailableTime}">${branchAvailableTime}</option>
							</c:forEach>
					</select></td>
					<td class="text-center"><input type="text" id="productPrice1"
						name="bookingDetails[0].price" class="form-control"/>
					</td>
					<td class="text-center"><input id="deleteRow1"
						name="deleteRow" tabindex="3" type="button" value="Remove Row"
						class="btn btn-default col-sm-4" /></td>
				</tr>
			</table>
		</div>

		<div id="actions" class="form-group">
			<s:submit type="button" cssClass="btn btn-primary" key="button.save"
				theme="simple">
				<i class="icon-ok icon-white"></i>
				<fmt:message key="button.save" />
			</s:submit>
			<s:submit type="button" cssClass="btn btn-default" method="cancel"
				key="button.cancel" theme="simple">
				<i class="icon-remove"></i>
				<fmt:message key="button.cancel" />
			</s:submit>
		</div>
	</s:form>
</div>

<script type="text/javascript">
	$('#addRow').click(function() {
		addBookingRow();
	});

	function addOnChangeEvent(rowCount){
		$('#product'+rowCount).change(function() {
			loadAvailableTimes(rowCount);
		});
	}
	
	function deleteRow(rowCount) {
		$("#deleteRow" + rowCount).click(function() {
			$(this).closest('tr').remove();
		});
	}
	
	function loadAvailableTimes(rowCount){
		var url = "/branch/getBranchAvailableTimes?ajax=true"
		var productId = $("#product"+rowCount).val();
	    var paramMap = {
	        productId:productId
	    };
	    $.post(url,paramMap,function(response) {
				if (response != null && response != '') {
					$('#branchAvailableTime'+rowCount).empty();
					$.each(response, function(val, text) {
			            $("#branchAvailableTime"+rowCount).append( $("<option></option>").val(val).html(text) )
		            }); // there was also a ) missing here
				} else {
					console.log(response);
				}
			}
		);
	    
	    
	}
	
	function addBookingRow() {
		var newRowNo = $('#bookingDetails tr').length;
		var oldRowNo = newRowNo - 1;

		var $last = $('#bookingDetails tr:last');
		var last_row = $last.clone();
		$(last_row).find(":input").each(function() {
			var oldId = $(this).attr("id");
			var oldName = $(this).prop('name');
			var newId = oldId.replace(oldRowNo, newRowNo);
			var newName = oldName.replace(oldRowNo - 1, newRowNo - 1);
			$(this).attr("id", newId);
			$(this).attr("name", newName);
		});
		last_row.appendTo($('#bookingDetails'));
		loadAvailableTimes(newRowNo)
		addOnChangeEvent(newRowNo);
		deleteRow(newRowNo);
	}

	loadAvailableTimes(1);
	addOnChangeEvent(1);
	deleteRow(1);
	
</script>