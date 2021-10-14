<%@ page import="com.group7.fruitswebsite.config.ApplicationConfig" %>
<!-- JSTL -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- define variable -->
<c:set var="server" value="${pageContext.servletContext.contextPath}"/>
<c:set var="req" value="${pageContext.request}"/>
<c:set var="uploadsDir" value="<%=ApplicationConfig.UPLOAD_DIR%>"/>