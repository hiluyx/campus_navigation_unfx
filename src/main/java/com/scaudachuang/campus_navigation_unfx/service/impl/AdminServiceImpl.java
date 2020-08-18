package com.scaudachuang.campus_navigation_unfx.service.impl;

import com.scaudachuang.campus_navigation_unfx.DAO.AdminDAO;
import com.scaudachuang.campus_navigation_unfx.entity.Admin;
import com.scaudachuang.campus_navigation_unfx.service.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class AdminServiceImpl implements AdminService {

    @Resource
    private AdminDAO adminDAO;

    @Override
    public Admin findAdminByAdminName(String adminName) {
        return adminDAO.findAdminByAdminName(adminName);
    }

    @Override
    public List<Admin> findAll() {
        return adminDAO.findAll();
    }

    @Override
    public void addAdmin(Admin admin) {
        adminDAO.save(admin);
    }

    @Override
    public void deleteAdmins(List<Admin> adminList) {
        adminDAO.deleteInBatch(adminList);
    }

    @Override
    public void updateAdmin(Map<String, String> map) {
        String adminId = map.get("id");
        String name = map.get("adminName");
        String password = map.get("passWord");
        if (name != null) adminDAO.updateAdminNameById(name,adminId);
        if (password != null) adminDAO.updateAdminPasswordById(password,adminId);
    }
}
