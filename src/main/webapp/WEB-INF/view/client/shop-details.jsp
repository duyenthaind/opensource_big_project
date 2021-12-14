<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../variable.jsp"%>
<!-- header -->
<jsp:include page="/WEB-INF/view/client/common/header.jsp"></jsp:include>
<!-- end header -->

<!-- container -->

<section class="breadcrumb-section set-bg"
	data-setbg="img/breadcrumb.jpg">
	<div class="container">
		<div class="row">
			<div class="col-lg-12 text-center">
				<div class="breadcrumb__text">
					<h2>${product.category.name}'sPackage</h2>
					<div class="breadcrumb__option">
						<a href="/index">Home</a> <a href="/index">Vegetables</a> <span>${product.category.name}'™s
							Package</span>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
<!-- Breadcrumb Section End -->

<!-- Product Details Section Begin -->
<section class="product-details spad">
	<div class="container">
		<div class="row">
			<div class="col-lg-6 col-md-6">
				<div class="product__details__pic">
					<div class="product__details__pic__item">
						<img class="product__details__pic__item--large"
							src="${uploadsDir}/${product.productImages[0]}" alt="">
					</div>
					<div class="product__details__pic__slider owl-carousel">
						<c:forEach var="productImage" items="${productImages}">
							<img data-imgbigurl="${uploadsDir}/${productImage}"
								src="${uploadsDir}/${productImage}" alt="">
						</c:forEach>
					</div>
				</div>
			</div>
			<div class="col-lg-6 col-md-6">
				<div class="product__details__text">
					<h3>${product.name }</h3>
					<div class="product__details__price">$${product.price}</div>
					${product.shortDescription}
					<div class="product__details__quantity">
						<div class="quantity">
							<div class="pro-qty">
								<input type="text" id="customQuantity" value="1" onchange="changeQty()">
							</div>
						</div>
					</div>
					<a style="color: white; cursor: pointer;" class="primary-btn"
						onClick="addCustomCart(${product.id},1)">ADD TO CARD</a> <a href="#"
						class="heart-icon"><span class="icon_heart_alt"></span></a>
					<ul>
						<li><b>Available</b> <span>${product.available}</span></li>
						<li><b>Shipping</b> <span>01 day shipping. <samp>Free
									pickup today</samp></span></li>
						<li><b>Weight</b> <span>${product.weight} kg</span></li>
					</ul>
				</div>
			</div>
			<div class="col-lg-12">
				<div class="product__details__tab">
					<ul class="nav nav-tabs" role="tablist">
						<li class="nav-item"><a class="nav-link active"
							data-toggle="tab" href="#tabs-1" role="tab" aria-selected="true">Description</a>
						</li>
						<li class="nav-item"><a class="nav-link" data-toggle="tab"
							href="#tabs-3" role="tab" aria-selected="false">Reviews <span>(1)</span></a>
						</li>
					</ul>
					<div class="tab-content">
						<div class="tab-pane active" id="tabs-1" role="tabpanel">
							<div class="product__details__tab__desc">
								<h6>Products Infomation</h6>
								${product.detailDescription}
							</div>
						</div>
						<div class="tab-pane" id="tabs-3" role="tabpanel">
							<div class="product__details__tab__desc">
								<div class="container mt-5 mb-5">
									<div
										class="row height d-flex justify-content-center align-items-center">
										<div class="col-md-12">
											<div class="card">
												<div class="p-3">
													<h6>Comments</h6>
												</div>
												<div
													class="mt-3 d-flex flex-row align-items-center p-3 form-color">
													<img src="https://i.imgur.com/zQZSWrt.jpg" width="50"
														class="rounded-circle mr-2"> <input type="text"
														class="form-control" placeholder="Enter your comment...">
												</div>
												<div class="mt-2">
													<div class="d-flex flex-row p-3">
														<img src="https://i.imgur.com/zQZSWrt.jpg" width="40"
															height="40" class="rounded-circle mr-3">
														<div class="w-100">
															<div
																class="d-flex justify-content-between align-items-center">
																<div class="d-flex flex-row align-items-center">
																	<span class="mr-2">Brian selter</span> <small
																		class="c-badge">Top Comment</small>
																</div>
																<small>12h ago</small>
															</div>
															<p class="text-justify comment-text mb-0">Lorem ipsum
																dolor sit amet, consectetur adipiscing elit, sed do
																eiusmod tempor incididunt ut labore et dolore magna
																aliqua. Ut enim ad minim veniam</p>
															<div class="d-flex flex-row user-feed">
																<span class="wish"><i
																	class="fa fa-heartbeat mr-2"></i>24</span> <span class="ml-3"><i
																	class="fa fa-comments-o mr-2"></i>Reply</span>
															</div>


															<div
																class="mt-3 d-flex flex-row align-items-center p-3 form-color">
																<img src="https://i.imgur.com/zQZSWrt.jpg" width="50"
																	class="rounded-circle mr-2"> <input type="text"
																	class="form-control"
																	placeholder="Enter your comment...">
															</div>
															<div class="d-flex flex-row p-3">
																<img src="https://i.imgur.com/3J8lTLm.jpg" width="40"
																	height="40" class="rounded-circle mr-3">
																<div class="w-100">
																	<div
																		class="d-flex justify-content-between align-items-center">
																		<div class="d-flex flex-row align-items-center">
																			<span class="mr-2">Seltos Majito</span> <small
																				class="c-badge">Top Comment</small>
																		</div>
																		<small>2h ago</small>
																	</div>
																	<p class="text-justify comment-text mb-0">Tellus in
																		hac habitasse platea dictumst vestibulum. Lectus nulla
																		at volutpat diam ut venenatis tellus. Aliquam etiam
																		erat velit scelerisque in dictum non consectetur.
																		Sagittis nisl rhoncus mattis rhoncus urna neque
																		viverra justo nec. Tellus cras adipiscing enim eu
																		turpis egestas pretium aenean pharetra. Aliquam
																		faucibus purus in massa.</p>
																	<div class="d-flex flex-row user-feed">
																		<span class="wish"><i
																			class="fa fa-heartbeat mr-2"></i>14</span> <span
																			class="ml-3"><i class="fa fa-comments-o mr-2"></i>Reply</span>
																	</div>
																</div>
															</div>
														</div>
													</div>
													<div class="d-flex flex-row p-3">
														<img src="https://i.imgur.com/3J8lTLm.jpg" width="40"
															height="40" class="rounded-circle mr-3">
														<div class="w-100">
															<div
																class="d-flex justify-content-between align-items-center">
																<div class="d-flex flex-row align-items-center">
																	<span class="mr-2">Seltos Majito</span> <small
																		class="c-badge">Top Comment</small>
																</div>
																<small>2h ago</small>
															</div>
															<p class="text-justify comment-text mb-0">Tellus in
																hac habitasse platea dictumst vestibulum. Lectus nulla
																at volutpat diam ut venenatis tellus. Aliquam etiam erat
																velit scelerisque in dictum non consectetur. Sagittis
																nisl rhoncus mattis rhoncus urna neque viverra justo
																nec. Tellus cras adipiscing enim eu turpis egestas
																pretium aenean pharetra. Aliquam faucibus purus in
																massa.</p>
															<div class="d-flex flex-row user-feed">
																<span class="wish"><i
																	class="fa fa-heartbeat mr-2"></i>14</span> <span class="ml-3"><i
																	class="fa fa-comments-o mr-2"></i>Reply</span>
															</div>
														</div>
													</div>
													<div class="d-flex flex-row p-3">
														<img src="https://i.imgur.com/agRGhBc.jpg" width="40"
															height="40" class="rounded-circle mr-3">
														<div class="w-100">
															<div
																class="d-flex justify-content-between align-items-center">
																<div class="d-flex flex-row align-items-center">
																	<span class="mr-2">Maria Santola</span> <small
																		class="c-badge">Top Comment</small>
																</div>
																<small>12h ago</small>
															</div>
															<p class="text-justify comment-text mb-0">Id eu nisl
																nunc mi ipsum faucibus. Massa massa ultricies mi quis
																hendrerit dolor. Arcu bibendum at varius vel pharetra
																vel turpis nunc eget. Habitasse platea dictumst quisque
																sagittis purus sit amet volutpat. Urna condimentum
																mattis pellentesque id.Lorem ipsum dolor sit amet,
																consectetur adipiscing elit, sed do eiusmod tempor
																incididunt ut labore et dolore magna aliqua. Ut enim ad
																minim veniam</p>
															<div class="d-flex flex-row user-feed">
																<span class="wish"><i
																	class="fa fa-heartbeat mr-2"></i>54</span> <span class="ml-3"><i
																	class="fa fa-comments-o mr-2"></i>Reply</span>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
</section>
<!-- Product Details Section End -->

<!-- Related Product Section Begin -->
<section class="related-product">
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<div class="section-title related__product__title">
					<h2>Related Product</h2>
				</div>
			</div>
		</div>
		<div class="row">
			<c:forEach var="product" items="${top9Products1}">
				<div class="col-lg-3 col-md-4 col-sm-6">
					<div class="product__item">
						<div class="product__item__pic set-bg"
							data-setbg="${uploadsDir}/${product.productImages[0]}">
							<ul class="product__item__pic__hover">
								<li><a href="#"><i class="fa fa-heart"></i></a></li>
								<li><a onClick="addCart(${product.id},1)"><i
										class="fa fa-shopping-cart"></i></a></li>
							</ul>
						</div>
						<div class="product__item__text">
							<h6>
								<a href="#">${product.name}</a>
							</h6>
							<h5>$${product.price}</h5>
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