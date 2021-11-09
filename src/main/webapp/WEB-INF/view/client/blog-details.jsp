<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- header -->
<jsp:include page="/WEB-INF/view/client/common/header.jsp"></jsp:include>
<!-- end header -->

<!-- container -->

<section class="blog-details-hero set-bg"
	data-setbg="img/blog/details/details-hero.jpg">
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<div class="blog__details__hero__text">
					<h2>${blog.thumbnail}</h2>
					<ul>
						<li>By Michael Scofield</li>
						<li>January 14, 2019</li>
						<li>8 Comments</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</section>
<!-- Blog Details Hero End -->

<!-- Blog Details Section Begin -->
<section class="blog-details spad">
	<div class="container">
		<div class="row">
			<div class="col-lg-4 col-md-5 order-md-1 order-2">
				<div class="blog__sidebar">
					<div class="blog__sidebar__search">
						<form action="#">
							<input type="text" placeholder="Search...">
							<button type="submit">
								<span class="icon_search"></span>
							</button>
						</form>
					</div>
					<div class="blog__sidebar__item">
						<h4>Categories</h4>
						<ul>
							<li><a href="#">All</a></li>
							<li><a href="#">Beauty (20)</a></li>
							<li><a href="#">Food (5)</a></li>
							<li><a href="#">Life Style (9)</a></li>
							<li><a href="#">Travel (10)</a></li>
						</ul>
					</div>
					<div class="blog__sidebar__item">
						<h4>Recent News</h4>
						<div class="blog__sidebar__recent">
							<a href="#" class="blog__sidebar__recent__item">
								<div class="blog__sidebar__recent__item__pic">
									<img src="img/blog/sidebar/sr-1.jpg" alt="">
								</div>
								<div class="blog__sidebar__recent__item__text">
									<h6>
										09 Kinds Of Vegetables<br /> Protect The Liver
									</h6>
									<span>MAR 05, 2019</span>
								</div>
							</a> <a href="#" class="blog__sidebar__recent__item">
								<div class="blog__sidebar__recent__item__pic">
									<img src="img/blog/sidebar/sr-2.jpg" alt="">
								</div>
								<div class="blog__sidebar__recent__item__text">
									<h6>
										Tips You To Balance<br /> Nutrition Meal Day
									</h6>
									<span>MAR 05, 2019</span>
								</div>
							</a> <a href="#" class="blog__sidebar__recent__item">
								<div class="blog__sidebar__recent__item__pic">
									<img src="img/blog/sidebar/sr-3.jpg" alt="">
								</div>
								<div class="blog__sidebar__recent__item__text">
									<h6>
										4 Principles Help You Lose <br />Weight With Vegetables
									</h6>
									<span>MAR 05, 2019</span>
								</div>
							</a>
						</div>
					</div>
					<div class="blog__sidebar__item">
						<h4>Search By</h4>
						<div class="blog__sidebar__item__tags">
							<a href="#">Apple</a> <a href="#">Beauty</a> <a href="#">Vegetables</a>
							<a href="#">Fruit</a> <a href="#">Healthy Food</a> <a href="#">Lifestyle</a>
						</div>
					</div>
				</div>
			</div>
			<div class="col-lg-8 col-md-7 order-md-1 order-1">
				<div class="blog__details__text">${blog.details}</div>
			</div>
		</div>
	</div>
</section>
<!-- Blog Details Section End -->

<!-- Related Blog Section Begin -->
<section class="related-blog spad">
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<div class="section-title related-blog-title">
					<h2>Post You May Like</h2>
				</div>
			</div>
		</div>
		<div class="row">
			<c:forEach var="blog" items="${blogs}">
				<div class="col-lg-4 col-md-4 col-sm-6">
					<div class="blog__item">
						<div class="blog__item__pic">
							<img src="${uploadsDir}/${blog.avatar}" alt="">
						</div>

						<div class="blog__item__text">
							<ul>
								<li class="blogFormatDate" data-value="${blog.createdDate}">${blog.createdDate}</li>
							</ul>
							<h5>
								<a href="#">${blog.thumbnail}</a>
							</h5>
							<p>${blog.shortDescription}</p>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
</section>

<!-- end container -->

<!-- footer -->
<jsp:include page="/WEB-INF/view/client/common/footer.jsp"></jsp:include>
<!-- end footer -->