package com.tool.plaform.controller;

import com.mongodb.BasicDBObject;
import com.tool.plaform.dao.TestToolsDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/*
* post请求必须要要使用RestController
*
* 在controller上加注解@Controller 和@RestController都可以在前端调通接口，但是二者的区别在于，
* 当用前者的时候在方法上必须添加注解@ResponseBody，如果不添加@ResponseBody，就会报上面错误，
* 因为当使用@Controller 注解时，spring默认方法返回的是view对象（页面）。
* 而加上@ResponseBody，则方法返回的就是具体对象了。@RestController的作用就相当于@Controller+@ResponseBody的结合体
* */

@RestController

public class TestToolsController {
//    TestToolsDao testToolsDao=new TestToolsDao();

    @RequestMapping(value = "/loan/replay" ,method = RequestMethod.GET)
    public boolean updateLoanDabaBase(@RequestParam(value = "env") String env,@RequestParam(value = "loanId") String loanId){
        boolean isAdd=TestToolsDao.save(loanId,env);
        if(isAdd){
            return true;
        }
        return false;
    }

}
