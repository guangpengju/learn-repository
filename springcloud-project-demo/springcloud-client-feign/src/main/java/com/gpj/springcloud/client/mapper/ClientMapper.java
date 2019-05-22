package com.gpj.springcloud.client.mapper;

import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientMapper {
    public HashMap getUserByLoginId(String loginId);

    public List getUsers();
    
}
