/**
 * Created by JasonTang on 5/16/2017.
 */
var teacherCode;
//获取首页信息
$(function () {
    setTimeout(function () {
        teacherCode = $(window.parent.document).find('#usercode')[0].value;
        $.ajax({
            type: "post",
            dataType: "jsonp",
            jsonp: "callback",
            url: ppt_hw_url + "/welcome/get",
            data: {
                "userCode" : teacherCode
            },
            timeout: 10000,
            beforeSend: function () {
                if (teacherCode == null || teacherCode.trim() == "") {
                    return false;
                }
            },
            success: function (responseData) {
                if (responseData.success) {
                    var data = responseData.data;
                    var classCourseNum = data.classCourseNum;
                    var classNum = data.classNum;
                    var courseNum = data.courseNum;
                    var pptNum = data.pptNum;
                    var hwNum = data.hwNum;
                    $("#classCourseNum").text(classCourseNum);
                    $("#classNum").text(classNum);
                    $("#courseNum").text(courseNum);
                    $("#pptNum").text(pptNum);
                    $("#hwNum").text(hwNum);
                    if (classCourseNum != 0) {
                        $("#classCourseNum").addClass("label-success");
                    } else {
                        $("#classCourseNum").removeClass("label-success");
                    }
                    if (classNum != 0) {
                        $("#classNum").addClass("label-warning");
                    } else {
                        $("#classNum").removeClass("label-warning");
                    }
                    if (courseNum != 0) {
                        $("#courseNum").addClass("label-primary");
                    } else {
                        $("#courseNum").removeClass("label-primary");
                    }
                    if (pptNum != 0) {
                        $("#pptNum").addClass("label-danger");
                    } else {
                        $("#pptNum").removeClass("label-danger");
                    }
                    if (hwNum != 0) {
                        $("#hwNum").addClass("label-secondary");
                    } else {
                        $("#hwNum").removeClass("label-secondary");
                    }
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
                    text: "网络繁忙，请稍后再试",
                    timer: 2000,
                    type: "error"
                });
            }
        });
    },300);
});

