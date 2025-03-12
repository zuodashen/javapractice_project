package com.example.controller;


import cn.hutool.core.io.FileUtil;
import com.example.common.Result;
import com.example.exception.CustomException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文件相关的接口
 */
@RestController
@RequestMapping("/files")
public class FileController {

        //System.getProperty("usr.dir") 获取当前项目根路径
        //文件上传路径
        private static final String filePath = System.getProperty("user.dir") + "/files/";
    /**
     * 文件上传接口
     * @return
     */
        @PostMapping("/upload")
        public Result upload(MultipartFile file){ //文件流的形式接受前端发来的文件
            String originalFilename = file.getOriginalFilename(); // xxx.png
            if(!FileUtil.isDirectory(filePath)){
                FileUtil.mkdir(filePath);
            }
            //提供文件存储的完整路径
            //给文件名加一个唯一的表示标识
            String fileName = System.currentTimeMillis() + originalFilename; //时间戳
            String realPath = filePath + fileName;
            try {
                FileUtil.writeBytes(file.getBytes(),realPath);
            } catch (IOException e) {
                e.printStackTrace();
                throw new CustomException("500","文件上传失败");
            }
            //返回一个网络连接
            //http://localhost:9090/files/upload
            String url = "http://localhost:9090/files/download/" + fileName;
            return Result.success(url);
        }


    /**
     * 文件下载
     */
    @GetMapping("/download/{fileName}")
    public void download(@PathVariable String fileName, HttpServletResponse response){
        try {
            //文件名统一设置为utf-8
            response.addHeader("Content-Disposition","attachment;filename=" + URLEncoder.encode(fileName, StandardCharsets.UTF_8));
            response.setContentType("application/octet-stream");
            OutputStream os = response.getOutputStream();
            String realPath = filePath + fileName;
            //获取到文件的字节数组
            byte[] bytes = FileUtil.readBytes(realPath);
            os.write(bytes);
            os.flush();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new CustomException("500","文件下载失败");
        }
    }

    @PostMapping("/wang/upload")
    public Map<String,Object> wangEditorUpload(MultipartFile file){
        if (file == null || file.isEmpty()) {
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("errno", 1);
            resultMap.put("errmsg", "文件不能为空");
            return resultMap;
        }
        String originalFilename = file.getOriginalFilename(); // xxx.png

        if(!FileUtil.isDirectory(filePath)){
            FileUtil.mkdir(filePath);
        }
        //提供文件存储的完整路径
        //给文件名加一个唯一的表示标识
        String fileName = System.currentTimeMillis() + originalFilename; //时间戳
        String realPath = filePath + fileName;
        try {
            FileUtil.writeBytes(file.getBytes(),realPath);
        } catch (IOException e) {
            e.printStackTrace();
            throw new CustomException("500","文件上传失败");
        }
        //返回一个网络连接
        //http://localhost:9090/files/upload
        String url = "http://localhost:9090/files/download/" + fileName;
        Map<String,Object> resMap = new HashMap<>();
        List<Map<String,Object>> list = new ArrayList<>();
        Map<String,Object> urlMap = new HashMap<>();
        urlMap.put("url",url);
        list.add(urlMap);
        resMap.put("errno",0);
        resMap.put("data",list);
        return resMap;
    }

}
