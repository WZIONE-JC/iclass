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
                url: user_url + "/user/getLoginedUserInfo",
                timeout: 3000,
                success: function (logineduserdata) {
                    if (logineduserdata.success) {
                        $("#menu").removeClass("displayNone");
                        var role = logineduserdata.data.user.userrole;
                        if (role == "管理员") {
                            $("#menu-system").removeClass("displayNone");
                        }
                        $("#rolename").text(role);

                        var username = logineduserdata.data.user.username;
                        $("#username").text(username);
                        var usercode = logineduserdata.data.user.usercode;
                        $("#usercode").val(usercode);
                        $("#personalInfo").on("click", function () {
                            member_show(username,'user-show.html','usercode='+usercode,'360','400');
                        });
                    } else {
                        swal({
                            title: "Sorry!",
                            text: logineduserdata.message,
                            timer: 2000,
                            type: "error"
                        });
                    }
                },
                error: function (logineduserdata) {
                    swal({
                        title: "Sorry!",
                        text: "网络繁忙，请稍后再试",
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
                            closeOnCancel: true,
                            showLoaderOnConfirm: true
                        },
                        function (isConfirm) {
                            if (isConfirm) {
                                setTimeout(function() {
                                    $.ajax({
                                        type: "post",
                                        dataType: "jsonp",
                                        jsonp: "callback",
                                        url: user_url + "/user/logout",
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
                                                text: "网络繁忙，请稍后再试",
                                                timer: 2000,
                                                type: "error"
                                            });
                                        }
                                    })}, 1200);
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
