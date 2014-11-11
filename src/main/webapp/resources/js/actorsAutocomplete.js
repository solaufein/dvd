InitAutocompleteInput = {
    /*
     init: function (o) {                  // init function with parametrized object
     InitAutocompleteInput.config = o;

     this.setAutocompleteAction();
     },
     */

    init: function () {
        InitAutocompleteInput.config = {	// tutaj jest ok, mamy utworzony obiekt w funkcji init, dlatego obiekty nie sa puste i mozemy sie do nich odwolywac
            autocompleteField: $("#actor"),
            form: $("#myform").find("form")
        };

        this.setAutocompleteAction();
    },

    setAutocompleteAction: function () {

        var url = InitAutocompleteInput.config.form.attr("action") + "/getTags";
        console.log("url = " + url);

        InitAutocompleteInput.config.autocompleteField.autocomplete({
            source: function (request, response) {
                $.ajax({
                    url: url,
                    dataType: "json",
                    data: {
                        term: request.term
                    },
                    success: function (data) {
                        response($.map(data, function (item) {
                            console.log("item id = " + item.id);
                            console.log("item tag = " + item.tag);

                            return {
                                id: item.id,    //access as ui.item.id for example in select
                                label: item.tag,
                                value: item.tag
                                /* edits */
                                //  fathersLast: item.FathersLast, //access as ui.item.fathersLast
                                //  mothersLast: item.MothersLast, //access as ui.item.mothersLast, etc [...]
                                //[...]
                            }
                        }));
                    },
                    open: function () {
                        $(this).removeClass("ui-corner-all").addClass("ui-corner-top");
                    },
                    close: function () {
                        $(this).removeClass("ui-corner-top").addClass("ui-corner-all");
                    }
                });
            },
            minLength: 2,
            select: function (event, ui) {
                console.log("id = " + ui.item.id);
                console.log("value = " + ui.item.value);
                //   InitAutocompleteInput.config.autocompleteField.val(ui.item.label);
            }
        });
    }
};
