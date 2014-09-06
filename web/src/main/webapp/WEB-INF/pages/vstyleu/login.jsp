<%@ include file="/common/taglibs.jsp"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>

	<section id="form"><!--form-->
		<div class="container">
			<div class="row">
                <!-- <button class="btn btn-default" id="fbLogin">Connect With Facebook</button> -->
                <div><!--login form-->
                    <a href="javascript:void(0)" id="showLogin">Login</a>
                    <a>&nbsp;|&nbsp;</a>
                    <a href="javascript:void(0)" id="showSingup" >Signup!</a><br/><br/>
                    <!-- <form action="#"> -->
                </div>
                        <div class="col-sm-4 login-form" id="showLoginDiv"><!--login form-->
						<form method="post" id="loginForm" action="<c:url value='/j_security_check'/>"
    onsubmit="saveUsername(this);return validateForm(this)" class="form-signin" autocomplete="off">
							<input type="text" placeholder="Name" name="j_username"/>
							<!-- <input type="email" placeholder="Email Address"/> -->
							<input type="text" placeholder="password" name="j_password"/>
							<span>
								<input type="checkbox" class="checkbox">
								Keep me signed in
							</span>
							<button class="btn btn-default">Login</button>

                        </form>
                    </div>
				</div>
				<div class="col-sm-4  signup-form hide"  id="showSingupDiv"><!--sign up form-->
					<form action="#">
						<input type="text" placeholder="Name"/>
						<input type="email" placeholder="Email Address"/>
						<input type="password" placeholder="Password"/>
						<button type="submit" class="btn btn-default">Signup</button>
					</form>
				</div><!--/sign up form-->
		</div>

		<fb:login-button autologoutlink="true" scope="public_profile,email,user_address,user_mobile_phone" size="large"
                 onlogin="onlogin="checkLoginState();"">
		  Login
		</fb:login-button>

        <!-- <fb:login-button scope="public_profile,email,phone,username" onlogin="checkLoginState();">
		</fb:login-button> -->
		
		<div id="status">
		</div>

	</section><!--/form-->