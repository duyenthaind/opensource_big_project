

function addNewCate(event){
	 event.preventDefault();
	var form = $('#fileUploadForm')[0];

    var data = new FormData(form);
    
    $("#submitButtonCate").prop("disabled", true);
	if(validateCate()){
		$.ajax({
			url : "/admin/addcate",
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
    document.getElementById("avatarName").value = $('#ufile').val().replace(/C:\\fakepath\\/i, '');
}

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
		url:"/admin/api/v1/getAll",
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
		url : "/admin/api/v1/getOne",
		type : "GET",
		data : {
			id : id
		},
		success : function(data){
			var object = data.result.data;
			if(object){
				$(".modal-body").empty();
				var html = '<div class="row">'+	
					'<div class="col-lg-4">'+
						'<div class="card" style="width: 200px">'+
						'<img class="card-img-top img-thumbnail"'+
							'src="${base}/uploads/category' + "//////////" + '" alt="Card image"'+
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
										'<td><input id="cateName" name="name" type="text"'+
											'class="form-control" aria-required="true" data-val="true"'+
											'data-val-required="Please enter the name" aria-invalid="false" value="' + object[0].name + '"></input></td>'+
									'</tr>'+
									'<tr>'+
										'<th class="pl-0 w-25" scope="row"><strong>Description'+
										'</strong></th>'+
										'<td>'+
									'<div class="form-group has-success">'+
									'<textarea id="summernote1" name="description" type="text">' + object[0].description + '</textarea>'+
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
		url:"/admin/api/v1/getAll",
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
					+			"<div class='table-data-feature'>"
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

function addNewContactMessage(event){
	event.preventDefault();
	var form = $('#sendContactMessageForm')[0];

	var data = new FormData(form);

	var rawData = {};

	data.forEach(function(value, key){
		rawData[key]= value;
	});

	if(!rawData["name"] && !rawData["email"]){
		alert("Please fill in at least your name or your email")
		return
	}

	if(!validateEmail(rawData["email"])){
		alert("Please give us a valid email address")
		return
	}

	if(!rawData["message"]){
		alert("Please give us a message")
		return
	}

	$.ajax({
		url:"/api/v1/send_contact",
		type: "POST",
		enctype: 'multipart/form-data',
		// dataType: 'json',
		cache: false,
		processData: false,
		contentType: false,
		data: data,
		timeout: 5000,
		success : function(data){
			if(data.status === 200){
				console.log("Send data ok");
				alert("Thanks for your submission, we appreciated that")
			}
		},
		error: function (jqXHR,textStatus,errorThrown){
			console.log(jqXHR);
			console.log(textStatus);
			console.log(errorThrown);
			alert("Cannot send message now");
		}
	});
}

function validateEmail(email) {
	const re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	return re.test(String(email).toLowerCase());
}