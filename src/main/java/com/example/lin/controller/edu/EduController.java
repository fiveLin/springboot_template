package com.example.lin.controller.edu;


import com.example.lin.dao.edu.EduDao;
import com.example.lin.resp.APIObjectJson;


import com.example.lin.resp.APIRespJson;
import com.example.lin.service.common.DownloadFileService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/data/trainDetail")
@Api(value = "", description = "", tags = {"/data/trainDetail"})
@RequiredArgsConstructor
public class EduController {
    private final EduDao eduDao;
    private final DownloadFileService downloadFileService;

    private static final Logger logger = LoggerFactory.getLogger(EduController.class);

    /**
     * 获取单个TrainDetailPo
     * @param
     * @return APIRespJson
     */
    @GetMapping("/getEdu")
    public APIObjectJson getEdu() {
        return new APIObjectJson(1);
    }

    /**
     * 下载apk
     * @param response
     * @return APIRespJson
     */
    @GetMapping("/downLoadApk")
    public APIRespJson downLoadApk(HttpServletResponse response) {
        downloadFileService.downLoadApk(response);
        return new APIRespJson();
    }
    /**
     * 下载apk
     * @param response
     * @return APIRespJson
     */
    @GetMapping("/downLoadFtpApk")
    public APIRespJson downLoadFtpApk(HttpServletResponse response) {
        downloadFileService.downLoadFtpApk(response);
        return new APIRespJson();
    }
}