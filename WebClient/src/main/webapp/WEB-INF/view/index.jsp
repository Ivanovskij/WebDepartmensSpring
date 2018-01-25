<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:url value="resources/assets/css" var="css"/>
<spring:url value="resources/assets/js" var="js"/>
<spring:url value="resources/assets/images" var="images"/>

<c:set value="${pageContext.request.contextPath}" var="contextPath"/>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Home - bootstrap theme</title>

    <!-- Bootstrap Core CSS -->
    <link href="${contextPath}/${css}/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="${contextPath}/${css}/shop-item.css" rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

    <!-- Navigation -->
    <%@include file="./template/navigation.jsp"  %>
    
    
    <!-- Page Content -->
    <%@include file="./template/content.jsp"%>
    <!-- /.container -->

    <!-- Footer -->
    <%@include file="./template/footer.jsp"  %>

    <!-- jQuery -->
    <script src="${contextPath}/${js}/jquery.js"></script>
    
    <!-- Bootstrap Core JavaScript -->
    <script src="${contextPath}/${js}/bootstrap.min.js"></script>

</body>

</html>
