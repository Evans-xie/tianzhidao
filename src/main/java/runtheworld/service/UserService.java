package runtheworld.service;

import org.springframework.stereotype.Service;
import runtheworld.entity.User;


@Service
public interface UserService {


    /**
     * 根据id和password获取用户
     * @param name
     * @param password
     * @return
     */
    public User getUser (String name, String password);

    public boolean addUser(User user);

    public boolean isUserExist(String name);

    public boolean isUserCorrert(User user);
}
