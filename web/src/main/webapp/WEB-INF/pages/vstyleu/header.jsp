<%@ include file="/common/taglibs.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>

<div class="menu-section">
    <div class="container">
        <nav class="navbar navbar-default menu-container header-section" role="navigation">
            <div class="container-fluid">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"> <span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button>
                    <a class="navbar-brand logo-section" href="/"><img src="/images/logo.png" class="logo"/></a>
                </div>
                <!-- Collect the nav links, forms, and other content for toggling -->

                <div class="menu-section-right pull-right">
                    <span class="login"><a href="javascript:void(0)" id="loginLink" data-toggle="modal" data-target="#myModal">log in</a><a href="#">Contacts</a></span>
                    <div class="header-cart">
                        <div class="input-group">
                            <span class="input-group-addon"><img src="/images/search-icon.png"></span>
                            <input type="text" class="form-control" placeholder="Search" />
                        </div>
                        <ul class="cart-menu">

                        <jsp:include page="cart-detail.jsp"/>
                        </ul>
                    </div>

                </div><!-- /.navbar-collapse -->
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
            </div><!-- /.container-fluid -->
        </nav>
    </div>
</div>