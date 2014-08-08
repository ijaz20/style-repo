<%@ include file="/common/taglibs.jsp"%>

<head>
<title><fmt:message key="category.form.title" /></title>
<meta name="menu" content="Category" />
</head>

<div class="col-sm-12">
	<h2>
		<fmt:message key="category.form.heading" />
	</h2>
	<p>
		<fmt:message key="category.form.message" />
	</p>
</div>
<div class="col-sm-12">
	<s:form action="saveCategory" enctype="multipart/form-data"
		method="post" validate="true" id="partnerForm" cssClass="well">
		<s:hidden key="category.id" />

		<div class="row">
			<s:textfield key="category.categoryName" required="true"
				autofocus="true" cssClass="form-control" />
		</div>

		<div class="row table-responsive">
			<table id="branchDetails" class="table table-striped">
				<tr>
					<th class="text-center">Partner</th>
					<th class="text-center">Branch</th>
					<th class="text-center"><input type="button" id="addRow"
						name="addRow" value="Add Row" class="btn btn-default col-sm-4" /></th>
				</tr>
				<tr>
					<td class="text-center"><select id="partner1"
						class="form-control">
							<c:forEach items="${availablePartners}" var="partner">
								<option value="${partner.value}">${partner.label}</option>
							</c:forEach>
					</select></td>
					<td class="text-center"><select id="branches1"
						name="branchIds" class="form-control" multiple="true">
							<c:forEach items="${branches}" var="branch">
								<%-- <option value="${branch.id}" ${fn:contains(category.branch.id, branch.id) ? 'selected' : ''}>${branch.branchName}</option> --%>
							</c:forEach>
					</select></td>
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
		var oldRowNo = newRowNo - 1;

		var $last = $('#branchDetails tr:last');
		var last_row = $last.clone();
		$(last_row).find(":input").each(function() {
			var store = $(this).attr("id");
			var new1 = store.replace(oldRowNo, newRowNo);
			$(this).attr("id", new1);
		});
		last_row.appendTo($('#branchDetails'));
		//$("#branchDetails tr:last").hide().fadeIn('slow');
		addPartnerChangeEvent(newRowNo);
		loadBranches(newRowNo);
		deleteRow(newRowNo);
	});

	function deleteRow(rowCount) {
		$("#deleteRow" + rowCount).click(function() {
			$(this).closest('tr').remove();
		});
	}
	$(document).ready(function() {
		loadBranches(1);
		addPartnerChangeEvent(1);
		deleteRow(1);
	});
</script>