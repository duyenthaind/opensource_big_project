function addCart(id,quantity){
	$.ajax({
		url : "/user-carts/carts",
		type:"POST",
		data : JSON.stringify({
			productId : id,
			quantity : quantity,
		}),
		dataType: 'json',
        contentType: 'application/json',
		success: function(responseData){
			setDialog("Thêm thành công");
			updateTotal();
		},
		error : function(jqXhr, textStatus, errorMessage) { // error
			// callback

		}
	});
}

function updateTotal(){
	$.ajax({
		url : "/user-carts/carts",
		type:"GET",
		dataType: 'json',
        contentType: 'application/json',
		success: function(responseData){
			$("#productTotal").html(responseData.result.data.length)
		},
		error : function(jqXhr, textStatus, errorMessage) { // error
			// callback

		}
	});
}

function shopCart(){
	window.location.href = "/shoping-cart";
	loadCart()
}

function loadCart(){
	$("#tableCart").empty();
	$.ajax({
		url : "/user-carts/carts",
		type:"GET",
		dataType: 'json',
        contentType: 'application/json',
		success: function(responseData){
			var html = "";
			var data = responseData.result.data;
			for(var i=0;i<data.length;i++){
				html += '<tr>'
                                 +'   <td class="shoping__cart__item">'
                                 +'       <img src="/uploads/'+ data[i].avatar +'" alt="">'
                                 +'       <h5>'+ data[i].name +'</h5>'
                                 +'   </td>'
                                 +'   <td class="shoping__cart__price">'
                                  +'      $'+ data[i].price +''
                                 +'   </td>'
                                 +'   <td class="shoping__cart__quantity">'
                                  +'      <div class="quantity">'
                                  +'          <div class="pro-qty">'
                                  +'              <input type="text" value="'+ data[i].quantity +'">'
                                  +'          </div>'
                                  +'      </div>'
                                   +' </td>'
                                  +'  <td class="shoping__cart__total">'
                                   +'     $'+ data[i].price*data[i].quantity +''
                                   +' </td>'
                                   +' <td class="shoping__cart__item__close">'
                                   +'     <span class="icon_close"></span>'
                                   +' </td></tr>';
			}
			$("#tableCart").append(html);
		},
		error : function(jqXhr, textStatus, errorMessage) { // error
			// callback

		}
	});
}

(function () {
	updateTotal()
	loadCart()// I will invoke myself
})();


function setDialog(message){
	$("#dialogModal").modal("show");
	$("#dialogTitle").empty();
	$("#dialogTitle").append("<p>\'" + message +  "\'</p>")
}