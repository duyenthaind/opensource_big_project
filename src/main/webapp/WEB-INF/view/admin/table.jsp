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
				<div class="col-md-12">
					<!-- DATA TABLE -->
					<h3 class="title-5 m-b-35">Category data</h3>
					<div class="table-data__tool">
						<div class="table-data__tool-left">
							<div class="rs-select2--light rs-select2--md">
								<select class="js-select2" name="property">
									<option selected="selected">All Properties</option>
									<option value="">Option 1</option>
									<option value="">Option 2</option>
								</select>
								<div class="dropDownSelect2"></div>
							</div>
							<div class="rs-select2--light rs-select2--sm">
								<select class="js-select2" name="time">
									<option selected="selected">Today</option>
									<option value="">3 Days</option>
									<option value="">1 Week</option>
								</select>
								<div class="dropDownSelect2"></div>
							</div>
							<button class="au-btn-filter">
								<i class="zmdi zmdi-filter-list"></i>filters
							</button>
						</div>
						<div class="table-data__tool-right">
							<button class="au-btn au-btn-icon au-btn--green au-btn--small"
								type="button" data-toggle="modal" data-target="#newCate">
								<i class="zmdi zmdi-plus"></i>add item
							</button>

						</div>
					</div>
					<div class="table-responsive table-responsive-data2">

						<table class="table table-data2">
							<thead>
								<tr>
									<th>name</th>
									<th>created date</th>
									<th>updated date</th>
									<th>created by</th>
									<th></th>
								</tr>
							</thead>
							<tbody class="resultCategories">



							</tbody>
						</table>

					</div>
					<ul id="paginationCate" class="pagination">

					</ul>
					<!-- END DATA TABLE -->
				</div>
			</div>
			<br> <br>
			<div class="row">
				<div class="col-md-12">
					<!-- DATA TABLE -->
					<h3 class="title-5 m-b-35">Product data</h3>
					<div class="table-data__tool">
						<div class="table-data__tool-left">
							<div class="rs-select2--light rs-select2--md">
								<select class="js-select2" name="property">
									<option selected="selected">All Properties</option>
									<option value="">Option 1</option>
									<option value="">Option 2</option>
								</select>
								<div class="dropDownSelect2"></div>
							</div>
							<div class="rs-select2--light rs-select2--sm">
								<select class="js-select2" name="time">
									<option selected="selected">Today</option>
									<option value="">3 Days</option>
									<option value="">1 Week</option>
								</select>
								<div class="dropDownSelect2"></div>
							</div>
							<button class="au-btn-filter">
								<i class="zmdi zmdi-filter-list"></i>filters
							</button>
						</div>
						<div class="table-data__tool-right">
							<button class="au-btn au-btn-icon au-btn--green au-btn--small"
								type="button" data-toggle="modal" data-target="#newProduct"
								onclick="emptyGallery()">
								<i class="zmdi zmdi-plus"></i>add item
							</button>

						</div>
					</div>
					<div class="table-responsive table-responsive-data2">
						<table class="table table-data2">
							<thead>
								<tr>
									<th>name</th>
									<th>created date</th>
									<th>updated date</th>
									<th>price</th>
									<th>price sale</th>
									<th></th>
								</tr>
							</thead>
							<tbody id="resultProducts">
							</tbody>

						</table>
						<ul id="paginationProd" class="pagination">

						</ul>
					</div>
					<!-- END DATA TABLE -->
				</div>
			</div>
			<div class="row m-t-30">
				<div class="col-md-12">
					<!-- DATA TABLE-->
					<div class="table-responsive m-b-40">
						
					</div>
					<!-- END DATA TABLE-->
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