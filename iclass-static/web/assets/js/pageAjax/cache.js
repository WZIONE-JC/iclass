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
                    $("#userfullname").text(usercache.user.userfullname);
                    $("#userbirth").text(usercache.user.userbirth);
                    $("#usersex").text(usercache.user.usersex);
                    $("#userphone").text(usercache.user.userphonenum);
                    $("#useremail").text(usercache.user.useremail);
                    $("#userregisterdate").text(usercache.user.userregisterdate);
                    $("#userrole").text(usercache.user.userrole);
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
