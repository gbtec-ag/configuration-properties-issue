package com.example.demo.feign;

import com.example.demo.feign.model.Post;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class ExampleFeignClientIT extends TestcontainersBackedIT {

    @Autowired
    public ExampleFeignClient feign;

    @Test
    public void name() {
        Post post = feign.getPost();
        System.out.println(post);
    }

}