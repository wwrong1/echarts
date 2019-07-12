<%@ page contentType="text/html;charset=utf8" language="java" isELIgnored="false"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>ECharts</title>
    <!-- 引入 echarts.js -->
    <script type="text/javascript" src="/js/echarts.js"></script>
    <script src="/js/echarts-gl.js"></script>
    <script src="/js/jquery-1.8.2.min.js"
           ></script>

</head>
<body>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="main" style="width: 600px;height:600px;"></div>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例

    var myChart = echarts.init(document.getElementById('main'));
    var a = {
        type: "get",
        url: "",
        data: {},
        success: function (result) {
            console.log(result);
            myChart.setOption(result)
        },
        error: function (error) {
            console.log(error);
        }
    };
    a.url="${url}"
        <%--"<%=request.getAttribute("url")%>"--%>
    $.ajax(a)

    // option = {
    //     title : {
    //         text: '某站点用户访问来源',
    //         subtext: "",
    //         x:'center'
    //     },
    //     tooltip : {
    //         trigger: 'item',
    //         formatter: "{a} <br/> {c} ({d}%)"
    //     },
    //     dataset:{
    //         source:[
    //             [ '直接访问',335],
    //             [ '邮件营销',310],
    //             [ '联盟广告',234],
    //             [ '视频广告',135],
    //             ['搜索引擎',1548]
    //             ,]
    //     },
    //     legend: {
    //         orient: 'vertical',
    //         left: 'left',
    //         data: ['直接访问','邮件营销','联盟广告','视频广告','搜索引擎']
    //     },
    //     series : [
    //         {
    //             name: '访问来源',
    //             type: 'pie',
    //             radius : '55%',
    //             center: ['50%', '60%'],
    //
    //             itemStyle: {
    //                 emphasis: {
    //                     shadowBlur: 10,
    //                     shadowOffsetX: 0,
    //                     shadowColor: 'rgba(0, 0, 0, 0.5)'
    //                 }
    //             }
    //         }
    //     ]
    // };
    // myChart.setOption(option)




    // var url='${url}'
    //
    // $.get(url).done(function (data){
    //     myChart.setOption(data)
    //     // handler(data)
    // })



    // function handler(data) {
    //     myChart.setOption({
    //         grid3D: {},
    //         xAxis3D: {},
    //         yAxis3D: {},
    //         zAxis3D: {},
    //         dataset: {
    //             dimensions: [
    //                 'NumOfThreads',
    //                 'SumOfPerBatch',
    //                 'Time',
    //             ],
    //             source:[[1,2,3,],[2,3,6],[1,4,5],[5,6,8]]
    //                 // data
    //
    //         },
    //         series: [
    //             {
    //                 type: 'scatter3D',
    //                 symbolSize: 2.5,
    //                 encode: {
    //                     x: 'NumOfThreads',
    //                     y: 'SumOfPerBatch',
    //                     z: 'Time',
    //                 }
    //             }
    //         ]
    //     })
    // }

</script>
</body>
</html>