package com.example.controller;

import com.example.common.Result;
import com.example.entity.Employee;
import com.example.service.EmployeeService;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Resource
    private EmployeeService employeeService;
    /**
     * 新增数据
     */
    @PostMapping("/add")
    public Result add(@RequestBody Employee employee){
        employeeService.add(employee);
        return Result.success();
    }

    /**
     * 更新数据
     */
    @PutMapping("/update")
        public Result upadte(@RequestBody Employee employee){
        employeeService.update(employee);
        return Result.success();
    }
    /**
     * 删除单个数据
     */
    @DeleteMapping("/deleteById/{id}")
    public Result deleteById(@PathVariable Integer id){
        employeeService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除多行数据
     */
    @DeleteMapping("/deleteBatch")
    public Result deleteBatch(@RequestBody List<Integer> ids){
            employeeService.deleteBatch(ids);
            return Result.success();
    }


    /**
     * 查询所有员工信息
     * @return
     */
    @GetMapping("/selectAll")
    public Result selectAll(Employee employee){
        List<Employee> list = employeeService.selectAll(employee);
        return Result.success(list);
    }


//查询单个数据：
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id){
        Employee employee = employeeService.selectById(id);
        return Result.success(employee);
    }


//查询单个数据：
//    @GetMapping("/selectOne")
//    public Result selectOne(@RequestParam Integer id){
//        Employee employee = employeeService.selectById(id);
//        return Result.success(employee);
//    }
//    @GetMapping("/selectList")
//    public Result selectList(Employee employee){
//        List<Employee> list = employeeService.selectList(employee);
//        return Result.success(list);
//    }
    /**
     * 分页查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/selectPage")
    public Result selectPage(Employee employee,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize){
        PageInfo<Employee> pageInfo = employeeService.selectPage(employee,pageNum,pageSize);
        return Result.success(pageInfo);
    }

}
