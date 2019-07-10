package com.wwr.echarts;


import com.wwr.echarts.api.ChartDataService;
import com.wwr.echarts.model.ChartData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringbootEchartsApplication.class/*, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT*/)
public class MapperTest {

    @Autowired
    private ChartDataService chartDataService;

    @Test
    public void testGetAll(){
        List<ChartData> list = chartDataService.getAllChartData();
        for(int i=0;i<list.size();i++){
            System.out.println(list.get(i).toString());
        }
    }


}
