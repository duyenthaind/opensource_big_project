
(function () {
	loadFirstOrder()
})();

function checkStatus(orderStatus){	
	if(orderStatus == 2){
		return "Approved";
	}if(orderStatus == 3){
		return "Getting item";
	}if(orderStatus == 5){
		return "Item is being delivered";
	}if(orderStatus == 6){
		return "Order is completed";
	}
	return "Not approved yet";
}


function loadOrderDataPage(page){
	$.ajax({
		url : "/checkout/v1/api/checkouts",
		type:"GET",
		data:{
			page:page,
		},
	    contentType: "applicaton/json",
		success : function(jsonResult){
			$("#dataOrderTableUser").empty();
			$("#pageOrder").empty();
			var html = "";
			var page = "";
			var data = jsonResult.result.data;
			var totalPages = jsonResult.result.total_pages;
			if(jsonResult.status == 200){
				for(var j=0;j<totalPages;j++){
					page += '<a style="cursor:pointer;" onclick="loadOrderDataPage('+ totalPages[j] +')">'+j+'</a>';
				}
				for(var i=0;i<data.length;i++){
					if(data[i].orderStatus < 3){
						html += '<tr>'
							+'<td>'+ data[i].code_name +'</td>'
							+'<td>'+ data[i].created_date +'</td>'
							+'<td>'+ data[i].customer_name +'</td>'		
							+'<td>'+ checkStatus(data[i].orderStatus) +'</td>'		
							+'<td width="100px"><button onClick="detailOrder(' + data[i].id + ')" type="button" class="btn btn-primary"><i class="fa fa-info" aria-hidden="true"></i></button>  <button onClick="deleteOrder('+data[i].id+')"  type="button" class="btn btn-danger"><i class="fa fa-trash" aria-hidden="true"></i></button></td>'
							+'</tr>';
					}else{
						html += '<tr>'
							+'<td>'+ data[i].code_name +'</td>'
							+'<td>'+ data[i].created_date +'</td>'
							+'<td>'+ data[i].customer_name +'</td>'	
							+'<td>'+ checkStatus(data[i].orderStatus) +'</td>'	
							+'<td width="100px"><button onClick="detailOrder(' + data[i].id + ')" type="button" class="btn btn-primary"><i class="fa fa-info" aria-hidden="true"></i></button></td>'
							+'</tr>';
					}
					
				}
				$("#dataOrderTableUser").html(html);
				$("#pageOrder").html(page);
			}
		},
		error : function(jqXhr, textStatus, errorMessage) { // error
			// callback
		} 
	});
}

function detailOrder(orderId){
	$.ajax({
		url : "/checkout/v1/api/checkouts/"+orderId,
		type:"GET",
		contentType: "applicaton/json",
		success : function(jsonResult){
			$("#orderDetail").empty();
			var html = "";
			var data = jsonResult.result.data[0].listProductDto;
			var totalPages = jsonResult.result.total_pages;
			if(jsonResult.status == 200){
				for(var i=0;i<data.length;i++){
					html += '<tr>'
							+'<td>'+ data[i].name +'</td>'
							+'<td>'+ data[i].quantity +'</td>'
							+'<td>'+ data[i].price +'</td>'		
							+'<td>'+ data[i].quantity*data[i].price +'</td>'
							+'</tr>';
			}
			$("#orderDetail").html(html);
			$("#detailOrder").modal("show");
		}
	},
	error : function(jqXhr, textStatus, errorMessage) { // error
		// callback
	} 
	});
}

function deleteOrder(orderId){
	$.ajax({
		url : "/checkout/v1/api/checkouts/"+orderId,
		type:"DELETE",
		contentType: "applicaton/json",
		success : function(jsonResult){
			if(jsonResult.status == 200){
				setDialog("Delete order success");
				loadFirstOrder();
			}else{
				setDialog("Failure");
			}
			
		},
		error : function(jqXhr, textStatus, errorMessage) { // error
			// callback
			setDialog("Failure");
		} 
});
}

$("#pageOrder").hide();
$("#orderTableUser").hide();

function loadFirstOrder(){
	$.ajax({
		url : "/checkout/v1/api/checkouts",
		type:"GET",
	    contentType: "applicaton/json",
		success : function(jsonResult){
			$("#dataOrderTableUser").empty();
			$("#pageOrder").empty();
			var html = "";
			var page = "";
			var data = jsonResult.result.data;
			var totalPages = jsonResult.result.total_pages;
			if(jsonResult.status == 200){
				for(var j=0;j<totalPages;j++){
					page += '<a style="cursor:pointer;" onclick="loadOrderDataPage('+ totalPages[j] +')">'+j+'</a>';
				}
				for(var i=0;i<data.length;i++){
					if(data[i].orderStatus < 3){
						html += '<tr>'
							+'<td>'+ data[i].code_name +'</td>'
							+'<td>'+ data[i].created_date +'</td>'
							+'<td>'+ data[i].customer_name +'</td>'		
							+'<td>'+ checkStatus(data[i].orderStatus) +'</td>'		
							+'<td width="100px"><button onClick="detailOrder(' + data[i].id + ')" type="button" class="btn btn-primary"><i class="fa fa-info" aria-hidden="true"></i></button>  <button onClick="deleteOrder('+data[i].id+')" type="button" class="btn btn-danger"><i class="fa fa-trash" aria-hidden="true"></i></button></td>'
							+'</tr>';
					}else{
						html += '<tr>'
							+'<td>'+ data[i].code_name +'</td>'
							+'<td>'+ data[i].created_date +'</td>'
							+'<td>'+ data[i].customer_name +'</td>'	
							+'<td>'+ checkStatus(data[i].orderStatus) +'</td>'	
							+'<td width="100px"><button onClick="detailOrder(' + data[i].id + ')" type="button" class="btn btn-primary"><i class="fa fa-info" aria-hidden="true"></i></button></td>'
							+'</tr>';
					}
					
				}
				$("#dataOrderTableUser").html(html);
				$("#pageOrder").html(page);
			}
		},
		error : function(jqXhr, textStatus, errorMessage) { // error
			// callback
		} 
	});
}

$(document).ready(function() {
	
	document.getElementById("udetail").addEventListener("click", function() {
		$("#home1").show();
		$("#orderTableUser").hide();
		$("#pageOrder").hide();
	});
	document.getElementById("uorder").addEventListener("click", function() {
		$("#home1").hide();
		$("#orderTableUser").show(); 
		$("#pageOrder").show();
	});
	
	
	
    
    var readURL = function(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();

            reader.onload = function (e) {
                $('.avatar').attr('src', e.target.result);
            }
    
            reader.readAsDataURL(input.files[0]);
        }
    }
    

    $(".file-upload").on('change', function(){
        readURL(this);
    });
});

function updateUser(event){
	event.preventDefault();
	let userinfo = JSON.parse(localStorage.userinfo);
	var form = $('#updateForm')[0];
	
    var data = new FormData(form);
    data.append('userName',userinfo.username);
    data.append("userid",$('#userid').val());
    data.append('username',$("#username").val());
		$.ajax({
			url : "/v1/api/users/users",
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
					console.log(jsonResult);
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


