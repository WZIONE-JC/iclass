/**
 * Created by yang.tang on 2017/2/22.
 */
var tips = $(".fileerrorTip");
$(function () {
    $("#fileCreator").val($(window.parent.document).find('#usercode')[0].value);
    tips.addClass("error");
    $(".a-upload").on("change","input[type='file']",function(){
        var filePath=$(this).val();
        if(filePath == "") {
            $(".showFileName").html("").hide();
            tips.addClass("error");
            tips.html("请选择文件!").show();
            return false;
        } else if(filePath.indexOf("ppt")!=-1 || filePath.indexOf("pptx")!=-1
            || filePath.indexOf("doc")!=-1 || filePath.indexOf("docx")!=-1
            || filePath.indexOf("xls")!=-1 || filePath.indexOf("xlsx")!=-1
            || filePath.indexOf("txt")!=-1){
            tips.html("").hide();
            var arr=filePath.split('\\');
            var fileName=arr[arr.length-1];
            $(".showFileName").html(fileName).show();
            tips.removeClass("error");
        }else{
            $(".showFileName").html("").hide();
            tips.addClass("error");
            tips.html("您上传文件类型有误！").show();
            return false;
        }
    });
})
function uploadfile(form, fileType){
    if(!$(".fileerrorTip").hasClass("error")) {
        var formData = new FormData($( form )[0]);
        $.ajax({
            url: ppt_hw_rurl + '/file/upload' ,
            type: 'POST',
            data: formData,
            cache: false,
            contentType: false,
            processData: false,
            success: function (responseData) {
                if(responseData.success) {
                    swal({
                        title: "Good Job!",
                        text: responseData.msg,
                        timer: 2000,
                        showConfirmButton: false,
                        type: "success"
                    }, function () {
                        $(window.parent.document).find('#btn-refresh')[0].click();
                        $("#uploadCloseButton").click();
                    });
                } else {
                    swal({
                        title: "Sorry!",
                        text: responseData.message,
                        timer: 2000,
                        type: "error"
                    });
                }
            },
            error: function (returndata) {
                swal({
                    title: "Sorry!",
                    text: "网络繁忙,请稍后再试",
                    timer: 2000,
                    type: "error"
                });
            }
        });
        $(".showFileName").html("").hide();
    } else {
        tips.html("您未上传文件，或者您上传文件类型有误！").show();
    }
}

