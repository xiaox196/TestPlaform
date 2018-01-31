package com.tool.plaform.controller;

import com.tool.plaform.entity.User;
import com.tool.plaform.service.UserService;
import com.tool.plaform.utils.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public ApiResult<User> saveUser(@RequestBody User user){
        user.setEnable(true);
        user.setPassword("123456");
        return ApiResult.success(user);
    }
}
