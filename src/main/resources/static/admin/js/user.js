function getFirstListUser(){
//	document.getElementById("currentPageCate").value = 0;
	$.ajax({
		url:"/api-admin/v1/user/users",
		type:"GET",
		data: {
			page:0
		},
		success : function(data){
				loadPageuser(data.result.total_pages,0);
				$(".userTable").empty();
				var html = "";
				var properties = "";
				var dataArr = data.result.data;
				for(var i = 0;i < dataArr.length; i++){
					if(dataArr[i].role === "ADMIN"){
						properties = '<td><span class="role admin">' + dataArr[i].role +  '</span></td>'
					}else if(dataArr[i].role === "CLIENT"){
						properties ='<td><span class="role user">' + dataArr[i].role +  '</span></td>'
					}else{
						properties ='<td><span class="role member">' + dataArr[i].role +  '</span></td>'
					}
					html += '<tr>'
									+'	<td>'
									+'	<div class="table-data__info">'
									+'	<h6>' + dataArr[i].name +  '</h6>'
									+'	<span> <a href="#">' + dataArr[i].email +  '</a>'
									+'		</span>'
									+'	</div>'
									+'</td>'
									+ properties
									+'<td>'
									+'	<div class="rs-select2--trans rs-select2--sm">'
									+'		<select class="js-select2"  onchange="updateRole('+ dataArr[i].id +', \'' + dataArr[i].role + '\' ,this)" name="property">'
									+'			<option selected="selected">' + dataArr[i].role + '</option>'
									+'			<option value="SUPER_ADMIN">SUPER_ADMIN</option>'
									+'			<option value="ADMIN">ADMIN</option>'
									+'			<option value="CLIENT">CLIENT</option>'
									+'		</select>'
									+'		<div class="dropDownSelect2"></div>'
									+'	</div>'
									+'</td>'
									+'<td><span class="more"> <i class="zmdi zmdi-more"></i>'
									+'</span></td>'
									+'</tr>';
				}				
				$(".userTable").append(html);
		},
		error : function(jqXHR,testStatus,errorThrown){
			console.log(jqXHR);
			console.log(testStatus);
			console.log(errorThrown);
		}
	});
}

function loadDataUser(currentPage){
	$.ajax({
		url:"/api-admin/v1/user/users",
		type:"GET",
		data: {
			page:currentPage
		},
		success : function(data){
				loadPageuser(data.result.total_pages,currentPage);
				var tag = document.getElementsByClassName("users");
				for(a =0;a<tag.length;a++){
					tag[a].className = tag[a].className.replace("users active", "users");
				}
				console.log(currentPage);
				tag[currentPage].className = "page-item users active";
				$(".userTable").empty();
				var html = "";
				var properties = "";
				var dataArr = data.result.data;
				for(var i = 0;i < dataArr.length; i++){
					if(dataArr[i].role === "ADMIN"){
						properties = '<td><span class="role admin">' + dataArr[i].role +  '</span></td>'
					}else if(dataArr[i].role === "CLIENT"){
						properties ='<td><span class="role user">' + dataArr[i].role +  '</span></td>'
					}else{
						properties ='<td><span class="role member">' + dataArr[i].role +  '</span></td>'
					}
					html += '<tr>'
									+'	<td>'
									+'	<div class="table-data__info">'
									+'	<h6>' + dataArr[i].name +  '</h6>'
									+'	<span> <a href="#">' + dataArr[i].email +  '</a>'
									+'		</span>'
									+'	</div>'
									+'</td>'
									+ properties
									+'<td>'
									+'	<div class="rs-select2--trans rs-select2--sm">'
									+'		<select class="js-select2"  onchange="updateRole('+ dataArr[i].id +', \'' + dataArr[i].role + '\' ,this)" name="property">'
									+'			<option selected="selected" value="' + dataArr[i].role + '">' + dataArr[i].role + '</option>'
									+'			<option value="SUPER_ADMIN">SUPER_ADMIN</option>'
									+'			<option value="ADMIN">ADMIN</option>'
									+'			<option value="CLIENT">CLIENT</option>'
									+'		</select>'
									+'		<div class="dropDownSelect2"></div>'
									+'	</div>'
									+'</td>'
									+'<td><span class="more"> <i class="zmdi zmdi-more"></i>'
									+'</span></td>'
									+'</tr>';
				}				
				$(".userTable").append(html);
		},
		error : function(jqXHR,testStatus,errorThrown){
			console.log(jqXHR);
			console.log(testStatus);
			console.log(errorThrown);
		}
	});
}

function updateRole(id,oldRole,selectObject) {
	  var value = selectObject.value;  
	  console.log(value);
	  console.log(id);
	  console.log(oldRole);
	  
	  $.ajax({
			url:"/api-super/v1/users/users",
			method: 'put',
			data: JSON.stringify({'userId' : id,
				'oldRole' : oldRole,
				'newRole' : value}),
	        contentType: 'application/json',
			success : function(data){
				loadDataUser(document.getElementById("currentPageUser").value);
					alert("success");				
			},
			error : function(jqXHR,testStatus,errorThrown){
				alert("cannot change!")
				console.log(jqXHR);
				console.log(testStatus);
				console.log(errorThrown);
			}
		});
	  
	}

function loadPageuser(total_pages,currentPage){
	document.getElementById("currentPageUser").value = currentPage;
	$("#paginationUser").empty();
	for(var i = 0;i<total_pages;i++){
		if(i==0){
			$("#paginationUser").append('<li class="page-item users active"><span class="page-link" onclick="loadDataUser('+ (i) +');">'+ (i+1) +'</span></li>');
		}else{
			$("#paginationUser").append('<li class="page-item users"><span class="page-link" onclick="loadDataUser('+ (i) +');">'+ (i+1)+'</span></li>');
		}
	}			
}

$(function(){
	getFirstListUser();
});