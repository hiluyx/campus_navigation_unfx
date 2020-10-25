package com.scaudachuang.campus_navigation_unfx.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@Entity(name ="user")
@Table(name = "user")
public class User implements Serializable,Data {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "open_id")
    private String openId;

    @Column(name = "session_key")
    private String sessionKey;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_avatar")
    private String userAvatar;

    @Column(name = "curr_log_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date currLogTime;//最近授权

    @Column(name = "reg_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date regTime;//第一次授权

}
