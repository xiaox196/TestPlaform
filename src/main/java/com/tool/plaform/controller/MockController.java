package com.tool.plaform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MockController {

    @RequestMapping(value = "/mock")
    public  String getMockServer(){
        return "mock";
    }
}
