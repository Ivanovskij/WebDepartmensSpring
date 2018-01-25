$(function () {

    // view PageController
    switch (menu) {

        case 'create': {
            $("#link_create").addClass("active"); break;
        }
        case 'delete': {
            $("#link_delete").addClass("active"); break;
        }
        case 'update': {
            $("#link_update").addClass("active"); break;
        }
        case 'Average salary': {
            $("#link_avgSalary").addClass("active"); break;
        }
        default: {
            $("#link_read").addClass("active");
            break;
        }

    }

});