package com.example.service;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.example.entity.Account;
import com.example.entity.Article;
import com.example.exception.CustomException;
import com.example.mapper.ArticleMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    @Resource
    private ArticleMapper articleMapper;


    public List<Article> selectAll(Article article) { return articleMapper.selectAll(article);}
//    public List<article> selectAll(Employee employee) {
//        return employeeMapper.selectAll(employee);
//    }

//    public Employee selectById(Integer id) {
//        return employeeMapper.selectById(id);
//    }

    public Article selectById(Integer id){ return articleMapper.selectById(id);}

//    public PageInfo<Employee> selectPage(Employee employee, Integer pageNum, Integer pageSize) {
//        PageHelper.startPage(pageNum,pageSize);
//        List<Employee> lsit=employeeMapper.selectAll(employee);
//        return PageInfo.of(lsit);
//    }
    public PageInfo<Article> selectPage(Article article,Integer pageNum,Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Article>  list =articleMapper.selectAll(article);
        return PageInfo.of(list);
    }

    
    public void add(Article article) {
            article.setTime(DateUtil.now()); //获取当前最新时间的时间字符串
            articleMapper.insert(article);
    }


    public void update(Article article) {
        articleMapper. updateById(article);
    }

    public void deleteById(Integer id) {
        articleMapper.deleteById(id);
    }
    public void deleteBatch(List <Integer> ids) {
        for(Integer id:ids){
            this.deleteById(id);
        }
    }






//    public List<Article> selectList(Article article) {
//        System.out.println(article);
//        return null;
//    }


}
