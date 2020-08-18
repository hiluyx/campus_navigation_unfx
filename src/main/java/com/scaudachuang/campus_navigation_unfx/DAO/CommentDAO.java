package com.scaudachuang.campus_navigation_unfx.DAO;

import com.scaudachuang.campus_navigation_unfx.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CommentDAO extends JpaRepository<Comment,Integer>, JpaSpecificationExecutor<Comment> {
    List<Comment> findAllByUid(int uid);
    void deleteByUid(int uId);

//    @Transactional
//    @Query(value = "update user set userName = ?1 where openId = ?2")
//    @Modifying

//    Page<Comment> findCommentsByBidEqualsOrderByNumberOfPraiseAsc(int bid);

//    Page<Comment> findCommentsByBidEqualsOrderByTimeOfCommentaryAsc(int bid);
}
