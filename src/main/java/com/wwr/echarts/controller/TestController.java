package com.wwr.echarts.controller;

import com.alibaba.fastjson.JSONObject;
import com.wwr.echarts.api.GetChartJsonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



/**
 * Created by Administrator on 2018/12/3.
 */
@Controller
public class TestController {

    @Autowired
    private GetChartJsonService getChartJsonService;

    @RequestMapping("/testEcharts")
    public String test(){

        System.out.println("\u7fa1\u6155");
        return "test";//option.toJSONString();
    }


    @RequestMapping("/getOption")
    @ResponseBody
    public JSONObject getOption(){
        return getChartJsonService.getChartJson();
    }
}
