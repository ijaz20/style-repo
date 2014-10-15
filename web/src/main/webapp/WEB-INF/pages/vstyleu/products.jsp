<%@ include file="/common/taglibs.jsp"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>

<s:hidden name="mobileSite" id="mobileSite" value="%{mobileSite}"/>
<div class="col-md-9 col-sm-9 col-lg-9 content-right-nav">
    <div class="title">Featured products</div>
    <div class="content-area" id="features_products">
        <jsp:include page="/WEB-INF/pages/vstyleu/ajax-product.jsp" />
    </div>
</div>

<div class="modal fade in" id="product_offer" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <jsp:include page="/WEB-INF/pages/vstyleu/product-details.jsp" />
</div>