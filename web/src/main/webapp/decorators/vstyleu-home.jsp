<!DOCTYPE html>
<%@ include file="/common/taglibs.jsp"%>
<html lang="en">

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<title>VstyleU</title>
<link href="/themes/css/bootstrap.min.css" rel="stylesheet">
<link href="/themes/css/font-awesome.min.css" rel="stylesheet">
<link href="/themes/css/prettyPhoto.css" rel="stylesheet">
<link href="/themes/css/price-range.css" rel="stylesheet">
<link href="/themes/css/animate.css" rel="stylesheet">
<link href="/themes/css/main.css" rel="stylesheet">
<link href="/themes/css/responsive.css" rel="stylesheet">
    <!-- Loading Flat UI -->
    <link href="/themes/css/flat-ui.css" rel="stylesheet">
    <link href="/themes/css/style.css" rel="stylesheet">
    <link href="/themes/css/newstyle.css" rel="stylesheet">

    <link rel="shortcut icon" href="/images/favicon.ico">
<!--[if lt IE 9]>
    <script src="/themes/js/html5shiv.js"></script>
    <script src="/themes/js/respond.min.js"></script>
    <![endif]-->
<link rel="shortcut icon" href="/themes/images/ico/favicon.ico">
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="/themes/images/ico/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="/themes/images/ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="/themes/images/ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed"
	href="/themes/images/ico/apple-touch-icon-57-precomposed.png">
</head>
<!--/head-->

<body>
	
	<jsp:include page="/WEB-INF/pages/vstyleu/header.jsp" />
	<jsp:include page="/WEB-INF/pages/vstyleu/main.jsp" />
	<jsp:include page="/WEB-INF/pages/vstyleu/footer.jsp" />

	<script src="/themes/js/jquery.js"></script>
	<script src="/themes/js/price-range.js"></script>
	<!-- <script src="/themes/js/jquery.scrollUp.min.js"></script> -->
    <script src="/themes/js/bootstrap-select.js"></script>
    <script src="/themes/js/bootstrap-switch.js"></script>
    <script src="/themes/js/flatui-checkbox.js"></script>
    <script src="/themes/js/application.js"></script>
    <script src="/themes/js/flatui-radio.js"></script>
    <script src="/themes/js/jquery.tagsinput.js"></script>
    <script src="/themes/js/jquery.placeholder.js"></script>
    <script src="/themes/js/jquery-ui-1.10.3.custom.min.js"></script>
    <script src="/bootstrap/js/holder-dropdown.js"></script>
	<script src="/themes/js/bootstrap.min.js"></script>
	<script src="/themes/js/jquery.prettyPhoto.js"></script>
	<script src="/themes/js/typeahead.js"></script>
    <script src="/themes/js/jquery.hoverIntent.js"></script>
    <script src="/themes/js/jquery.lazyload.js"></script>
	<script src="/themes/js/main.js"></script>
    <script src="/themes/js/nav-bar.js"></script>
	<script src="/themes/js/social.js"></script>
    <script src="/themes/js/Login.js"></script>
    <script src="/themes/js/util.js"></script>
    <script>
        $(".banner a[href^='#']").on('click', function(e) {
        // prevent default anchor click behavior
        e.preventDefault();
        // animate
        $('html, body').animate({
        scrollTop: $(this.hash).offset().top
        }, 300, function(){
        // when done, add hash to url
        // (default click behaviour)
        window.location.hash = this.hash;
        });
        });
    </script>
</body>

</html>