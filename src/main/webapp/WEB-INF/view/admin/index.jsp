<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- sidebar -->

<jsp:include page="/WEB-INF/view/admin/common/sidebar.jsp"></jsp:include>

<!-- end sidebar -->

<!-- narbar -->

<jsp:include page="/WEB-INF/view/admin/common/navbar.jsp"></jsp:include>

<!-- end narbar -->

<!-- main containter -->

<!-- MAIN CONTENT-->
<div class="main-content">
	<div class="section__content section__content--p30">
		<div class="container-fluid">
			<div class="row m-t-25">
				<div class="col-sm-6 col-lg-3">
					<div class="overview-item overview-item--c1">
						<div class="overview__inner">
							<div class="overview-box clearfix">
								<div class="icon">
									<i class="zmdi zmdi-account-o"></i>
								</div>
								<div class="text">
									<h2>${members}</h2>
									<span>members</span>
								</div>
							</div>
							<div class="overview-chart">
								<canvas id="widgetChart1"></canvas>
							</div>
						</div>
					</div>
				</div>
				<div class="col-sm-6 col-lg-3">
					<div class="overview-item overview-item--c2">
						<div class="overview__inner">
							<div class="overview-box clearfix">
								<div class="icon">
									<i class="zmdi zmdi-shopping-cart"></i>
								</div>
								<div class="text">
									<h2>${itemsSolid}</h2>
									<span>items solid</span>
								</div>
							</div>
							<div class="overview-chart">
								<canvas id="widgetChart2"></canvas>
							</div>
						</div>
					</div>
				</div>
				<div class="col-sm-6 col-lg-3">
					<div class="overview-item overview-item--c4">
						<div class="overview__inner">
							<div class="overview-box clearfix">
								<div class="icon">
									<i class="zmdi zmdi-money"></i>
								</div>
								<div class="text">
									<h2>$${totalEarn}</h2>
									<span>total earnings</span>
								</div>
							</div>
							<div class="overview-chart">
								<canvas id="widgetChart4"></canvas>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-12">
					<h2 class="title-1 m-b-25">Earnings By Items</h2>
					<div class="table-responsive table--no-card m-b-40">
						<input type="hidden" id="currentPageDoneOrder" value="0" />
						<table class="table table-borderless table-striped table-earning">
							<thead>
								<tr>
									<th>date</th>
									<th>code</th>
									<th>total</th>
									<th></th>
								</tr>
							</thead>
							<tbody id="doneOrder">

							</tbody>
						</table>
						<ul id="paginationDoneOrder" class="pagination">

						</ul>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-12">
					<div class="au-card au-card--no-shadow au-card--no-pad m-b-40">
						<div class="au-card-title"
							style="background-image: url('images/bg-title-02.jpg');">
							<div class="bg-overlay bg-overlay--blue"></div>
							<h3>
								<i class="zmdi zmdi-comment-text"></i>New Messages
							</h3>
							<button class="au-btn-plus">
								<i class="zmdi zmdi-plus"></i>
							</button>
						</div>
						<div class="au-inbox-wrap js-inbox-wrap">
							<div class="au-message js-list-load">
								<div class="au-message__noti">
								</div>
								<div class="au-message-list">
									<c:forEach var="contact" items="${contacts}">
										<div class="au-message__item unread">
											<div class="au-message__item-inner">
												<div class="au-message__item-text">
													<div class="avatar-wrap">
														<div class="avatar">
															<img src="images/icon/avatar-01.jpg" alt="John Smith">
														</div>
													</div>
													<div class="text">
														<h5 class="name">${contact.name }</h5>
														<p>${contact.message }</p>
													</div>
												</div>
											</div>
										</div>
									</c:forEach>

								</div>
							</div>
						</div>
					</div>
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
<!-- END MAIN CONTENT-->

<!-- end main containter -->


<!-- narbar -->

<jsp:include page="/WEB-INF/view/admin/common/footer.jsp"></jsp:include>

<!-- end narbar -->
