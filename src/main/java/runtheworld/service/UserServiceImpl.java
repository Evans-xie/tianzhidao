package runtheworld.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import runtheworld.dao.UserMapper;
import runtheworld.entity.User;
import runtheworld.entity.UserExample;
import runtheworld.entity.UserExample.Criteria;
import runtheworld.exception.ServerInnerException;

import java.util.Date;
import java.util.List;

/**
 * Copyright(C) 2018-2018
 * Author: wanhaoran
 * Date: 2018/5/9 16:10
 */
@Service
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
        try {
            user.setGmtCreate(new Date());
            int count = userMapper.insertSelective(user);
            if (count >0) return true;
            else return  false;
        }catch (Exception e){
            throw new ServerInnerException("操作异常");
        }

    }


    @Override
    public boolean isUserCorrert(User user){
        try {
            if (user.getName() == null ||user.getPassword() ==null ||user.getClassName() ==null){
                return  false;
            }
            return  true;
        }catch (Exception e){
            throw new ServerInnerException("操作异常");
        }
    }

    /**
     * 根据name来判断是否存在该账号
     * @param name
     * @return
     */
    @Override
    public boolean isUserExist(String name){
        try {
            UserExample userExample = new UserExample();
            Criteria criteria = userExample.createCriteria();
            criteria.andNameEqualTo(name);
            if (!userMapper.selectByExample(userExample).isEmpty()){
                return  true;
            }
            return false;
        }catch (Exception e){
            throw new ServerInnerException("操作异常");
        }
    }
}
