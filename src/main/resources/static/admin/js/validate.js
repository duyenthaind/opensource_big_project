function addNewCate(){
	var data = {};
	data["name"] = $("#cateName").val();
	data["description"] = $('#summernote').summernote('code');
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
					html = "<tr class='tr-shadow'><td>" + dataArr[i].name + "</td><td><span class='block-email'>" + formatDate(new Date(dataArr[i].created_date)) + "</span></td>"
						+	"<td><span class='block-email'>" + formatDate(new Date(dataArr[i].updated_date)) + "</span></td>"
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

$(function(){

	getFirst();

});





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