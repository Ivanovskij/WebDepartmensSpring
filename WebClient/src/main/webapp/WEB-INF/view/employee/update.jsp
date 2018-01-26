<form id="form_update" action="#">
    <table class="table table-hover">
        <thead>
        <tr>
            <th>id</th>
            <th>fio</th>
            <th>dateOfBirth</th>
            <th>salary</th>
            <th>department</th>
            <th>action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="emp" items="${result}">
            <tr>
                <td><input class="employeeId" type="text" disabled name="id" value="${emp.id}"></td>
                <td><input class="fio" type="text" name="fio" value="${emp.fio}"></td>
                <td><input class="dateOfBirth" type="text" name="dateOfBirth" value="${emp.dateOfBirth}"></td>
                <td><input class="salary" type="text" size="5" name="salary" value="${emp.salary}"></td>
                <td><input class="department" type="text" name="department" value="${emp.department}"></td>
                <td><button class="btn_update">Edit</button></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</form>

<div id="ajaxResponse"></div>
<!-- /.container -->
