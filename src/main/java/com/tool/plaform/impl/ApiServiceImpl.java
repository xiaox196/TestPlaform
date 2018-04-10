package com.tool.plaform.impl;

import com.alibaba.fastjson.JSON;
import com.tool.plaform.dao.ApiMapper;
import com.tool.plaform.entity.Api;
import com.tool.plaform.service.ApiService;
import com.tool.plaform.utils.HttpClientUtil;
import com.tool.plaform.vo.ApiVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
    public String apiTest(ApiVo apiVo) {
        Api api = new Api();
        api.setExpect(apiVo.getExpect());
        Map paramsMap = apiVo.getParams();
        String params = JSON.toJSONString(paramsMap);
        api.setParam(params);

        String result=null;
        String url=apiVo.getUrl();
        String param=api.getParam();
        int port=apiVo.getPort();
        String rootUrl=apiVo.getRootUrl();
        String method=apiVo.getMethod();
        url=buildUrl(rootUrl,url,port,param,method);
        if(method.equalsIgnoreCase("get")){
            result=HttpClientUtil.get(url,5000);
        }else if(method.equalsIgnoreCase("post")){
            result=HttpClientUtil.post(url,param,5000);
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

    public static  String buildUrl(String rootUrl,String url,int port,String param,String method){
        if(!rootUrl.startsWith("http")){
            rootUrl="http://"+rootUrl;
        }

        if(method.equalsIgnoreCase("get")){
            url =rootUrl+":"+port+url+"?"+param;
        }else if(method.equalsIgnoreCase("post")){
            url =rootUrl+":"+port+url;
        }
        System.out.println("===================================================");
        System.out.println("request method:"+method+" request url:"+url+" request param:"+param);
        return url;
    }
}
