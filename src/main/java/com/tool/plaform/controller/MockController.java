package com.tool.plaform.controller;

import com.tool.plaform.dao.MockDao;
import com.tool.plaform.entity.Mock;
import org.springframework.web.bind.annotation.*;

@RestController
public class MockController {
    MockDao mockDao = new MockDao();



    @RequestMapping(value = "/mock/{applicationId}")
    public static String executeMock(@PathVariable("applicationId")  String applicationId,String param) {

        return param;
    }

    @ModelAttribute
    Mock setmock() {
        return new Mock();
    }
}
