package com.scaudachuang.campus_navigation_unfx.service.impl;

import com.scaudachuang.campus_navigation_unfx.DAO.BuildingDAO;
import com.scaudachuang.campus_navigation_unfx.entity.Building;
import com.scaudachuang.campus_navigation_unfx.service.BuildingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BuildingServiceImpl implements BuildingService {

    @Resource
    private BuildingDAO buildingDAO;

    @Override
    public Building getBuildingById(int id) {
        return buildingDAO.getOne(id);
    }

    @Override
    public List<Building> finAll() {
        return buildingDAO.findAll();
    }

}
