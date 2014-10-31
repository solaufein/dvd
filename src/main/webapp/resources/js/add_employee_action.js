var initAddButton = (function () {
    // ADD ACTION
    console.log("init Add Button entered");

    var redirectLocation = $("#navigation_left").find("ul li.employees a").attr('href');
    var emailRegex = /^[a-zA-Z0-9.!#$%&'*+\/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/;
    var firstName = $("#firstName");
    var fooname = "moj fooo";
    var lastName = $("#lastName");
    var phoneNumber = $("#phoneNumber");
    var email = $("#email");
    var roles = $("#rolesSet");
    var password = $("#password");
    var repassword = $("#repassword");
    var createUserButton = $("#create-user");
    var allFields = $([]).add(firstName).add(lastName).add(phoneNumber).add(email).add(password).add(repassword);
    var tips = $(".validateTips");
    var dialog;
    var form;

    function setAddButton() {
        console.log("setAddButton entered");

        emailRegex = /^[a-zA-Z0-9.!#$%&'*+\/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/;
        firstName = $("#firstName");
        lastName = $("#lastName");
        phoneNumber = $("#phoneNumber");
        email = $("#email");
        roles = $("#rolesSet");
        password = $("#password");
        repassword = $("#repassword");
        createUserButton = $("#create-user");
        allFields = $([]).add(firstName).add(lastName).add(phoneNumber).add(email).add(password).add(repassword);
        tips = $(".validateTips");

        function addUser() {
            console.log("addUser entered");
            console.log(fooname);
            console.log(firstName);

            var valid = true;
            allFields.removeClass("ui-state-error");

            valid = valid && checkLength(tips, firstName, "firstname", 3, 16);
            valid = valid && checkLength(tips, lastName, "lastname", 3, 16);
            valid = valid && checkLength(tips, phoneNumber, "phone number", 3, 16);
            valid = valid && checkLength(tips, email, "email", 6, 80);
            valid = valid && checkLength(tips, password, "password", 5, 16);

            valid = valid && checkRegexp(tips, firstName, /^[a-z]([0-9a-z_\s])+$/i, "Firstname may consist of a-z, 0-9, underscores, spaces and must begin with a letter.");
            valid = valid && checkRegexp(tips, lastName, /^[a-z]([0-9a-z_\s])+$/i, "Lastname may consist of a-z, 0-9, underscores, spaces and must begin with a letter.");
            valid = valid && checkRegexp(tips, email, emailRegex, "eg. ui@jquery.com");
            valid = valid && checkRegexp(tips, password, /^([0-9a-zA-Z])+$/, "Password field only allow : a-z 0-9");

            valid = valid && checkPasswordMatch(tips, password, repassword);

            if (valid) {
                // ajax call -> controller get data -> zapis do bazy -> on success reload page
                console.log("valid. saving...");

                var datas = form.serialize();
                console.log("data = " + datas);

                var urls = createUserButton.attr("value");
                console.log("url = " + urls);

                $.ajax({
                    type: "POST",
                    url: urls,
                    data: datas,
                    success: function (data) {
                        // logic, on success
                        //      var redirectLocation = $("#navigation_left ul li.employees a").attr('href');
                        console.log("redirect location = " + redirectLocation + "?currentPage=" + currPage);

                        $.ajax
                        ({ url: redirectLocation,
                            success: function (html) {
                                $("#ajax-content").empty().append(html);
                            }
                        });
                    }
                });

                dialog.dialog("close");
            }
            return valid;
        }

        dialog = $("#dialog-form").dialog({
            autoOpen: false,
            height: 500,
            width: 350,
            modal: true,
            buttons: {
                "Create an account": addUser,
                Cancel: function () {
                    dialog.dialog("close");
                }
            },
            close: function () {
                form[ 0 ].reset();
                allFields.removeClass("ui-state-error");
            }
        });

        form = dialog.find("form").on("submit", function (event) {
            event.preventDefault();
            addUser();
        });

        $("#create-user").button().on("click", function () {
            dialog.dialog("open");
            //  return false;
        });
    }

    return {					//returns object - it can access private variables and functions inside
        initAdd: setAddButton
    }
})();

