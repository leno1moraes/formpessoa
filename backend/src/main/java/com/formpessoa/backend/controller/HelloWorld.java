package com.formpessoa.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test/")
public class HelloWorld {

    @GetMapping("/helloworld")
    public String helloworld(){
        return "Olá Mundo !!!";
    }
}
