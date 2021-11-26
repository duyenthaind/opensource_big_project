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

			$('#ufileProductUpdate').on('change', function() {
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
			url : "/api-admin/product/v1/products",
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
					prodGetFirst();
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

function updateProduct(event){
	event.preventDefault();

	let productCategory = $("#productCategoryUpdate").val()
    let productName = $("#productNameUpdate").val()
    let productAvailable = $("#productAvailableUpdate").val();
    let productPrice = $("#productPriceUpdate").val()
    let productPriceSale = $("#productPriceSaleUpdate").val()
    let productWeight = $("#productWeightUpdate").val()
    let productShortDescription = $("#productShortDescriptionUpdate").val()
    let productDetailDescription = $("#productDetailDescriptionUpdate").val()
	let id = $("#productIdUpdate").val();
    let createdDate = $("#createdDateProd").val();

    let data = new FormData();

	data.set("productName",productName);
	data.set("available",productAvailable);
	data.set("price",productPrice);
	data.set("priceSale",productPriceSale);
	data.set("categoryId",productCategory);
	data.set("weight",productWeight);
	data.set("shortDescription",productShortDescription);
	data.set("detailDescription",productDetailDescription);
	data.set("id", id);
	data.set("createdDate",createdDate);

	var files = document.getElementById('ufileProductUpdate').files;
	var currentPage = document.getElementById("currentPageUpdateProd").value;
	for(var i=0;i<files.length;i++){
		data.append("files",files[i]);
	}

	if(validateProduct2(data)){
		
		$.ajax({
			url : "/api-admin/product/v1/products",
			type:"PUT",
			enctype: 'multipart/form-data',
			data : data,

			processData: false,
			contentType: false,
			cache: false,
			timeout: 1000000,
			success : function(jsonResult){
				if(jsonResult.status >= 200 && jsonResult.status < 300){
					alert("Success");				
					prodloadData(currentPage);
					$("#modalUpdateProduct").modal("hide");
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

function validateProduct2(data){
	let numberPattern = /\d+/g;
	let floatNumberPattern = /^\d{0,2}(?:\.\d{0,2}){0,1}$/;

	let rawData = {};

	data.forEach(function(value, key){
		rawData[key]= value;
	});

	console.log(data)
	console.log(rawData)

	let productCategory = rawData.categoryId
	let productName = rawData.productName
	let productAvailable = rawData.available
	let productPrice = rawData.price
	let productPriceSale = rawData.priceSale
	let productWeight = rawData.weight
	let productShortDescription = rawData.shortDescription
	let productDetailDescription = rawData.detailDescription

	if(!productName){
		alert("Invalid name");
		return false;
	}
	if(!productAvailable || isNaN(parseInt(productAvailable))){
		alert("Available is not number");
		return false;
	}
	if(!productPrice || !productPrice.match(numberPattern)){
		alert("Price is not number");
		return false;
	}
	if(!productPriceSale  || !productPriceSale.match(numberPattern)){
		alert("Price sale is not number");
		return false;
	}
	if(!productWeight  || isNaN(parseInt(productWeight))){
		alert("Weight is wrong format");
		return false;
	}
	if(!productShortDescription ){
		alert("Invalid short description");
		return false;
	}
	if(!productDetailDescription ){
		alert("Invalid detail description");
		return false;
	}

	return true;
}

function prodGetFirst(){
	document.getElementById("currentPageUpdateProd").value = 0;
	$.ajax({
			url: "/api-admin/product/v1/products",
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
						+ "<td>" + products[index].name + "</td>"
						+ "<td><span class='block-email'>" + formatDate(new Date(products[index].created_date)) + "</span></td>"
						+ "<td class='desc'>" + formatDate(new Date(products[index].updated_date)) + "</td>"
						+ "<td>" + products[index].price + "</td>"
						// + "<td><span class='status--process'>Processed</span></td>"
						+ "<td>" + products[index].priceSale + "</td>"
						+ "<td>"
						+ "<div class='table-data-feature'>"
						+ "<button class='item' data-toggle='tooltip' data-placement='top' title='Detail' onclick='detailsProduct(" + products[index].id + ")'>"
						+ "<i class='zmdi zmdi-receipt'></i>"
						+ "</button>"
						+ "<button class='item' data-toggle='tooltip' data-placement='top' title='Edit' onclick='showModalUpdateProduct(" + products[index].id + "," + response.result.page + ")'>"
						+ "<i class='zmdi zmdi-edit'></i>"
						+ "</button>"
						+ "<button class='item' data-toggle='tooltip' data-placement='top' title='Delete' onclick='showModalDeleteProduct(" + products[index].id + ")'>"
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
			url: "/api-admin/product/v1/products",
			type: "GET",
			data: {
				page: currentPage
			},

			success: function (response){
				prodLoadPage(response.result.total_pages, currentPage);
				var tag = document.getElementsByClassName("prod");
				for(a =0;a<tag.length;a++){
					tag[a].className = tag[a].className.replace("prod active", "prod");
				}
				tag[currentPage].className = "page-item prod active";
				if(response.status >= 200 && response.status < 300){
					$("#resultProducts").empty();
					let products = response.result.data;
					for(let index = -1; ++index < products.length;){
						let html = "<tr class='tr-shadow'>"
							+ "<td>" + products[index].name + "</td>"
							+ "<td><span class='block-email'>" + formatDate(new Date(products[index].created_date)) + "</span></td>"
							+ "<td class='desc'>" + formatDate(new Date(products[index].updated_date)) + "</td>"
							+ "<td>" + products[index].price + "</td>"
							// + "<td><span class='status--process'>Processed</span></td>"
							+ "<td>" + products[index].priceSale + "</td>"
							+ "<td>"
							+ "<div class='table-data-feature'>"
							+ "<button class='item linkDetail' data-toggle='tooltip' data-placement='top' title='Detail' onclick='detailsProduct(" + products[index].id + ")'>"
							+ "<i class='zmdi zmdi-receipt'></i>"
							+ "</button>"
							+ "<button class='item' data-toggle='tooltip' data-placement='top' title='Edit' onclick='showModalUpdateProduct(" + products[index].id + "," + response.result.page + ")'>"
							+ "<i class='zmdi zmdi-edit'></i>"
							+ "</button>"
							+ "<button class='item' data-toggle='tooltip' data-placement='top' title='Delete' onclick='showModalDeleteProduct(" + products[index].id + ")'>"
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
	for(let i = 0;i<total_pages;i++){
		if(i===0){
			paginationEntityProd.append('<li class="page-item prod active"><span class="page-link" onclick="prodloadData('+ (i) +');">'+ (i+1) +'</span></li>');
		}else{
			paginationEntityProd.append('<li class="page-item prod"><span class="page-link" onclick="prodloadData('+ (i) +');">'+ (i+1)+'</span></li>');
		}
	}
}

function detailsProduct(id){
	$.ajax({
		url: "/api-admin/product/v1/products/" + id,
		type: "GET",
		data:{

		},

		success: function(response){
			if(response.status >= 200 && response.status < 300){
				console.log(response)
				let objects = response.result.data;
				let listImages = objects[0].productImages;
				let images = "<div style='width: 210px'>";

				for(let image of listImages){
					images += '<div class="col-lg-4" style="position: relative"><div class="card" style="width: 200px " >'
								+ '<img class="card-img-top img-thumbnail" src="/uploads/' + image + '" alt="Product Detail image"'
								+ 'style="width:100%"/></div></div><br/>'
				}
				images += "</div>";
				let html = '<div class="row">'+ images +
					'<div class="col-lg-8">'+
					'<h5>Detail Information</h5>'+
					'<div class="table-responsive">'+
					'<table class="table table-sm table-borderless mb-0">'+
					'<tbody>'+
					'<tr>'+
					'<th class="pl-0 w-25" scope="row"><strong>Name</strong></th>'+
					'<td>' + objects[0].name + '</td>'+
					'</tr>'+
					'<tr>'+
					'<th class="pl-0 w-25" scope="row"><strong>Available</strong></th>'+
					'<td>' + objects[0].available + '</td>'+
					'</tr>'+
					'<tr>'+
					'<th class="pl-0 w-25" scope="row"><strong>Price</strong></th>'+
					'<td>' + objects[0].price + '</td>'+
					'</tr>'+
					'<tr>'+
					'<th class="pl-0 w-25" scope="row"><strong>Price sale</strong></th>'+
					'<td>' + objects[0].priceSale + '</td>'+
					'</tr>'+
					'<tr>'+
					'<th class="pl-0 w-25" scope="row"><strong>Weight</strong></th>'+
					'<td>' + objects[0].weight + '</td>'+
					'</tr>'+
					'<tr>'+
					'<th class="pl-0 w-25" scope="row"><strong>Description'+
					'</strong></th>'+
					'<td>'+
					'<div class="form-group has-success">'+
					'<textarea class="summernote" rows="4" cols="50" name="description" type="text">' + objects[0].detailDescription + '</textarea>'+
					'<span class="help-block field-validation-valid"'+
					'data-valmsg-for="cc-name" data-valmsg-replace="true"></span>'+
					'</div>'+
					'</td>'+
					'</tr>'+
					'<tr>'+
					'<th class="pl-0 w-25" scope="row"><strong>Created'+
					' date</strong></th>'+
					'<td>' + formatDate(new Date(objects[0].created_date)) + '</td>'+
					'</tr>'+
					'<tr>'+
					'<th class="pl-0 w-25" scope="row"><strong>Update date</strong></th>'+
					'<td>' + formatDate(new Date(objects[0].updated_date)) + '</td>'+
					'</tr>'+
					'<tr>'+
					'<th class="pl-0 w-25" scope="row"><strong>Created '+
					'by</strong></th>'+
					'<td>' + objects[0].created_by + '</td>'+
					'</tr>'+
					'<tr>'+
					'<th class="pl-0 w-25" scope="row"><strong>Updated '+
					'by</strong></th>'+
					'<td>' + objects[0].update_by + '</td>'+
					'</tr>'+
					'</tbody>'+
					'</table>'+
					'</div>'+
					'<br>'+
					'</div>'+
					'</div>';
				$(".modal-body-details").html(html);
				$("#detailsProduct").modal("show");
			}
		},
		error: function(jqXhr, textStatus, errorMessage){
			console.log(textStatus);
			console.log(errorMessage);
		}
	});
}

function deleteProduct(id){
	$.ajax({
		url: "/api-admin/product/v1/products" ,
		type: "DELETE",
		data: {
			id: id
		},

		success: function(response){
			if(response.status >= 200 && response.status < 300){
				prodGetFirst()
			}
		},
		error: function (jqXhr, textStatus, errorMessage){
			console.log("Error", errorMessage)
		}
	})
	$("#confirmDeleteProduct").modal("hide");
}

function showModalDeleteProduct(id){
	$("#confirmDeleteProduct").modal("show");
	document.getElementById("idForDeleteProduct").value = id;
}

function showModalUpdateProduct(id,currentPage){
	
	if(currentPage > 0){
		currentPage = currentPage - 1;
	}
	
	$.ajax({
		url: "/api-admin/product/v1/products/" + id,
		type: "GET",
		data:{

		},

		success: function(response) {
            if(response.status >= 200 && response.status < 300){
                let data = response.result.data[0]
                $("#modalUpdateProduct").modal("show");
                $('#productIdUpdate').val(data.id)
                $('#productCategoryUpdate').val(data.categoryId)
                $('#productNameUpdate').val(data.name)
                $('#productAvailableUpdate').val(data.available)
                $('#productPriceUpdate').val(data.price)
                $('#productPriceSaleUpdate').val(data.priceSale)
                $('#productWeightUpdate').val(data.weight)
                $("#productShortDescriptionUpdate").summernote("code", data.shortDescription);
                $("#productDetailDescriptionUpdate").summernote("code", data.detailDescription);
                $("#currentPageUpdateProd").val(currentPage);
                $("#createdDateProd").val(data.created_date);
                let images = "";
				let index = 0;
				for(let image of data.listProductImages){
                    images += '<div class="col-lg-4" style="position: relative" id="imageProduct'+ index +'"><div class="card" style="width: 200px " >'
                        + '<img class="card-img-top img-thumbnail" src="/uploads/' + image.path + '" alt="Product Detail image"'
                        + 'style="width:100%"/></div>'
						+ "<button type='button' class='item' data-toggle='tooltip' data-placement='top' title='Delete' onclick='showModalDeleteProductImage(" + image.id + ", event, " + index + ")'>"
						+ "<i class='zmdi zmdi-block'></i>"
						+ "</button>"
						+ '</div>'
					index++
                }
                let galleryElement = $(".gallery")
                galleryElement.empty()
                galleryElement.append(images)
            }
		},
		error:function (jqXhr, textStatus, errorMessage){
			console.log("Error", errorMessage)
		}
	});


}

function emptyGallery(){
	let galleryElement = $(".gallery")
	galleryElement.empty()

}

$(document).ready(function(){
	$("#buttonDeleteProduct").on("click",function(){
		let productIdElement = document.getElementById("idForDeleteProduct");
		/*console.log(productIdElement)
		console.log(productIdElement.value)*/

		deleteProduct(productIdElement.value)
	});

});
