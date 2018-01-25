<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Page Content -->

    <table>
        <thead>
        <tr>
            <th>Name</th>
            <th>Average salary</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="salary" items="${result}">
            <tr>

                <td><input type="text" class="department" name="department" readonly="true" value="${salary.department}"></td>
                <td><input type="text" class="avgsalary" name="avgsalary" value="${salary.avgSalary}"></td>

            </tr>
        </c:forEach>
        </tbody>
    </table>

