<%@ include file="/common/taglibs.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>

<div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close fui-cross" data-dismiss="modal" aria-hidden="true"></button>
            <h4 class="modal-title">Hurrah! <s:property value="%{product.productPrices.size()}"/> offers found</h4>
        </div>

        <div class="modal-body">
            <div class="popupbox col-md-12 col-sm-12 pad-left pad-right">
                <div class="col-md-4 col-xs-12 col-lg-4 col-sm-4 popup-left-content pad-left">
                    <a href="#"><img alt="<s:property value="%{product.productName}"/>" src="<s:property value="%{imagePath}"/>"><strong><s:property value="%{product.productName}"/> </strong><span
                            class="product-details"><s:property value="%{product.description}"/></span><span
                            class="bucket-rupee"></span></a>
                </div>
                <div class="col-md-8 col-xs-12 col-lg-8 col-sm-8 popup-right-content pad-left pad-right"><div class="contact-section">
                    <div class="" style="padding: 10px 0px; float: left;">
                        <div class="col-md-4">
                            <input type="text" name="area" placeholder="Enter Location" class="form-control" /></div>
                        <div class="col-md-4">
                            <div class="">
                                <div class="input-group">
                                        <span class="input-group-btn">
                                          <button class="btn" type="button"><span class="fui-calendar"></span></button>
                                        </span>
                                    <input type="text" class="form-control" value="14-March-2013" id="datepicker-01"/>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <s:select list="list_timeInterval" name="preferableTimeInterval" id="preferableTimeInterval" cssClass="form-control" theme="simple"/>
                        </div>
                    </div>
                    <div class="specification">
                        <dl>
                            <dt>Branch Name</dt>
                            <dd>Price</dd>
                            <dd>Available time</dd>
                            <dd>Add</dd>
                            <dd class="mobi-hide">Buy</dd>
                        </dl>
                        <s:iterator var="offerList" value="%{product.productPrices}">
                            <dl><dt><strong><s:property value="%{#offerList.branch.branchName}"/></strong></dt>
                                <dd><img src="/images/rupee.png"> <s:property value="%{#offerList.price}"/></dd>
                                <dd>
                                    <s:select list="%{#offerList.branch.availableTimes}" name="availableTimeList" id="availableTimeList_<s:property value='%{#offerList.priceId}'/>" cssClass="form-control" theme="simple"/>
                                </dd>
                                <dd><button id="product_<s:property value='%{#offerList.priceId}'/>" class="add-to-cart btn btn-primary">Add to Cart</button></dd>
                                <dd class="mobi-hide"><button id="pay_product_<s:property value='%{#offerList.priceId}'/>" class="add-to-cart btn btn-primary">Proceed To Pay</button></dd>
                            </dl>
                        </s:iterator>
                    </div>
                </div></div>
            </div>
        </div>

        <div class="modal-footer">

        </div>
    </div>
</div>