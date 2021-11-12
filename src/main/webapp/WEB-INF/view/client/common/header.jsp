
<%@page import="org.springframework.security.core.Authentication"%>
<%@page import="com.group7.fruitswebsite.entity.DhUser"%>
<%@page
	import="org.springframework.security.core.userdetails.UserDetails"%>
<%@page
	import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../../variable.jsp"%>


<c:set var="menu" value="${menu}" />
<c:if test="${menu == 'menu'}">
	<c:set var="display" value="" />
</c:if>
<c:if test="${menu != 'menu'}">
	<c:set var="display" value="display:none" />
</c:if>

<%
	String email = "Account";
String username = "";
boolean checkRole = false;
Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
if (principal instanceof UserDetails) {
	email = ((DhUser) principal).getEmail();
	username = ((DhUser) principal).getUsername();
}
Authentication auth = SecurityContextHolder.getContext().getAuthentication();
if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
	checkRole = true;
}
%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="description" content="Ogani Template">
<meta name="keywords" content="Ogani, unica, creative, html">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Ogani | Template</title>
<style>
body {
	background-color: #eee
}

.card {
	background-color: #fff;
	border: none
}

.form-color {
	background-color: #fafafa
}

.form-control {
	height: 48px;
	border-radius: 25px
}

.form-control:focus {
	color: #495057;
	background-color: #fff;
	border-color: #35b69f;
	outline: 0;
	box-shadow: none;
	text-indent: 10px
}

.c-badge {
	background-color: #35b69f;
	color: white;
	height: 20px;
	font-size: 11px;
	width: 92px;
	border-radius: 5px;
	display: flex;
	justify-content: center;
	align-items: center;
	margin-top: 2px
}

.comment-text {
	font-size: 13px
}

.wish {
	color: #35b69f
}

.user-feed {
	font-size: 14px;
	margin-top: 12px
}
</style>
<!-- Google Font -->
<link
	href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;900&display=swap"
	rel="stylesheet">

<!-- Css Styles -->
<link rel="stylesheet" href="${server}/css/bootstrap.min.css"
	type="text/css">
<link rel="stylesheet" href="${server}/css/font-awesome.min.css"
	type="text/css">
<link rel="stylesheet" href="${server}/css/elegant-icons.css"
	type="text/css">
<link rel="stylesheet" href="${server}/css/nice-select.css"
	type="text/css">
<link rel="stylesheet" href="${server}/css/jquery-ui.min.css"
	type="text/css">
<link rel="stylesheet" href="${server}/css/owl.carousel.min.css"
	type="text/css">
<link rel="stylesheet" href="${server}/css/slicknav.min.css"
	type="text/css">
<link rel="stylesheet" href="${server}/css/style.css" type="text/css">
<link rel="stylesheet" type="text/css"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/js/bootstrap.bundle.min.js">
</head>

<body>
	<!-- Page Preloder -->
	<div id="preloder">
		<div class="loader"></div>
	</div>

	<!-- Humberger Begin -->
	<div class="humberger__menu__overlay"></div>
	<div class="humberger__menu__wrapper">
		<div class="humberger__menu__logo">
			<a href="#"><img src="img/logo.png" alt=""></a>
		</div>
		<div class="humberger__menu__cart">
			<ul>
				<li><a href="#"><i class="fa fa-heart"></i> <span>1</span></a></li>
				<li><a href="#"><i class="fa fa-shopping-bag"></i> <span>3</span></a></li>
			</ul>
			<div class="header__cart__price">
				item: <span>$150.00</span>
			</div>
		</div>
		<div class="humberger__menu__widget">
			<div class="header__top__right__language">
				<img src="img/language.png" alt="">
				<div>English</div>
				<span class="arrow_carrot-down"></span>
				<ul>
					<li><a href="#">English</a></li>
				</ul>
			</div>
			<div class="header__top__right__auth">
				<a href="#"><i class="fa fa-user"></i> Login</a>
			</div>
		</div>
		<nav class="humberger__menu__nav mobile-menu">
			<ul>
				<li class="active"><a href="/index">Home</a></li>
				<li><a
					href="${server}/shop-grid?categoryId=-1&page=0&searchText=&typeSearch=name&operator=LIKE">Shop</a>
				</li>
				<li><a href="#">Pages</a>
					<ul class="header__menu__dropdown">
						<li><a href="${server}/shoping-cart">Shoping Cart</a></li>
						<li><a href="${server}/checkout">Check Out</a></li>
					</ul></li>
				<li><a href="./blog.html">Blog</a></li>
				<li><a href="./contact.html">Contact</a></li>
			</ul>
		</nav>
		<div id="mobile-menu-wrap"></div>
		<div class="header__top__right__social">
			<a href="#"><i class="fa fa-facebook"></i></a> <a href="#"><i
				class="fa fa-twitter"></i></a> <a href="#"><i class="fa fa-linkedin"></i></a>
			<a href="#"><i class="fa fa-pinterest-p"></i></a>
		</div>
		<div class="humberger__menu__contact">
			<ul>
				<li><i class="fa fa-envelope"></i> noreply@fruitshop.com</li>
				<li>Free Shipping for all Order of $99</li>
			</ul>
		</div>
	</div>
	<!-- Humberger End -->

	<!-- Header Section Begin -->
	<header class="header">
		<div class="header__top">
			<div class="container">
				<div class="row">
					<div class="col-lg-6 col-md-6">
						<div class="header__top__left">
							<ul>
								<li><i class="fa fa-envelope"></i> noreply@fruitshop.com</li>
								<li>Free Shipping for all Order of $99</li>
							</ul>
						</div>
					</div>
					<div class="col-lg-6 col-md-6">
						<div class="header__top__right">
							<div class="header__top__right__social">
								<a href="#"><i class="fa fa-facebook"></i></a> <a href="#"><i
									class="fa fa-twitter"></i></a> <a href="#"><i
									class="fa fa-linkedin"></i></a> <a href="#"><i
									class="fa fa-pinterest-p"></i></a>
							</div>
							<div class="header__top__right__language">
								<img src="img/language.png" alt="">
								<div>English</div>
								<span class="arrow_carrot-down"></span>
								<ul>
									<li><a href="#">English</a></li>
								</ul>
							</div>
							<%
								if (!username.equals("") && checkRole == false) {
							%>
							<div class="header__top__right__auth">
								<a href="/user-profile"><i class="fa fa-user"></i> My Account</a>
							</div>
							<div class="header__top__right__auth">
								<a href="/logout"><i class="fa fa-sign-out"></i> Logout</a>
							</div>
							<%
								}
							%>
							<%
								if (!username.equals("") && checkRole == true) {
							%>
							<div class="header__top__right__auth">
								<a href="/admin/index"><i class="fa fa-user-secret"></i> Admin Home</a>
							</div>
							<div class="header__top__right__auth">
								<a href="/logout"><i class="fa fa-exclamation"></i> Logout</a>
							</div>
							<%
								}
							%>
							<%
								if (username.equals("") && checkRole == false) {
							%>
							<div class="header__top__right__auth">
								<a href="/login"><i class="fa fa-user"></i> Login</a>
							</div>
							<div class="header__top__right__auth">
								<a href="/sigin"><i class="fa fa-sign-in"></i> Sigin</a>
							</div>
							<%
								}
							%>

						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="container">
			<div class="row">
				<div class="col-lg-3">
					<div class="header__logo">
						<a href="${server}/home"><img src="img/logo.png" alt=""></a>
					</div>
				</div>
				<div class="col-lg-6">

					<jsp:include page="navbar.jsp" />


				</div>
				<div class="col-lg-3">
					<div class="header__cart">
						<ul>
							<li><a href="#"><i class="fa fa-heart"></i> <span>1</span></a></li>
							<li><a href="#"><i class="fa fa-shopping-bag"></i> <span>3</span></a></li>
						</ul>
						<div class="header__cart__price">
							item: <span>$150.00</span>
						</div>
					</div>
				</div>
			</div>
			<div class="humberger__open">
				<i class="fa fa-bars"></i>
			</div>
		</div>
	</header>
	<!-- Header Section End -->

	<!-- Hero Section Begin -->
	<section class="hero">
		<div class="container">
			<div class="row">
				<div class="col-lg-3">
					<div class="hero__categories">
						<div class="hero__categories__all">
							<i class="fa fa-bars"></i> <span>Menu</span>
						</div>
						<ul style="${display}">
							<li><a
								href="${server}/shop-grid?categoryId=-1&page=0&searchText=&typeSearch=name&operator=LIKE">Shopping</a>
							</li>
							<li><a href="${server}/shoping-cart">Shopping Cart</a></li>
							<li><a href="${server}/checkout">Check Out</a></li>
							<li><a href="${server}/blog">Blog</a></li>
							<li><a href="${server}/contact">Contact</a></li>
						</ul>
					</div>
				</div>
				<div class="col-lg-9">
					<div class="hero__search">
						<div class="hero__search__form">
							<form action="${server}/shop-grid-search" method="GET">
								<div class="hero__search__categories" id="outPutTypeName">
									All Products <span onclick="showAllCategory();"
										class="arrow_carrot-down dropdowncate"></span>
								</div>
								<input type="hidden" id="typeToSearch" value="name"
									name="typeSearch" /> <input type="hidden"
									id="categoryIdToSearch" value="-1" name="categoryId" /> <input
									type="hidden" id="operator" value="LIKE" name="operator" /> <input
									type="text" name="searchText" placeholder="What do yo u need?">
								<button type="submit" class="site-btn">SEARCH</button>
							</form>
						</div>
						<div id="dropDownCategory" class="dropdown-content">
							<span onclick="getTypeSearch(event);">All Products</span>
							<ul id="cateNameToSearch" class="list-group listcate">
								<c:forEach items="${categories}" var="category">
									<c:if test="${category.status = true}">
										<li onclick="getTypeSearch(event);" value="${category.id}"
											class="list-group-item">${category.name}</li>
									</c:if>
								</c:forEach>
							</ul>
						</div>
						<div class="hero__search__phone">
							<div class="hero__search__phone__icon">
								<i class="fa fa-phone"></i>
							</div>
							<div class="hero__search__phone__text">
								<h5>+65 11.188.888</h5>
								<span>support 24/7 time</span>
							</div>
						</div>
					</div>

					<c:if test="${menu == 'menu'}">
						<div class="hero__item set-bg" data-setbg="img/hero/banner.jpg">
							<div class="hero__text">
								<span>FRUIT FRESH</span>
								<h2>
									Vegetable <br />100% Organic
								</h2>
								<p>Free Pickup and Delivery Available</p>
								<a href="#" class="primary-btn">SHOP NOW</a>
							</div>
						</div>
					</c:if>
				</div>
			</div>
		</div>
	</section>
	<!-- Hero Section End -->