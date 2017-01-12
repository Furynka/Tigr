$(document).ready(function () {

    $(".del-button").on("click", function () {
        if (confirm($(this).attr("confirmMessage")))
            window.location.href = $(this).attr("link");
    })

    $(".tigr-button").unbind("click");
    $(".tigr-button").on("click", function () {
        window.location.href = $(this).attr("link");
    })

});