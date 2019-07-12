package com.wwr.echarts.controller;

import com.alibaba.fastjson.JSONObject;
import com.wwr.echarts.api.GetChartJsonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
public class TestController {

    @Autowired
    private GetChartJsonService getChartJsonService;

    @RequestMapping("/testEcharts")
    public String test(){
        System.out.println("\u7fa1\u6155");
        return "test";//option.toJSONString();
    }


    @RequestMapping(value="/getOption/{title}/{shape}",method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getOption(@PathVariable("title")  String title, @PathVariable("shape")String shape){
        return getChartJsonService.getChartJson(title,shape);
    }
}
