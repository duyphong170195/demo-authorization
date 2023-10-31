package com.example.demoauthorization.controller;

import com.example.demoauthorization.annotation.Authorize;
import com.example.demoauthorization.annotation.CustomAppAnnotation;
import com.example.demoauthorization.request.HelloRequestWithArea;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/hello")
public class HelloController {


    @GetMapping("/{id}")
    public void hello1(@PathVariable String id,
                       @RequestParam String query1,
                       @RequestParam String query2,
                       @RequestParam String query3) {
        System.out.println("Hello world");
    }

    @PostMapping("/post")
    public void postHello(@RequestBody(required = true) HelloRequestWithArea body) {
        System.out.println("Hello world");
    }
}
