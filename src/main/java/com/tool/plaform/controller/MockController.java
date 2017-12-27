package com.tool.plaform.controller;

import com.tool.plaform.domain.Mock;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MockController {

    @RequestMapping(value = "/mock")
    public  String getMockServer(){
        return "mock";
    }

    @RequestMapping(value = "/addmock")
    public  String addMockServer(){
        return "addmock";
    }


    @ModelAttribute
    Mock setmock (){
        return new Mock();
    }
}
