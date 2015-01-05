var loading;

function tabz(obj, loadingGif) {

    loading = $('<div id="loading"/>');
    //  loading.prepend('<img src="' + loadingGif + '" />');
    loading.addClass("content-loading");

    $("#ajax-content").empty().append(loading);
    $("#navigation_left ul li a").removeClass("current");
    $(obj).addClass("current");

    $.ajax
    ({ url: obj.href,
        success: function (html) {
            $("#ajax-content").empty().append(html);
        }
    });
    return false;
}

function tabsAction(loadingGif) {
    $("#navigation_left ul li a").on("click", function (e) {
        e.preventDefault();

        // sprawdzenie, if (this Has class .employees / this Has class .promotions - to taki ajax)

        if ($(this).parent().hasClass("promotions")) {
            tabz(this, loadingGif);
        } else {
            tabz(this, loadingGif);
        }
    });
}
