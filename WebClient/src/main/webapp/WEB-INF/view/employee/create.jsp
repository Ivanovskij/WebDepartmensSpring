<!-- Page Content -->
<form action="#" id="form_create" >
    <table class="table table-hover">
        <tbody>
        <tr>
            <td><input type="text" name="fio" placeholder="fio"></td>
            <td><input type="text" name="dateOfBirth" placeholder="date_of_birth"></td>
            <td><input type="text" name="salary" placeholder="salary"></td>
            <td>
                <select name="department" id="select">
                    <c:forEach items="${departments}" var="dep">
                        <option value="${dep.name_dep}">${dep.name_dep}</option>
                    </c:forEach>
                </select>
            </td>
            <td><button id="btn_create">+</button></td>
        </tr>
        </tbody>
    </table>
</form>

<div id="ajaxResponse"></div>
<!-- /.container -->