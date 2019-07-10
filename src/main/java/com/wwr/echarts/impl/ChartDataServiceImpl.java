package com.wwr.echarts.impl;

import com.wwr.echarts.api.ChartDataService;
import com.wwr.echarts.mapper.ChartDataMapper;
import com.wwr.echarts.model.ChartData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChartDataServiceImpl implements ChartDataService {

    @Autowired
    private ChartDataMapper chartDataMapper;

    @Override
    public List<ChartData> getAllChartData() {
        return chartDataMapper.getAll();
    }


}
