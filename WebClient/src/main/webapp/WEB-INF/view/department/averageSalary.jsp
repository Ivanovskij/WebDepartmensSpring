<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Page Content -->

    <table class="table table-hover">
        <thead>
        <tr>
            <th>Name</th>
            <th>Average salary</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="salary" items="${result}">
            <tr>

                <td>${salary.department}</td>
                <td>${salary.avgSalary}</td>

            </tr>
        </c:forEach>
        </tbody>
    </table>

