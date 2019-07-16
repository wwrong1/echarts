package com.wwr.echarts.controller;

import com.alibaba.fastjson.JSONObject;
import com.wwr.echarts.service.GetChartService;
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


    @RequestMapping(value="/testEcharts/{id}", method = RequestMethod.GET)

    public ModelAndView test(/**HttpServletRequest request**/@PathVariable("id")  int id){
//        model.addAttribute("url","/getOption/45/bar");
        ModelAndView mav = new ModelAndView("test");
//        request.setAttribute("url","/getOption/45/bar");
        mav.addObject("url","/getOption/"+id);
        return mav;
//        return "test";
    }


    @RequestMapping(value="/getOption/{id}",method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getOption(@PathVariable("id")  int id){
        return getChartService.getChartJson(id);
    }

    @RequestMapping(value = "/saveChart/{org}/{sql}/{title}/{type}/{xAxis}/{remake}")
    @ResponseBody
    public int saveChart(@PathVariable("org")String org,@PathVariable("sql")String sql,@PathVariable("title")String title,
                         @PathVariable("type")String type,@PathVariable("xAxis")String xAxis,@PathVariable("remake") String remake){

        int id = getChartService.saveChart(org,sql,title,type,xAxis,remake);
        return id;
    }

    @RequestMapping(value="/queryCharts/{org}", method=RequestMethod.GET )
    @ResponseBody
    public String queryCharts(@PathVariable("org") String org){

        String s = getChartService.queryCharts(org);
        return s;

    }



}
