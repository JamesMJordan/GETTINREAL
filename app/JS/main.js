var main = function (jQuery) {
	console.log("cocks");
	$('.welcomeheader').hover(function() {
		$(this).toggleClass("spanshadow");
	});

	$('.welcomeheader').click(function() {
		$(this).addClass('hide');
		$('div.login').removeClass('hide')
	})

};
$(document).ready(main);