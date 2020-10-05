package com.scaudachuang.campus_navigation_unfx;

import com.scaudachuang.campus_navigation_unfx.service.NoticeService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class CampusNavigationUnfxApplicationTests {

    @Resource
    private NoticeService noticeService;
    @Test
    void contextLoads() {
        System.out.println(noticeService.getLatestRelease());
    }

}
