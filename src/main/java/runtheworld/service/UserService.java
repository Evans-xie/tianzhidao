package runtheworld.service;

import org.springframework.stereotype.Service;
import runtheworld.entity.user.User;

@Service
public interface UserService {


    public User getUser (Integer id,String password);

    public boolean addUser(User user);

    public boolean isUserExist(Integer id);

    public boolean isUserCorrert(User user);
}
