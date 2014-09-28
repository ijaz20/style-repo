<%@ include file="/common/taglibs.jsp"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>

<div class="col-md-9 col-sm-9 col-lg-9 content-right-nav">
    <div class="title">Featured products</div>
    <%--<div class="popupbox col-md-12 col-sm-12 pad-left pad-right">--%>
    <%--<div class="col-md-4 col-xs-12 col-lg-4 col-sm-4 popup-left-content pad-left">--%>
    <%--<a href="#"><img src="/images/product-img.jpg"><strong>New Products names</strong><span class="product-details">Aenean sodales pharetra nulla et venenatis. In hac habitasse platea dictumst...</span><span class="bucket-rupee"><img src="/images/rupee.png">95</span><span class="bucket-details pull-right"><img src="/images/menu-cart-button.png">Details</span></a>--%>
    <%--</div>--%>
    <%--<div class="col-md-8 col-xs-12 col-lg-8 col-sm-8 popup-right-content pad-left pad-right"><div class="contact-section">--%>
    <%--<ul>--%>
    <%--<li class="col-md-4">--%>
    <%--<select name="info" class="mbn">--%>
    <%--<optgroup label="Profile">--%>
    <%--<option value="0">My Profile</option>--%>
    <%--<option value="1">My Friends</option>--%>
    <%--</optgroup>--%>
    <%--</select></li>--%>
    <%--<li class="col-md-4"><select name="info" class="mbn">--%>
    <%--<optgroup label="Profile">--%>
    <%--<option value="0">My Profile</option>--%>
    <%--<option value="1">My Friends</option>--%>
    <%--</optgroup>--%>

    <%--</select></li>--%>
    <%--<li class="col-md-4"><select name="info" class="mbn">--%>
    <%--<optgroup label="Profile">--%>
    <%--<option value="0">My Profile</option>--%>
    <%--<option value="1">My Friends</option>--%>
    <%--</optgroup>--%>
    <%--</select></li>--%>

    <%--</ul>--%>
    <%--<div class="specification">--%>
    <%--<dl>--%>
    <%--<dt>Branch Name</dt>--%>
    <%--<dd>Price</dd>--%>
    <%--<dd>Available time</dd>--%>
    <%--<dd>Buy/Add</dd>--%>
    <%--</dl>--%>
    <%--<dl><dt><strong>The Park</strong>Nungambakkam</dt>--%>
    <%--<dd><img src="/images/rupee.png"> 95</dd>--%>
    <%--<dd><select><optgroup><li>slfssll</li></optgroup></select></dd>--%>
    <%--<dd><a href="#">Add to Cart</a></dd>--%>
    <%--</dl>--%>
    <%--<dl><dt><strong>The Park</strong>Nungambakkam</dt>--%>
    <%--<dd><img src="/images/rupee.png"> 95</dd>--%>
    <%--<dd><select><optgroup><li>slfssll</li></optgroup></select></dd>--%>
    <%--<dd><a href="#">Add to Cart</a></dd>--%>
    <%--</dl>--%>
    <%--<dl><dt><strong>The Park</strong>Nungambakkam</dt>--%>
    <%--<dd><img src="/images/rupee.png"> 95</dd>--%>
    <%--<dd><select><optgroup><li>slfssll</li></optgroup></select></dd>--%>
    <%--<dd><a href="#">Add to Cart</a></dd>--%>
    <%--</dl>--%>
    <%--<dl><dt><strong>The Park</strong>Nungambakkam</dt>--%>
    <%--<dd><img src="/images/rupee.png"> 95</dd>--%>
    <%--<dd><select><optgroup><li>slfssll</li></optgroup></select></dd>--%>
    <%--<dd><a href="#">Add to Cart</a></dd>--%>
    <%--</dl>--%>
    <%--<dl><dt><strong>The Park</strong>Nungambakkam</dt>--%>
    <%--<dd><img src="/images/rupee.png"> 95</dd>--%>
    <%--<dd><select><optgroup><li>slfssll</li></optgroup></select></dd>--%>
    <%--<dd><a href="#">Add to Cart</a></dd>--%>
    <%--</dl>--%>

    <%--</div>--%>
    <%--</div></div>--%>
    <%--</div>--%>
    <div class="content-area" id="features_products">
        <jsp:include page="/WEB-INF/pages/vstyleu/ajax-product.jsp" />
    </div>
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
