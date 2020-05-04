/**
 * 遍历给定input数组，如果有内容则改变背景颜色、字体等使突出
 *
 * 入参：inputArray input数组
 *
 */
function changInputColor(inputArray) {
    $.each(inputArray, function(i, n){
        if($(n).val() != '') {
            $(n).css({"background":"#ccffb6","font-weight":"bold","color":"black"});
        }
        else {
            $(n).css("background", '#fff');
        }
    })
}