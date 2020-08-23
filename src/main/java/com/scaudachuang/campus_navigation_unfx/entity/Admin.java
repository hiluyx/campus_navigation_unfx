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
@Entity
@Table(name = "admin")
public class Admin implements Serializable,Data {

    @Id
    private int id;

    @Column(name = "password")
    private String password;

    @Column(name = "admin_name")
    private String adminName;

    @Column(name = "reg_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date regTime;

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", adminName='" + adminName + '\'' +
                ", regTime=" + regTime +
                '}';
    }
}
