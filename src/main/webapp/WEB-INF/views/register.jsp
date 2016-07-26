<%@include file="jspf/header.jspf"%>
		<!-- registration-form -->
<div class="registration-form">
	<div class="container">
		<div class="dreamcrub">
			<ul class="breadcrumbs">
				<li class="home">
					<a href="/" title="Go to Home Page">Home</a>&nbsp;
					<span>&gt;</span>
				</li>
				<li class="women">
					Registration
				</li>
			</ul>
			<div class="clearfix"></div>
		</div>
		<h2>Registration</h2>
		<div class="registration-grids">
			<div class="reg-form">
				<div class="reg">
					 <p>Welcome, please enter the following details to continue.</p>
					 <p>If you have previously registered with us, <a href="/login">click here</a></p>
					 <form action="/registration" method="post">
						 <ul>
							 <li class="text-info">Login: </li>
							 <li><input type="text" name="login" id="login" pattern=".{3,20}"
										title="input 3-20 characters"  required></li>
						 </ul>
						 <ul>
							 <li class="text-info">First Name: </li>
							 <li><input type="text" name="firstName" id="firstName" pattern=".{1,15}"
										title="input 1-15 characters"  required></li>
						 </ul>
						 <ul>
							 <li class="text-info">Last Name: </li>
							 <li><input type="text" name="secondName" id="secondName" pattern=".{1,20}"
										title="input 1-20 characters"  required></li>
						 </ul>				 
						<ul>
							 <li class="text-info">Email: </li>
							 <li><input type="email" name="email" id="email"
										title="input valid email" required></li>
						 </ul>
						 <ul>
							 <li class="text-info">Password: </li>
							 <li><input type="password" name="password" id="password" pattern=".{3,45}"
										title="input 3-45 characters" required></li>
						 </ul>
						 <ul>
							 <li class="text-info">Birthday:</li>
							 <li><input type="date" name="birthday" id="birthday"
										min="1900-01-01" max="2010-01-01"
										title="input birthday" required></li>
						 </ul>
						 <input type="submit" value="Join">
					 </form>
				 </div>
			</div>
			<div class="reg-right">
				 <div class="strip"></div>
				 <div class="strip"></div>
			</div>
			<div class="clearfix"></div>
		</div>
	</div>
</div>
<!-- registration-form -->

<%@include file="jspf/footer.jspf"%>