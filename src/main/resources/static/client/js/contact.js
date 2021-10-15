function addNewContactMessage(event){
    event.preventDefault();
    var form = $('#sendContactMessageForm')[0];

    var data = new FormData(form);

    var rawData = {};

    data.forEach(function(value, key){
        rawData[key]= value;
    });

    if(!rawData["name"] && !rawData["email"]){
        alert("Please fill in at least your name or your email")
        return
    }

    if(!validateEmail(rawData["email"])){
        alert("Please give us a valid email address")
        return
    }

    if(!rawData["message"]){
        alert("Please give us a message")
        return
    }

    $.ajax({
        url:"/api/client_contact/v1/send_contact",
        type: "POST",
        enctype: 'multipart/form-data',
        // dataType: 'json',
        cache: false,
        processData: false,
        contentType: false,
        data: data,
        timeout: 5000,
        success : function(data){
            if(data.status === 200){
                console.log("Send data ok");
                alert("Thanks for your submission, we appreciated that")
            }
        },
        error: function (jqXHR,textStatus,errorThrown){
            console.log(jqXHR);
            console.log(textStatus);
            console.log(errorThrown);
            alert("Cannot send message now");
        }
    });
}

function validateEmail(email) {
    const re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(String(email).toLowerCase());
}