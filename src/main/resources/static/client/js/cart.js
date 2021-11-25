function addCart(id,quantity){
	$.ajax({
		url : "",
		type:"POST",
		data : {
			productId : id,
			quantity : quantity,
		},
		success: function(responseData){
			
		},
		error
	});
}