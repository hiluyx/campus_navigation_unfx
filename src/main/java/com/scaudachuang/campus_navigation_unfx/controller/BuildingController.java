package com.scaudachuang.campus_navigation_unfx.controller;

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

//    public static File multipartFileToFile(MultipartFile multipartFile) throws IOException {
//        String fileRealName = multipartFile.getOriginalFilename();
//        //获得原始文件名;
//        InputStream ins = multipartFile.getInputStream();
//        assert fileRealName != null;
//        File file = new File(fileRealName);
//        try {
//            OutputStream os = new FileOutputStream(file);
//            int bytesRead;
//            byte[] buffer = new byte[8192];
//            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
//                os.write(buffer, 0, bytesRead);
//            }
//            os.close();
//            ins.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return file;
//    }
}
