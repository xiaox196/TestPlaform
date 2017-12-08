package com.tool.plaform.controller;

import com.tool.plaform.dao.TestMobileDao;
import com.tool.plaform.domain.TestPlaform;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class PlaformController {

    TestMobileDao dao = new TestMobileDao();

    @RequestMapping("/")
    public String index(Model model) {
        List<TestPlaform> testPlaforms = dao.findAllTestMobile();
        model.addAttribute("mobileInfoList", testPlaforms);
        return "index";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String searchByPlaform(TestPlaform testPlaform, Model model) {
        List<TestPlaform> testPlaforms = dao.findTestMobileByPlaform(testPlaform.getPlaform());
        model.addAttribute("mobileInfoList", testPlaforms);
        return "index";
    }

    @ModelAttribute
    TestPlaform setTestPlaform() {
        return new TestPlaform();
    }

}
