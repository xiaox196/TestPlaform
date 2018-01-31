package com.tool.plaform.impl;

import com.tool.plaform.entity.User;
import com.tool.plaform.entity.UserQuery;
import com.tool.plaform.service.UserService;
import com.tool.plaform.utils.UpdatePasswordInput;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public int insert(User user) {
        if (user.getName().isEmpty())
        {
            throw new IllegalArgumentException("登录名称不能为空");
        }
        if (user.getPassword().isEmpty())
        {
            throw new IllegalArgumentException("用户密码不能为空");
        }
        return 0;
    }

    @Override
    public int updateUser(User user) {
        return 0;
    }

    @Override
    public User queryUserById(int id) {
        return null;
    }

    @Override
    public User login(UserQuery userQuery) {
        return null;
    }

    @Override
    public int updatePassword(UpdatePasswordInput input, int userId) {
        return 0;
    }

    @Override
    public int updateNickname(int userId, String nickname) {
        return 0;
    }

    @Override
    public List<User> findUserList(UserQuery userQuery) {
        return null;
    }
}
