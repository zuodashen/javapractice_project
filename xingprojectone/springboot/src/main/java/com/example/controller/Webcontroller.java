package com.example.controller;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.example.common.Result;
import com.example.entity.Account;
import com.example.entity.Employee;
import com.example.exception.CustomException;
import com.example.service.AdminService;
import com.example.service.ArticleService;
import com.example.service.EmployeeService;
import jakarta.annotation.Resource;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
public class Webcontroller {

    @Resource
    private EmployeeService employeeService;
    @Resource
    private AdminService adminService;
    @Resource
    private ArticleService articleService;
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
       }else {
           throw new CustomException("500","角色错误非法输入");
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
    /**
     * 修改密码
     * @param
     * @return
     */
    @PutMapping("/updatePassword")
    public Result updatePassword(@RequestBody Account account) {
        if("ADMIN".equals(account.getRole())){
            adminService.updatePassword(account);
        }else if("EMP".equals(account.getRole())) {
            employeeService.updatePassword(account);
        }else {
            throw new CustomException("500","角色错误非法输入");
        }
        return Result.success();
    }

    @GetMapping("/barData")
    public Result getBarData(){
        Map<String,Object> map =new HashMap<>();
        List<Employee> employeeList = employeeService.selectAll(null);
        Set<String> departmentNameSet =employeeList.stream().map(Employee::getDepartmentName).collect(Collectors.toSet());
        map.put("department",departmentNameSet); //x轴数据
        List<Long> countList = new ArrayList<>();
        for (String departmentName : departmentNameSet) {
            // 统计这个部门下面的员工数量
            long count = employeeList.stream()
                    .filter(employee -> departmentName != null && departmentName.equals(employee.getDepartmentName()))
                    .count();
            countList.add(count);
        }
        map.put("count",countList); //y轴员工数量的数据
        return Result.success(map);
    }


    @GetMapping("/lineData")
    public Result getLineData(){
        Map<String,Object> map = new HashMap<>();
        Date date = new Date();
        DateTime start = DateUtil.offsetDay(date,-7);
        List<DateTime> dataTimeList = DateUtil.rangeToList(start,date, DateField.DAY_OF_YEAR);
        //把Datetime类型的日期转换成字符串类型的日期
        List<String> dateStrList = dataTimeList.stream().map(dateTime->DateUtil.format(dateTime,"MM月dd日"))
                .sorted(Comparator.naturalOrder()).collect(Collectors.toList());
        map.put("date",dateStrList);//x轴的数据
        List<Integer> countList = new ArrayList<>();

        for (DateTime day : dataTimeList){
            //10月22日  格式要匹配
            String dayFormat = DateUtil.formatDate(day);
            //获取当天所有的文章数量
            Integer count = articleService.selectCountByDate(dayFormat);
            countList.add(count);
        }
        map.put("count", countList); //y轴的发布文章的数量
        return Result.success(map);
    }

    @GetMapping("/pieData")
    public Result getPieData(){
        List<Map<String,Object>> list = new ArrayList<>();
        List<Employee> employeeList = employeeService.selectAll(null);
        Set<String> departmentNameSet =employeeList.stream().map(Employee::getDepartmentName).collect(Collectors.toSet());
        List<Long> countList = new ArrayList<>();
        for (String departmentName : departmentNameSet) {
            HashMap<String,Object> map = new HashMap<>();
            map.put("name",departmentName);
            // 统计这个部门下面的员工数量
            long count = employeeList.stream()
                    .filter(employee -> departmentName != null && departmentName.equals(employee.getDepartmentName()))
                    .count();
            map.put("value",count);
            list.add(map);
        }
        return Result.success(list);
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
