<%@page import="java.util.List"%>
<%@page import="com.group7.fruitswebsite.util.DateUtil"%>
<%@page import="com.group7.fruitswebsite.dto.DhBlogDto"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- header -->
<jsp:include page="common/header.jsp" />
<%@ include file="../variable.jsp"%>
<!-- end header -->


<!-- container -->

<section class="categories">
	<div class="container">
		<div class="row">
			<div class="categories__slider owl-carousel">
				<c:forEach var="category" items="${categories}">
					<c:if test="${category.status = true}">
						<div class="col-lg-3">
							<div class="categories__item set-bg"
								data-setbg="${uploadsDir}/${category.avatar}">
								<h5>
									<a href="${server}/shop-grid?categoryId=${category.id}">${category.name}</a>
								</h5>
							</div>
						</div>
					</c:if>
				</c:forEach>
			</div>
		</div>
	</div>
</section>
<!-- Categories Section End -->

<!-- Featured Section Begin -->
<section class="featured spad">
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<div class="section-title">
					<h2>Featured Product</h2>
				</div>
				<div class="featured__controls">
					<ul>
						<li class="active" data-filter="*">All</li>
						<c:forEach var="category" items="${categories}">
							<li data-filter=".${category.name}">${category.name}</li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
		<div class="row featured__filter">
			<c:forEach var="product" items="${products}">
				<div
					class="col-lg-3 col-md-4 col-sm-6 mix ${product.category.name} fresh-meat">
					<div class="featured__item">
						<div class="featured__item__pic set-bg"
							data-setbg="${uploadsDir}/${product.productImages[0]}">
							<ul class="featured__item__pic__hover">
								<li><a href="#"><i class="fa fa-heart"></i></a></li>

								<li><a onClick="addCart(${product.id},1)"><i
										class="fa fa-shopping-cart"></i></a></li>
							</ul>
						</div>
						<div class="featured__item__text">
							<h6>
								<a href="${server}/shop-details?productId=${product.id}">${product.name}</a>
							</h6>
							<h5>$ ${product.price}</h5>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
</section>
<!-- Featured Section End -->

<!-- Banner Begin -->
<div class="banner">
	<div class="container">
		<div class="row">
			<div class="col-lg-6 col-md-6 col-sm-6">
				<div class="banner__pic">
					<img src="img/banner/banner-1.jpg" alt="">
				</div>
			</div>
			<div class="col-lg-6 col-md-6 col-sm-6">
				<div class="banner__pic">
					<img src="img/banner/banner-2.jpg" alt="">
				</div>
			</div>
		</div>
	</div>
</div>
<!-- Banner End -->

<!-- Latest Product Section Begin -->
<section class="latest-product spad">
	<div class="container">
		<div class="row">
			<div class="col-lg-4 col-md-6">
				<div class="latest-product__text">
					<h4>Latest Products</h4>
					<div class="latest-product__slider owl-carousel">
						<div class="latest-prdouct__slider__item">
							<c:forEach var="top9Product" items="${top9Products}">
								<a href="${server}/shop-details?productId=${top9Product.id}" class="latest-product__item">
									<div class="latest-product__item__pic">
										<img src="${uploadsDir}/${top9Product.productImages[0]}"
											alt="">
									</div>
									<div class="latest-product__item__text">
										<h6>${top9Product.name}</h6>
										<span>$${top9Product.price}</span>
									</div>
								</a>
							</c:forEach>
						</div>
						<div class="latest-prdouct__slider__item">
							<c:forEach var="top9Product" items="${top9Products1}">
								<a href="${server}/shop-details?productId=${top9Product.id}" class="latest-product__item">
									<div class="latest-product__item__pic">
										<img src="${uploadsDir}/${top9Product.productImages[0]}"
											alt="">
									</div>
									<div class="latest-product__item__text">
										<h6>${top9Product.name}</h6>
										<span>$${top9Product.price}</span>
									</div>
								</a>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>
			<div class="col-lg-4 col-md-6">
				<div class="latest-product__text">
					<h4>Top Rated Products</h4>
					<div class="latest-product__slider owl-carousel">
						<div class="latest-prdouct__slider__item">
							<c:forEach var="top9Product" items="${top9Products1}">
								<a href="${server}/shop-details?productId=${top9Product.id}" class="latest-product__item">
									<div class="latest-product__item__pic">
										<img src="${uploadsDir}/${top9Product.productImages[0]}"
											alt="">
									</div>
									<div class="latest-product__item__text">
										<h6>${top9Product.name}</h6>
										<span>$${top9Product.price}</span>
									</div>
								</a>
							</c:forEach>
						</div>
						<div class="latest-prdouct__slider__item">
							<c:forEach var="top9Product" items="${top9Products2}">
								<a href="${server}/shop-details?productId=${top9Product.id}" class="latest-product__item">
									<div class="latest-product__item__pic">
										<img src="${uploadsDir}/${top9Product.productImages[0]}"
											alt="">
									</div>
									<div class="latest-product__item__text">
										<h6>${top9Product.name}</h6>
										<span>$${top9Product.price}</span>
									</div>
								</a>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>
			<div class="col-lg-4 col-md-6">
				<div class="latest-product__text">
					<h4>Review Products</h4>
					<div class="latest-product__slider owl-carousel">
						<div class="latest-prdouct__slider__item">
							<c:forEach var="top9Product" items="${top9Products2}">
								<a href="${server}/shop-details?productId=${top9Product.id}" class="latest-product__item">
									<div class="latest-product__item__pic">
										<img src="${uploadsDir}/${top9Product.productImages[0]}"
											alt="">
									</div>
									<div class="latest-product__item__text">
										<h6>${top9Product.name}</h6>
										<span>$${top9Product.price}</span>
									</div>
								</a>
							</c:forEach>
						</div>
						<div class="latest-prdouct__slider__item">
							<c:forEach var="top9Product" items="${top9Products}">
								<a href="${server}/shop-details?productId=${top9Product.id}" class="latest-product__item">
									<div class="latest-product__item__pic">
										<img src="${uploadsDir}/${top9Product.productImages[0]}"
											alt="">
									</div>
									<div class="latest-product__item__text">
										<h6>${top9Product.name}</h6>
										<span>$${top9Product.price}</span>
									</div>
								</a>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
<!-- Latest Product Section End -->

<!-- Blog Section Begin -->
<section class="from-blog spad">
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<div class="section-title from-blog__title">
					<h2>From The Blog</h2>
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
								<li class="blogFormatDate" data-value="${blog.date}">${blog.date}</li>
							</ul>
							<h5>
								<a href="${server}/blog-details?id=${blog.id}">${blog.thumbnail}</a>
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
<jsp:include page="common/footer.jsp" />
<!-- end footer -->