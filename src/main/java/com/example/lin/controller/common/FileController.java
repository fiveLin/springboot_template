package com.example.lin.controller.common;

import com.example.lin.resp.APIObjectG;
import com.example.lin.resp.APIRespJson;
import com.example.lin.service.common.FileService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
  *@Author:Ljh
  *@Date: 2019/12/16 14:15
 */
@RestController
@RequestMapping("/data/file")
@Api(value = "文件处理", description = "文件处理", tags = {"/data/file"})
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;
    /**
     * 上传文件
     * @param file
     * @return APIRespJson
     */
    @PostMapping("/uploadFile")
    public APIObjectG<Integer> uploadFile(MultipartFile file, String partOfFilePath) {
        int i = fileService.uploadFile(file,partOfFilePath);
        return new APIObjectG(i);
    }
}
