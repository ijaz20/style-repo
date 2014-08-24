<%@ include file="/common/taglibs.jsp"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<div class="features_items" id="features_items">
    <!--features_items-->
    <h2 class="title text-center">Features Items</h2>
    <div id="features_products">
        <s:iterator var="product" value="%{products}" status="rowstatus">
            <div class="col-sm-4 product-details-div">
            	<div class="row">
                    <img src="/images/products/close-red.png" style="cursor: pointer" onclick="closeOfferDisplay($(this))" id="close_<s:property value='id'/>" class="hide"/>
	                <div class="product-image-wrapper col-sm-12" id="product_<s:property value="id"/>_image">
	                    <div class="single-products">
	                        <div class="productinfo">
	                            <a href="javascript:void(0)">
	                            <img
	                                src="<s:property value="imagePath"/>"
	                                alt="<s:property value="productName"/>" height="150" width="150"/>
	                            </a>
	                            <div class="">
		                            <p class="text-capitalize">
		                                <span id="productName_<s:property value="id"/>"><s:property value="productName"/></span>
		                            </p> 
	                            </div>                           
	                        </div>
	                    </div>
	                </div>
                    <input type="button" onclick="populateOfferFromProduct($(this))" id="product_<s:property value="id"/>" value="Check Offer" class="btn-primary" />
                </div>
                <input type="hidden" value="false" id="displayOffer_<s:property value='id'/>"/>
            </div>
        </s:iterator>
    </div>
    <div id="more_products"></div>

</div>
<!--features_items-->
