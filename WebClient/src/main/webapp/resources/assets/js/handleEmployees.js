$(document).ready(function() {

    var URL_TO_MAPPING_CLIENT = "http://localhost:8084/employees";

    $("#btn_search").click(function(e) {
        e.preventDefault();

        var searchFrom = $("#from").val();
        var searchTo = $("#to").val();

        var url = "";
        if (searchTo == null) {
            url = URL_TO_MAPPING_CLIENT + "/search/" + searchFrom;
        } else {
            url = URL_TO_MAPPING_CLIENT + "/search/" + searchFrom + "/" + searchTo;
        }

        $.ajax({
            url: url,
            type: 'GET',
            contentType: 'application/json',
            headers: { }, // Разные заголовки, нестандартные заголовки
            success: function (res) {
                $("#body").html(res);
            },
            error: function (res) {
                console.log(res);
                $("#ajaxResponse").html("error search");
            },

            dataType: "html"
        });
    });

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

        var employeeId = $(this).closest("tr").find("input.employeeId").val();

        $.ajax({
            url: URL_TO_MAPPING_CLIENT + "/delete/" + employeeId,
            type: 'DELETE',
            contentType: 'application/json',
            headers: { }, // Разные заголовки, нестандартные заголовки
            success: function (res) {
                $("#ajaxResponse").html("success deleted, please refresh page");
            },
            error: function () {
                console.log(res);
                $("#ajaxResponse").html("error deleted");
            }
        });
    });


    $(".btn_update").click(function(e) {
        e.preventDefault();

        var employeeId = $(this).closest("tr").find("input.employeeId").val();
        var fio = $(this).closest("tr").find("input.fio").val();
        var dateOfBirth = $(this).closest("tr").find("input.dateOfBirth").val();
        var salary = $(this).closest("tr").find("input.salary").val();
        var department = $(this).closest("tr").find("input.department").val();

        var jsonObject = {
            "id" : employeeId,
            "fio" : fio,
            "dateOfBirth" : dateOfBirth,
            "salary" : salary,
            "department" : department,
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