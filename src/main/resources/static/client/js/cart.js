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
		},
		error : function(jqXhr, textStatus, errorMessage) { // error
			// callback

		}
	});
}

function setDialog(message){
	$("#dialogModal").modal("show");
	$("#dialogTitle").append("<p>\'" + message +  "\'</p>")
}