package com.scaudachuang.campus_navigation_unfx.controller;

import com.scaudachuang.campus_navigation_unfx.entity.Comment;
import com.scaudachuang.campus_navigation_unfx.entity.Notice;
import com.scaudachuang.campus_navigation_unfx.service.CommentService;
import com.scaudachuang.campus_navigation_unfx.service.NoticeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author best lu
 */
@RestController
@RequestMapping("/affair")
public class AffairController {

    @Resource
    private CommentService commentService;
    @Resource
    private NoticeService noticeService;

    @GetMapping(value = "/getNotice")
    public Notice notice(){
        return noticeService.getLatestRelease();
    }

    @PostMapping(value = "/reportedIt")
    public void reportedComment(@RequestBody Comment reportedComment){
        commentService.updateReportsOfComment(reportedComment);
    }
}
