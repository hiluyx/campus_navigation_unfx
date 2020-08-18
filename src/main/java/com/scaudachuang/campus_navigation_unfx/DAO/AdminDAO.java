package com.scaudachuang.campus_navigation_unfx.DAO;

import com.scaudachuang.campus_navigation_unfx.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Date;

public interface AdminDAO extends JpaRepository<Admin,Integer> {
    Admin findAdminByAdminName(String adminName);

    @Transactional
    @Query(value = "update Admin set adminName = ?1 where id = ?2")
    @Modifying
    void updateAdminNameById(String adminName,String id);

    @Transactional
    @Query(value = "update Admin set password = ?1 where id = ?2")
    @Modifying
    void updateAdminPasswordById(String adminPassword,String id);
}
