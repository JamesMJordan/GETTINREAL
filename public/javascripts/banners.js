$(document).ready(function(){

    const Key = 1;
    var DoubleSided = false;

 	$("input").keyup(function(){
     		var input = $(this).val();
        if(input == ''){
        	$(this).val('0');
        }

        const heightIn = parseInt($("#heightin").val());
       	const widthIn = parseInt($("#widthin").val());
        const heightFt = parseInt($("#heightft").val());
        const widthFt = parseInt($("#widthft").val());
        const Quantity = parseInt($("#qty").val());

        var Banner = {Key, DoubleSided, Quantity, widthIn, widthFt, heightIn, heightFt};

        console.log(JSON.stringify(Banner))

       $.ajax(jsRoutes.controllers.Messages.pricing(JSON.stringify(Banner)))
                .done(function(data){
                console.log(data);
                var Price = data
                $('.price').text(data);

                })
                .fail(function(data){
                console.log(data)
                });
             });

  });
