<%@ include file="/common/taglibs.jsp" %>

<head>
    <title><fmt:message key="category.form.title"/></title>
    <meta name="menu" content="Category"/>
</head>

<div class="col-sm-2">
    <h2><fmt:message key="category.form.heading"/></h2>
    <p><fmt:message key="category.form.message"/></p>
</div>
<div class="col-sm-7">
	<s:form action="saveCategory" enctype="multipart/form-data" method="post" validate="true" id="partnerForm" cssClass="well">
        <s:hidden key="category.id"/>

		<div class="row">
        	<s:textfield key="category.categoryName" required="true" autofocus="true" cssClass="form-control"/>
		</div>
		
        <div class="row">
        	<table id = "branchDetails" tabindex="5" cellpadding=20>
	        	<tr>
		        	<th>Partner</th>
		        	<th>Branch</th>
	        	</tr>
	        	<tr>
	        		<td>
						<select id="partner" class="form-control" onchange="loadBranches();">
			                <c:forEach items="${availablePartners}" var="partner">
			                    <option value="${partner.value}" >${partner.label}</option>
			                </c:forEach>
			            </select>	        		
	        		</td>
	        		<td>
	        			<select id="branches" name="branches" class="form-control" multiple="true">
			                <c:forEach items="${branches}" var="branch">
			                    <%-- <option value="${branch.id}" ${fn:contains(category.branch.id, branch.id) ? 'selected' : ''}>${branch.branchName}</option> --%>
			                </c:forEach>
			            </select>
	        		</td>
	        		<td>
	        			<a href="#" onclick="cloneBranchRow();">Add</a>
	        		</td>
	        		<td>
	        			<a href="#" onclick="removeBranchRow(this);">Remove</a>
	        		</td>
	        	</tr>
            </table>
		</div>
		
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

<script type="text/javascript">
var rowCount = 2;

function loadBranches(thisObject){
	var branchOptions = '';
	<c:forEach items="${branches}" var="branch">
		if('${branch.partner.id}' == thisObject.val()){
    		branchOptions = branchOptions+'<option value="${branch.id}">${branch.branchName}</option>';
		}
	</c:forEach>
	$('#branches').html(branchOptions);
	
}

$(document).ready(function() {
		loadBranches($('#partner'));
});

function cloneBranchRow(){
	var partnerOptions = '';
	var branchOptions = '';
	var row = "<tr>"
	row = row+'<td><select id="partner'+rowCount+'" class="form-control" onchange="loadBranches(this);">';
    <c:forEach items="${availablePartners}" var="partner">
        row = row+'<option value="${partner.value}" ${fn:contains(branch.partner.id, partner.value) ? "selected" : ""}>${partner.label}</option>';
    </c:forEach>
	row = row+'</select></td>';
	row= row + '<td><select id="branches'+rowCount+'" name="branches" class="form-control" multiple="true">';
        <c:forEach items="${branches}" var="branch">
           	row = row + '<option value="${branch.id}" ${fn:contains(category.branch.id, branch.id) ? "selected" : ""}>${branch.branchName}</option>';
        </c:forEach>
    row = row + '</select></td>';
    row = row + '</tr>';
	$('#branchDetails').append(row);
}
</script>