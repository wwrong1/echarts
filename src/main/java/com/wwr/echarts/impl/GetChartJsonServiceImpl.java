package com.wwr.echarts.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wwr.echarts.api.GetChartJsonService;
import com.wwr.echarts.mapper.ChartDataMapper;
import com.wwr.echarts.model.ChartData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetChartJsonServiceImpl implements GetChartJsonService {

    @Autowired
    private ChartDataMapper chartDataMapper;

    @Override
    public JSONObject getChartJson(String title,String shape) {
        //获取图数据
        List<ChartData> dataList = chartDataMapper.getAll();
        long[][] chartData = new long[dataList.size()][3];
        for (int i = 0; i < dataList.size(); i++) {
            ChartData data = dataList.get(i);
            long[] m = {data.getX(), data.getY(), data.getZ()};
            chartData[i] = m;
        }

        String str1 = "{\n" +
                "            title: {\n" +
                "                text: '";

        String str2 = "'\n" +
                "            },\n" +
                "            tooltip: {},\n" +
                "            dataset: {\n" +
                "                // 提供一份数据。\n" +
                "                source: [";
        String barHead = str1 + title + str2;

        String str3 = "',\n" +
                "            x:'center'\n" +
                "        },\n" +
                "        tooltip : {\n" +
                "            trigger: 'item',\n" +
                "            formatter: \"{a} <br/> {c} ({d}%)\"\n" +
                "        },\n" +
                "        dataset:{\n" +
                "            source:[";
        String pieHead = str1 + title + str3;

        String pieTail = "]\n" +
                "        },\n" +
                "        legend: {\n" +
                "            orient: 'vertical',\n" +
                "            left: 'left',\n" +
                "            data: ['直接访问','邮件营销','联盟广告','视频广告','搜索引擎']\n" +
                "        },\n" +
                "        series : [\n" +
                "            {\n" +
                "                name: '访问来源',\n" +
                "                type: 'pie',\n" +
                "                radius : '55%',\n" +
                "                center: ['50%', '60%'],\n" +
                "\n" +
                "                itemStyle: {\n" +
                "                    emphasis: {\n" +
                "                        shadowBlur: 10,\n" +
                "                        shadowOffsetX: 0,\n" +
                "                        shadowColor: 'rgba(0, 0, 0, 0.5)'\n" +
                "                    }\n" +
                "                }\n" +
                "            }\n" +
                "        ]\n" +
                "    }";

        String barTail = " ]\n" +
                "            },\n" +
                "            legend: {\n" +
                "                // data:['销量',\"营业额\"]\n" +
                "            },\n" +
                "            xAxis: {type:'category'\n" +
                "            },\n" +
                "            yAxis: {},\n" +
                "            series: [\n" +
                "            {name: '销量', type: 'bar',symbolSize: 5},\n" +
                "            {name: '营业额', type: 'bar',symbolSize: 20}\n" +
                "\n" +
                "        ]\n" +
                "        }";
        String data = "";

        for (int i = 0; i < dataList.size(); i++) {
            data += dataList.get(i).toString();
        }
        JSONObject result= new JSONObject();

        if (shape.equals("bar")) {
             result= JSON.parseObject(barHead + data + barTail);
        }
        else if (shape.equals("pie")){
            System.out.println("=========");
            result = JSON.parseObject(pieHead + data + pieTail);

        }
        return result;
    }
}
