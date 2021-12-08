

function addNewCate(event){
	event.preventDefault();
	var form = $('#fileUploadForm')[0];

    var data = new FormData(form);
	if(validateCate()){
		$.ajax({
			url : "/api-admin/category/v1/add",
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
					getFirst();
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


function getFirst(){
	document.getElementById("currentPageCate").value = 0;
	$.ajax({
		url:"/api-admin/category/v1/getAll",
		type:"GET",
		data: {
			page:0
		},
		success : function(data){
				loadPage(data.result.total_pages,0);
				$(".resultCategories").empty();
				var html = "";
				var dataArr = data.result.data;
				for(var i = 0;i < dataArr.length; i++){
					html += "<tr class='tr-shadow'><td>" + dataArr[i].name + "</td><td><span class='block-email'>" + formatDate(new Date(dataArr[i].created_date)) + "</span></td>"
						+	"<td><span class='block-email'>" + formatDate(new Date(dataArr[i].updated_date)) + "</span></td>"
						+	"<td>" + dataArr[i].updated_by + "</td>"
						+	"<td>"
						+		"<div class='table-data-feature'>"
						+			"<button class='item linkDetail' onclick='detailsCategory(" + dataArr[i].id + "," + data.result.page + ");' data-toggle='tooltip'"
						+				"data-placement='top' title='Detail'>"
						+				"<i class='zmdi zmdi-receipt'></i>"
						+			"</button>"
						+			"<button class='item' data-toggle='modal' onclick='showUpdateCategory(" + dataArr[i].id + "," + data.result.page + ");' data-toggle='tooltip' data-target='#updateCate' data-placement='top' title='Edit'>"
                        +				"<i class='zmdi zmdi-edit'></i>"
                        +			"</button>"
						+			"<button class='item' data-toggle='tooltip' onclick='showModalDeleteCategory(" + dataArr[i].id + ")'"
						+				"data-placement='top' title='Delete'>"
						+				"<i class='zmdi zmdi-delete'></i>"
						+			"</button>"
						+		"</div>"
						+	"</td>"
						+"</tr>"
						+"<tr class='spacer'>"
						+ "</tr>";
				}				
				$(".resultCategories").append(html);
		},
		error : function(jqXHR,testStatus,errorThrown){
			console.log(jqXHR);
			console.log(testStatus);
			console.log(errorThrown);
		}
	});
}



$(function(){

	getFirst();

});

function showModalDeleteCategory(id){
	$("#confirmDeleteCategory").modal("show");
	document.getElementById("idForDelete").value = id;
}

$(document).ready(function(){
	$("#buttonDeleteCategory").on("click",function(){
		var id = document.getElementById("idForDelete").value;
		$.ajax({
			url : "/api-admin/category/v1/delete",
			type : "DELETE",
			data : {
				id : id
			},
			success : function(data){
				var object = data.result.data;
				if(object){
					getFirst();
					$("#confirmDeleteCategory").modal("hide");
				}	
			},
			error : function(jqXHR,testStatus,errorThrown){
				alert("cannot delete this category, because it has a dependency with the product !")
				console.log(jqXHR);
				console.log(testStatus);
				console.log(errorThrown);
			}
		});
	});	
});

function showUpdateCategory(id,currentPage){
	
	if(currentPage > 0){
		currentPage = currentPage - 1;
	}
	
	$.ajax({
		url : "/api-admin/category/v1/getOne",
		type : "GET",
		data : {
			id : id
		},
		success : function(data){
			var object = data.result.data;
			if(object){
				document.getElementById("updateCateName").value = object[0].name;
				document.getElementById("cateId").value = object[0].id;
				$("#description").summernote("code", object[0].description);
				document.getElementById("currentPageCate").value = currentPage;
				document.getElementById("outputUpdateCate").src = "/uploads/" + object[0].avatar;
				document.getElementById("avatarNameUpdating").value =  object[0].avatar.substring(object[0].avatar.lastIndexOf('/')+4);
			}
			
		},
		error : function(jqXHR,testStatus,errorThrown){
			console.log(jqXHR);
			console.log(testStatus);
			console.log(errorThrown);
		}
	});
	
}

function updateCategory(event){
	 event.preventDefault();
	 var form = $('#updateCateForm')[0];
	 var currentPage = document.getElementById("currentPageCate").value;
	 var data = new FormData(form);
	
	$.ajax({
		url : "/api-admin/category/v1/update",
		type:"PUT",
		enctype: 'multipart/form-data',
		data : data,
		
		processData: false,
	    contentType: false,
	    cache: false,
	    timeout: 1000000,
	    success : function(jsonResult){
			if(jsonResult.status == 200){			
				alert("Success");
				loadData(currentPage);
			}else{
				alert("error");
			}
		},
		error : function(jqXhr, textStatus, errorMessage) { // error
			// callback

		} 
	});
}

function detailsCategory(id){
	
	$.ajax({
		url : "/api-admin/category/v1/getOne",
		type : "GET",
		data : {
			id : id
		},
		success : function(data){
			var object = data.result.data;
			if(object){
				$("#modal-body").empty();
				var html = '<div class="row">'+	
					'<div class="col-lg-4">'+
						'<div class="card" style="width: 200px">'+
						'<img id="outputCateImage" class="card-img-top img-thumbnail"'+
							'src="/uploads/' + object[0].avatar + '" alt="Card image"'+
							'style="width: 100%" />'+	
						'</div>'+						
					'</div>'+
					'<div class="col-lg-8">'+
						'<h5>Detail Information</h5>'+
						'<div class="table-responsive">'+
							'<table class="table table-sm table-borderless mb-0">'+
								'<tbody>'+
									'<tr>'+
										'<th class="pl-0 w-25" scope="row"><strong>Category'+
												' name</strong></th>'+
										'<td>' + object[0].name + '</td>'+
									'</tr>'+
									'<tr>'+
										'<th class="pl-0 w-25" scope="row"><strong>Description'+
										'</strong></th>'+
										'<td>'+
									'<div class="form-group has-success">'+
									'<textarea class="summernote" rows="4" cols="50" name="description" type="text">' + object[0].description + '</textarea>'+
									'<span class="help-block field-validation-valid"'+
										'data-valmsg-for="cc-name" data-valmsg-replace="true"></span>'+
									'</div>'+
									'</td>'+
									'</tr>'+
									'<tr>'+
										'<th class="pl-0 w-25" scope="row"><strong>Created'+
												' date</strong></th>'+
										'<td>' + formatDate(new Date(object[0].created_date)) + '</td>'+
									'</tr>'+
									'<tr>'+
										'<th class="pl-0 w-25" scope="row"><strong>Update date</strong></th>'+
										'<td>' + formatDate(new Date(object[0].updated_date)) + '</td>'+
									'</tr>'+
									'<tr>'+
										'<th class="pl-0 w-25" scope="row"><strong>created '+
												'by</strong></th>'+
										'<td>' + object[0].created_by + '</td>'+
									'</tr>'+
									'<tr>'+
									'<th class="pl-0 w-25" scope="row"><strong>updated '+
											'by</strong></th>'+
									'<td>' + object[0].update_by + '</td>'+
								'</tr>'+
								'</tbody>'+
							'</table>'+
						'</div>'+
						'<br>'+
					'</div>'+
				'</div>';
				$(".modal-body-details").html(html);
				$("#detailsCategory").modal("show");
			}
		},
		error : function(jqXHR,testStatus,errorThrown){
			console.log(jqXHR);
			console.log(testStatus);
			console.log(errorThrown);
		}
	});
}



function loadData(currentPage){
	
		document.getElementById("currentPageCate").value = currentPage;
	
		$.ajax({
		url:"/api-admin/category/v1/getAll",
		type:"GET",
		data: {
			page:currentPage
		},
		success : function(data){
				loadPage(data.result.total_pages,currentPage);
				var tag = document.getElementsByClassName("cate");
				for(a =0;a<tag.length;a++){
					tag[a].className = tag[a].className.replace("cate active", "cate");
				}
				tag[currentPage].className = "page-item cate active";
				var html = "";
				$(".resultCategories").empty();
				var dataArr = data.result.data;
				for(var i = 0;i < dataArr.length; i++){
					html = "<tr class='tr-shadow'><td>" + dataArr[i].name + "</td><td><span class='block-email'>" + formatDate(new Date(dataArr[i].created_date)) + "</span></td>"
					+	"<td class='desc'>" + formatDate(new Date(dataArr[i].updated_date)) + "</td>"
					+	"<td>" + dataArr[i].updated_by + "</td>"
					+	"<td>"
					+		"<div class='table-data-feature'>"
					+			"<div class='table-data-feature'>"
					+			"<button class='item linkDetail' onclick='detailsCategory(" + dataArr[i].id + "," + data.result.page + ");' data-toggle='tooltip'"
					+				"data-placement='top' title='Edit'>"
					+				"<i class='zmdi zmdi-receipt'></i>"
					+			"</button>"
					+			"<button class='item' data-toggle='modal' onclick='showUpdateCategory(" + dataArr[i].id + "," + data.result.page + ");' data-toggle='tooltip' data-target='#updateCate' data-placement='top' title='Edit'>"
                    +				"<i class='zmdi zmdi-edit'></i>"
                    +			"</button>"
					+			"<button class='item' data-toggle='tooltip' onclick='showModalDeleteCategory(" + dataArr[i].id + ")'"
					+				"data-placement='top' title='Delete'>"
					+				"<i class='zmdi zmdi-delete'></i>"
					+			"</button>"
					+		"</div>"
					+	"</td>"
					+"</tr>"
					+"<tr class='spacer'>"
					+ "</tr>";
					$(".resultCategories").append(html);
				}				
		},
		error : function(jqXHR,testStatus,errorThrown){
			console.log(jqXHR);
			console.log(testStatus);
			console.log(errorThrown);
		}
	});
}

function loadPage(total_pages,currentPage){
	$("#paginationCate").empty();
	for(var i = 0;i<total_pages;i++){
		if(i==0){
			$("#paginationCate").append('<li class="page-item cate active"><span class="page-link" onclick="loadData('+ (i) +');">'+ (i+1) +'</span></li>');
		}else{
			$("#paginationCate").append('<li class="page-item cate"><span class="page-link" onclick="loadData('+ (i) +');">'+ (i+1)+'</span></li>');
		}
	}			
}

var loadFile = function (event) {
    var image = document.getElementById("output");
    image.src = URL.createObjectURL(event.target.files[0]);
	var uploadedFilePath = $('#ufile').val();
    document.getElementById("avatarName").value = uploadedFilePath.substring(uploadedFilePath.lastIndexOf("/"));
}

var loadFileUpdateCate = function (event) {
    var imageUpdate = document.getElementById("outputUpdateCate");
    imageUpdate.src = URL.createObjectURL(event.target.files[0]);
	var uploadedFilePath = $('#ufile1').val();
    document.getElementById("avatarNameUpdating").value = uploadedFilePath.substring(uploadedFilePath.lastIndexOf("/"));
}

function validateCate(){
	var name = document.getElementById("cateName");
	if(name.value == ""){
		alert("Invalid name");
		return false;
	}
	return true;
}