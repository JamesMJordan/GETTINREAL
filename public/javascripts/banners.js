$(document).ready(function(){

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

	var newPrice = $.ajax(jsRoutes.controllers.Pricing.bannerPricing(widthIn, widthFt, heightIn, heightFt, QUANTITY))
	                .done(function(data){
	                console.log(data);
	                })
	                .fail(function(data){
	                console.log(data);
	                });

   	$(".price").text(newPrice);
  });
});