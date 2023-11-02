package com.example.demoauthorization.request;

import java.util.List;

public class HelloRequestWithArea {
    private List<Long> areaIds;
    private List<Long> blockIds;

    public HelloRequestWithArea() {
    }

    public HelloRequestWithArea(List<Long> areaIds, List<Long> blockIds) {
        this.areaIds = areaIds;
        this.blockIds = blockIds;
    }

    public List<Long> getAreaIds() {
        return areaIds;
    }

    public void setAreaIds(List<Long> areaIds) {
        this.areaIds = areaIds;
    }

    public List<Long> getBlockIds() {
        return blockIds;
    }

    public void setBlockIds(List<Long> blockIds) {
        this.blockIds = blockIds;
    }
}
