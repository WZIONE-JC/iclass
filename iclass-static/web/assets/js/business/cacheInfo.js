/**
 * Created by yang.tang on 2017/2/14.
 */
var GetCache = function () {

    //获取用户信息
    var a = function () {
        $(function () {
            $.ajax({
                type: "post",
                dataType: "jsonp",
                jsonp: "callback",
                url: user_url + "/cache/getCache",
                timeout: 10000,
                success: function (responseData) {
                    if(responseData.success) {
                        var data = responseData.data;
                        if (data == null) {
                            return;
                        } else {
                            data = responseData.data.user;
                        }
                        $("#userid").val(data.userid);
                        var userfullname = $("#userfullname");
                        userfullname.text(data.userfullname);
                        userfullname.val(data.userfullname);
                        var username = $("#username");
                        username.text(data.username);
                        username.val(data.username);
                        var usercode = $("#usercode");
                        usercode.text(data.usercode);
                        usercode.val(data.usercode);
                        var userbirth = $("#userbirth");
                        userbirth.text(data.userbirth);
                        userbirth.val(data.userbirth);
                        $("#usersex").text(data.usersex);
                        if(data.usersex == "男") {
                            $("#sex-1").parent().addClass("checked").attr("checked","checked");
                        }
                        if(data.usersex == "女") {
                            $("#sex-2").parent().addClass("checked").attr("checked","checked");
                        }
                        var userphonenum = $("#userphonenum");
                        userphonenum.text(data.userphonenum);
                        userphonenum.val(data.userphonenum);
                        var useremail = $("#useremail");
                        useremail.text(data.useremail);
                        useremail.val(data.useremail);
                        $("#userregisterdate").text(data.userregisterdate);
                        var userrole = $("#userrole");
                        userrole.text(data.userrole);
                        userrole.val(data.userrole);
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
                        text: "网络繁忙，请稍后再试",
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
