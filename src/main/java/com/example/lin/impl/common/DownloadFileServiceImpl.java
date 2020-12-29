package com.example.lin.impl.common;

import com.example.lin.controller.edu.EduController;

import java.io.*;

import com.example.lin.service.common.DownloadFileService;
import com.example.lin.untils.FileUtil;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import sun.net.ftp.FtpClient;

import javax.servlet.http.HttpServletResponse;

/**
  *@Author:Ljh
  *@Date: 2019/11/25 16:34
 */
@Service
public class DownloadFileServiceImpl implements DownloadFileService {

    private static final Logger logger = LoggerFactory.getLogger(DownloadFileServiceImpl.class);

    @Override
    public void downLoadApk(HttpServletResponse response) {
        String filePath = "";
        String os = System.getProperty("os.name");
        if(os.toLowerCase().startsWith("win")){
            filePath = "C:\\Users\\XJ\\Documents\\Tencent Files\\296209494\\FileRecv\\MobileFile\\com.mt9dna.MyApplication.apk.1";
        }else {
            filePath = "/usr/local/lin/apk_path/com.mt9dna.MyApplication.apk.1";
        }
        FileUtil.downloadFile(response,filePath);
    }

    @Override
    public void downLoadFtpApk(HttpServletResponse response) {
        OutputStream os = null;
        //创建FTP客户端，所有的操作都基于FTPClinet
        FTPClient ftp = new FTPClient();
        //连接FTP服务器
        try {
            ftp.connect("39.109.11.252");
            //如果是需要认证的服务器，就需要账号和密码来登录
            ftp.login("zdkj", "Zd123456");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //获取服务器返回的状态码
        int reply = ftp.getReplyCode();
        if (!FTPReply.isPositiveCompletion(reply)) {
            logger.error("connect failed...ftp服务器:" + "39.109.11.252");
        }
        String fileName = "MTAndroid1.apk";
        String localpath = "C:\\Users\\XJ\\Documents\\Tencent Files\\296209494\\FileRecv\\MobileFile\\";
        try {
            ftp.changeWorkingDirectory("/www/app/");
            ftp.enterLocalPassiveMode();
            FTPFile[] ftpFiles = ftp.listFiles();
            for (FTPFile file : ftpFiles) {
                if (fileName.equalsIgnoreCase(file.getName())) {
                    File localFile = new File(localpath + File.separator + file.getName());
                    os = new FileOutputStream(localFile);
                    ftp.retrieveFile(file.getName(), os);
                    os.close();
                }
            }
            ftp.logout();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != os) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
