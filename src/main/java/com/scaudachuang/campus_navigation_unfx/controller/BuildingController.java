package com.scaudachuang.campus_navigation_unfx.controller;

import com.scaudachuang.campus_navigation_unfx.POJO.ImageFromWx;
import com.scaudachuang.campus_navigation_unfx.entity.Building;
import com.scaudachuang.campus_navigation_unfx.service.BuildingService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;

/*
request小程序相机发来拍摄图片
return建筑实体类building
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
    public Building getBuilding(@RequestParam("img") MultipartFile img) throws IOException {
        File imgFile = multipartFileToFile(img);
        ImageFromWx imageFromWx = new ImageFromWx();
        imageFromWx.setImg(imgFile);
        /*
        调用python模型计算得到id
         */
        return buildingService.getBuildingById(1);

    }

    public static File multipartFileToFile(MultipartFile multipartFile) throws IOException {
        String fileRealName = multipartFile.getOriginalFilename();//获得原始文件名;
        InputStream ins = multipartFile.getInputStream();
        assert fileRealName != null;
        File file = new File(fileRealName);
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }
}
