<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="act" value="${action}" />

<c:choose>
	<c:when test="${act == 'index'}">
		<nav class="header__menu">
			<ul>
				<li class="active"><a href="${base}/index">Home</a></li>
				<li><a href="${base}/shop-grid?categoryId=-1&page=0&searchText=&typeSearch=name&operator=LIKE">Shop</a></li>
				<li><a href="#">Pages</a>
					<ul class="header__menu__dropdown">
						<li><a href="${server}/shoping-cart">Shoping Cart</a></li>
						<li><a href="${server}/checkout">Check Out</a></li>
					</ul></li>
				<li><a href="${base}/blog">Blog</a></li>
				<li><a href="${base}/contact">Contact</a></li>
			</ul>
		</nav>
	</c:when>
	<c:when test="${act == 'blog'}">
		<nav class="header__menu">
			<ul>
				<li><a href="${base}/index">Home</a></li>
				<li><a href="${base}/shop-grid?categoryId=-1&page=0&searchText=&typeSearch=name&operator=LIKE">Shop</a></li>
				<li><a href="#">Pages</a>
					<ul class="header__menu__dropdown">
						<li><a href="${base}/shoping-cart">Shoping Cart</a></li>
						<li><a href="${base}/checkout">Check Out</a></li>
					</ul></li>
				<li class="active"><a href="${base}/blog">Blog</a></li>
				<li><a href="${base}/contact">Contact</a></li>
			</ul>
		</nav>
	</c:when>
	<c:when test="${act == 'contact'}">
		<nav class="header__menu">
			<ul>
				<li><a href="${base}/index">Home</a></li>
				<li><a href="${base}/shop-grid?categoryId=-1&page=0&searchText=&typeSearch=name&operator=LIKE">Shop</a></li>
				<li><a href="#">Pages</a>
					<ul class="header__menu__dropdown">
						<li><a href="${base}/shoping-cart">Shoping Cart</a></li>
						<li><a href="${base}/checkout">Check Out</a></li>
					</ul></li>
				<li><a href="${base}/blog">Blog</a></li>
				<li class="active"><a href="${base}/contact">Contact</a></li>
			</ul>
		</nav>
	</c:when>
	<c:otherwise>
		<nav class="header__menu">
			<ul>
				<li><a href="${base}/index">Home</a></li>
				<li class="active"><a href="${base}/shop-grid?categoryId=-1&page=0&searchText=&typeSearch=name&operator=LIKE">Shop</a></li>
				<li><a href="#">Pages</a>
					<ul class="header__menu__dropdown">
						<li><a href="${base}/shoping-cart">Shoping Cart</a></li>
						<li><a href="${base}/checkout">Check Out</a></li>
					</ul></li>
				<li><a href="${base}/blog">Blog</a></li>
				<li><a href="${base}/contact">Contact</a></li>
			</ul>
		</nav>
	</c:otherwise>
</c:choose>

