function addCustomCart(id,quantity){
	var qty = quantity;
	var newQty = parseInt($("#customQuantity").val());
	if(newQty > 1){
		qty = newQty;
	}
	$.ajax({
		url : "/user-carts/carts",
		type:"POST",
		data : JSON.stringify({
			productId : id,
			quantity : qty,
		}),
		dataType: 'json',
        contentType: 'application/json',
		success: function(responseData){
			setDialog("Add item to cart success");
			updateTotal();
		},
		error : function(jqXhr, textStatus, errorMessage) { // error
			// callback
			var contentType = jqXhr.getResponseHeader("Content-Type");
		    if (jqXhr.status == 200 && contentType.toLowerCase().indexOf("text/html") >= 0) {
		        // assume that our login has expired - reload our current page
		        window.location.href = "/login";
		    }
		}
	});
}

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
			setDialog("Add item to cart success");
			updateTotal();
		},
		error : function(jqXhr, textStatus, errorMessage) { // error
			// callback
			var contentType = jqXhr.getResponseHeader("Content-Type");
		    if (jqXhr.status == 200 && contentType.toLowerCase().indexOf("text/html") >= 0) {
		        // assume that our login has expired - reload our current page
		        window.location.href = "/login";
		    }
		}
	});
}

function likeCart(id){
	$.ajax({
		url : "/liked/v1/api/liked?productId="+id,
		type:"POST",
		dataType: 'json',
        contentType: 'application/json',
		success: function(responseData){
			setDialog("Add item to liked success");
			updateTotalLiked();
			if(responseData.status == 208){
				setDialog(responseData.message);
			}
		},
		error : function(jqXhr, textStatus, errorMessage) { // error
			// callback
			var contentType = jqXhr.getResponseHeader("Content-Type");
		    if (jqXhr.status == 200 && contentType.toLowerCase().indexOf("text/html") >= 0) {
		        // assume that our login has expired - reload our current page
		        window.location.href = "/login";
		    }
		}
	});
}

function updateTotalLiked(){
	$.ajax({
		url : "/liked/v1/api/likeds",
		type:"GET",
		dataType: 'json',
        contentType: 'application/json',
		success: function(responseData){
			$("#likeTotal").html(responseData.result.data.length)
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

function likedCart(){
	window.location.href = "/liked";
	loadLiked();
}

function loadLiked(){
	$("#tableLiked").empty();
	$.ajax({
		url : "/liked/v1/api/likeds",
		type:"GET",
		dataType: 'json',
        contentType: 'application/json',
		success: function(responseData){
			var html = "";
			var data = responseData.result.data;
			for(var i=0;i<data.length;i++){
				html += '<tr class="product_tr"><td><input type="hidden" value="'+data[i].productId+'" class="cartLikedId"/></td>'
                                 +'   <td class="shoping__cart__item">'
                                 +'       <img width="250px" height="100px" src="/uploads/'+ data[i].avatar +'" alt="">'
                                 +'       <a style="cursor:pointer" href="/shop-details?productId='+data[i].productId+'"><h5>'+ data[i].name +'</h5></a>'
                                 +'   </td>'
                                 +'   <td class="shoping__cart__price">'
                                  +'      '+ data[i].price +''
                                 +'   </td>'
                                   +' <td class="shoping__cart__item__close">'
                                   +'     <span class="icon_close remove_cart remove_like" onclick="removeLike('+data[i].productId+',this)"></span>'
                                   +' </td></tr>';
			}
			$("#tableLiked").append(html);
			var index = 0;
			var removeCart = $(".remove_like").on("click",function(){
				index = removeCart.index(this);
				var productId = document.getElementsByClassName("cartLikedId")[index].value;
				$(".product_tr")[index].remove();
				removeLike(productId);
				updateTotalLiked();
			});
		},
		error : function(jqXhr, textStatus, errorMessage) { // error
			// callback

		}
	});
}

function removeLike(productId){
	var id = parseInt(productId);
	$.ajax({
		url : "/liked/v1/api/likeds?productId="+id,
		type:"DELETE",
		dataType: 'json',
        contentType: 'application/json',
		success: function(responseData){
			console.log(responseData);
		},
		error : function(jqXhr, textStatus, errorMessage) { // error
			// callback

		}
	});
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
				html += '<tr class="product_tr"><td><input type="hidden" value="'+data[i].productId+'" class="cartProductId"/></td>'
                                 +'   <td class="shoping__cart__item">'
                                 +'       <img width="250px" height="100px" src="/uploads/'+ data[i].avatar +'" alt="">'
                                 +'       <a style="cursor:pointer" href="/shop-details?productId='+data[i].productId+'"><h5>'+ data[i].name +'</h5></a>'
                                 +'   </td>'
                                 +'   <td class="shoping__cart__price">'
                                  +'      '+ data[i].price +''
                                 +'   </td>'
                                 +'   <td class="shoping__cart__quantity">'
                                  +'      <div class="quantity">'
                                  +'          <div class="pro-qty">'
                                  +' <span class="dec qtybtn decrease">-</span>'
                                  +'              <input class="qty" type="text" value="'+ data[i].quantity +'">'
                                  +'  <span class="inc qtybtn increase">+</span>'
                                  +'          </div>'
                                  +'      </div>'
                                   +' </td>'
                                  +'  <td class="shoping__cart__total totalOneProduct">'
                                   +'     '+ data[i].price*data[i].quantity +''
                                   +' </td>'
                                   +' <td class="shoping__cart__item__close">'
                                   +'     <span class="icon_close remove_cart"></span>'
                                   +' </td></tr>';
			}
			$("#tableCart").html(html);
			cartTotal();
			var index = 0;
			var proQty = $('.pro-qty');
			
			var removeCart = $(".remove_cart").on("click",function(){
				index = removeCart.index(this);
				var productId = document.getElementsByClassName("cartProductId")[index].value;
				$(".product_tr")[index].remove();
				remove(productId);
				cartTotal();
				cartTotal();
			});
			
			var decrease = $(".decrease").on("click",function(){
				
				index = decrease.index(this);
				var $qty=$(this).parent().find('input');
		        var currentVal = parseInt($qty.val());
		        var newVal = 0;
		        if (!isNaN(currentVal) && currentVal > 1) {
		            $qty.val(currentVal - 1);
		            newVal = $qty.val();
		           
	            	document.getElementsByClassName("totalOneProduct")[index].innerHTML=newVal*parseInt(document.getElementsByClassName("shoping__cart__price")[index].innerHTML);
	            	cartTotal()
	            	updateCart(document.getElementsByClassName("cartProductId")[index].value,newVal);
		        }
			});
			
			var increase = $('.increase').on('click',function(){
				
				index = increase.index(this);
				var $qty=$(this).parent().find('input');
		        var currentVal = parseInt($qty.val());
		        var newVal = 0;
		        if (!isNaN(currentVal) && currentVal > 0) {
		            $qty.val(currentVal + 1);
		            newVal = $qty.val();
		           	document.getElementsByClassName("totalOneProduct")[index].innerHTML=newVal*parseInt(document.getElementsByClassName("shoping__cart__price")[index].innerHTML);
		           	cartTotal();
		           	updateCart(document.getElementsByClassName("cartProductId")[index].value,newVal);
		        }
		    });
		},
		error : function(jqXhr, textStatus, errorMessage) { // error
			// callback

		}
	});
}

function remove(productId){
	var id = parseInt(productId);
	$.ajax({
		url : "/user-carts/carts?productId="+id,
		type:"DELETE",
		dataType: 'json',
        contentType: 'application/json',
		success: function(responseData){
			loadCart();
		},
		error : function(jqXhr, textStatus, errorMessage) { // error
			// callback

		}
	});
}

function cartTotal(){
	var total = 0;
	var totalProduct = document.getElementsByClassName("totalOneProduct");
	for(var i=0;i<totalProduct.length;i++){
		total += parseInt(totalProduct[i].innerHTML);
	}
	$("#totalCart").html("$"+total);
}

function updateCart(productId,quantity){
	var id = parseInt(productId);
	$.ajax({
		url : "/user-carts/carts",
		type:"PUT",
		data : JSON.stringify({
			productId : id,
			quantity : quantity,
		}),
		dataType: 'json',
        contentType: 'application/json',
		success: function(responseData){
			
		},
		error : function(jqXhr, textStatus, errorMessage) { // error
			// callback

		}
	});
}

(function () {
	updateTotal()
	loadCart();
	cartTotal();
	updateTotalLiked();
	loadLiked();
})();


function setDialog(message){
	$("#dialogModal").modal("show");
	$("#dialogTitle").empty();
	$("#dialogTitle").append("<p>\'" + message +  "\'</p>")
}