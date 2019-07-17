package com.wwr.echarts.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wwr.echarts.mapper.ChartDataMapper;
import com.wwr.echarts.mapper.ChartMapper;
import com.wwr.echarts.mapper.ChartOptionMapper;
import com.wwr.echarts.model.Chart;
import com.wwr.echarts.model.ChartData;
import com.wwr.echarts.service.GetChartService;
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

//    @Override
//    public JSONObject getChartJson(int id) {
//
//        Chart chart = chartMapper.getOne(id);
//        String type = chart.getType();
//        String title = chart.getTitle();
//
//        //获取x轴上的参数并将其拼接成特定格式的字符串
//        String[] xAxisList =chart.getX_axis().split(" ");
//        String xAxisStr="";
//
//        for(String i: xAxisList){
//            xAxisStr+="\""+i+"\""+",";
//        }
//
//        //获取图数据
//        List<ChartData> dataList = chartDataMapper.getAll();
//        //根据所给类型取出相应的图表配置。
//        StringBuffer option_str = new StringBuffer(chartOptionMapper.getOne(type).getOption());
//        //给图表赋予标题
//        option_str.insert(option_str.indexOf("text:")+6,title);
//        //给图表传入数据
//        String data = "";
//        if(type.equals("pie")) {
//
//            for (int i = 0; i < 4; i++) {
//                data += "[\'"+xAxisList[i]+"\',"+dataList.get(i).getY()+"],";
//            }
//
//            option_str.insert(option_str.indexOf("source")+8,data);
//        }
//        else{
//
//            for (int i = 0; i < 4; i++) {
//                data += dataList.get(i).toString();
//            }
//
//            option_str.insert(option_str.indexOf(",data: [") + 8, data);
//            option_str.insert(option_str.indexOf("data:[")+6,xAxisStr);
//
//        }
//        System.out.println(option_str);
//        //转换成json对象
//        JSONObject result = JSON.parseObject(option_str.toString());
//        return result;
//    }

    @Override
    public JSONObject getChartJson(int id) {

        Chart chart = chartMapper.getOne(id);
        String type = chart.getType();
        String title = chart.getTitle();

        String [] bList = chart.getBar_name().split(" ");
        //获取x轴上的参数并将其拼接成特定格式的字符串
        String[] xAxisList =chart.getX_axis().split(" ");
        System.out.println("++++++++++"+xAxisList.length);
        //获取图数据
        List<ChartData> dataList = chartDataMapper.getAll();
        //根据所给类型取出相应的图表配置。
        JSONObject optionJson = JSON.parseObject(chartOptionMapper.getOne(type).getOption());

        JSONObject title1 = JSON.parseObject(optionJson.get("title").toString());
        title1.put("text",title);
        optionJson.put("title",title1);

        int [] l = {1,2,3,-1};

        if(type.equals("pie")){
            String[][] sourList = new String[xAxisList.length+1][2];
            sourList[0][0]="";
            sourList[0][1]="";
            JSONObject dataset = new JSONObject();
            for(int i =1;i<xAxisList.length+1;i++){
                sourList[i][0] = xAxisList[i-1];
                sourList[i][1] =Integer.toString(l[i-1]);
            }
            dataset.put("source",sourList);
            optionJson.put("dataset",dataset);

        }
        else{
            JSONObject xAxis = JSON.parseObject(optionJson.get("xAxis").toString());
            xAxis.put("data",xAxisList);
            optionJson.put("xAxis",xAxis);

            JSONArray series = JSON.parseArray(optionJson.get("series").toString());

            for (int i =0; i<bList.length;i++){
                JSONObject bar = new JSONObject();

                if (bList.length==1){
                    JSONObject itemStyle = new JSONObject();
                    JSONObject normal = new JSONObject();
                    normal.put("color","#188df0");
                    itemStyle.put("normal",normal);
                    bar.put("itemStyle",itemStyle);
                }
                bar.put("name",bList[i]);
                bar.put("type",type);
                bar.put("data",l);
                System.out.println(bar.toJSONString());
                series.add(bar);
            }
            optionJson.put("series",series);
        }
        System.out.println(optionJson.toJSONString());
        return optionJson;
    }

    @Override
    //存储特定chart的配置所需数据，并返回该条数据在数据库中的id
    public int saveChart(String org, String sql, String title, String type, String xAxis,String barname, String remake) {

        Chart chart = new Chart(org,sql,title,type,xAxis,barname,remake);
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
