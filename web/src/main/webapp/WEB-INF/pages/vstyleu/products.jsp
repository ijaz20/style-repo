<%@ include file="/common/taglibs.jsp"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>

<div class="col-md-9 col-sm-9 col-lg-9 content-right-nav">
    <div class="title">Featured products</div>
    <div class="content-area" id="features_products">
        <jsp:include page="/WEB-INF/pages/vstyleu/ajax-product.jsp" />
    </div>
</div>

<div class="modal fade in" id="product_offer" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <jsp:include page="/WEB-INF/pages/vstyleu/product-details.jsp" />
</div>
        <%--<s:iterator var="product" value="%{products}" status="rowstatus">--%>
            <%--<div class="col-sm-4 product-details-div">--%>
            	<%--<div class="row">--%>
                    <%--<img src="/images/products/close-red.png" style="cursor: pointer" onclick="closeOfferDisplay($(this))" id="close_<s:property value='id'/>" class="hide"/>--%>
	                <%--<div class="product-image-wrapper col-sm-12" id="product_<s:property value="id"/>_image">--%>
	                    <%--<div class="single-products">--%>
	                        <%--<div class="productinfo">--%>
	                            <%--<a href="javascript:void(0)">--%>
	                            <%--<img--%>
	                                <%--src="<s:property value="imagePath"/>"--%>
	                                <%--alt="<s:property value="productName"/>" height="150" width="150"/>--%>
	                            <%--</a>--%>
	                            <%--<div class="">--%>
		                            <%--<p class="text-capitalize">--%>
		                                <%--<span id="productName_<s:property value="id"/>"><s:property value="productName"/></span>--%>
		                            <%--</p>--%>
	                            <%--</div>--%>
	                        <%--</div>--%>
	                    <%--</div>--%>
	                <%--</div>--%>
                    <%--<input type="button" onclick="populateOfferFromProduct($(this))" id="product_<s:property value="id"/>" value="Check Offer" class="btn-primary" />--%>
                <%--</div>--%>
                <%--<input type="hidden" value="false" id="displayOffer_<s:property value='id'/>"/>--%>
            <%--</div>--%>
        <%--</s:iterator>--%>
