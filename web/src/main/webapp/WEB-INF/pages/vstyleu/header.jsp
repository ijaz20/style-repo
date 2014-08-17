<%@ include file="/common/taglibs.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>

<header id="header">
	<!-- <div class="header_top hidden">
		<div class="container">
			<div class="row">
				<div class="col-sm-6 ">
					<div class="contactinfo">
						<ul class="nav nav-pills">
							<li><a href=""><i class="fa fa-phone"></i> +2 95 01 88
									821</a></li>
							<li><a href=""><i class="fa fa-envelope"></i>
									info@domain.com</a></li>
						</ul>
					</div>
				</div>
				<div class="col-sm-6">
					<div class="social-icons pull-right">
						<ul class="nav navbar-nav">
							<li><a href=""><i class="fa fa-facebook"></i></a></li>
							<li><a href=""><i class="fa fa-twitter"></i></a></li>
							<li><a href=""><i class="fa fa-linkedin"></i></a></li>
							<li><a href=""><i class="fa fa-dribbble"></i></a></li>
							<li><a href=""><i class="fa fa-google-plus"></i></a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div> -->
	<!--/header_top-->

	<div class="header-middle">
		<!--header-middle-->
		<div class="container">
			<div class="row">
				<div class="col-sm-4">
					<div class="logo pull-left">
						<a href="/"><img alt="vstyleu" src="/themes/images/home/vstyleu.png"></a>
					</div>
				</div>
				<div class="col-sm-8">
					<div class="mainmenu pull-right">
						<ul class="nav navbar-nav collapse navbar-collapse">
							<li><a href="/">Home</a></li>
							<li class="dropdown"><a href="#" class="active">Shop<i
									class="fa fa-angle-down"></i></a>
								<ul role="menu" class="sub-menu">
									<li><a href="shop.html" class="active">Products</a></li>
									<li><a href="product-details.html">Product Details</a></li>
									<li><a href="checkout.html">Checkout</a></li>
									<li><a href="cart.html">Cart</a></li>
								</ul></li>
							<li><a href="javascript:void(0)" id="loginLink" data-toggle="modal" data-target="#myModal">
                                Login
                            </a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--/header-middle-->

    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <h4 class="modal-title" id="myModalLabel">Login</h4>
                </div>
                    <jsp:include page="login.jsp"/>
            </div>
        </div>
    </div>


	<div class="header-bottom">
		<!--header-bottom-->
		<div class="container">
			<div class="row">
				<div class="col-sm-3 pull-right">
					<!-- <div class="input-group">
						<input type="text" class="form-control" placeholder="Search"
							name="search">
						<div class="input-group-btn">
							<button class="btn btn-default" type="submit">
								<i class="glyphicon glyphicon-search"></i>
							</button>
						</div>
					</div> -->
				</div>

				<div class="col-sm-9">
					<div class="col-sm-6">
							<input type="text" id="city" class="form-control" placeholder="city"
								name="city">
					</div>
					<div class="col-sm-6">
							<input type="text" id="area" class="form-control typeahead"  placeholder="area"
								name="area">
					</div>
				</div>
			</div>
		</div>
	</div>
</header>