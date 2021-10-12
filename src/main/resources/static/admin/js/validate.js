function addNewCate(){
	var data = {};
	data["name"] = $("#cateName").val();
	data["description"] = $('#summernote').summernote('code');
	data["avatar"] = $('#ufile').val().replace(/C:\\fakepath\\/i, '')
	if(validateCate()){
		$.ajax({
			url : "/admin/addcate",
			type:"post",
			contentType: "application/json",
			data : JSON.stringify(data),
			
			dataType : "json",
			success : function(jsonResult){
				if(jsonResult.status == 200){			
					alert("Success");
					getFirst();
				}else{
					alert(jsonResult.result);
					alert("error");
				}
			},
			error : function(jqXhr, textStatus, errorMessage) { // error
				// callback

			} 
		});
	}
}

var loadFile = function (event) {
    var image = document.getElementById("output");
    image.src = URL.createObjectURL(event.target.files[0]);
    console.log(image);
    console.log(image.src);
}

//function updateCate(){
//	var data = {};
//	data["name"] = $("#cateName").val();
//	data["description"] = $('#summernote').summernote('code');
//	if(validateCate()){
//		$.ajax({
//			url : "/admin/addcate",
//			type:"post",
//			contentType: "application/json",
//			data : JSON.stringify(data),
//			
//			dataType : "json",
//			success : function(jsonResult){
//				if(jsonResult.status == 200){			
//					alert("Success");
//					getFirst();
//				}else{
//					alert(jsonResult.result);
//					alert("error");
//				}
//			},
//			error : function(jqXhr, textStatus, errorMessage) { // error
//				// callback
//
//			} 
//		});
//	}
//}

//function getAllCategory(){
//	$.ajax({
//		url:
//	})
//}

function validateCate(){
	var name = document.getElementById("cateName");
	var description = $('#summernote').summernote('code');
	if(name.value == ""){
		alert("Invalid name");
		return false;
	}
	return true;
}

function loadPage(total_pages,currentPage){
			$(".pagination").empty();
			for(var i = 0;i<total_pages;i++){
				if(i==0){
					$(".pagination").append('<li class="page-item active"><span class="page-link" onclick="loadData('+ (i) +');">'+ (i+1) +'</span></li>');
				}else{
					$(".pagination").append('<li class="page-item"><span class="page-link" onclick="loadData('+ (i) +');">'+ (i+1)+'</span></li>');
				}
			}			
}

function formatDate(current_datetime){
	if(current_datetime == new Date(null)){
		return null;
	}
	else{
		return current_datetime.getDate() + "-" + (current_datetime.getMonth() + 1) + "-" + current_datetime.getFullYear() 
		+ " " + current_datetime.getHours() + ":" + current_datetime.getMinutes() + ":" + current_datetime.getSeconds();
	} 
}

function getFirst(){
	$.ajax({
		url:"http://localhost:8080/admin/api/v1/getAll",
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
						+				"data-placement='top' title='Edit'>"
						+				"<i class='zmdi zmdi-edit'></i>"
						+			"</button>"
						+			"<button class='item' data-toggle='tooltip'"
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


function detailsCategory(id,currentPage){
	$.ajax({
		url : "http://localhost:8080/admin/api/v1/getOne",
		type : "GET",
		data : {
			id : id
		},
		success : function(data){
			var object = data.result.data;
			if(object){
				$(".modal-body").empty();
				var html = '<div class="row">'+		
					'<div class="col-lg-12">'+
						'<h5>Detail Information</h5>'+
						'<div class="table-responsive">'+
							'<table class="table table-sm table-borderless mb-0">'+
								'<tbody>'+
									'<tr>'+
										'<th class="pl-0 w-25" scope="row"><strong>Category'+
												' name</strong></th>'+
										'<td><input id="cateName" name="name" type="text"'+
											'class="form-control" aria-required="true" data-val="true"'+
											'data-val-required="Please enter the name" aria-invalid="false">' + object[0].name + '</input></td>'+
									'</tr>'+
									'<tr>'+
										'<th class="pl-0 w-25" scope="row"><strong>Description'+
										'</strong></th>'+
										'<td>'+
									'<div class="form-group has-success">'+
									'<textarea id="summernote" name="description" type="text">' + object[0].description + '</textarea>'+
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
										'<td>' + object[0].update_date + '</td>'+
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
					'</div>'+
				'</div>';
				$(".modal-body").html(html);
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
		$.ajax({
		url:"http://localhost:8080/admin/api/v1/getAll",
		type:"GET",
		data: {
			page:currentPage
		},
		success : function(data){
				loadPage(data.result.total_pages,currentPage);
				var tag = document.getElementsByClassName("page-item");
				for(a =0;a<tag.length;a++){
					tag[a].className = tag[a].className.replace(" active", "");
				}
				tag[currentPage].className = "page-item active";
				var html = "";
				$(".resultCategories").empty();
				var dataArr = data.result.data;
				for(var i = 0;i < dataArr.length; i++){
					html = "<tr class='tr-shadow'><td>" + dataArr[i].name + "</td><td><span class='block-email'>" + formatDate(new Date(dataArr[i].created_date)) + "</span></td>"
					+	"<td class='desc'>" + formatDate(new Date(dataArr[i].updated_date)) + "</td>"
					+	"<td>" + dataArr[i].updated_by + "</td>"
					+	"<td>"
					+		"<div class='table-data-feature'>"
					+			"<button class='item' data-toggle='tooltip'"
					+				"data-placement='top' title='Detail'>"
					+				"<i class='zmdi zmdi-more'></i></button>"
					+			"<button class='item' data-toggle='tooltip'"
					+				"data-placement='top' title='Edit'>"
					+				"<i class='zmdi zmdi-edit'></i>"
					+			"</button>"
					+			"<button class='item' data-toggle='tooltip'"
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