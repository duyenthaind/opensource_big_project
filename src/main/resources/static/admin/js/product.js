$(function() {
			// Multiple images preview in browser
			var imagesPreview = function(input, placeToInsertImagePreview) {

				if (input.files) {
					var filesAmount = input.files.length;
					if(filesAmount <= 5){
						for (i = 0; i < filesAmount; i++) {
							var reader = new FileReader();
							
							reader.onload = function(event) {
								$($.parseHTML('<img>')).attr('src',
										event.target.result).appendTo(
										placeToInsertImagePreview);
								
							}
							reader.readAsDataURL(input.files[i]);
						}
					}else{
						alert("no more than 5");
					}
					
				}

			};

			$('#ufileProduct').on('change', function() {
				$(".gallery").empty();
				imagesPreview(this, 'div.gallery');
			});
		});

function addNewProduct(event){
	event.preventDefault();
	var productCategory = document.getElementById("productCategory").value;
	var productName = document.getElementById("productName").value;
	var productAvailble = document.getElementById("productAvailble").value;	
	var productPrice = document.getElementById("productPrice").value;
	var productPriceSale = document.getElementById("productPriceSale").value;
	var productWeight = document.getElementById("productWeight").value;
	var productShortDescription = document.getElementById("productShortDescription").value;
	var productDetaiDescription = document.getElementById("productDetaiDescription").value;
	
    var data = new FormData();
    
    data.set("productName",productName);
    data.set("available",productAvailble);
    data.set("price",productAvailble);
    data.set("priceSale",productPriceSale);
    data.set("categoryId",productCategory);
    data.set("weight",productWeight);
    data.set("shortDescription",productShortDescription);
    data.set("detailDescription",productDetaiDescription);
    
	var files = document.getElementById('ufileProduct').files;
	
	for(var i=0;i<files.length;i++){
		data.append("files",files[i]);
	}
	
	if(validateProduct()){
		$.ajax({
			url : "/api/product/v1/add",
			type:"POST",
			enctype: 'multipart/form-data',
			data : data,
			
			processData: false,
		    contentType: false,
		    cache: false,
		    timeout: 1000000,
			success : function(jsonResult){
				if(jsonResult.status == 200){			
					alert("Success");
					console.log(jsonResult);
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

function validateProduct(){
	var numberPattern = /\d+/g;
	var floatNumberPattern = /^\d{0,2}(?:\.\d{0,2}){0,1}$/;
	
	var productCategory = document.getElementById("productCategory");
	var productName = document.getElementById("productName");
	var productAvailble = document.getElementById("productAvailble");	
	var productPrice = document.getElementById("productPrice");
	var productPriceSale = document.getElementById("productPriceSale");
	var productWeight = document.getElementById("productWeight");
	var productShortDescription = document.getElementById("productShortDescription");
	var productDetaiDescription = document.getElementById("productDetaiDescription");
	
	if(productName.value == ""){
		alert("Invalid name");
		return false;
	}
	if(productAvailble.value == "" || !productAvailble.value.match(numberPattern)){
		alert("Available is not number");
		return false;
	}
	if(productPrice.value == "" || !productPrice.value.match(floatNumberPattern)){
		alert("Price is not number");
		return false;
	}
	if(productPriceSale.value == "" || !productPriceSale.value.match(floatNumberPattern)){
		alert("Price sale is not number");
		return false;
	}
	if(productWeight.value == "" || !productWeight.value.match(floatNumberPattern)){
		alert("Weight is wrong format");
		return false;
	}
	if(productShortDescription.value == ""){
		alert("Invalid short description");
		return false;
	}
	if(productDetaiDescription.value == ""){
		alert("Invalid detail description");
		return false;
	}

	return true;
	
}