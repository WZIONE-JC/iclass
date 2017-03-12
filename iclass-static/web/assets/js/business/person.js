// var PersonMaintenance = function () {
//     //获取用户信息
//     var a = function () {
//         $(function () {
//             $.ajax({
//                 type: "post",
//                 dataType: "jsonp",
//                 jsonp: "callback",
//                 url: rurl + "/user/getUserInfoBySession",
//                 timeout: 3000,
//                 success: function (responseData) {
//                     // <tr class="text-c" >
//                     //     <td><input type="checkbox" value="1" name=""></td>
//                     //     <td><span style="cursor:pointer" title="点击个人信息" class="label label-secondary radius" onclick="member_show('张三','entity-show.html','10001','360','400')">小黄瓜</span></td>
//                     //     <td>1308030333</td>
//                     //     <td>唐洋</td>
//                     //     <td>男</td>
//                     //     <td>1994-09-05</td>
//                     //     <td>1882934777</td>
//                     //     <td>361239731@qq.com</td>
//                     // <td>2016-06-11 11:11:42</td>
//                     // <td class="td-status"><span class="label label-primary radius">学生</span></td>
//                     //     <td class="td-manage"><a title="编辑" href="javascript:;" onclick="member_edit('修改信息','entity-add.html','4','','570')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a> <a style="text-decoration:none" class="ml-5" onClick="change_password('修改密码','change-password.html','10001','600','350')" href="javascript:;" title="修改密码"><i class="Hui-iconfont">&#xe63f;</i></a> <a title="删除" href="javascript:;" onclick="member_del(this,'1')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a></td>
//                     // </tr>
//                     if(responseData.success) {
//                         var tr = $("<tr class='text-c'></tr>");
//                         var usercode = responseData.data.user.usercode;
//                         var td1 = $("<td><input type='checkbox' value='1'></td>");
//                         var td2 = $("<td><span style='cursor:pointer' title='查看个人信息' class='label label-secondary radius' onclick=member_show('"+responseData.data.user.username+"','user-show.html','usercode="+usercode+"','360','400')>"+responseData.data.user.username+"</span></td>");
//                         var td3 = $("<td>"+usercode+"</td>");
//                         var td4 = $("<td>"+responseData.data.user.userfullname+"</td>");
//                         var td5 = $("<td>"+responseData.data.user.usersex+"</td>");
//                         var td6 = $("<td>"+responseData.data.user.userbirth+"</td>");
//                         var td7 = $("<td>"+responseData.data.user.userphonenum+"</td>");
//                         var td8 = $("<td>"+responseData.data.user.useremail+"</td>");
//                         var td9 = $("<td>"+responseData.data.user.userregisterdate+"</td>");
//                         var td10 = $("<td class='td-status'><span class='label label-primary radius'>"+responseData.data.user.userrole+"</span></td>");
//                         var td11 = $("<td class='td-manage'><a title='编辑' onclick=member_edit('修改信息','user-update.html','usercode="+usercode+"','570','540') class='ml-5' style='text-decoration:none'><i class='Hui-iconfont'>&#xe6df;</i></a> <a style='text-decoration:none' class='ml-5' onClick=change_password('修改密码','change-password.html','usercode="+usercode+"','600','500')  title='修改密码'><i class='Hui-iconfont'>&#xe63f;</i></a></td>");
//                         td1.appendTo(tr);
//                         td2.appendTo(tr);
//                         td3.appendTo(tr);
//                         td4.appendTo(tr);
//                         td5.appendTo(tr);
//                         td6.appendTo(tr);
//                         td7.appendTo(tr);
//                         td8.appendTo(tr);
//                         td9.appendTo(tr);
//                         td10.appendTo(tr);
//                         td11.appendTo(tr);
//                         tr.appendTo($("#personinfo")[0]);
//                         // $("#maintenancetips").text("欢迎 "+responseData.data.user.userrole +" : <"+responseData.data.user.username +"> 登录 , 您可以在此页面修改个人信息");
//                         $("#totalRecord").text(1);
//                     } else {
//                         swal({
//                             title: "Sorry!",
//                             text:  responseData.message,
//                             timer: 5000,
//                             type: "error"
//                         });
//                     }
//
//                 },
//                 error: function (data) {
//                     swal({
//                         title: "Sorry!",
//                         text: "请求用户信息出错,错误信息:"+data,
//                         timer: 2000,
//                         type: "error"
//                     });
//                 }
//             })
//         })
//     };
//
//     return {
//         init: function () {
//             a();
//         },
//     }
// }();

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
                    text:  responseData.message,
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
                text: "网络繁忙，请稍后再试",
                timer: 2000,
                type: "error"
            });
        }
    });
}
//
// //获取全部用户信息
// function getAll () {
//     $.ajax({
//         type: "post",
//         dataType: "jsonp",
//         jsonp: "callback",
//         url: rurl + "/user/all",
//         timeout: 3000,
//         success: function (responseData) {
//             if(responseData.success) {
//                 for(var i =0; i < responseData.size; i ++) {
//                     var tr;
//                     if(i % 2 == 0) {
//                        tr = $("<tr class='text-c odd' role='row'></tr>");
//                     } else {
//                         tr = $("<tr class='text-c even' role='row'></tr>");
//                     }
//                     var usercode = responseData.data[i].user.usercode;
//                     var td1 = $("<td><input type='checkbox' value='1'></td>");
//                     var td2 = $("<td class='sorting_1'><span style='cursor:pointer' title='查看个人信息' class='label label-secondary radius' onclick=member_show('"+responseData.data[i].user.username+"','user-show.html','usercode="+usercode+"','360','400')>"+responseData.data[i].user.username+"</span></td>");
//                     var td3 = $("<td>"+usercode+"</td>");
//                     var td4 = $("<td>"+responseData.data[i].user.userfullname+"</td>");
//                     var td5 = $("<td>"+responseData.data[i].user.usersex+"</td>");
//                     var td6 = $("<td>"+responseData.data[i].user.userbirth+"</td>");
//                     var td7 = $("<td>"+responseData.data[i].user.userphonenum+"</td>");
//                     var td8 = $("<td>"+responseData.data[i].user.useremail+"</td>");
//                     var td9 = $("<td>"+responseData.data[i].user.userregisterdate+"</td>");
//                     var td10 = $("<td class='td-status'><span class='label label-primary radius'>"+responseData.data[i].user.userrole+"</span></td>");
//                     var td11 = $("<td class='td-manage'><a title='编辑' onclick=member_edit('修改信息','user-update.html','usercode="+usercode+"','570','540') class='ml-5' style='text-decoration:none'><i class='Hui-iconfont'>&#xe6df;</i></a> <a style='text-decoration:none' class='ml-5' onClick=change_password('修改密码','change-password.html','usercode="+usercode+"','600','500')  title='修改密码'><i class='Hui-iconfont'>&#xe63f;</i></a> <a title='删除' href='javascript:;' onclick='member_del(this,usercode)' class='ml-5' style='text-decoration:none'><i class='Hui-iconfont'>&#xe6e2;</i></a></td>");
//                     td1.appendTo(tr);
//                     td2.appendTo(tr);
//                     td3.appendTo(tr);
//                     td4.appendTo(tr);
//                     td5.appendTo(tr);
//                     td6.appendTo(tr);
//                     td7.appendTo(tr);
//                     td8.appendTo(tr);
//                     td9.appendTo(tr);
//                     td10.appendTo(tr);
//                     td11.appendTo(tr);
//                     tr.appendTo($("#personinfo")[0]);
//                 }
//                 $("#totalRecord").text(responseData.size);
//             } else {
//                 swal({
//                     title: "Sorry!",
//                     text:  responseData.message,
//                     timer: 2000,
//                     type: "error"
//                 });
//             }
//
//         },
//         error: function (data) {
//             swal({
//                 title: "Sorry!",
//                 text: "请求用户信息出错,错误信息:"+data,
//                 timer: 2000,
//                 type: "error"
//             });
//         }
//     })
// }
function userTableHandler (formId, isPersonal, url) {
//http://datatables.club/upgrade/1.10-convert.html
//http://datatables.club/reference/option/
//https://datatables.net/reference/option/columns.createdCell
    $(formId).prop("width","100%");
    var colmuns =
        [{
            orderable: false,
            data: "user.usercode",
            render: function (data, type, row, meta) {
                // console.log("data:"+data);
                return "<input type='checkbox' value=" + data + " name='usercode'>";
            }
        },
        {
            data: "user.username",
        },
        {
            data: "user.usercode"
        },
        {
            data: "user.userfullname"
        },
        {
            data: "user.usersex",
        },
        {
            data: "user.userbirth"
        },
        {
            data: "user.userphonenum"
        },
        {
            data: "user.useremail"
        },
        {
            data: "user.userregisterdate",
            width: "8%"
        },
        {
            data: "user.userrole",
        },
        {
            data: null,
            orderable: false,
            width: "10%"
        }];

    var columnDefs =
        [{
            targets: [1,-2,-1],
            "createdCell":function (td, cellData, rowData, row, col) {
//					console.log(td);
                var usercode = rowData.user.usercode;
                var username = rowData.user.username;
                var userrole = rowData.user.userrole;
                if(userrole == "教师" ) {
                    if(col == 1) {
                        $(td).wrapInner("<span style='cursor:pointer' title='查看个人信息' class='label label-secondary radius' onclick=member_show('"+username+"','user-show.html','usercode="+usercode+"','360','400')></span>");
                    }
                    if(col == 9) {
                        $(td).addClass("td-status");
                        $(td).wrapInner("<span class='label label-success radius'></span>");
                    }
                } else if(userrole == "学生") {
                    if(col == 1) {
                        $(td).wrapInner("<span style='cursor:pointer' title='查看个人信息' class='label label-secondary radius' onclick=member_show('"+username+"','user-show.html','usercode="+usercode+"','360','400')></span>");
                    }
                    if(col == 9) {
                        $(td).addClass("td-status");
                        $(td).wrapInner("<span class='label label-primary radius'></span>");
                    }
                } else {
                    if(col == 1) {
                        $(td).wrapInner("<span style='cursor:pointer' title='查看个人信息' class='label label-secondary radius' onclick=member_show('"+username+"','user-show.html','usercode="+usercode+"','360','400')></span>");
                    }
                    if(col == 9) {
                        $(td).addClass("td-status");
                        $(td).wrapInner("<span class='label label-warning radius'></span>");
                    }
                }
                if(col == 10) {
                    $(td).addClass("td-manage");
                    //个人页面不需要显示删除按钮
                    if(isPersonal == true) {
                        $(td).html("<a title='编辑' onclick=member_edit('修改信息','user-update.html','usercode="+usercode+"','570','540') class='ml-5' style='text-decoration:none'><i class='Hui-iconfont'>&#xe6df;</i></a> <a style='text-decoration:none' class='ml-5' onClick=change_password('修改密码','change-password.html','usercode="+usercode+"','600','500')  title='修改密码'><i class='Hui-iconfont'>&#xe63f;</i></a>");
                    } else {
                        $(td).html("<a title='编辑' onclick=member_edit('修改信息','user-update.html','usercode="+usercode+"','570','540') class='ml-5' style='text-decoration:none'><i class='Hui-iconfont'>&#xe6df;</i></a> <a style='text-decoration:none' class='ml-5' onClick=change_password('修改密码','change-password.html','usercode="+usercode+"','600','500')  title='修改密码'><i class='Hui-iconfont'>&#xe63f;</i></a> <a title='删除' href='javascript:;' onclick='member_del(this,"+usercode+")' class='ml-5' style='text-decoration:none'><i class='Hui-iconfont'>&#xe6e2;</i></a>");
                    }

                }
            }
        }
    ];

    var table = $(formId).dataTable({
        // processing: true,
        order: [], //默认没有排序的,在后面指定具体排序的类
        stateSave: true, //允许浏览器缓存Datatables，以便下次恢复之前的状态
        serverSide: true, //开启服务器模式
        searching: true, //开启搜索功能
        paging: true, //允许表格分页
        lengthChange: true, //允许改变每页显示的数据条数
        bStateSave: true,//状态保存
        autoWidth: true,
        columnDefs: columnDefs,
        ajax: {
            url:  rurl + url,
            dataType: "jsonp",
            dataSrc: function (result) {
                if(result.success) {
                    //显示总数
                    $("#totalRecord").text(result.recordsTotal);
                    return result.data;
                } else {
                    swal({
                        title: "Sorry!",
                        text:  result.message,
                        timer: 2000,
                        type: "error"
                    });
                    return false;
                }
            },
        },
        columns: colmuns,
        "createdRow": function ( row, data, index ) {
            //http://datatables.club/reference/option/createdRow.html
            //row 是某一行 ， data是 ajax返回数据 ， index是行标(0)
            //行渲染回调,在这里可以对该行dom元素进行任何操作
            //给当前行加样式
            $(row).addClass("text-c");
        },
        "drawCallback": function( data, settings ) {
            //http://datatables.club/manual/daily/2016/09/04/option-rowCallback.html
            //渲染完毕后的回调
            //清空全选状态
            $(":checkbox[name='cb-check-all']").prop('checked', false);

            $('table tbody input:checkbox').on( 'click', function () {
                var tr = $(this).parent().parent("tr");
                if ( tr.hasClass('selected') ) {
                    tr.removeClass('selected');
                    tr.find('input[type=checkbox]').prop("checked",false);
                }
                else {
                    tr.removeClass('selected');
                    tr.addClass('selected');
                    tr.find('input[type=checkbox]').prop("checked",true);
                }
            });
        },
        "initComplete": function ( settings, json ) {

        }
    });
    return table;
}
/*用户-添加*/
function member_add(title,url,w,h){
    layer_show(title,url,'',w,h);
}
/*用户-查看*/
function member_show(title,url,id,w,h){
    layer_show(title,url,id,w,h);
}

/*用户-编辑*/
function member_edit(title,url,id,w,h){
    layer_show(title,url,id,w,h);
}
/*密码-修改*/
function change_password(title,url,id,w,h){
    layer_show(title,url,id,w,h);
}
/*用户-删除*/
function member_del(obj,id){
    layer.confirm('确认要删除吗？',function(index){
        $(obj).parents("tr").remove();
        layer.msg('已删除!',{icon:1,time:1000});
    });
}

/*图片-添加*/
function picture_add(title,url){
    var index = layer.open({
        type: 2,
        title: title,
        content: url
    });
    layer.full(index);
}
/*用户-停用*/
function member_stop(obj,id){
    layer.confirm('确认要停用吗？',function(index){
        $(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="member_start(this,id)" href="javascript:;" title="启用"><i class="Hui-iconfont">&#xe6e1;</i></a>');
        $(obj).parents("tr").find(".td-status").html('<span class="label label-defaunt radius">已停用</span>');
        $(obj).remove();
        layer.msg('已停用!',{icon: 5,time:1000});
    });
}

/*用户-启用*/
function member_start(obj,id){
    layer.confirm('确认要启用吗？',function(index){
        $(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="member_stop(this,id)" href="javascript:;" title="停用"><i class="Hui-iconfont">&#xe631;</i></a>');
        $(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已启用</span>');
        $(obj).remove();
        layer.msg('已启用!',{icon: 6,time:1000});
    });
}

