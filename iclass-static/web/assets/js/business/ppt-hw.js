/**
 * Created by JasonTang on 3/12/2017.
 */
//获取课程信息
function getCourseByTeacherCode() {
    var teacherCode = $(window.parent.document).find('#usercode')[0].value;
    // alert(teacherCode);
    $.ajax({
        type: "post",
        dataType: "jsonp",
        jsonp: "callback",
        url: ppt_hw_url + "/course/get",
        data: {
          "teacherCode" : teacherCode,
            "start": 0,
            "length": 1000
        },
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
// 监听课程选择事件
$(function () {
    $("#courseCode").on("change", function () {
       var courseCode = $("#courseCode").val();
       if(courseCode != null && courseCode != "") {
           //先清空Options
           $("#classCode")[0].length = 0;
           getClassByCourseCode(courseCode);
       } else {
           swal({
               title: "Sorry!",
               text: "请先选择课程信息",
               timer: 2000,
               type: "error"
           });
       }
    })
});

// 表格数据
function classTableHandler(formId, url, fileType) {
//http://datatables.club/upgrade/1.10-convert.html
//http://datatables.club/reference/option/
//https://datatables.net/reference/option/columns.createdCell
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
    var columns =
        [
            {
                data: null,
                orderable: false,
                render: function (data, type, row, meta) {
                    return "<span class='label label-default radius orderNum'>" + data + "</span>";
                }
            },
            {
                data: "classRoomName",
            },
            {
                data: "aClass.classname"
            },
            {
                data: "course.coursename",
            },
            {
                data: "teacherName",
                render: function (data, type, row, meta) {
                    return "<span class='label label-default radius'>" + data + "</span>";
                }
            },
            {
                data: null,
            },
            {
                data: "creatTime",
            },
            {
                data: "deadline",
            },
            {
                data: "students.length",
                render: function (data, type, row, meta) {
                    return "<span class='label label-default radius'>" + data + "</span>";
                }
            },
            {
                data: "status",
                render: function (data, type, row, meta) {
                    if (data == 0) {
                       return "<span class='label label-default radius' title='课程已关闭'>已关闭</span>";
                    } else if (data == 1) {
                        return "<span class='label label-success radius' title='课程已发布'>已发布</span>";
                    }
                }
            },
            {
                data: null,
                orderable: false,
                width: "5%"
            }];

    var columnDefs =
        [{
            targets: [1, 2, 3, 5, -2, -1],
            "createdCell": function (td, cellData, rowData, row, col) {
                var classcode = rowData.aClass.classcode;
                var classname = rowData.aClass.classname;
                var coursename = rowData.course.coursename;
                var coursecode = rowData.course.coursecode;
                var id = rowData.classcourseId;
                var fileCount = rowData.fileCount;
                var status = rowData.status;
                if (col == 1) {
                    $(td).css("text-align", "left");
                }
                if (col == 2) {
                    if (fileType == 0) {
                        $(td).wrapInner("<span style='cursor:pointer' title='查看班级信息' class='label label-secondary radius' onclick=class_show('" + classname + "','class-show.html','" + classcode + "','360','400')></span>");
                    } else {
                        $(td).wrapInner("<span style='cursor:pointer' title='查看班级信息' class='label label-success radius' onclick=class_show('" + classname + "','class-show.html','" + classcode + "','360','400')></span>");
                    }

                }
                if (col == 3) {
                    if (fileType == 0) {
                        $(td).wrapInner("<span style='cursor:pointer' title='查看课程信息' class='label label-success radius' onclick=course_show('" + coursename + "','course-show.html','" + coursecode + "','360','400')></span>");
                    } else {
                        $(td).wrapInner("<span style='cursor:pointer' title='查看课程信息' class='label label-secondary radius' onclick=course_show('" + coursename + "','course-show.html','" + coursecode + "','360','400')></span>");
                    }
                }
                if (col == 5) {
                    var title;
                    if (fileCount > 0) {
                        if(fileType == 0) {
                            title = "查看作业";
                        }
                        if(fileType == 1) {
                            title = "查看课件";
                        }
                        $(td).html("<span style='cursor:pointer' onclick=file_list_show('850','400','"+classcode+"','"+coursecode+"','"+fileType+"') title='"+title+"'><span class='label radius label-primary'>"+ title +"</span></span>");
                    } else{
                        if(fileType == 0) {
                            $(td).html("没有作业");
                        }
                        if(fileType == 1) {
                            $(td).html("没有课件");
                        }
                    }
                }
                if (col == 9) {
                    $(td).addClass("td-status");
                }
                if (col == 10) {
                    $(td).addClass("td-manage");
                    if (status == 1) {
                        $(td).html("<a style='text-decoration:none' class='ml-5' title='编辑课堂' onclick=class_update('编辑课堂','class-update.html','"+classcode+"','570','400')><i class='Hui-iconfont'>&#xe6df;</i></a> <a style='text-decoration:none' class='ml-5' onClick='stop(this,"+id+")'  title='关闭'><i class='Hui-iconfont'>&#xe6de;</i></a>");
                    } else {
                        $(td).html("<a style='text-decoration:none' class='ml-5' title='编辑课堂' onclick=class_update('编辑课堂','class-update.html','"+classcode+"','570','400')><i class='Hui-iconfont'>&#xe6df;</i></a> <a style='text-decoration:none' class='ml-5' onClick='start(this,"+id+")' title='发布'><i class='Hui-iconfont'>&#xe603;</i></a>");
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
        destroy: true, //每次请求都销毁列表
        ajax: {
            url: ppt_hw_url + url,
            dataType: "jsonp",
            data: {
                "classCreator": usercode,
                "fileType": fileType,
                "classCourseId": $("#classcourse").val()
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
                $("#hw-info-table_processing").hide();
            }
        },
        columns: columns,
        "preDrawCallback": function () {
            $(".dataTables_filter").hide();
            // alert("2");
        },
        "initComplete": function (settings, json) {
            // alert("1");
        },
        "createdRow": function (row, data, index) {
            //http://datatables.club/reference/option/createdRow.html
            //row 是某一行 ， data是 ajax返回数据 ， index是行标(0)
            //行渲染回调,在这里可以对该行dom元素进行任何操作
            //给当前行加样式
            $(row).find(".orderNum").text((index+1));
            $(row).addClass("text-c");
        },

        "drawCallback": function (data, settings) {
            //http://datatables.club/manual/daily/2016/09/04/option-rowCallback.html
            //渲染完毕后的回调
            //清空全选状态
            // $(":checkbox[name='cb-check-all']").prop('checked', false);
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


// 文件表格数据
function fileTableHandler(classCode, courseCode, fileType) {
    var formId = "#file-info-table";
    // alert("表格数据:"+classCode+","+courseCode+","+fileType);
    $(formId).prop("width", "100%");
    var columns =
        [
            {
                data: null,
                orderable: false,
                render: function (data, type, row, meta) {
                    return "<span class='label label-default radius orderNum'>" + data + "</span>";
                }
            },
            {
                data: "iclassfile.filename"
            },
            {
                data: "iclassfile.filedesc",
            },
            {
                data: "teacherName",
                render: function (data, type, row, meta) {
                    return "<span class='label label-success radius'>" + data + "</span>";
                }
            },
            {
                data: "iclassfile.filecreatetime",
            },
            {
                data: "iclassfile.filedownloadtime",
                render: function (data, type, row, meta) {
                    return "<span class='label label-warning radius'>" + data + "</span>";
                }
            },
            {
                data: "iclassfile.filestatus",
                render: function (data, type, row, meta) {
                    if (data == 0) {
                        return "<span class='label label-default radius' title='已关闭'>已关闭</span>";
                    } else if (data == 1) {
                        return "<span class='label label-success radius' title='已发布'>已发布</span>";
                    }
                }
            },
            {
                data: null,
                orderable: false,
                width: "5%"
            }];

    var columnDefs =
        [{
            targets: [1, -2, -1],
            "createdCell": function (td, cellData, rowData, row, col) {
                var fileCode = rowData.iclassfile.filecode;
                var id = rowData.iclassfile.fileid;
                var status = rowData.iclassfile.filestatus;
                if (col == 1) {
                    $(td).wrapInner("<a style='cursor:pointer' target='_blank' href='"+ppt_hw_url+"/file/download?fileCode="+fileCode+"&fileType="+fileType+"' onclick='updateDownloadTime(this); return false;' title='点击下载'></a>");
                }
                if (col == 6) {
                    $(td).addClass("td-status");
                }
                if (col == 7) {
                    $(td).addClass("td-manage");
                    if (status == 1) {
                        $(td).html("<a style='text-decoration:none' class='ml-5' title='编辑' onclick=class_update('编辑',fileConfig,'"+fileCode+"','500','370')><i class='Hui-iconfont'>&#xe6df;</i></a> <a style='text-decoration:none' class='ml-5' onClick='stop(this,"+id+")'  title='关闭'><i class='Hui-iconfont'>&#xe6de;</i></a>");
                    } else {
                        $(td).html("<a style='text-decoration:none' class='ml-5' title='编辑' onclick=class_update('编辑','file-update.html','"+fileCode+"','500','370')><i class='Hui-iconfont'>&#xe6df;</i></a> <a style='text-decoration:none' class='ml-5' onClick='start(this,"+id+")' title='发布'><i class='Hui-iconfont'>&#xe603;</i></a>");
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
        columns: columns,
        ajax: {
            url: ppt_hw_url + "/ppthw/getPPTFileInfo",
            dataType: "jsonp",
            data: {
                "classCode": classCode,
                "courseCode": courseCode,
                "fileType": fileType
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

        "preDrawCallback": function () {
        },
        "initComplete": function (settings, json) {
            $(".dataTables_filter").hide();
        },
        "createdRow": function (row, data, index) {
            //http://datatables.club/reference/option/createdRow.html
            //row 是某一行 ， data是 ajax返回数据 ， index是行标(0)
            //行渲染回调,在这里可以对该行dom元素进行任何操作
            //给当前行加样式
            $(row).find(".orderNum").text((index+1));
            $(row).addClass("text-c");
        },

        "drawCallback": function (data, settings) {
            //http://datatables.club/manual/daily/2016/09/04/option-rowCallback.html
            //渲染完毕后的回调
            //清空全选状态
            // $(":checkbox[name='cb-check-all']").prop('checked', false);
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
            // alert("drawCallback");
        }
    });
    return table;
}

function updateDownloadTime(obj) {
    var href =  obj.href;
    window.open(href);
    setTimeout(function(){
        $("#btn-refresh").click();
    },1000);
}

/*课件-添加*/
function file_add(title,url){
    var index = layer.open({
        type: 2,
        title: title,
        content: url
    });
    layer.full(index);
}
/**
 * 没有使用
 * @param fileCode
 * @param fileType
 */
function download(fileCode,fileType) {
    // console.log(fileCode);
    var $form = $("#download-form").attr("action", ppt_hw_url+"/file/download");
    $("#fileCode").val(fileCode);
    $("#fileType").val(fileType);
    $form.submit();
}
/*课程-查看*/
function course_show(title,url,id,w,h){
    layer_show(title,url,id,w,h);
}
/*课堂-查看*/
function class_show(title,url,id,w,h){
    layer_show(title,url,id,w,h);
}
/*文件列表查看*/
function file_list_show(w,h,courseCode,classCode,fileType){
    layer_show_2("文件列表(点击文件名下载)","file-list-show.html",w,h,courseCode,classCode,fileType);
}
/*课堂-编辑*/
function class_update(title,url,id,w,h){
    layer_show(title,url,id,w,h);
}

