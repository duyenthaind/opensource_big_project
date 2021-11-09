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
            <jsp:include page="blog-sidebar.jsp"/>
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
                                    <h5><a href="${server}/blog-details?id=${blog.id}">${blog.thumbnail}</a></h5>
                                    <p>${blog.shortDescription}</p>
                                    <a href="${server}/blog-details?id=${blog.id}" class="blog__btn">READ MORE <span class="arrow_right"></span></a>
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