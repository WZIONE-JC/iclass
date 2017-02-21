/**
 * Created by yang.tang on 2017/2/14.
 */
var Usershow = function () {

    //获取用户信息
    var a = function () {
        $(function () {
            $.ajax({
                type: "post",
                dataType: "jsonp",
                jsonp: "callback",
                url: rurl + "/cache/getCache",
                timeout: 3000,
                success: function (usercache) {
                    $("#userfullname").text(usercache.sessionUser.user.userfullname);
                    $("#userbirth").text(usercache.sessionUser.user.userbirth);
                    $("#usersex").text(usercache.sessionUser.user.usersex);
                    $("#userphone").text(usercache.sessionUser.user.userphonenum);
                    $("#useremail").text(usercache.sessionUser.user.useremail);
                    $("#userregisterdate").text(usercache.sessionUser.user.userregisterdate);
                    $("#userrole").text(usercache.sessionUser.user.userrole);
                },
                error: function (data) {
                    alert("请求缓存信息出错,错误信息:"+data);
                }
            })
        })
    };

    return {
        init: function () {
            a();
        },
    }
}();
