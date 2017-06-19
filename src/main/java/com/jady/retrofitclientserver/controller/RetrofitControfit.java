package com.jady.retrofitclientserver.controller;

import com.jady.retrofitclientserver.model.DeleteBody;
import com.jady.retrofitclientserver.model.ServerResult;
import com.jady.retrofitclientserver.model.User;
import com.jady.retrofitclientserver.model.UserForLogin;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lipingfa on 2017/6/16.
 */
@RestController
public class RetrofitControfit {
    /**
     * get请求
     *
     * @return
     */
    @GetMapping("/user/info")
    public ServerResult getUser() {
        ServerResult serverResult = new ServerResult<User>();
        serverResult.setServer_time(System.currentTimeMillis());
        serverResult.setSuccess(true);
        User user = new User("jady", 12);
        serverResult.setData(user);
        return serverResult;
    }

    @PostMapping("/user/login")
    public ServerResult login(@RequestParam String name, @RequestParam String password) {
        ServerResult serverResult = new ServerResult<String>();
        serverResult.setServer_time(System.currentTimeMillis());
        if ("jady".equals(name) && "1234".equals(password)) {
            serverResult.setSuccess(true);
            serverResult.setData("addafeas_cdedhyuj_daledage_leiaefss");
        } else {
            serverResult.setSuccess(false);
            serverResult.setErr_code("1000");
            serverResult.setMessage("用户名或密码错误");
        }
        return serverResult;
    }

    @PostMapping(path = "/user/loginByBody")
    public ServerResult login(@RequestBody UserForLogin userForLogin) {
        ServerResult serverResult = new ServerResult<String>();
        serverResult.setServer_time(System.currentTimeMillis());
        if ("jady".equals(userForLogin.getName()) && "1234".equals(userForLogin.getPassword())) {
            serverResult.setSuccess(true);
            serverResult.setData("addafeas_cdedhyuj_daledage_leiaefss");
        } else {
            serverResult.setSuccess(false);
            serverResult.setErr_code("1000");
            serverResult.setMessage("用户名或密码错误");
        }
        return serverResult;
    }

    @PutMapping(path = "/user/update")
    public ServerResult updateUserInfo(@RequestHeader("access_token") String accessToken, @RequestParam(required = false) String name, @RequestParam(required = false) String age) {
        ServerResult serverResult = new ServerResult();
        serverResult.setServer_time(System.currentTimeMillis());
        if ("1234".equals(accessToken)) {
            //更新用户信息
            serverResult.setSuccess(true);
        } else {
            serverResult.setSuccess(false);
            serverResult.setErr_code("10001");
            serverResult.setMessage("更新接口token错误");
        }
        return serverResult;
    }

    @DeleteMapping(path = "/feed/delete")
    public ServerResult deleteFeed(@RequestHeader("access_token") String accessToken, @RequestBody DeleteBody body) {
        ServerResult serverResult = new ServerResult();
        serverResult.setServer_time(System.currentTimeMillis());
        if ("1234".equals(accessToken)) {
            //删除feed
            serverResult.setSuccess(true);
        } else {
            serverResult.setSuccess(false);
            serverResult.setErr_code("10001");
            serverResult.setMessage("删除接口token错误");
        }
        return serverResult;
    }
}
