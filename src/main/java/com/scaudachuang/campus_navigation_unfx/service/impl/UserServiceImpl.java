package com.scaudachuang.campus_navigation_unfx.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.scaudachuang.campus_navigation_unfx.DAO.UserDAO;
import com.scaudachuang.campus_navigation_unfx.POJO.InfoBound2SingleComment;
import com.scaudachuang.campus_navigation_unfx.entity.User;
import com.scaudachuang.campus_navigation_unfx.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author best lu
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDAO userDAO;

    @Override
    public User findByOpenId(String openId) {
        return userDAO.findByOpenId(openId);
    }

    @Override
    public void updateUserByOpenId(String openId, JSONObject jsonObject) {
        userDAO.updateUserCurrLogTimeByOpenId(new Date(),openId);
        userDAO.updateUserNameByOpenId(jsonObject.getString("nickName"),openId);
    }

    @Override
    public String insertRegUser(JSONObject userInfo,String openId,String sessionKey) {
        String user_name;

        User insert_user = new User();

        insert_user.setOpenId(openId);

        insert_user.setSessionKey(sessionKey);

        insert_user.setRegTime(new Date());

        insert_user.setCurrLogTime(new Date());

        insert_user.setUserAvatar(userInfo.getString("avatarUrl"));

        insert_user.setUserName(userInfo.getString("nickName"));

        userDAO.save(insert_user);

        user_name = insert_user.getUserName();

        int id = userDAO.getUserIdByOpenId(openId);

        return user_name + " " + id;
    }

    @Override
    public List<User> findAll() {
        return userDAO.findAll();
    }

    @Override
    public void deleteUsers(List<User> userList) {
        userDAO.deleteInBatch(userList);
    }

    @Override
    public InfoBound2SingleComment getNameAndAvatar(int id) {
        String name = userDAO.getUserName(id);
        String avatar = userDAO.getUserAvatar(id);
        return new InfoBound2SingleComment(name,avatar);
    }
}
