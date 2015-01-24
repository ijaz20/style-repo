<%@ include file="/common/taglibs.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>

<div class="menu-section header-main">
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
                    <span class="login">
                        <a href="javascript:void(0)">Contact us</a>
                            <c:choose>
                                <c:when test="${pageContext.request.remoteUser != null}">
                                    <input type="hidden" id="loginUser" name="loginUser" value="${pageContext.request.remoteUser}" />
                                    <a id="loginLink" href="/logout"><i class="glyphicon glyphicon-user user-icon"></i> <span class="userRight">Hi, ${pageContext.request.remoteUser}</span><i style="margin-left: 4px" class="glyphicon glyphicon-collapse-down"></i></a>
                                </c:when>
                                <c:otherwise>
                                    <a href="javascript:void(0)" id="loginLink" data-toggle="modal" data-target="#loginModal">log in</a>
                                </c:otherwise>
                            </c:choose>
                    </span>
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
                <jsp:include page="login.jsp"/>
            </div><!-- /.container-fluid -->
        </nav>
    </div>
</div>