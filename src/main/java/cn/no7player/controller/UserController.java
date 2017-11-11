package cn.no7player.controller;

import cn.no7player.model.Page;
import cn.no7player.model.User;
import cn.no7player.service.impl.UserService;
import com.google.common.base.Supplier;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by zl on 2015/8/27.
 */
@Controller
public class UserController {

    private Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/get")
    @ResponseBody
    public Page<User> getUserInfo() {
        Page<User> page = new Page<>();
        page.setPage(1);
        page.setStart(1);
        page.setRows(20);
        Page<User> user = userService.selectPage(page);
        return user;
    }

    @RequestMapping("/id/{id}")
    @ResponseBody
    public User getUserInfo(@PathVariable Long id) {
        User user = userService.findByIdId(id);
        return user;
    }

    @RequestMapping("/i/{id}")
    @ResponseBody
    public User getUserIn(@PathVariable Long id) {
        User user = userService.findByIdId(id);
        return user;
    }

    //通过关键字分页查询
    @RequestMapping("/sel")
    @ResponseBody //如果返回json格式，需要这个注解，这里用来测试环境
    public Object selectPage(Page<User> page){
        Page p = userService.selectPage(page);
        System.out.println("----page:"+page);
        return page.getPageMap();
    }
}
