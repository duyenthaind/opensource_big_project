    function setHeader(){
    	let accountDiv = $('#accountDiv');
    	let selectionDiv = $('#selectionDiv');
        let userinfo = JSON.parse(localStorage.userinfo);
        if(userinfo != null){
        	$('#loginDiv').hide()
        	$('#logoutDiv').hide()
        	selectionDiv.empty();
            selectionDiv.html('<a class="dropdown-item" href="/user-profile?username='+ userinfo.username +'"><i class="fa fa-user"></i> Your account</a><a class="dropdown-item" href="/logout" onClick="logout()"><i class="fa fa-exclamation"></i> Logout</a>');
            
            accountDiv.html(userinfo.username);
            
            if(userinfo.roles.includes('ADMIN')){
            	selectionDiv.html('<a class="dropdown-item" href="/user-profile?username='+ userinfo.username +'"><i class="fa fa-user"></i> Your account</a><a class="dropdown-item" href="/admin"><i class="fa fa-user-secret"></i> Admin page</a><a class="dropdown-item" href="/logout" onClick="logout()"><i class="fa fa-exclamation"></i> Logout</a>')
            }
        }else{
        	$('#validateAccount').hide();
        }
    }

    function getCookie(cname) {
    	  let name = cname + "=";
    	  let decodedCookie = decodeURIComponent(document.cookie);
    	  let ca = decodedCookie.split(';');
    	  for(let i = 0; i <ca.length; i++) {
    	    let c = ca[i];
    	    while (c.charAt(0) == ' ') {
    	      c = c.substring(1);
    	    }
    	    if (c.indexOf(name) == 0) {
    	      return c.substring(name.length, c.length);
    	    }
    	  }
    	  return "";
    	}
    
function logout(){
	 
    if(localStorage.userinfo != null && getCookie("accessToken") != null){
    	document.cookie = "accessToken=";
        localStorage.userinfo = null;
    }
    window.location.href = "/logout";
}    
    
$(function(){

    setHeader()

});