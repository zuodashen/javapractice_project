package com.example.service;
import cn.hutool.core.util.StrUtil;
import com.example.entity.Account;
import com.example.entity.Employee;
import com.example.exception.CustomException;
import com.example.mapper.EmployeeMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Resource
    private EmployeeMapper employeeMapper;



    public List<Employee> selectAll(Employee employee) {
        return employeeMapper.selectAll(employee);
    }

    public Employee selectById(Integer id) {
        return employeeMapper.selectById(id);
    }

    public PageInfo<Employee> selectPage(Employee employee,Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Employee> lsit=employeeMapper.selectAll(employee);
        return PageInfo.of(lsit);
    }

    
    public void add(Employee employee) {
        String username = employee.getUsername();
        Employee dbEmployee = employeeMapper.selectByUsername(username);
        if(dbEmployee != null){//注册账号已经存在
            throw new CustomException("500","注册账号已经存在");
        }
        Employee dbEmployee1 = employeeMapper.selectByNo(employee.getNo());
        if(dbEmployee1 != null){//注册账号已经存在
            throw new CustomException("500","工号已经存在");
        }
        if(StrUtil.isBlank(employee.getPassword())){//密码没填
            employee.setPassword("123");
        }
        if(StrUtil.isBlank(employee.getName())){ //名字没填
            employee.setName(employee.getUsername()); //默认名称
        }
            //一定要设置角色
            employee.setRole("EMP");//员工的角色
            employeeMapper.insert(employee);
    }


    public void update(Employee employee) {
        employeeMapper.updateById(employee);
    }

    public void deleteById(Integer id) {
        employeeMapper.deleteById(id);
    }
    public void deleteBatch(List <Integer> ids) {
        for(Integer id:ids){
            this.deleteById(id);
        }
    }

    public Employee login(Account account) {
        String username = account.getUsername();
        Employee dbEmployee = employeeMapper.selectByUsername(username);
        if(dbEmployee == null ){  //用户名不存在
                throw new CustomException("500","用户名不存在");
        }
        //数据库存在该用户
        String password = account.getPassword();
        if (!dbEmployee.getPassword().equals(password)) {  // 用户输入的密码和数据库密码不一致
            throw new CustomException("500", "账号或密码错误");
        }
        return dbEmployee;
    }


    public void register(Employee employee) {
            this.add(employee);
    }




//    public List<Employee> selectList(Employee employee) {
//        System.out.println(employee);
//        return null;
//    }


}
