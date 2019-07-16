package com.wwr.echarts.service;

import com.wwr.echarts.model.ChartData;

import java.util.List;

public interface ChartDataService {
    //获取数据库中绘图的所有数据
    List<ChartData> getAllChartData();

}
