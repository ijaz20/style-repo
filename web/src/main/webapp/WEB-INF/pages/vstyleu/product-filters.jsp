<%@ include file="/common/taglibs.jsp"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<div class="left-sidebar" id="left-sidebar">
	<!--/category-productsr-->

    <div class="brands_products">
        <h2>Category</h2>
        <div class="brands-name" id="category-filters">
            <ul class="nav nav-pills nav-stacked">
                <c:forEach items="${availableCategories}" var="category">
                    <li>
                        <div class="checkbox">
                            <label>
                                <a class="text-capitalize" href="#" ><input type="checkbox" value="${category.value}" class="margin-left"> ${category.label}</a>
                            </label>
                        </div>
                    </li> 
                </c:forEach>
            </ul>
        </div>
    </div>
    
    <div class="brands_products">
        <!--brands_products-->
        <h2>Brands</h2>
        <div class="brands-name" id="brand-filters">
            <ul class="nav nav-pills nav-stacked">
                <c:forEach items="${availablePartners}" var="brand">
                    <li>
                        <div class="checkbox">
                            <label>
                                <a class="text-capitalize" href="#" ><input type="checkbox" value="${brand.value}" class="margin-left"> ${brand.label}</a>
                            </label>
                        </div>
                    </li> 
                </c:forEach>
            </ul>
        </div>
    </div>

</div>