<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>

<c:set value="${pageContext.request.contextPath}" var="contextPath"/>
<c:url value="${contextPath}" var="home"/>

<a href="${home}">Go to home</a>

<c:if test="${not empty errCode}">
    <h1>${errCode} : System Errors</h1>
</c:if>

<c:if test="${empty errCode}">
    <h1>System Errors</h1>
</c:if>

<c:if test="${not empty errMsg}">
    <h2>${errMsg}</h2>
</c:if>

</body>
</html>
