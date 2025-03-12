package com.example.controller;
import com.example.common.Result;
import com.example.entity.Article;
import com.example.service.ArticleService;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Resource
    private ArticleService articleService;
    /**
     * 新增数据
     */
    @PostMapping("/add")
    public Result add(@RequestBody Article article){
        articleService.add(article);
        return Result.success();
    }

    /**
     * 更新数据
     */
    @PutMapping("/update")
        public Result update(@RequestBody Article article){
        articleService.update(article);
        return Result.success();
    }
    /**
     * 删除单个数据
     */
    @DeleteMapping("/deleteById/{id}")
    public Result deleteById(@PathVariable Integer id){
        articleService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除多行数据
     */
    @DeleteMapping("/deleteBatch")
    public Result deleteBatch(@RequestBody List<Integer> ids){
            articleService.deleteBatch(ids);
            return Result.success();
    }


    /**
     * 查询所有员工信息
     * @return
     */
    @GetMapping("/selectAll")
    public Result selectAll(Article article){
        List<Article> list = articleService.selectAll(article);
        return Result.success(list);
    }


//查询单个数据：
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id){
        Article article = articleService.selectById(id);
        return Result.success(article);
    }


//查询单个数据：
//    @GetMapping("/selectOne")
//    public Result selectOne(@RequestParam Integer id){
//        article article = articleService.selectById(id);
//        return Result.success(article);
//    }
//    @GetMapping("/selectList")
//    public Result selectList(article article){
//        List<article> list = articleService.selectList(article);
//        return Result.success(list);
//    }
    /**
     * 分页查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/selectPage")
    public Result selectPage(Article article,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize){
        PageInfo<Article> pageInfo = articleService.selectPage(article,pageNum,pageSize);
        return Result.success(pageInfo);
    }

}
