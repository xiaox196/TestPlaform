package com.tool.plaform.controller;

import com.tool.plaform.dao.RuleMapper;
import com.tool.plaform.entity.Rule;
import com.tool.plaform.entity.RuleWithBLOBs;
import com.tool.plaform.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/rule")
public class RuleController {
    @Autowired
    RuleService ruleMapper;

    @RequestMapping(value = "/findall",method = RequestMethod.GET)
    public List<Rule> findAllRule(){
        return ruleMapper.findAllRule();
    }
}
