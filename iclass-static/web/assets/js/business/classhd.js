/**
 * Created by JasonTang on 4/14/2017.
 */
function classhdTableHandler() {
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
    $("#classcreator").val(usercode);
    $("#form-classhd-table").prop("width", "100%");
    var colmuns =
        [
            {
                orderable: false,
                data: "classhd.classhdid",
                render: function (data, type, row, meta) {
                    return "<input type='checkbox' value=" + data + " name='classid'>";
                }
            },
            {
                data: "classRoomName",
            },
            {
                data: "classhd.classhdcontent",
            },
            {
                data: "classhd.classhdoptions"
            },
            {
                data: "classhd.classhdanswer",
                render: function (data, type, row, meta) {
                    return "<span class='label label-secondary radius'>" + data + "</span>";
                }
            },
            {
                data: "teacherName",
                render: function (data, type, row, meta) {
                    return "<span class='label label-warning radius'>" + data + "</span>";
                }
            },
            {
                data: "classhd.classhdcreatetime"
            },
            {
                data: "classhd.rightnumber",
                render: function (data, type, row, meta) {
                    return "<span class='label label-default radius'>" + data + "</span>";
                }
            },
            {
                data: "classhd.classhdstatus",
                render: function (data, type, row, meta) {
                    if (data == 0) {
                        return "<span class='label label-default radius' title='已下架'>已下架</span>";
                    } else if (data == 1) {
                        return "<span class='label label-success radius' title='已发布'>已发布</span>";
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
            targets: [1, -2, -1],
            "createdCell": function (td, cellData, rowData, row, col) {
                var id = rowData.classhd.classhdid;
                var classroomid = rowData.classhd.classcourseid;
                var classRoomName = rowData.classRoomName;
                var status = rowData.classhd.classhdstatus;
                // if (col == 1) {
                //     $(td).wrapInner("<span style='cursor:pointer' title='查看课堂信息' class='label label-default radius' onclick=show('" + classRoomName + "','classhd-show.html','" + classroomid + "','360','400')></span>");
                // }
                if (col == 8) {
                    $(td).addClass("td-status");
                }
                if (col == 9) {
                    $(td).addClass("td-manage");
                    if(status == 1) {
                        $(td).html("<a style='text-decoration:none' class='ml-5' title='编辑' onclick=edit('编辑','classhd-update.html','"+id+"','570','400')><i class='Hui-iconfont'>&#xe6df;</i></a> <a style='text-decoration:none' class='ml-5' onClick='delClasshd("+id+")' title='删除'><i class='Hui-iconfont'>&#xe6e2;</i></a> <a style='text-decoration:none' class='ml-5' onClick='stop(this,"+id+")'  title='下架'><i class='Hui-iconfont'>&#xe6de;</i></a>");
                    } else if(status == 0) {
                        $(td).html("<a style='text-decoration:none' class='ml-5' title='编辑' onclick=edit('编辑','classhd-update.html','"+id+"','570','400')><i class='Hui-iconfont'>&#xe6df;</i></a> <a style='text-decoration:none' class='ml-5' onClick='delClasshd("+id+")' title='删除'><i class='Hui-iconfont'>&#xe6e2;</i></a> <a style='text-decoration:none' class='ml-5' onClick='start(this,"+id+")' title='发布'><i class='Hui-iconfont'>&#xe603;</i></a>");
                    }
                }
            }
        }];

    var table = $("#form-classhd-table").dataTable({
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
            url: ppt_hw_url + "/classhd/getAll",
            dataType: "jsonp",
            data: {
                "classhdCreator": usercode,
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

/**
* 添加课堂互动内容
*/
function addClasshd() {
    $.ajax({
        type: "post",
        url: ppt_hw_url + "/classhd/save",
        dataType: "jsonp",
        timeout: 5000,
        data: $("#form-classhd-add").serialize(),
        success: function (responseData) {
            if(responseData.success) {
                swal({
                    title: "Good Jop!",
                    text: "课堂互动内容添加成功",
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
/**
* 更新课堂互动内容
*/
function updateClasshd() {
    $.ajax({
        type: "post",
        url: ppt_hw_url + "/classhd/update",
        dataType: "jsonp",
        timeout: 5000,
        data: $("#form-classhd-update").serialize(),
        success: function (responseData) {
            if(responseData.success) {
                swal({
                    title: "Good Jop!",
                    text: "课堂互动内容更新成功",
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

/**
 * 显示课堂互动信息
 * @param id 课堂互动id
 */
function showClasshd(id) {
    $.ajax({
        type: "post",
        url: ppt_hw_url + "/classhd/get/"+id,
        dataType: "jsonp",
        timeout: 5000,
        ansyc: false,
        success: function (responseData) {
            if(responseData.success) {
                var data = responseData.data;
                $("#courseCode option").each(function () {
                    if($(this).val() == data.courseCode) {
                        $(this).attr("selected", "true");
                    }
                });
                $("#classCode option").each(function () {
                    if($(this).val() == data.classCode) {
                        $(this).attr("selected", "true");
                    }
                });
                $("#classhdcontent").val(data.classhd.classhdcontent);
                $("#classhdoptions").val(data.classhd.classhdoptions);
                $("#classhdanswer").val(data.classhd.classhdanswer);
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
/**
 * 删除课堂互动
 * @param id
 */
function delClasshd(id){
    swal({
            title: "确定删除?",
            text: "即将删除这个问题",
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
                    url: ppt_hw_url + "/classhd/del/" + id,
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