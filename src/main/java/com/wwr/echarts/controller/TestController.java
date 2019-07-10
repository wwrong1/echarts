package com.wwr.echarts.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wwr.echarts.mapper.ChartDataMapper;
import com.wwr.echarts.model.ChartData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2018/12/3.
 */
@Controller
public class TestController {

    @Autowired
    ChartDataMapper chartDataMapper;

    @RequestMapping("/testEcharts")
    public String test(){

        System.out.println("\u7fa1\u6155");
        return "test";//option.toJSONString();
    }


    @RequestMapping("/getValue")
    @ResponseBody
    public long [][] getOption(){
        JSONArray result = new JSONArray();
        List<ChartData> dataList = chartDataMapper.getAll();
        long [][] s = new long[dataList.size()][3];
        for(int i =0; i <dataList.size();i++){
            ChartData data = dataList.get(i);
            long [] m = {data.getX(),data.getY(),data.getZ()};
            s[i] = m;
        }
        return s;
    }
}
