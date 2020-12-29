package com.example.lin.service.common;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
/**
 * @ClassName FileService
 * @Description : 功能说明
 * @Author : Ljh
 * @Date : 2019/12/16 14:53
*/
public interface FileService {
    int uploadFile(MultipartFile file, String partOfFilePath);
}
