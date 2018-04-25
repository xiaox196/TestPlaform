package com.tool.plaform.service;

import com.tool.plaform.entity.Rule;
import com.tool.plaform.entity.RuleWithBLOBs;

import java.util.List;

public interface RuleService {
    public int insert(Rule rule);
    public Rule queryApiByparam(String param);
    public List<Rule> findAllRule();
    int updateByPrimaryKey(Rule record);
    Rule selectByPrimaryKey(Integer id);
}
