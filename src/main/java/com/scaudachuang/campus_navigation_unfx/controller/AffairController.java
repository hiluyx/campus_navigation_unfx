package com.scaudachuang.campus_navigation_unfx.controller;

import com.scaudachuang.campus_navigation_unfx.entity.Comment;
import com.scaudachuang.campus_navigation_unfx.service.CommentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/affair")
public class AffairController {

    @Resource
    private CommentService commentService;

    @GetMapping(value = "/getNotice")
    public String notice(){
        return "notice";
    }

    @PostMapping(value = "/reportedIt")
    public void reportedComment(@RequestBody Comment reportedComment){
        commentService.updateReportsOfComment(reportedComment);
    }
}
