$(document).ready(function() {

        const DoubleSide = false;
        const Key = 0
        var Businesscards ={key, DoubleSide}

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

            Key = $(this).text();

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


