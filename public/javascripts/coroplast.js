$(document).ready(function() {

        var DoubleSided = false;
        const Key = 6;
        const heightIn = 0
        const widthIn = 0
        const heightFt = 0
        const widthFt = 0
        var Quantity = 1

        $("#doublesided").click(function() {
                    if($('#doublesided').is(':checked')) {
                        DoubleSided = true;
                    } else {
                        DoubleSided = false;
                    }

                    var Coroplast = {Key, DoubleSided, Quantity, widthIn, widthFt, heightIn, heightFt}
                    console.log(Coroplast);

            $.ajax(jsRoutes.controllers.Messages.pricing(JSON.stringify(Coroplast)))
                                .done(function(data){
                                console.log(data);
                                Price = data.toString();
                                $("#price").text(Price);
                                })
                                .fail(function(data){
                                console.log(data);
                                });
         });

        $("#quantity").keyup(function(){
                    Quanitity = $(this).val();
                        if(Quantity == ''){
                           $(this).val('0');
                        }

            var Coroplast = {Key, DoubleSided, Quantity, widthIn, widthFt, heightIn, heightFt}
            console.log(Coroplast);

            $.ajax(jsRoutes.controllers.Messages.pricing(JSON.stringify(Coroplast)))
                    .done(function(data){
                    console.log(data);
                    Price = data.toString();
                    $("#price").text(Price);
                    })
                    .fail(function(data){
                    console.log(data);
                    });
        });

});


