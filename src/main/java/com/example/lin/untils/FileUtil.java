package com.example.lin.untils;



import com.example.lin.config.PropertiesConfig;
import com.example.lin.exception.BusinessException;
import com.example.lin.resp.ResultCode;
import com.google.common.collect.Maps;
import org.apache.tomcat.util.http.fileupload.util.Streams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 时光之里山南水北,你我之间人山人海
 * 文件操作
 *
 * @author 折骨为刀
 * @create 2018-05-10 16:12
 **/
public class FileUtil {

    private static final Logger log = LoggerFactory.getLogger(FileUtil.class);

    private static String FILE_UPLOAD_PATH = "/uploadFile";
    /**
     * 加载音频流
     * @param filePath
     * @param response
     * @throws Exception
     */
    public static void loadAudioFile(String filePath , HttpServletResponse response , HttpServletRequest request)throws Exception{
        OutputStream os = null;
        FileInputStream fis = null;
        try{
            File file = new File(filePath);
            if(!file.exists()) {
                throw new BusinessException(ResultCode.FAIL,"文件不存在 --> 404");
            }
            os = response.getOutputStream();
            fis = new FileInputStream(file);
            long length = file.length();

            response.addHeader("Accept-Ranges", "bytes");
            response.addHeader("Content-Length", length + "");
            response.addHeader("Content-Range", "bytes " + 0 + "-" + length + "/" + length);
            response.addHeader("Content-Type", request.getSession().getServletContext().getMimeType (file.getName ())+
                    ";charset=UTF-8");

            int len = 0;
            byte[] b = new byte[1024 * 8];
            while ((len = fis.read(b)) != -1) {
                os.write(b, 0, len);
            }
        }catch (Exception e){
            log.error ("load audio file stream fail ,filepath is:"+filePath);
            log.error (e.getMessage ());
        }finally {
            closeCloseable(fis,os);
        }

    }

    /**
     * 加载视频图片文件流
     * @param filePath
     * @param request
     * @param response
     * @throws Exception
     */
    public static void loadVideoFile(String filePath , HttpServletRequest request, HttpServletResponse response)throws Exception{

        OutputStream os = null;
        FileInputStream fis = null;
        BufferedOutputStream bufferOut = null;
        ByteArrayOutputStream output = new ByteArrayOutputStream ();

        try{
            File file = new File(filePath);

            if(!file.exists()) {
                throw new BusinessException(ResultCode.FAIL,"文件不存在 --> 404");
            }

            response.addHeader("Accept-Ranges", "bytes");
            response.addHeader("Content-Length", String.valueOf(file.length()) );
            response.addHeader("Content-Type", request.getSession().getServletContext().getMimeType (file.getName ())+
                    ";charset=UTF-8");
            response.addHeader("filename-parm", String.format("attachment;fileName=\"%s\"", file.getName().replace(" ",""), "utf-8"));

            fis = new FileInputStream(file);
            byte[] buff = new byte[1024 * 8];
            int rc = 0;
            byte[] downByte = null;
            while (-1 !=(rc = fis.read(buff))){
                output.write(buff, 0, rc);
            }
            output.flush();
            downByte = output.toByteArray();
            output.close();
            os = response.getOutputStream();
            bufferOut = new BufferedOutputStream(os);
            bufferOut.write(downByte);
            bufferOut.flush();
        }catch (Exception e){
            log.error ("load video/pic file stream fail ,filepath is:"+filePath);
            log.error (e.getMessage ());
        }finally {
            closeCloseable(fis,os);
        }

    }

    /**
     * 下载文件
     * @param response
     * @param filePath
     */
    public static void downloadFile(HttpServletResponse response, String filePath){
        String filePathTemp = "";

        try {
            filePathTemp = URLDecoder.decode (filePath,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error (e.getMessage ());
        }

        File file = new File(filePathTemp);
        if (file.exists()) {

            response.setContentType("application/force-download");

            try {
                response.addHeader("Content-Disposition", String.format("attachment;fileName=\"%s\"",
                        URLEncoder.encode(file.getName().replace(" ",""), "utf-8")));
            } catch (UnsupportedEncodingException e) {
                log.error (e.getMessage ());
            }

            byte[] buffer = new byte[1024 * 8];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            OutputStream os = null;

            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                os = response.getOutputStream();
                int readByte = 0;
                while ((readByte = fis.read(buffer, 0, 1024 * 8)) != -1) {
                    os.write(buffer, 0, readByte);
                }
            } catch (Exception e) {
                log.error ("获取文件失败!");
                log.error (e.getMessage ());
            } finally {
                closeCloseable (bis,fis,os);
            }

        } else {
            log.error ("文件不存在请联系管理员!");
        }
    }

    /**
     * 下载文件
     * @param request
     * @param response
     */
    public static void downloadFile(HttpServletRequest request, HttpServletResponse response) {

        String filePath = request.getParameter("filePath");
        downloadFile (response,filePath);

    }

    public static List<File> uploadFile(MultipartFile file, String partOfFilePath) {
        Map<String, MultipartFile> map = Maps.newHashMap ();
        map.put (file.getName (), file);
        return uploadFile(map , partOfFilePath);
    }

    /**
     * 上传文件
     * @param fileMap
     * @param
     * @return
     * @throws Exception
     */
    public static List<File> uploadFile(Map<String, MultipartFile> fileMap, String partOfFilePath) {

        List<File> files = new ArrayList<> ();
        if (fileMap.size() == 0){
            return files;
        }

        String dirPath = PropertiesConfig.get("config","fileUploadPath",FILE_UPLOAD_PATH) +
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
            log.error ("upload file fail");
            log.error (e.getMessage ());
        }finally {
            closeCloseable (os,is);
        }

        return files;
    }

    /**
     * 关闭流文件
     * @param ios
     * @param <T>
     */
    @SafeVarargs
    public static  <T extends Closeable> void closeCloseable(T... ios){
        Arrays.asList (ios).forEach (io -> {
            try {
                io.close ();
            } catch (IOException e) {
                e.printStackTrace ();
            }
        });
    }

    public static void create(File file, long length) throws IOException{
        long start = System.currentTimeMillis();
        FileOutputStream fos = null;
        FileChannel output = null;
        try {
            fos = new FileOutputStream(file);
            output = fos.getChannel();
            output.write(ByteBuffer.allocate(1), length-1);
        } finally {
            try {
                if (output != null) {
                    output.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("total times "+(end-start));
    }

    public static void main(String[] args) throws IOException{
            for(int i = 0; i < 4000; i++) {
                System.out.print(i+",");
            }
    }

}
