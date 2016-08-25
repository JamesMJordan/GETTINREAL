$(document).ready(function(){
  var Feet = jsRoutes.controllers.Pricing.addFeet
  var bannerPricing = jsRoutes.controllers.Pricing.bannerPricing

 	$("input").keyup(function(){
 		var input = $(this).val();
    if(input == ''){
    	$(this).val('0');
    }

   	const heightIn = $("#heightin").val();
   	const widthIn = $("#widthin").val();
    const heightFt = $("#heightft").val();
    const widthFt = $("#widthft").val();
    const QUANTITY = parseInt($("#qty").val());

    const SQUARE_FT = Feet(widthFt, widthIn) * Feet(heightFt, heightIn);
	console.log(SQUARE_FT)
	var newPrice = bannerPricing(SQUARE_FT, QUANTITY);

   	$(".price").text(newPrice.toFixed(0) + ".00");
  });
});