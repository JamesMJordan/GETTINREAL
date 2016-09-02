$(document).ready(function(){

    const key = 1;

 	$("input").keyup(function(){
     		var input = $(this).val();
        if(input == ''){
        	$(this).val('0');
        }

        const heightIn = parseInt($("#heightin").val());
       	const widthIn = parseInt($("#widthin").val());
        const heightFt = parseInt($("#heightft").val());
        const widthFt = parseInt($("#widthft").val());
        const QUANTITY = parseInt($("#qty").val());

        var Measurements = {key, widthIn, widthFt, heightIn, heightFt, QUANTITY};

        console.log(JSON.stringify(Measurements))

       $.ajax(jsRoutes.controllers.Pricing.bannerPricing2(JSON.stringify(Measurements)))
                .done(function(data){
                console.log(data);
                })
                .fail(function(data){
                console.log(data)
                });

//	$.ajax(jsRoutes.controllers.Pricing.bannerPricing(JSON.stringify(Measurements)))
//	                .done(function(data){
//	                console.log(data);
//	                Price = data.toString();
//	                $(".price").text(Price);
//	               	})
//	                .fail(function(data){
//	                console.log(data);
//	                });

  });

});