package com.example.lin.impl.common;

import com.example.lin.config.PropertiesConfig;
import com.example.lin.constant.WechatConstant;
import com.example.lin.service.common.FileService;
import com.example.lin.untils.FileUtil;
import com.example.lin.untils.StringUtils;
import com.google.common.collect.Maps;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.util.Streams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
  *@Author:Ljh
  *@Date: 2019/12/16 14:52
 */
@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    private static final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    @Override
    public int uploadFile(MultipartFile file, String partOfFilePath) {
        Map<String, MultipartFile> map = Maps.newHashMap ();
        map.put (file.getName (), file);
        uploadFileMap(map , partOfFilePath);
        return 0;
    }
    private static List<File> uploadFileMap(Map<String, MultipartFile> fileMap, String partOfFilePath) {

        List<File> files = new ArrayList<>();
        if (fileMap.size() == 0){
            return files;
        }

        String dirPath = PropertiesConfig.get("config","fileUploadPath", WechatConstant.FILE_UPLOAD_PATH) +
                "/" + StringUtils.fmtEmpty (partOfFilePath,"other") + "/";

        File file = new File(dirPath);
        if(!file.isDirectory()||!file.exists()){
            file.mkdirs();
        }

        InputStream is = null ;
        OutputStream os = null;
        try {

            for(Map.Entry<String, MultipartFile> entry : fileMap.entrySet()){
                // 对文件进处理
                String filePath = dirPath +entry.getValue().getOriginalFilename()
                        .substring (entry.getValue().getOriginalFilename().lastIndexOf ("\\")+1);
                is = entry.getValue().getInputStream();
                os = new FileOutputStream(filePath);
                long copy = Streams.copy(is, os, true);
                files.add (new File (filePath));
            }

        }catch (Exception e){
            logger.error ("upload file fail");
            logger.error (e.getMessage ());
        }finally {
            FileUtil.closeCloseable (os,is);
        }
        return files;
    }

}
