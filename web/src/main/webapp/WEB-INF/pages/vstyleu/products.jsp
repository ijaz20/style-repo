<%@ include file="/common/taglibs.jsp"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<div class="features_items" id="features_items">
    <!--features_items-->
    <h2 class="title text-center">Features Items</h2>
    <div id="features_products">
        <s:iterator var="product" value="%{products}" status="rowstatus">
            <div class="col-sm-4" id="product_<s:property value="id"/>">
            	<div class="row">
	                <div class="product-image-wrapper col-sm-12" id="product_<s:property value="id"/>_image">
	                    <div class="single-products">
	                        <div class="productinfo">
	                            <a href="javascript:void(0)">
	                            <img
	                                src="<s:property value="imagePath"/>"
	                                alt="<s:property value="productName"/>" height="200" width="200"/>
	                            </a>
	                            <div class="">
		                            <p class="text-capitalize">
		                                <s:property value="productName" />
		                            </p> 
	                            </div>                           
	                        </div>
	                    </div>
	                </div>
	                <div id="product_<s:property value="id"/>_price">
	                </div>
                </div>
                <div class="row">
                	<div id="product_<s:property value="id"/>_details">
                	
                	</div>
                </div>
            </div>
        </s:iterator>
    </div>
    <div id="more_products"></div>

</div>
<!--features_items-->
