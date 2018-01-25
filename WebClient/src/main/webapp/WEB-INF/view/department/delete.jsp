<!-- Page Content -->
<form id="form_delete" action="#">
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
                    <td><input class="departmentId" type="text" name="id" value="${department.id}"></td>
                    <td><input type="text" name="name_dep" value="${department.name_dep}"></td>
                    <td><button class="btn_delete">delete</button></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <div id="ajaxResponse"></div>
</form>
<!-- /.container -->
