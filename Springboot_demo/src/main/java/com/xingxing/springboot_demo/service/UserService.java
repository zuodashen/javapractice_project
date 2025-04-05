package com.xingxing.springboot_demo.service;


import com.xingxing.springboot_demo.mapper.UserMapper;
import com.xingxing.springboot_demo.pojo.User;
import com.xingxing.springboot_demo.pojo.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service   //spring的bean
public class UserService implements IUserService {

    @Autowired
    UserMapper userMapper;
    @Override
    public User add(UserDto user) {
        User userPojo = new User();
        BeanUtils.copyProperties(user,userPojo);
        return userMapper.save(userPojo);
    }

    @Override
    public User getUser(Integer userId) {
        return userMapper.findById(userId).orElseThrow(() -> {
            throw new IllegalArgumentException("用户不存在");
        });
    }

    @Override
    public User edit(UserDto user) {
        User userPojo = new User();
        BeanUtils.copyProperties(user,userPojo);
        return userMapper.save(userPojo); //save方法只允许接收pojo类型对象
    }
    @Override
    public void del(Integer userId) {
        userMapper.deleteById(userId);
    }
}























