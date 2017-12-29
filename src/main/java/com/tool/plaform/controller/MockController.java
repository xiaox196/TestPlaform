package com.tool.plaform.controller;

import com.tool.plaform.dao.MockDao;
import com.tool.plaform.domain.Mock;
import org.springframework.web.bind.annotation.*;

@RestController
public class MockController {
    MockDao mockDao = new MockDao();



    @RequestMapping(value = "/mock/{applicationId}")
    public static String executeMock(@PathVariable("applicationId")  String applicationId,String param) {
//        if (mock.operateCode == Mock.GET_OPERATE) {
//            mock.returnUrl = "";
//            mock.resultMsg="";
//        } else if (mock.operateCode == Mock.POST_OPERATE) {
//            mock.returnUrl = "";
//
//        }
//        mockDao.addMockDataToDb(mock);

        return param;
    }

    @ModelAttribute
    Mock setmock() {
        return new Mock();
    }
}
