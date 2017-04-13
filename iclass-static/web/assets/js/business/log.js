/**
 * Created by JasonTang on 4/11/2017.
 */
function logTableHandler(formId, url) {
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
    $(formId).prop("width", "100%");
    var colmuns =
        [
            {
                orderable: false,
                data: "id",
            },
            {
                data: "ip",
                render: function (data, type, row, meta) {
                    return "<span class='label label-default radius'>" + data + "</span>";
                }
            },
            {
                data: "device"
            },
            {
                data: "http_method",
                render: function (data, type, row, meta) {
                    if (data == "GET") {
                        return "<span class='label label-success radius'>" + data + "</span>";
                    } else if(data == "POST") {
                        return "<span class='label label-warning radius'>" + data + "</span>";
                    }
                }
            },
            {
                data: "operation",
            },
            {
                data: "method",
            },
            {
                data: "args",
            },
            {
                data: "exetime",
            },
            {
                data: "logtime",
            },
            {
                data: null,
                orderable: false,
            }];

    var columnDefs =
        [{
            targets: [-2, -1],
            "createdCell": function (td, cellData, rowData, row, col) {
                var id = rowData.id;
                if (col == 9) {
                    $(td).addClass("td-manage");
                    $(td).html("<a title='详情' onclick=log_detail('日志详情','system-log-detail.html','"+id+"') class='ml-5' style='text-decoration:none'><i class='Hui-iconfont'>&#xe665;</i></a> <a title='删除' onclick=log_del(this,'"+id+"') class='ml-5' style='text-decoration:none'><i class='Hui-iconfont'>&#xe6e2;</i></a>");
                }
            }
        }
        ];

    var table = $(formId).dataTable({
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
            url: ppt_hw_url + url,
            type: "post",
            dataType: "json",
            data: {
                "userCode": usercode,
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
            timeout: 3000,
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

function showLog(id) {
    $.ajax({
        type: "post",
        url: ppt_hw_url + "/log/get/" + id,
        dataType: "json",
        data: {
            id: id
        },
        timeout: 3000,
        success: function (responseData) {
            if(responseData.success) {
                var data = responseData.data;
                $("#logId").text(data.id);
                $("#logIp").text(data.ip);
                $("#logOperation").text(data.operation);
                var error = data.logerror;
                if (error == null) {
                    error = "没有错误";
                }
                $("#logError").text(error);
                $("#logDetail").text(data.logdata);
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
    });
}

function del(id) {
    $.ajax({
        type: "post",
        url: ppt_hw_url + "/log/del",
        data: {
            "id": id
        },
        timeout: 3000,
        success: function (responseData) {
            if(responseData.success) {
                swal({
                    title: "Good Job!",
                    text: "删除成功",
                    timer: 2000,
                    type: "success"
                });
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
    });
}

/*日志-详情*/
function log_detail(title,url,id){
    layer_show(title,url,id,400,500);
}
/*日志-删除*/
function log_del(obj,id){
    layer.confirm('确认要删除吗？',function(index){
        del(id);
        $(obj).parents("tr").remove();
        layer.close(index);
        $("#btn-refresh").click();
        // layer.msg('已删除!',{icon:1,time:1000});
    });
}