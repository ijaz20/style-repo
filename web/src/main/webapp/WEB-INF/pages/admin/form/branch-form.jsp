<%@ include file="/common/taglibs.jsp"%>

<head>
<title><fmt:message key="branch.form.title" /></title>
<meta name="menu" content="Branch" />
</head>

<div class="col-sm-12">
	<h2>
		<fmt:message key="branch.form.heading" />
	</h2>
	<p>
		<fmt:message key="branch.form.message" />
	</p>
</div>
<div class="col-sm-12">
	<s:form action="saveBranch" enctype="multipart/form-data" method="post"
		validate="true" id="partnerForm" cssClass="well">
		<s:hidden key="branch.id" />

		<div class="row">
			<div class="col-sm-6">
				<s:textfield key="branch.branchName" required="true"
					autofocus="true" cssClass="form-control" />
			</div>
			<c:if test="${branch.id != '' && branch.id != 'null'}">
				<div class="col-sm-6">
					<s:textfield key="branch.username" required="true"
						autofocus="true" cssClass="form-control" />
				</div>
				<div class="col-sm-6">
					<s:textfield key="branch.password" required="true"
						autofocus="true" cssClass="form-control" />
				</div>
			</c:if>
			<div class="col-sm-6">
				<label for="partner" class="control-label"><fmt:message
						key="branch.form.partner" /></label> <select id="partner"
					name="branch.partner.id" class="form-control">
					<c:forEach items="${availablePartners}" var="partner">
						<option value="${partner.value}"
							${fn:contains(branch.partner.id, partner.value) ? 'selected' : ''}>${partner.label}</option>
					</c:forEach>
				</select>
			</div>
		</div>

		<div class="row">
			<div class="col-sm-12">
				<s:textfield key="branch.address1" cssClass="form-control" />
			</div>
		</div>

		<div class="row">
			<div class="col-sm-12">
				<s:textfield key="branch.address2" cssClass="form-control" />
			</div>
		</div>

		<div class="row">
			<div class="col-sm-6">
				<s:textfield key="branch.landmark" cssClass="form-control" />
			</div>
			<div class="col-sm-6">
				<s:hidden key="branch.area.city.id" id="cityId" required="true" />
				<s:textfield key="branch.area.city.cityName" id="city"
					cssClass="form-control" />
			</div>
		</div>

		<div class="row">
			<div class="col-sm-6">
				<s:hidden key="branch.area.id" id="areaId" />
				<s:textfield key="branch.area.areaName" id="area"
					cssClass="form-control" required="true" />
			</div>
			<div class="col-sm-6">
				<s:set name="country" value="branch.area.city.countryCode"
					scope="page" />
				<div class="form-group">
					<label for="branch.address.country"> <fmt:message
							key="branch.address.country" />
					</label>
					<appfuse:country name="branch.area.city.countryCode" prompt=""
						default="${country}" />
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-sm-4">
				<s:textfield key="branch.noOfResource" cssClass="form-control" />
			</div>
			<div class="col-sm-4">
				<s:textfield key="branch.openTime" cssClass="form-control" />
			</div>
			<div class="col-sm-4">
				<s:textfield key="branch.closeTime" cssClass="form-control" />
			</div>
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
	initializeTypeahead('city', '/meta/city-suggestions');
	initializeAreaTypeahead('area', '/meta/area-suggestions');
</script>