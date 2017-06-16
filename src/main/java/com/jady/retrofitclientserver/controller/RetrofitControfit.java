package com.jady.retrofitclientserver.controller;

import org.springframework.boot.json.JsonJsonParser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Map getUser() {
        User user = new User("jady", 12);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("success", true);
        resultMap.put("server_time", System.currentTimeMillis());
        resultMap.put("data", user);
        return resultMap;
    }

}
