package com.wwr.echarts.controller;

import com.alibaba.fastjson.JSONObject;
import com.wwr.echarts.api.GetChartJsonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


@Controller
public class TestController {

    @Autowired
    private GetChartJsonService getChartJsonService;

    @RequestMapping(value="/testEcharts/{title}/{shape}", method = RequestMethod.GET)

    public ModelAndView test(/**HttpServletRequest request**/@PathVariable("title")  String title, @PathVariable("shape")String shape){
//        model.addAttribute("url","/getOption/45/bar");
        ModelAndView mav = new ModelAndView("test");
//        request.setAttribute("url","/getOption/45/bar");
        mav.addObject("url","/getOption/"+title+"/"+shape);
        return mav;
//        return "test";
    }


    @RequestMapping(value="/getOption/{title}/{shape}",method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getOption(@PathVariable("title")  String title, @PathVariable("shape")String shape){
        return getChartJsonService.getChartJson(title,shape);
    }

}
