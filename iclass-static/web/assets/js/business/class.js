/**
 * Created by radishmaster on 07/04/17.
 */


//获取课堂信息
function getUnrelatedClassByCourseCode(courseCode) {
    var teacherCode = $(window.parent.document).find('#classcreator')[0].value;
    $.ajax({
        type: "post",
        dataType: "jsonp",
        jsonp: "callback",
        url: ppt_hw_url + "/class/getUnrelatedClass",
        data: {
            "classCreator": teacherCode,
            "courseCode": courseCode
        },
        timeout: 3000,
        success: function (responseData) {
            if (responseData.success) {
                var classes = responseData.data;
                var size = classes.length;
                var option;
                for(var i = 0; i < size; i ++) {
                    option = new Option(classes[i].classname, classes[i].classcode);
                    $("#classCode")[0].add(option);
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

//获取课堂信息
function getClassByCourseCode(courseCode) {
    $.ajax({
        type: "post",
        dataType: "jsonp",
        jsonp: "callback",
        url: ppt_hw_url + "/class/getClassesByCourseCode",
        data: {
            "courseCode" : courseCode,
        },
        timeout: 3000,
        success: function (responseData) {
            if (responseData.success) {
                var classes = responseData.data;
                var size = classes.length;
                var option;
                for(var i = 0; i < size; i ++) {
                    option = new Option(classes[i].aClass.classname, classes[i].aClass.classcode);
                    $("#classCode")[0].add(option);
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

//获取课堂信息
function getClassByCourseCodeNoLimit() {
    var classCreator = $(window.parent.document).find('#classcreator')[0].value;
    $.ajax({
        type: "post",
        dataType: "jsonp",
        jsonp: "callback",
        url: ppt_hw_url + "/class/getClass",
        data: {
            "classCreator" : classCreator,
            "isLimit": false
        },
        async:false,
        timeout: 3000,
        success: function (responseData) {
            if (responseData.success) {
                var classes = responseData.data;
                var size = classes.length;
                var option;
                for(var i = 0; i < size; i ++) {
                    option = new Option(classes[i].aClass.classname, classes[i].aClass.classcode);
                    $("#classCode")[0].add(option);
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
function classTableHandler(formId, url) {
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
                data: "aClass.classid",
                render: function (data, type, row, meta) {
                    return "<input type='checkbox' value=" + data + " name='classid'>";
                }
            },
            {
                data: "aClass.classcode",
                render: function (data, type, row, meta) {
                    return "<span class='label label-secondary radius'>" + data + "</span>";
                }
            },
            {
                data: "aClass.classname"
            },
            {
                data: "aClass.classdescription"
            },
            {
                data: "teacherName",
                render: function (data, type, row, meta) {
                    return "<span class='label label-warning radius'>" + data + "</span>";
                }
            },
            {
                data: "aClass.classcreatetime"
            },
            {
                data: "aClass.classstatus",
                render: function (data, type, row, meta) {
                    if (data == 0) {
                        return "<span class='label label-default radius' title='课堂已下架'>已下架</span>";
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
            targets: [-2, -1],
            "createdCell": function (td, cellData, rowData, row, col) {
                var classcode = rowData.aClass.classcode;
                var id = rowData.aClass.classid;

                if (col == 6) {
                    $(td).addClass("td-status");
                }
                if (col == 7) {
                    $(td).addClass("td-manage");
                    $(td).html("<a style='text-decoration:none' class='ml-5' title='编辑课堂' onclick=edit('编辑课堂','class-update.html','"+id+"','570','400')><i class='Hui-iconfont'>&#xe6df;</i></a> <a style='text-decoration:none' class='ml-5' onClick='stop(this,"+classcode+")'  title='下架课堂'><i class='Hui-iconfont'>&#xe6de;</i></a>");
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
            $("#classcreator").val(usercode);
        }
    });
    return table;
}

function addClass() {
    if (!$("#msg").hasClass("error")) {
        $.ajax({
            type: "post",
            url: ppt_hw_url + "/class/save",
            dataType: "jsonp",
            timeout: 3000,
            data: $("#form-class-add").serialize(),
            success: function (responseData) {
                if(responseData.success) {
                    swal({
                        title: "Good Jop!",
                        text: "课堂添加成功",
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
function check() {
    var classcode = $("#classcode").val();
    if($.trim(classcode) != '' && classcode.length == 4) {
        $.ajax({
            type: "post",
            url: ppt_hw_url + "/class/check/"+classcode,
            dataType: "jsonp",
            timeout: 3000,
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

//获取课堂数据,根据id
function showClass(id) {
    $.ajax({
        type: "post",
        url: ppt_hw_url + "/class/get/"+id,
        dataType: "jsonp",
        timeout: 3000,
        success: function (responseData) {
            if(responseData.success) {
                var data = responseData.data;
                $("#classcode").val(data.classcode);
                $("#classname").val(data.classname);
                $("#classdescription").val(data.classdescription);
                $("#classcreator").val(data.classcreator);
                $("#courseCode").val(data.classcoursecode);
                $("#datemin").val(data.classcreatetime);
                $("#classdeadline").val(data.classdeadline);
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

function updateClass() {
    $.ajax({
        type: "put",
        url: ppt_hw_url + "/class/update",
        dataType: "jsonp",
        timeout: 3000,
        data: $("#form-class-update").serialize(),
        success: function (responseData) {
            if(responseData.success) {
                swal({
                    title: "Good Jop!",
                    text: "课堂信息修改成功",
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