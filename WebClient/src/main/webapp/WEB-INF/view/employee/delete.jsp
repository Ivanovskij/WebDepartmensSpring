<!-- Page Content -->
<form id="form_delete" action="#">
    <table>
        <thead>
        <tr>
            <th>id</th>
            <th>fio</th>
            <th>dateOfBirth</th>
            <th>salary</th>
            <th>department</th>
        </tr>
        </thead>
        <tbody>
            <c:forEach var="emp" items="${result}">
                <tr>
                    <td><input class="employeeId" type="text" name="id" value="${emp.id}"></td>
                    <td><input type="text" name="fio" value="${emp.fio}"></td>
                    <td><input type="text" name="dateOfBirth" value="${emp.dateOfBirth}"></td>
                    <td><input type="text" name="salary" value="${emp.salary}"></td>
                    <td><input type="text" name="department" value="${emp.department}"></td>
                    <td><button class="btn_delete">-</button></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</form>
<!-- /.container -->
