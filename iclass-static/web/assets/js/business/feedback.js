/**
 * Created by JasonTang on 4/26/2017.
 */

function feedbackTableHandler(formId, url) {
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
    $("#usercode").val(usercode);
    $(formId).prop("width", "100%");
    var colmuns =
        [
            {
                orderable: false,
                data: null,
                render: function (data, type, row, meta) {
                    return "<span class='label label-default radius orderNum'>" + data + "</span>";
                }
            },
            {
                data: "classRoomName",
            },
            {
                data: "feedback.title",
            },
            {
                data: "feedback.content",
            },
            {
                data: "feedbacker",
                render: function (data, type, row, meta) {
                    return "<span class='label label-default radius'>" + data + "</span>";
                }
            },
            {
                data: "className",
                render: function (data, type, row, meta) {
                    return data;
                }
            },
            {
                data: "feedback.createtime",
            },
            {
                data: "feedback.status",
                render: function (data, type, row, meta) {
                    if (data == 1) {
                        return "<span class='label label-default radius'>待解决</span>";
                    } else if (data == 2) {
                        return "<span class='label label-success radius'>已解决</span>";
                    }
                }
            },
            {
                data: null,
                orderable: false
            }];

    var columnDefs =
        [{
            targets: [2, -2, -1],
            "createdCell": function (td, cellData, rowData, row, col) {
                var id = rowData.feedback.feedbackid;
                var feedbackNum = rowData.feedbackNum;
                if (col == 2) {
                    if (feedbackNum != 0) {
                        $(td).wrapInner("<span class='label label-success radius'></span>");
                        $(td).wrapInner("<a style='text-decoration:none' title='查看详情' onclick=show('查看','feedback-show.html','"+ id +"','770','400')></a>");
                    } else {
                        $(td).wrapInner("<span class='label label-default radius'></span>");
                    }
                }
                if (col == 7) {
                    $(td).addClass("td-status");
                }
                if (col == 8) {
                    $(td).addClass("td-manage");
                    $(td).html("<a style='text-decoration:none' class='ml-5' title='修改状态' onclick=edit('修改状态','feedback-update.html','"+ id +"','400','400')><i class='Hui-iconfont'>&#xe6b3;</i></a> <a style='text-decoration:none' class='ml-5' title='回复' onclick=edit('回复','feedback-reply.html','"+ id +"','400','400')><i class='Hui-iconfont'>&#xe70c;</i></a>");
                }
            }
        }];

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
        destroy: true, //每次请求都销毁列表
        ajax: {
            url: ppt_hw_url + url,
            dataType: "jsonp",
            data: {
                "userCode": usercode,
                "classCourseId": $("#classcourse").val(),
                "isLimit": true
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
            timeout: 10000,
            error: function () {
                swal({
                    title: "Sorry!",
                    text: "网络忙,请稍后再试",
                    timer: 2000,
                    type: "error"
                });
                $("#hw-info-table_processing").hide();
            }
        },
        columns: colmuns,
        "preDrawCallback": function () {
            $(".dataTables_filter").hide();
        },
        "createdRow": function (row, data, index) {
            $(row).find(".orderNum").text((index+1));
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

function feedbackShowTableHandler(formId, url, parentId) {
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
    $("#usercode").val(usercode);
    $(formId).prop("width", "100%");
    var colmuns =
        [
            {
                orderable: false,
                data: null,
                render: function (data, type, row, meta) {
                    return "<span class='label label-default radius orderNum'>" + data + "</span>";
                }
            },
            {
                data: "classRoomName",
            },
            {
                data: "feedback.content",
            },
            {
                data: "feedbacker",
                render: function (data, type, row, meta) {
                    return "<span class='label label-default radius'>" + data + "</span>";
                }
            },
            {
                data: "className",
                render: function (data, type, row, meta) {
                    return data;
                }
            },
            {
                data: "feedback.createtime",
            }];
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
        ajax: {
            url: ppt_hw_url + url,
            dataType: "jsonp",
            data: {
                "userCode": usercode,
                "parentId": parentId,
                "isLimit": true
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
            timeout: 10000,
            error: function () {
                swal({
                    title: "Sorry!",
                    text: "网络忙,请稍后再试",
                    timer: 2000,
                    type: "error"
                });
                $("#hw-info-table_processing").hide();
            }
        },
        columns: colmuns,
        "createdRow": function (row, data, index) {
            $(row).find(".orderNum").text((index+1));
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

function updateStatus() {
    $.ajax({
        type: "post",
        url: ppt_hw_url + "/feedback/update",
        dataType: "jsonp",
        timeout: 10000,
        data: $("#form-updatefeedback").serialize(),
        success: function (responseData) {
            if(responseData.success) {
                swal({
                    title: "Success!",
                    text:  "状态修改成功",
                    timer: 1000,
                    type: "success"
                }, function () {
                    $(window.parent.document).find('#btn-refresh')[0].click();
                    $("#cancelButton").click();
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

function get(id) {
    $.ajax({
        type: "post",
        url: ppt_hw_url + "/feedback/get/" + id,
        dataType: "jsonp",
        timeout: 10000,
        success: function (responseData) {
            if(responseData.success) {
                var data = responseData.data;
                $("#status option").each(function () {
                    if($(this).val() == data.status) {
                        $(this).attr("selected", "true");
                    }
                });
                $("#title").text(data.title);
                $("#content").text(data.content);
                var usercode = $(window.parent.document).find('#usercode')[0].value;
                $("#teacherCode").val(usercode);
            } else {
                swal({
                    title: "Sorry!",
                    text:  responseData.message,
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

function reply(form) {
    $.ajax({
        type: "post",
        url: ppt_hw_url + "/feedback/reply",
        dataType: "jsonp",
        timeout: 10000,
        data: form.serialize(),
        success: function (responseData) {
            if(responseData.success) {
                swal({
                    title: "Good Job!",
                    text:  "发表成功!",
                    timer: 2000,
                    type: "success"
                }, function () {
                    $(window.parent.document).find('#btn-refresh')[0].click();
                    $("#cancelButton").click();
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