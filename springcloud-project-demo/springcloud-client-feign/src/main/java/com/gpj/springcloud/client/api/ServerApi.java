package com.gpj.springcloud.client.api;

import com.gpj.springcloud.server.Server;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "PROJECT-SERVER",fallback = ServerApiFallback.class,path = "server")
public interface ServerApi extends Server {
    
}
