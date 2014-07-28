<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="partner.form.title"/></title>
    <meta name="menu" content="Partner"/>
</head>

<div class="col-sm-3">
    <h2><fmt:message key="partner.form.heading"/></h2>
    <p><fmt:message key="partner.form.message"/></p>
</div>
<div class="col-sm-7">
    <s:form action="savePartner" enctype="multipart/form-data" method="post" validate="true" id="partnerForm" cssClass="well">
    	<s:hidden key="partner.id"/>
    	<div class="row">
            <div class="col-sm-6">
                <s:textfield key="partner.partnerName" required="true" cssClass="form-control"/>
            </div>
            <div class="col-sm-6">
                <s:file name="file" label="Logo"/>
            </div>
        </div>
        
        
        <div id="actions" class="form-group">
            <s:submit type="button" key="button.save" name="save" cssClass="btn btn-primary" theme="simple">
                <i class="icon-upload icon-white"></i>
                <fmt:message key="button.save"/>
            </s:submit>

            <a class="btn btn-default" href="home" >
                <i class="icon-remove"></i>
                <fmt:message key="button.cancel"/>
            </a>
        </div>
    </s:form>
</div>