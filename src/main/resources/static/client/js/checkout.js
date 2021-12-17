$("#btn_apply_coupon").click(function(){
	var order = $("#checkout_order_coupon");
	var code = $("#apply_coupon").val();
	var total = $("#totalCartOrder");
	$.ajax({
		url : "/v1/api/client-coupon/coupons",
		type:"POST",
		data : JSON.stringify({
			code : code,
		}),
		dataType: 'json',
        contentType: 'application/json',
		success: function(responseData){
			if(responseData.result != null){
				order.empty();
				order.append("Your Coupon <span>"+ responseData.result.data[0].code +"</span><input name='couponCode' type='hidden' value="+ responseData.result.data[0].code +">");
				setDialog("Success");
				var newTotal = parseInt(total.text().substring(1))-responseData.result.data[0].total
				total.html("$"+newTotal);
			}else {
				setDialog("coupon code is not valid");
			}

		},
		error : function(jqXhr, textStatus, errorMessage) { // error
			// callback

		}
	});
});

$(function() {
	loadCartOrder();
});

function loadCartOrder(){
	var cartOrder = $("#loadCartOrder");
	cartOrder.empty();
	var html = "";
	var total = 0;
	$.ajax({
		url : "/user-carts/carts",
		type:"GET",
		dataType: 'json',
        contentType: 'application/json',
		success: function(responseData){
			var data = responseData.result.data;
				for(let i=0;i<data.length;i++){
					html += "<li>"+ data[i].name +"<span>$"+data[i].price*data[i].quantity+"</span></li>";
					total += data[i].price*data[i].quantity;
				}
				cartOrder.append(html);
				$("#totalCartOrder").html("$"+total);
		},
		error : function(jqXhr, textStatus, errorMessage) { // error
			// callback

		}
	});
}

$("#place_order").click(function(event){
	event.preventDefault();
	var form = $('#formCheckout')[0];

    var data = new FormData(form);
    if(validateOrder()){
    	$.ajax({
			url : "/checkout/v1/api/checkouts",
			type:"POST",
			data : data,			
			processData: false,
		    contentType: false,
		    cache: false,
		    timeout: 1000000,
			success : function(jsonResult){
				if(jsonResult.status == 200){	
					var data = jsonResult.result.data[0];
					setDialog("Success");
					
					if(data.type == 2){
						window.location.href=data.payUrl;
					}
					else{
						let userinfo = JSON.parse(localStorage.userinfo);
						window.location.href="/user-profile?username="+ userinfo.username +"";
					}
				
					
					$("#totalCartOrder").empty();
					$("#totalCartOrder").html("$0");
				}
			},
			error : function(jqXhr, textStatus, errorMessage) { // error
				// callback
				setDialog("Failure");
			} 
		});
    }
});

function validateOrder(){
	var customerName = document.getElementById("customerName");
	var customerAddress = document.getElementById("customerAddress");
	var customerPhone = document.getElementById("customerPhone");
	var customerEmail = document.getElementById("customerEmail");
	if(customerName.value == ""){
		alert("Invalid customerName");
		return false;
	}
	if(customerAddress.value == ""){
		alert("Invalid customerAddress");
		return false;
	}
	if(customerPhone.value == ""){
		alert("Invalid customerPhone");
		return false;
	}
	if(customerEmail.value == ""){
		alert("Invalid customerEmail");
		return false;
	}
	return true;
}
