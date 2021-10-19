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
	var productAvailable = document.getElementById("productAvailable").value;
	var productPrice = document.getElementById("productPrice").value;
	var productPriceSale = document.getElementById("productPriceSale").value;
	var productWeight = document.getElementById("productWeight").value;
	var productShortDescription = document.getElementById("productShortDescription").value;
	var productDetailDescription = document.getElementById("productDetailDescription").value;
	
    var data = new FormData();
    
    data.set("productName",productName);
    data.set("available",productAvailable);
    data.set("price",productPrice);
    data.set("priceSale",productPriceSale);
    data.set("categoryId",productCategory);
    data.set("weight",productWeight);
    data.set("shortDescription",productShortDescription);
    data.set("detailDescription",productDetailDescription);
    
	var files = document.getElementById('ufileProduct').files;
	
	for(var i=0;i<files.length;i++){
		data.append("files",files[i]);
	}
	
	if(validateProduct()){
		$.ajax({
			url : "/api/product/v1/products",
			type:"POST",
			enctype: 'multipart/form-data',
			data : data,
			
			processData: false,
		    contentType: false,
		    cache: false,
		    timeout: 1000000,
			success : function(jsonResult){
				if(jsonResult.status >= 200 && jsonResult.status < 300){
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
	let numberPattern = /\d+/g;
	let floatNumberPattern = /^\d{0,2}(?:\.\d{0,2}){0,1}$/;
	
	let productCategory = document.getElementById("productCategory");
	let productName = document.getElementById("productName");
	let productAvailable = document.getElementById("productAvailable");
	let productPrice = document.getElementById("productPrice");
	let productPriceSale = document.getElementById("productPriceSale");
	let productWeight = document.getElementById("productWeight");
	let productShortDescription = document.getElementById("productShortDescription");
	let productDetailDescription = document.getElementById("productDetailDescription");

	if(!productName.value){
		alert("Invalid name");
		return false;
	}
	if(!productAvailable.value || isNaN(parseInt(productAvailable.value))){
		alert("Available is not number");
		return false;
	}
	if(!productPrice.value || !productPrice.value.match(numberPattern)){
		alert("Price is not number");
		return false;
	}
	if(!productPriceSale.value  || !productPriceSale.value.match(numberPattern)){
		alert("Price sale is not number");
		return false;
	}
	if(!productWeight.value  || isNaN(parseInt(productWeight.value))){
		alert("Weight is wrong format");
		return false;
	}
	if(!productShortDescription.value ){
		alert("Invalid short description");
		return false;
	}
	if(!productDetailDescription.value ){
		alert("Invalid detail description");
		return false;
	}

	return true;
	
}