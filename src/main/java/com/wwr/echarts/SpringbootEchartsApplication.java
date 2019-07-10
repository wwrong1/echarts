package com.wwr.echarts;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.wwr.echarts.mapper")
public class SpringbootEchartsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootEchartsApplication.class, args);
	}
}
