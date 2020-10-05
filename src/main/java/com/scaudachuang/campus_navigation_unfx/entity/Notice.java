package com.scaudachuang.campus_navigation_unfx.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "notice")
public class Notice implements Data{

    @Id
    private int id;

    @Column(name = "string")
    private String string;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "time")
    private Date time;

    @Override
    public String toString() {
        return "Notice{" +
                "id=" + id +
                ", string='" + string + '\'' +
                ", time=" + time +
                '}';
    }
}
