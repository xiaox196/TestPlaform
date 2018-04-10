package com.tool.plaform.controller;

import com.tool.plaform.entity.Api;
import com.tool.plaform.entity.HttpBean;
import com.tool.plaform.service.ApiService;
import com.tool.plaform.utils.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class ApiController {

    @Autowired
    ApiService apiService;

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public ApiResult<Api> saveApi(@RequestBody Api api){
        apiService.insert(api);
        return ApiResult.success(api);
    }

    @RequestMapping(value = "/findall",method = RequestMethod.GET)
    public ApiResult<List<Api>> FindAll(){
        return ApiResult.success(apiService.findAllApi());
    }

    @RequestMapping(value = "/find",method = RequestMethod.POST)
    public ApiResult<Api> FindBy(@RequestBody HttpBean httpBean){
        List<Api> list=new ArrayList<>();
        Api api=new Api();
        return ApiResult.success(apiService.selectByPrimaryKey(httpBean.getId()));
    }


    @RequestMapping(value = "/test",method = RequestMethod.POST)
    public String doTest(@RequestBody Api api){
        String result=apiService.apiTest(api);
        return result;
    }
}
