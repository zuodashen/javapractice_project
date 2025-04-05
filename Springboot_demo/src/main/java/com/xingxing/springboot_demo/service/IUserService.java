package com.xingxing.springboot_demo.service;

import com.xingxing.springboot_demo.pojo.User;
import com.xingxing.springboot_demo.pojo.dto.UserDto;

public interface IUserService {
    /**
     * 添加用户
     *
     * @param user 参数
     * @return
     */
    User add(UserDto user);

    /**
     *  查询用户
     * @param userId
     * @return
     */
    User getUser(Integer userId);

    /**
     * 修改用户
     * @param user 修改的用户对象
     * @return
     */
    User edit(UserDto user);

    /**
     * 删除用户
     *
     * @param userId
     */
    void del(Integer userId);
}
