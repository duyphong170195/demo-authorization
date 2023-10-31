package com.example.demoauthorization.request;

public class HelloRequestWithArea {
    private String area;
    private String block;

    public HelloRequestWithArea(String area, String block) {
        this.area = area;
        this.block = block;
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
}
