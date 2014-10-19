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

$(".checkbox").change(function(e){
    var href = $(this).attr("href");//get the href so we can navigate later
    filterProducts(true, "features_products", 0);
});

$("#preorder-button").on('click', function(){
    $('#priceIdList').val(priceIdList);
    $('#cartPage').submit();
});


$(document).on( 'click', '.deleteProduct', function(){
    var priceElement = $(this).prev();
    $(this).parent().remove();
    priceIdList = jQuery.grep(priceIdList, function(value) {
        return value != priceElement.attr("id").split('_')[1];
    });
    calculateSubtotal(priceElement.text(), 'remove')
    if($.isEmptyObject(priceIdList)){
        $(".cart-subtotal").text('0');
        $(".cart-line-noOrder").removeClass('hide').siblings().addClass('hide');
    }
});


function constructProductNameInCart(productId, priceId){

    var productName = $("#productName_"+productId).text();
    var branchPrice = $("#branchPrice_"+priceId).text();
    var branchName =  $("#branchName_"+priceId).text();
    var cartElement = createDiv("row").append(createSpan(productName+"("+branchName+")", "col-sm-8"));
    cartElement.append(createSpan(branchPrice, "col-sm-2", "cartPrice_"+priceId));
    cartElement.append(createSpan("&times;", "col-sm-2 close deleteProduct"));
    calculateSubtotal(branchPrice, 'add');
    $(".cart-added-products").append(cartElement);
}

var priceIdList = [];
function checkCartDuplication(productId, priceId){
    if($.inArray(priceId, priceIdList) != -1){
        $('#cart-error-message').fadeIn();
        setTimeout(function(){
            $('#cart-error-message').fadeOut();
        }, 2000);
        return false;
    }
    else {
        priceIdList.push(priceId);
    }
    constructProductNameInCart(productId, priceId);
    return true;
}

function calculateSubtotal(branchPrice, operation){
    var subTotal = parseInt($(".cart-subtotal").text());
    if(operation == 'add')
        subTotal+=parseInt(branchPrice);
    else if(operation == 'remove')
        subTotal-=parseInt(branchPrice);
    $(".cart-subtotal").text(subTotal);
}

function addCartFromOfferList(elem){
    var priceId = elem.attr("id").split('_')[1];
    var productId = elem.data("select-product-id");
    $(".cart-line-noOrder").addClass('hide').siblings().removeClass('hide');
    return checkCartDuplication(productId, priceId);
}
function updateCart(elem) {
    var elementId = elem.attr("id");
    var offerId = elementId.split('_')[1];
    var url = "/vstyleu/saveBooking?ajax=true"
    var paramMap = {
        offerId: offerId,
        bookingId:$("#bookingId").val()
    };
    $.post(url, paramMap, function (response) {
            if (response != null && response != '') {
                $("#product_offer").modal('hide');

            }
        }
    );
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
    $('.category input:checked').each(function() {
    	categories.push($(this).val());
    });
    $('.brand input:checked').each(function() {
    	brands.push($(this).val());
    });

    var url = "/vstyleu/filter-products?ajax=true"
    var paramMap = {
        productCount:productCount,
        categories:categories,
        brands:brands
    };
    $.post(url,paramMap,function(response) {
			if (response != null && response != '') {
				if(isFilter){
					$("#" + renderId).html(response);
				} else {
					$("#" + renderId).append(response);
                    updateProducts = true;
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
	);
}

/* scroll to top */
var updateProducts = true ;
$(window).scroll(function() {
	if ($("#features_products").length > 0) {

        $(window).scroll(function() {
            if($(window).scrollTop() + $(window).height() > $(document).height() - 100 && updateProducts) {
                updateProducts = false;
                filterProducts(false, "features_products", $('#features_products').children().length);
            }
        });
	}
	// Updates scroll position to find scroll down/ up

});


function populateOfferFromProduct(elem) {
    var elementId = elem.attr("id");
    var productId = elementId.split('_')[1];
    var url = "/vstyleu/get-product?ajax=true"
    var paramMap = {
        id: productId
    };
    $.post(url,paramMap,function(response) {
            if (response != null && response != '') {
                $("#product_offer").html('');
                $("#product_offer").html(response);
                $("#product_offer").modal('show');
            }
        }
    );
}

function showProductDetails(){
    $(".product-bucket").on('click', function(){
        populateOfferFromProduct($(this));
    });
}
function generateCartDetails(){
    $(".container").on('click', '.add-to-cart' ,function(){
        updateCart($(this));
    })
}
$(function(){
	if ($("#left-sidebar").length > 0) {
		scrollSideMenu();
	}
    $("#fbLogin").on("click",function(){
        console.log("test")
        checkLoginState();
    });
    showProductDetails();
    generateCartDetails();
    // jQuery UI Datepicker JS init
    var datepickerSelector = '#datepicker-01';
    $(datepickerSelector).datepicker({
        showOtherMonths: true,
        selectOtherMonths: true,
        dateFormat: "d MM, yy",
        yearRange: '-1:+1'
    }).prev('.btn').on('click', function (e) {
        e && e.preventDefault();
        $(datepickerSelector).focus();
    });

// Now let's align datepicker with the prepend button
    $(datepickerSelector).datepicker('widget').css({'margin-left': -$(datepickerSelector).prev('.btn').outerWidth()});


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
