<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../variable.jsp" %>

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
