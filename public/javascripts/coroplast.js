$(document).ready(function() {

        var DoubleSide = false;
        var Qty = 0;

        $("#doublesided").click(function() {
                    if($('#doublesided').is(':checked')) {
                        DoubleSide = true;
                    } else {
                        DoubleSide = false;
                    }

            $.ajax(jsRoutes.controllers.Pricing.coroplastPricing(Qty, DoubleSide))
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
                    Qty = $(this).val();
                        if(Qty == ''){
                           $(this).val('0');
                        }


            $.ajax(jsRoutes.controllers.Pricing.coroplastPricing(Qty, DoubleSide))
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


