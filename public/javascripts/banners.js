$(document).ready(function(){
    const TAX = 1.08125;
    const PRICE_SQFT = 2.75;

  function toInches(feet, inches){
		return Math.round((parseInt(inches) / 12) + .42) + Math.round(parseInt(feet))
	}

  function calculatePrice(squareFeet, quantity){
  	return ((((squareFeet * PRICE_SQFT) * quantity) * TAX) + 15);
  }

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

    const SQUARE_FT = toInches(widthFt, widthIn) * toInches(heightFt, heightIn);
		var newPrice = calculatePrice(SQUARE_FT, QUANTITY);
    console.log(SQUAR_FT)
    console.log(((SQUARE_FT * PRICE_SQFT) * QUANTITY) * TAX);
   	$(".price").text(newPrice.toFixed(2));
  });
});