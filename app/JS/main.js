var main = function (jQuery) {
	console.log("cocks");
	$('.welcomeheader').hover(function() {
		$(this).toggleClass("spanshadow");
	});

	$('.welcomeheader').click(function() {
		$(this).addClass('hide');
		$('div.login').removeClass('hide')
	})

    function price() {
        var heightIn = $("#heightin").val();
            widthIn = $("#widthin").val();
            heightFt = $("#heightft").val() + Math.round(heightIn + .5);
            widthFt = $("#widthft").val() Math.round(widthIn + .5);
            squareFt = heightFt * widthFt
            qty = $("#qty").val();

        $(".price").text(function () {
                (((squareFt * 2.75) * qty) * .08125)
            });
        }

};
$(document).ready(main);