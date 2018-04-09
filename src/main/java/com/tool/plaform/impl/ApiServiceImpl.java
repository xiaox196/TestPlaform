package com.tool.plaform.impl;

import com.tool.plaform.dao.ApiMapper;
import com.tool.plaform.entity.Api;

import com.tool.plaform.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApiServiceImpl implements ApiService{

    @Autowired
    ApiMapper apiMapper ;

    @Override
    public int insert(Api api) {
        if (api.getMethod().isEmpty())
        {
            throw new IllegalArgumentException("请求方法不能为空");
        }
        if (api.getParam().isEmpty()||api.getParam().equals(""))
        {
            throw new IllegalArgumentException("请求参数不能为空");
        }
        return apiMapper.insert(api);
    }

    @Override
    public Api queryApiByparam(String param) {
        return null;
    }

    @Override
    public List<Api> findAllApi() {
        return apiMapper.findAllApi();
    }
}
