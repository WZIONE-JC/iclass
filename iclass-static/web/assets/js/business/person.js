var PersonMaintenance = function () {
    //获取用户信息
    var a = function () {
        $(function () {
            $.ajax({
                type: "post",
                dataType: "jsonp",
                jsonp: "callback",
                url: rurl + "/user/getUserInfoBySession",
                timeout: 3000,
                success: function (responseData) {
                    // <tr class="text-c" >
                    //     <td><input type="checkbox" value="1" name=""></td>
                    //     <td><span style="cursor:pointer" title="点击个人信息" class="label label-secondary radius" onclick="member_show('张三','entity-show.html','10001','360','400')">小黄瓜</span></td>
                    //     <td>1308030333</td>
                    //     <td>唐洋</td>
                    //     <td>男</td>
                    //     <td>1994-09-05</td>
                    //     <td>1882934777</td>
                    //     <td>361239731@qq.com</td>
                    // <td>2016-06-11 11:11:42</td>
                    // <td class="td-status"><span class="label label-primary radius">学生</span></td>
                    //     <td class="td-manage"><a title="编辑" href="javascript:;" onclick="member_edit('修改信息','entity-add.html','4','','570')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a> <a style="text-decoration:none" class="ml-5" onClick="change_password('修改密码','change-password.html','10001','600','350')" href="javascript:;" title="修改密码"><i class="Hui-iconfont">&#xe63f;</i></a> <a title="删除" href="javascript:;" onclick="member_del(this,'1')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a></td>
                    // </tr>
                    if(responseData.success) {
                        var tr = $("<tr class='text-c'></tr>");
                        var usercode = responseData.data.user.usercode;
                        var td1 = $("<td><input type='checkbox' value='1'></td>");
                        var td2 = $("<td><span style='cursor:pointer' title='查看个人信息' class='label label-secondary radius' onclick=member_show('"+responseData.data.user.username+"','user-show.html','usercode="+usercode+"','360','400')>"+responseData.data.user.username+"</span></td>");
                        var td3 = $("<td>"+usercode+"</td>");
                        var td4 = $("<td>"+responseData.data.user.userfullname+"</td>");
                        var td5 = $("<td>"+responseData.data.user.usersex+"</td>");
                        var td6 = $("<td>"+responseData.data.user.userbirth+"</td>");
                        var td7 = $("<td>"+responseData.data.user.userphonenum+"</td>");
                        var td8 = $("<td>"+responseData.data.user.useremail+"</td>");
                        var td9 = $("<td>"+responseData.data.user.userregisterdate+"</td>");
                        var td10 = $("<td class='td-status'><span class='label label-primary radius'>"+responseData.data.user.userrole+"</span></td>");
                        var td11 = $("<td class='td-manage'><a title='编辑' onclick=member_edit('修改信息','user-update.html','usercode="+usercode+"','570','540') class='ml-5' style='text-decoration:none'><i class='Hui-iconfont'>&#xe6df;</i></a> <a style='text-decoration:none' class='ml-5' onClick=change_password('修改密码','change-password.html','usercode="+usercode+"','600','500')  title='修改密码'><i class='Hui-iconfont'>&#xe63f;</i></a></td>");
                        td1.appendTo(tr);
                        td2.appendTo(tr);
                        td3.appendTo(tr);
                        td4.appendTo(tr);
                        td5.appendTo(tr);
                        td6.appendTo(tr);
                        td7.appendTo(tr);
                        td8.appendTo(tr);
                        td9.appendTo(tr);
                        td10.appendTo(tr);
                        td11.appendTo(tr);
                        tr.appendTo($("#personinfo")[0]);
                        // $("#maintenancetips").text("欢迎 "+responseData.data.user.userrole +" : <"+responseData.data.user.username +"> 登录 , 您可以在此页面修改个人信息");
                        $("#totalRecord").text(1);
                    } else {
                        swal({
                            title: "Sorry!",
                            text:  responseData.message,
                            timer: 5000,
                            type: "error"
                        });
                    }

                },
                error: function (data) {
                    swal({
                        title: "Sorry!",
                        text: "请求用户信息出错,错误信息:"+data,
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

//修改用户信息
function updateUser () {
    $.ajax({
        type: "put",
        url: rurl + "/user/update",
        dataType: "jsonp",
        timeout: 3000,
        data: $("#form-user-update").serialize(),
        success: function (responseData) {
            if(responseData.success) {
                swal({
                    title: "Good Jop!",
                    text: "用户信息修改成功",
                    timer: 2000,
                    type: "success"
                }, function () {
                    $(window.parent.document).find('#btn-refresh')[0].click();
                    $("#updateCloseButton").click();
                });
            } else {
                swal({
                    title: "Sorry!",
                    text: "修改用户信息出错,错误信息:"+responseData.message,
                    timer: 2000,
                    type: "error"
                });
            }
        },
        error: function (responseData) {
            swal({
                title: "Sorry!",
                text: "请求用户信息出错,错误信息:"+responseData.responseText,
                timer: 2000,
                type: "error"
            });
        }
    });
};
//修改密码
function changePassword () {
    $.ajax({
        type: "post",
        url: rurl + "/user/changePwd",
        dataType: "jsonp",
        timeout: 3000,
        data: $("#form-changepassword").serialize(),
        success: function (responseData) {
            if(responseData.success) {
                swal({
                    title: "Good Jop!",
                    text: "密码修改成功",
                    timer: 1500,
                    type: "success"
                }, function () {
                    $("#closeChangePwdButton").click();
                });
            } else {
                swal({
                    title: "Sorry!",
                    text: "修改密码出错,错误信息:"+responseData.message,
                    timer: 1500,
                    type: "error"
                });
            }
        },
        error: function (responseData) {
            swal({
                title: "Sorry!",
                text: "请求修改密码出错,错误信息:"+responseData.responseText,
                timer: 2000,
                type: "error"
            });
        }
    });
}

//获取全部用户信息
function getAll () {
    $.ajax({
        type: "post",
        dataType: "jsonp",
        jsonp: "callback",
        url: rurl + "/user/all",
        timeout: 3000,
        success: function (responseData) {
            if(responseData.success) {
                for(var i =0; i < responseData.size; i ++) {
                    var tr;
                    if(i % 2 == 0) {
                       tr = $("<tr class='text-c odd' role='row'></tr>");
                    } else {
                        tr = $("<tr class='text-c even' role='row'></tr>");
                    }
                    var usercode = responseData.data[i].user.usercode;
                    var td1 = $("<td><input type='checkbox' value='1'></td>");
                    var td2 = $("<td class='sorting_1'><span style='cursor:pointer' title='查看个人信息' class='label label-secondary radius' onclick=member_show('"+responseData.data[i].user.username+"','user-show.html','usercode="+usercode+"','360','400')>"+responseData.data[i].user.username+"</span></td>");
                    var td3 = $("<td>"+usercode+"</td>");
                    var td4 = $("<td>"+responseData.data[i].user.userfullname+"</td>");
                    var td5 = $("<td>"+responseData.data[i].user.usersex+"</td>");
                    var td6 = $("<td>"+responseData.data[i].user.userbirth+"</td>");
                    var td7 = $("<td>"+responseData.data[i].user.userphonenum+"</td>");
                    var td8 = $("<td>"+responseData.data[i].user.useremail+"</td>");
                    var td9 = $("<td>"+responseData.data[i].user.userregisterdate+"</td>");
                    var td10 = $("<td class='td-status'><span class='label label-primary radius'>"+responseData.data[i].user.userrole+"</span></td>");
                    var td11 = $("<td class='td-manage'><a title='编辑' onclick=member_edit('修改信息','user-update.html','usercode="+usercode+"','570','540') class='ml-5' style='text-decoration:none'><i class='Hui-iconfont'>&#xe6df;</i></a> <a style='text-decoration:none' class='ml-5' onClick=change_password('修改密码','change-password.html','usercode="+usercode+"','600','500')  title='修改密码'><i class='Hui-iconfont'>&#xe63f;</i></a> <a title='删除' href='javascript:;' onclick='member_del(this,usercode)' class='ml-5' style='text-decoration:none'><i class='Hui-iconfont'>&#xe6e2;</i></a></td>");
                    td1.appendTo(tr);
                    td2.appendTo(tr);
                    td3.appendTo(tr);
                    td4.appendTo(tr);
                    td5.appendTo(tr);
                    td6.appendTo(tr);
                    td7.appendTo(tr);
                    td8.appendTo(tr);
                    td9.appendTo(tr);
                    td10.appendTo(tr);
                    td11.appendTo(tr);
                    tr.appendTo($("#personinfo")[0]);
                }
                $("#totalRecord").text(responseData.size);
            } else {
                swal({
                    title: "Sorry!",
                    text:  responseData.message,
                    timer: 2000,
                    type: "error"
                });
            }

        },
        error: function (data) {
            swal({
                title: "Sorry!",
                text: "请求用户信息出错,错误信息:"+data,
                timer: 2000,
                type: "error"
            });
        }
    })
}
