var editBtnAction = (function () {
    // EDIT ACTION
    console.log("init Edit Button entered");

    var redirectLocation = $("#navigation_left").find("ul li.employees a").attr('href');
    var emailRegex = /^[a-zA-Z0-9.!#$%&'*+\/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/;
    var firstName = $("#firstName");
    var lastName = $("#lastName");
    var phoneNumber = $("#phoneNumber");
    var email = $("#email");
    var roles = $("#rolesSet");
    var password = $("#password");
    var repassword = $("#repassword");
    var allFields = $([]).add(firstName).add(lastName).add(phoneNumber).add(email).add(password).add(repassword);
    var tips = $(".validateTips");
    var editEmpMethod = $(".editemp").attr("method");
    var editEmpAction = $(".editemp").attr("action");
    var dialog;
    var form;

    function setEditButton() {
        console.log("setEditButton entered");

        emailRegex = /^[a-zA-Z0-9.!#$%&'*+\/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/;
        firstName = $("#firstName");
        lastName = $("#lastName");
        phoneNumber = $("#phoneNumber");
        email = $("#email");
        roles = $("#rolesSet");
        password = $("#password");
        repassword = $("#repassword");
        allFields = $([]).add(firstName).add(lastName).add(phoneNumber).add(email).add(password).add(repassword);
        tips = $(".validateTips");
        editEmpMethod = $(".editemp").attr("method");
        editEmpAction = $(".editemp").attr("action");

        // this method is called when Save edition Button in modal form is clicked
        function editUser() {
            console.log("editUser entered");

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
                console.log("valid. saving edited employee...");

                var datas = form.serialize();
                console.log("data = " + datas);
                console.log("url = " + editEmpAction);

                $.ajax({
                    type: "POST",
                    url: editEmpAction,
                    data: datas,
                    success: function (data) {
                        // logic, on success
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

        // utworzenie okienka
        dialog = $("#dialog-form").dialog({
            autoOpen: false,
            height: 500,
            width: 350,
            modal: true,
            buttons: {
                "Save edition": editUser,
                Cancel: function () {
                    dialog.dialog("close");
                }
            },
            close: function () {
                form[ 0 ].reset();
                allFields.removeClass("ui-state-error");
            }
        });

        //przypisanie akcji dla formularza przy Submicie
        form = dialog.find("form").on("submit", function (event) {
            event.preventDefault();
            editUser();
        });

        $(".table").on("click", ".editempbtn", function (e) {		//oddelegowanie .editempbtn - za kazdym razem szuka w .table przycisku o tej klasie
            e.preventDefault();

            //1 tutaj zrobic ajax call z idekiem z edit buttona, aby uzyskac dane employee...
            //2 po uzyskaniu danych(on success) wrzucamy je do forma i otwieramy ten dialog
            //3 przed zapisem - validacja - jesli ok, to ajax call -> update w bazie i on success odswiezenie strony

            var dataForm = $(this).parent().serialize();
            console.log("dataForm = " + dataForm);
            console.log("editEmpAction = " + editEmpAction);

            //get employee data by ID
            $.ajax
            ({
                type: "GET",
                //contentType: "application/json; charset=utf-8",
                url: editEmpAction,
                data: dataForm,
                dataType: "json",
                success: function (response) {
                    console.log(response);
                    console.log("wrzucamy dane... i otwieramy formularz");
                    //teraz wrzucamy otrzymane dane do formularza... i go otwieramy...

                    dialog.dialog("open");
                }
            });

            //dialog.dialog( "open" );
            //tutaj? czy on success ?
        });
    }

    return {					//returns object - it can access private variables and functions inside
        initBtn: setEditButton
    }

})(); //auto invoke function - only once! - so its initializing variables and functions, and returns object
