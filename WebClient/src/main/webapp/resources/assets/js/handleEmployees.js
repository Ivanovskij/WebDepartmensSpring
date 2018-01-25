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
            contentType: 'application/json', // тот тип контента, который вы будете получать от своего сервиса
            headers: { }, // Разные заголовки, нестандартные заголовки, не забудте их указать в Access-Control-Allow-Headers
            success: function (res) {
                $("#body").html(res);
            },
            error: function () { console.log("Error"); },

            dataType: "html"
        });
    });


    $(".btn_delete").click(function(e) {
        e.preventDefault();

        var employeeId = $(this).closest("tr").find("input.employeeId").val();

        /*var request = new XMLHttpRequest();
        request.open("DELETE", "/departments/delete/" + departmentId);
        request.send();*/

        $.ajax({
            url: URL_TO_MAPPING_CLIENT + "/delete/" + employeeId,
            type: 'DELETE',
            contentType: 'application/json', // тот тип контента, который вы будете получать от своего сервиса
            headers: { }, // Разные заголовки, нестандартные заголовки, не забудте их указать в Access-Control-Allow-Headers
            success: function (res) {
                console.log(res);
            },
            error: function () { console.log("Error"); }
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
            contentType: 'application/json', // тот тип контента, который вы будете получать от своего сервиса
            data: JSON.stringify(jsonObject),
            headers: { }, // Разные заголовки, нестандартные заголовки, не забудте их указать в Access-Control-Allow-Headers
            success: function (res) {
                console.log(res);
            },
            error: function (res) { console.log(res); },

            dataType: "json"
        });
    });
});