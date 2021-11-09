<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- header -->
<jsp:include page="common/header.jsp"/>
<%@include file="../variable.jsp"%>
<!-- end header -->

<!-- container -->

<section class="blog-details-hero set-bg"
	data-setbg="${uploadsDir}/${blog.avatar}">
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<div class="blog__details__hero__text">
					<h2>${blog.thumbnail}</h2>
					<ul>
						<li>By Michael Scofield</li>
						<li>${blog.date}</li>
						<li>0 Comments</li>
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
			<jsp:include page="blog-sidebar.jsp"/>
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
								<li class="blogFormatDate" data-value="${blog.date}">${blog.date}</li>
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
<jsp:include page="common/footer.jsp"/>
<!-- end footer -->