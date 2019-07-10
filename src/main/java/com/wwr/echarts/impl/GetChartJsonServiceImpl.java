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
    public JSONObject getChartJson() {
        //获取图数据
        List<ChartData> dataList = chartDataMapper.getAll();
        long [][] chartData = new long[dataList.size()][3];
        for(int i =0; i <dataList.size();i++){
            ChartData data = dataList.get(i);
            long [] m = {data.getX(),data.getY(),data.getZ()};
            chartData[i] = m;
        }
        String head = "{\n" +
                "            grid3D: {},\n" +
                "            xAxis3D: {},\n" +
                "            yAxis3D: {},\n" +
                "            zAxis3D: {},\n" +
                "            dataset: {\n" +
                "                dimensions: [\n" +
                "                    'NumOfThreads',\n" +
                "                    'SumOfPerBatch',\n" +
                "                    'Time',\n" +
                "                ],\n" +
                "                source:[";

        String data = "";

        for(int i=0;i<dataList.size();i++){
            data += dataList.get(i).toString();
        }

        String tail = "]\n},\n" +
                "            series: [\n" +
                "                {\n" +
                "                    type: 'scatter3D',\n" +
                "                    symbolSize: 2.5,\n" +
                "                    encode: {\n" +
                "                        x: 'NumOfThreads',\n" +
                "                        y: 'SumOfPerBatch',\n" +
                "                        z: 'Time',\n" +
                "                    }\n" +
                "                }\n" +
                "            ]\n" +
                "        }";

        JSONObject result = JSON.parseObject(head+data+tail);
        return result;
    }
}
