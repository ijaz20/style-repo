/**
 * Created by ijaz on 16/8/14.
 */

function createSpan(elemText, className){
    return $('<span/>', {
        class: className,
        html: elemText
    })
}

function createDiv(className){
    return  $('<div/>', {
        class: className
    })
}
