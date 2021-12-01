$(document).ready(function() {
document.getElementById("udetail").addEventListener("click", function() {
		$("#home1").show();
	});
	document.getElementById("uorder").addEventListener("click", function() {
		$("#home1").hide();
		$("#orderTableUser").DataTable({
			
		});
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


