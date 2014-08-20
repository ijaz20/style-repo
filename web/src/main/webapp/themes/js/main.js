/*price range*/

$('#sl2').slider();

var RGBChange = function() {
	$('#RGB').css(
			'background',
			'rgb(' + r.getValue() + ',' + g.getValue() + ',' + b.getValue()
					+ ')')
};

function scrollSideMenu() {
	if ($(window).width() < 1200) {
		if ($("#left-sidebar").css("position") == "fixed") {
			$("#left-sidebar").css("position", "relative");
		}
	} else {
		if ($("#left-sidebar").css("position") == "relative") {
			$("#left-sidebar").css("position", "fixed");
		}
	}
}

function initializeCartWindow(){
    $(".cart-line-noOrder").addClass('active').siblings().addClass('hide');
}

function bindCartDetailEvent(){
    $("#addRow").on("click", function(){

    });
}

$("#category-filters a").click(function(e){
    var href = $(this).attr("href");//get the href so we can navigate later
    if($(this).find("input:checkbox").is(':checked')){
    	$(this).find("input:checkbox").prop('checked', false);
    } else {
    	$(this).find("input:checkbox").prop('checked', true);
    }
    filterProducts(true, "features_products", 0);
});

$("#brand-filters a").click(function(e){
    var href = $(this).attr("href");//get the href so we can navigate later
    if($(this).find("input:checkbox").is(':checked')){
    	$(this).find("input:checkbox").prop('checked', false);
    } else {
    	$(this).find("input:checkbox").prop('checked', true);
    }
    filterProducts(true, "features_products", 0);
});

function populateOfferFromProduct(elem) {
        var elementId = elem.attr("id");
        var productId = elementId.split('_')[1];
            if($("#displayOffer_"+productId).val() != "true") {
                $.ajax({
                    url: "/vstyleu/get-product",
                    type: "GET",
                    asyn: false,
                    data: {
                        id: productId
                    },
                    dataType: "json",
                    success: function (response) {
                        var priceDetails = '<div class="row table-responsive">'
                            + '<table id="branchDetails" class="table table-striped">'
                            + '<tr>'
                            + '<th class="text-center">Branch Name</th>'
                            + '<th class="text-center">Price</th>'
                            + '<th class="text-center">Add</th>'
                            + '</tr>';
                        for (var i in response.productPrices) {
                            priceDetails = priceDetails + '<tr>'
                                + '<td class="text-center" id=branchName_' + response.productPrices[i].priceId + '>' + response.productPrices[i].branch.branchName + '</td>'
                                + '<td class="text-center" id=branchPrice_' + response.productPrices[i].priceId + '>' + response.productPrices[i].price + '</td>'
                                + '<td class="text-center"><input type="button" data-select-product-id="'+productId+'" onclick="addCartFromOfferList($(this))" id="addCart_' + response.productPrices[i].priceId + '" value="Add List" class="btn-primary col-sm-8" /></td>'
                                + '</tr>';
                        }
                        priceDetails = priceDetails + '</table></div>';
                        $('<div/>', {
                            id: elementId + "_price",
                            class: "col-sm-6",
                            html: priceDetails
                        }).insertAfter($("#" + elementId + "_image"));
                        $("#displayOffer_" + productId).val("true")
                        //Don't delete this comment we need to show descripton later.
                        //createDiv("row").html(response.description).insertAfter("#product_"+productId);
                    }
                });
            }

            $("#" + elementId + "_price").removeClass("hide");
            $(".product-details-div").toggleClass("col-sm-4 col-sm-12");
            $("#" + elementId + "_image").toggleClass("col-sm-12 col-sm-4");
            $("#close_"+ productId).removeClass("hide");
            $("#product_"+productId).addClass("hide");
}

$(".deleteProduct").click(function(){
    var priceElement = $(this).prev();
    var productElement = priceElement.prev();
    console.log(priceElement);
    console.log(productElement);
});

function constructProductNameInCart(productId, priceId){

    var productName = $("#productName_"+productId).text();
    var branchPrice = $("#branchPrice_"+priceId).text();
    var branchName =  $("#branchName_"+priceId).text();
    var cartElement = createDiv("row").append(createSpan(productName+"("+branchName+")", "col-sm-8", "cartProductName_"+productId));
    cartElement.append(createSpan(branchPrice, "col-sm-2", "cartPrice_"+priceId));
    cartElement.append(createSpan("&times;", "col-sm-2 close deleteProduct"));
    $(".deleteProduct").bind('click')
    calculateSubtotal(branchPrice);
    $(".cart-added-products").append(cartElement);
}

var productIdList = [];
var priceIdList = [];
function checkCartDuplication(productId, priceId){
    if($.inArray(productId, productIdList) != -1){
        $('#cart-error-message').fadeIn();
        setTimeout(function(){
            $('#cart-error-message').fadeOut();
        }, 2000);
        return false;
    }
    else {
        productIdList.push(productId);
        priceIdList.push(priceId);
    }
    constructProductNameInCart(productId, priceId);
    closeOfferDisplay($("#close_"+productId));
    return true;
}

function calculateSubtotal(branchPrice){
    var subTotal = parseInt($(".cart-subtotal").text());
    subTotal+=parseInt(branchPrice);
    $(".cart-subtotal").text(subTotal);
}

function addCartFromOfferList(elem){
    var priceId = elem.attr("id").split('_')[1];
    var productId = elem.data("select-product-id");
    $(".cart-line-noOrder").addClass('hide').siblings().removeClass('hide');
    return checkCartDuplication(productId, priceId);
}

function closeOfferDisplay(elem){
    var elementId = elem.attr("id");
    var productId = elementId.split('_')[1];
    $(".product-details-div").toggleClass("col-sm-12 col-sm-4");
    $("#product_" + productId + "_image").toggleClass("col-sm-4 col-sm-12");
    $("#product_" + productId + "_price").addClass("hide");
    elem.addClass("hide");
    $("#product_"+productId).removeClass("hide");
}

function filterProducts(isFilter, renderId, productCount){
	var categories = [];
	var brands = [];
    $('#category-filters input:checked').each(function() {
    	categories.push($(this).val());
    });
    $('#brand-filters input:checked').each(function() {
    	brands.push($(this).val());
    });
    
    $
	.ajax({
		url : "/vstyleu/filter-products",
		type : "POST",
		data : {
			productCount:productCount,
			categories:categories,
			brands:brands
		},
		dataType : "json",
		success : function(response) {
			if (response != null
					&& response != '') {
				var productsHtml = "";
				for ( var i in response) {
					// alert(response.products[i].name);
					productsHtml = productsHtml
							+ '<div class="col-sm-4" id="'
							+ response[i].id
							+ '">'
							+ '<div class="product-image-wrapper">'
							+ '<div class="single-products">'
							+ '<div class="productinfo text-center">'
							+ '<a href="/vstyleu/product-details?productName='+response[i].productName+'&productId='+response[i].id+'">'
							+ '<img src="/images/products/'
							+ response[i].productName
							+ '.jp" alt="" />'
							+'</a>'
							+ '<h2>'
							+ response[i].price.currencyCode
							+ '' + response[i].price.price
							+'</h2>'
							+ '<p class="text-capitalize">'
							+ response[i].productName
							+ '</p>'							
							+ '</div>'
							+ '</div>'
							+ '</div>'
							+ '</div>'
				}
				if(isFilter){
					$("#" + renderId).html(productsHtml);
				} else {
					$("#" + renderId).append(productsHtml);
				}
			} else {
				// need to add no product found
				if(isFilter){
					$("#" + renderId).html("No Results Found");
				} else {
					//$("#" + renderId).append(productsHtml);
				}
			}
		}
	});
}

/* scroll to top */

$(window).scroll(function() {
	if ($("#features_products").length > 0) {
		var scrollTop = $(this).scrollTop();
		var moreProductsTop = $("#more_products").offset().top;
		// scrollSideMenu();
		if ($(window).scrollTop() == $(document).height() - $(window).height()) {
			filterProducts(false, "features_products", $('#features_products').children().length);
		}
	}
	// Updates scroll position to find scroll down/ up

});

$(document).ready(function() {
	if ($("#left-sidebar").length > 0) {
		scrollSideMenu();
	}
    $("#fbLogin").on("click",function(){
        console.log("test")
        checkLoginState();
    });
	$(function() {
		$(window).slideUp({
			scrollName : 'scrollUp', // Element ID
			scrollDistance : 300, // Distance from top/bottom before showing
									// element (px)
			scrollFrom : 'top', // 'top' or 'bottom'
			scrollSpeed : 300, // Speed back to top (ms)
			easingType : 'linear', // Scroll to top easing (see
									// http://easings.net/)
			animation : 'fade', // Fade, slide, none
			animationSpeed : 200, // Animation in speed (ms)
			scrollTrigger : false, // Set a custom triggering element. Can be
									// an HTML string or jQuery object
			// scrollTarget: false, // Set a custom target element for scrolling
			// to the top
			scrollText : '<i class="fa fa-angle-up"></i>', // Text for element,
															// can contain HTML
			scrollTitle : false, // Set a custom <a> title if required.
			scrollImg : false, // Set true to use image
			activeOverlay : false, // Set CSS color to display scrollUp active
									// point, e.g '#00FFFF'
			zIndex : 2147483647
		// Z-Index for the overlay
		});
	});
    initializeCartWindow();
    bindCartDetailEvent();

});

/**
 * TODO: Not implemented now. To be done in future by Ijaz
 */
function hideFilters() {
	$("#withFilterContent").addClass("hide");
	$("#withoutFilterContent").removeClass("hide");
}

/**
 * TODO: Not implemented now. To be done in future by Ijaz
 */
function showFilters() {
	$("#withoutFilterContent").addClass("hide");
	$("#withFilterContent").removeClass("hide");
}

function statusChangeCallback(response) {
    console.log('statusChangeCallback');
    console.log(response);
    // The response object is returned with a status field that lets the
    // app know the current login status of the person.
    // Full docs on the response object can be found in the documentation
    // for FB.getLoginStatus().
    if (response.status === 'connected') {
        // Logged into your app and Facebook.
        testAPI();
    } else if (response.status === 'not_authorized') {
        // The person is logged into Facebook, but not your app.
        document.getElementById('status').innerHTML = 'Please log ' +
            'into this app.';
    } else {
        // The person is not logged into Facebook, so we're not sure if
        // they are logged into this app or not.
        document.getElementById('status').innerHTML = 'Please log ' +
            'into Facebook.';
    }
}


// This function is called when someone finishes with the Login
// Button.  See the onlogin handler attached to it in the sample
// code below.
function checkLoginState() {
    FB.getLoginStatus(function(response) {
        statusChangeCallback(response);
    });
}

window.fbAsyncInit = function() {
    FB.init({
        appId      : '785611088139231',
        cookie     : true,  // enable cookies to allow the server to access
        // the session
        xfbml      : true,  // parse social plugins on this page
        version    : 'v2.1' // use version 2.1
    });

    // Now that we've initialized the JavaScript SDK, we call
    // FB.getLoginStatus().  This function gets the state of the
    // person visiting this page and can return one of three states to
    // the callback you provide.  They can be:
    //
    // 1. Logged into your app ('connected')
    // 2. Logged into Facebook, but not your app ('not_authorized')
    // 3. Not logged into Facebook and can't tell if they are logged into
    //    your app or not.
    //
    // These three cases are handled in the callback function.

    FB.getLoginStatus(function(response) {
        statusChangeCallback(response);
    });

};

// Load the SDK asynchronously
(function(d, s, id) {
    var js, fjs = d.getElementsByTagName(s)[0];
    if (d.getElementById(id)) return;
    js = d.createElement(s); js.id = id;
    js.src = "//connect.facebook.net/en_US/sdk.js";
    fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));

// Here we run a very simple test of the Graph API after login is
// successful.  See statusChangeCallback() for when this call is made.
function testAPI() {
    console.log('Welcome!  Fetching your information.... ');
    FB.api('/me', function(response) {
        console.log('Successful login for: ' + response.name);
        document.getElementById('status').innerHTML =
            'Thanks for logging in, ' + response.name + '!';
    });
}