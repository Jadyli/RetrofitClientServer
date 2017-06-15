package com.jady.retrofitclientserver.controller;

import com.jady.retrofitclientserver.model.HelloWorld;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lipingfa on 2017/6/15.
 */
@RestController("/hello")
public class HelloController {

    @Autowired
    private HelloWorld helloWorld;

    public HelloController(HelloWorld helloWorld) {
        this.helloWorld = helloWorld;
    }

    @GetMapping
    public String hello() {
        return helloWorld.hello();
    }
}
