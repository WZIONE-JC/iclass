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
                url: rurl + "/cache/getCache",
                timeout: 3000,
                success: function (responseData) {
                    if(responseData.success) {
                        $("#userid").val(responseData.data.user.userid);
                        var userfullname = $("#userfullname");
                        userfullname.text(responseData.data.user.userfullname);
                        userfullname.val(responseData.data.user.userfullname);
                        var username = $("#username");
                        username.text(responseData.data.user.username);
                        username.val(responseData.data.user.username);
                        var usercode = $("#usercode");
                        usercode.text(responseData.data.user.usercode);
                        usercode.val(responseData.data.user.usercode);
                        var userbirth = $("#userbirth");
                        userbirth.text(responseData.data.user.userbirth);
                        userbirth.val(responseData.data.user.userbirth);
                        $("#usersex").text(responseData.data.user.usersex);
                        if(responseData.data.user.usersex == "男") {
                            $("#sex-1").parent().addClass("checked").attr("checked","checked");
                        }
                        if(responseData.data.user.usersex == "女") {
                            $("#sex-2").parent().addClass("checked").attr("checked","checked");
                        }
                        var userphonenum = $("#userphonenum");
                        userphonenum.text(responseData.data.user.userphonenum);
                        userphonenum.val(responseData.data.user.userphonenum);
                        var useremail = $("#useremail");
                        useremail.text(responseData.data.user.useremail);
                        useremail.val(responseData.data.user.useremail);
                        $("#userregisterdate").text(responseData.data.user.userregisterdate);
                        var userrole = $("#userrole");
                        userrole.text(responseData.data.user.userrole);
                        userrole.val(responseData.data.user.userrole);
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
