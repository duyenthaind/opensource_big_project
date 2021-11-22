var listCart = [];

//$(function(){
//	localStorage.cartItem = "";
//});

//function updateTotalCart(){
//	listCart = JSON.parse(localStorage.cartItem);
//	$(".totalCart").html(listCart.length);
//	console.log(listCart);
//}

function addCart(id,quantity){
	var cart = new Object();
	cart.productId = id;
	cart.quantity = quantity;
	console.log(listCart.length)
	console.log(localStorage.cartItem)
	console.log(cart.quantity)
	
	if(listCart.length == 0 && localStorage.cartItem != '' && localStorage.cartItem != null){
		console.log("here2")
		
		let listCartOld = JSON.parse(localStorage.getItem("cartItem"));
		listCart = listCartOld;
		for(var i=0;i<listCart.length;i++){
			if(cart.productId == listCart[i].productId){
				listCart[i].quantity = listCart[i].quantity + quantity;
			}else{
				listCart.push(cart);
			}
		}	
	}else{
		console.log("here1")
		if(listCart.length != 0){
			for(var i=0;i<listCart.length;i++){
				if(cart.productId == listCart[i].productId){
					listCart[i].quantity = listCart[i].quantity + quantity;
				}else{
					console.log("here");
					listCart.push(cart);
				}
			}	
		}else{
			listCart.push(cart);
		}	
	}
	localStorage.cartItem = JSON.stringify(listCart);
	
	$(".totalCart").html(listCart.length);
	$("#dialogTitle").html("successfully added");
	$("#cartTitle").html("Add new cart");
	$("#dialogModal").modal("toggle");
	
}