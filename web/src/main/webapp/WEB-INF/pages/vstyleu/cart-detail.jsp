<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
    <input type="hidden" name="bookingId" id="bookingId" value="<s:property value="%{booking.bookingId}"/>"/>
    <ul class="nav navbar-nav menu-section-left">

        <li class="ll-link"><a class="dropdown-toggle" data-hover="dropdown" data-toggle="dropdown" href="#"><strong><s:property value="%{booking == null? 0:booking.bookingDetails.size()}"/></strong><img src="/images/menu-cart-button.png"></a>
            <ul class="tabbable dropdown-menu popover bottom cc-sub">
                <li class="tab-lesft">
                    <ul class="nav nav-tabs nav-append-content">
                        <s:if test="%{booking.bookingDetails != null && booking.bookingDetails.size() > 0}">
                            <s:iterator var="booking" value="booking.bookingDetails">
                                <li><a href="#"><s:property value="%{#booking.product.productName}"/></a></li>
                            </s:iterator>
                        </s:if>
                        <s:else>
                            <li><a href="javascript:void(0)">No style in your cart</a></li>
                        </s:else>
                    </ul>
                </li>
                <!-- /tab-content -->
            </ul>
        </li>
    </ul>
</div>
