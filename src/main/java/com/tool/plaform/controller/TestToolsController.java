package com.tool.plaform.controller;

import com.tool.plaform.dao.TestToolsDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestToolsController {
    TestToolsDao testToolsDao=new TestToolsDao();

    @RequestMapping(value = "/update")
    public String updateLoanDabaBase(){

        return null;
    }


}
