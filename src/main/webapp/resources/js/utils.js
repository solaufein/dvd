DvdFormUtil = {
    checkPasswordMatch: function (tip, p1, p2) {
        if (p1.val() != p2.val()) {
            p2.addClass("ui-state-error");
            this.updateTips(tip, "Passwords must be the same!");
            return false;
        }
        else {
            $("#divCheckPasswordMatch").html("Passwords match.");
            return true;
        }
    },

    updateTips: function (tip, t) {
        tip
            .text(t)
            .addClass("ui-state-highlight");
        setTimeout(function () {
            tip.removeClass("ui-state-highlight", 1500);
        }, 500);
    },

    checkLength: function (tip, o, n, min, max) {
        if (o.val().length > max || o.val().length < min) {
            o.addClass("ui-state-error");
            this.updateTips(tip, "Length of " + n + " must be between " +
                min + " and " + max + ".");
            return false;
        } else {
            return true;
        }
    },

    checkRegexp: function (tip, o, regexp, n) {
        if (!( regexp.test(o.val()) )) {
            o.addClass("ui-state-error");
            this.updateTips(tip, n);
            return false;
        } else {
            return true;
        }
    },

    checkCheckboxIsChecked: function (jsonSet, inputSet) {
        $.each(jsonSet, function (key, value) {
            //   console.log("id = " + value.id + ", role = " + value.role);
            inputSet.each(function () {
                //   console.log($(this).val());
                if (value.id == $(this).val()) {
                    //   console.log("id = " + value.id + ", is CHECKED");
                    $(this).prop('checked', true);
                }
            });
        });
    },

    submitFormViaAjax1: function (type, url, data, dataType, contentType, callback) {

        $.ajax({
            type: type,
            contentType: contentType, //"application/json; charset=utf-8",	// default is 'application/x-www-form-urlencoded; charset=UTF-8', if you want send data as JSON change it - i think
            dataType: dataType,     // expected data type in return. for example: "json"
            url: url,
            //	data: "{'data1':'" + value1+ "', 'data2':'" + value2+ "', 'data3':'" + value3+ "'}",	// json data
            data: data,	// normal data: $("#form").serialize()
            success: function (datas) {
                // logic, on success
                callback(datas);
            }
        });
    },
    submitFormViaAjax2: function (type, url, data, dataType, callback) {

        $.ajax({
            type: type,
            dataType: dataType,
            url: url,
            data: data,
            success: function (datas) {
                callback(datas);
            }
        });
    },
    submitFormViaAjax3: function (type, url, data, contentType, callback) {

        $.ajax({
            type: type,
            contentType: contentType,
            url: url,
            data: data,
            success: function (datas) {
                callback(datas);
            }
        });
    },
    submitFormViaAjax4: function (type, url, data, callback) {

        $.ajax({
            type: type,
            url: url,
            data: data,
            success: function (datas) {
                callback(datas);
            }
        });
    }
};