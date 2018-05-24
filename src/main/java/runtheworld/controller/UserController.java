package runtheworld.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import runtheworld.dto.Message;
import runtheworld.entity.User;
import runtheworld.entity.UserDemo.UserLogin;
import runtheworld.exception.UserLoginException;
import runtheworld.service.UserService;

import javax.servlet.http.HttpServletRequest;

/**
 * Copyright(C) 2018-2018
 * Author: wanhaoran
 * Date: 2018/5/9 16:08
 */
@RestController
@RequestMapping(value = "/user/")
@Api(value = "/UserController",description = "用户登陆与注册接口")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * HashMap中存储的是{name,password}
     * @param request
     * @param loginDemo
     * @return
     */
    @ApiOperation(value = "用户登陆接口",notes = "根据用户的name和password来登陆")
    @PostMapping(value = "login")
    public Message getUser(HttpServletRequest request, @RequestBody UserLogin loginDemo) {
        if (loginDemo != null) {
            try {
                String name = loginDemo.getName();
                String password = loginDemo.getPassword();
                User user = userService.getUser(name, password);
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

    /**
     * 账号注册接口
     * @param request
     * @param newUser
     * @return
     */
    @ApiOperation(value = "注册",notes = "注册,不传id")
    @PostMapping(value = "user")
    public Message addUser(HttpServletRequest request, @RequestBody User newUser) {
        if (newUser != null) {
            try {
                if (userService.isUserExist(newUser.getName()))
                    return new Message(201, "该账号已存在");

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
