<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Page Content -->
<form id="form_update" action="#">
    <table>
        <thead>
            <tr>
                <th>id</th>
                <th>Name</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach var="department" items="${result}">
            <tr>

                <td><input type="text" class="departmentId" name="iddepartments" readonly="true" value="${department.id}"></td>
                <td><input type="text" class="name_dep" name="name_dep" value="${department.name_dep}"></td>
                <td><button class="btn_update">Edit</button></td>

            </tr>
        </c:forEach>
        </tbody>
    </table>
</form>

<div id="ajaxResponse"></div>


<!-- /.container -->
