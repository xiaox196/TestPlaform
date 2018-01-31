package com.tool.plaform.service;

import com.tool.plaform.entity.User;
import com.tool.plaform.entity.UserQuery;
import com.tool.plaform.utils.UpdatePasswordInput;

import java.util.List;

public interface UserService {

    int insert(User user);
    int updateUser(User user);
    User queryUserById(int id);
    User login(UserQuery userQuery);
    int updatePassword(UpdatePasswordInput input, int userId);
    int updateNickname(int userId, String nickname);
    List<User> findUserList(UserQuery userQuery);
}
