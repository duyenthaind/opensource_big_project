$(function() {
	getFirstOrderPage();
	getFirstDoneOrderPage();
});

function getFirstDoneOrderPage() {
	$.ajax({
		url : "/api-admin/checkout/v1/orders-orderstatus",
		type : "GET",
		data : {
			page : 0,
			orderStatus:5
		},
		success : function(data) {
			loadPageDoneOrder(data.result.total_pages,0)
			$("#doneOrder").empty();
			var html = "";
			var dataArr = data.result.data;
			for (var i = 0; i < dataArr.length; i++) {
				html += '<tr>'
                  +'  <td>'+ dataArr[i].date +'</td>'
                  +'  <td>'+ dataArr[i].code_name +'</td>'
                  +'  <td>$'+ dataArr[i].total +'</td>'
					+		'<td><div class="table-data-feature">'
					+			'<button class="item linkDetail" onclick="detailOrderProduct('+ dataArr[i].id +');" data-toggle="tooltip"'
					+				'data-placement="top" title="Detail">'
					+				'<i class="zmdi zmdi-receipt"></i>'
					+			'</button>'
					+		'</div></td>'
                  +'  </tr>';
			}
			$("#doneOrder").append(html);
		},
		error : function(jqXHR, testStatus, errorThrown) {
			console.log(jqXHR);
			console.log(testStatus);
			console.log(errorThrown);
		}
	});
}

function loadDoneOrderPage(page) {
	document.getElementById("currentPageDoneOrder").value = page;
	 $("#doneOrder").empty();
	$.ajax({
		url : "/api-admin/checkout/v1/orders-orderstatus",
		type : "GET",
		data : {
			page : 0,
			orderStatus:5
		},
		success : function(data) {
			loadPageDoneOrder(data.result.total_pages,page)
			var tag = document.getElementsByClassName("done-order");
			for(a =0;a<tag.length;a++){
				tag[a].className = tag[a].className.replace("done-order active", "done-order");
			}
			tag[page].className = "page-item done-order active";
			$("#doneOrder").empty();
			var html = "";
			var dataArr = data.result.data;
			for (var i = 0; i < dataArr.length; i++) {
				html += '<tr>'
                  +'  <td>'+ dataArr[i].date +'</td>'
                  +'  <td>'+ dataArr[i].code_name +'</td>'
                  +'  <td>$'+ dataArr[i].total +'</td>'
					+		'<td><div class="table-data-feature">'
					+			'<button class="item linkDetail" onclick="detailOrderProduct('+ dataArr[i].id +');" data-toggle="tooltip"'
					+				'data-placement="top" title="Detail">'
					+				'<i class="zmdi zmdi-receipt"></i>'
					+			'</button>'
					+		'</div></td>'
                  +'  </tr>';
			}
			$("#doneOrder").append(html);
		},
		error : function(jqXHR, testStatus, errorThrown) {
			console.log(jqXHR);
			console.log(testStatus);
			console.log(errorThrown);
		}
	});
}

function loadPageDoneOrder(total_pages,currentPage){
	$("#paginationDoneOrder").empty();
	for(var i = 0;i<total_pages;i++){
		if(i==0){
			$("#paginationDoneOrder").append('<li class="page-item done-order active"><span class="page-link" onclick="loadDataDoneOrder('+ (i) +');">'+ (i+1) +'</span></li>');
		}else{
			$("#paginationDoneOrder").append('<li class="page-item done-order"><span class="page-link" onclick="loadDataDoneOrder('+ (i) +');">'+ (i+1)+'</span></li>');
		}
	}			
}

function getFirstOrderPage() {
	// document.getElementById("currentPageBlog").value = 0;
	$.ajax({
		url : "/api-admin/checkout/v1/orders",
		type : "GET",
		data : {
			page : 0
		},
		success : function(data) {
			loadPageOrder(data.result.total_pages,0)
			$("#check-order").empty();
			var html = "";
			var dataArr = data.result.data;
			for (var i = 0; i < dataArr.length; i++) {
				html += '<tr>' + '<td>'+ dataArr[i].date +'</td>' + '<td>'
						+ dataArr[i].code_name + '</td>' + '<td>'
						+ validatePaymentStatus(dataArr[i].isPrepaid) + '</td>'
						+'<td>'
						+'	<div class="rs-select2--trans rs-select2--sm">'
						+'		<select class="js-select2"  onchange="updateOrderStatus('+ dataArr[i].id +', '+ dataArr[i].isPrepaid +',this)" name="property">'
						+'			<option selected="selected">' + validateOrderStatus(dataArr[i].orderStatus) + '</option>'
						+'			<option value="3">GETTING_GOODS</option>'
						+'			<option value="4">DELIVERING</option>'
						+'			<option value="5">COMPLETED</option>'
						+'		</select>'
						+'		<div class="dropDownSelect2"></div>'
						+'	</div>'
						+'</td>'
						+ '<td>$'+ dataArr[i].total +'</td>' 
						+		'<td><div class="table-data-feature">'
						+			'<button class="item linkDetail" onclick="detailOrderProduct('+ dataArr[i].id +');" data-toggle="tooltip"'
						+				'data-placement="top" title="Detail">'
						+				'<i class="zmdi zmdi-receipt"></i>'
						+			'</button>'
						+			"<button class='item' data-toggle='tooltip' onclick='showModalDeleteOrder(" + dataArr[i].id + ")'"
						+				"data-placement='top' title='Delete'>"
						+				"<i class='zmdi zmdi-delete'></i>"
						+			"</button>"
						+		'</div></td>'
						+ '</tr>';
			}
			$("#check-order").append(html);
		},
		error : function(jqXHR, testStatus, errorThrown) {
			console.log(jqXHR);
			console.log(testStatus);
			console.log(errorThrown);
		}
	});
}

function loadDataOrder(page) {
	 document.getElementById("currentPageOrder").value = page;
	 $("#check-order").empty();
	$.ajax({
		url : "/api-admin/checkout/v1/orders",
		type : "GET",
		data : {
			page : page
		},
		success : function(data) {
			loadPageOrder(data.result.total_pages,page)
			var tag = document.getElementsByClassName("order");
			for(a =0;a<tag.length;a++){
				tag[a].className = tag[a].className.replace("order active", "order");
			}
			tag[page].className = "page-item order active";
			
			var html = "";
			var dataArr = data.result.data;
				for (var i = 0; i < dataArr.length; i++) {
					html += '<tr>' + '<td>2018-09-29 05:57</td>' + '<td>'
							+ dataArr[i].code_name + '</td>' + '<td>'
							+ validatePaymentStatus(dataArr[i].isPrepaid) + '</td>'
							+'<td>'
							+'	<div class="rs-select2--trans rs-select2--sm">'
							+'		<select class="js-select2"  onchange="updateOrderStatus('+ dataArr[i].id +', '+ dataArr[i].isPrepaid +',this)" name="property">'
							+'			<option selected="selected">' + validateOrderStatus(dataArr[i].orderStatus) + '</option>'
							+'			<option value="3">GETTING_GOODS</option>'
							+'			<option value="4">DELIVERING</option>'
							+'			<option value="5">COMPLETED</option>'
							+'		</select>'
							+'		<div class="dropDownSelect2"></div>'
							+'	</div>'
							+'</td>'
							+ '<td>$'+ dataArr[i].total +'</td>' 

							+		'<td><div class="table-data-feature">'
							+			'<button class="item linkDetail" onclick="detailOrderProduct('+ dataArr[i].id +');" data-toggle="tooltip"'
							+				'data-placement="top" title="Detail">'
							+				'<i class="zmdi zmdi-receipt"></i>'
							+			'</button>'
							+			"<button class='item' data-toggle='tooltip' onclick='showModalDeleteOrder(" + dataArr[i].id + ")'"
							+				"data-placement='top' title='Delete'>"
							+				"<i class='zmdi zmdi-delete'></i>"
							+			"</button>"
							+		'</div></td>'
							+ '</tr>';
				}
				$("#check-order").append(html);
			
		},
		error : function(jqXHR, testStatus, errorThrown) {
			console.log(jqXHR);
			console.log(testStatus);
			console.log(errorThrown);
		}
	});
}

function detailOrderProduct(orderId){
	$("#orderProduct").modal("show");
	$("#listOrderProduct").empty();
	  $.ajax({
			url:"/api-admin/checkout/v1/orders/"+orderId,
			method: 'GET',
	        contentType: 'application/json',
			success : function(responseData){
				var html = "";
				var data = responseData.result.data[0].listProductDto;
				var info = responseData.result.data[0];
				$("#orderCustomerName").html(info.customer_name);
				$("#orderPhone").html(info.customer_phone);
				$("#orderAddress").html(info.customer_address);
				for(var i = 0;i<data.length;i++){
					html += '<tr>'
						+'<td>'+ data[i].productId +'</td>'
					    +'<td>'+ data[i].name +'</td>'
					    +'<td>'+ data[i].quantity +'</td>'
					    +'<td>'+ data[i].price*data[i].quantity +'</td>'
					    +' </tr>';
				}
				$("#listOrderProduct").append(html);
			},
			error : function(jqXHR,testStatus,errorThrown){
				console.log(jqXHR);
				console.log(testStatus);
				console.log(errorThrown);
			}
		});
}

function updateOrderStatus(orderId,isPrepaid,orderStatus){
	 var value = orderStatus.value;
	  $.ajax({
			url:"/api-admin/checkout/v1/orders",
			method: 'put',
			data: JSON.stringify({
				'orderId' : orderId,
				'isPrepaid' : isPrepaid,
				'orderStatus' : value 
			}),
	        contentType: 'application/json',
			success : function(data){
				loadDataOrder(document.getElementById("currentPageOrder").value);
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

function showModalDeleteOrder(id){
	$("#confirmDeleteOrder").modal("show");
	document.getElementById("idForDeleteOrder").value = id;
}

$(document).ready(function(){
	$("#buttonDeleteOrder").on("click",function(){
		var id = document.getElementById("idForDeleteOrder").value;
		$.ajax({
			url : "/api-admin/checkout/v1/orders/"+id,
			type : "DELETE",
			success : function(data){
				if(data.status == 200){
					alert("success");
					loadDataOrder(document.getElementById("currentPageOrder").value);
					$("#confirmDeleteOrder").modal("hide");
				}	
			},
			error : function(jqXHR,testStatus,errorThrown){
				alert("cannot delete this order !")
				console.log(jqXHR);
				console.log(testStatus);
				console.log(errorThrown);
			}
		});
	});	
});


function loadPageOrder(total_pages,currentPage){
	$("#paginationOrder").empty();
	for(var i = 0;i<total_pages;i++){
		if(i==0){
			$("#paginationOrder").append('<li class="page-item order active"><span class="page-link" onclick="loadDataOrder('+ (i) +');">'+ (i+1) +'</span></li>');
		}else{
			$("#paginationOrder").append('<li class="page-item order"><span class="page-link" onclick="loadDataOrder('+ (i) +');">'+ (i+1)+'</span></li>');
		}
	}			
}

function validatePaymentStatus(isPrepaid) {
	return isPrepaid == true ? "Paid" : "Haven't paid yet";
}

function validateOrderStatus(orderStatus) {
	if (orderStatus == 2) {
		return "APPROVED";
	}
	if (orderStatus == 3) {
		return "GETTING_GOODS";
	}
	if (orderStatus == 4) {
		return "DELIVERING";
	}
	if (orderStatus == 5) {
		return "COMPLETED";
	}
	return "UNAPPROVED";
}

function validateColorStatus(orderStatus) {
	if (orderStatus  < 3) {
		return "denied";
	}else{
		return "process";
	}
}