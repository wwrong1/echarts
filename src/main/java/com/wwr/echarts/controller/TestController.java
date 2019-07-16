package com.wwr.echarts.controller;

import com.alibaba.fastjson.JSONObject;
import com.wwr.echarts.api.GetChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class TestController {

    @Autowired
    private GetChartService getChartService;


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
        return getChartService.getChartJson(title,shape);
    }

    @RequestMapping(value = "/saveChart/{org}/{sql}/{title}/{{type}/{xAxis}/{remake}")
    @ResponseBody
    public int saveChart(@PathVariable("org")String org,@PathVariable("sql")String sql,@PathVariable("title")String title,
                         @PathVariable("type")String type,@PathVariable("xAxis")String xAxis,@PathVariable("remake") String remake){

        int id = getChartService.saveChart(org,sql,title,type,xAxis,remake);
        return id;

    }

    @RequestMapping(value="/queryChart/{org}", method=RequestMethod.GET )
    @ResponseBody
    public String queryChart(@PathVariable("org") String org){

        String s = getChartService.queryChart(org);

        return s;

    }



}
