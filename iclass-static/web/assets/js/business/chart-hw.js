/**
 * Created by JasonTang on 5/15/2017.
 */
function chart_init() {
    $('#container').highcharts({
        chart: {
            type: 'column'
        },
        title: {
            text: '作业统计'
        },
        subtitle: {
            text: '当前班级：计科1302班:计算机组成原理',
            useHTML: true
        },
        xAxis: {
            categories: [
                '第1次课',
                '第2次课',
                '第3次课',
                '第4次课',
                '第5次课',
                '第6次课',
                '第7次课',
                '第8次课',
                '第9次课',
                '第10次课',
                '第11次课',
                '第12次课'
            ]
        },
        yAxis: {
            min: 0,
            title: {
                text: '人数'
            }
        },
        credits: {
            href: "#",
            text: "www.aiiclass.cn"
        },
        tooltip: {
            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
            '<td style="padding:0"><b>{point.y} 人</b></td></tr>',
            footerFormat: '</table>',
            shared: true,
            useHTML: true
        },
        plotOptions: {
            column: {
                pointPadding: 0.2,
                borderWidth: 0
            }
        },
        series: [{
            name: '已交',
            data: [49, 31, 35, 39, 34, 37, 32, 31, 32, 33, 38, 54]
        }, {
            name: '未交',
            data: [3, 7, 9, 9, 10, 8, 10, 10, 9, 3, 6, 3],
            color: "#F7A35C"
        }]
    });
}