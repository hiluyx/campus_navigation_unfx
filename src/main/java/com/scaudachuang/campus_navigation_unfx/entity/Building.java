package com.scaudachuang.campus_navigation_unfx.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "building")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
public class Building implements Serializable,Data {

    @Id
    @GeneratedValue
    private int id;//主键

    @Column(name = "name",columnDefinition = "varchar(10)  default 'Unknown' not null comment '建筑名称'")
    private String name;//建筑名字

    @Column(name = "position",columnDefinition = "decimal                        null comment '经纬度'")
    private BigDecimal position;//问地图

    @Column(name = "brief_introduction",columnDefinition = "varchar(255) default 'Unknown' null comment '简介'")
    private String briefIntroduction;

    @Column(name = "number_of_browse",columnDefinition = "int          default 0         null comment '浏览次数'")
    private int numberOfBrowse;//初始化为0

    @Column(name = "number_of_comment",columnDefinition = "int          default 0         null comment '评论条数'")
    private int numberOfComment;//Comment表的行数

    @Column(name = "img",columnDefinition = "varchar(255)                   null comment '相片编码'")
    @Lob
    private String img;//BASE64编码将file图片转成String

    /*
    多对多级联
    删除评论时，不影响building表
    */
    /*
    @JsonIgnoreProperties(value = "building")
    @OneToMany(mappedBy = "building",cascade=CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();
    */

    @Override
    public String toString() {
        return "{Building:"+this.id+",\n" +
                "\t["+this.getName()+",\n" +
                //"\t\t"+this.getComments()+",\n" +
                "\t"+this.getPosition()+"]\n" +
                "}";
    }


}