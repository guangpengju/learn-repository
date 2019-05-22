package com.gpj.springcloud.client.service;

import com.gpj.springcloud.client.api.ServerApi;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    @Autowired
    ServerApi api;

    public List users(){
        return api.users();
    }
    
    public Map user(String loginId){
        return api.user(loginId);
    }
}
