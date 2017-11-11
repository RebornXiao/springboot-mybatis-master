package cn.no7player.service.impl;

import cn.no7player.model.Page;
import cn.no7player.model.User;
import cn.no7player.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * Created by zl on 2015/8/27.
 */
@Service
public class UserService extends BaseService<User> implements IUserService {

    @Override
    public User findByIdId(Long id) {
        return userMapper.findByIdId(id);
    }

}
