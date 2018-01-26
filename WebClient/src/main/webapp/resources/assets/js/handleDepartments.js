$(document).ready(function() {

    var URL_TO_MAPPING_CLIENT = "http://localhost:8084/departments";

    $("#btn_create").click(function(e) {
        e.preventDefault();

        var $form_create = $("#form_create");
        var jsonObject = $form_create.serializeObject();

        $.ajax({
            url: URL_TO_MAPPING_CLIENT + "/create",
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(jsonObject),
            headers: { }, // Разные заголовки, нестандартные заголовки
            success: function (res) {
                console.log("success created");
                $("#ajaxResponse").html("success created");
            },
            error: function (res) {
                console.log(res);
                $("#ajaxResponse").html("error created");
            }
        });
    });

    $(".btn_delete").click(function(e) {
        e.preventDefault();

        var departmentId = $(this).closest("tr").find("input.departmentId").val();

        /*var request = new XMLHttpRequest();
        request.open("DELETE", "/departments/delete/" + departmentId);
        request.send();*/

        $.ajax({
            url: URL_TO_MAPPING_CLIENT + "/delete/" + departmentId,
            type: 'DELETE',
            contentType: 'application/json',
            headers: { }, // Разные заголовки, нестандартные заголовки
            success: function () {
                $("#ajaxResponse").html("success deleted, please refresh page");
            },
            error: function (res) {
                console.log(res);
                $("#ajaxResponse").html("error deleted");
            }
        });
    });

    $(".btn_update").click(function(e) {
        e.preventDefault();

        var departmentId = $(this).closest("tr").find("input.departmentId").val();
        var name_dep = $(this).closest("tr").find("input.name_dep").val();

        var jsonObject = {
            "id" : departmentId,
            "name_dep" : name_dep
        };

        $.ajax({
            url: URL_TO_MAPPING_CLIENT + "/update",
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(jsonObject),
            headers: { }, // Разные заголовки, нестандартные заголовки
            success: function (res) {
                console.log("success updated");
                $("#ajaxResponse").html("success updated");
            },
            error: function (res) {
                console.log(res);
                $("#ajaxResponse").html("error updated");
            }
        });
    });
});