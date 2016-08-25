$(document).ready(function(){

 	$("input").keyup(function(){
 		var input = $(this).val();
    if(input == ''){
    	$(this).val('0');
    }

   	const heightIn = $("#heightin").val();
   	const widthIn = $("#widthin").val();
    const heightFt = $("#heightft").val();
    const widthFt = $("#widthft").val();
    const QUANTITY = $("#qty").val();

	var newPrice = $.ajax(jsRoutes.controllers.Pricing.bannerPricing(widthIn, widthFt, heightIn, heightFt, QUANTITY))
	                .done(function(data){
	                console.log(data);
	                })
	                .fail(function(data){
	                console.log(data);
	                });

   	$(".price").text(newPrice.toFixed(0) + ".00");
  });
});