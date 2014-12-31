InitReturnPrintReceipt = {
    init: function () {
        console.log("init function entered");

        InitReturnPrintReceipt.config = {	// tutaj jest ok, mamy utworzony obiekt w funkcji init, dlatego obiekty nie sa puste i mozemy sie do nich odwolywac
            form: $("#ok"),
            divReturnOrPrint: $("#returnOrPrint"),
            receiptAction: '/dvd/emp/return/printReceipt',
            registryId: $("#registryId").val()
        };

        this.setReturnButton();
    },

    setReturnButton: function () {
        console.log("return button set");

        InitReturnPrintReceipt.config.form.on("submit", function (e) {
            e.preventDefault();
            console.log("return-print action submit");
            var myForm = $(this);

            var clientId = myForm.find('input[name="clientId"]').val();
            var registryId = InitReturnPrintReceipt.config.registryId;

            var findAction = myForm.attr("action");
            var dataForm = myForm.serialize();

            console.log("clientId: " + clientId);
            console.log("registryId: " + registryId);

            // rent
            $.ajax({
                type: "POST",
                //contentType: "application/json; charset=utf-8",
                url: findAction,
                data: dataForm,
                dataType: "json",
                success: function (data) {
                    console.log("data: " + data);

                    if (data) { // is late
                        // print receipt, and delete Return form
                        var url = "/dvd/emp/return/printReceipt?clientid=" + clientId + "&registryid=" + registryId;
                        console.log("url: " + url);

                        var printForm = InitReturnPrintReceipt.createPrintButton(clientId, registryId);
                        var dif = InitReturnPrintReceipt.config.divReturnOrPrint;
                        dif.empty();
                        printForm.appendTo(dif);

                        //        window.open(url, "_blank");
                    } else {
                        //redirect without receipt
                        // similar behavior as an HTTP redirect
                        window.location.replace("/dvd/emp/clients/clientdetails?id=" + clientId);
                        //   window.location.href = "/dvd/emp/clients/clientdetails?id=" + clientId;
                    }
                }
            });
        });
    },

    createPrintButton: function (clientId, registryId) {
        var printForm = $("<form/>",
            {
                action: InitReturnPrintReceipt.config.receiptAction,
                method: 'post',
                target: '_blank'
            }
        );
        printForm.append(
            $("<input/>",
                {
                    type: 'hidden',
                    name: 'clientId',
                    value: clientId
                }
            )
        );
        printForm.append(
            $("<input/>",
                {
                    type: 'hidden',
                    name: 'registryId',
                    value: registryId
                }
            )
        );

        printForm.append('<input type="submit" value="Additional Fee" class="myButtonPink" />');

        return printForm;
    }
};