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

			prodGetFirst();
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

function prodGetFirst(){
	$.ajax({
			url: "/api/product/v1/products",
			type: "GET",
			data: {
				page: 0
			},

			success: function (response){
				prodLoadPage(response.result.total_pages, 0);
				if(response.status >= 200 && response.status < 300){
					$("#resultProducts").empty();
					let products = response.result.data;
					for(let index = -1; ++index < products.length;){
						let html = "<tr class='tr-shadow'>"
						+ "<td><label class='au-checkbox'> <input type='checkbox'> <span class='au-checkmark'></span>"
						+ "</label></td>"
						+ "<td>" + products[index].name + "</td>"
						+ "<td><span class='block-email'>" + formatDate(new Date(products[index].created_date)) + "</span></td>"
						+ "<td class='desc'>" + formatDate(new Date(products[index].updated_date)) + "</td>"
						+ "<td>" + products[index].price + "</td>"
						// + "<td><span class='status--process'>Processed</span></td>"
						+ "<td>" + products[index].priceSale + "</td>"
						+ "<td>"
						+ "<div class='table-data-feature'>"
						+ "<button class='item' data-toggle='tooltip' data-placement='top' title='Detail'>"
						+ "<i class='zmdi zmdi-more'></i>"
						+ "</button>"
						+ "<button class='item' data-toggle='tooltip' data-placement='top' title='Edit'>"
						+ "<i class='zmdi zmdi-edit'></i>"
						+ "</button>"
						+ "<button class='item' data-toggle='tooltip' data-placement='top' title='Delete'>"
						+ "<i class='zmdi zmdi-delete'></i>"
						+ "</button>"
						+ "</div>"
						+ "</td>"
						+ "</tr>"
						$("#resultProducts").append(html);
					}
				}
			},
			error: function(jqXhr, textStatus, errorMessage){

			}
		}
	)
}

function prodloadData(currentPage){
	$.ajax({
			url: "/api/product/v1/products",
			type: "GET",
			data: {
				page: currentPage
			},

			success: function (response){
				prodLoadPage(response.result.total_pages, currentPage);
				if(response.status >= 200 && response.status < 300){
					$("#resultProducts").empty();
					let products = response.result.data;
					for(let index = -1; ++index < products.length;){
						let html = "<tr class='tr-shadow'>"
							+ "<td><label class='au-checkbox'> <input type='checkbox'> <span class='au-checkmark'></span>"
							+ "</label></td>"
							+ "<td>" + products[index].name + "</td>"
							+ "<td><span class='block-email'>" + formatDate(new Date(products[index].created_date)) + "</span></td>"
							+ "<td class='desc'>" + formatDate(new Date(products[index].updated_date)) + "</td>"
							+ "<td>" + products[index].price + "</td>"
							// + "<td><span class='status--process'>Processed</span></td>"
							+ "<td>" + products[index].priceSale + "</td>"
							+ "<td>"
							+ "<div class='table-data-feature'>"
							+ "<button class='item' data-toggle='tooltip' data-placement='top' title='Detail'>"
							+ "<i class='zmdi zmdi-more'></i>"
							+ "</button>"
							+ "<button class='item' data-toggle='tooltip' data-placement='top' title='Edit'>"
							+ "<i class='zmdi zmdi-edit'></i>"
							+ "</button>"
							+ "<button class='item' data-toggle='tooltip' data-placement='top' title='Delete'>"
							+ "<i class='zmdi zmdi-delete'></i>"
							+ "</button>"
							+ "</div>"
							+ "</td>"
							+ "</tr>"
						$("#resultProducts").append(html);
					}
				}
			},
			error: function(jqXhr, textStatus, errorMessage){

			}
		}
	)
}


function prodLoadPage(total_pages,currentPage){
	let paginationEntityProd = $("#paginationProd");
	paginationEntityProd.empty();
	for(var i = 0;i<total_pages;i++){
		if(i==0){
			paginationEntityProd.append('<li class="page-item active"><span class="page-link" onclick="prodloadData('+ (i) +');">'+ (i+1) +'</span></li>');
		}else{
			paginationEntityProd.append('<li class="page-item"><span class="page-link" onclick="prodloadData('+ (i) +');">'+ (i+1)+'</span></li>');
		}
	}
}
