package com.example.controller;
import com.example.common.Result;
import com.example.entity.Admin;
import com.example.service.AdminService;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Resource
    private AdminService adminService;
    /**
     * 新增数据
     */
    @PostMapping("/add")
    public Result add(@RequestBody Admin admin){
        adminService.add(admin);
        return Result.success();
    }

    /**
     * 更新数据
     */
    @PutMapping("/update")
        public Result upadte(@RequestBody Admin admin){
        adminService.update(admin);
        return Result.success();
    }
    /**
     * 删除单个数据
     */
    @DeleteMapping("/deleteById/{id}")
    public Result deleteById(@PathVariable Integer id){
        adminService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除多行数据
     */
    @DeleteMapping("/deleteBatch")
    public Result deleteBatch(@RequestBody List<Integer> ids){
            adminService.deleteBatch(ids);
            return Result.success();
    }


    /**
     * 查询所有员工信息
     * @return
     */
    @GetMapping("/selectAll")
    public Result selectAll(Admin admin){
        List<Admin> list = adminService.selectAll(admin);
        return Result.success(list);
    }


//查询单个数据：
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id){
        Admin admin = adminService.selectById(id);
        return Result.success(admin);
    }


//查询单个数据：
//    @GetMapping("/selectOne")
//    public Result selectOne(@RequestParam Integer id){
//        admin admin = adminService.selectById(id);
//        return Result.success(admin);
//    }
//    @GetMapping("/selectList")
//    public Result selectList(admin admin){
//        List<admin> list = adminService.selectList(admin);
//        return Result.success(list);
//    }
    /**
     * 分页查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/selectPage")
    public Result selectPage(Admin admin,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize){
        PageInfo<Admin> pageInfo = adminService.selectPage(admin,pageNum,pageSize);
        return Result.success(pageInfo);
    }

}
