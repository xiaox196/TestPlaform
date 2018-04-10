package com.tool.plaform.service;

import com.tool.plaform.entity.Api;
import com.tool.plaform.vo.ApiVo;

import java.util.List;

public interface ApiService {
    public int insert(Api api);
    public Api queryApiByparam(String param);
    public List<Api> findAllApi();
    public String apiTest(ApiVo apiVo);
    int updateByPrimaryKey(Api record);
    Api selectByPrimaryKey(Integer id);
}
