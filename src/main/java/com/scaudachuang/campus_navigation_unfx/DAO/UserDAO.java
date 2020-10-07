package com.scaudachuang.campus_navigation_unfx.DAO;

import com.scaudachuang.campus_navigation_unfx.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Date;

public interface UserDAO extends JpaRepository<User,Integer> {

    User findByOpenId(String openId);

    @Transactional
    @Query(value = "update user set userName = ?1 where openId = ?2")
    @Modifying
    void updateUserNameByOpenId(String userName,String openId);

    @Transactional
    @Query(value = "update user set currLogTime = ?1 where openId = ?2")
    @Modifying
    void updateUserCurrLogTimeByOpenId(Date currLogTime, String openId);

    @Transactional
    @Query(value = "select u.id from user u where u.openId = ?1 ")
    int getUserIdByOpenId(String openId);
}
