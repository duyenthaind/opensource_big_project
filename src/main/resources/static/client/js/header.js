    function setHeader(){
        let divLogin = $('#loginDiv')
        let divLogout = $('#logoutDiv')
        let divAdmin = $('#adminDiv')
        let userinfo = JSON.parse(localStorage.userinfo);
        if(userinfo != null){
            divLogin.empty()
            divLogin.html('<a href=""><i class="fa fa-user"></i>' +  userinfo.username + '</a>')
            
            divLogout.empty()
            divLogout.html('<a href="/logout"><i class="fa fa-user"></i> Log out </a>')
            
            if(userinfo.roles.includes('ADMIN')){
                divAdmin.html('<a href="/admin"><i class="fa fa-user"></i> Admin page </a>')
            }
        }
    }

$(function(){

    setHeader()

});