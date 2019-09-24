/**
 * Created by JasonTang on 5/16/2017.
 */
// 请求统计数据
var chart = null; // 定义全局变量
// Create the chart
function init_chart() {
    chart = new Highcharts.Chart({
        chart: {
            renderTo: 'container',
            type: 'column',
            events: {
                load: requestData, // 图表加载完毕后执行的回调函数
                // drilldown: function (e) {
                //     if (!e.seriesOptions) {
                //         var chart = this,
                //             drilldowns = {
                //                 '计科1303班:数据结构': {
                //                     name: '计科1303班:数据结构',
                //                     // id: "计科1303班:数据结构",
                //                     data: [
                //                         [
                //                             "第一题",
                //                             24.13],
                //                         [
                //                             "第二题",
                //                             17.2]
                //                     ]
                //                 },
                //                 '计科1303班:嵌入式': {
                //                     name: "计科1303班:嵌入式",
                //                     // id: "计科1303班:嵌入式",
                //                     data: [
                //                         [
                //                             "第一题",
                //                             5],
                //                         [
                //                             "第二题",
                //                             4.32],
                //                         [
                //                             "第三题",
                //                             3.68],
                //                         [
                //                             "第四题",
                //                             2.96],
                //                         [
                //                             "第五题",
                //                             2.53]
                //                     ]
                //                 }
                //          },
                //         series = drilldowns[e.point.name];
                //         chart.addSeriesAsDrilldown(e.point, series);
                //     }
                // }
            }
        },
        title: {
            text: '课堂互动统计'
        },
        subtitle: {
            text: '点击柱形图查看详情',
        },
        xAxis: {
            type: 'category'
        },
        yAxis: {
            title: {
                text: '做对人数（人）'
            },
            min: 0
        },
        legend: {
            enabled: false
        },
        credits: {
            href: "#",
            text: "www.aiiclass.cn"
        },
        plotOptions: {
            series: {
                borderWidth: 0,
                dataLabels: {
                    enabled: true,
                    format: '{point.y} 人'
                }
            }
        },
        tooltip: {
            headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
            pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y} 人</b><br/>'
        },
        series: [{
            name: "作对人数",
            colorByPoint: true,
            data: []
        }],
        drilldown: {
            series: [{
                name: "计科1302班:数据结构",
                id: "计科1302班:数据结构",
                data: [
                    [
                        "第一题",
                        24],
                    [
                        "第二题",
                        17],
                    [
                        "第三题",
                        8],
                    [
                        "第四题",
                        5],
                    [
                        "第五题",
                        1],
                    [
                        "第六题",
                        0]
                ]
            }, {
                name: "计科1302班:嵌入式系统及应用",
                id: "计科1302班:嵌入式系统及应用",
                data: [
                    [
                        "第一题",
                        5],
                    [
                        "第二题",
                        4],
                    [
                        "第三题",
                        3],
                    [
                        "第四题",
                        2],
                    [
                        "第五题",
                        2],
                    [
                        "第六题",
                        1],
                    [
                        "第七题",
                        1],
                    [
                        "第八题",
                        0],
                    [
                        "第九题",
                        0],
                    [
                        "第十题",
                        0],
                    [
                        "第十一题",
                        0]
                ]
            }, {
                name: "计科1303班:数据结构",
                id: "计科1303班:数据结构",
                data: [
                    [
                        "第一题",
                        2],
                    [
                        "第二题",
                        2],
                    [
                        "第三题",
                        2],
                    [
                        "第四题",
                        1],
                    [
                        "第五题",
                        1],
                    [
                        "第六题",
                        0],
                    [
                        "第七题",
                        0],
                    [
                        "第八题",
                        0]
                ]
            }, {
                name: "计科1302班:计算机组成原理",
                id: "计科1302班:计算机组成原理",
                data: [
                    [
                        "第一题",
                        2],
                    [
                        "第二题",
                        0],
                    [
                        "第三题",
                        0],
                    [
                        "第四题",
                        0],
                    [
                        "第五题",
                        0],
                    [
                        "第六题",
                        0],
                    [
                        "第七题",
                        0]
                ]
            }, {
                name: "计科1301班:计算机组成原理",
                id: "计科1301班:计算机组成原理",
                data: [
                    [
                        "第一题",
                        1],
                    [
                        "第二题",
                        2],
                    [
                        "第三题",
                        3],
                    [
                        "第四题",
                        4]
                ]
            }, {
                name: "软工1301班:机器学习",
                id: "软工1301班:机器学习",
                data: [
                    [
                        "第一题",
                        2],
                    [
                        "第二题",
                        1],
                    [
                        "第三题",
                        1],
                    [
                        "第四题",
                        0]
                ]
            }, {
                name: "计科1303班:嵌入式系统及应用",
                id: "计科1303班:嵌入式系统及应用",
                data: [
                    [
                        "第一题",
                        22],
                    [
                        "第二题",
                        21],
                    [
                        "第三题",
                        22],
                    [
                        "第四题",
                        35]
                ]
            }, {
                name: "计科1303班:计算机组成原理",
                id: "计科1303班:计算机组成原理",
                data: [
                    [
                        "第一题",
                        31],
                    [
                        "第二题",
                        23],
                    [
                        "第三题",
                        23],
                    [
                        "第四题",
                        58]
                ]
            }, {
                name: "计科1303班:Python基础教程",
                id: "计科1303班:Python基础教程",
                data: [
                    [
                        "第一题",
                        6],
                    [
                        "第二题",
                        12],
                    [
                        "第三题",
                        8],
                    [
                        "第四题",
                        5]
                ]
            }, {
                name: "计科1301班:Python基础教程",
                id: "计科1301班:Python基础教程",
                data: [
                    [
                        "第一题",
                        2],
                    [
                        "第二题",
                        3],
                    [
                        "第三题",
                        1],
                    [
                        "第四题",
                        2]
                ]
            }, {
                name: "计科1302班:人工智能",
                id: "计科1302班:人工智能",
                data: [
                    [
                        "第一题",
                        1],
                    [
                        "第二题",
                        2],
                    [
                        "第三题",
                        1]
                ]
            }
            ]
        }
    });
}

function requestData() {
    var teacherCode = $(window.parent.document).find('#usercode')[0].value;
    $.ajax({
        type: "post",
        dataType: "jsonp",
        jsonp: "callback",
        ansy: false,
        url: ppt_hw_url + "/classhd/getChartData",
        data: {
            "classCreator": teacherCode
        },
        timeout: 10000,
        success: function (responseData) {
            if (responseData.success) {
                var seriesData = responseData.data;
                // console.log(seriesData);
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