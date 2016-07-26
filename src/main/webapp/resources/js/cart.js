function addToCart(phoneId){
  $.ajax({
      async:false,
      url:'/cart/addToCart',
      dataType:"text",
      data: ({phoneId: phoneId}),
      success: function(data) {
          $.ajax({
              url: '/cart/getTotalSum',
              dataType: "text",
              success: function (data) {
                  $('#simpleCart_total').text(data + " rub")
              }
          });
          $.ajax({
              url: '/cart/getQuantityPhonesInCart',
              dataType: "text",
              success: function (data) {
                  $('#simpleCart_quantity').text(data)
              }
          });
          $('.content').text(data);

          var id = $('#dialog');
          var maskHeight = $(document).height();
          var maskWidth = $(window).width();
          $('#mask').css({'width': maskWidth, 'height': maskHeight});
          $('#mask').fadeIn(1000);
          $('#mask').fadeTo("slow", 0.8);
          var winH = $(window).height();
          var winW = $(window).width();
          $(id).css('top', winH / 2 - $(id).height() / 2);
          $(id).css('left', winW / 2 - $(id).width() / 2);
          $(id).fadeIn(2000);
      }
});
}

function updateCart(){
    $.ajax({
        url:'/cart/getTotalSum',
        dataType:"text",
        success: function(data){
            $('#simpleCart_total').text(data + " rub")
        }
    });
    $.ajax({
        url:'/cart/getQuantityPhonesInCart',
        dataType:"text",
        success: function(data){
            $('#simpleCart_quantity').text(data)
        }
    });
}

function clearCart(){
    $.ajax({
        async:false,
        url:'/cart/clearCart',
        success: function(){
            $.ajax({
                url:'/cart/getTotalSum',
                dataType:"text",
                success: function(data){
                    $('#simpleCart_total').text(data + " rub")
                }
            });
            $.ajax({
                url:'/cart/getQuantityPhonesInCart',
                dataType:"text",
                success: function(data){
                    $('#simpleCart_quantity').text(data)
                }
            });
        }
    });
}

function removeOrderLine(phoneId){
    $.ajax({
        async:false,
        url:'/cart/removeOrderLine',
        data: ({phoneId: phoneId}),
        success: function(){
            updateCart();
            document.getElementById("cart_item_" + phoneId).remove();
            $.ajax({
                url:'/cart/getTotalSum',
                dataType:"text",
                success: function(data){
                    $('#create_order').text("Create order (Total: " + data + " rub)")
                }
            });
        }
    });
}

function changeQuantity(phoneId, position){
    var newQuantity = document.getElementById("quantity_" + phoneId).value;
        $.ajax({
        url:'/cart/changeQuantity',
        data: ({phoneId: phoneId, newQuantity: newQuantity}),
        success: function(data) {
            updateCart();
            $.ajax({
                url:'/cart/getTotalSum',
                dataType:"text",
                success: function(data){
                    $('#create_order').text("Create order (Total: " + data + " rub)")
                }
            });
            document.getElementById("price_" + phoneId).innerHTML = data + " rub";
            //$('#price' + position).text(data)}
        }
    });
}

function totalSum(){
    $.ajax({
        url:'/cart/getTotalSum',
        dataType:"text",
        success: function(data){
            $('#create_order').text("Create order (Total: " + data + " rub)")
        }
    });
}

function createOrder(){
    document.getElementById('box').style.display='block';
    //$('#box').style.display='block';
}

function addAddress(data){
    if (data.value == 'self')
        document.getElementById('address').style.display='none';
    else
        document.getElementById('address').style.display='block';

}
function showPopup(){
    var id = $('#dialog');
    var maskHeight = $(document).height();
    var maskWidth = $(window).width();
    $('#mask').css({'width': maskWidth, 'height': maskHeight});
    $('#mask').fadeIn(1000);
    $('#mask').fadeTo("slow", 0.8);
    var winH = $(window).height();
    var winW = $(window).width();
    $(id).css('top', winH / 2 - $(id).height() / 2);
    $(id).css('left', winW / 2 - $(id).width() / 2);
    $(id).fadeIn(2000);
}
$(window).load(updateCart());
$(document).ready(function() {
$('.window .close').click(function (e) {
    e.preventDefault();
    $('#mask, .window').hide();
});
$('#mask').click(function () {
    $(this).hide();
    $('.window').hide();
});
});