var loading;

function tabz(obj, loadingGif) {

    loading = $('<div id="loading"/>');
    loading.prepend('<img src="' + loadingGif + '" />');

    //loading = $('<div></div>');
    //loading.addClass(".content-loading");

    $("#ajax-content").empty().append(loading);
    $("#navigation_left ul li a").removeClass("current");
    $(obj).addClass("current");

    $.ajax
    ({ url: obj.href,
       success: function(html) {
        $("#ajax-content").empty().append(html);
        }
    });
        return false;
}

function tabsAction(loadingGif){
    $("#navigation_left ul li a").on("click", function(e){
        e.preventDefault();
        tabz(this, loadingGif);
    });
}
