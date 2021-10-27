$(function(){

})

function showModalDeleteProductImage(id, event, index){
    event.preventDefault()
    $("#confirmDeleteProductImage").modal("show")
    $("#idForDeleteProductImage").val(id)
    $("#indexForDeleteProductImage").val(index)

}

function deleteProductImage(){
    let id = $("#idForDeleteProductImage").val()
    let index = "imageProduct" + $("#indexForDeleteProductImage").val()
    let divId = "#" + index
    $.ajax({
        url: "/api/product-image/v1/product-images",
        type: "DELETE",
        data:{
            id: id
        },
        success: function(response){
            if(response.status >= 200 && response.status < 300){
                $("#confirmDeleteProductImage").modal("hide")
                // document.getElementById(index).outerHTML = ""
                $(divId).html("")
                $("#modalUpdateProduct").modal("hide")
            }
        },
        error: function(xhjQr, textStatus, errorMessage){
            alert("Eror alter product image, res: " + textStatus)
            console.log(textStatus, errorMessage)
        }
    })
}

