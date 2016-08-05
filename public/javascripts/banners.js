var banners = function (jQuery) {

    $("input").keyup(function() {
        var heightIn = $("#heightin").val(),
            widthIn = $("#widthin").val(),
            heightFt = $("#heightft").val() + Math.round(heightIn + .5),
            widthFt = $("#widthft").val() + Math.round(widthIn + .5),
            squareFt = heightFt * widthFt,
            tax = .08125,
            pricesqft = 2.75,
            qty = $("#qty").val();

        var newPrice = (squareFt * pricesqft) * qty * tax;



            console.log(((squareFt * pricesqft) * qty) * tax);

        $(".price").text(newPrice.toFixed(2));



    });
};
$(document).ready(banners);