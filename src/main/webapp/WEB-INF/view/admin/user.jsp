<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- sidebar -->

<jsp:include page="/WEB-INF/view/admin/common/sidebar.jsp"></jsp:include>

<!-- end sidebar -->

<!-- narbar -->

<jsp:include page="/WEB-INF/view/admin/common/navbar.jsp"></jsp:include>

<!-- end narbar -->

<!-- MAIN CONTENT-->
<div class="main-content">
	<div class="section__content section__content--p30">
		<div class="container-fluid">
			<div class="row">
				<div class="col-lg-12">
					<div class="table-responsive table--no-card m-b-30">
						
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-12">
					<!-- USER DATA-->
					<div class="user-data m-b-30">
						<h3 class="title-3 m-b-30">
							<i class="zmdi zmdi-account-calendar"></i>user data
						</h3>
						<div class="filters m-b-45">
							<div
								class="rs-select2--dark rs-select2--md m-r-10 rs-select2--border">
								<select class="js-select2" name="property">
									<option selected="selected">All Properties</option>
									<option value="">Products</option>
									<option value="">Services</option>
								</select>
								<div class="dropDownSelect2"></div>
							</div>
							<div class="rs-select2--dark rs-select2--sm rs-select2--border">
								<select class="js-select2 au-select-dark" name="time">
									<option selected="selected">All Time</option>
									<option value="">By Month</option>
									<option value="">By Day</option>
								</select>
								<div class="dropDownSelect2"></div>
							</div>
						</div>
						<div class="table-responsive table-data">
							<table class="table">
								<thead>
									<tr>

										<td>name</td>
										<td>role</td>
										<td>type</td>
										<td></td>
									</tr>
								</thead>
								<tbody class="userTable">
									<tr>
										<td>
											<div class="table-data__info">
												<h6>lori lynch</h6>
												<span> <a href="#">johndoe@gmail.com</a>
												</span>
											</div>
										</td>
										<td><span class="role admin">admin</span></td>
										<td>
											<div class="rs-select2--trans rs-select2--sm">
												<select class="js-select2" name="property">
													<option selected="selected">Full Control</option>
													<option value="">Post</option>
													<option value="">Watch</option>
												</select>
												<div class="dropDownSelect2"></div>
											</div>
										</td>
										<td><span class="more"> <i class="zmdi zmdi-more"></i>
										</span></td>
									</tr>
								</tbody>
							</table>
						</div>
						<input id="currentPageUser" type="hidden" value="0" />
						<div class="user-data__footer">
							<ul id="paginationUser" class="pagination userpage">

							</ul>
						</div>
					</div>
					<!-- END USER DATA-->
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<div class="copyright">
						<p>
							Copyright © 2018 Colorlib. All rights reserved. Template by <a
								href="https://colorlib.com">Colorlib</a>.
						</p>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</div>

<!-- narbar -->

<jsp:include page="/WEB-INF/view/admin/common/footer.jsp"></jsp:include>

<!-- end narbar -->