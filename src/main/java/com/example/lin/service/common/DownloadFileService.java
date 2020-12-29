package com.example.lin.service.common;

import javax.servlet.http.HttpServletResponse;

public interface DownloadFileService {
    /**
     * 下载apk
     * @param response
     */
    void downLoadApk(HttpServletResponse response);

    /**
     * 下载ftp中的apk
     * @param response
     */
    void downLoadFtpApk(HttpServletResponse response);
}
