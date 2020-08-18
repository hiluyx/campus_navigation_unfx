package com.scaudachuang.campus_navigation_unfx.service;

import com.scaudachuang.campus_navigation_unfx.entity.Admin;

import java.util.List;
import java.util.Map;

public interface AdminService {
    Admin findAdminByAdminName(String adminName);
    List<Admin> findAll();
    void addAdmin(Admin admin);
    void deleteAdmins(List<Admin> adminList);
    void updateAdmin(Map<String,String> map);// id,"XX";修改的属性String,“XX”
}
