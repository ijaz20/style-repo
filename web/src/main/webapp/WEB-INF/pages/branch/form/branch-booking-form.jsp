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
		method="post" validate="true" id="partnerForm" cssClass="well">
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
			<s:textfield key="booking.Date" required="true"
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
					<td class="text-center"><select id="product1" name="product1"
						class="form-control">
							<c:forEach items="${branchProductPrices}" var="productPrice">
								<option value="${productPrice.product.id}">${productPrice.product.productName}</option>
							</c:forEach>
					</select></td>
					<td class="text-center"><select id="branchAvailableTime1"
						name="branchAvailableTime1" class="form-control">
							<c:forEach items="${branchAvailableTimes}" var="branchAvailableTimes">
								<option value="${branchAvailableTime}">${branchAvailableTime}</option>
							</c:forEach>
					</select></td>
					<td class="text-center"><input type="text" id="productPrice1"
						name="productPrice1" class="form-control"/>
					</td>
					<td class="text-center"><input type="text" id="productDiscount1"
						name="productDiscount1" class="form-control"/>
					</td>
					<td class="text-center"><input type="text" id="productNetPrice1"
						name="productNetPrice1" class="form-control"/>
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

	function loadAvailableTimes(rowCount){
		var url = "/branch/getBranchAvailableTimes?ajax=true"
		var productId = $("product"+rowCount).val();
	    var paramMap = {
	        productId:productId
	    };
	    $.post(url,paramMap,function(response) {
				if (response != null && response != '') {
					alert(response);
				} else {
					// need to add no product found
					if(isFilter){
						$("#" + renderId).html("No Results Found");
					} else {
						//$("#" + renderId).append(productsHtml);
					}
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
			var store = $(this).attr("id");
			var new1 = store.replace(oldRowNo, newRowNo);
			$(this).attr("id", new1);
			$(this).attr("name", new1);
			$(this).attr("onChanage", loadAvailableTimes(newRowNo));
		});
		last_row.appendTo($('#bookingDetails'));
		deleteRow(newRowNo);
	}

	function deleteRow(rowCount) {
		$("#deleteRow" + rowCount).click(function() {
			$(this).closest('tr').remove();
		});
	}
	
	loadAvailableTimes(1);
	deleteRow(1);
	$('#product1').change(function() {
		loadAvailableTimes(1);
	});
</script>