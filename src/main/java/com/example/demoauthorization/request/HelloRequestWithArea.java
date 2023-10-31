package com.example.demoauthorization.request;

public class HelloRequestWithArea {
    private String area;
    private String block;

    private String areaId;

    public HelloRequestWithArea(String area, String block, String areaId) {
        this.area = area;
        this.block = block;
        this.areaId = areaId;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }
}
