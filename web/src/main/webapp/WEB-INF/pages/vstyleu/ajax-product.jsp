<%@ include file="/common/taglibs.jsp"%>

<s:iterator var="product" value="%{products}" status="rowstatus">
    <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12 product-bucket" id="product_<s:property value='%{id}'/>">
        <a href="javascript:void(0)"><img class="prod-image" title="<s:property value="productName"/>" alt="<s:property value="productName"/>" src="<s:property value="imagePath"/>">
            <strong><s:property value="productName"/></strong>
            <span class="product-details"><s:property value="%{description}"/> </span>
            <%--<span class="bucket-rupee"><img src="/images/rupee.png" alt="<s:property value="productName"/>">95</span>--%>
                <span class="bucket-details pull-right">
                <img src="/images/menu-cart-button.png">Check Offer</span>
        </a>
        <div class="clearfix"></div>
    </div>
</s:iterator>