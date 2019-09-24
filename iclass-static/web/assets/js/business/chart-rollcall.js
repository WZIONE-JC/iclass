/**
 * Created by JasonTang on 5/15/2017.
 */
// 获取点名次数
$("#classcourse").change(function () {
    $.ajax({
        type: "post",
        dataType: "jsonp",
        jsonp: "callback",
        url: ppt_hw_url + "/rollcall/getTimes",
        data: {
            "classCourseId": $("#classcourse").val()
        },
        timeout: 10000,
        success: function (responseData) {
            if (responseData.success) {
                var datas = responseData.data;
                var size = datas.length;
                var option;
                $("#times")[0].length = 1;
                for(var i = 0; i < size; i ++) {
                    option = new Option(datas[i].name, datas[i].value);
                    $("#times")[0].add(option);
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
});
// 监听选择次数,初始化图表数据
$("#times").change(function () {
    chart_init();
});

var chart = null;
function chart_init() {
    chart = new Highcharts.Chart({
        chart: {
            renderTo: 'container',
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false,
            spacing : [100, 0 , 40, 0],
            events: {
                load: requestData, // 图表加载完毕后执行的回调函数
            }
        },
        title: {
            floating:true,
            text: '点名统计'
        },
        subtitle: {
            text: ''
        },
        credits: {
            href: "#",
            text: "www.aiiclass.cn"
        },
        tooltip: {
            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    format: '<b>{point.name}</b>: {point.percentage:.1f}%',
                    style: {
                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                    }
                },
                point: {
                    events: {
                        mouseOver: function(e) {  // 鼠标滑过时动态更新标题
                            // 标题更新函数，API 地址：https://api.hcharts.cn/highcharts#Chart.setTitle
                            chart.setTitle({
                                text: e.target.name+ '\t'+ e.target.y + ' 人'
                            });
                        }
                        //,
                        // click: function(e) { // 同样的可以在点击事件里处理
                        //     chart.setTitle({
                        //         text: e.point.name+ '\t'+ e.point.y + ' %'
                        //     });
                        // }
                    }
                },
            }
        },
        series: [{
            type: 'pie',
            innerSize: '80%',
            name: '出勤率',
            data: []
        }]
    }, function(c) {
        // 环形图圆心
        var centerY = c.series[0].center[1],
            titleHeight = parseInt(c.title.styles.fontSize);
        c.setTitle({
            y:centerY + titleHeight/2
        });
        chart = c;
    });
}
// {
//     name: '已到',
//         y: 28,
//     color: "#F15C80"
// },
// {
//     name: '缺勤',
//         y: 3,
//     sliced: true,
//     selected: true,
//     color: "#90ED7D"
// }
function requestData() {
    var teacherCode = $(window.parent.document).find('#usercode')[0].value;
    console.log(teacherCode);
    $.ajax({
        type: "post",
        dataType: "jsonp",
        jsonp: "callback",
        ansy: false,
        url: ppt_hw_url + "/rollcall/show",
        data: {
            "teacherCode": teacherCode,
            "classCourseId": $("#classcourse").val(),
            "times": $("#times").val()
        },
        timeout: 10000,
        success: function (responseData) {
            if (responseData.success) {
                var seriesData = responseData.data;
                console.log(seriesData);
                // 新增点操作
                //具体的参数详见：https://api.hcharts.cn/highcharts#Series.addPoint
                for (var i = 0; i < seriesData.length; i ++) {
                    chart.series[0].addPoint(seriesData[i]);
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