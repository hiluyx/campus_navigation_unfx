package com.scaudachuang.campus_navigation_unfx.POJO;

import lombok.Data;

@Data
public class InfoBound2SingleComment {
    private String user_name;
    private String user_avatar;

    public InfoBound2SingleComment(String user_name, String user_avatar) {
        this.user_name = user_name;
        this.user_avatar = user_avatar;
    }
}
