
function addNewCate(){
	var data = {};
	data["name"] = $("#cateName").val();
	data["description"] = $("#summernote").val();
	console.log(data["name"]);
	if(validateCate()){
		$.ajax({
			url : "/admin/addcate",
			type:"post",
			contentType: "application/json",
			data : JSON.stringify(data),
			
			dataType : "json",
			success : function(jsonResult){
				if(jsonResult.statusCode == 200){
					alert("Success");
				}else{
					alert("error");
				}
			},
			error : function(jqXhr, textStatus, errorMessage) { // error
				// callback

			} 
		});
	}
}

function validateCate(){
	var name = document.getElementById("cateName");
	var description = document.getElementById("description");
	
	if(name.value == ""){
		alert("Invalid name");
		return false;
	}
	return true;
}