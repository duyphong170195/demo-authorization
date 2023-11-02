package com.example.demoauthorization.controller;

import com.example.demoauthorization.annotation.Authorize;
import com.example.demoauthorization.annotation.CustomAppAnnotation;
import com.example.demoauthorization.request.ContentAnnouncementCreateRequest;
import com.example.demoauthorization.request.HelloRequestWithArea;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/hello")
public class HelloController {


    @GetMapping("/{id}")
    public void hello1(@PathVariable String id,
                       @RequestParam(required = false) List<Long> areaIds,
                       @RequestParam(required = false) List<Long> blockIds) {
        System.out.println("Hello world");
    }

    @PostMapping("/post")
    public void postHello(@RequestBody HelloRequestWithArea helloRequestWithArea) {
        System.out.println("Hello world");
    }

    @DeleteMapping("/post")
    public void deleteHello(@RequestPart("content_announcement_create_request")
                                ContentAnnouncementCreateRequest request,
                            @RequestPart(value = "file", required = false) MultipartFile file) {
        System.out.println("Hello world");
    }

    @DeleteMapping("/delete")
    public void deleteHello2(@RequestBody List<String> aaa) {
        System.out.println("Hello world");
    }
}
