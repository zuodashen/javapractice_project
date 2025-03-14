package com.example.controller;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.example.common.Result;
import com.example.entity.Employee;
import com.example.service.EmployeeService;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
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


    /**
     * 导出数据excel
     */
    @GetMapping("/export")
    public void export(HttpServletResponse response) throws IOException {
        //1.拿到所有的员工数据
        List<Employee> employeeList = employeeService.selectAll(null);
        //2.构建Excelwriter 这是hutool提供的工具类
        ExcelWriter writer = ExcelUtil.getWriter(true);
        //3.设置中文表头
        writer.addHeaderAlias("username","账号");
        writer.addHeaderAlias("name","名称");
        writer.addHeaderAlias("sex","性别");
        writer.addHeaderAlias("no","工号");
        writer.addHeaderAlias("age","年龄");
        writer.addHeaderAlias("descr","个人介绍");
        writer.addHeaderAlias("departmentName","部门");
        //默认的，未添加alias的属性也会写出，如果想只写出加了别名的字段，可以调用此方法排除
        writer.setOnlyAlias(true);
        //4. 写出数据到writer
        writer.write(employeeList,true);
        //5.设置输出到文件的名称 以及输出流的头信息
        //设置浏览器响应的格式
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("员工信息", StandardCharsets.UTF_8);
        response.setHeader("Content-Disposition","attachment;filename=" + fileName + ".xlsx");
        //6.写出到输出流并关闭writer
        ServletOutputStream os = response.getOutputStream();
        writer.flush(os);
        writer.close();
    }

        @PostMapping("import")
        public Result imp(MultipartFile file) throws Exception {
        //1.拿到输入流 构建reader
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);
        //2.读取excel里面的数据
            //addheaderalias设置别名 read的时候别写反了 和write正好相反
            reader.addHeaderAlias("账号","username");
            reader.addHeaderAlias("名称","name");
            reader.addHeaderAlias("性别","sex");
            reader.addHeaderAlias("工号","no");
            reader.addHeaderAlias("年龄","age");
            reader.addHeaderAlias("个人介绍","descr");
            reader.addHeaderAlias("部门","departmentName");
        List<Employee> employeeList = reader.readAll(Employee.class);
        //3.写入List到数据库
        for (Employee employee : employeeList){
            employeeService.add(employee);
        }
        return Result.success();
        }
}
