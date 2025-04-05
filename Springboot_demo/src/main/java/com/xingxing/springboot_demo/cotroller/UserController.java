package com.xingxing.springboot_demo.cotroller;
import com.xingxing.springboot_demo.pojo.ResponseMessage;
import com.xingxing.springboot_demo.pojo.User;
import com.xingxing.springboot_demo.pojo.dto.UserDto;
import com.xingxing.springboot_demo.service.IUserService;
import com.xingxing.springboot_demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user") //localhost:9090/user
public class UserController {
    @Autowired
    IUserService userService;

    //增加
    @PostMapping("/add")     //URL: localhost:9090/user/add
    public ResponseMessage<User> add(@Validated @RequestBody UserDto user){
        User userNew = userService.add(user);
        return ResponseMessage.success(userNew);
    }

    //查询
    @GetMapping("/{userId}") //URL: localhost:9090/user/1
    public ResponseMessage<User> get(@PathVariable Integer userId){
        User userNew = userService.getUser(userId);
        return ResponseMessage.success(userNew);
    }

    //修改
    @PutMapping("/edit")
    public ResponseMessage<User> edit(@Validated @RequestBody UserDto user){
        User userNew = userService.edit(user);
        return ResponseMessage.success(userNew);
    }

    //删除用户
    @DeleteMapping("/{userId}")
    public ResponseMessage<User> delete(@PathVariable Integer userId){
        userService.del(userId);
        return ResponseMessage.success();
    }

}















