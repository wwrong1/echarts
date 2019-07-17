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
    $.get("${url}",function (option) {
        myChart.setOption(option);
    })

    <%--$.ajax({--%>
    <%--    type: "get",--%>
    <%--    url: "${url}",&lt;%&ndash;"<%=request.getAttribute("url")%>"&ndash;%&gt;--%>
    <%--    data: {},--%>
    <%--    success: function (result) {--%>
    <%--        console.log(result);--%>
    <%--        myChart.setOption(result)--%>
    <%--    },--%>
    <%--    error: function (error) {--%>
    <%--        console.log(error);--%>
    <%--    }--%>
    <%--})--%>



    // var a  = {title : {text:'', x:'center'}, tooltip : {trigger: 'item', formatter: "{c} ({d}%)"}, dataset:{source:[[1],[2],[3],[4],[6]],}, legend: {orient: 'vertical', left: 'left',data: ['直接访问','邮件营销','联盟广告','视频广告','搜索引擎']}, series : [{type: 'pie', radius : '55%', center: ['50%', '60%'], itemStyle: {emphasis: {shadowBlur: 10, shadowOffsetX: 0, shadowColor: 'rgba(0, 0, 0, 0.5)'}}}]}
    // myChart.setOption(a);


</script>
</body>
</html>