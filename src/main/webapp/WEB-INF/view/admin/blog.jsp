<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!-- sidebar -->

<jsp:include page="/WEB-INF/view/admin/common/sidebar.jsp"></jsp:include>

<!-- end sidebar -->

<!-- narbar -->

<jsp:include page="/WEB-INF/view/admin/common/navbar.jsp"></jsp:include>

<!-- end narbar -->



<div class="main-content">
	<div class="section__content section__content--p30">

		<div class="container-fluid">

			<div class="container">
				<div class="table-data__tool-right">
					<button class="au-btn au-btn-icon au-btn--green au-btn--small"
						type="button" data-toggle="modal" data-target="#newBlog">
						<i class="zmdi zmdi-plus"></i>add item
					</button>

				</div>
				<br /> <br /> <br />
				<div class="data_table_blog">

					<div class="row">
						<div class="col-lg-4">
							<div class="hovereffect">
								<img class="img-responsive" src="" alt="" />
								<div class="overlay">
									<h2>Name blog</h2>
									<a class="info" href="#">detail</a>
								</div>
							</div>
						</div>

						<div class="col-lg-7">
							<h4>Name</h4>
							<br /> <i class="fa fa-calendar-o"> May 4,2019</i>
							<p>Một trang blog có thể chứa các siêu liên kết, hình ảnh và
								liên kết (tới các trang chứa phim và âm nhạc). Văn bản blog dùng
								phong cách thảo luận. Một blog thường chỉ liên quan đến một chủ
								đề yêu thích.</p>
						</div>
						<div class="col-lg-1">
							<button class="btn btn-danger">Delete</button>
						</div>
					</div>
					<br />

				</div>



				<br /> <br /> <br />
				<ul id="paginationBlog" class="pagination">

				</ul>
			</div>

		</div>

	</div>

</div>




<!-- narbar -->

<jsp:include page="/WEB-INF/view/admin/common/footer.jsp"></jsp:include>

<!-- end narbar -->