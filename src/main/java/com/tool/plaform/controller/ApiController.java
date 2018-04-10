package com.tool.plaform.controller;

import com.alibaba.fastjson.JSON;
import com.tool.plaform.entity.Api;
import com.tool.plaform.entity.HttpBean;
import com.tool.plaform.service.ApiService;
import com.tool.plaform.utils.ApiResult;
import com.tool.plaform.vo.ApiVo;
import io.swagger.annotations.ApiOperation;
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
    public ApiResult<Api> saveApi(@RequestBody ApiVo apiVo){
        Api api=setApi(apiVo);
        apiService.insert(api);
        return ApiResult.success(api);
    }

    public static Api setApi(ApiVo apiVo){
        Api api=new Api();
        api.setMethod(apiVo.getMethod());
        String params = JSON.toJSONString(apiVo.getParams());
        api.setParam(params);
        api.setExpect(apiVo.getExpect());
        api.setPort(apiVo.getPort());
        api.setUrl(apiVo.getUrl());
        api.setRooturl(apiVo.getRootUrl());
        api.setType(apiVo.getType());
        return api;
    }


    @ApiOperation(value = "查找所有数据", notes = "使用GET方法")
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

    @ApiOperation(value = "接口测试", notes = "使用post方法")
    @RequestMapping(value = "/test",method = RequestMethod.POST)
    public String doTest(@RequestBody ApiVo apiVo){
        String result=apiService.apiTest(apiVo);
        return result;
    }
}
