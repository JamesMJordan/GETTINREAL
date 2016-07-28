var main = function (jQuery) {
	$('a').click(function () {
		$('.active').removeClass().load(
			$(this).attr('href', function {
				$(this).removeClass('nonactive');
				$(this).addclasS('active');
		});
	});
}
$(document).ready(main);