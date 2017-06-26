package com.jady.retrofitclientserver.controller;

import com.jady.retrofitclientserver.model.DeleteBody;
import com.jady.retrofitclientserver.model.ServerResult;
import com.jady.retrofitclientserver.model.User;
import com.jady.retrofitclientserver.model.UserForLogin;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * Created by lipingfa on 2017/6/16.
 */
@RestController
public class FileUploadController {
    /**
     * 单文件上传
     *
     * @return
     */
    @PostMapping("/file/upload/single")
    public ServerResult uploadFile(@RequestParam("file") MultipartFile file) {

        ServerResult serverResult = new ServerResult<String>();
        serverResult.setServer_time(System.currentTimeMillis());

        if (!file.isEmpty()) {
            try {
                BufferedOutputStream out = new BufferedOutputStream(
                        new FileOutputStream(new File("files/" + file.getOriginalFilename())));
                out.write(file.getBytes());
                out.flush();
                out.close();
                serverResult.setSuccess(true);
            } catch (Exception e) {
                e.printStackTrace();
                serverResult.setSuccess(false);
                serverResult.setErr_code("10010");
                serverResult.setMessage("上传失败:" + e.getMessage());
            }
        } else {
            serverResult.setSuccess(false);
            serverResult.setErr_code("10010");
            serverResult.setMessage("上传失败，文件是空的");
        }
        return serverResult;
    }

    /**
     * 多文件上传
     *
     * @return
     */
    @PostMapping("/file/upload/multiple")
    public ServerResult uploadFiles(HttpServletRequest request) {
        ServerResult serverResult = new ServerResult<String>();
        serverResult.setServer_time(System.currentTimeMillis());

        Map<String, MultipartFile> files = ((MultipartHttpServletRequest) request).getFileMap();
        BufferedOutputStream stream = null;
        for (MultipartFile multipartFile : files.values()) {
            if (!multipartFile.isEmpty()) {
                try {
                    byte[] bytes = multipartFile.getBytes();
                    stream = new BufferedOutputStream(new FileOutputStream(new File("files/" + multipartFile.getOriginalFilename())));
                    stream.write(bytes);
                    stream.close();
                } catch (Exception e) {
                    stream = null;
                    serverResult.setSuccess(false);
                    serverResult.setErr_code("10010");
                    serverResult.setMessage("上传失败:" + e.getMessage());
                    return serverResult;
                }
            } else {
                serverResult.setSuccess(false);
                serverResult.setErr_code("10010");
                serverResult.setMessage("上传失败，文件是空的");
                return serverResult;
            }
        }
        serverResult.setSuccess(true);
        return serverResult;
    }
}
