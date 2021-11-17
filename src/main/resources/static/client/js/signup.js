
(function ($) {
    "use strict";

    
    /*==================================================================
    [ Validate ]*/
    var input = $('.validate-input .input100');

    $('.validate-form').on('submit',function(){
        var check = true;

        for(var i=0; i<input.length; i++) {
            if(validate(input[i]) == false){
                showValidate(input[i]);
                check=false;
            }
        }

        return check;
    });


    $('.validate-form .input100').each(function(){
        $(this).focus(function(){
           hideValidate(this);
        });
    });

    function validate (input) {
        if($(input).attr('type') == 'email' || $(input).attr('name') == 'email') {
            if($(input).val().trim().match(/^([a-zA-Z0-9_\-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([a-zA-Z0-9\-]+\.)+))([a-zA-Z]{1,5}|[0-9]{1,3})(\]?)$/) == null) {
                return false;
            }
        }
        else {
            if($(input).val().trim() == ''){
                return false;
            }
        }
    }

    function showValidate(input) {
        var thisAlert = $(input).parent();

        $(thisAlert).addClass('alert-validate');
    }

    function hideValidate(input) {
        var thisAlert = $(input).parent();

        $(thisAlert).removeClass('alert-validate');
    }
    
    

})(jQuery);

function performSignup(event){
    event.preventDefault();
    var form = $("#signupForm")[0];
    var data = new FormData(form);
    $.ajax({
        url:'/v1/api/users/perform_signup',
        method: 'post',
        data: data,
        processData: false,
	    contentType: false,
	    cache: false,
	    timeout: 10000,
        success: function(response){
            if(response.status >= 200 && response.status < 300){
            	alert("Success, please login!")
                window.location.href = "/login";
            }
        }, error: function(xhjr, textStatus, errorMessage){
        	window.location.href = "/signup?signup_error=true";
        }
    })
}