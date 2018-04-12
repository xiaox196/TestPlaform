package com.tool.plaform.impl;

import com.tool.plaform.dao.ResultMapper;
import com.tool.plaform.entity.Result;
import com.tool.plaform.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResultServiceImpl  implements ResultService{

    @Autowired
    ResultMapper resultMapper;

    @Override
    public int insert(Result result) {
        if(result.getResult()!=null&&!result.getResult().equals("")){
            return resultMapper.insert(result);
        }else {
            return 0;
        }
    }

    @Override
    public Result selectByPrimaryKey(Integer id) {
        return null;
    }
}
