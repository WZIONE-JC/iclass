/**
 * Created by radishmaster on 08/04/17.
 */
function getClassCourseInfo() {
    var usercode = $(window.parent.document).find('#usercode')[0].value;
    $.ajax({
        type: "post",
        url: ppt_hw_url + "/classcourse/getClassCourseInfo",
        dataType: "jsonp",
        timeout: 10000,
        data: {
            "teacherCode": usercode
        },
        success: function (responseData) {
            if(responseData.success) {
                var data = responseData.data;
                var size = data.length;
                for(var i = 0; i < size; i ++) {
                    var option = new Option(data[i].classRoomName, data[i].classCourseId);
                    $("#classcourse")[0].add(option);
                }
                $("option").on("click", function(){
                    $("#classcourse").removeAttr("size");
                    $("#classcourse").blur();
                    $(this).attr("selected","");
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
        },
        complete: function () {
            $("#classcourse").focus(function(){
                $("#classcourse").attr("size","5");
            });
        }
    });
}

function classCourseTableHandler(formId, url) {
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
                // render: function (data, type, row, meta) {
                //     return "<span class='label label-default radius'>" + data + "</span>";
                // }
            },
            {
                data: "course.coursename",
            },
            {
                data: "aClass.classname"
            },
            {
                data: "teacherName",
                render: function (data, type, row, meta) {
                    return "<span class='label label-warning radius'>" + data + "</span>";
                }
            },
            {
                data: "attendTime",

            },
            {
                data: "creatTime"
            },
            {
                data: "deadline"
            },
            {
                data: "status",
                render: function (data, type, row, meta) {
                    if (data == 0) {
                        return "<span class='label label-default radius' title='课堂已关闭'>已关闭</span>";
                    } else if (data == 1) {
                        return "<span class='label label-success radius' title='课堂已发布'>已发布</span>";
                    }
                    return "<input type='checkbox' value=" + data + " name='classid'>";
                }
            },
            {
                data: null,
                orderable: false,
                width: "5%"
            }];

    var columnDefs =
        [{
            targets: [1, 2, -2, -1],
            "createdCell": function (td, cellData, rowData, row, col) {
                var id = rowData.classcourseId;
                var coursecode = rowData.course.coursecode;
                var coursename = rowData.course.coursename;
                var status = rowData.status;
                if (col == 1) {
                    $(td).css("text-align", "left");
                }
                if (col == 2) {
                    $(td).wrapInner("<span style='cursor:pointer' title='查看课程信息' class='label label-success radius' onclick=course_show('" + coursename + "','course-show.html','" + coursecode + "','360','400')></span>");
                }
                if (col == 8) {
                    $(td).addClass("td-status");
                }
                if (col == 9) {
                    $(td).addClass("td-manage");
                    if(status == 1) {
                        $(td).html("<a style='text-decoration:none' class='ml-5' title='编辑课堂' onclick=edit('编辑课堂','class-course-update.html','"+id+"','570','400')><i class='Hui-iconfont'>&#xe6df;</i></a> <a style='text-decoration:none' class='ml-5' onClick='stop(this,"+id+")'  title='关闭'><i class='Hui-iconfont'>&#xe6de;</i></a>");
                    } else if(status == 0) {
                        $(td).html("<a style='text-decoration:none' class='ml-5' title='编辑课堂' onclick=edit('编辑课堂','class-course-update.html','"+id+"','570','400')><i class='Hui-iconfont'>&#xe6df;</i></a> <a style='text-decoration:none' class='ml-5' onClick='start(this,"+id+")' title='发布'><i class='Hui-iconfont'>&#xe603;</i></a>");
                    }
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
            dataType: "jsonp",
            data: {
                "classCreator": usercode,
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
            }
        },
        columns: colmuns,
        "preDrawCallback": function () {
            $(".dataTables_filter").hide();
        },
        "initComplete": function (settings, json) {
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
            $("#classcreator").val(usercode);
        }
    });
    return table;
}

function addClassCourse() {
    $.ajax({
        type: "post",
        url: ppt_hw_url + "/classcourse/save",
        dataType: "jsonp",
        timeout: 10000,
        data: $("#form-class-course-add").serialize(),
        success: function (responseData) {
            if(responseData.success) {
                swal({
                    title: "Good Jop!",
                    text: "课堂添加成功",
                    timer: 2000,
                    type: "success"
                }, function () {
                    $("#cancelButton").click();
                    $(window.parent.document).find('#btn-refresh')[0].click();
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

//显示课堂信息
function showClassCourse(id) {
    $.ajax({
        type: "post",
        url: ppt_hw_url + "/classcourse/get/"+id,
        dataType: "jsonp",
        timeout: 10000,
        ansyc: false,
        success: function (responseData) {
            if(responseData.success) {
                var data = responseData.data;
                $("#courseCode option").each(function () {
                   if($(this).val() == data.coursecode) {
                       $(this).attr("selected", "true");
                   }
                });
                $("#classCode option").each(function () {
                    if($(this).val() == data.classcode) {
                        $(this).attr("selected", "true");
                    }
                });
                $("#datemin").val(data.createtime);
                $("#deadline").val(data.deadline);
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

function updateClassCourse() {
    $.ajax({
        type: "post",
        url: ppt_hw_url + "/classcourse/update",
        dataType: "jsonp",
        timeout: 10000,
        ansyc: false,
        data: $("#form-class-course-update").serialize(),
        success: function (responseData) {
            if(responseData.success) {
                swal({
                    title: "Good Jop!",
                    text: "课堂修改成功",
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

//查看课堂是否存在
function checkClassCourse() {
    var result = false;
    $.ajax({
        type: "post",
        url: ppt_hw_url + "/classcourse/check",
        dataType: "jsonp",
        timeout: 10000,
        data: $("#form-class-course-update").serialize(),
        success: function (responseData) {
            if(!responseData.success) {
                swal({
                    title: "Sorry!",
                    text:  responseData.message,
                    timer: 2000,
                    type: "error"
                });
            } else {
                result = true;
            }
        },
        error: function () {
            swal({
                title: "Sorry!",
                text: "网络忙,请稍后再试",
                timer: 2000,
                type: "error"
            });
        },
        complete: function () {
            if(result) {
                updateClassCourse();
            }
        }
    });
}
/*课程-查看*/
function course_show(title,url,id,w,h){
    layer_show(title,url,id,w,h);
}