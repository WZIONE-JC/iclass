/**
 * Created by yang.tang on 2017/2/22.
 */
var tips = $(".fileerrorTip");
$(function () {
    $("#fileCreator").val($(window.parent.document).find('#usercode')[0].value);
    tips.addClass("error");
    $(".a-upload").on("change","input[type='file']",function(){
        var filePath=$(this).val().toLowerCase();
        if(filePath == "") {
            $(".showFileName").html("").hide();
            tips.addClass("error");
            tips.html("请选择文件!").show();
            return false;
        } else if(filePath.indexOf("ppt")!=-1 || filePath.indexOf("pptx")!=-1
            || filePath.indexOf("doc")!=-1 || filePath.indexOf("docx")!=-1
            || filePath.indexOf("xls")!=-1 || filePath.indexOf("xlsx")!=-1
            || filePath.indexOf("txt")!=-1 || filePath.indexOf("pdf")!=-1){
            var maxsize = 5*1024*1024;//5M
            var errMsg = "上传的附件文件不能超过5M！！！";
            var tipMsg = "您的浏览器暂不支持计算上传文件的大小，确保上传文件不要超过5M，建议使用IE、FireFox、Chrome浏览器。";
            var  browserCfg = {};
            var ua = window.navigator.userAgent;
            if (ua.indexOf("MSIE")>=1){
                browserCfg.ie = true;
            }else if(ua.indexOf("Firefox")>=1){
                browserCfg.firefox = true;
            }else if(ua.indexOf("Chrome")>=1){
                browserCfg.chrome = true;
            }
            var filesize = 0;
            var obj_file = document.getElementById("file");
            if(browserCfg.firefox || browserCfg.chrome ){
                filesize = obj_file.files[0].size;
            }else if(browserCfg.ie){
                filesize = obj_file.value.fileSize;
            }else {
                swal({
                    title: "Sorry!",
                    text: tipMsg,
                    timer: 2000,
                    type: "error"
                });
                return;
            }
            if(filesize==-1){
                swal({
                    title: "Sorry!",
                    text: tipMsg,
                    timer: 2000,
                    type: "error"
                });
                return;
            }else if(filesize>maxsize) {
                tips.html(errMsg).show();
                tips.addClass("error");
                return;
            }
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
            url: ppt_hw_url + '/file/upload' ,
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
                        var $refresh = $(window.parent.document).find('#btn-refresh')[0];
                        if ($refresh != null) {
                            $refresh.click();
                        }
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
            error: function () {
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

