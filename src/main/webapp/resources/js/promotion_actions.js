InitActionButtons = {
    init: function () {
        InitActionButtons.config = {	// tutaj jest ok, mamy utworzony obiekt w funkcji init, dlatego obiekty nie sa puste i mozemy sie do nich odwolywac
            dialog: $("#dialog-form"),
            form: $("#dialog-form").find("form"),
            createButton: $("#create-promotion"),
            deleteForm: $(".deletePromotion"),
            deleteButtonString: ".delPromotionBtn",
            editForm: $(".editPromotion"),
            editButtonString: ".editPromotionBtn",
            redirectLocation: $("#navigation_left").find("ul li.promotions a").attr('href'),
            id: $("#id"),
            name: $("#name"),
            value: $("#value"),
            promotionDaysNumber: $("#promotionDaysNumber"),
            emailRegex: /^[a-zA-Z0-9.!#$%&'*+\/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/,
            allFields: function () {
                var allo = $([]).add(this.name).add(this.value).add(this.promotionDaysNumber);
                return allo;
            },
            tips: $(".validateTips")
        };

        this.setAddButton();
        this.setEditButton();
        this.setDeleteButton();
    },

    createAddDialog: function () {
        var conf = InitActionButtons.config;
        var allFields = InitActionButtons.config.allFields();

        conf.dialog.dialog({
            title: "Create new promotion",
            autoOpen: false,
            height: 400,
            width: 350,
            modal: true,
            buttons: {
                "Create promotion": function () {
                    InitActionButtons.addAction();
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
            InitActionButtons.addAction();
        });
    },

    createEditDialog: function () {
        var conf = InitActionButtons.config;
        var allFields = InitActionButtons.config.allFields();

        conf.dialog.dialog({
            title: "Update promotion",
            autoOpen: false,
            height: 400,
            width: 350,
            modal: true,
            buttons: {
                "Update promotion": function () {
                    InitActionButtons.editAction();
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
            InitActionButtons.editAction();
        });
    },

    addAction: function () {
        console.log("addAction entered");

        var valid = true;
        var tips = InitActionButtons.config.tips;
        var name = InitActionButtons.config.name;
        var val = InitActionButtons.config.value;
        var promotionDaysNumber = InitActionButtons.config.promotionDaysNumber;

        InitActionButtons.config.allFields().removeClass("ui-state-error");

        valid = valid && DvdFormUtil.checkLength(tips, name, "name", 3, 16);
        valid = valid && DvdFormUtil.checkRegexp(tips, name, /^[a-z]([0-9a-z_\s])+$/i, "Name may consist of a-z, 0-9, underscores, spaces and must begin with a letter.");
        valid = valid && DvdFormUtil.checkRegexp(tips, val, /^[0-9]+(?:\.\d{1,2})?$/, "Value may consist of digits, and dot.");
        valid = valid && DvdFormUtil.checkRegexp(tips, promotionDaysNumber, /^[0-9]+$/, "Promotion Days Number may consist only of digits.");

        if (valid) {
            // ajax call -> controller get data -> save to db -> on success reload page
            console.log("valid. saving...");

            var datas = InitActionButtons.config.form.serialize();
            var urls = InitActionButtons.config.createButton.attr("value");

            console.log("data = " + datas);
            console.log("url = " + urls);

            $.ajax({
                type: "POST",
                url: urls,
                data: datas,
                success: function (data) {
                    // logic, on success
                    console.log("redirect location = " + InitActionButtons.config.redirectLocation + "?currentPage=" + currPage);

                    $.ajax
                    ({ url: InitActionButtons.config.redirectLocation,
                        success: function (html) {
                            $("#ajax-content").empty().append(html);
                        }
                    });
                }
            });
            InitActionButtons.config.dialog.dialog("close");
        }
        return valid;
    },

    editAction: function () {
        console.log("editAction entered");

        var valid = true;
        var tips = InitActionButtons.config.tips;
        var name = InitActionButtons.config.name;
        var val = InitActionButtons.config.value;
        var promotionDaysNumber = InitActionButtons.config.promotionDaysNumber;

        InitActionButtons.config.allFields().removeClass("ui-state-error");

        valid = valid && DvdFormUtil.checkLength(tips, name, "name", 3, 16);
        valid = valid && DvdFormUtil.checkRegexp(tips, name, /^[a-z]([0-9a-z_\s])+$/i, "Name may consist of a-z, 0-9, underscores, spaces and must begin with a letter.");
        valid = valid && DvdFormUtil.checkRegexp(tips, val, /^[0-9]+(?:\.\d{1,2})?$/, "Value may consist of digits, and dot.");
        valid = valid && DvdFormUtil.checkRegexp(tips, promotionDaysNumber, /^[0-9]+$/, "Promotion Days Number may consist only of digits.");

        if (valid) {
            //ajax call controller -> update user -> redirect on success
            console.log("valid. saving edited employee...");

            var datas = InitActionButtons.config.form.serialize();
            console.log("Edited data (sending) = " + datas);

            $.ajax({
                type: "POST",
                url: InitActionButtons.config.editForm.attr("action"),
                data: datas,
                success: function (data) {
                    // logic, on success
                    console.log("redirect location = " + InitActionButtons.config.redirectLocation + "?currentPage=" + currPage);

                    $.ajax({
                        url: InitActionButtons.config.redirectLocation,
                        success: function (html) {
                            $("#ajax-content").empty().append(html);
                        }
                    });
                }
            });
            InitActionButtons.config.dialog.dialog("close");
        }

        return valid;
    },

    deleteAction: function (obj) {
        var conBox = confirm("Are you sure ?");
        if (conBox) {
            $.ajax({
                type: InitActionButtons.config.deleteForm.attr("method"),
                url: InitActionButtons.config.deleteForm.attr("action"),
                data: $(obj).parent().serialize(),
                success: function (response) {
                    alert(response);
                    // logic, on success
                    console.log("redirect location = " + InitActionButtons.config.redirectLocation + "?currentPage=" + currPage);

                    $.ajax({
                        url: InitActionButtons.config.redirectLocation,
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

        InitActionButtons.config.createButton.button().on("click", function () {
            InitActionButtons.createAddDialog();
            console.log("opening ADD dialog");
            InitActionButtons.config.dialog.dialog("open");
        });
    },

    setEditButton: function () {
        console.log("set edit button entered");

        $(".table").on("click", InitActionButtons.config.editButtonString, function (e) {		//oddelegowanie .editButton - za kazdym razem szuka w .table przycisku o tej klasie
            e.preventDefault();
            InitActionButtons.createEditDialog();
            console.log("opening EDIT dialog");

            //1 tutaj zrobic ajax call z idekiem z edit buttona, aby uzyskac dane employee...
            //2 po uzyskaniu danych(on success) wrzucamy je do forma i otwieramy ten dialog

            var dataForm = $(this).parent().serialize();
            var editPromotionAction = InitActionButtons.config.editForm.attr("action");
            console.log("dataForm = " + dataForm);
            console.log("editPromotionAction = " + editPromotionAction);

            //get employee data by ID
            $.ajax
            ({
                type: "GET",
                //contentType: "application/json; charset=utf-8",
                url: editPromotionAction,
                data: dataForm,
                dataType: "json",
                success: function (data) {
                    console.log(data);

                    //teraz wrzucamy otrzymane dane do formularza... i go otwieramy...
                    InitActionButtons.config.id.val(data.id);
                    InitActionButtons.config.name.val(data.name);
                    InitActionButtons.config.value.val(data.value);
                    InitActionButtons.config.promotionDaysNumber.val(data.promotionDaysNumber);

                    InitActionButtons.config.dialog.dialog("open");
                }
            });
        });
    },

    setDeleteButton: function () {
        console.log("delete button entered");

        $(".table").on("click", InitActionButtons.config.deleteButtonString, function (e) {		// oddelegowanie .deleteButton - za kazdym razem szuka w .table przycisku o tej klasie
            e.preventDefault();
            InitActionButtons.deleteAction(this);
        });
    }
};
