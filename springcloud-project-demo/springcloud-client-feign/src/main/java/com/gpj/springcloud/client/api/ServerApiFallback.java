package com.gpj.springcloud.client.api;

import com.gpj.springcloud.server.Server;
import java.util.List;
import java.util.Map;

public class ServerApiFallback implements Server {
    @Override
    public List users() {
        return null;
    }

    @Override
    public Map user(String loginId) {
        return null;
    }
}
