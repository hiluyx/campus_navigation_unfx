package com.scaudachuang.campus_navigation_unfx.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Setter
@Getter
public class FlaskServerConfig {
    @Value("${flask.HOST}")
    private String host;
    @Value(("${flask.PORT}"))
    private String port;
    @Value("${flask.ROUTE}")
    private String route;
}
