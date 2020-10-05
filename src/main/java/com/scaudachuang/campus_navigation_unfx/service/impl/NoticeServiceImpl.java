package com.scaudachuang.campus_navigation_unfx.service.impl;

import com.scaudachuang.campus_navigation_unfx.DAO.NoticeDAO;
import com.scaudachuang.campus_navigation_unfx.entity.Notice;
import com.scaudachuang.campus_navigation_unfx.service.NoticeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class NoticeServiceImpl implements NoticeService {
    @Resource
    private NoticeDAO noticeDAO;

    @Override
    public Notice getLatestRelease() {

        return noticeDAO.findFirstByOrderByTimeDesc();
    }
}
