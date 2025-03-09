package com.example.controller;

import com.example.common.Result;
import com.example.entity.Account;
import com.example.entity.Employee;
import com.example.exception.CustomException;
import com.example.service.AdminService;
import com.example.service.EmployeeService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class Webcontroller {

    @Resource
    private EmployeeService employeeService;
    @Resource
    private AdminService adminService;

    @GetMapping("/hello")
    public String hello() {
        return "Hello Ha";
    }


    /**
     * 管理员或者员工登录
     * @param account
     * @return
     */

    @PostMapping("/login")
    public Result login(@RequestBody Account account) {
        Account result = null;
       if("ADMIN".equals(account.getRole())){
           result = adminService.login(account);
       } else if ("EMP".equals(account.getRole())){
           result = employeeService.login(account);
       }
//       Employee loggedInEmployee = employeeService.login(account);
         return Result.success(result);
        }


    @PostMapping("/register")
    public Result register(@RequestBody Employee employee) {
        if (employee == null) {
            return Result.error();
        }
        employeeService.register(employee);
        return Result.success();
    }


}












//    @GetMapping("/weather")
//    public Result weather(){
//        return  Result.success("今天天气：晴 23摄氏度");
//    }
//    @GetMapping("/count")
//    public Result count(){
//        //int a = 1 / 0;
//        //return Result.success(10);
//        //throw new RuntimeException("服务器异常");
//        throw new CustomException("500","错误！禁止请求！");
//    }
//    @GetMapping("/map")
//    public Result map(){
//        HashMap<String,Object> map = new HashMap<>();
//        map.put("name","星星");
//        map.put("age",18);
//        return Result.success(map);
//    }
