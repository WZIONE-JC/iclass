/**
 * Created by radishmaster on 15/04/17.
 */
function rollcallTableHandler() {
    var usercode = $(window.parent.document).find('#usercode')[0].value;
    if ( usercode == null ) {
        swal({
            title: "Sorry!",
            text: "用户未登录",
            timer: 2000,
            type: "error"
        });
        return;
    }
    $("#teacherCode").val(usercode);
    $("#form-rollcall-table").prop("width", "100%");
    var colmuns =
        [
            {
                orderable: false,
                data: "rollcall.rollcallid",
                render: function (data, type, row, meta) {
                    return "<input type='checkbox' value=" + data + " name='classid'>";
                }
            },
            {
                data: "classRoomName",
            },
            {
                data: "student.user.usercode",
            },
            {
                data: "student.user.userfullname"
            },
            {
                data: "teacherName",
                render: function (data, type, row, meta) {
                    return "<span class='label label-default radius'>" + data + "</span>";
                }
            },
            {
                data: "rollcall.rollcalltime"
            },
            {
                data: "rollcall.rollcallstatus",
                render: function (data, type, row, meta) {
                    if (data == 0) {
                        return "<span class='label label-danger radius'>缺勤</span>";
                    } else if (data == 1) {
                        return "<span class='label label-success radius'>已到</span>";
                    }
                    return "<input type='checkbox' value=" + data + " name='classid'>";
                }
            },
            {
                data: null,
                orderable: false,
                width: "5%"
            }
        ];

    var columnDefs =
        [{
            targets: [1, 2, -2, -1],
            "createdCell": function (td, cellData, rowData, row, col) {
                var id = rowData.rollcall.rollcallid;
                var studentName = rowData.student.user.userfullname;
                var studentCode = rowData.student.user.usercode;
                if (col == 2) {
                    $(td).wrapInner("<span style='cursor:pointer' title='查看个人信息' class='label label-success radius' onclick=show('"+studentName+"','user-show.html','"+studentCode+"','360','400')></span>");
                }
                if (col == 6) {
                    $(td).addClass("td-status");
                }
                if (col == 7) {
                    $(td).addClass("td-manage");
                    $(td).html(" <a style='text-decoration:none' class='ml-5' onClick='delClasshd("+id+")' title='删除'><i class='Hui-iconfont'>&#xe6e2;</i></a>");
                }
            }
        }];

    var table = $("#form-rollcall-table").dataTable({
        // processing: true,
        order: [], //默认没有排序的,在后面指定具体排序的类
        stateSave: true, //允许浏览器缓存Datatables，以便下次恢复之前的状态
        processing: true, //是否显示处理状态
        serverSide: true, //开启服务器模式
        searching: true, //开启搜索功能
        paging: true, //允许表格分页
        lengthChange: true, //允许改变每页显示的数据条数
        bStateSave: true,//状态保存
        autoWidth: true,
        columnDefs: columnDefs,
        ajax: {
            url: ppt_hw_url + "/rollcall/getAll",
            dataType: "jsonp",
            data: {
                "teacherCode": usercode,
            },
            dataSrc: function (result) {
                if (result.success) {
                    //显示总数
                    $("#totalRecord").text(result.recordsFiltered);
                    return result.data;
                } else {
                    swal({
                        title: "Sorry!",
                        text: result.message,
                        timer: 2000,
                        type: "error"
                    });
                    return false;
                }
            },
            timeout: 5000,
            error: function () {
                swal({
                    title: "Sorry!",
                    text: "网络忙,请稍后再试",
                    timer: 2000,
                    type: "error"
                });
            }
        },
        columns: colmuns,
        "preDrawCallback": function () {
        },
        "initComplete": function (settings, json) {
        },
        "createdRow": function (row, data, index) {
            $(row).addClass("text-c");
        },

        "drawCallback": function (data, settings) {
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
        }
    });
    return table;
}

function delClasshd(id) {
    swal({
            title: "确定删除?",
            text: "即将删除这个记录",
            type: "warning",
            showCancelButton: true,
            cancelButtonText: "取消",
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "删除!",
            closeOnConfirm: false,
            showLoaderOnConfirm: true
        }, function () {
            setTimeout(function() {
                $.ajax({
                    type: "post",
                    url: ppt_hw_url + "/rollcall/del/" + id,
                    dataType: "jsonp",
                    timeout: 5000,
                    success: function (responseData) {
                        if(responseData.success) {
                            swal({
                                title: "Good Job!",
                                text: "删除成功",
                                timer: 1200,
                                type: "success"
                            });
                            $("#btn-refresh").click();
                        } else {
                            swal({
                                title: "Sorry!",
                                text: responseData.message,
                                timer: 2000,
                                type: "error"
                            });
                        }
                    },
                    error: function () {
                        swal({
                            title: "Sorry!",
                            text: "网络忙,请稍后再试",
                            timer: 2000,
                            type: "error"
                        });
                    }
                })},1200);
        }
    );
}