package com.wwr.echarts.api;

import com.alibaba.fastjson.JSONObject;

public interface GetChartService {
    JSONObject getChartJson(String title,String type);
    int saveChart(String org,String sql,String title,String type,String xAxis, String remake);
    String queryChart(String org);

}
