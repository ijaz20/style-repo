<%@ include file="/common/taglibs.jsp"%>

<head>
<title><fmt:message key="product.form.title" /></title>
<meta name="menu" content="Product" />
</head>

<div class="col-sm-2">
	<h2>
		<fmt:message key="product.form.heading" />
	</h2>
	<p>
		<fmt:message key="product.form.message" />
	</p>
</div>
<div class="col-sm-7">
	<s:form action="saveProduct" enctype="multipart/form-data"
		method="post" validate="true" id="partnerForm" cssClass="well">
		<s:hidden key="product.id" />

		<div class="row">
			<s:textfield key="product.productName" required="true"
				autofocus="true" cssClass="form-control" />
		</div>

		<div class="row">
			<label for="category" class="control-label"><fmt:message key="product.form.category"/></label>
			<select id="category" name="product.category.id" autofocus="true" cssClass="form-control">
				<c:forEach items="${availableCategories}" var="cat">
                    <option value="${cat.value}" ${fn:contains(product.category.id, cat.value) ? 'selected' : ''}>${cat.label}</option>
                </c:forEach>
				
			</select>
		</div>
		
		<div class="row">
			<table id="branchDetails" tabindex="5" cellpadding=20>
				<tr>
					<th>Partner</th>
					<th>Branch</th>
					<th><input type="button" id="addRow" name="addRow"
						value="Add Row" /></th>
				</tr>
				<tr>
					<td><select id="partner1" class="form-control">
							<c:forEach items="${availablePartners}" var="partner">
								<option value="${partner.value}">${partner.label}</option>
							</c:forEach>
					</select></td>
					<td><select id="branches1" name="prices[0].branch.id"
						class="form-control">
							<c:forEach items="${branches}" var="branch">
								<option value="${branch.id}">${branch.branchName}</option>
							</c:forEach>
					</select></td>

					<td><input type="text" id="price1" name="prices[0].price"
						class="form-control"/>
					</td>
					
					<td><input id="deleteRow1" name="deleteRow" tabindex="3"
						type="button" value="Remove Row" class="remove" /></td>
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

<!-- <table id="invoice">
		<tr>
			<th>Quantity</th>
			<th>Item</th>
			<th><input type="button" id="addnew" name="addnew"
				value="Add Row" /></th>
		</tr>
		<tr id="id1">
			<td><input id="quantity1" name="quantity[]" tabindex="1"
				type="text" value="" /></td>
			<td><select id="rate1" name="rate[]" tabindex="2" value="">
					<option value="">SELECT</option>
					<option value="1">ONE</option>
					<option value="2">TWO</option>
			</select></td>
			<td><input id="remove1" name="remove[]" tabindex="3"
				type="button" value="Remove Row" class="remove" /></td>
		</tr>
	</table> -->

<script type="text/javascript">
	
	function loadBranches(rowCount) {
		var branchOptions = '';
		<c:forEach items="${branches}" var="branch">
		if ('${branch.partner.id}' == $("#partner" + rowCount).val()) {
			branchOptions = branchOptions
					+ '<option value="${branch.id}">${branch.branchName}</option>';
		}
		</c:forEach>
		$('#branches' + rowCount).html(branchOptions);
	}

	function addPartnerChangeEvent(rowCount) {
		$('#branchDetails tr:eq(' + rowCount + ') td:eq(0) select').change(
				function(e) {
					loadBranches(rowCount);
				});
	}
	
	$('#addRow').click(function() {
		var newRowNo = $('#branchDetails tr').length;
		var oldRowNo = newRowNo-1;
		var $last = $('#branchDetails tr:last');
		var last_row = $last.clone();
		$(last_row).find(":input").each(function() {
			var oldId = $(this).attr("id");
			var oldName = $(this).prop('name');
			var newId = oldId.replace(oldRowNo, newRowNo);
			var newName = oldName.replace(oldRowNo-1, newRowNo-1);
			$(this).attr("id", newId);
			$(this).attr("name", newName);
		});
		last_row.appendTo($('#branchDetails'));
		//$("#branchDetails tr:last").hide().fadeIn('slow');
		addPartnerChangeEvent(newRowNo);
		loadBranches(newRowNo);
		deleteRow(newRowNo);
	});

	function deleteRow(rowCount) {
		$("#deleteRow"+rowCount).click(function(){
		    $(this).closest('tr').remove();
		});
	}
	$(document).ready(function() {
		loadBranches(1);
		addPartnerChangeEvent(1);
		deleteRow(1);
	});
	
</script>