package com.scaudachuang.campus_navigation_unfx.service;

import com.scaudachuang.campus_navigation_unfx.POJO.BuildingLocation;
import com.scaudachuang.campus_navigation_unfx.entity.Building;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public interface BuildingService {
    Building getBuildingById(int id);
    BuildingLocation getLocationByImage(MultipartFile imgFile) throws IOException, URISyntaxException;
    Building getBuildingFromFlaskServer(MultipartFile imgFile) throws URISyntaxException, IOException;
}
