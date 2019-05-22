package com.gpj.springcloud.server.service;

import com.gpj.springcloud.server.mapper.ServerMapper;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServerService {
    @Autowired
    ServerMapper mapper;

    public List users(){
        return mapper.getUsers();
    }
    
    public Map user(String loginId){
        return mapper.getUserByLoginId(loginId);
    }
}
