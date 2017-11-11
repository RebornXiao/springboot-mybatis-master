package cn.no7player.service;


import cn.no7player.model.User;

public interface IUserService extends IBaseService<User> {
    User findByIdId(Long id);
}
