<%@ include file="/common/taglibs.jsp" %>

<head>
    <title><fmt:message key="productList.title"/></title>
    <meta name="menu" content="AdminMenu"/>
</head>

<div class="col-sm-10">
    <h2><fmt:message key="productList.heading"/></h2>

    <form method="get" action="${ctx}/admin/products" id="searchForm" class="form-inline">
    <div id="search" class="text-right">
        <span class="col-sm-9">
            <input type="text" size="20" name="q" id="query" value="${param.q}"
                   placeholder="<fmt:message key="search.enterTerms"/>" class="form-control input-sm"/>
        </span>
        <button id="button.search" class="btn btn-default btn-sm" type="submit">
            <i class="icon-search"></i>
            <fmt:message key="button.search"/>
        </button>
    </div>
    </form>

    <div id="actions" class="btn-group">
        <a class="btn btn-primary" href="<c:url value='/admin/showProduct?method=Add&from=list'/>" >
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/>
        </a>
        <a class="btn btn-default" href="<c:url value="/home"/>">
            <i class="icon-ok"></i> <fmt:message key="button.done"/>
        </a>
    </div>

    <display:table name="products" cellspacing="0" cellpadding="0" requestURI=""
                   defaultsort="1" id="products" pagesize="25" class="table table-condensed table-striped table-hover" export="true">
        <display:column property="id" escapeXml="true" sortable="true" titleKey="product.id" style="width: 25%"
                        url="/admin/showProduct?from=list" paramId="id" paramProperty="id"/>
        <display:column property="productName" escapeXml="true" sortable="true" titleKey="product.productName" style="width: 25%"/>
        <display:column property="gender" escapeXml="true" sortable="true" titleKey="product.form.gender" style="width: 25%"/>
        <display:column property="category.categoryName" escapeXml="true" sortable="true" titleKey="product.form.category" style="width: 25%"/>
        <display:column sortProperty="isActive" sortable="true" titleKey="product.active"
                        style="width: 16%; padding-left: 15px" media="html">
            <input type="checkbox" <c:if test="${products.isActive}">checked="checked"</c:if>/>
        </display:column>
        <display:column property="isActive" titleKey="product.active" media="csv xml excel pdf"/>
        <display:setProperty name="paging.banner.item_name"><fmt:message key="productList.product"/></display:setProperty>
        <display:setProperty name="paging.banner.items_name"><fmt:message key="productList.products"/></display:setProperty>

        <display:setProperty name="export.excel.filename" value="Product List.xls"/>
        <display:setProperty name="export.csv.filename" value="Product List.csv"/>
        <display:setProperty name="export.pdf.filename" value="Product List.pdf"/>
    </display:table>
</div>
