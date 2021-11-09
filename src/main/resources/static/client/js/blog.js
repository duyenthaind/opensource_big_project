function searchBlog(event) {
    event.preventDefault();
    let form = $("#searchBlogForm")[0];
    let formData = new FormData(form)
    let rawData = {};
    formData.forEach(function (value, key) {
        rawData[key] = value
    });
    rawData['page'] = 0
    console.log(rawData)
    $.ajax({
        url: '/blog/search',
        type: 'get',
        data: {
            page: 0,
            textSearch: rawData['textSearch']
        },
        timeout: 5000,
    })
}

