InitHomeFields = {
    init: function () {
        InitHomeFields.config = {	// tutaj jest ok, mamy utworzony obiekt w funkcji init, dlatego obiekty nie sa puste i mozemy sie do nich odwolywac
            form: $("#myform").find(".inputs"),
            allFields: function () {
                var allo = $([]).add(this.name).add(this.value).add(this.promotionDaysNumber);
                return allo;
            },
            tips: $(".validateTips")
        };

        this.setValidation();
    },

    setValidation: function () {
        InitHomeFields.config.form.find("#rentClientLastname").on("submit", function (e) {
            console.log("rentClientLastname clicked");
            return InitHomeFields.validateName(this);
        });
        InitHomeFields.config.form.find("#rentClientPesel").on("submit", function (e) {
            console.log("rentClientPesel clicked");
            return InitHomeFields.validatePesel(this);
        });

        InitHomeFields.config.form.find("#findClientLastname").on("submit", function (e) {
            console.log("findClientLastname clicked");
            return InitHomeFields.validateName(this);
        });
        InitHomeFields.config.form.find("#findClientPesel").on("submit", function (e) {
            //   e.preventDefault();
            var s = InitHomeFields.validatePesel(this);
            console.log("findClientPesel clicked, val = " + s);

            return s;
        });
    },

    validatePesel: function (obj) {
        var valid = true;
        var input = $(obj).find('input[type=text]');

        valid = valid && DvdFormUtil.simpleCheckPesel(input);

        return valid;
    },

    validateName: function (obj) {
        var valid = true;
        var input = $(obj).find('input[type=text]');

        valid = valid && DvdFormUtil.simpleCheckRegexp(input, /^[a-zA-Z' ]+$/);

        return valid;
    }
};