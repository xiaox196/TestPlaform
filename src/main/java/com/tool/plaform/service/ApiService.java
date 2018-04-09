package com.tool.plaform.service;

import com.tool.plaform.entity.Api;

import java.util.List;

public interface ApiService {
    public int insert(Api api);
    public Api queryApiByparam(String param);
    public List<Api> findAllApi();
}
