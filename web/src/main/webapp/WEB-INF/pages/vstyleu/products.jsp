<%@ include file="/common/taglibs.jsp"%>

<div class="col-md-3 col-sm-3 col-lg-3 left-nav">
        <div class="leftnav-products">
            <strong>Categories</strong>
            <ul class="leftnav-product-list text-capitalize">
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
            <ul class="leftnav-product-list text-capitalize">
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
 
<s:hidden name="mobileSite" id="mobileSite" value="%{mobileSite}"/>
<div class="col-md-9 col-sm-9 col-lg-9 content-right-nav">
    <div class="title">Featured products</div>
    <div class="content-area" id="features_products">
        <jsp:include page="/WEB-INF/pages/vstyleu/ajax-product.jsp" />
    </div>
</div>

<div class="modal fade in" id="product-offer" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <jsp:include page="/WEB-INF/pages/vstyleu/product-details.jsp" />
</div>