package com.gpj.springcloud.server;

import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/server")
public interface Server {
    @RequestMapping("/users")
    public List users();

    @RequestMapping("/user/{id}")
    public Map user(@PathVariable("id") String loginId);
}
