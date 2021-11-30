<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
					<h2>Checkout</h2>
					<div class="breadcrumb__option">
						<a href="./index.html">Home</a> <span>Checkout</span>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
<!-- Breadcrumb Section End -->

<!-- Checkout Section Begin -->
<section class="checkout spad">
	<div class="container">

		<div class="checkout__form">
			<h4>Billing Details</h4>
			<form action="#">
				<div class="row">
					<div class="col-lg-8 col-md-6">
						<div class="row">
							<div class="col-lg-8">
								<div class="checkout__input">
									<p>
										Full Name<span>*</span>
									</p>
									<input type="text">
								</div>
							</div>
						</div>
						<div class="checkout__input">
							<p>
								Address<span>*</span>
							</p>
							<input type="text" placeholder="Street Address"
								class="checkout__input__add"> 
						</div>
						
						<div class="row">
							<div class="col-lg-6">
								<div class="checkout__input">
									<p>
										Phone<span>*</span>
									</p>
									<input type="text">
								</div>
							</div>
							<div class="col-lg-6">
								<div class="checkout__input">
									<p>
										Email<span>*</span>
									</p>
									<input type="text">
								</div>
							</div>
						</div>
						<div class="checkout__input">
							<p>
								Order notes<span>*</span>
							</p>
							<input type="text"
								placeholder="Notes about your order, e.g. special notes for delivery.">
						</div>
						<div class="shoping__continue">
							<div class="shoping__discount">
								<h5>Discount Codes</h5>

								<input type="text" placeholder="Enter your coupon code">
								<button type="submit" class="site-btn">APPLY COUPON</button>

							</div>
						</div>
					</div>
					<div class="col-lg-4 col-md-6">

						<div class="checkout__order">
							<h4>Your Order</h4>
							<div class="checkout__order__products">
								Products <span>Total</span>
							</div>
							<ul>
								<li>Vegetable’s Package <span>$75.99</span></li>
								<li>Fresh Vegetable <span>$151.99</span></li>
								<li>Organic Bananas <span>$53.99</span></li>
							</ul>
							<div class="checkout__order__total">
								Total <span>$750.99</span>
							</div>	
							<div class="checkout__input__checkbox">
								<label for="payment"> Ship Cod <input
									type="checkbox" id="payment"> <span class="checkmark"></span>
								</label>
							</div>
							<div class="checkout__input__checkbox">
								<label for="paypal"> MOMO <input type="checkbox"
									id="paypal"> <span class="checkmark"></span>
								</label>
							</div>
							<button type="submit" class="site-btn">PLACE ORDER</button>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</section>

<!-- end container -->

<!-- footer -->
<jsp:include page="/WEB-INF/view/client/common/footer.jsp"></jsp:include>
<!-- end footer -->