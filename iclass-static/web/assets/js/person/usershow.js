/**
 * Created by yang.tang on 2017/2/14.
 */
var Usershow = function () {
    //request url
    // var ip = "localhost";
    var ip = "115.159.63.34";
    var port = "8080";
    var rurl = "http://"+ip+":"+port+"/iclass";

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
                    $.ajax({
                        type: "post",
                        dataType: "jsonp",
                        jsonp: "callback",
                        url: rurl + "/user/getUserInfoByUsercode",
                        timeout: 3000,
                        data: {
                            usercode: usercache.usercode
                        },
                        success: function (userdata) {
                            $("#userfullname").text(userdata.userfullname);
                            $("#userbirth").text(userdata.userbirth);
                            $("#usersex").text(userdata.usersex);
                            $("#userphone").text(userdata.userphonenum);
                            $("#useremail").text(userdata.useremail);
                            $("#userregisterdate").text(userdata.userregisterdate);
                            $("#userrole").text(userdata.userrole);
                        },
                        error: function (data) {
                            alert("请求用户信息出错,错误信息:"+data);
                        }
                    })
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
