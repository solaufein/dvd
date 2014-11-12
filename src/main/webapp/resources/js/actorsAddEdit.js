InitAutocompleteInput = {
    /*
     init: function (o) {                  // init function with parametrized object
     InitAutocompleteInput.config = o;

     this.setAutocompleteAction();
     },
     */

    x: 1,

    init: function () {
        InitAutocompleteInput.config = {
            autocompleteField: $("#myform").find(".findActorArea .searchActor input"), // search actor input
            form: $("#myform").find("#movie"),
            max_fields: 10, //maximum input boxes allowed
            wrapper: $("#myform").find(".findActorArea"), //find actor box wrapper
            searchActor: $("#myform").find(".findActorArea .searchActor"), //search actor wrapper
            actorSet: $("#actorset")
        };

        this.setAutocompleteAction();
        this.setRemoveAddedField();
        this.getActorSetData();
        this.appendDataBeforeSubmit();
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
                                id: item.id,    //access id as ui.item.id for example in select
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
                if (InitAutocompleteInput.x < InitAutocompleteInput.config.max_fields) { //max input box allowed
                    InitAutocompleteInput.x++; //text box increment
                    console.log("add x = " + InitAutocompleteInput.x);

                    var div = $('<div class="actorList"/>');
                    var remove = $('<a href="#" class="remove_field">Remove</a></div>');

                    var input = $('<input type="text" name="mytext[]"/>');
                    input.prop('disabled', true);
                    input.val(ui.item.value);

                    var hidden = $('<input type="hidden" name="mytext[]"/>');
                    hidden.val(ui.item.id);
                    console.log("this hidden val = " + hidden.val());

                    div.append(hidden).append(input).append(remove);

                    $(InitAutocompleteInput.config.wrapper).append(div);

                    // after select, clear input field
                    $(this).val('');
                    return false;
                }
            }
        });
    },

    setRemoveAddedField: function () {
        $(InitAutocompleteInput.config.wrapper).on("click", ".remove_field", function (e) { //user click on remove text
            e.preventDefault();
            $(this).parent('div').remove();
            InitAutocompleteInput.x--;
            console.log("odd x = " + InitAutocompleteInput.x);
        })
    },

    appendDataBeforeSubmit: function () {
        InitAutocompleteInput.config.form.submit(function (e) {
            //    e.preventDefault();
            console.log("appendDataBeforeSubmit entered");

            //iterate over hidden fields, add ID's to actorSet hidden imput, and submit form

            var actorList = InitAutocompleteInput.config.wrapper.find(".actorList");
            actorList.children('input[type=hidden]').each(function () {
                console.log("this val = " + this.value);

                $("<input />", {
                    name: 'actorset',
                    type: 'hidden',
                    value: this.value
                }).appendTo(InitAutocompleteInput.config.actorSet.parent());
            });

            InitAutocompleteInput.config.actorSet.remove();
        })
    },
    getActorSetData: function () {
        var obj = InitAutocompleteInput.config.actorSet.val();

        console.log("actor set val = " + obj);

        if (obj) {
            $.each($.parseJSON(obj), function (idx, obj) {
                console.log("this id val = " + obj.id);
                console.log("this id name = " + obj.name);

                // create input fields and hidden fields with id
            });
        }
    }
};
