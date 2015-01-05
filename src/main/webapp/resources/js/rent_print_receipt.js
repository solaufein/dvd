InitRentPrintReceipt = {
    init: function () {
        console.log("init function entered");

        InitRentPrintReceipt.config = {	// tutaj jest ok, mamy utworzony obiekt w funkcji init, dlatego obiekty nie sa puste i mozemy sie do nich odwolywac
            form: $("#ok"),
            divRentOrPrint: $("#rentOrPrint"),
            receiptAction: '/dvd/emp/rent/printReceipt'
        };

        this.setRentButton();
    },

    setRentButton: function () {
        console.log("rent button set");

        InitRentPrintReceipt.config.form.on("submit", function (e) {
            e.preventDefault();
            console.log("rent-print action submit");
            var myForm = $(this);

            var movieCopyId = myForm.find('input[name="movieCopyId"]').val();
            var clientId = myForm.find('input[name="clientId"]').val();
            var promotionDaysNumber = myForm.find('input[name="promotionDaysNumber"]').val();
            var price = myForm.find('input[name="price"]').val();

            var findAction = myForm.attr("action");
            var dataForm = myForm.serialize();

            console.log("movieCopyId: " + movieCopyId);
            console.log("clientId: " + clientId);
            console.log("promotionDaysNumber: " + promotionDaysNumber);
            console.log("price: " + price);

            //  var url = "/dvd/emp/clients/clientdetails?id=" + clientId;
            //       var newWindow = window.open("","_blank");
            //       this.submit();
            //           newWindow.location.href = url;


            // rent
            $.ajax({
                type: "POST",
                //contentType: "application/json; charset=utf-8",
                url: findAction,
                data: dataForm,
                dataType: "json",
                success: function (data) {
                    console.log("data: " + data);

                    if (data != -1) {   // movie copy available
                        var printForm = InitRentPrintReceipt.createPrintButton(clientId, data);
                        var dif = InitRentPrintReceipt.config.divRentOrPrint;
                        dif.empty();
                        printForm.appendTo(dif);
                    } else {    // movie copy NOT available
                        InitRentPrintReceipt.config.divRentOrPrint.empty();
                        alert("This movie copy is no longer available");
                    }
                }
            });

        });
    },

    createPrintButton: function (clientId, registryId) {
        var printForm = $("<form/>",
            {
                action: InitRentPrintReceipt.config.receiptAction,
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
        printForm.append('<input type="submit" value="Print Receipt" class="myButton" />');

        return printForm;
    }
};