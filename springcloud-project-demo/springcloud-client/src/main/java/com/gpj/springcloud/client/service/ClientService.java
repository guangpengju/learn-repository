package com.gpj.springcloud.client.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ClientService {
    @Autowired
    RestTemplate template;
    
    public List users(){
        return template.getForEntity("http://PROJECT-SERVER/server/server/users",List.class).getBody();
    }
}
