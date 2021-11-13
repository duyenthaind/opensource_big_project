
$(function(){

	getFirstBlogPage();

});


function addNewBlog(event){
	event.preventDefault();
	var form = $('#blogUploadForm')[0];

    var data = new FormData(form);
	if(validateBlog()){
		$.ajax({
			url : "/api-admin/blog/v1/blogs",
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
					getFirstBlogPage();
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

function getFirstBlogPage(){
	document.getElementById("currentPageBlog").value = 0;
	$.ajax({
		url:"/api-admin/blog/v1/blogs",
		type:"GET",
		data: {
			page:0
		},
		success : function(data){
			loadPageBlog(data.result.total_pages,0);
				$(".data_table_blog").empty();
				var html = "";
				var dataArr = data.result.data;
				for(var i = 0;i < dataArr.length; i++){
					html += '<div class="row">'+
						'<div class="col-lg-4">'+
							'<div class="hovereffect">'+
								'<img class="img-responsive" src="/uploads/' + dataArr[i].avatar + '" alt="" />'+
								'<div class="overlay">'+
								'	<h2>' + dataArr[i].thumbnail + '</h2>'+
								'	<a class="info" data-toggle="modal"'+
								'onclick="showDetailAndUpdateBlog(' + dataArr[i].id + "," + data.result.page + ');" data-toggle="tooltip" data-target="#detailBlog" data-placement="top" title="Edit"'+
								'href="#">detail</a>'+
								'</div>'+
							'</div>'+
						'</div>'+

						'<div class="col-lg-7">'+
							'<h4>' + dataArr[i].thumbnail + '</h4>'+
							'<br /> <i class="fa fa-calendar-o"> ' + formatDate(new Date(dataArr[i].created_date)) + '</i>'+
							'<p>' + dataArr[i].thumbnail + '</p>'+
						'</div>'+
						'<div class="col-lg-1">'+
							'<button class="btn btn-danger">Delete</button>'+
						'</div>'+
					'</div>'+
					'<br />';
				}				
				$(".data_table_blog").append(html);
		},
		error : function(jqXHR,testStatus,errorThrown){
			console.log(jqXHR);
			console.log(testStatus);
			console.log(errorThrown);
		}
	});
}

function showDetailAndUpdateBlog(id,currentPage){
	if(currentPage > 0){
		currentPage = currentPage - 1;
	}
	
	$.ajax({
		url : "/api-admin/blog/v1/blogs",
		type : "GET",
		data : {
			id : id
		},
		success : function(data){
			var object = data.result.data;
			if(object){
				document.getElementById("detailBlogThumbnail").value = object[0].thumbnail;
				document.getElementById("updateBlogId").value = object[0].id;
				document.getElementById("detailShortDescription").value = object[0].shortDescription;
				$("#updateDetailsBlog").summernote("code", object[0].details);
				document.getElementById("currentPageBlog").value = currentPage;
				document.getElementById("detailOutputBlog").src = "/uploads/" + object[0].avatar;
				$('#blogCreatedDate').val(object[0].created_date)
			}
			
		},
		error : function(jqXHR,testStatus,errorThrown){
			console.log(jqXHR);
			console.log(testStatus);
			console.log(errorThrown);
		}
	});
	
}


function updateBlog(event){
	 event.preventDefault();
	 var form = $('#blogDetailForm')[0];
	 var currentPage = document.getElementById("currentPageBlog").value;
	 var data = new FormData(form);
	
	$.ajax({
		url : "/api-admin/blog/v1/blogs",
		type:"PUT",
		enctype: 'multipart/form-data',
		data : data,
		
		processData: false,
	    contentType: false,
	    cache: false,
	    timeout: 10000,
	    success : function(jsonResult){
			if(jsonResult.status == 200){			
				alert("Success");
				loadDataCurrentPage(currentPage);
			}else{
				alert("error");
			}
		},
		error : function(jqXhr, textStatus, errorMessage) { // error
			// callback

		} 
	});
}

function loadDataCurrentPage(currentPage){
	
	document.getElementById("currentPageBlog").value = currentPage;
	$.ajax({
	url:"/api-admin/blog/v1/blogs",
	type:"GET",
	data: {
		page:currentPage
	},
	success : function(data){
		loadPageBlog(data.result.total_pages,currentPage);
			var tag = document.getElementsByClassName("blogs");
			for(a = 0;a<tag.length;a++){
				tag[a].className = tag[a].className.replace("blogs active", "blogs");
			}
			tag[currentPage].className = "page-item blogs active";
			var html = "";
			$(".data_table_blog").empty();
			var dataArr = data.result.data;
			for(var i = 0;i < dataArr.length; i++){
				html += '<div class="row">'+
					'<div class="col-lg-4">'+
						'<div class="hovereffect">'+
							'<img class="img-responsive" src="/uploads/' + dataArr[i].avatar + '" alt="" />'+
							'<div class="overlay">'+
							'	<h2>' + dataArr[i].thumbnail + '</h2>'+
							'	<a class="info" data-toggle="modal"'+
							'onclick="showDetailAndUpdateBlog(' + dataArr[i].id + "," + data.result.page + ');" data-toggle="tooltip" data-target="#detailBlog" data-placement="top" title="Edit"'+
							'href="#">detail</a>'+
							'</div>'+
						'</div>'+
					'</div>'+

					'<div class="col-lg-7">'+
						'<h4>' + dataArr[i].thumbnail + '</h4>'+
						'<br /> <i class="fa fa-calendar-o"> ' + formatDate(new Date(dataArr[i].created_date)) + '</i>'+
						'<p>' + dataArr[i].thumbnail + '</p>'+
					'</div>'+
					'<div class="col-lg-1">'+
						'<button class="btn btn-danger" onclick="showModalDeleteBlog(' + dataArr[i].id + ')">Delete</button>'+
					'</div>'+
				'</div>'+
				'<br />';
			}				
			$(".data_table_blog").append(html);			
	},
	error : function(jqXHR,testStatus,errorThrown){
		console.log(jqXHR);
		console.log(testStatus);
		console.log(errorThrown);
	}
});
}

function showModalDeleteBlog(id){
	$("#confirmDeleteBlog").modal("show");
	document.getElementById("idForDeleteBlog").value = id;
}

$(document).ready(function(){
	$("#buttonDeleteBlog").on("click",function(){
		var id = document.getElementById("idForDeleteBlog").value;
		var currentPage = document.getElementById("currentPageBlog").value;
		$.ajax({
			url : "/api-admin/blog/v1/blogs",
			type : "DELETE",
			data : {
				id : id
			},
			success : function(data){
				if(data.status == 200){
					alert("success");
					loadDataCurrentPage(currentPage);
					$("#confirmDeleteBlog").modal("hide");
				}	
			},
			error : function(jqXHR,testStatus,errorThrown){
				alert("cannot delete this blog!")
				console.log(jqXHR);
				console.log(testStatus);
				console.log(errorThrown);
			}
		});
	});	
});

function loadPageBlog(total_pages,currentPage){
	$("#paginationBlog").empty();
	for(var i = 0;i<total_pages;i++){
		if(i==0){
			$("#paginationBlog").append('<li class="page-item blogs active"><span class="page-link" onclick="loadDataCurrentPage('+ (i) +');">'+ (i+1) +'</span></li>');
		}else{
			$("#paginationBlog").append('<li class="page-item blogs"><span class="page-link" onclick="loadDataCurrentPage('+ (i) +');">'+ (i+1)+'</span></li>');
		}
	}			
}

function validateBlog(){
	var blogThumbnail = document.getElementById("blogThumbnail");
	var shortDescription = document.getElementById("shortDescription");
	var details = document.getElementById("detailsBlog");
	if(blogThumbnail.value == ""){
		alert("Invalid name");
		return false;
	}
	if(shortDescription.value == ""){
		alert("Invalid short description");
		return false;
	}
	if(details.value == ""){
		alert("Invalid details");
		return false;
	}
	return true;
}

var loadBlogFile = function (event) {
    var image = document.getElementById("outputBlog");
    image.src = URL.createObjectURL(event.target.files[0]);
	var uploadedFilePath = $('#blogFile').val();
}

var loadDetailBlogFile = function (event) {
    var image = document.getElementById("detailOutputBlog");
    image.src = URL.createObjectURL(event.target.files[0]);
	var uploadedFilePath = $('#blogFileDetail').val();
}