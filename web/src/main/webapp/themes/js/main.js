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

function loadMoreProducts(scrollTop, moreProductsTop, renderId) {
	if ($(window).scrollTop() == $(document).height() - $(window).height()) {
		$
				.ajax({
					url : "/vstyleu/filter-products",
					type : "POST",
					data : {
						name : "test"
					},
					dataType : "json",
					success : function(response) {
						if (response.products != null
								&& response.products != '') {
							var productsHtml = "";
							for ( var i in response.products) {
								// alert(response.products[i].name);
								productsHtml = productsHtml
										+ '<div class="col-sm-4" id="'
										+ response.products[i].id
										+ '">'
										+ '<div class="product-image-wrapper">'
										+ '<div class="single-products">'
										+ '<div class="productinfo text-center">'
										+ '<a href="/vstyleu/product-details?productName='+response.products[i].productName+'&productId='+response.products[i].id+'>'
										+ '<img src="/images/products/'
										+ response.products[i].productName
										+ '.jp" alt="" />'
										+'</a>'
										+ '<h2>'
										+ response.products[i].price.currencyCode
										+ '' + response.products[i].price.price
										+'</h2>'
										+ '<p>'
										+ response.products[i].productName
										+ '</p>'
										+ '<a href="#" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Add to cart</a>'
										+ '</div>'
										+ '</div>'
										+ '<div class="choose">'
										+ '<ul class="nav nav-pills nav-justified">'
										+ '<li><a href=""><i class="fa fa-plus-square"></i>Add to wishlist</a></li>'
										+ '<li><a href=""><i class="fa fa-plus-square"></i>Add to compare</a></li>'
										+ '</ul>' + '</div>' + '</div>'
										+ '</div>'
							}
							$("#" + renderId).append(productsHtml);
						} else {
							// need to add no product found
						}
					}
				});
	}
}

/*
 * function scrollSideMenu() { if ($(window).scrollTop() >= ($(window).height() -
 * $('#footer') .height())) { if ($("#left-sidebar").css("position") == "fixed") {
 * $("#left-sidebar").css("position", "relative"); } } else { if
 * ($("#left-sidebar").css("position") == "relative") {
 * $("#left-sidebar").css("position", "fixed"); } } }
 */

/* scroll to top */

$(window).scroll(function() {
	if ($("#features_products").length > 0) {
		var scrollTop = $(this).scrollTop();
		var moreProductsTop = $("#more_products").offset().top;
		// scrollSideMenu();
		loadMoreProducts(scrollTop, moreProductsTop, "features_products");
	}
	// Updates scroll position to find scroll down/ up

});

$(document).ready(function() {
	if ($("#left-sidebar").length > 0) {
		scrollSideMenu();
	}
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

function hideFilters() {
	$("#withFilterContent").addClass("hide");
	$("#withoutFilterContent").removeClass("hide");
}

function showFilters() {
	$("#withoutFilterContent").addClass("hide");
	$("#withFilterContent").removeClass("hide");
}