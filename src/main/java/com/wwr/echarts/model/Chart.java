package com.wwr.echarts.model;

public class Chart {
    private int id;
    private String org;
    private String sql_str;
    private String title;
    private String type;

    //要求存入数据库中的X轴的参数之间有且只有一个空格分隔开
    private String xAxis;

    private String remake;

    public Chart() {
    }

    public Chart( String org, String sql_str, String title, String type, String xAxis, String remake) {
        this.org = org;
        this.sql_str = sql_str;
        this.title = title;
        this.type = type;
        this.xAxis = xAxis;
        this.remake = remake;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public String getSql_str() {
        return sql_str;
    }

    public void setSql_str(String sql_str) {
        this.sql_str = sql_str;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getxAxis() {
        return xAxis;
    }

    public void setxAxis(String xAxis) {
        this.xAxis = xAxis;
    }

    public String getRemake() {
        return remake;
    }

    public void setRemake(String remake) {
        this.remake = remake;
    }

    @Override
    public String toString() {

        String str = "id:"+this.id+", org:"+this.org+", sql_str:"+this.sql_str +", title:"+this.title+", type:"+this.type+
                ", xAxis:"+this.xAxis+", remake:"+this.remake+"\n";
        return str;
    }
}
