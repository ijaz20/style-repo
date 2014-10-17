// Tabs for second level
$(".menu-nav-tabs a").hoverIntent({over: function(e){
        $(this).tab("show")
    }, interval: 125}
);

$(".menu-nav-tabs a").on('click', function (e) {
    $(this).tab("show");
});

// For Image Lazy Load
$(".navbar-nav").on('hover', function (e) {
    $("img.lazy").lazyload();
});

// Function for closing menus
var unhighlightMenu = function(){
    $('ul.navbar-nav li.dd-present').removeClass('active');
    $(".tab-content.submenu > .tab-pane").removeClass('active');
}

$('ul.navbar-nav li.dd-present a').hoverIntent({over: function(e){
        $(this).tab("show")
    },interval: 125}
);

$('ul.navbar-nav li').not(".dd-present").hoverIntent({over: function(e){
        if($('ul.navbar-nav li.dd-present').hasClass('active')) {
            unhighlightMenu();
        }
    },interval: 125}
);

$(".tab-content.submenu").on("mouseleave", function(){
    unhighlightMenu();
});




