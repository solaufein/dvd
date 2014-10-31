/**
 * Created by Sola on 2014-10-31.
 */
function checkPasswordMatch(tip, p1, p2) {
    if (p1.val() != p2.val()) {
        p2.addClass("ui-state-error");
        updateTips(tip, "Passwords must be the same!");
        return false;
    }
    else {
        $("#divCheckPasswordMatch").html("Passwords match.");
        return true;
    }
}

function updateTips(tip, t) {
    tip
        .text(t)
        .addClass("ui-state-highlight");
    setTimeout(function () {
        tip.removeClass("ui-state-highlight", 1500);
    }, 500);
}

function checkLength(tip, o, n, min, max) {
    if (o.val().length > max || o.val().length < min) {
        o.addClass("ui-state-error");
        updateTips(tip, "Length of " + n + " must be between " +
            min + " and " + max + ".");
        return false;
    } else {
        return true;
    }
}

function checkRegexp(tip, o, regexp, n) {
    if (!( regexp.test(o.val()) )) {
        o.addClass("ui-state-error");
        updateTips(tip, n);
        return false;
    } else {
        return true;
    }
}

function submitFormViaAjax() {
    $.ajax({
        type: "POST",
        //    contentType: "application/json; charset=utf-8",	// default is 'application/x-www-form-urlencoded; charset=UTF-8', if you want send data as JSON change it - i think
        dataType: "json",     // expected data type in return
        url: "your controller url",
        //	data: "{'data1':'" + value1+ "', 'data2':'" + value2+ "', 'data3':'" + value3+ "'}",	// json data
        data: $("#form").serialize(),	// normal data
        success: function (data) {
            // logic, on success
        }
    });
}