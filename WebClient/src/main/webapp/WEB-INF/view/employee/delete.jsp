<!-- Page Content -->
<form id="form_delete" action="#">
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
                    <td><input class="employeeId" size="5" disabled value="${emp.id}"></td>
                    <td>${emp.fio}</td>
                    <td>${emp.dateOfBirth}</td>
                    <td>${emp.salary}</td>
                    <td>${emp.department}</td>
                    <td><button class="btn_delete">-</button></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</form>

<div id="ajaxResponse"></div>
<!-- /.container -->
