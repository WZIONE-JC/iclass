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
                success: function (userdata) {
                    // <tr class="text-c" >
                    //     <td><input type="checkbox" value="1" name=""></td>
                    //     <td><span style="cursor:pointer" title="点击个人信息" class="label label-secondary radius" onclick="member_show('张三','user-show.html','10001','360','400')">小黄瓜</span></td>
                    //     <td>1308030333</td>
                    //     <td>唐洋</td>
                    //     <td>男</td>
                    //     <td>1994-09-05</td>
                    //     <td>1882934777</td>
                    //     <td>361239731@qq.com</td>
                    // <td>2016-06-11 11:11:42</td>
                    // <td class="td-status"><span class="label label-primary radius">学生</span></td>
                    //     <td class="td-manage"><a title="编辑" href="javascript:;" onclick="member_edit('修改信息','user-add.html','4','','570')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a> <a style="text-decoration:none" class="ml-5" onClick="change_password('修改密码','change-password.html','10001','600','350')" href="javascript:;" title="修改密码"><i class="Hui-iconfont">&#xe63f;</i></a> <a title="删除" href="javascript:;" onclick="member_del(this,'1')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a></td>
                    // </tr>

                    var tr = $("<tr class='text-c'></tr>");
                    var td1 = $("<td><input type='checkbox' value='1'></td>");
                    var td2 = $("<td><span style='cursor:pointer' title='查看个人信息' class='label label-secondary radius' onclick=member_show('"+userdata.username+"','user-show.html','usercode="+userdata.usercode+"','360','400')>"+userdata.username+"</span></td>");
                    var td3 = $("<td>"+userdata.usercode+"</td>");
                    var td4 = $("<td>"+userdata.userfullname+"</td>");
                    var td5 = $("<td>"+userdata.usersex+"</td>");
                    var td6 = $("<td>"+userdata.userbirth+"</td>");
                    var td7 = $("<td>"+userdata.userphonenum+"</td>");
                    var td8 = $("<td>"+userdata.useremail+"</td>");
                    var td9 = $("<td>"+userdata.userregisterdate+"</td>");
                    var td10 = $("<td class='td-status'><span class='label label-primary radius'>"+userdata.userrole+"</span></td>");
                    var usercode = userdata.usercode;
                    var td11 = $("<td class='td-manage'><a title='编辑' onclick=member_edit('修改信息','user-update.html','usercode="+userdata.usercode+"','','570') class='ml-5' style='text-decoration:none'><i class='Hui-iconfont'>&#xe6df;</i></a> <a style='text-decoration:none' class='ml-5' onClick=change_password('修改密码','change-password.html','usercode="+userdata.usercode+"','600','350')  title='修改密码'><i class='Hui-iconfont'>&#xe63f;</i></a> <a title='删除' onclick=member_del(this,'usercode="+usercode+"') class='ml-5' style='text-decoration:none'><i class='Hui-iconfont'>&#xe6e2;</i></a></td>");
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

                    $("#maintenancetips").text("欢迎 "+userdata.userrole +" : <"+userdata.username +"> 登录 , 您可以在此修改个人信息");
                    $("#totalRecord").text(1);
                },
                error: function (data) {
                    alert("请求用户信息出错,错误信息:"+data);
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
