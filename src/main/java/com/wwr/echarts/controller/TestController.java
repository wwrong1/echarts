package com.wwr.echarts.controller;

import com.wwr.echarts.service.GetChartService;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value="查看图表入口", notes="输入id以查看生成的图表")
    @RequestMapping(value="/testEcharts/{id}", method = RequestMethod.GET)
    public ModelAndView test(/**HttpServletRequest request**/@PathVariable("id")  int id){

        ModelAndView mav = new ModelAndView("test");
        mav.addObject("option",getChartService.getChartJson(id));
        return mav;

    }

    /**将url传到前台，利用ajax访问方法的写法
//    @RequestMapping(value="/testEcharts/{id}", method = RequestMethod.GET)
//    public ModelAndView test(HttpServletRequest request,Model model@PathVariable("id")  int id){
//
//     (1)ModelAndView mav = new ModelAndView("test");
//        mav.addObject("url","/getOption/"+id);
//        return mav;

//     (2)request.setAttribute("url","/getOption/45/bar");
//        model.addAttribute("url","/getOption/"+id);
//        return "test";
//    }

//    @RequestMapping(value="/getOption/{id}",method = RequestMethod.GET)
//    @ResponseBody
//    public JSONObject getOption(@PathVariable("id")  int id){
//        return getChartService.getChartJson(id);
//    }
**/

    @ApiOperation(value="保存chart数据", notes="输入所需数据，包括组织，sql语句（根据xAxis查询），图表的名字，" +
            "图表类型（pie,bar,line,scatter）,所定义的要展示的数据列名（即在二维图中的x轴数据，注意：程序需要列名之间有且只有一个空格隔开！！），图的备注信息")
    @RequestMapping(value = "/saveChart/{org}/{sql}/{title}/{type}/{xAxis}/{bar_name}/{remake}",method = RequestMethod.GET)
    @ResponseBody
    public int saveChart(@PathVariable("org")String org,@PathVariable("sql")String sql,@PathVariable("title")String title,
                         @PathVariable("type")String type,@PathVariable("xAxis")String xAxis,@PathVariable("bar_name") String bar_name,@PathVariable("remake") String remake){

        int id = getChartService.saveChart(org,sql,title,type,xAxis,bar_name,remake);
        return id;
    }

    @ApiOperation(value="查看chart表中的数据项", notes="可以根据自己设置的备注，找到需要展示的图的id")
    @RequestMapping(value="/queryCharts/{org}", method=RequestMethod.GET )
    @ResponseBody
    public String queryCharts(@PathVariable("org") String org){
        String s = getChartService.queryCharts(org);
        return s;
    }



}
