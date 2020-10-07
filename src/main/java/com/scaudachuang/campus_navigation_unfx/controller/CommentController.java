package com.scaudachuang.campus_navigation_unfx.controller;

import com.scaudachuang.campus_navigation_unfx.POJO.CommentParameters;
import com.scaudachuang.campus_navigation_unfx.entity.Comment;
import com.scaudachuang.campus_navigation_unfx.service.CommentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/*
小程序点击评论按钮，返回排好序的json数据
 */
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Resource
    private CommentService commentService;

//    /**
//     * 此方法用于前端，用户点击评论，查看目前建筑（已识别出来）的第几页评论内容。
//     *     @param buildingId 建筑的主键（与comment表级联）作为查询的条件
//     *     @param page 获取评论的第几页（从0开始）非必须，为0
//     *     @param size 获取的评论列表的一页里包含多少条评论（至少为1）非必须，为1
//     *     @param sortType 排序方式，按时间或热度，true = 时间，false = 热度
//     *     @return comments 第page+1页包含size条的评论列表（json.按时间排序,新的在前面）
//     */
    @PostMapping(value = "/commentsDivideIntoPages")
    public List<Comment> commentsDivideIntoPages(@RequestBody CommentParameters commentParameters){

        return commentService.findByPage(
                commentParameters.getPage(),
                commentParameters.getSize(),
                commentParameters.getBuildingId(),
                commentParameters.getSortKey()).getContent();

    }

    @RequestMapping(value = "/getCommentsByUid")
    public List<Comment> getCommentsByUid(@RequestParam("Uid") int uid){
        return commentService.findByUid(uid);
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String saveComments(@RequestBody Comment comment){

        String response = "Message: ";
        try{
            System.out.println(comment);
            comment.setTimeOfCommentary(new Date());
            commentService.addComment(comment);
            response += "Done!";
        }catch (Exception e){
            response = e.getMessage();
        }
        return response;
    }
}
