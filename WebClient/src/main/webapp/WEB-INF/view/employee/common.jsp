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

    <title>Employees - ${title}</title>

    <script>
        window.menu = '${title}';
    </script>

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

<body id="body">

    <!-- Navigation -->
    <%@include file="../template/navigation.jsp" %>

    <div class="container">
        <div class="row">
            <!-- Sidebar -->
            <c:url value="${contextRoot}/employees" var="list"/>
            <c:url value="${contextRoot}/employees/create" var="create"/>
            <c:url value="${contextRoot}/employees/update" var="update"/>
            <c:url value="${contextRoot}/employees/delete" var="delete"/>
            <div class="col-lg-3">
                <h1 class="my-4">Actions</h1>
                <div class="list-group">
                    <a id="link_read" href="${list}" class="list-group-item">Read</a>
                    <a id="link_create" href="${create}" class="list-group-item">Create</a>
                    <a id="link_update" href="${update}" class="list-group-item">Update</a>
                    <a id="link_delete" href="${delete}" class="list-group-item">Delete</a>
                </div>
            </div>
            <!-- /.col-lg-3 -->

            <!-- Page Content -->
            <div class="container">
                <div class="row">
                    <div class="col-md-9">

                        <c:if test="${userClickReadList == true}">
                            <%@include file="read.jsp"%>
                        </c:if>

                        <c:if test="${userClickCreate == true}">
                            <%@include file="create.jsp"%>
                        </c:if>

                        <c:if test="${userClickUpdate == true}">
                            <%@include file="update.jsp"%>
                        </c:if>

                        <c:if test="${userClickDelete == true}">
                            <%@include file="delete.jsp"%>
                        </c:if>

                    </div>
                </div>
            </div>
            <!-- Page Content End -->

        </div>
    </div>
    <!-- /.container -->

    <!-- Footer -->
    <%@include file="../template/footer.jsp" %>

    <!-- jQuery -->
    <script src="${contextPath}/${js}/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="${contextPath}/${js}/bootstrap.min.js"></script>

    <!-- my scripts -->
    <script src="${contextPath}/${js}/handleEmployees.js"></script>
    <script src="${contextPath}/${js}/navigation.js"></script>
    <script src="${contextPath}/${js}/formToJSON.js"></script>
</body>

</html>
