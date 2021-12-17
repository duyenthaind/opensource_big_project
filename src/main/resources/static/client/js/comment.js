$(function() {
	var productId = $("#productIdForComment").val();
	if(productId != null || productId != ""){
		loadComment(productId)
	}	
});

function loadComment(productId){
	$("#commentBody").empty();
	$.ajax({
		url : "/v1/api/client-comment/comments?productId="+productId,
		type:"GET",
		dataType: 'json',
        contentType: 'application/json',
		success: function(responseData){
			var data = responseData.result.data;
			var html= "";
				for(var i=0;i<data.length;i++){		
					var html1 = "";
					html1 += '<div class="d-flex flex-row p-3">'
				      +'  <img'
				      +'   src="https://i.imgur.com/3J8lTLm.jpg"'
				      +'    width="40"'
				      +'    height="40"'
				      +'    class="rounded-circle mr-3"'
				        	  +'  />'
				        	  +'  <div class="w-100">'
				        	  +'     <div class="d-flex justify-content-between align-items-center">'
				        	  +'       <div class="d-flex flex-row align-items-center">'
				        	  +'         <span class="mr-2">'+data[i].displayName+'</span>'
				              +'       </div>'
				              +'       <small>2h ago</small>'
				              +'      </div>'
				              +'      <p class="text-justify comment-text mb-0">'
				                  + data[i].message 
				              +'     </p>'
				              +'    <div class="d-flex flex-row user-feed" style="cursor:pointer">'
				              +'     <span class="ml-3" ><i class="fa fa-comments-o mr-2"></i>Reply</span>'
				              +' </div>';
					if(data[i].childComments != null){
						var html2 = "";
						console.log(data[i].childComments.length)
						for(var j=0;j<data[i].childComments.length;i++){
							html2 += '<div class="d-flex flex-row p-3">'
							      +'  <img'
							      +'   src="https://i.imgur.com/3J8lTLm.jpg"'
							      +'    width="40"'
							      +'    height="40"'
							      +'    class="rounded-circle mr-3"'
							        	  +'  />'
							        	  +'  <div class="w-100">'
							        	  +'     <div class="d-flex justify-content-between align-items-center">'
							        	  +'       <div class="d-flex flex-row align-items-center">'
							        	  +'         <span class="mr-2">'+data[i].childComments[j].displayName+'</span>'
							              +'       </div>'
							              +'       <small>2h ago</small>'
							              +'      </div>'
							              +'      <p class="text-justify comment-text mb-0">'
							                  + data[i].childComments[j].message 
							              +'     </p>'
							              +'    <div class="d-flex flex-row user-feed" style="cursor:pointer">'
							              +'     <span class="ml-3" ><i class="fa fa-comments-o mr-2"></i>Reply</span>'
							              +'   </div>'
							              +' </div>'
							              +' </div>';
						}
						html += html1.concat(html2 + '</div></div>');
						console.log(html);
					}else{
						html += html1.concat('</div></div>');
						console.log(html);
					}

				}
				console.log(html);
				$("#commentBody").append(html);
		},
		error : function(jqXhr, textStatus, errorMessage) { // error
			// callback

		}
	});
}

//function loadCommentParent(productId,parentId){
//	var html = "";
//	$.ajax({
//		url : "/v1/api/client-comment//comments-child?productId="+productId+"parentId="+parentId,
//		type:"GET",
//		dataType: 'json',
//        contentType: 'application/json',
//		success: function(responseData){
//			var data = responseData.result.data;
//			
//				for(let i=0;i<data.length;i++){
//					html += '<div class="d-flex flex-row p-3">'
//				      +'  <img'
//				      +'   src="https://i.imgur.com/3J8lTLm.jpg"'
//				      +'    width="40"'
//				      +'    height="40"'
//				      +'    class="rounded-circle mr-3"'
//				        	  +'  />'
//				        	  +'  <div class="w-100">'
//				        	  +'     <div class="d-flex justify-content-between align-items-center">'
//				        	  +'       <div class="d-flex flex-row align-items-center">'
//				        	  +'         <span class="mr-2">'+data[i].displayName+'</span>'
//				              +'       </div>'
//				              +'       <small>2h ago</small>'
//				              +'      </div>'
//				              +'      <p class="text-justify comment-text mb-0">'
//				                  + data[i].message 
//				              +'     </p>'
//				              +'    <div class="d-flex flex-row user-feed" style="cursor:pointer">'
//				              +'     <span class="ml-3" ><i class="fa fa-comments-o mr-2"></i>Reply</span>'
//				              +'   </div>'
//				              +' </div>'
//				              +' </div>'
//				              +' </div>'
//				              +' </div>';
//				}
//		},
//		error : function(jqXhr, textStatus, errorMessage) { // error
//			// callback
//
//		}
//	});
//	return html;
//}




