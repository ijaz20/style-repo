<%@ include file="/common/taglibs.jsp"%>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<s:iterator var="product" value="%{products}" status="rowstatus">
    <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 product-bucket pad-left">
        <a href="#"><img src="<s:property value="imagePath"/>">
            <strong><s:property value="productName"/></strong>
            <span class="product-details"><s:property value="%{description}"/> </span>
            <%--<span class="bucket-rupee"><img src="/images/rupee.png" alt="<s:property value="productName"/>">95</span>--%>
                <span class="bucket-details pull-right">
                <img src="/images/menu-cart-button.png">Check Offer</span></a>
    </div>
</s:iterator>