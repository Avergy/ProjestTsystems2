<%@include file="jspf/header.jspf"%>
<div class="content">
	<div class="container">
		<div class="login-page">
			<div class="dreamcrub">
				<ul class="breadcrumbs">
					<li class="home">
						<a href="/" title="Go to Home Page">Home</a>&nbsp;
						<span>&gt;</span>
                    </li>
                    <li class="women">Login</li>
                </ul>
                <div class="clearfix"></div>
            </div>
			<div class="account_grid">
                <div class="col-md-6 login-left wow fadeInLeft" data-wow-delay="0.4s">
                    <h2>NEW CUSTOMERS</h2>
                    <p>By creating an account with our store, you will be able to move through the checkout process
                        faster, store multiple shipping addresses,
                        view and track your orders in your account and more.</p>
                    <a class="acount-btn" href="/registration">Create an Account</a>
                </div>
                <div class="col-md-6 login-right wow fadeInRight" data-wow-delay="0.4s">
                    <h3>REGISTERED CUSTOMERS</h3>
                    <p>If you have an account with us, please log in.</p>
                    <spring:url var="authUrl" value="/static/j_spring_security_check" />
                    <form class="login_form"  name="user" action="${authUrl}" method="POST" id="login_form">
						<div>
							<span>Login:</span>
							<input type="text" name="j_username" pattern=".{3,20}" title="input 3-20 characters" id="login">
						</div>
						<div>
                            <span>Password:</span>
							<input type="password" name="j_password" pattern=".{3,45}" title="input 3-45 characters" id="password">
						</div>
						<input type="submit" value="Login">
					</form>
				</div>
				<div class="clearfix"> </div>
			</div>
		</div>
	</div>
</div>
<%@include file="jspf/footer.jspf"%>