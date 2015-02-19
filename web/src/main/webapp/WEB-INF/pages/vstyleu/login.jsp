<%@ include file="/common/taglibs.jsp"%>

<div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="continueLabel1" aria-hidden="true">
    <div class="modal-dialog loginpopup">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fa fa-times"></i></button>
                <h2 class="modal-title" id="continueLabel1">Log in to VstyleU</h2>
            </div>
            <div class="modal-body">
                <div class="row login-box">

                    <div class="login-inform col-xs-12 col-sm-6 col-lg-6 cold-md-6 hide-on-mobile">
                        <h2>Create an account with</h2>
                        <p>Why Facebook/Google plus?</p>
                        <ul class="login-benefits">
                            <li>We don't post on your wall</li>
                            <li>We don't access your private data</li>
                            <li>We don't post on your friend wall.</li>
                        </ul>
                        <p></p>
                    </div>
                    <div class="login-details col-xs-12 col-sm-6 col-lg-6 cold-md-6">
                        <div class="socialbtn">
                            <a class="btn btn-block btn-social btn-google-plus">
                                <i class="fa fa-google-plus"></i> Login with Google Plus
                            </a>
                            <a class="btn btn-block btn-social btn-facebook">
                                <i class="fa fa-facebook"></i> Login with Facebook
                            </a>
                        </div>
                        <%--<fb:login-button autologoutlink="true" scope="public_profile,email,user_address,user_mobile_phone" size="large"--%>
                        <%--onlogin="onlogin="checkLoginState();"">--%>
                        <%--Login--%>
                        <!-- Nav tabs -->
                        <div class="logintab">
                            <ul class="nav nav-tabs" role="tablist">
                                <li class="active"><a href="#exituser" role="tab" data-toggle="tab">Existing User</a></li>
                                <li><a href="#newuser" role="tab" data-toggle="tab">New User</a></li>
                            </ul>
                        </div>
                        <!-- Tab panes -->
                        <div class="tab-content">
                            <div class="tab-pane active" id="exituser">
                                <form method="post" id="loginForm" action="<c:url value='/j_security_check'/>"
                                      onsubmit="saveUsername(this);return validateForm(this)" class="form-signin" autocomplete="off">

                                    <div class="form-group">
                                        <label for="Username">Email</label>
                                        <input type="text" placeholder="Enter your email/username" name="j_username" id="j_username"class="form-control">
                                    </div>
                                    <div class="form-group">
                                        <label for="Password">Password</label>
                                        <input type="password" placeholder="Password" id="j_password" name="j_password" class="form-control">
                                    </div>
                                    <div class="checkbox checked">
                                        <label>
                                            <span class="icons"><span class="first-icon fui-checkbox-unchecked"></span><span class="second-icon fui-checkbox-checked"></span></span><input type="checkbox"> Remember me on this computer
                                        </label>

                                    </div>
                                    <div class="forgetpwd"><a href="javascript:void(0)">Forget Password?</a></div>
                                    <div class="row loginbtn">
                                        <div class="col-sm-12 col-lg-12 cold-md-12">
                                            <button type="button" id="loginButton" class="btn btn-primary">Login</button>
                                        </div>
                                    </div>

                                </form>

                            </div>
                            <div class="tab-pane" id="newuser">
                                <form name="signupForm" action="saveSignup" method="post" validate="true"
                                        autocomplete="off" cssClass="well">
                                <div class="form-group">
                                        <label for="j_username">Email</label>
                                        <input type="text" class="form-control" id="Username" name="user.username"  placeholder="Enter your email/username">
                                    </div>
                                    <div class="form-group">
                                        <label for="j_password">Password</label>
                                        <input type="password" class="form-control" id="Password" name="user.password" placeholder="Password">
                                    </div>
                                    <div class="checkbox checked">
                                        <label>
                                            <span class="icons"><span class="first-icon fui-checkbox-unchecked"></span><span class="second-icon fui-checkbox-checked"></span></span><input type="checkbox"> Remember me on this computer
                                        </label>

                                    </div>
                                    <div class="row loginbtn">
                                        <div class="col-sm-12 col-lg-12 cold-md-12">
                                            <button type="submit" class="btn btn-primary">Create account</button>
                                        </div>
                                    </div>

                                </form>
                            </div>
                        </div>


                    </div>
                </div>
            </div>
        </div>
    </div>
</div>