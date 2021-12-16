function subscribeEmail(event){
    event.preventDefault()
    let email = $('#emailSubcriber').val()
    if(!validateEmail(email)){
        alert('Not a valid email')
        return
    }
    let payload = {email: email}
    console.log("entering......")
    console.log(payload)
    $.ajax({
        url: '/v1/api/client-subscriber/subscribers',
        type: 'post',
        data: JSON.stringify(payload),
        processData: false,
        dataType: 'json',
        contentType: 'application/json',
        success: function(response){
            if(response.status >= 200 && response.status < 300){
                alert('Thank you, we will send you the latest new about our products and events')
            }
        },
        error: function(jqXhr, textStatus, errorMessage){
            alert('Error')
            console.log(textStatus,errorMessage)
        }
    })
}

function validateEmail(email){
	if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(email))
	  {
	    return (true)
	  }
	    return (false)
}