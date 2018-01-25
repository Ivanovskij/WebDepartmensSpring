    <!-- Page Content -->
    <table>
        <thead>
        <tr>
            <th>id</th>
            <th>Name</th>
        </tr>
        </thead>
        <tbody>
            <c:forEach var="department" items="${result}">
                <tr>
                    <td>${department.id}</td>
                    <td>${department.name_dep}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <!-- /.container -->

