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

$('div[id^="product_"]').on('click', function(){
	var elementId = $(this).attr("id");	
	var productId = elementId.split('_')[1];
	$.ajax({
		url : "/vstyleu/get-product",
		type : "GET",
		asyn : false,
		data : {
			id:productId
		},
		dataType : "json",
		success : function(response) {
			var priceDetails = '<div class="row table-responsive">'
				+'<table id="branchDetails" class="table table-striped">'
				+'<tr>'
					+'<th class="text-center">Branch Name</th>'
					+'<th class="text-center">Price</th>'
					+'<th class="text-center">Add</th>'
					+'<th class="text-center">Proceed To Pay</th>'
				+'</tr>';
				for ( var i in response.productPrices) {
					priceDetails = priceDetails+'<tr>'
					+'<td class="text-center">'+response.productPrices[i].branch.branchName+'</td>'
					+'<td class="text-center">'+response.productPrices[i].price+'</td>'
					+'<td class="text-center"><input type="button" id="addRow" value="Add List" class="btn btn-primary col-sm-8" /></td>'
					+'<td class="text-center"><input type="button" id="addRow" value="Proceed" class="btn btn-primary col-sm-8" /></td>'
					+'</tr>';
				}
				priceDetails = priceDetails+'</table></div>';
			$("#"+elementId+"_price").html(priceDetails);
			$("#"+elementId+"_details").html(response.description);
		}
	});
	 
	 $("#"+elementId).toggleClass("col-sm-4","col-sm-12");
	 $("#"+elementId+"_image").toggleClass("col-sm-12","col-sm-4");
	 $("#"+elementId+"_price").addClass("col-sm-8");
});

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