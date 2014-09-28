<%@ include file="/common/taglibs.jsp"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
	<!--/category-productsr-->
    <div class="col-md-3 col-sm-3 col-lg-3 left-nav">
        <div class="leftnav-products">
            <strong>Categories</strong>
            <ul class="leftnav-product-list">
                <c:forEach items="${availableCategories}" var="category">
                <li>
                    <label class="category checkbox" for="${category.label}">
                        <input type="checkbox" value="${category.value}" id="category_${category.label}" data-toggle="checkbox"/>
                        ${category.label}
                    </label>
                </li>
                </c:forEach>
            </ul>
            <strong>Brands</strong>
            <ul class="leftnav-product-list">
                <c:forEach items="${availablePartners}" var="brand">
                    <li><label class="brand checkbox" for="${brand.label}">
                        <input type="checkbox" value="${brand.value}" id="brand_${brand.label}" data-toggle="checkbox"/>
                            ${brand.label}
                    </label>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>

    <%--<div class="brands_products">--%>
        <%--<h2>Category</h2>--%>
        <%--<div class="brands-name" id="category-filters">--%>
            <%--<ul class="nav nav-pills nav-stacked">--%>
                <%--<c:forEach items="${availableCategories}" var="category">--%>
                    <%--<li>--%>
                        <%--<div class="checkbox">--%>
                            <%--<label>--%>
                                <%--<a class="text-capitalize" href="#" ><input type="checkbox" value="${category.value}" class="margin-left"> ${category.label}</a>--%>
                            <%--</label>--%>
                        <%--</div>--%>
                    <%--</li> --%>
                <%--</c:forEach>--%>
            <%--</ul>--%>
        <%--</div>--%>
    <%--</div>--%>
    <%----%>
    <%--<div class="brands_products">--%>
        <%--<!--brands_products-->--%>
        <%--<h2>Brands</h2>--%>
        <%--<div class="brands-name" id="brand-filters">--%>
            <%--<ul class="nav nav-pills nav-stacked">--%>
                <%--<c:forEach items="${availablePartners}" var="brand">--%>
                    <%--<li>--%>
                        <%--<div class="checkbox">--%>
                            <%--<label>--%>
                                <%--<a class="text-capitalize" href="#" ><input type="checkbox" value="${brand.value}" class="margin-left"> ${brand.label}</a>--%>
                            <%--</label>--%>
                        <%--</div>--%>
                    <%--</li> --%>
                <%--</c:forEach>--%>
            <%--</ul>--%>
        <%--</div>--%>
    <%--</div>--%>