package com.scaudachuang.campus_navigation_unfx.POJO;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/*
参数接受包装成POJO
 */
@Getter
@Setter
public class ImageFromWx {
    private File img;
}
