package runtheworld.service;

import org.springframework.stereotype.Service;
import runtheworld.entity.User;


@Service
public interface UserService {


    /**
     * 根据id和password获取用户
     * @param id
     * @param password
     * @return
     */
    public User getUser (Long id, String password);

    public boolean addUser(User user);

    public boolean isUserExist(Long id);

    public boolean isUserCorrert(User user);
}
