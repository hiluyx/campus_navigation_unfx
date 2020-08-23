package com.scaudachuang.campus_navigation_unfx.service;

import com.scaudachuang.campus_navigation_unfx.entity.Comment;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CommentService {
    Page<Comment> findByPage(int page,int size,int bId,String sortKey);
    List<Comment> findByUid(int definedStatus);
    void addComment(Comment comment);
    List<Comment> findAll();
    void updateReportsOfComment(Comment comment);
    void deleteComments(List<Comment> commentList);
}
