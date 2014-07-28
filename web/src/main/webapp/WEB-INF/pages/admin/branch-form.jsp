<%@ include file="/common/taglibs.jsp" %>

<head>
    <title><fmt:message key="branch.form.title"/></title>
    <meta name="menu" content="Branch"/>
</head>

<div class="col-sm-2">
    <h2><fmt:message key="branch.form.heading"/></h2>
    <p><fmt:message key="branch.form.message"/></p>
</div>
<div class="col-sm-7">
	<s:form action="saveBranch" enctype="multipart/form-data" method="post" validate="true" id="partnerForm" cssClass="well">
        <s:hidden key="branch.id"/>

		<div class="row">
        	<s:textfield key="branch.branchName" required="true" autofocus="true" cssClass="form-control"/>
		</div>
		
        <div class="row">
          	<label for="partner" class="control-label"><fmt:message key="branch.form.partner"/></label>
            <select id="partner" name="branch.partner.id" class="form-control">
                <c:forEach items="${availablePartners}" var="partner">
                    <option value="${partner.value}" ${fn:contains(branch.partner.id, partner.value) ? 'selected' : ''}>${partner.label}</option>
                </c:forEach>
            </select>
        </div>
		
        <fieldset>
            <legend class="accordion-heading">
                <a data-toggle="collapse" href="#collapse-address"><fmt:message key="branch.address.address"/></a>
            </legend>
            <div id="collapse-address" class="accordion-body collapse">
                <s:textfield key="branch.address.address" cssClass="form-control"/>

                <div class="row">
                    <div class="col-sm-7">
                        <s:textfield key="branch.address.city" cssClass="form-control"/>
                    </div>
                    <div class="col-sm-2">
                        <s:textfield key="branch.address.province" cssClass="form-control"/>
                    </div>
                    <div class="col-sm-3">
                        <s:textfield key="branch.address.postalCode" cssClass="form-control"/>
                    </div>
                </div>

                <s:set name="country" value="branch.address.country" scope="page"/>
                <div class="form-group">
                    <label for="branch.address.country">
                        <fmt:message key="branch.address.country"/>
                    </label>

                    <appfuse:country name="branch.address.country" prompt="" default="${country}"/>
                </div>
            </div>
        </fieldset>

        <div id="actions" class="form-group">
            <s:submit type="button" cssClass="btn btn-primary" key="button.save" theme="simple">
                <i class="icon-ok icon-white"></i>
                <fmt:message key="button.save"/>
            </s:submit>
            <s:submit type="button" cssClass="btn btn-default" method="cancel" key="button.cancel" theme="simple">
                <i class="icon-remove"></i>
                <fmt:message key="button.cancel"/>
            </s:submit>
        </div>
        </s:form>
    </div>

