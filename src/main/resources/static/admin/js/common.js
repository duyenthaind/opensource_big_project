function formatDate(current_datetime){
    if(current_datetime == null || isNaN(current_datetime) || current_datetime === 0){
        return null;
    }
    else{
        return current_datetime.getDate() + "-" + (current_datetime.getMonth() + 1) + "-" + current_datetime.getFullYear()
            + " " + current_datetime.getHours() + ":" + current_datetime.getMinutes() + ":" + current_datetime.getSeconds();
    }
}