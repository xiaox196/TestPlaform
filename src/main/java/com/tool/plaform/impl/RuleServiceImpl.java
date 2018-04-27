package com.tool.plaform.impl;

import com.tool.plaform.dao.RuleMapper;
import com.tool.plaform.entity.Rule;
import com.tool.plaform.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RuleServiceImpl implements RuleService{

    @Autowired
    RuleMapper ruleMapper;

    @Override
    public int insert(Rule rule) {
        return 0;
    }

    @Override
    public Rule queryRuleByCode(String code) {
        return null;
    }

    @Override
    public List<Rule> findAllRule() {
        List<Rule> rules=ruleMapper.findAllRule();
        List<String> resultCode=new ArrayList<>();
        List<Rule> resultList=new ArrayList<>();
        for(Rule rule:rules){
            String result=rule.getResult();
            String condContent=rule.getCondContent();
            if(result!=null&&!result.equalsIgnoreCase("")&&condContent!=null&&!resultCode.contains(result)){
                resultCode.add(result);
                resultList.add(rule);
            }
        }
        return resultList;
    }

    @Override
    public int updateByPrimaryKey(Rule record) {
        return 0;
    }

    @Override
    public Rule selectByPrimaryKey(Integer id) {
        return null;
    }
}
