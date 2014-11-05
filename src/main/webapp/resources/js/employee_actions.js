InitEmpActionButtons = {
    checkAction: function () {
        console.log("check action");
        if ("add") {
            this.init();
        } else {
            this.init2();
        }
    },

    /*config : {	// to jest zadeklarowane, ale nie zainicjalizowane, funkcja init nie wywoluje tego i dlatego potem jak sie odwolujemy do tego, to te wartosci sa puste
     dialog : $( "#dialog-form" ),
     form : $( "#dialog-form" ).find( "form" ),
     name : $( "#name" ),
     fooname : "foo foo",
     email : $( "#email" ),
     emailRegex : /^[a-zA-Z0-9.!#$%&'*+\/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/,
     password : $( "#password" ),
     allFields : function(){
     var allo = $( [] ).add( this.name ).add( this.email ).add( this.password );
     console.log("1 allo = " + allo.size());
     return allo;
     },
     tips : $( ".validateTips" )
     },*/

    /*config : function(){	// to jest dobra deklaracja, jesli w funkcji init, wywolamy funkcje config ktora zwroci nam rzeczywiste obiekty, to nie sa one puste i mozemy sie do nich odwolywac
     var x = {
     dialog : $( "#dialog-form" ),
     form : $( "#dialog-form" ).find( "form" ),
     name : $( "#name" ),
     fooname : "foo foo",
     email : $( "#email" ),
     emailRegex : /^[a-zA-Z0-9.!#$%&'*+\/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/,
     password : $( "#password" ),
     allFields : function(){
     var allo = $( [] ).add( this.name ).add( this.email ).add( this.password );
     console.log("1 allo = " + allo.size());
     return allo;
     },
     tips : $( ".validateTips" )
     }
     return x;
     },*/

    showPasswordFields: function (show) {
        if (show) {
            InitEmpActionButtons.config.password.prev().show();
            InitEmpActionButtons.config.password.show();
            InitEmpActionButtons.config.repassword.prev().show();
            InitEmpActionButtons.config.repassword.show();
        } else {
            InitEmpActionButtons.config.password.prev().hide();
            InitEmpActionButtons.config.password.hide();
            InitEmpActionButtons.config.repassword.prev().hide();
            InitEmpActionButtons.config.repassword.hide();
        }
    },

    init: function () {
        InitEmpActionButtons.config = {	// tutaj jest ok, mamy utworzony obiekt w funkcji init, dlatego obiekty nie sa puste i mozemy sie do nich odwolywac
            dialog: $("#dialog-form"),
            form: $("#dialog-form").find("form"),
            createUserButton: $("#create-user"),
            editEmp: $(".editemp"),
            delEmp: $(".delemp"),
            redirectLocation: $("#navigation_left").find("ul li.employees a").attr('href'),
            firstName: $("#firstName"),
            lastName: $("#lastName"),
            phoneNumber: $("#phoneNumber"),
            fooname: "foo foo",
            email: $("#email"),
            emailRegex: /^[a-zA-Z0-9.!#$%&'*+\/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/,
            password: $("#password"),
            repassword: $("#repassword"),
            allFields: function () {
                var allo = $([]).add(this.firstName).add(this.lastName).add(this.phoneNumber).add(this.email).add(this.password).add(this.repassword);
                return allo;
            },
            tips: $(".validateTips")
        };

        this.setAddButton();
        this.setEditButton();
        this.setDeleteButton();
    },

    createAddDialog: function () {
        var conf = InitEmpActionButtons.config;
        var allFields = InitEmpActionButtons.config.allFields();

        // show password and repassword fields
        InitEmpActionButtons.showPasswordFields(true);

        conf.dialog.dialog({
            title: "Create new user",
            autoOpen: false,
            height: 500,
            width: 350,
            modal: true,
            buttons: {
                "Create an account": function () {
                    InitEmpActionButtons.addUser();
                },
                Cancel: function () {
                    conf.dialog.dialog("close");
                }
            },
            close: function () {
                conf.tips.text("All form fields are required.");
                conf.form[ 0 ].reset();
                allFields.removeClass("ui-state-error");
            }
        });

        conf.form.on("submit", function (event) {
            event.preventDefault();
            InitEmpActionButtons.addUser();
        });
    },

    createEditDialog: function () {
        var conf = InitEmpActionButtons.config;
        var allFields = InitEmpActionButtons.config.allFields();

        // hide password and repassword becouse in Edition we dont want to change password
        InitEmpActionButtons.showPasswordFields(false);

        conf.dialog.dialog({
            title: "Update user",
            autoOpen: false,
            height: 500,
            width: 350,
            modal: true,
            buttons: {
                "Update an account": function () {
                    InitEmpActionButtons.editUser();
                },
                Cancel: function () {
                    conf.dialog.dialog("close");
                }
            },
            close: function () {
                conf.tips.text("All form fields are required.");
                conf.form[ 0 ].reset();
                allFields.removeClass("ui-state-error");
            }
        });

        conf.form.on("submit", function (event) {
            event.preventDefault();
            InitEmpActionButtons.editUser();
        });
    },

    addUser: function () {
        console.log("addUser entered");

        var valid = true;
        var tips = InitEmpActionButtons.config.tips;
        var firstName = InitEmpActionButtons.config.firstName;
        var lastName = InitEmpActionButtons.config.lastName;
        var email = InitEmpActionButtons.config.email;
        var emailRegex = InitEmpActionButtons.config.emailRegex;
        var password = InitEmpActionButtons.config.password;
        var repassword = InitEmpActionButtons.config.repassword;
        var phoneNumber = InitEmpActionButtons.config.phoneNumber;

        InitEmpActionButtons.config.allFields().removeClass("ui-state-error");

        valid = valid && DvdFormUtil.checkLength(tips, firstName, "firstname", 3, 16);
        valid = valid && DvdFormUtil.checkLength(tips, lastName, "lastname", 3, 16);
        valid = valid && DvdFormUtil.checkLength(tips, phoneNumber, "phone number", 3, 16);
        valid = valid && DvdFormUtil.checkLength(tips, email, "email", 6, 80);
        valid = valid && DvdFormUtil.checkLength(tips, password, "password", 5, 16);

        valid = valid && DvdFormUtil.checkRegexp(tips, firstName, /^[a-z]([0-9a-z_\s])+$/i, "Firstname may consist of a-z, 0-9, underscores, spaces and must begin with a letter.");
        valid = valid && DvdFormUtil.checkRegexp(tips, lastName, /^[a-z]([0-9a-z_\s])+$/i, "Lastname may consist of a-z, 0-9, underscores, spaces and must begin with a letter.");
        valid = valid && DvdFormUtil.checkRegexp(tips, email, emailRegex, "eg. ui@jquery.com");
        valid = valid && DvdFormUtil.checkRegexp(tips, password, /^([0-9a-zA-Z])+$/, "Password field only allow : a-z 0-9");

        valid = valid && DvdFormUtil.checkPasswordMatch(tips, password, repassword);

        if (valid) {
            // ajax call -> controller get data -> save to db -> on success reload page
            console.log("valid. saving...");

            var datas = InitEmpActionButtons.config.form.serialize();
            var urls = InitEmpActionButtons.config.createUserButton.attr("value");

            console.log("data = " + datas);
            console.log("url = " + urls);

            $.ajax({
                type: "POST",
                url: urls,
                data: datas,
                success: function (data) {
                    // logic, on success
                    console.log("redirect location = " + InitEmpActionButtons.config.redirectLocation + "?currentPage=" + currPage);

                    $.ajax
                    ({ url: InitEmpActionButtons.config.redirectLocation,
                        success: function (html) {
                            $("#ajax-content").empty().append(html);
                        }
                    });
                }
            });
            InitEmpActionButtons.config.dialog.dialog("close");
        }
        return valid;
    },

    editUser: function () {
        console.log("editUser entered");

        var valid = true;
        var tips = InitEmpActionButtons.config.tips;
        var firstName = InitEmpActionButtons.config.firstName;
        var lastName = InitEmpActionButtons.config.lastName;
        var email = InitEmpActionButtons.config.email;
        var emailRegex = InitEmpActionButtons.config.emailRegex;
        var phoneNumber = InitEmpActionButtons.config.phoneNumber;

        InitEmpActionButtons.config.allFields().removeClass("ui-state-error");

        valid = valid && DvdFormUtil.checkLength(tips, firstName, "firstname", 3, 16);
        valid = valid && DvdFormUtil.checkLength(tips, lastName, "lastname", 3, 16);
        valid = valid && DvdFormUtil.checkLength(tips, phoneNumber, "phone number", 3, 16);
        valid = valid && DvdFormUtil.checkLength(tips, email, "email", 6, 80);

        valid = valid && DvdFormUtil.checkRegexp(tips, firstName, /^[a-z]([0-9a-z_\s])+$/i, "Firstname may consist of a-z, 0-9, underscores, spaces and must begin with a letter.");
        valid = valid && DvdFormUtil.checkRegexp(tips, lastName, /^[a-z]([0-9a-z_\s])+$/i, "Lastname may consist of a-z, 0-9, underscores, spaces and must begin with a letter.");
        valid = valid && DvdFormUtil.checkRegexp(tips, email, emailRegex, "eg. ui@jquery.com");

        if (valid) {
            //ajax call controller -> update user -> redirect on success
            console.log("valid. saving edited employee...");

            var datas = InitEmpActionButtons.config.form.serialize();
            console.log("Edited data (sending) = " + datas);

            $.ajax({
                type: "POST",
                url: InitEmpActionButtons.config.editEmp.attr("action"),
                data: datas,
                success: function (data) {
                    // logic, on success
                    console.log("redirect location = " + InitEmpActionButtons.config.redirectLocation + "?currentPage=" + currPage);

                    $.ajax({
                        url: InitEmpActionButtons.config.redirectLocation,
                        success: function (html) {
                            $("#ajax-content").empty().append(html);
                        }
                    });
                }
            });
            InitEmpActionButtons.config.dialog.dialog("close");
        }

        return valid;
    },

    deleteUser: function (obj) {
        var conBox = confirm("Are you sure ?");
        if (conBox) {
            $.ajax({
                type: InitEmpActionButtons.config.delEmp.attr("method"),
                url: InitEmpActionButtons.config.delEmp.attr("action"),
                data: $(obj).parent().serialize(),
                success: function (response) {
                    alert(response);
                    // logic, on success
                    console.log("redirect location = " + InitEmpActionButtons.config.redirectLocation + "?currentPage=" + currPage);

                    $.ajax({
                        url: InitEmpActionButtons.config.redirectLocation,
                        success: function (html) {
                            $("#ajax-content").empty().append(html);
                        }
                    });

                    //	$(".table").load(address + "?currentPage="+ currPage +" #tabb");
                    //	window.location.href = redirectLocation;
                    //	window.location.reload(true);
                    //	$(e.target).closest("tr").remove;
                }
            });
            return false;
        } else {
            return false;
        }
    },

    setAddButton: function () {
        console.log("set add button entered");

        $("#create-user").button().on("click", function () {
            InitEmpActionButtons.createAddDialog();
            console.log("opening ADD dialog");
            InitEmpActionButtons.config.dialog.dialog("open");
        });
    },

    setEditButton: function () {
        console.log("set edit button entered");

        $(".table").on("click", ".editempbtn", function (e) {		//oddelegowanie .editempbtn - za kazdym razem szuka w .table przycisku o tej klasie
            e.preventDefault();
            InitEmpActionButtons.createEditDialog();
            console.log("opening EDIT dialog");

            //1 tutaj zrobic ajax call z idekiem z edit buttona, aby uzyskac dane employee...
            //2 po uzyskaniu danych(on success) wrzucamy je do forma i otwieramy ten dialog

            var dataForm = $(this).parent().serialize();
            var editEmpAction = InitEmpActionButtons.config.editEmp.attr("action");
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


                    InitEmpActionButtons.config.dialog.dialog("open");
                }
            });
        });
    },

    setDeleteButton: function () {
        console.log("delete button entered");

        $(".table").on("click", ".delempbtn", function (e) {		// oddelegowanie .delempbtn - za kazdym razem szuka w .table przycisku o tej klasie
            e.preventDefault();
            InitEmpActionButtons.deleteUser(this);
        });
    }
};
