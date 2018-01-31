package com.tool.plaform.controller;

import com.tool.plaform.dao.MockDao;
import com.tool.plaform.dao.TestMobileDao;
import com.tool.plaform.entity.Mock;
import com.tool.plaform.entity.TestPlaform;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class PlaformController {

    TestMobileDao dao = new TestMobileDao();
    MockDao mockDao = new MockDao();
    @RequestMapping("/")
    public String index(Model model) {
        List<TestPlaform> testPlaforms = dao.findAllTestMobile();
        model.addAttribute("mobileInfoList", testPlaforms);
        return "index";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String searchByPlaform(TestPlaform testPlaform, Model model) {
        List<TestPlaform> testPlaforms = dao.findTestMobileByPlaform(testPlaform.getMobile());
        model.addAttribute("mobileInfoList", testPlaforms);
        return "index";
    }

    @RequestMapping(value = "/testshow",method = RequestMethod.GET)
    public String show(){
        return "test";
    }

    @ModelAttribute
    TestPlaform setTestPlaform() {
        return new TestPlaform();
    }

    @ModelAttribute
    Mock setmock() {
        return new Mock();
    }

    @RequestMapping(value = "/mock")
    public String getMockServer(Model model) {
        List<Mock> list=mockDao.findMockDataAll();
        for (Mock mock:list){
            MockController.executeMock(mock.ApiPath,mock.resultMsg);
        }
        model.addAttribute("mockList", list);
        return "mock";
    }

    @RequestMapping(value = "/addmock")
    public String toMockHtml() {
        return "addmock";
    }

    @RequestMapping(value = "/addmockdata", method = RequestMethod.POST)
    public String addMockServer(Mock mock) {
        mockDao.addMockDataToDb(mock);
        return "mock";
    }

}
