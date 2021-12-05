<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

<link rel="stylesheet" href="${server}/data-table/datatables.css">
<style>

.pagination {
	display: inline-block;
}

.pagination a {
	color: black;
	float: left;
	padding: 8px 16px;
	text-decoration: none;
}

.pagination a.active {
	background-color: #4CAF50;
	color: white;
	border-radius: 5px;
}

.pagination a:hover:not (.active ) {
	background-color: #ddd;
	border-radius: 5px;
}

#orderTableUser {
	font-family: arial, sans-serif;
	border-collapse: collapse;
	width: 100%;
}

#orderTableUser td, th {
	border: 1px solid #dddddd;
	text-align: left;
	padding: 8px;
}

#orderTableUser tr:nth-child(even) {
	background-color: #dddddd;
}
</style>
<hr>
<div class="container bootstrap snippet">
	<div class="row">
		<div class="col-sm-10">
			<h1>Your profile</h1>
		</div>

	</div>
	<form class="form" id="updateForm" enctype="multipart/form-data">
		<div class="row">
			<div class="col-sm-3">
				<!--left col-->


				<div class="text-center">
					<c:if test="${user.avatar == null}">
						<img src="http://ssl.gstatic.com/accounts/ui/avatar_2x.png"
							width="170px" height="70px"
							class="avatar img-circle img-thumbnail" alt="avatar">
					</c:if>
					<c:if test="${user.avatar != null}">
						<img src="${uploadsDir}/${user.avatar}" width="170px"
							height="70px" name="files"
							class="avatar img-circle img-thumbnail" alt="avatar">
					</c:if>

					<h6>Upload a different photo...</h6>
					<input type="file" name="files"
						class="text-center center-block file-upload">
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
						<input type="hidden" id="userid" name="id" value="${user.id}" />
						<input type="hidden" id="username" value="${user.username}" />
						<div class="form-group">

							<div class="col-xs-6">
								<label for="name"><h4>Full name</h4></label> <input type="text"
									value="${user.name}" class="form-control" name="name" id="name"
									placeholder="name" title="enter your first name if any.">
							</div>
						</div>

						<div class="form-group">

							<div class="col-xs-6">
								<label for="phone"><h4>Phone</h4></label> <input type="text"
									value="${user.phone}" class="form-control" name="phone"
									id="phone" placeholder="enter phone"
									title="enter your phone number if any.">
							</div>
						</div>

						<div class="form-group">

							<div class="col-xs-6">
								<label for="email"><h4>Email</h4></label> <input type="email"
									value="${user.email}" class="form-control" name="email"
									id="email" placeholder="you@email.com"
									title="enter your email.">
							</div>
						</div>
						<div class="form-group">

							<div class="col-xs-6">
								<label for="address"><h4>Address</h4></label> <input type="text"
									value="${user.address}" class="form-control" id="location"
									name="address" placeholder="somewhere" title="enter a location">
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
								<button class="btn btn-lg btn-success" type="submit"
									onclick="updateUser(event)">
									<i class="glyphicon glyphicon-ok-sign"></i> Save
								</button>
								<button class="btn btn-lg" type="reset">
									<i class="glyphicon glyphicon-repeat"></i> Reset
								</button>
							</div>
						</div>


						<hr>
					</div>
					<br> <br>
					<table id="orderTableUser">
						<thead>
							<tr>
								<th>CODE NAME</th>
								<th>Created Date</th>
								<th>Customer name</th>
								<th>Status</th>
								<th></th>
							</tr>
						</thead>
						<tbody id="dataOrderTableUser">

						</tbody>
						
					</table>
					<div class="pagination" id="pageOrder">
						 	
					</div>
				</div>
				<!--/tab-pane-->
			</div>
			<!--/tab-content-->

		</div>
		<!--/col-9-->
	</form>
</div>
<!--/row-->


<!-- footer -->
<jsp:include page="common/footer.jsp" />
<!-- end footer -->