$(document).ready(function() {

        var DoubleSided = false;
        var Key = 0;
        const heightIn = 0
        const widthIn = 0
        const heightFt = 0
        const widthFt = 0
        const Quantity = 1



        $("#doublesided").click(function() {
                    if($('#doublesided').is(':checked')) {
                        DoubleSided = true;
                    } else {
                        DoubleSided = false;
                    }
             var Businesscards = {Key, DoubleSided, Quantity, widthIn, widthFt, heightIn, heightFt}
             console.log(JSON.stringify(Businesscards));

            $.ajax(jsRoutes.controllers.Messages.pricing(JSON.stringify(Businesscards)))
                                .done(function(data){
                                console.log(data);
                                Price = data.toString();
                                $("#price").text(Price);
                                })
                                .fail(function(data){
                                console.log(data);
                                });
         });

        $('#businesscardsqty').change(function() {

            var key = $(this).val();

            Key = parseInt(key)

            var Businesscards = {Key, DoubleSided, Quantity, widthIn, widthFt, heightIn, heightFt}
            console.log(JSON.stringify(Businesscards));

            $.ajax(jsRoutes.controllers.Messages.pricing(JSON.stringify(Businesscards)))
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


