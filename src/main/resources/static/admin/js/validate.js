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

function getFirst(){
	$.ajax({
		url:"http://localhost:8080/admin/api/v1/getAll",
		type:"GET",
		data: {
			page:1
		},
		success : function(data){

				var html = "";
				var dataArr = data.result.data;
				for(var i = 0;i < dataArr.length; i++){
					html = "<tr class='tr-shadow'><td>" + dataArr[i].name + "</td><td><span class='block-email'>" + dataArr[i].createdDate + "</span></td>"
						+	"<td class='desc'>" + dataArr[i].updatedDate + "</td>"
						+	"<td>" + dataArr[i].createdBy + "</td>"
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

	var page = 1;
	var total_pages = 4;
	var total = 0;

	getFirst();

	$(".prev-btn").on("click",function(){
		if(page > 0){
			page--;
		}
		console.log(total);
		console.log("Prev page: " + page);
	});
	$(".next-btn").on("click",function(){
		if(page < total_pages){
			page++;
		}
		console.log(total);
		console.log("Next page: " + page);
	});

	for(var i = 0;i<total_pages;i++){
		console.log("hi");
		if(i==0){
			$(".pagination").append('<li class="page-item active"><span class="page-link" onclick="loadData('+ (i+1) +');">'+ (i+1) +'</span></li>');
		}else{
			$(".pagination").append('<li class="page-item"><span class="page-link" onclick="loadData('+ (i+1) +');">'+ (i+1)+'</span></li>');
		}
	}
});





function loadData(currentPage){
		$.ajax({
		url:"https://reqres.in/api/users",
		type:"GET",
		data: {
			page:currentPage
		},
		success : function(data){

				var html = "";
				$(".result").empty();
				var dataArr = data.data;
				for(var i = 0;i < dataArr.length; i++){
					console.log(dataArr[i].email);
					html = "<div class='sample-user'>" + 
						"<h3>ID: " + dataArr[i].email + "</h3>" 
						+ "</div>";

						$(".result").append(html);
				}				
		},
		error : function(jqXHR,testStatus,errorThrown){
			console.log(jqXHR);
			console.log(testStatus);
			console.log(errorThrown);
		}
	});
	}