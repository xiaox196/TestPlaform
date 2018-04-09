package com.tool.plaform.impl;

import com.tool.plaform.dao.ApiMapper;
import com.tool.plaform.entity.Api;
import com.tool.plaform.service.ApiService;
import com.tool.plaform.utils.HttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApiServiceImpl implements ApiService {

    @Autowired
    ApiMapper apiMapper;

    @Override
    public int insert(Api api) {
        if (api.getMethod().isEmpty()) {
            throw new IllegalArgumentException("请求方法不能为空");
        }
        if (api.getParam().isEmpty() || api.getParam().equals("")) {
            throw new IllegalArgumentException("请求参数不能为空");
        }
        if (api.getUrl().isEmpty() || api.getUrl().equals("")) {
            throw new IllegalArgumentException("请求url不能为空");
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

    @Override
    public String apiTest(Api api) {
        String result=null;
        String url=api.getUrl();
        String param=api.getParam();
        int port=api.getPort();
        String rootUrl=api.getRooturl();
        String method=api.getMethod();
        url=buildUrl(rootUrl,url,port,param);
        if(method.equals("get")){
            result=HttpClientUtil.get(url,5000);
        }
        return result;
    }

    @Override
    public int updateByPrimaryKey(Api record) {
        return 0;
    }

    @Override
    public Api selectByPrimaryKey(Integer id) {
        return apiMapper.selectByPrimaryKey(id);
    }

    public static  String buildUrl(String rootUrl,String url,int port,String param){
        if(!rootUrl.startsWith("http")){
            rootUrl="http://"+rootUrl;
        }
        url =rootUrl+":"+port+url+"?"+param;
        System.out.println("=========");
        System.out.println("request url:"+url);
        return url;
    }
}
