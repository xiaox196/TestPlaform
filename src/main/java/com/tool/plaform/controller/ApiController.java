package com.tool.plaform.controller;

import com.tool.plaform.entity.Api;
import com.tool.plaform.service.ApiService;
import com.tool.plaform.utils.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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


}
