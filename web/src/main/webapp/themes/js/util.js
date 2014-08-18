/**
 * Created by ijaz on 16/8/14.
 */

function createSpan(elemText, className, id){
    return $('<span/>', {
        class: className,
        html: elemText,
        id: id
    })
}

function createDiv(className){
    return  $('<div/>', {
        class: className
    })
}
