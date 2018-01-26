<!-- Page Content -->
<form id="form_delete" action="#">
    <table class="table table-hover">
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
                    <td><input class="departmentId" size="5" disabled value="${department.id}"></td>
                    <td>${department.name_dep}</td>
                    <td><button class="btn_delete">delete</button></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</form>

<div id="ajaxResponse"></div>
<!-- /.container -->
