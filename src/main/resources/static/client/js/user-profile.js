$(document).ready(function() {
document.getElementById("udetail").addEventListener("click", function() {
		$("#home1").show();
	});
	document.getElementById("uorder").addEventListener("click", function() {
		$("#home1").hide();
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