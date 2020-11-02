package com.scaudachuang.campus_navigation_unfx.service.impl;

import com.scaudachuang.campus_navigation_unfx.DAO.BuildingDAO;
import com.scaudachuang.campus_navigation_unfx.POJO.BuildingLocation;
import com.scaudachuang.campus_navigation_unfx.POJO.FlaskServerResponse;
import com.scaudachuang.campus_navigation_unfx.config.FlaskServerConfig;
import com.scaudachuang.campus_navigation_unfx.entity.Building;
import com.scaudachuang.campus_navigation_unfx.service.BuildingService;
import com.scaudachuang.campus_navigation_unfx.utils.http.HttpClientUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;

@Service
public class BuildingServiceImpl implements BuildingService {

    @Resource
    private BuildingDAO buildingDAO;

    @Resource
    private FlaskServerConfig flaskServerConfig;

    @Override
    public Building getBuildingById(int id) {
        return buildingDAO.getOne(id);
    }

    @Override
    public BuildingLocation getLocationByImage(MultipartFile file) throws IOException, URISyntaxException {

        String fileName = file.getOriginalFilename();
        FlaskServerResponse resultFromPyTorch = HttpClientUtil.doPost2Flask(
                file.getInputStream()
                ,fileName
                ,flaskServerConfig.getHost()
                ,flaskServerConfig.getPort()
                ,flaskServerConfig.getRoute());

        BuildingLocation retLocation = new BuildingLocation();
        retLocation.setLatitude(buildingDAO.findLatitudeById(resultFromPyTorch.getId()));
        retLocation.setLongitude(buildingDAO.findLongitudeById(resultFromPyTorch.getId()));
        return retLocation;
    }

    @Override
    public Building getBuildingFromFlaskServer(MultipartFile file) throws URISyntaxException, IOException {

        String fileName = file.getOriginalFilename();

        FlaskServerResponse resultFromPyTorch = HttpClientUtil.doPost2Flask(
                file.getInputStream()
                ,fileName
                ,flaskServerConfig.getHost()
                ,flaskServerConfig.getPort()
                ,flaskServerConfig.getRoute());

        return buildingDAO.getOne(resultFromPyTorch.getId());
    }

}
