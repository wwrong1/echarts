package com.wwr.echarts.service;

import com.alibaba.fastjson.JSONObject;

public interface GetChartService {
    JSONObject getChartJson(int id);
    int saveChart(String org,String sql,String title,String type,String xAxis, String remake);
    String queryCharts(String org);

}
