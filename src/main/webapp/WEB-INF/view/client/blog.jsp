<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- header -->
<jsp:include page="common/header.jsp"/>
<%@ include file="../variable.jsp" %>
<!-- end header -->

<!-- container -->

<section class="breadcrumb-section set-bg" data-setbg="${server}/img/breadcrumb.jpg">
    <div class="container">
        <div class="row">
            <div class="col-lg-12 text-center">
                <div class="breadcrumb__text">
                    <h2>Blog</h2>
                    <div class="breadcrumb__option">
                        <a href="${server}/home">Home</a>
                        <span>Blog</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- Breadcrumb Section End -->

<!-- Blog Section Begin -->
<section class="blog spad">
    <div class="container">
        <div class="row">
            <div class="col-lg-4 col-md-5">
                <div class="blog__sidebar">
                    <div class="blog__sidebar__search">
                        <form action="${server}/blog/search" id="searchBlogForm" method="get">
                            <input type="text" placeholder="Search..." name="textSearch"/>
                            <button type="submit" ><span class="icon_search"></span></button>
                        </form>
                    </div>
                    <div class="blog__sidebar__item">
                        <h4>Recent News</h4>
                        <div class="blog__sidebar__recent">
                            <c:forEach var="blog" items="${mostRecentBlogs}">
                                <c:if test="${blog.status = true}">
                                    <a href="#" class="blog__sidebar__recent__item">
                                        <div class="blog__sidebar__recent__item__pic"
                                             style="width: 70px; height: 70px;">
                                            <img src="${server}/${uploadsDir}/${blog.avatar}" alt="">
                                        </div>
                                        <div class="blog__sidebar__recent__item__text">
                                            <h6>${blog.thumbnail}</h6>
                                            <span>${blog.date}</span>
                                        </div>
                                    </a>
                                </c:if>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="blog__sidebar__item">
                        <h4>Search By</h4>
                        <div class="blog__sidebar__item__tags">
                            <a href="${server}/blog/search?page=${index}&textSearch=Apple">Apple</a>
                            <a href="${server}/blog/search?page=${index}&textSearch=Beauty">Beauty</a>
                            <a href="${server}/blog/search?page=${index}&textSearch=Vegetables">Vegetables</a>
                            <a href="${server}/blog/search?page=${index}&textSearch=Apple">Fruit</a>
                            <a href="${server}/blog/search?page=${index}&textSearch=Healthy Food">Healthy Food</a>
                            <a href="${server}/blog/search?page=${index}&textSearch=Lifestyle">Lifestyle</a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-8 col-md-7">
                <div class="row">
                    <c:forEach var="blog" items="${displayBlogs}">
                        <div class="col-lg-6 col-md-6 col-sm-6">
                            <div class="blog__item">
                                <div class="blog__item__pic" style="width: 360px; height:260px; ">
                                    <img src="${server}/${uploadsDir}/${blog.avatar}" alt="" style="width: 100%; height: 100%;">
                                </div>
                                <div class="blog__item__text">
                                    <ul>
                                        <li><i class="fa fa-calendar-o"></i>${blog.date}</li>
                                    </ul>
                                    <h5><a href="#">${blog.thumbnail}</a></h5>
                                    <p>${blog.shortDescription}</p>
                                    <a href="#" class="blog__btn">READ MORE <span class="arrow_right"></span></a>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                    <div class="col-lg-12">
                        <div class="product__pagination blog__pagination">
                            <c:if test="${displayBlogs != null && listPageIndexes != null}">
                                <c:forEach var="index" items="${listPageIndexes}">
                                    <c:choose>
                                        <c:when test="${textSearch != null}">
                                            <a href="${server}/blog/search?page=${index}&textSearch=${textSearch}">${index + 1}</a>
                                        </c:when>
                                        <c:otherwise>
                                            <a href="${server}/blog/search?page=${index}">${index + 1}</a>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </c:if>
                            <a href="#"><i class="fa fa-long-arrow-right"></i></a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<!-- end container -->

<!-- footer -->
<jsp:include page="common/footer.jsp"/>
<!-- end footer -->