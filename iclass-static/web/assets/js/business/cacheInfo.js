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
                success: function (responseData) {
                    if(responseData.success) {
                        $("#userfullname").text(responseData.data.user.userfullname);
                        $("#userbirth").text(responseData.data.user.userbirth);
                        $("#usersex").text(responseData.data.user.usersex);
                        $("#userphone").text(responseData.data.user.userphonenum);
                        $("#useremail").text(responseData.data.user.useremail);
                        $("#userregisterdate").text(responseData.data.user.userregisterdate);
                        $("#userrole").text(responseData.data.user.userrole);
                    } else {
                        swal({
                            title: "Sorry!",
                            text: responseData.message,
                            timer: 2000,
                            type: "error"
                        });
                    }
                },
                error: function (data) {
                    swal({
                        title: "Sorry!",
                        text: "请求获取缓存信息出错,错误信息:"+data.responseText,
                        timer: 2000,
                        type: "error"
                    });
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
