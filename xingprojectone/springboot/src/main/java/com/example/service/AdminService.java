package com.example.service;
import cn.hutool.core.util.StrUtil;
import com.example.entity.Account;
import com.example.entity.Admin;
import com.example.exception.CustomException;
import com.example.mapper.AdminMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    @Resource
    private AdminMapper adminMapper;


    public List<Admin> selectAll(Admin admin) { return adminMapper.selectAll(admin);}
//    public List<admin> selectAll(Employee employee) {
//        return employeeMapper.selectAll(employee);
//    }

//    public Employee selectById(Integer id) {
//        return employeeMapper.selectById(id);
//    }

    public Admin selectById(Integer id){ return adminMapper.selectById(id);}

//    public PageInfo<Employee> selectPage(Employee employee, Integer pageNum, Integer pageSize) {
//        PageHelper.startPage(pageNum,pageSize);
//        List<Employee> lsit=employeeMapper.selectAll(employee);
//        return PageInfo.of(lsit);
//    }
    public PageInfo<Admin> selectPage(Admin admin,Integer pageNum,Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Admin>  list =adminMapper.selectAll(admin);
        return PageInfo.of(list);
    }

    
    public void add(Admin admin) {
        String username = admin.getUsername();
        Admin dbAdmin = adminMapper.selectByUsername(username);
        if(dbAdmin != null){//注册账号已经存在
            throw new CustomException("500","注册账号已经存在");
        }
        if(StrUtil.isBlank(admin.getPassword())){//密码没填
            admin.setPassword("admin");
        }
        if(StrUtil.isBlank(admin.getName())){ //名字没填
            admin.setName(admin.getUsername()); //默认名称
        }
            //一定要设置角色
            admin.setRole("ADMIN");//员工的角色
            adminMapper.insert(admin);
    }


    public void update(Admin admin) {
        adminMapper. updateById(admin);
    }

    public void deleteById(Integer id) {
        adminMapper.deleteById(id);
    }
    public void deleteBatch(List <Integer> ids) {
        for(Integer id:ids){
            this.deleteById(id);
        }
    }

    public Admin login(Account account) {
        String username = account.getUsername();
        Admin dbAdmin = adminMapper.selectByUsername(username);
        if(dbAdmin == null ){  //用户名不存在
                throw new CustomException("500","用户名不存在");
        }
        //数据库存在该用户
        String password = account.getPassword();
        if (!dbAdmin.getPassword().equals(password)) {  // 用户输入的密码和数据库密码不一致
            throw new CustomException("500", "账号或密码错误");
        }
        return dbAdmin;
    }
    public void updatePassword(Account account) {
        Integer id = account.getId();
        Admin admin = this.selectById(id);
        if(!admin.getPassword().equals(account.getPassword())){
            throw new CustomException("500","旧密码错误");
        }
        admin.setPassword(account.getNewPassword());
        this.update(admin);
    }





//    public List<Admin> selectList(Admin admin) {
//        System.out.println(admin);
//        return null;
//    }


}
