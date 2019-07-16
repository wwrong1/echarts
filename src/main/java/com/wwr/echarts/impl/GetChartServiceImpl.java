package com.wwr.echarts.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wwr.echarts.api.GetChartService;
import com.wwr.echarts.mapper.ChartDataMapper;
import com.wwr.echarts.mapper.ChartMapper;
import com.wwr.echarts.mapper.ChartOptionMapper;
import com.wwr.echarts.model.Chart;
import com.wwr.echarts.model.ChartData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetChartServiceImpl implements GetChartService {

    @Autowired
    private ChartDataMapper chartDataMapper;

    @Autowired
    private ChartOptionMapper chartOptionMapper;

    @Autowired
    private ChartMapper chartMapper;

    @Override
    public JSONObject getChartJson(String title,String type) {

        //获取图数据
        List<ChartData> dataList = chartDataMapper.getAll();
        //根据所给类型取出相应的图表配置。
        StringBuffer option_str = new StringBuffer(chartOptionMapper.getOne(type).getOption());
        //给图表赋予标题
        option_str.insert(option_str.indexOf("text:")+6,title);
        //给图表传入数据
        if(type.equals("pie")) {
            String data = "";
            for (int i = 0; i < 100; i++) {
                data += "['test',"+dataList.get(i).getY()+"],";
            }
            option_str.insert(option_str.indexOf("source")+8,data);
        }
        else{
            String data = "";
            for (int i = 0; i < 100; i++) {
                data += dataList.get(i).toString();
            }
            option_str.insert(option_str.indexOf(",data") + 8, data);
        }
        //转换成json对象
        JSONObject result = JSON.parseObject(option_str.toString());
        return result;
    }

    @Override
    public int saveChart(String org, String sql, String title, String type, String xAxis, String remake) {

        Chart chart = new Chart(org,sql,title,type,xAxis,remake);
        chartMapper.insertAndGetId(chart);
        int id = chart.getId();
        return id;

    }

    @Override
    public String queryChart(String org) {
        List<Chart> l = chartMapper.getOrgAll(org);
        String s = "";
        for(Chart i: l){
            System.out.println(i.getSql_str());
            s+=i.toString();
        }
        return s;
    }

}
