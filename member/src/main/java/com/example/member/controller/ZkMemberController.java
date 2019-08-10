package com.example.member.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ZkMemberController {

    @Value("${server.port}")
    private String port;
    @RequestMapping("/getMember")
    public  String getMember(){
        return "调用会员服务,端口号为：" +port;
    }
}
