$(document).ready(function() {
    const TAX = 1.08125;
    const DOUBLESIDED = 1.25;

    function doublesidedprice(quantity) {
        return (quantity * DOUBLESIDED * TAX);
    }

    function calculatedprice(quantity) {
        return (quantity * TAX);
    }

    function check)

        $('.quantity').change(function() {

          var QUANTITY = parseFloat($(this).find("option:selected").data("price"));
          var newPrice = calculatedprice(QUANTITY);

          if ($('#doublesided').is('checked')) {
                var newPrice = doublesidedprice(QUANTITY);
                }
                $('#price').text(doublesidedPrice.toFixed(0));
                }
        });

        $('#doublesided').click(function() {

             var QUANTITY = parseFloat($('.quantity').find("option:selected").data("price"));
             var newPrice = calculatedprice(QUANTITY);
             var doublesidedPrice = doublesidedprice(QUANTITY);

             $('#price').text(doublesidedPrice.toFixed(0) + ".00");
        });
});


