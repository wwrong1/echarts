package com.wwr.echarts.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wwr.echarts.service.GetChartService;
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
    public JSONObject getChartJson(int id) {

        Chart chart = chartMapper.getOne(id);
        String type = chart.getType();
        String title = chart.getTitle();

        //获取x轴上的参数并将其拼接成特定格式的字符串
        String[] xAxisList =chart.getxAxis().split(" ");
        String xAxisStr="";
        for(String i: xAxisList){
            xAxisStr+="\""+i+"\""+",";
        }

        //获取图数据
        List<ChartData> dataList = chartDataMapper.getAll();
        //根据所给类型取出相应的图表配置。
        StringBuffer option_str = new StringBuffer(chartOptionMapper.getOne(type).getOption());
        //给图表赋予标题
        option_str.insert(option_str.indexOf("text:")+6,title);
        //给图表传入数据
        if(type.equals("pie")) {
            String data = "";
            for (int i = 0; i < 4; i++) {
                data += "[\'"+xAxisList[i]+"\',"+dataList.get(i).getY()+"],";
            }
            option_str.insert(option_str.indexOf("source")+8,data);
        }
        else{
            String data = "";
            for (int i = 0; i < 4; i++) {
                data += dataList.get(i).toString();
            }
            option_str.insert(option_str.indexOf(",data: [") + 8, data);
            option_str.insert(option_str.indexOf("xAxis:{data: [")+14,xAxisStr);

        }
        System.out.println(option_str);
        //转换成json对象
        JSONObject result = JSON.parseObject(option_str.toString());
        return result;
    }

    @Override
    //存储特定chart的配置所需数据，并返回该条数据在数据库中的id
    public int saveChart(String org, String sql, String title, String type, String xAxis, String remake) {

        Chart chart = new Chart(org,sql,title,type,xAxis,remake);
        chartMapper.insertAndGetId(chart);
        int id = chart.getId();
        return id;

    }

    @Override
    //查看数据表中某个特定组织的全部chart配置数据
    public String queryCharts(String org) {
        List<Chart> l = chartMapper.getOrgAll(org);
        String s = "";
        for(Chart i: l){
            System.out.println(i.getSql_str());
            s+=i.toString();
        }
        return s;
    }

}
