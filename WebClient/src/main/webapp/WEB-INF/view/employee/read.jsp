
<form id="form_search" action="#">
    <input id="from" type="text" name="from" placeholder="form or exact date">
    <input id="to" type="text" name="to" placeholder="to">
    <button id="btn_search">Search</button>
</form>

<!-- Page Content -->
    <table class="table table-hover">
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
                    <td>${emp.id}</td>
                    <td>${emp.fio}</td>
                    <td>${emp.dateOfBirth}</td>
                    <td>${emp.salary}</td>
                    <td>${emp.department}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <!-- /.container -->

<div id="ajaxResponse"></div>

