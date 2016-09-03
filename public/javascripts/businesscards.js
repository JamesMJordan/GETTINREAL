$(document).ready(function() {

        var DoubleSide = false;
        var Key = 0;
        var Businesscards = {Key, DoubleSide}

        $("#doublesided").click(function() {
                    if($('#doublesided').is(':checked')) {
                        DoubleSide = true;
                    } else {
                        DoubleSide = false;
                    }

            $.ajax(jsRoutes.controllers.Messages.pricing(Businesscards))
                                .done(function(data){
                                console.log(data);
                                Price = data.toString();
                                $("#price").text(Price);
                                })
                                .fail(function(data){
                                console.log(data);
                                });
         });

        $('.quantity').change(function() {

            Key = $(this).val();

            $.ajax(jsRoutes.controllers.Messages.pricing(Businesscards))
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


