package com.scaudachuang.campus_navigation_unfx.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "comment")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
public class Comment implements Serializable,Data {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;//主键


    /**
     * json context
     *
     * **/

    @Column(name = "b_id",insertable = false,updatable = false)
    private int bid;

    @Column(name = "u_id",insertable = false,updatable = false)
    private int uid;

    @Column(name = "message")
    private String message;//内容

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "time_of_commentary")
    private Date timeOfCommentary;//评论的时间

    @Column(name = "number_of_praise")
    private int numberOfPraise;//点赞次数

    /**    **/



    @JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false,fetch = FetchType.LAZY)
    @JoinColumn(name = "b_id")
    private Building building;

    @JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
    @ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=false,fetch = FetchType.LAZY)
    @JoinColumn(name = "u_id")
    private User user;

    @Override
    public String toString() {
        return "{Comment:"+this.id+",\n" +
                "\t\t\t["+this.message+","+this.timeOfCommentary+","+this.numberOfPraise+"]\n" +
                "\t\t}";
    }
}
