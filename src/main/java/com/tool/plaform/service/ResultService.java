package com.tool.plaform.service;

import com.tool.plaform.entity.Result;


public interface ResultService {
    int insert(Result result);
    Result selectByPrimaryKey(Integer id);
}
