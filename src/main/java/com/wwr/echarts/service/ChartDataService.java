package com.wwr.echarts.service;

import com.wwr.echarts.model.ChartData;

import java.util.List;


/**
 * @author wwr
 * @Description
 * @date 2019/7/17
 */
public interface ChartDataService {
    //获取数据库中绘图的所有数据
    List<ChartData> getAllChartData();

}
