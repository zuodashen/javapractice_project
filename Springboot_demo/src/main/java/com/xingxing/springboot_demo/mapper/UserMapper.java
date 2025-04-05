package com.xingxing.springboot_demo.mapper;

import com.xingxing.springboot_demo.pojo.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository   //数据访问层的Bean
public interface UserMapper extends CrudRepository<User,Integer> {

}
