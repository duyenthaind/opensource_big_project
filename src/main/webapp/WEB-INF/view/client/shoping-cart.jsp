<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- header -->
<jsp:include page="/WEB-INF/view/client/common/header.jsp"></jsp:include>
<!-- end header -->

<!-- container -->
	
	 <section class="breadcrumb-section set-bg" data-setbg="img/breadcrumb.jpg">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <div class="breadcrumb__text">
                        <h2>Shopping Cart</h2>
                        <div class="breadcrumb__option">
                            <a href="/index">Home</a>
                            <span>Shopping Cart</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Breadcrumb Section End -->

    <!-- Shoping Cart Section Begin -->
    <section class="shoping-cart spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="shoping__cart__table">
                        <table>
                            <thead>
                                <tr>
                                	<th></th>
                                    <th class="shoping__product">Products</th>
                                    <th>Price ($)</th>
                                    <th>Quantity</th>
                                    <th>Total ($)</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody id="tableCart">
                                                       
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="row">
                
                <div class="col-lg-6">
                    <div class="shoping__checkout">
                        <h5>Cart Total</h5>
                        <ul>
                           
                            <li>Total <span id="totalCart">$454.98</span></li>
                        </ul>
                        <a href="/checkout" class="primary-btn">PROCEED TO CHECKOUT</a>
                    </div>
                </div>
            </div>
        </div>
    </section>

<!-- end container -->

<!-- footer -->
<jsp:include page="/WEB-INF/view/client/common/footer.jsp"></jsp:include>
<!-- end footer -->