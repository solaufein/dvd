InitRentMovie = {
    init: function () {
        console.log("init function entered");

        InitRentMovie.config = {	// tutaj jest ok, mamy utworzony obiekt w funkcji init, dlatego obiekty nie sa puste i mozemy sie do nich odwolywac
            dialog: $("#dialog-form"),
            form: $("#dialog-form").find("#findClients"),
            table: $("#dialog-form").find(".table"),
            rentAction: $(".table .rentMovieCopy").attr('action'),
            pesel: $("#pesel"),
            tips: $("#dialog-form").find(".validateTips"),
            movieCopyId: ""
        };

        this.setRentButton();
    },

    createRentDialog: function () {
        var conf = InitRentMovie.config;

        conf.dialog.dialog({
            title: "Select Client",
            autoOpen: false,
            height: 400,
            width: 400,
            modal: true,
            buttons: {
                Cancel: function () {
                    conf.dialog.dialog("close");
                }
            },
            close: function () {
                conf.tips.text("11 digit pesel required.");
                conf.form[ 0 ].reset();
                conf.pesel.removeClass("ui-state-error");
                InitRentMovie.config.table.empty();
            }
        });

        this.findClientsAction();
    },

    findClientsAction: function () {
        console.log("find clients action set");
        InitRentMovie.config.form.on("submit", function (e) {
            e.preventDefault();
            console.log("find clients action submit");

            var valid = true;
            var tips = InitRentMovie.config.tips;
            var pesel = InitRentMovie.config.pesel;

            InitRentMovie.config.pesel.removeClass("ui-state-error");
            InitRentMovie.config.table.empty();

            valid = valid && DvdFormUtil.checkPesel(tips, pesel);

            if (valid) {
                var form = $(this);
                var dataForm = form.serialize();
                var findAction = form.attr("action");
                console.log("dataForm = " + dataForm);
                console.log("Find action url = " + findAction);
                console.log("Rent action url = " + InitRentMovie.config.rentAction);

                $.ajax({
                    type: "GET",
                    //contentType: "application/json; charset=utf-8",
                    url: findAction,
                    data: dataForm,
                    dataType: "json",
                    success: function (data) {
                        console.log(data);

                        //teraz tworzymy tabele z danymi (client_id, movie_copy_id) oraz buttonem submit
                        // po wyslaniu forward POST do controllera rentController

                        var chooseForm = $("<form/>",
                            {
                                action: InitRentMovie.config.rentAction,
                                method: 'post'
                            }
                        );
                        chooseForm.append(
                            $("<input/>",
                                {
                                    type: 'hidden',
                                    name: 'clientId',
                                    value: data.id
                                }
                            )
                        );
                        chooseForm.append(
                            $("<input/>",
                                {
                                    type: 'hidden',
                                    name: 'movieCopyId',
                                    value: InitRentMovie.config.movieCopyId
                                }
                            )
                        );
                        chooseForm.append('<input type="submit" value="Choose" class="myButton" />');

                        var table = $('<table></table>').addClass('foo');
                        var heedrow = "<tr><td>" + "Firstname" + "</td><td>" + "Lastname" + "</td><td>" + "Pesel" + "</td><td>" + "" + "</td></tr>";
                        var htmlrow = "<tr><td>" + data.firstName + "</td><td>" + data.lastName + "</td><td>" + data.pesel + "</td><td class='toinsert'></td></tr>";
                        table.append(heedrow).append(htmlrow);
                        chooseForm.appendTo(table.find(".toinsert"));

                        InitRentMovie.config.table.append(table);
                    }
                });
            }
            return valid;
        })
    },

    setRentButton: function () {
        console.log("rent button set");
        this.createRentDialog();
        //    this.findClientsAction();

        $(".rentMovieCopy").on("click", function (e) {
            e.preventDefault();
            //    InitRentMovie.createRentDialog();

            var movieCopyId = $(this).find('input[name="moviecopyid"]').val();
            InitRentMovie.config.movieCopyId = movieCopyId;     // set movieCopyId

            console.log("opening Rent dialog, movie copy id = " + movieCopyId);
            InitRentMovie.config.dialog.dialog("open");
        });
    }
};