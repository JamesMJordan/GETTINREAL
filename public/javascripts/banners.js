$(document).ready(function(){

    var Price = 0;

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

	$.ajax(jsRoutes.controllers.Pricing.bannerPricing(widthIn, widthFt, heightIn, heightFt, QUANTITY))
	                .done(function(data){
	                console.log(data);
	                Price = data.toString();
	                $(".price").text(Price);
	               	})
	                .fail(function(data){
	                console.log(data);
	                });

  });

});