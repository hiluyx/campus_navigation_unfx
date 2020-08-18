package com.scaudachuang.campus_navigation_unfx.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Setter
@Getter
public class WxAppConfig {

    @Value("${wx.WX_LOGIN_URL}")
    private String WX_LOGIN_URL;

    @Value("${wx.WX_LOGIN_APP_ID}")
    private String WX_LOGIN_APP_ID;

    @Value("${wx.WX_LOGIN_SECRET}")
    private String WX_LOGIN_SECRET;

    @Value("${wx.WX_LOGIN_GRANT_TYPE}")
    private String WX_LOGIN_GRANT_TYPE;

}
