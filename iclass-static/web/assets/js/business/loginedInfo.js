/**
 * Created by JasonTang on 2/14/2017.
 */
var Logined = function () {

    //获取已登录的用户信息
    var a = function () {
        $(function () {
            $.ajax({
                type: "post",
                dataType: "jsonp",
                jsonp: "callback",
                url: rurl + "/user/getLoginedUserInfo",
                timeout: 3000,
                success: function (logineduserdata) {
                    if (logineduserdata.success) {
                        $("#rolename").text(logineduserdata.data.user.userrole);
                        $("#username").text(logineduserdata.data.user.username);
                    } else {
                        swal({
                            title: "Sorry!",
                            text: "获取用户信息出错，请重试 :" + logineduserdata.message,
                            timer: 2000,
                            type: "error"
                        });
                    }
                },
                error: function (logineduserdata) {
                    swal({
                        title: "Sorry!",
                        text: "请求用户信息出错，请重试 :" + logineduserdata.responseText,
                        timer: 2000,
                        type: "error"
                    });
                }
            })
        })
    };

    //用户注销事件
    var b = function () {
            $("#logout").click(function () {

                    swal({
                            title: "是否注销?",
                            text: "即将注销账户",
                            type: "warning",
                            showCancelButton: true,
                            confirmButtonColor: "#DD6B55",
                            confirmButtonText: "注销",
                            cancelButtonText: "取消",
                            closeOnConfirm: false,
                            closeOnCancel: false,
                            showLoaderOnConfirm: true
                        },
                        function (isConfirm) {
                            if (isConfirm) {
                                setTimeout(function() {
                                    $.ajax({
                                        type: "post",
                                        dataType: "jsonp",
                                        jsonp: "callback",
                                        url: rurl + "/user/logout",
                                        timeout: 3000,
                                        success: function (responseData) {
                                            if (responseData.success) {
                                                swal({
                                                    title: "Good Job!",
                                                    text: "您已经注销成功",
                                                    type: "success",
                                                    showConfirmButton: false,
                                                    timer: 1500
                                                }, function () {
                                                    window.location.href = "login.html";
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
                                        error: function (logineduserdata) {
                                            swal({
                                                title: "Sorry!",
                                                text: "请求用户信息出错，请重试 :" + logineduserdata.responseText,
                                                timer: 2000,
                                                type: "error"
                                            });
                                        }
                                    })}, 1200);
                            } else {
                                swal({
                                        title: "已取消",
                                        text: "已取消注销操作",
                                        type: "info",
                                        timer: 1000,
                                    });
                            }
                        })
                }
            )
        };
    return {
        init: function () {
            a();
            b();
        },
    }
}();
