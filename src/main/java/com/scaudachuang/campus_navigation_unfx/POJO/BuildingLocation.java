package com.scaudachuang.campus_navigation_unfx.POJO;

import lombok.Data;

@Data
public class BuildingLocation {

    private double longitude;
    private double latitude;

    public BuildingLocation build(){
        return this;
    }
}
