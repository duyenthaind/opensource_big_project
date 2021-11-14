<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- header -->
<jsp:include page="common/header.jsp" />
<%@ include file="../variable.jsp"%>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<!-- end header -->

<hr>
<div class="container bootstrap snippet">
	<div class="row">
		<div class="col-sm-10">
			<h1>Your profile</h1>
		</div>

	</div>
	<div class="row">
		<div class="col-sm-3">
			<!--left col-->


			<div class="text-center">
				<img src="http://ssl.gstatic.com/accounts/ui/avatar_2x.png"
					width="170px" height="70px" class="avatar img-circle img-thumbnail"
					alt="avatar">
				<h6>Upload a different photo...</h6>
				<input type="file" class="text-center center-block file-upload">
			</div>
			</hr>
			<br>


			<ul class="list-group">
				<li class="list-group-item text-muted">Activity <i
					class="fa fa-dashboard fa-1x"></i></li>
				<li class="list-group-item text-right"><span class="pull-left"><strong>Shares</strong></span>
					125</li>
				<li class="list-group-item text-right"><span class="pull-left"><strong>Likes</strong></span>
					13</li>
				<li class="list-group-item text-right"><span class="pull-left"><strong>Posts</strong></span>
					37</li>
				<li class="list-group-item text-right"><span class="pull-left"><strong>Followers</strong></span>
					78</li>
			</ul>

		</div>
		<!--/col-3-->
		<div class="col-sm-9">
			<ul class="nav nav-tabs" id="myTab" role="tablist">
				<li class="nav-item"><a class="nav-link active" id="udetail"
					data-toggle="tab" href="#home" role="tab" aria-controls="home"
					aria-selected="true">Detail</a></li>
				<li class="nav-item"><a class="nav-link" id="uorder"
					data-toggle="tab" href="#profile" role="tab"
					aria-controls="profile" aria-selected="false">Orders</a></li>
			</ul>


			<div class="tab-content">
				<div class="tab-pane active" id="home1">
					<hr>
					<form class="form" action="##" method="post" id="registrationForm">
						<div class="form-group">

							<div class="col-xs-6">
								<label for="name"><h4>Full name</h4></label> <input type="text" value"${user.name}"
									class="form-control" name="name" id="name" placeholder="name"
									title="enter your first name if any.">
							</div>
						</div>

						<div class="form-group">

							<div class="col-xs-6">
								<label for="phone"><h4>Phone</h4></label> <input type="text" value"${user.name}"
									class="form-control" name="phone" id="phone"
									placeholder="enter phone"
									title="enter your phone number if any.">
							</div>
						</div>

						<div class="form-group">

							<div class="col-xs-6">
								<label for="email"><h4>Email</h4></label> <input type="email" value"${user.email}"
									class="form-control" name="email" id="email"
									placeholder="you@email.com" title="enter your email.">
							</div>
						</div>
						<div class="form-group">

							<div class="col-xs-6">
								<label for="address"><h4>Address</h4></label> <input type="text" value"${user.address}"
									class="form-control" id="location" placeholder="somewhere"
									title="enter a location">
							</div>
						</div>
						<div class="form-group">

							<div class="col-xs-6">
								<label for="password"><h4>Password</h4></label> <input
									type="password" class="form-control" name="password"
									id="password" placeholder="password" value"${user.password}"
									title="enter your password.">
							</div>
						</div>
						<div class="form-group">

							<div class="col-xs-6">
								<label for="password2"><h4>Verify</h4></label> <input
									type="password" class="form-control" name="password2"
									id="password2" placeholder="password2" value"${user.password}"
									title="enter your password2.">
							</div>
						</div>
						<div class="form-group">
							<div class="col-xs-12">
								<br>
								<button class="btn btn-lg btn-success" type="submit">
									<i class="glyphicon glyphicon-ok-sign"></i> Save
								</button>
								<button class="btn btn-lg" type="reset">
									<i class="glyphicon glyphicon-repeat"></i> Reset
								</button>
							</div>
						</div>
					</form>

					<hr>

				</div>
				<div id="demo1" class="collapse">Lorem ipsum dolor sit amet,
					consectetur adipisicing elit, sed do eiusmod tempor incididunt ut
					labore et dolore magna aliqua. Ut enim ad minim veniam, quis
					nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
					consequat.</div>
				<!--/tab-pane-->
				<div class="tab-pane" id="messages">

					<h2></h2>

					<hr>
					<form class="form" action="##" method="post" id="registrationForm">
						<div class="form-group">

							<div class="col-xs-6">
								<label for="first_name"><h4>First name</h4></label> <input
									type="text" class="form-control" name="first_name"
									id="first_name" placeholder="first name"
									title="enter your first name if any.">
							</div>
						</div>
						<div class="form-group">

							<div class="col-xs-6">
								<label for="last_name"><h4>Last name</h4></label> <input
									type="text" class="form-control" name="last_name"
									id="last_name" placeholder="last name"
									title="enter your last name if any.">
							</div>
						</div>

						<div class="form-group">

							<div class="col-xs-6">
								<label for="phone"><h4>Phone</h4></label> <input type="text"
									class="form-control" name="phone" id="phone"
									placeholder="enter phone"
									title="enter your phone number if any.">
							</div>
						</div>

						<div class="form-group">
							<div class="col-xs-6">
								<label for="mobile"><h4>Mobile</h4></label> <input type="text"
									class="form-control" name="mobile" id="mobile"
									placeholder="enter mobile number"
									title="enter your mobile number if any.">
							</div>
						</div>
						<div class="form-group">

							<div class="col-xs-6">
								<label for="email"><h4>Email</h4></label> <input type="email"
									class="form-control" name="email" id="email"
									placeholder="you@email.com" title="enter your email.">
							</div>
						</div>
						<div class="form-group">

							<div class="col-xs-6">
								<label for="email"><h4>Location</h4></label> <input type="email"
									class="form-control" id="location" placeholder="somewhere"
									title="enter a location">
							</div>
						</div>
						<div class="form-group">

							<div class="col-xs-6">
								<label for="password"><h4>Password</h4></label> <input
									type="password" class="form-control" name="password"
									id="password" placeholder="password"
									title="enter your password.">
							</div>
						</div>
						<div class="form-group">

							<div class="col-xs-6">
								<label for="password2"><h4>Verify</h4></label> <input
									type="password" class="form-control" name="password2"
									id="password2" placeholder="password2"
									title="enter your password2.">
							</div>
						</div>
						<div class="form-group">
							<div class="col-xs-12">
								<br>
								<button class="btn btn-lg btn-success" type="submit">
									<i class="glyphicon glyphicon-ok-sign"></i> Save
								</button>
								<button class="btn btn-lg" type="reset">
									<i class="glyphicon glyphicon-repeat"></i> Reset
								</button>
							</div>
						</div>
					</form>

				</div>
				<!--/tab-pane-->
				<div class="tab-pane" id="settings">


					<hr>
					<form class="form" action="##" method="post" id="registrationForm">
						<div class="form-group">

							<div class="col-xs-6">
								<label for="first_name"><h4>First name</h4></label> <input
									type="text" class="form-control" name="first_name"
									id="first_name" placeholder="first name"
									title="enter your first name if any.">
							</div>
						</div>
						<div class="form-group">

							<div class="col-xs-6">
								<label for="last_name"><h4>Last name</h4></label> <input
									type="text" class="form-control" name="last_name"
									id="last_name" placeholder="last name"
									title="enter your last name if any.">
							</div>
						</div>

						<div class="form-group">

							<div class="col-xs-6">
								<label for="phone"><h4>Phone</h4></label> <input type="text"
									class="form-control" name="phone" id="phone"
									placeholder="enter phone"
									title="enter your phone number if any.">
							</div>
						</div>

						<div class="form-group">
							<div class="col-xs-6">
								<label for="mobile"><h4>Mobile</h4></label> <input type="text"
									class="form-control" name="mobile" id="mobile"
									placeholder="enter mobile number"
									title="enter your mobile number if any.">
							</div>
						</div>
						<div class="form-group">

							<div class="col-xs-6">
								<label for="email"><h4>Email</h4></label> <input type="email"
									class="form-control" name="email" id="email"
									placeholder="you@email.com" title="enter your email.">
							</div>
						</div>
						<div class="form-group">

							<div class="col-xs-6">
								<label for="email"><h4>Location</h4></label> <input type="email"
									class="form-control" id="location" placeholder="somewhere"
									title="enter a location">
							</div>
						</div>
						<div class="form-group">

							<div class="col-xs-6">
								<label for="password"><h4>Password</h4></label> <input
									type="password" class="form-control" name="password"
									id="password" placeholder="password"
									title="enter your password.">
							</div>
						</div>
						<div class="form-group">

							<div class="col-xs-6">
								<label for="password2"><h4>Verify</h4></label> <input
									type="password" class="form-control" name="password2"
									id="password2" placeholder="password2"
									title="enter your password2.">
							</div>
						</div>
						<div class="form-group">
							<div class="col-xs-12">
								<br>
								<button class="btn btn-lg btn-success pull-right" type="submit">
									<i class="glyphicon glyphicon-ok-sign"></i> Save
								</button>
								<!--<button class="btn btn-lg" type="reset"><i class="glyphicon glyphicon-repeat"></i> Reset</button>-->
							</div>
						</div>
					</form>
				</div>

			</div>
			<!--/tab-pane-->
		</div>
		<!--/tab-content-->

	</div>
	<!--/col-9-->
</div>
<!--/row-->

<!-- footer -->
<jsp:include page="common/footer.jsp" />
<!-- end footer -->