package com.scaudachuang.campus_navigation_unfx.controller;

import com.scaudachuang.campus_navigation_unfx.POJO.BuildingLocation;
import com.scaudachuang.campus_navigation_unfx.POJO.FlaskServerResponse;
import com.scaudachuang.campus_navigation_unfx.config.FlaskServerConfig;
import com.scaudachuang.campus_navigation_unfx.entity.Building;
import com.scaudachuang.campus_navigation_unfx.service.BuildingService;
import com.scaudachuang.campus_navigation_unfx.utils.http.HttpClientUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;
import java.net.URISyntaxException;

/*
request小程序相机发来拍摄图片
return建筑实体类building
 */

/**
 * @author best lu
 */
@Getter
@Setter
@RestController
@RequestMapping("/sendImage")
public class BuildingController {

    @Resource
    private BuildingService buildingService;

    /*
    id查询建筑api
     */
    @RequestMapping(value = "/getBuilding/{id}",method = RequestMethod.GET)
    public Building getBuilding(@PathVariable int id){
        return buildingService.getBuildingById(id);
    }

    /*
    上传图片得到建筑api
     */
    @RequestMapping(value = "/image",method = RequestMethod.POST)
    public Building getBuilding(@RequestParam("img") MultipartFile img) throws IOException, URISyntaxException {
        return buildingService.getBuildingFromFlaskServer(img);
    }

    /*
    上传图片得到建筑经纬度api
     */
    @RequestMapping(value = "/locationByImage",method = RequestMethod.POST)
    public BuildingLocation getBuildingLocation(@RequestParam("img") MultipartFile img) throws IOException, URISyntaxException {
        return buildingService.getLocationByImage(img);
    }
}
