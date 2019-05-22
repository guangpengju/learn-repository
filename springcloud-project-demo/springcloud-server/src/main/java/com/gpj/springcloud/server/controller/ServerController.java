package com.gpj.springcloud.server.controller;

import com.gpj.springcloud.server.Server;
import com.gpj.springcloud.server.service.ServerService;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ServerController implements Server{
    @Value("${server.port}")
    private String port;
    
    @Autowired
    private ServerService service;

    @Override
    public List users() {
        log.info("server服务【{}】被调用",port);
        return service.users();
    }

    @Override
    public Map user(@PathVariable("id") String loginId) {
        log.info("server服务【{}】被调用",port);
        return service.user(loginId);
    }
}
