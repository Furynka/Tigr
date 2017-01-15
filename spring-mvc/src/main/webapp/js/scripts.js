function showMessage(message, type) {
    type = type || "danger";
    var type_re = new RegExp("alert-\\w+", "g");

    setTimeout(function () {
        var message_el = document.getElementById("message");
        message_el.className = message_el.className.replace(type_re, "alert-"+type);
        message_el.innerHTML = message;

        message_el.style.opacity = 1;
        message_el.style["margin-top"] = "20px";

        setTimeout(function () {
            message_el.style.opacity = 0;
            message_el.style["margin-top"] = "-40px";
        }, 4000)
    }, 300);
}