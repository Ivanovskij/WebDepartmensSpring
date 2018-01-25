$(document).ready(function() {

    var URL_TO_MAPPING_CLIENT = "http://localhost:8084/departments";

    $(".btn_delete").click(function(e) {
        e.preventDefault();

        var departmentId = $(this).closest("tr").find("input.departmentId").val();

        /*var request = new XMLHttpRequest();
        request.open("DELETE", "/departments/delete/" + departmentId);
        request.send();*/

        $.ajax({
            url: URL_TO_MAPPING_CLIENT + "/delete/" + departmentId,
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

        var departmentId = $(this).closest("tr").find("input.departmentId").val();
        var name_dep = $(this).closest("tr").find("input.name_dep").val();

        var jsonObject = {
            "id" : departmentId,
            "name_dep" : name_dep
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