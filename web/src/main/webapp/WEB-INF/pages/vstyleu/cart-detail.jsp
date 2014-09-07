<%@ taglib prefix="s" uri="/struts-tags" %>
<div id="cart" class="row">
    <div id="cart-fixed-wrapper" class="col-sm-12">
        <div class="cart-content-wrapper">
            <div class="cart-content-header">
                YOUR ORDER
            </div>
            <div class="cart-content-teaser clearfix">
                <div class="cart-vendor-title"></div>
                <div class="cart-vendor-image">
                    <img title="" alt="" src="">
                </div>
            </div>
            <div class="cart-content-body">
                <div class="cart-content-separator"></div>
                <div class="cart-content-orders-container">
                    <div class="row cart-line-noOrder">
                        <div>
                            <span class="col-sm-12">Select your style!</span>
                        </div>
                    </div>
                    <div class="cart-added-products">
                        <div id="cart-error-message" class="alert alert-danger alert-dismissable" style="display: none" >
                            <a href="#" data-dismiss="alert" class="close">&times;</a>
                            <span>Product already added.</span>
                        </div>
                    </div>
                    <div class="row">
                        <span class=""><hr></span>
                    </div>
                    <div class="row cart-subtotal-div">
                        <span class="col-sm-4">Subtotal</span>
                        <span class="col-sm-4 pull-right cart-subtotal">0</span>
                    </div>
                    <div class="row">
                        <span class=""></span>
                    </div>
                    <div class="row whitespace">
                        <form id="cartPage" action="saveBooking" method="POST">
                            <input type="hidden" id="priceIdList" name="priceIdList" data-price-id-list="" data-product-id-list="" />
                            <span class="col-sm-12">
                                <a title="" class="btn btn-default btn-block btn-lg" id="preorder-button">
                                Proceed to checkout
                                </a>
                            </span>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div class="cart-content-footer text-center hide">
             <span class="cart-footer-info-icon"></span>
             <span class="cart-footer-info-text">
                 Delivery in: 1h
             </span>
        </div>
    </div>
</div>