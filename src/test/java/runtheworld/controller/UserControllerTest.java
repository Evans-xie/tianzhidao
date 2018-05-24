package runtheworld.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import runtheworld.entity.User;
import runtheworld.entity.UserDemo.UserLogin;
import runtheworld.exception.UserLoginException;
import runtheworld.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-context.xml")
public class UserControllerTest {

    @Autowired
    private UserService userService;

    @Test
    public void getUser() {
        UserLogin loginDemo = new UserLogin();
        loginDemo.setName("wan");
        loginDemo.setPassword("123");
        if (loginDemo != null) {
            try {
                String name = loginDemo.getName();
                String password = loginDemo.getPassword();
                User user = userService.getUser(name, password);
                if (user == null) {
                    System.out.println("用户不存在");
                } else {
                    System.out.println("用户存在 "+user.toString());
                }
            } catch (UserLoginException e) {
                System.out.println("出现异常");
            }
        }

    }

    @Test
    public void addUser() {
        User newUser = new User();
        newUser.setName("wan");
        newUser.setPassword("123");
        if (newUser != null) {
            try {
                if (userService.isUserExist(newUser.getName())) {
                    System.out.println("该账号已存在");
                    return;
                }
                if (!userService.isUserCorrert(newUser)) {
                    System.out.println("信息不完整");
                    return;
                }
                if (userService.addUser(newUser))
                    System.out.println("注册成功");

                else
                    System.out.println("注册失败");
                return;
            } catch (UserLoginException e) {
                System.out.println("出现异常");
                return;
            }
        }
        System.out.println("客户端错误");
    }
}