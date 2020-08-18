package com.scaudachuang.campus_navigation_unfx.service.impl;

import com.scaudachuang.campus_navigation_unfx.DAO.CommentDAO;
import com.scaudachuang.campus_navigation_unfx.entity.Comment;
import com.scaudachuang.campus_navigation_unfx.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class CommentServiceImpl implements CommentService {

    @Resource
    CommentDAO commentDAO;

    @Override
    public Page<Comment> findByPage(int page, int size, int bId,String sortKey) {
        Pageable pageable = PageRequest.of(page,size, Sort.Direction.DESC,sortKey,"timeOfCommentary");
        return commentDAO.findAll((Specification<Comment>) (root, criteriaQuery, criteriaBuilder) -> {

            List<Predicate> predicatesList = new ArrayList<>();
            //动态sql
            predicatesList.add(criteriaBuilder.equal(root.get("bid"),bId));
            Predicate[] p = new Predicate[predicatesList.size()];
            return criteriaBuilder.and(predicatesList.toArray(p));

        }, pageable);
    }

    @Override
    public List<Comment> findByUid(int uId) {
        return commentDAO.findAllByUid(uId);
    }

    public void addComment(Comment comment){
        commentDAO.save(comment);
    }

    @Override
    public List<Comment> findAll() {
        return commentDAO.findAll();
    }

    @Override
    public void deleteComments(List<Comment> commentList) {
        commentDAO.deleteInBatch(commentList);
    }
}
