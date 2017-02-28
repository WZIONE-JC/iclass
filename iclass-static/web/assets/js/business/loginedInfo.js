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
                    if(logineduserdata.success) {
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
    // jQuery191031458320198317424_1487004197538({code: "1004", msg: "工号可用", data: null});
    // jQuery19109494609197273987_1487004113222({username: "管理员", usercode: "1308030331", userrole: "管理员"})

    return {
        init: function () {
            a();
        },
    }
}();
