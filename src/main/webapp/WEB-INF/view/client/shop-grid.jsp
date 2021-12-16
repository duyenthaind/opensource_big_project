<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- header -->
<jsp:include page="common/header.jsp" />
<%@ include file="../variable.jsp"%>
<!-- end header -->

<!-- container -->

<section class="breadcrumb-section set-bg"
	data-setbg="img/breadcrumb.jpg">
	<div class="container">
		<div class="row">
			<div class="col-lg-12 text-center">
				<div class="breadcrumb__text">
					<h2>Organi Shop</h2>
					<div class="breadcrumb__option">
						<a href="/index">Home</a> <span>Shop</span>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
<!-- Breadcrumb Section End -->

<!-- Product Section Begin -->
<section class="product spad">
	<div class="container">
		<div class="row">
			<div class="col-lg-3 col-md-5">
				<div class="sidebar">
					<div class="sidebar__item">
						<h4>Categories</h4>
						<ul>
							<c:forEach var="category" items="${categories}">
								<c:if test="${category.status = true}">
									<li><a
										href="${server}/shop-grid?categoryId=${category.id}">${category.name}</a>
									</li>
								</c:if>
							</c:forEach>
						</ul>
					</div>
					<div class="sidebar__item">
						<h4>Price</h4>
						<div class="price-range-wrap">
							<div
								class="price-range ui-slider ui-corner-all ui-slider-horizontal ui-widget ui-widget-content"
								data-min="10" data-max="540">
								<div class="ui-slider-range ui-corner-all ui-widget-header"></div>
								<span tabindex="0"
									class="ui-slider-handle ui-corner-all ui-state-default"></span>
								<span tabindex="0"
									class="ui-slider-handle ui-corner-all ui-state-default"></span>
							</div>
							<div class="range-slider">
								<div class="price-input">
									<input type="text" id="minamount"> <input type="text"
										id="maxamount">
								</div>
							</div>
						</div>
					</div>
					<div class="sidebar__item">
						<div class="latest-product__text">
							<h4>Latest Products</h4>
							<div class="latest-product__slider owl-carousel">
								<div class="latest-prdouct__slider__item">
									<c:forEach var="top9Product" items="${top9Products}">
										<a href="${server}/shop-details?productId=${top9Product.id}"
											class="latest-product__item">
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
										<a href="${server}/shop-details?productId=${top9Product.id}"
											class="latest-product__item">
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
			<div class="col-lg-9 col-md-7">
				<div class="product__discount">
					<div class="section-title product__discount__title">
						<h2>Sale Off</h2>
					</div>
					<div class="row">
						<div class="product__discount__slider owl-carousel">
							<c:forEach var="prodOrBSale" items="${prodOrBPriceSale}">
								<div class="col-lg-4">
									<div class="product__discount__item">
										<div class="product__discount__item__pic set-bg"
											data-setbg="${uploadsDir}/${prodOrBSale.productImages[0]}">
											<div class="product__discount__percent">-${prodOrBSale.salePercent}%</div>
											<ul class="product__item__pic__hover">
												<li><a onclick="likeCart(${product.id})"><i class="fa fa-heart"></i></a></li>
												<li style="cursor: pointer;"><a onClick="addCart(${prodOrBSale.id},1)"><i
														class="fa fa-shopping-cart"></i></a></li>
											</ul>
										</div>
										<div class="product__discount__item__text">
											<span>${prodOrBSale.name}</span>
											<h5>
												<a href="${server}/shop-details?productId=${prodOrBSale.id}">${prodOrBSale.category.name}</a>
											</h5>
											<div class="product__item__price">
												$${prodOrBSale.priceSale} <span>$${prodOrBSale.price}</span>
											</div>
										</div>
									</div>
								</div>
							</c:forEach>
						</div>
					</div>
				</div>
				<div class="filter__item">
					<div class="row">
						<div class="col-lg-4 col-md-5">
							<div class="filter__sort">
								<span>Sort By</span> <select>
									<option value="0">Name</option>
									<option value="0">Price</option>
								</select>
							</div>
						</div>
						<div class="col-lg-4 col-md-4">
							<div class="filter__found">
								<h6>
									<span> .. </span> Products found
								</h6>
							</div>
						</div>
						<div class="col-lg-4 col-md-3">
							<div class="filter__option">
								<span class="icon_grid-2x2"></span> 
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<c:if test="${productByCategoryWithPage != null }">
						<c:forEach var="prodByCategory"
							items="${productByCategoryWithPage}">
							<div class="col-lg-4 col-md-6 col-sm-6">
								<div class="product__item">
									<div class="product__item__pic set-bg"
										data-setbg="${uploadsDir}/${prodByCategory.productImages[0]}">
										<ul class="product__item__pic__hover">
											<li><a onclick="likeCart(${product.id})"><i class="fa fa-heart"></i></a></li>
											<li style="cursor: pointer;"><a onClick="addCart(${prodByCategory.id},1)"><i
													class="fa fa-shopping-cart"></i></a></li>
										</ul>
									</div>
									<div class="product__item__text">
										<h6>
											<a
												href="${server}/shop-details?productId=${prodByCategory.id}">${prodByCategory.name}</a>
										</h6>
										<h5>$${prodByCategory.priceSale}</h5>
										<div class="product__item__price">
											<span style="color: #8F6A43"><del>$${prodByCategory.price}</del></span>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>
					</c:if>
					<c:if test="${productSearch != null }">
						<c:forEach var="productSearch" items="${productSearch}">
							<div class="col-lg-4 col-md-6 col-sm-6">
								<div class="product__item">
									<div class="product__item__pic set-bg"
										data-setbg="${uploadsDir}/${productSearch.productImages[0]}">
										<ul class="product__item__pic__hover">
											<li><a onclick="likeCart(${product.id})"><i class="fa fa-heart"></i></a></li>
											<li style="cursor: pointer;"><a onClick="addCart(${productSearch.id},1)"><i
													class="fa fa-shopping-cart"></i></a></li>
										</ul>
									</div>
									<div class="product__item__text">
										<h6>
											<a
												href="${server}/shop-details?productId=${productSearch.id}">${productSearch.name}</a>
										</h6>
										<h5>$${productSearch.priceSale}</h5>
										<div class="product__item__price">
											<span style="color: #8F6A43"><del>$${productSearch.price}</del></span>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>
					</c:if>
					<c:if test="${allProducts != null }">
						<c:forEach var="productSearch" items="${allProducts}">
							<div class="col-lg-4 col-md-6 col-sm-6">
								<div class="product__item">
									<div class="product__item__pic set-bg"
										data-setbg="${uploadsDir}/${productSearch.productImages[0]}">
										<ul class="product__item__pic__hover">
											<li><a onclick="likeCart(${product.id})"><i class="fa fa-heart"></i></a></li>
											<li style="cursor: pointer;"><a onClick="addCart(${productSearch.id},1)"><i
													class="fa fa-shopping-cart"></i></a></li>
										</ul>
									</div>
									<div class="product__item__text">
										<h6>
											<a
												href="${server}/shop-details?productId=${productSearch.id}">${productSearch.name}</a>
										</h6>
										<h5>$${productSearch.priceSale}</h5>
										<div class="product__item__price">
											<span style="color: #8F6A43"><del>$${productSearch.price}</del></span>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>
					</c:if>
				</div>
				<div class="product__pagination">
					<c:if test="${productByCategoryWithPage != null}">
						<c:forEach var="totalPage" items="${totalPages}">
							<a
								href="${server}/shop-grid?categoryId=${categoryId}&page=${totalPage}">${totalPage+1}</a>
						</c:forEach>
					</c:if>
					<c:if test="${allProducts != null}">
						<c:forEach var="totalPage" items="${totalPages}">
							<a href="${server}/shop-grid?&page=${totalPage}">${totalPage+1}</a>
						</c:forEach>
					</c:if>
					<c:if test="${conditions != null}">
						<c:forEach var="totalPage" items="${totalPages}">
							<a
								href="${server}/shop-grid?categoryId=${categoryId}&page=${totalPage}&searchText=${searchText}">${totalPage+1}</a>
						</c:forEach>
					</c:if>
				</div>
			</div>
		</div>
	</div>
</section>

<!-- end container -->

<!-- footer -->
<jsp:include page="common/footer.jsp" />
<!-- end footer -->