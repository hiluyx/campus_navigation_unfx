package com.scaudachuang.campus_navigation_unfx.POJO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginParameters {
    private String code;
    private String iv;
    private String encryptedData;
}
