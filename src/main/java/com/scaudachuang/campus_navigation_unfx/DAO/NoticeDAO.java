package com.scaudachuang.campus_navigation_unfx.DAO;

import com.scaudachuang.campus_navigation_unfx.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface NoticeDAO extends JpaRepository<Notice,Integer> {
    Notice findFirstByOrderByTimeDesc();
}
