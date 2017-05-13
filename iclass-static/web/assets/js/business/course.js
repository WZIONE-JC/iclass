/**
 * Created by radishmaster on 07/04/17.
 */

//获取课程信息
function getCourseByTeacherCode() {
    var teacherCode = $(window.parent.document).find('#classcreator')[0].value;
    $.ajax({
        type: "post",
        dataType: "jsonp",
        jsonp: "callback",
        url: ppt_hw_url + "/course/get",
        data: {
            "teacherCode" : teacherCode
        },
        async:false,
        timeout: 10000,
        success: function (responseData) {
            if (responseData.success) {
                var courses = responseData.data;
                var size = courses.length;
                var option;
                for(var i = 0; i < size; i ++) {
                    option = new Option(courses[i].course.coursename, courses[i].course.coursecode);
                    $("#courseCode")[0].add(option);
                }
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
                text: "网络繁忙，请稍后再试",
                timer: 2000,
                type: "error"
            });
        }
    });
}


function courseTableHandler(formId, url) {
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
    $("#coursecreator").val(usercode);
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
                data: "course.coursecode",
                render: function (data, type, row, meta) {
                    return "<span class='label label-success radius'>" + data + "</span>";
                }
            },
            {
                data: "course.coursename",
            },
            {
                data: "course.coursedescription",
            },
            {
                data: "course.coursegrade",
            },
            {
                data: "teacherName",
                render: function (data, type, row, meta) {
                    return "<span class='label label-secondary radius'>" + data + "</span>";
                }
            },
            {
                data: "course.coursecreatetime",
            },

            {
                data: "course.coursestatus",
                render: function (data, type, row, meta) {
                    if (data == 0) {
                        return "<span class='label label-default radius' title='课程已下架'>已下架</span>";
                    } else if (data == 1) {
                        return "<span class='label label-success radius' title='课程已发布'>已发布</span>";
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
            targets: [-2, -1],
            "createdCell": function (td, cellData, rowData, row, col) {
                var id = rowData.course.courseid;
                var status = rowData.course.coursestatus;
                if (col == 7) {
                    $(td).addClass("td-status");
                }
                if (col == 8) {
                    $(td).addClass("td-manage");
                    if(status == 1) {
                        $(td).html("<a style='text-decoration:none' class='ml-5' title='编辑课程' onclick=edit('编辑课程','course-update.html','"+id+"','570','400')><i class='Hui-iconfont'>&#xe6df;</i></a> <a style='text-decoration:none' class='ml-5' onClick='stop(this,"+id+")'  title='下架'><i class='Hui-iconfont'>&#xe6de;</i></a>");
                    } else {
                        $(td).html("<a style='text-decoration:none' class='ml-5' title='编辑课程' onclick=edit('编辑课程','course-update.html','"+id+"','570','400')><i class='Hui-iconfont'>&#xe6df;</i></a> <a style='text-decoration:none' class='ml-5' onClick='start(this,"+id+")' title='发布'><i class='Hui-iconfont'>&#xe603;</i></a>");
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

        }
    });
    return table;
}
//添加课程
function addCourse() {
    if (!$("#msg").hasClass("error")) {
        $.ajax({
            type: "post",
            url: ppt_hw_url + "/course/save",
            dataType: "jsonp",
            timeout: 10000,
            data: $("#form-course-add").serialize(),
            success: function (responseData) {
                if(responseData.success) {
                    swal({
                        title: "Good Jop!",
                        text: "课程添加成功",
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
}
//检查courseCode是否存在
function check() {
    var coursecode = $("#coursecode").val();
    if($.trim(coursecode) != '' && coursecode.length == 4) {
        $.ajax({
            type: "post",
            url: ppt_hw_url + "/course/check/"+coursecode,
            dataType: "jsonp",
            timeout: 10000,
            success: function (responseData) {
                if(!responseData.success) {
                    $("#msg").addClass("error");
                    $("#msg").addClass("class-add-msg").css("color","red").text(responseData.data.msg);
                } else {
                    $("#msg").removeClass("error");
                    $("#msg").removeClass("class-add-msg").text("");
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
}


//获取课程数据,根据id或者code
function showCourse(url,param) {
    $.ajax({
        type: "post",
        url: ppt_hw_url + url+param,
        dataType: "jsonp",
        timeout: 10000,
        success: function (responseData) {
            if(responseData.success) {
                var data = responseData.data;
                $("#coursecode").val(data.coursecode);
                $("#coursename").val(data.coursename);
                $("#coursedescription").val(data.coursedescription);
                $("#coursegrade").val(data.coursegrade);

                $("#coursecode").text(data.coursecode);
                $("#coursename").text(data.coursename);
                $("#coursedescription").text(data.coursedescription);
                $("#coursecreatetime").text(data.coursecreatetime);
                var status = data.coursestatus;
                if (status == 1) {
                    status = "<span class='label label-success radius' title='已发布'>已发布</span>";
                }
                if (status == 0) {
                    status = "<span class='label label-defaunt radius' title='已下架'>已下架</span>";
                }
                $("#coursestatus").html(status);
                $("#coursegrade").text(data.coursegrade);
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

function updateCourse() {
    $.ajax({
        type: "put",
        url: ppt_hw_url + "/course/update",
        dataType: "jsonp",
        timeout: 10000,
        data: $("#form-course-update").serialize(),
        success: function (responseData) {
            if(responseData.success) {
                swal({
                    title: "Good Jop!",
                    text: "课程信息修改成功",
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