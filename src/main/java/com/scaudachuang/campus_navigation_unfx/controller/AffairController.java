package com.scaudachuang.campus_navigation_unfx.controller;

import com.scaudachuang.campus_navigation_unfx.entity.Admin;
import com.scaudachuang.campus_navigation_unfx.entity.Comment;
import com.scaudachuang.campus_navigation_unfx.entity.User;
import com.scaudachuang.campus_navigation_unfx.service.AdminService;
import com.scaudachuang.campus_navigation_unfx.service.BuildingService;
import com.scaudachuang.campus_navigation_unfx.service.CommentService;
import com.scaudachuang.campus_navigation_unfx.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.File;
import java.util.Arrays;

@RestController
@RequestMapping("/affair")
public class AffairController {

    @GetMapping(value = "/getNotice")
    public String notice(){
        File file = new File(System.getProperty("user.dir") +"\\src\\main\\resources\\static\\notice");
        return "notice";
    }

    @PostMapping(value = "/reportedIt")
    public void reportedComment(@RequestBody Comment reportedComment){
        System.out.println(reportedComment.getMessage());
    }
}
