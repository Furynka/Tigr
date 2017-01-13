$().ready(function () {

    $(".del-button").on("click", function () {
        if (confirm($(this).attr("confirmMessage")))
            window.location.href = $(this).attr("link");
    })

    $(".js-button").unbind("click");
    $(".js-button").on("click", function () {
        window.location.href = $(this).attr("link");
    })

});