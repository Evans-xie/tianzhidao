package runtheworld.service;

import org.springframework.beans.factory.annotation.Autowired;
import runtheworld.dao.UserMapper;
import runtheworld.entity.User;
import runtheworld.entity.UserExample;
import runtheworld.entity.UserExample.Criteria;

import java.util.List;

/**
 * Copyright(C) 2018-2018
 * Author: wanhaoran
 * Date: 2018/5/9 16:10
 */
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUser(String name, String password) {
        UserExample userExample = new UserExample();
        Criteria criteria = userExample.createCriteria();
        criteria.andNameEqualTo(name);
        criteria.andPasswordEqualTo(password);
        List<User> users = userMapper.selectByExample(userExample);
        if (users.isEmpty()){
            return  null;
        }else {
            return  users.get(0);//返回第一个符合条件的，应该只有一个符合条件
        }
    }

    @Override
    public boolean addUser(User user){
        int count = userMapper.insertSelective(user);
        if (count >0) return true;
        else return  false;
    }


    @Override
    public boolean isUserCorrert(User user){
        if (user.getName() == null ||user.getPassword() ==null ||user.getClassName() ==null){
            return  false;
        }
        return  true;
    }

    @Override
    public boolean isUserExist(String name){
        UserExample userExample = new UserExample();
        Criteria criteria = userExample.createCriteria();
        criteria.andNameEqualTo(name);
        if (userMapper.selectByExample(userExample) !=null){
            return  true;
        }
        return false;
    }
}
