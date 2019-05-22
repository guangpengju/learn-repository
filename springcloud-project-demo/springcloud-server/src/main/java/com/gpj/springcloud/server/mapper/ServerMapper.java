package com.gpj.springcloud.server.mapper;

import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface ServerMapper {
    public HashMap getUserByLoginId(String loginId);

    public List getUsers();
}
