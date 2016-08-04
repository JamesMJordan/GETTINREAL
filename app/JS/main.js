var main = function (jQuery) {
	console.log("cocks");
	$('.welcomeheader').hover(function() {
		$(this).toggleClass("spanshadow");
	});

	$('.welcomeheader').click(function() {
		$(this).addClass('hide');
		$('div.login').removeClass('hide')
	})
function price () {
    var heightIn = function() {
        if (($("#heightin").val() / 12) is decimal) round up
        }

    var widthIn = function()  {
        if (($("widthin").val() / 12) is decimal) round up
        }

    var heightFt = function() {
        ($("#heightft").val() + heightIn);
        }

    var widthFt = function() {
        ($("#widthft").val() = widthIn);
        }

    var squareFt = function() {
        (heightFt * widthFt);
        }

    var qty = $("#qty").val();

    $(".price").text(function () {
            (((squareFt * 2.75) * qty) * .083)
        });
    }

};
$(document).ready(main);