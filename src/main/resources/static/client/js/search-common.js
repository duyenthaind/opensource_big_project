function parsedConditionBlog(rawData){
    let jsonData = []
    for(data of rawData){
        let condition
        let key
        switch (data.key){
            case 'textSearch':
                key = 'thumbnail'
                condition = LIKE_OPERATOR
                break
            default:
                break;
        }
        jsonData.push({key: key, operator: condition, value: data.value})
    }
    return jsonData
}

const EQUAL_OPERATOR = 1
const NOT_EQUAL_OPERATOR = 2
const GREATER_THAN_OPERATOR = 3
const GREATER_THAN_OR_EQUAL_OPERATOR = 4
const LESS_THEN_OPERATOR = 5
const LESS_THAN_OR_EQUAL_OPERATOR = 6
const LIKE_OPERATOR = 7