package com.scaudachuang.campus_navigation_unfx;

import com.scaudachuang.campus_navigation_unfx.POJO.FlaskServerResponse;
import com.scaudachuang.campus_navigation_unfx.utils.http.HttpClientUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Objects;

@SpringBootTest
class CampusNavigationUnfxApplicationTests {

    @Test
    public void py() throws URISyntaxException, IOException {
//        FlaskServerResponse flaskServerResponse = HttpClientUtil.doPost2Flask(new File(Objects.requireNonNull(this.getClass().getClassLoader().getResource("DSC05186.JPG")).toURI())
//                ,"localhost",
//                "9001",
//                "predict");
//        System.out.println(flaskServerResponse.getBuildingName());
    }
}
