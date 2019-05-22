package com.gpj.springcloud.client.controller;

import com.gpj.springcloud.client.service.ClientService;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    private ClientService service;
    
    @RequestMapping("/users")
    public List users(){
        return service.users();
    }

    @RequestMapping("/user/{id}")
    public Map user(@PathVariable("id") String loginId){
        return service.user(loginId);
    }
}
