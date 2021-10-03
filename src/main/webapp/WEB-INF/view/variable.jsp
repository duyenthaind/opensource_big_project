<!-- JSTL -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- define variable -->
<c:set var="server" value="${pageContext.servletContext.contextPath}"></c:set>
<c:set var="req" value="${pageContext.request}" />