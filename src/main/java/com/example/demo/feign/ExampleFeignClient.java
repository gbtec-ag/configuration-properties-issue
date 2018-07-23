package com.example.demo.feign;

import com.example.demo.feign.model.Post;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name ="noName", url = "http://jsonplaceholder.typicode.com", configuration = KeycloakFeignClientConfiguration.class)
public interface ExampleFeignClient {

    @RequestMapping("/posts/1")
    Post getPost();
}