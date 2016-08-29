$(document).ready(function() {

        var DoubleSide = false;
        var Qty = 0;

        $("#doublesided").click(function() {
                    if($('#doublesided').is(':checked')) {
                        DoubleSide = true;
                    } else {
                        DoubleSide = false;
                    }


            $.ajax(jsRoutes.controllers.Pricing.businesscardPricing(Qty, DoubleSide))
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

            Qty = $(this).val();

            $.ajax(jsRoutes.controllers.Pricing.businesscardPricing(Qty, DoubleSide))
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


