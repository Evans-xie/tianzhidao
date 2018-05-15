package runtheworld.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import runtheworld.dto.Message;
import runtheworld.entity.user.User;
import runtheworld.exception.UserLoginException;
import runtheworld.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * Copyright(C) 2018-2018
 * Author: wanhaoran
 * Date: 2018/5/9 16:08
 */
@RestController
@RequestMapping(value = "/user/")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "login")
    public Message getUser(HttpServletRequest request, @RequestBody HashMap<String, Object> loginDemo) {
        if (loginDemo != null) {
            try {
                Integer id = (Integer) loginDemo.get("id");
                String password = (String) loginDemo.get("password");
                User user = userService.getUser(id, password);
                if (user == null) {
                    return new Message(200, "账号密码输入错误");
                } else {
                    return new Message(200, "登陆成功").putData("user", user);
                }
            } catch (UserLoginException e) {
                return new Message(401, e.getMessage());
            }
        }
        return new Message(400,"客户端错误");
    }

    @PostMapping(value = "user")
    public Message addUser(HttpServletRequest request, @RequestBody User newUser) {
        if (newUser != null) {
            try {
                if (userService.isUserExist(newUser.getId()))
                    return new Message(200, "该账号已存在");

                if (!userService.isUserCorrert(newUser))
                    return new Message(200, "信息不完整");

                if (userService.addUser(newUser))
                    return new Message(200, "注册成功");
                else return new Message(200,"注册失败");
            } catch (UserLoginException e) {
                return new Message(401, e.getMessage());
            }
        }
        return new Message(400,"客户端错误");
    }

}
