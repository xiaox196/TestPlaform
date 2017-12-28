package com.tool.plaform.controller;

import com.tool.plaform.dao.MockDao;
import com.tool.plaform.domain.Mock;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MockController {
    MockDao mockDao = new MockDao();

    @RequestMapping(value = "/mock")
    public String getMockServer() {
        return "mock";
    }

    @RequestMapping(value = "/addmock")
    public String ToMockHtml() {
        return "addmock";
    }

    @RequestMapping(value = "/addmockdata", method = RequestMethod.POST)
    public String addMockServer(Mock mock) {
        mockDao.addMockDataToDb(mock);
        return "mock";
    }

    @RequestMapping(value = "/execute", method = RequestMethod.POST)
    public String executeMock(Mock mock) {
        mockDao.addMockDataToDb(mock);
        if (mock.operateCode == Mock.GET_OPERATE) {
            mock.returnUrl = "";
            mock.resultMsg="";
        } else if (mock.operateCode == Mock.POST_OPERATE) {
            mock.returnUrl = "";

        }
        return "mock";
    }

    @ModelAttribute
    Mock setmock() {
        return new Mock();
    }
}
