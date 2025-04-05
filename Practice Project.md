# 练手项目

# 项目1：springboot+vue

### 项目初始环境配置见java学习.md

![create vue](/Users/ruixianli/Desktop/jie/project1的proceed screen/create vue.png)

![截屏2025-02-27 14.26.42](/Users/ruixianli/Library/Application Support/typora-user-images/截屏2025-02-27 14.26.42.png)

点击绿色箭头启动

### 遇到的bug

全局搜索bug即可

### 项目瘦身

删除assets中的main和base 将components中全部删去

setup是必备属性

```vue
<script setup>
<script>
```

精简路由

精简App.vue只做为入口文件

## 基本知识点

##### 数据的双向绑定使用  **input v-model**

```vue
<div>
      <input v-model="data.a"/>
</div>

<el-input v-model="data.input" style="width: 240px"
                    placeholder="请输入内容" :prefix-icon="Search" />{{data.input}}
          <el-input style="width: 200px" :suffix-icon="Calendar"></el-input>
        <el-input type="textarea" v-model="data.descr" style="width:300px" placeholder="请输入一段描述"></el-input>
//添加 type="textarea" 变多行文本


```

**Span** 用来数据绑定      

##### **reactive示例：**

```vue
const data = reactive({
  a:123,
  b:"xingxing",
  name:"小小章",
  arr:[1,2,3],
  smallname:"小煮！",
  fruits:["苹果","香蕉","橘子"]
})
```

##### **v-if  ：**

```vue
<div style="margin-bottom: 20px">
  <span style="color: red;font-size: 20px" v-if="data.name === '小小章'">小小章</span>
  <span style="color: blue;font-size: 20px" v-if="data.name === '小小龙'">小小龙</span>
  <span style="color: green;font-size: 20px" v-else >小小过</span>
</div>
```

##### **v-for示例：**

```vue
<div>
  <select style="width: 200px">
        <option v-for="item in data.fruits">{{item}}</option>
  </select>
</div>
```

##### **v-on：**

```vue
<div style="margin-bottom: 20px">
  <button v-on:click="click">点我加好运</button>
  或者用<button @click="click">点我加好运</button>
</div>
const click = () => {
  alert("好运加1")
}
```

```vue
@click = show  点击显示数字 点击事件
<div @click ="show(it)" v-for="it in data.arr" :key="it" style="width: 300px;height: 300px;background-color: aqua;text-align: center;font-size: 30px;margin-right: 10px" >
  {{ it }}
</div>
```

##### **v-bind:**

```vue
<div>
<!--      <div :style="{'width':'100px','height':'100px','background-color':'red'}"></div>-->
          <div :style="data.box"></div>
          <div>
              <img :src="'https://www.runoob.com/wp-content/uploads/2017/01/vue.png'"alt="">
          </div>
    </div>
```

上述代码中div ： 表示绑定

##### V-model:



##### **button and click**

```vue
<div style="margin-bottom: 20px">
<!--      <button v-on:click="click">点我加好运</button>-->
      <button @click="click">点我加好运</button>
    </div>

const click = () => {
  alert("好运加1")
}
```

##### **onMounted**

就是在页面元素完全加载完成后触发，页面元素加载需要时间

```vue
onMounted(()=>{
  console.log('页面加载完成')
})
```



## ElementPlus

##### 在vue project下安装 elementplus  看截图

配置main.js

```vue
import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import '@/assets/global.css'
import zhCn from 'element-plus/es/locale/lang/zh-cn'

const app = createApp(App)
app.use(router)
app.use(ElementPlus, {
    locale: zhCn
})
app.mount('#app')
```

安装icon：npm install @element-plus/icons-vue

```vue
import * as ElementplusIconsVue from '@element-plus/icons-vue'

for (const [key, component] of Object.entries(ElementplusIconsVue)){
    app.component(key, component)
} //icons 导入
```

```vue

<div style="margin: 30px">
  <el-icon :size="20">
    <Edit />
  </el-icon>
  <span style="margin-left: 30px">
    <el-icon :size="20" color="black" style="top: 4px"><View />
    </el-icon>100
  </span>

</div>
```

在按钮中使用图标时需要单独导入图标。

##### **ElementPlus 主题色设置 （安装依赖）**

npm i sass@1.71.1 -D 

npm i unplugin-auto-import -D

npm i unplugin-element-plus -D

npm i unplugin-vue-components -D

##### 按钮居中：

```

<div text-align: center>
</div>
```

**disabled和readonly**可以只显示文本无法输入

##### **下拉框：**

```vue
<div style="margin: 20px">
  <el-select
      clearable
      v-model="data.value"
      placeholder="Select"
      size="large"
      style="width: 240px"
  >
    <el-option
        v-for="item in data.options"
        :key="item"
        :label="item"
        :value="item"
    />
  </el-select>

</div>
```

使用v-for时，key绑定的是唯一的

可以清空内容的属性：clearable

多选：multiple

Radio：单选框  单选按钮组常用于页面内容切换

```vue
<div style="margin:20px 0">
  <el-radio-group v-model="data.sex">
     <el-radio value="男">男</el-radio>
     <el-radio value="女">女</el-radio>
  </el-radio-group> <span style="margin-left: 50px">{{data.sex}}</span>
</div>
```

多选按钮,多选框绑定的是数组,数组中存储的是value绑定的值

图片渲染/旋转/放大/缩小 el-image   使用网络图片地址是可以渲染图片的，但是使用本地的图片路径是无法渲然    想要实现的话需要import 导入进来



##### **轮播图：（引入静态图片）**

```vue
import lun1 from '@/assets/lun1.png'
import lun2 from '@/assets/lun2.png'
    <div style="margin:20px 0">
      <el-carousel height="400px" style="width: 200px" >
        <el-carousel-item v-for="item in data.imgs" :key="item">
          <img style="width:100%" :src="item" alt="">
        </el-carousel-item>
      </el-carousel>
    </div>
```

##### **日期:**

```vue
<div style="margin: 20px 0">
  <el-date-picker
    v-model="data.date"
    type="date"
    placeholder="请选择日期"
    />{{data.date}}
</div>
需要处理才适合数据库存储 使用format指定输入框的格式，使用value- format 指定绑定值的格式
format="YYYY-MM-DD"
value-format="YYYY-MM-DD"
实际使用过程中需要同时设置上述两个格式
```

##### **日期与时间：**

```vue
    <el-date-picker
         style="margin-left: 20px"
       v-model="data.date1"
       type="datetime"
       placeholder="请选择日期与时间"
       format="YYYY-MM-DD HH:mm:ss"
       value-format="YYYY-MM-DD HH:mm:ss"
       />{{data.date1}}
```

##### **数据表格：**基本使用

```vue
<div style="margin: 20px 0">
<el-table :data="data.tablebox" style="width: 100%">
  <el-table-column prop="date" label="日期" width="180" header-align="center"></el-table-column>
  <el-table-column prop="name" label="名称" width="180" header-align="center"></el-table-column>
  <el-table-column prop="address" label="地址" width="180" header-align="center"></el-table-column>
</el-table>
</div>
 tablebox:[{id:1,date:'2022-01-01',name:'张三',address:'北京'},{id:2,date:'2022-01-02',name:'李四',address:'上海'}]

当一页中数据过多时用分页器：

```

##### **分页组件：**

```vue
<div style="padding: 10px 0">
    <el-pagination
      v-model:current-page="data.currentPage"
      v-model:page-size="data.pageSize"
      :page-size="[5,10,15,20]"
      background
      layout="total, sizes, prev, pager, next, jumper"
      :tatal="data.tablebox.length"
    />
</div>
按住alt向下拉 可以修改一片
<script setup>
data.tablebox=data.tablebox.splice(0,5)
</script>
stripe:斑马纹
barder：边框属性
插槽：
<el-table-column label="操作">
  <template #default="scope">
    <el-button
        size="small"
        type="danger"
        circle
        @click="del(scope.row.id)"
    >
      <el-icon><Delete /></el-icon>
    </el-button>
  </template>
</el-table-column>

 <template #default="scope"> 可以拿到行对象数据
```

##### **Dialog 弹窗**

dialog主要作用：新增或编辑数据

```vue
<el-dialog v-model="data.dialogVisible" title="Shipping address" width="800">
  dialogvisiable 用来控制弹窗显示
  
  <el-button
                size="small"
                type="primary"
                circle
                @click="edit(scope.row)"
            > 
    
  <el-dialog v-model="data.dialogVisible" title="编辑行对象" width="800">
      <div style="padding: 20px">
        <div style="margin-bottom: 10px">日期：{{data.row.date}}</div>
        <div style="margin-bottom: 10px">名称：{{data.row.name}}</div>
        <div>地址：{{data.row.address}}</div>
      </div>
    </el-dialog>
  
  const edit=(row)=>{
  data.row=row
  data.dialogVisible=true
}
总体流程是：点击按钮的时候触发edit函数，在edit函数中使dialogvisible变为true，让弹窗展现出来，edit中让行对象数据row赋给全局变量，最后在弹窗中展示
```

## Router实现路由跳转

**vue router**：客户端路由的作用是在单页应用中将浏览器的URL和用户看到的内容绑定起来，用户在应用中浏览不同页面的时候URL会随之更新

##### **定义新路由：**注意name不能重复

```vue
在index.js中
{
  path: '/test',
  name: 'test', // 修改为唯一的名称
  component: () => import('../views/Test.vue')
}
再在Home.vue中
<div style="margin-bottom: 20px">
      <RouterLink to="/test">跳转到Test.vue</RouterLink>
  </div>
button @click  push实现跳转 可以返回
<div>
      <el-button type="primary" @click="router.push()">跳转到新的页面</el-button>
    </div>
replace 实现跳转 无法返回 

重定向方式跳转路由：
{
      path: '/',
      redirect: '/home'
    },
```

##### **路由传参：**

我们使用的都是query类型的参数

传参：@click="router.push('/test?id=1 '）

```vue
<div style="margin-bottom: 20px">
      <el-button type="primary" @click="router.push('/Test?id=1&name=xingxing')">路由传参id=2和name=xingxing</el-button>
    </div>

<div style="margin-bottom: 20px">
  <el-button type="primary" @click="router.push({path:'/test',query:{id:2,name:'xingxing'}})">路由传参id=2和name=xingxing</el-button>
</div>
```

获取参数：在data中定义  id:router.currentRoute.value.query.id  获取到query对象

##### **嵌套路由：**

<Routerview>帮忙来渲染子路由   在父级页面通过<Routerview>来渲染出子页面

新建一个manager 然后在它里面嵌套Home和test页面

```vue
routes: [
    {
      path: '/manager',component: () => import('../views/Manager.vue'),
      children:[{
        path: 'home',
        name: 'home',
        component: () => import('../views/Home.vue')
      },
        {
          path: 'test',
          name: 'test', // 修改为唯一的名称
          component: () => import('../views/Test.vue')
        },
      ]
    },
```

```vue
<div style="background-color: #8c939d">
    这是一个父级页面，可以嵌套其他的子路由页面

  <div style="width: 50%;margin:30px auto;padding:20px;border: 1px solid #cccccc;background-color: white">
    <RouterView/>
  </div>

</div>
```

**App.vue就是最外层的父级页面**

在嵌套路由里面 子路由不能使用/ 会自动添加

##### 路由守卫：

Meta:补充路由参数的对象.  改变网页的标题

```vue
//beforeEach 表示跳转之前的一些操作
router.beforeEach((to,from,next) => {
  document.title=to.meta.title
	next() //不能少了next（）
})
//to：即将进入的目标路由对象。
// from：当前导航正要离开的路由对象。
// next：一个函数，用于继续导航。必须调用 next 函数来解析这个钩子。如果不调用 next，则导航将被中断。
routes:[
 {
      path: '/manager',component: () => import('../views/Manager.vue'),
      children:[{
        path: 'home',
        name: 'home',
        meta:{title:'首页'},  //meta用处
        component: () => import('../views/Home.vue')
      },]
```

##### 404页面

通过router来设置一个404页面

定义404路由

404.vue

```vue
<template>

  <div style="text-align: center">
    <img src="@/assets/404.png" alt="">
  </div>

  <div style="text-align: center">
    <el-button  type="primary" style="width: 400px;
    height: 60px;font-size: 20px"@click="goHome">返回主页</el-button>
  </div>
</template>

<script setup>

const goHome = () =>{
  location.href='/manager/home'
}

import router from "@/router/index.js";
</script>
```

```vue
在index.js中
{path:'/404',name:'NotFound',meta:{title:'404找不到页面 '},component:()=>import('../views/404.vue')}
```

路由输入错误的时候统一到达404页面：

```
在index.js中添加
{path:'/:pathMatch(.*)', redirect:'/404'}
```

## 搭建后台管理系统：![后台](/Users/ruixianli/Desktop/jie/project1的proceed screen/后台.png)

#### 大体框架：

**在父级vue中建立大体框架：**

```vue
<template>
  <div>
    <!-- 头部开始 -->
   <div style="height: 60px;background-color: #3c7fff;display: flex;align-items: center">
        <div style="width: 200px;display: flex; align-items: center;font-size: 20px;color: white" >
          <img style="width: 40px" src="@/assets/花.png" alt="">
          <span style="font-size: 20px;color: white">后台管理系统</span>
  </div>
        <div style="flex: 1"></div>
        <div style="width: fit-content;display: flex;align-items: center;padding-right: 10px">
          <img src="@/assets/头像.png" alt="" style="width: 40px;height: 40px">
          <span style="color: white;margin-left: 5px">小小章</span>
        </div>
    </div>
    <!-- 头部结束 -->

    <!-- 左侧导航菜单开始 -->
    <div style="display: flex">
      <div style="width: 200px;border-right: 1px solid #ddd;min-height:calc(100vh - 60px)">
        <el-menu router :default-active="router.currentRoute.value.path" style="border: 0">
          <el-menu-item index="/manager/home">
            <el-icon><House /></el-icon>
            系统首页
          </el-menu-item>

          <el-menu-item index="/manager/data">
            <el-icon><DataAnalysis/></el-icon>
            数据统计
          </el-menu-item>

          <el-sub-menu index="1">
            <template #title>
              <el-icon><User /></el-icon>
              <span>用户管理</span>
            </template>
            <el-menu-item>管理员信息</el-menu-item>
            <el-menu-item>普通用户信息</el-menu-item>
          </el-sub-menu>

        </el-menu>
      </div>
      <!-- 左侧导航菜单结束 -->
      <!-- 右侧主体区域开始 -->
      <div style="flex: 1;width: 0;background-color: #f5f7ff;padding: 10px">
      <RouterView/>                    //用来将子级路由整合过来
      </div>
      <!-- 右侧主体区域结束 -->
    </div>
    <!-- 下面部分结束 -->

  </div>

</template>


<script setup>

import {DataAnalysis, House, User} from "@element-plus/icons-vue";
import router from "@/router/index.js";
</script>
```



**主体区域配置一个card卡片样式：代码放在global.css**

```vue
.card {
    background-color: white;
    padding:10px;
    border-radius:5px;
    box-shadow: 0 0 8px rgba(0,0,0,.12);
}
```

**点击menu里面的某一项跳转页面:**

![router](/Users/ruixianli/Desktop/jie/project1的proceed screen/router.png)

```vue
<el-menu router :default-active="router.currentRoute.value.path" style="border: 0">
先router 再在item中设置index
<el-menu-item index="/manager/home">系统首页</el-menu-item>
  
记得要：import router from "@/router/index.js";
```

如何做高亮显示当前展示的路由： router ：default-active="router.currentRoute.value.path



## Mysql

navicat链接数据库

字符集 utf8mb4

排序规则：unicode-ci

**学会使用navicat**

创建数据库

创建表

数据库主键（选中自增标识） 设置注释

#### 基本增删改查

```mysql
SELECT * FROM employee; //查询所有
SELECT * FROM employee WHERE NAME ="星星";//单个查询
SELECT * FROM employee WHERE id=1;//根据唯一条件进行查询
SELECT * FROM employee WHERE name="星星"and age=21;SELECT * FROM employee WHERE name LIKE '%小%'//模糊查询  分为前缀 后缀 和 全模糊
SELECT * FROM employee LIMIT 0,2 //limit 可以分页 第一个参数诗分页的其始索引，第二个参数是每个显示多少个
SELECT * FROM employee ORDER BY id DESC  //通过id 倒序查询
SELECT COUNT(*) FROM employee GROUP BY sex HAVING sex='男'  //（count*）统计有多少个男生 分组查询 group by 和 having 组合使用     
sum（）用来计算总和 
SELECT employee.*,department.name as departmentName FROM employee 
LEFT JOIN department 
ON employee.department_id = department.id   //连接查询 关联查询 

```

```mysql
INSERT INTO 
`employee` (`name`,`sex`,`no`,`age`,`descr`,`department_id`)
VALUES
(	'星星', '男', '101', 22, '星星是一个努力学习的打工人，坚持好好学习', 1 );

UPDATE `employee`
SET `name`='星星',
`sex`= '男',
`no`='101',
`age` = 32,
`descr`='多多运动锻炼身体',
`department_id` =1
WHERE
`id`=1

DELETE FROM employee WHERE id=5;
TRUNCATE employee; //可以删除所有数据
```

 设置唯一索引 在navicat中 点击索引，选中unique

## Springboot3

##### 新建springboot3

：new project  

![create springboot](/Users/ruixianli/Desktop/jie/project1的proceed screen/create springboot.png)

选低版本，选web->springweb       Sql-> mysqldriver     sql—>mybatis framework	

删除 .idea   .mvn  .gitgnore   help.md.   mvnw   mvnw.cmd

删除test文件  删除resources中的两个无用文件

application.properties ->改为 application.yml

删除pom.xml中没用的依赖

修改包名的时候记得对应修改 pom.xml中的group id

##### 配置springboot(：

![设置编码](/Users/ruixianli/Desktop/jie/project1的proceed screen/设置编码.png)在设置（preference)中，找到encoding 进行相应**maven设置**    

maven->conf->settings.xml中配置阿里云镜像

![maven配置](/Users/ruixianli/Desktop/jie/project1的proceed screen/maven配置.png)

在maven 中新建repo文件，在local repository 中选中，之后将架包会下载在里面

在 pom.xml中右键 add as maven project   右下角开始下载maven依赖  （如果还是爆红色 就重启一下项目）

**启动springboot工程** ：在springboot Application中右键 run.    如果有问题就在 edit configuration 中把包的路径修改正确

启动时会打印一些参数

**显示启动失败，是因为没有配置数据库！**

**关于修改mysql密码：**在navicat  中右键数据库，选择console 然后输入ALTER USER'root'@'localhost' IDENTIFIED BY 'password' PASSWORD EXPIRE NEVER;   再回车即可  password即位要修改的密码

**Application.yml中添加：**

```java
server:
  9090
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    username: root
    password: 12345678
    url: jdbc:mysql://localhost:3306/xm-pro?useUnicode=true&characterEncoding=utf-8&allowWMultiQuries=true&useSSL=false&serverTimezone=GMT%2b8&allowPublicKeyRetrieval=true


```

##### 写一个测试接口：

新建controller包，新建webcontroller类

```java
package com.example.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class Webcontroller {
    @GetMapping("/hello")
    public String hello(){
        return "Hello World";
    }
}
//对外提供数据也就是写接口
//@RestController 用于定义一个 RESTful 控制器类。
//@GetMapping("/hello") 用于将 HTTP GET 请求映射到指定的方法上，处理特定路径的请求。
```

##### 统一返回包装类：

新建common包，建result类.   包装类的作用是统一后端返回的数据类型

在mac中  command +N 统一创建get和set方法

```java
package com.example.common;

/**
 * 这是一个统一返回的包装类
 */
public class Result {
    private String code;
    private String msg;
    private Object data;

    public static Result success(){
        Result result = new Result();
        result.setCode("200");
        result.setMsg("get success!");
        return result;
    }

    public static Result success (Object data){
        Result result = success();
        result.setData(data);
        return result;
    }
    public static Result error(){
        Result result = new Result();
        result.setCode("500");
        result.setMsg("get failure!");
        return result;
    }
   public static Result error(String code,String msg){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    } //自定义异常中的方法

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }
}

webcontroller.java: 
@GetMapping("/map")
    public Result map(){
        HashMap<String,Object> map = new HashMap<>();
        map.put("name","星星");
        map.put("age",18);
        return Result.success(map);
    }
```

##### 全局异常处理：

新建exception包，建GlobalExceptionHandler 类  用于全局异常处理 

```java
package com.example.exception;

import com.example.common.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * 全局异常处理
 */
@ControllerAdvice("com.example.controller")
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody //返回json格式数据
    public Result error(Exception e){
        e.printStackTrace();
        return Result.error();
    }
}
//@ControllerAdvice 是 Spring MVC 中的一个注解，用于定义全局异常处理器
//@ExceptionHandler 是 Spring MVC 中的一个注解，用于指定该方法可以处理特定类型的异常
//@ResponseBody 注解表示该方法的返回值将直接写入 HTTP 响应体中，而不是解析为视图。
```

**Java 开发 = 改错！**

##### 自定义异常：

新建customException.java

```java
package com.example.exception;
public class CustomException extends RuntimeException{
    private String code;
    private String msg;
    public CustomException(String code,String msg){
        this.code = code;
        this.msg = msg;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
}
```

并在globalException.java中新增下列代码：用于捕获

```java
@ExceptionHandler(CustomException.class)
@ResponseBody //返回json格式数据
public Result error(CustomException e){
    e.printStackTrace();//打印出异常信息  如果不是系统报错就去除这行代码
    return Result.error(e.getCode(),e.getMsg());
}
```

在Result.java中加入：

```java
public static Result error(String code,String msg){
    Result result = new Result();
    result.setCode(code);
    result.setMsg(msg);
    return result;
}
```

webcontroller.java中将Result count中改为

```java
throw new CustomException("500","错误！禁止请求！");
```

## Springboot集成Mybatis

```java
在application.yml中
#配置mybatis实体和xml映射
mybatis:
  ##映射xml
  mapper-locations: classpath:mapper/*.xml 
                                    //calsspath 相当于映射到resource下的mapper包下的xml文件
  configuration:
    #配置日志
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
    map-underscore-to-camel-case: true      //通过转换mabatis实现java驼峰 
```

基本xml格式:

xml是跟mapper接口 一一对应的

在resources下创建EmployeeMapper.xml

```java
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.example.mapper.EmployeeMapper">    //namepace为mapper包的路径
</mapper>
```

需要告诉springboot如何扫描mapper包.  在springbootapplication中添加：

```java 
@MapperScan("com.example.mapper")
```

建立mapper包和service包  分别创建EmployeeMapper.interface    和  EmployeeService.class

在service的EmployeeService创建 @service标注为springboot里面的一个Bean

新建EmployeeController.java:

```java
package com.example.controller;

import com.example.common.Result;
import com.example.entity.Employee;
import com.example.service.EmployeeService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Resource
    private EmployeeService employeeService;


    /**
     * 查询所有员工信息
     * @return
     */
    @GetMapping("selectAll")
    public Result selectAll(){
        List<Employee> list = employeeService.selectAll();
        return Result.success(list);
    }
}
/employee/selectAll 组合为完整路径  也就是接口的路径
```

在EmployeeService中补全selectAll（）方法

```java
package com.example.service;


import com.example.entity.Employee;
import com.example.mapper.EmployeeMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Resource
    private EmployeeMapper employeeMapper;
    public List<Employee> selectAll() {
        return employeeMapper.selectAll();
    }

}
```

在EmployeeMapper中补全selectAll（）方法

```java
package com.example.mapper;
import com.example.entity.Employee;
import java.util.List;
public interface EmployeeMapper {
    List<Employee> selectAll();
}
```

在EmployeeMapper.xml中写sql语句

```vue
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.example.mapper.EmployeeMapper">

    <select id="selectAll" resultType="com.example.entity.Employee">
        select * from employee
    </select>

</mapper>
```

**可以下载插件mybatisx**



新建employee的实体类:

```java
public class Employee {
    private Integer id;
    private String name;
    private String sex;
    private String no;
    private Integer age;
    private String descr;
    private Integer departmentId;
		
  	command+N 写出get和set方法
}
```

Mapper和Mapper.xml的对应关系![mapper和mapper.xml对应](/Users/ruixianli/Desktop/jie/project1的proceed screen/mapper和mapper.xml对应.png)

##### 整体流程：

1、先写实体类entity->employee 和数据库字段对应上

2、写Controller，EmployeeController.java         接口路径：/employee/selectAll

3、Controller从service拿数据，写service

4、从service层再到mapper  在此来帮忙查询数据

5、最后在EmployeeMapper.xml中写sql查数据    id要和mapper中对应上

完整的接口请求路径：http://localhost:9090/employee/selectAll    http://(ip):(port)/path

通过接口向后端服务发送请求，服务从数据库中将数据捞出转换成java对象，接口把java对象转为json

##### 数据请求流程：

**前端浏览器->Springboot服务器->Mybatis->Mysql->java对象->Json对象->浏览器**

**运行时报错：**通过debug发现，数据连接失败，是因为在application.xml数据库配置中 出现低级失误



##### 注解语法实现sql查询

简单的sql可以通过这种方法来写,关联查询还是在xml中写

**通过路径传递参数(可以传递多参数)**：@PathVariable

①在EmployeeController中写：

```java
@GetMapping("/selectById/{id}")
public Result selectById(@PathVariable Integer id){
    Employee employee = employeeService.selectById(id);
    return Result.success(employee);
}           //PathVariable是传递路径参数
```

②在EmployeeService中写

```java
public Employee selectById(Integer id) {
    return employeeMapper.selectById(id);
}
```

③在EmployeeMapper中写

```java
@Select("select * from employee where id = #{id}")
Employee selectById(Integer id);

//@Select 通过注解的方法实现sql查询
```

 **通过URL传递参数(可以传递多参数)：**@RequestParam  用的方法和路径传参一样但是 传递参数不同

http://localhost:9090/employee/selectOne?id=1&n o=101

```java
@GetMapping("/selectOne")
public Result selectOne(@RequestParam Integer id,@RequestParam(required = false) Integer no){     //required=false 表示参数不一定要写
    Employee employee = employeeService.selectById(id);
    return Result.success(employee);
}
```

**对象参数**![对象参数](/Users/ruixianli/Desktop/jie/project1的proceed screen/对象参数.png)

##### 查询方法：

上面两个方法为查询所有和单个查询

**分页查询：**

引入pagehelper这个插件 在pom.xml中添加依赖

```java
<dependency>
    <groupId>com.github.pagehelper</groupId>
    <artifactId>pagehelper-spring-boot-starter</artifactId>
    <version>1.4.6</version>
    <exclusions>
       <exclusion>
          <groupId>org.mybatis</groupId>
          <artifactId>mybatis</artifactId>
       </exclusion>
    </exclusions>
</dependency>
```

在controller中写： 分页接口

```java
    /**
     * 分页查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/selectPage")
    public Result selectPage(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize){
        PageInfo<Employee> pageInfo = employeeService.selectPage(pageNum,pageSize);
        return Result.success(pageInfo);
    }
```

在service里面通过三行代码实现分页查询

```java
public PageInfo<Employee> selectPage(Integer pageNum, Integer pageSize) {
    PageHelper.startPage(pageNum,pageSize);
    List<Employee> lsit=employeeMapper.selectAll();
    return PageInfo.of(lsit);
}
```

##### 使用Mybatis实现数据库的增删改

**get：查询操作                  mybatis里面写sql用下划线，涉及到绑定java对象值写驼峰**

**post：新增操作**

**put：修改操作**

**delete：删除操作**

 **@RequestBody :**可以把前端传来的**json**字符串映射成java的对象或者数组

###### 新增数据：

在Employee Controller中写：

```java
/**
 * 新增数据
 */
@PostMapping("/add")
public Result add(@RequestBody Employee employee){
    employeeService.add(employee);
    return Result.success();
}
```

Employee Service中写：

```java
public void add(Employee employee) {
        employeeMapper.insert(employee);
}
```

EmployeeMapper中写：

```java
void insert(Employee employee);
```

EmplooyeeMapper.xml中写 （新增sql语法）

```sql
<insert id="insert" parameterType="com.example.entity.Employee">
    insert into `employee` (name,sex,no,age,descr,department_id)
    values (#{name},#{sex},#{no},#{age},#{descr},#{departmentId})
</insert>
```

###### 更新数据：

记得使用@PutMapping

在Employee Controller中写：

```java
@PutMapping("/update")
        public Result upadte(@RequestBody Employee employee){
        employeeService.update(employee);
        return Result.success();
    }
```

Employee Service中写：

```java
public void update(Employee employee) {
    employeeMapper.updateById(employee);
}
```

EmployeeMapper中写：

```java
void updateById(Employee employee);
```

EmplooyeeMapper.xml中写 （更新sql语法）:

```sql
<update id="updateById" parameterType="com.example.entity.Employee">
    update `employee` set name=#{name},sex=#{sex},no=#{no},age=#{age},descr=#{descr},department_id=#{departmentId}
    where id=#{id}
</update>
```

###### 删除数据：

使用@DeleteMapping

在Employee Controller中写：

```java
@DeleteMapping("/deleteById/{id}")
public Result deleteById(@PathVariable Integer id){
    employeeService.deleteById(id);
    return Result.success();
}
```

Employee Service中写：

```java
public void deleteById(Integer id) {
    employeeMapper.deleteById(id);
}
```

EmployeeMapper中写：

```java
@Delete("delete from `employee` where id = #{id}")
void deleteById(Integer id);  //注解实现sql删除
```

##### 使用postman测试接口

发送post请求      下载postman

![postman测试](/Users/ruixianli/Desktop/jie/project1的proceed screen/postman测试.png)

**学会通过断点来排查问题**

## 打通前后端SpringBoot3+Vue3

##### 配置request.js

在vue工程下  安装axios封装前后端对接数据工具. npm i axios -S 

封装request.js工具类 来发送请求给后端         axios用于发起HTTP请求

在vue的src中新建utils包 新建request.js  写入：

**此bug解决了半天！：**

**在访问 `error.response.status` 之前先检查 `error.response` 是否存在**

```js
import axios from "axios";
import {ElMessage} from "element-plus";
const request= axios.create({
    baseURL: 'http://localhost:9090',
    timeout: 30000 //后台接口超时时间
})

//request 拦截器
//可以自请求发送前对请求做一些处理
request.interceptors.request.use(config => {
    config.headers['Content-Type'] = 'application/json;charset=utf-8';
    return config
},error => {
    return Promise.reject(error)
});
//response 拦截器
//可以在接口响应后对返回的数据 统一处理结果
request.interceptors.response.use(
    response => {
        let res = response.data;
        //兼容服务端返回的字符串数据
        if(typeof res === 'string') {
            res = res ? JSON.parse(res) : res
        }
        return res;
    },
    error=>{
        if (error.response) {
            const { status } = error.response;
            if (status === 404) {
                ElMessage.error('未找到请求接口');
            } else if (status === 500) {
                ElMessage.error('系统异常，请查看后端控制台报错');
            } else {
                console.error(error.message);
            }
        } else {
            // 请求已发送，但没有收到响应或者设置请求时发生错误
            console.error('请求出错:', error.message);
        }
        return Promise.reject(error);
    }
);

export default request
```

###### 简单的请求示例：

在home.vue   setup之后

```vue
import request from "@/utils/request.js";
```

在data中定义一个EmployeeList[]数组

添加：

```vue
request.get('/employee/selectAll').then(res => {
  console.log(res)  //控制台打印数据
  data.emplpueeList = res.data  //就是员工的列表数据 是一个数组
})
//.then 是 Promise 的回调函数，用于处理异步请求成功后的结果。
参数 res 是后端返回的响应数据。
```

遇到了跨域错误，在Springboot里面设置统一的跨域处理

在common包中new一个新的CoresConfig类![跨域错误](/Users/ruixianli/Desktop/jie/project1的proceed screen/跨域错误.png)

设置完后重启后端 CoresConfig.java

```java
package com.example.common;


import org.springframework.web.filter.CorsFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");//设置访问源地址
        corsConfiguration.addAllowedHeader("*");//设置访问源请求头
        corsConfiguration.addAllowedMethod("*");//设置访问源请求方法
        source.registerCorsConfiguration("/**",corsConfiguration);//注册Cors配置信息
        return new CorsFilter();
    }
}

//导入的 CorsFilter 类时应该注意,不应该导入 org.apache.catalina.filters.CorsFilter，这是 Tomcat 服务器自带的过滤器，并非 Spring 框架用于处理 CORS 的过滤器。正确的应该导入 org.springframework.web.filter.CorsFilter。
```

网络请求：

![网络请求](/Users/ruixianli/Desktop/jie/project1的proceed screen/网络请求.png)

##### 正式开始：

###### 员工信息分页（分页查询）：

将后端数据送到前端employee中

新建employee.vue 将data.vue中代码 copy过来，把Manager.vue中 管理员信息下面改成员工信息 并添加

index

```vue
<el-menu-item index="/manager/employee">员工信息</el-menu-item>
```

在index.js中添加路由 employee 

```vue
{
  path: 'employee',
  name: 'employee', // 修改为唯一的名称
  meta:{title:'员工信息页面'},
  component: () => import('../views/Employee.vue')
},
```

在employee.vue中正确修改employee数据库中的对应表格属性

在data中修改，并定义

记得一定要 import request from "@/utils/request.js";

```java
const load =() =>{
  request.get('employee/selectPage',{ // ?pageNum=1&pageSize=10
    params:{
      pageNum:data.pageNum,
      pageSize:data.pageSize,
    }
  }).then(res => {
    data.tablebox = res.data.list,
       data.total=res.data.total
  })
}
//res.data.list 表示分页数据列表,将 res.data.list 赋值给 data.tablebox，从而更新表格内容。
//data.tablebox：
//data 是通过 Vue 的 reactive 方法创建的一个响应式对象。
//tablebox 是 data 对象中的一个属性，用于存储从后端获取的分页数据。
//由于 data 是响应式的，当 tablebox 的值发生变化时，页面上的表格会自动更新。
流程：request.get 方法向后端发送 GET 请求，请求路径为 employee/selectPage，并附带分页参数 pageNum 和 pageSize。后端处理请求并返回分页数据。在 .then 回调函数中，将返回的数据列表赋值给 data.tablebox。
页面上的表格组件 <el-table> 会根据 data.tablebox 的最新值重新渲染。
```

当你发起请求的时候携带了参数，你可以在载荷这里查看

然后给data中的tablebox赋值.    data.tablebox = res.data.list

当数据比较多的时候在 card的各个属性中增加  show-overflow-tooltip 例如

```vue
<el-table-column label="个人介绍" prop="descr" show-overflow-tooltip />
```

想要分页在 res = > 增加 data.total=res.data.total

在el- pagination中 需要写明 current-change 才能点击下一页时有分页. 添加如下代码在 el-pagination中

```vue
@size-change="load"
@current-change="load"
```

###### 条件查询:

在load的params中写入name：data.name

在查询按钮出增加@click触发. @click="load"

在EmployeeController的selectPage接口中增加Employee 参数,相应修改service和mapper中的参数

在mapper.xml中修改 增加判断语法 **全模糊查询**     

**动态条件查询：**

```sql
   <select id="selectAll" resultType="com.example.entity.Employee">
        select * from employee
        <where>
            <if test="name != null">name like concat ('%',#{name},'%')</if>
        </where>
        order by id desc
    </select>
```

**重置**：很easy      先添加属性 @click = "reset"

```vue
const reset =() => {
  data.name=null
  load()
}
```

###### **条件查询流程：**

**在employee.vue中的load中发送请求，把参数从网络请求中带过去，在controller中接收参数，再一次传到service和mapper中，mapper中进入到sql，再进行判断。最后查询的结果返回到controller中，前端再去接收**

###### 新增数据：

1⃣️在employee.vue中加入新增dialog 弹窗组件并设置表单

```vue
<el-dialog title="员工信息" v-model="data.formVisible" width="500">
  <el-form :model="data.form" style="padding-right: 40px;padding-top: 20px "label-width="80px">
    <el-form-item label="名称"><el-input v-model="data.form.name" autocomplete="off" placeholder="请输入名称"/></el-form-item>
    <el-form-item label="性别">
      <el-radio-group v-model="data.form.sex">
        <el-radio value="男" label="男"></el-radio>
        <el-radio value="女" label="女"></el-radio>
      </el-radio-group>
    </el-form-item>
    <el-form-item label="工号" label-width="80px"><el-input v-model="data.form.no" autocomplete="off" placeholder="请输入工号"/></el-form-item>
    <el-form-item label="年龄" label-width="80px"><el-input-number style="width: 160px" min="18" v-model="data.form.age" autocomplete="off" placeholder="请输入年龄"/></el-form-item>
    <el-form-item label="个人介绍" label-width="80px"><el-input rows=3 type="textarea" v-model="data.form.descr" autocomplete="off" placeholder="请输入个人介绍"/></el-form-item>
  </el-form>
  <template #footer>
    <div class="dialog-footer">
      <el-button @click="data.formVisible = false">取 消</el-button>
      <el-button type="primary" @click="save">保 存</el-button>
    </div>
  </template>
</el-dialog>
```

在新 增处增加@click   

```vue
<el-button type="primary" @click="handleAdd">新 增</el-button>
```

定义函数：

```vue
const handleAdd =() =>{
  data.formVisible=true
  data.form={}
}
data中增加新属性：formVisible:false  form={}     
```

2⃣️保存按钮发起请求

```vue
const save = () => {
   request.post('employee/add',data.form).then(res =>{
     if(res.code === '200'){
       data.formVisible=false
       ElMessage.success('操作成功')
       load()  //新增后一定要重新加载最新的数据
     }else {
       ElMessage.error(res.msg)
     }
   })
}
```

3⃣️编辑按钮在 1⃣️的eltable中添加 (编辑数据)

```vue
<el-table-column label="操作" width="120px">
  <template #default="scope">
    <el-button @click="handleUpdate（scope.row）" type="primary" :icon="Edit" circle></el-button>
    <el-button  type="danger" :icon="Delete" circle></el-button>
  </template>
  </el-table-column>
!!!!!!把数据带过去需要 handleUpdate（scpoe.row）
```

**这里debug了半天** 发现没有在handleUpdate里面写参数 把行数据带过去

定义handle Update方法：

```vue
const handleUpdate =(row) =>{
  data.form = JSON.parse(JSON.stringify(row)) //深拷贝一个新的对象，用于编辑这样就不会影响行对象
  data.formVisible=true    
}
```

（**！！在一个save方法中要做两个报错操作，一个是新增一个是编辑**）

这时候修改save方法，分别定义add 和update  代码如下：

```vue
<script setup>
const handleAdd =() =>{
  data.formVisible=true
  data.form={}
}
const save = () => {  //在一个报错方法里面做2个操作，一个是新增，一个是编辑
  data.form.id ? update() : add()
}

const add =() =>{
  request.post('employee/add',data.form).then(res =>{      //新增的里面没有id
    if(res.code === '200'){
      data.formVisible=false
      ElMessage.success('操作成功')
      load()  //新增后一定要重新加载最新的数据
    }else {
      ElMessage.error(res.msg)
    }
  })
}

const update =() =>{
  request.put('employee/update',data.form).then(res =>{   //编辑的对象里面包含id
    if(res.code === '200'){
      data.formVisible=false
      ElMessage.success('操作成功')
      load()  //新增后一定要重新加载最新的数据
    }else {
      ElMessage.error(res.msg)
    }
  })
</script>
```

效果展示：

![效果展示](/Users/ruixianli/Desktop/jie/project1的proceed screen/效果展示.png)

![页面检查网络部分](/Users/ruixianli/Desktop/jie/project1的proceed screen/页面检查网络部分.png)

**编辑流程：1·打开弹窗2·设置弹窗数据 3·调用更新的接口4·重新加载表格数据**



**整体流程：**1⃣️设置弹窗组件dialog 通过handleAdd打开弹窗2⃣️点击保存按钮发起请求3⃣️编辑按钮（在dialog中添加）（**！！在一个save方法中要做两个报错操作，一个是新增一个是编辑**）

**JSON.parse：将 JSON 字符串解析为 JavaScript 对象。**
**JSON.stringify：将 JavaScript 对象或数组转换为 JSON 字符串。**
**localStorage：用于在浏览器中持久化存储数据，值必须是字符串。**



###### 删除数据：

每次const完方法之后，记得在按钮中用click进行绑定

**单个删除：**

```vue
const del = (id) =>{
  request.delete('employee/deleteById/' + id).then(res => {
    if (res.code === '200'){
      ElMessage.success('操作成功')
      load() //删除数据后一定要重新加载最新数据
    }else {
      ElMessage.error(res.msg)
    }
  })
}
```

在button中绑定

```vue
<el-button @click="del(scope.row.id)" type="danger" :icon="Delete" circle></el-button>
```

增加一个二次确认删除的功能，因此将del修改为：

```vue
<script setup>
const del = (id) => {
  ElMessageBox.confirm('删除数据后无法恢复，您确认删除吗？', '删除确认', {type: 'warning'}).then(() => {
    request.delete('employee/deleteById/' + id).then(res => {
      if (res.code === '200') {
        ElMessage.success('操作成功')
        load() //删除数据后一定要重新加载最新数据
      } else {
        ElMessage.error(res.msg)
      }
    })
  }).catch()
}
</script>
```

**批量删除：**

在table中添加

```vue
<el-table :data="data.tablebox" stripe @selection-change = "handleSelectChange">
  <el-table-column type="selection" width="55"/>  //想实现批量删除，必须写这两行
```

```vue
const handleSelectChange = (rows) => {  //返回所有选中的行对象数组
  //从选中的行数组里面取出所有行的id组成一个新的数组
  data.ids = rows.map(row => row.id)
  console.log(data.ids)
}
```

在后端中添加相应批量删除数据接口：

employeeController:中写

```java
@DeleteMapping('/deleteBatch')
public Result deleteBatch(@RequestBody List<Integer> ids){
       employeeService.deleteBatch(ids);
       return Result.success();
}
//后端接口必须使用 @RequestBody List<Integer> ids 接收数组
```

employeeService中写

```java
public void deleteBatch(List <Integer> ids) {
    for(Integer id:ids){
        this.deleteById(id);
    }
}
```

在前端中实现删除方法：

先定义：

```vue
<script setup>
const delBatch = () => {
  if (data.ids.length === 0){
    ElMessage.warning('请选择要删除的数据')
    return
  }
  ElMessageBox.confirm('删除数据后无法恢复，您确认删除吗？', '删除确认', {type: 'warning'}).then(() => {
    request.delete('employee/deleteBatch', {data: data.ids}).then(res => {
      if (res.code === '200') {
        ElMessage.success('操作成功')
        load() //删除数据后一定要重新加载最新数据
      } else {
        ElMessage.error(res.msg)
      }
    })
  }).catch()
}
</script>
```

```vue
<el-button type="danger" @click="delBatch(data.ids)">批量删除</el-button>
```

## 登陆注册页面开发：

在employee表中加三个字段

![增加三个字段](/Users/ruixianli/Desktop/jie/project1的proceed screen/增加三个字段.png)

在相应的entity中增加相应字段,和相应的get  set方法  （command + N）

```java
private String username;
private String password;
private  String role;
```

修改相应的 Employeemapper.xml

```sql
<insert id="insert" parameterType="com.example.entity.Employee">
    insert into `employee` (username,password,role,name,sex,no,age,descr,department_id)
    values (#{username},#{password},#{role},#{name},#{sex},#{no},#{age},#{descr},#{departmentId})
</insert>

<update id="updateById" parameterType="com.example.entity.Employee">
    update `employee` set username=#{username},password=#{password},role=#{role},name=#{name},sex=#{sex},no=#{no},age=#{age},descr=#{descr},department_id=#{departmentId}
    where id=#{id}
</update>
```

对应的employee.vue中添加

```vue
<el-table-column label="账号" prop="username"/>
<el-table-column label="角色" prop="role"/>
```

把新增的逻辑在vue中也补上

```vue
<el-form-item label="账号"><el-input v-model="data.form.username" autocomplete="off" placeholder="请输入账号"/></el-form-item>
```

**表单校验逻辑：**

1⃣️在elform增加 ref=“formRef”2⃣️在script中引入ref    并且在data下面定义好 constref

3⃣️在save中写相关逻辑. 代码如下:  如果不填账号或者名称会给提示

```vue
  <el-form ref="formRef" :rules="data.rules" :model="data.form" style="padding-right: 40px;padding-top: 20px "label-width="80px">
    在data中定义ruels
  rules:{
    username:[
      {required:true,message:'请输入账号',trigger:'blur'},
    ],
    name:[
      {required:true,message:'请输入名称',trigger:'blur'},
    ]
  }
})
```

```vue
const formRef = ref()
```

```vue
const save = () => {  //在一个报错方法里面做2个操作，一个是新增，一个是编辑
  formRef.value.validate((valid)  => {
    if (valid){
      data.form.id ? update() : add()
    }
  })
}
```

注意在el-form-item 账号中 增加prop=“username”

```vue
<el-form-item label="账号" prop="username"><el-input v-model="data.form.username" autocomplete="off" placeholder="请输入账号"/></el-form-item>
//prop 是 <el-form-item> 组件的一个属性，用于指定该表单项对应的字段名。
//这个字段名必须与 rules 中定义的校验规则中的字段名一致，以便在表单校验时能够正确关联到相应的规则。
//当使用 formRef.value.validate 方法进行表单校验时，框架会根据 prop 属性来查找对应的校验规则，并对相应的表单项进行校验。
```

在dialog弹窗中加入destroy-on-close 当弹窗关闭的时候会销毁掉本次内容

```vue
<el-dialog title="员工信息" v-model="data.formVisible" width="500" destroy-on-close>
```

#### 创建登录页面：

login.vue 添加路由时，记得放在manager的外面

```vue
{path:'/login',name:'Login', meta:{title: '登录系统'},component: () =>import("../views/Login.vue")},
//path后的路径一定要先加/符号
```

退出登录：

```vue
<el-menu-item index="/login">
  <el-icon><switch-button /></el-icon>
  退出登录
</el-menu-item>
```

**登录界面：**

```vue
<template>
  <div class="login-container">
<div class="login-box">
  <div style="padding: 30px;background-color: white;margin-left: 100px;border-radius: 30px;box-shadow: 0 0 10px rgba(0,0,0,0.2)">
    <el-form ref="formRef" :rules="data.rules" :model="data.form" style="width: 366px">
      <div style="margin-bottom: 30px; font-size: 24px;text-align: center;color: blue;font-weight: bold" >欢迎登录后台管理系统</div>
      <el-form-item prop="username">
        <el-input size="large" v-model="data.form.username" placeholder="请输入账号" prefix-icon="User"></el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input show-password size="large" v-model="data.form.password" placeholder="请输入密码" prefix-icon="Lock"></el-input>
      </el-form-item>
      <div style="margin-bottom: 20px">
        <el-button @click="login" size="large" style="width: 80%;margin-left: 40px" type="primary">登录</el-button>
      </div>
      <div style="text-align: right">还没有账号？<a style="color: #0742b1" href="/register">注册</a></div>
    </el-form>
  </div>
</div>
  </div>

</template>

<script setup>
import {reactive,ref} from "vue";
import {User,Lock} from "@element-plus/icons-vue"
import request from "@/utils/request.js";
import {ElMessage} from "element-plus";
const data = reactive({
  form:{},
  rules:{
    username: [
      {required:true,message:'请输入账号',trigger:'blur'}
    ],
    password:[
      {required:true,message:'请输入密码',trigger:'blur'}
    ]
  }
})
const formRef = ref()
const login = () => {
  formRef.value.validate((valid) => {
    if (valid) {
      request.post('/login', data.form).then(res => {
        console.log("登录返回后的数据：", res);
        if (res.code === '200') { // 登录成功
          // 存储后台返回的用户的信息
          localStorage.setItem('xingprojectone-user', JSON.stringify(res.data)); // 把json对象转成字符串
          console.log("打印", res);
          ElMessage.success('登录成功');
          setTimeout(() => {
            location.href = '/manager/home';
          }, 100);
        } else {
          ElMessage.error(res.msg);
        }
      }).catch(error => {
        console.error('请求出错:', error); // 打印错误信息
        ElMessage.error('登录请求出错，请稍后重试');
      });
    }
  });
};

</script>

<style scoped>
.login-container{
  height: 100vh;
  overflow: hidden;
  background-image: url("@/assets/bg.png");
  background-size: cover;
  background-position: -200px -1px;
}

.login-box{
  width: 50%;
  height: 100%;
  display: flex;
  align-items: center;
  right: 0;
  position:absolute;
}

</style>

```

登录页面的设计思路：

![登录页面](/Users/ruixianli/Desktop/jie/project1的proceed screen/登录页面.png)

#### 后端的登录接口：（登录逻辑）

先写webcontroller的登录接口：

```java
@PostMapping("/login")
public Result login(@RequestBody Employee employee){
    employeeService.login(employee);
    return Result.success();
}
```

再写service中的方法：

```java
public Employee login(Employee employee) {
    String username = employee.getUsername();
    Employee dbEmployee = employeeMapper.selectByUsername(username);
    if(dbEmployee == null ){  //用户名不存在
            throw new CustomException("500","用户名不存在");
    }
    //数据库存在该用户
    String password = employee.getPassword();
    if (dbEmployee.getPassword().equals(password)) {  //用户输入的密码和数据库密码不一致
        throw new CustomException("500","账号或者密码错误");
    }
    return dbEmployee;
}
```

补充mapper中需要用到的sql查询：

```java
@Select("select * from `employee` where username = #{username};")
Employee selectByUsername(String username);
```

通过loctation.href = '/manager/home' 跳转到主页

通过show-password这个属性隐藏密码

登录用户后我需要把，登录账号的信息带过来，并且右上角的用户会随之替换，需要存储后台返回的用户数据

在login方法中加入

```vue
localStorage.setItem('xm-pro-user',JSON.stringify(res.data)) //把json对象转成字符串
```

在manager.vue中添加：

```vue
const data = reactive({
  user: JSON.parse(localStorage.getItem("xm-user"))
});
记得import reactive 还有绑定data.user.name
```

显示TypeError: Cannot read properties of null (reading 'name')是因为异步获取数据,在页面渲染的时候数据还未从后端获取到。 Debug

啊啊啊啊啊啊啊啊啊终于解决了 **问题出在**后端的接口，后端的login应该写为：

**这里调用 employeeService.login(employee) 方法只是进行了登录验证，但没有将验证成功后的用户信息返回。正确的做法应该是将 login 方法返回的 Employee 对象作为 Result.success 的参数返回。**

```java
@PostMapping("/login")
public Result login(@RequestBody Employee employee) {
    if (employee == null) {
        return Result.error();
    }
    try {
        Employee loggedInEmployee = employeeService.login(employee);
        return Result.success(loggedInEmployee);
    } catch (CustomException e) {
        return Result.error(e.getCode(), e.getMessage());
    }
}
```

用postman测试，点击post 输入正确的url  然后写json语句

{

​    "code": "200",

​    "msg": "get success!",

​    "data": null

}

#### 写注册页面：

##### 注册前端：

```vue
<template>
  <div class="login-container">
<div class="login-box">
  <div style="padding: 30px;background-color: white;margin-left: 100px;border-radius: 30px;box-shadow: 0 0 10px rgba(0,0,0,0.2)">
    <el-form ref="formRef" :rules="data.rules" :model="data.form" style="width: 366px">
      <div style="margin-bottom: 30px; font-size: 24px;text-align: center;color: blue;font-weight: bold" >欢 迎 注 册</div>
      <el-form-item prop="username">
        <el-input size="large" v-model="data.form.username" placeholder="请输入账号" prefix-icon="User"></el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input show-password size="large" v-model="data.form.password" placeholder="请输入密码" prefix-icon="Lock"></el-input>
      </el-form-item>
      <el-form-item prop="confirmPassword">
        <el-input show-password size="large" v-model="data.form.confirmPassword" placeholder="请确认密码" prefix-icon="Lock"></el-input>
      </el-form-item>
      <div style="margin-bottom: 20px">
        <el-button @click="register" size="large" style="width: 80%;margin-left: 40px" type="primary">注册</el-button>
      </div>
      <div style="text-align: right">已有账号，请前去登录<a style="color: #0742b1" href="/login">登录</a></div>
    </el-form>
  </div>
</div>
  </div>

</template>

<script setup>
import {reactive,ref} from "vue";
import {User,Lock} from "@element-plus/icons-vue"
import request from "@/utils/request.js";
import {ElMessage} from "element-plus";

const validatePass = (rule, value, callback) => {
  if(!value){
    callback(new Error('请再次确认密码'))
  } else if (value !== data.form.password){
    callback(new Error('两次输入密码不一致'))
  }else {
    callback()
  }
}

const data = reactive({
  form:{},
  rules:{
    username: [
      {required:true,message:'请输入账号',trigger:'blur'}
    ],
    password:[
      {required:true,message:'请输入密码',trigger:'blur'}
    ],
    confirmPassword:[
      {validator: validatePass, tirgger:'blur'}
    ]
  }
})
const formRef = ref()



const register = () => {
  formRef.value.validate((valid)=>{
    if(valid) {
      request.post('/register',data.form).then(res =>{
        if(res.code === '200'){//注册成功
          ElMessage.success('注册成功')
          setTimeout(()=>{
            location.href = '/login'
          },500)
        }else {
          ElMessage.error(res.msg)
        }
      })
    }
  })
}

</script>

<style scoped>
.login-container{
  height: 100vh;
  overflow: hidden;
  background-image: url("@/assets/bg.png");
  background-size: cover;
  background-position: -200px -1px;
}

.login-box{
  width: 50%;
  height: 100%;
  display: flex;
  align-items: center;
  right: 0;
  position:absolute;
}

</style>

```

Form自定义校验规则:

debug了半天，啊啊啊啊啊啊啊，发现是因为在 data.rules 中，没有将 validatePass 作为函数引用传递，而不是调用它。

也就是这里：

```vue
confirmPassword:[
  {validator: validatePass, tirgger:'blur'}
]
```

##### 注册逻辑：

先写接口:

```vue
@PostMapping("/register")
public Result register(@RequestBody Employee employee){
        EmployeeService.register(employee);
        return Result.success();
}
```

service方法逻辑：在add中写 在register中调用

```java
public void add(Employee employee) {
    String username = employee.getUsername();
    Employee dbEmployee = employeeMapper.selectByUsername(username);
    if(dbEmployee != null){//注册账号已经存在
        throw new CustomException("500","注册账号已经存在");
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

```

## hutool包：

引入pom一个新的包 hutool工具包 

```xml
<dependency>
    <groupId>cn.hutool</groupId>
    <artifactId>hutool-all</artifactId>
    <version>5.8.25</version>
</dependency>

用到了这个工具：StrUtil.isBlank(employee.getName()))  在service的add方法中
```

又debug半天 一直返回get success 但是不跳转 不知道为啥我的code验证被改成了100   应该是200



我勒个！！！！！！原来前面data.user.name 那边的bug在后面有讲解！ 需要一个路由跳转重定向，因为还没有登录 所以还没有数据拿到

```vue
{
  path:'/',
  redirect: '/login'
},
```

## 管理员相关的增删改查：

#### sql：

新建一个表 admin     id设置自增

```sql
CREATE TABLE `admin` (
  `id` int NOT NULL COMMENT 'ID ',
  `username` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '账号',
  `password` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '密码',
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '名称',
  `role` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '角色',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='管理员信息';
```

#### 后端接口：

先entity再 新建admincontroller   adminservice   adminmapper   最后来mapper.xml

按照employee中类似的方法，删除没用的属性

#### 前端页面：

页面 菜单 路由

新建Admin.vue  index.js中新增路由

**未找到接口有两种情况** 一个是前端写错接口（或者是后端的controller中 @RequestMapping中写错接口名称） 二是后端没重启

**后端的接口部分注释：**

@RestController：
标记 AdminController 类为一个 RESTful 控制器。
类中的所有方法默认返回 JSON 或 XML 格式的数据。
@RequestMapping("/admin")：
为 AdminController 类中的所有方法提供基础路径 /admin。
例如，@PostMapping("/add") 实际上映射到 /admin/add。
@PostMapping("/add")：
将 HTTP POST 请求映射到 add 方法。
请求路径为 /admin/add。
该方法处理新增数据的请求

#### 流程：

用户操作 -> Admin.vue -> 发送 POST 请求到 /admin/add
                                    |
                                    v
                     AdminController -> @PostMapping("/add")
                                    |
                                    v
                     AdminService -> add 方法
                                    |
                                    v
                     AdminMapper -> 插入新管理员记录
                                    |
                                    v
                     返回响应 -> AdminController -> 返回 Result 对象
                                    |
                                    v
                     前端处理响应 -> 显示成功或错误消息

**退出登录逻辑：**

```vue
const logout = () => {
  localStorage.removeItem('xingprojectone-user')
  location.href ='/login'//清楚当前登录的用户缓存数据
} 
```

退出按钮要加清楚缓存功能

## 管理员登录（多角色登录）：

 新建父类  在entity中 建 Account 让employee和admin extends Account 然后 修改AdminService和Employee中的login方法 将admin修改为 Account    因为父类可以包含子类

将webcontroller.java中的login修改   并且在上面新加 @Resource private Adminservice  adminservice

```java
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
```

通过统一的基类来管理

在login.vue中也必须包含两个角色

```vue
<el-form-item>
  <el-select v-model="data.form.role" style="width: 100%" size="large">
    <el-option  value="ADMIN" label="管理员"></el-option>
    <el-option  value="EMP" label="员工"></el-option>
  </el-select>
</el-form-item>
```

### 个人信息和修改密码：

#### **新建person.vue，并添加相应的路由：**

```vue
<template>
  <div class="card" style="width: 50%;padding:40px 20px">
    <el-form ref="formRef" :rules="data.rules" :model="data.form" style="padding-right: 40px;padding-top: 20px "label-width="80px">
      <el-form-item label="账号" prop="username"><el-input disabled v-model="data.form.username" autocomplete="off" placeholder="请输入账号"/></el-form-item>
      <el-form-item label="名称" prop="name"><el-input v-model="data.form.name" autocomplete="off" placeholder="请输入名称"/></el-form-item>
      <div v-if="data.user.role === 'EMP'">
        <el-form-item label="性别">
          <el-radio-group v-model="data.form.sex">
            <el-radio value="男" label="男"></el-radio>
            <el-radio value="女" label="女"></el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="工号" prop="no" label-width="80px"><el-input disabled v-model="data.form.no" autocomplete="off" placeholder="请输入工号"/></el-form-item>
        <el-form-item label="年龄" label-width="80px"><el-input-number style="width: 160px" min="18" v-model="data.form.age" autocomplete="off" placeholder="请输入年龄"/></el-form-item>
        <el-form-item label="个人介绍" label-width="80px"><el-input rows=3 type="textarea" v-model="data.form.descr" autocomplete="off" placeholder="请输入个人介绍"/></el-form-item>
      </div>
      <div style="text-align: center">
        <!--        按钮居中-->
        <el-button @click="UpdateUser" type="primary" style="padding:20px 30px">更新个人信息</el-button>
      </div>
    </el-form>
  </div>
</template>


<script setup>
import {reactive,ref} from "vue";
import request from "@/utils/request.js";
import {ElMessage} from "element-plus";


const formRef =ref()
const data =reactive({
  form :{},
  user: JSON.parse(localStorage.getItem("xingprojectone-user")),
  rules:{
    username:[
      {required:true,message:"请输入账号",trigger:"blur"}],
    name:[{required:true,message:"请输入名称",trigger:"blur"}],
    no:[{required:true,message:"请输入工号",trigger:"blur"}],

  }

})


const emit = defineEmits(['updateUser'])

if (data.user.role === 'EMP'){
    request.get('employee/selectById/' + data.user.id).then(res => {
      data.form = res.data
    })
} else{
  data.form = data.user
}

const UpdateUser =() => {
   if(data.user.role === 'EMP'){
     request.put('employee/update',data.form).then(res =>{
       if(res.code === '200'){
         ElMessage.success('更新成功')
         //更新缓存数据
         localStorage.setItem('xingprojectone-user',JSON.stringify(data.form))
         //触发父级页面缓存里面取到的最新数据
         emit('updateUser')
       }
       else {
         ElMessage.error(res.msg)
       }
     })
   }else {
     request.put('employee/update',data.form).then(res =>{
       if(res.code === '200'){
         ElMessage.success('更新成功')
         //更新缓存数据
         localStorage.setItem('xingprojectone-user',JSON.stringify(data.form))
         //触发父级页面缓存里面取到的最新数据
         emit('updaterUser')
       }else {
         ElMessage.error(res.msg)
       }
     })
   }
}

</script>

//子页面 触发父级页面的更新
```

 **点击更新按钮更新个人信息的逻辑：**

#### 子组件发送请求更新父组件的数据

```vue
<script setup>
const UpdateUser =() => {
   if(data.user.role === 'EMP'){
     request.put('employee/update',data.form).then(res =>{
       if(res.code === '200'){
         ElMessage.success('更新成功')
         //更新缓存数据
         localStorage.setItem('xingprojectone-user',JSON.stringify(data.form))
         //触发父级页面缓存里面取到的最新数据
         emit('updaterUser')
       }
       else {
         ElMessage.error(res.msg)
       }
     })
   }else {
     request.put('employee/update',data.form).then(res =>{
       if(res.code === '200'){
         ElMessage.success('更新成功')
         //更新缓存数据
         localStorage.setItem('xingprojectone-user',JSON.stringify(data.form))
         //触发父级页面缓存里面取到的最新数据
         emit('updaterUser')
       }else {
         ElMessage.error(res.msg)
       }
     })
   }
}
</script>
重点是：
记得在按钮绑定updateuser
const emit = defineEmits(['updaterUser'])
//更新缓存数据
localStorage.setItem('xingprojectone-user',JSON.stringify(data.form))
emit('updaterUser')
```

在manager.vue中需要接收 ![父级页面接收](/Users/ruixianli/Desktop/jie/project1的proceed screen/父级页面接收.png)

```vue
定义updateUser
const updateUser =() =>{
  data.user = JSON.parse(localStorage.getItem('xingprojectone-user'))
}
用户点击“更新个人信息”按钮 -> 调用 UpdateUser 方法
                                    |
                                    v
                     检查用户角色 -> 是员工 (EMP) 或其他角色
                                    |
                                    v
                     发送 PUT 请求 -> /employee/update
                                    |
                                    v
                     处理响应 -> 如果响应状态码为 200
                                    |
                                    v
                     显示成功消息 -> 更新缓存数据
                                    |
                                    v
                     触发父组件更新事件 -> emit('updaterUser')
                                    |
                                    v
                     如果响应状态码不是 200 -> 显示错误消息
                                    |
                                    v
                     捕获请求错误 -> 显示错误消息
定义事件：在 Person.vue 中使用 defineEmits(['updateUser']) 定义 updateUser 事件。
触发事件：在 UpdateUser 方法中正确触发 updateUser 事件。
监听事件：在父组件中使用 @updateUser="updateUser" 监听 updateUser 事件，并执行相应的更新操作。
通过以上步骤，你可以确保在 Person.vue 中更新用户信息后，父组件能够正确地响应并更新用户信息

```

#### 修改密码：

新建Password.vue,在index.js中添加新路由,添加新路由的时候记得加入到manager里面

index.js：

```js
{
  path: 'password',
  name: 'password', // 修改为唯一的名称
  meta:{title:'修改密码页面'},
  component: () => import('../views/Password.vue') },
```

父页面：

manager.vue:

```vue
<el-menu-item index="/manager/password">
  <el-icon><Lock/></el-icon>
  修改密码
</el-menu-item>
```

Password.vue:

```vue
<template>
  <div class="card" style="padding: 40px 20px;width: 50%">
    <el-form ref="formRef" :rules="data.rules" :model="data.form" style="padding-right: 40px;padding-top: 20px "label-width="120px">
      <el-form-item label="原密码" prop="passwprd"><el-input show-password v-model="data.form.password" autocomplete="off" placeholder="请输入原密码"/></el-form-item>
      <el-form-item label="新密码" prop="newpassword"><el-input show-password v-model="data.form.newPassword" autocomplete="off" placeholder="请输入新密码"/></el-form-item>
      <el-form-item label="请输入新密码" prop="confirmPassword" required><el-input show-password v-model="data.form.confirmPassword" autocomplete="off" placeholder="请再次输入新密码"/></el-form-item>
    <div style="text-align: center">
      <el-button @click="updatePassword" type="primary" style="padding: 20px 30px">立即修改</el-button>
    </div>
    </el-form>

  </div>

</template>


<script setup>
import {reactive,ref} from "vue";
import request from "@/utils/request.js";
import {ElMessage} from "element-plus";

const validatePass = (rule, value, callback) => {
  if(!value){
    callback(new Error('请再次确认新密码'))
  } else if (value !== data.form.newPassword){
    callback(new Error('两次输入密码不一致'))
  }else {
    callback()
  }
}


const formRef=ref()
const data = reactive({
  user:JSON.parse(localStorage.getItem('xingprojectone-user')), //取数据
  form:{},
  rules: {
    password: [
      {required: true, message: "请输入原密码", trigger: "blur"}
    ],
    newPassword: [
      {required: true, message: "请输入新密码", trigger: "blur"}
    ],
    confirmPassword: [
      {validator:validatePass, trigger: "blur"}
    ],
  }
})

const updatePassword = () =>{
  data.form.id=data.user.id
  data.form.role = data.user.role
  formRef.value.validate((valid)=>{
    if(valid){
      request.put('/updatePassword',data.form).then(res=>{
        if(res.code === '200'){
          ElMessage.success('修改成功')
          localStorage.removeItem('xingprojectone-user') //退出当前用户 清除数据
          setTimeout(() => {
            location.href = '/login'
          },500)
        }else {
          ElMessage.error(res.msg)
        }
      })
    }
  })

}
</script>
 
需要重点注意const upadtePassword这部分的逻辑   要把id和角色一起传进来，然后再验证
```

写相应的接口和方法逻辑

后端逻辑：

Webcontroller.java:

```java
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
```

Employeeservice.java:

```java
public void updatePassword(Account account) {
    Integer id = account.getId();
    Employee employee = this.selectById(id);
    if(!employee.getPassword().equals(account.getPassword())){
        throw new CustomException("500","旧密码错误");
    }
    employee.setPassword(account.getNewPassword());
    this.update(employee);
}
```

AdminService.java:

```java
public void updatePassword(Account account) {
    Integer id = account.getId();
    Admin admin = this.selectById(id);
    if(!admin.getPassword().equals(account.getPassword())){
        throw new CustomException("500","旧密码错误");
    }
    admin.setPassword(account.getNewPassword());
    this.update(admin);
}
```

## 文件上传与下载

### 后端接口：

#### 文件上传：

新建FileController.java：

```java
    //System.getProperty("usr.dir") 获取当前项目根路径
    //文件上传路径
    private static final String filePath = System.getProperty("user.dir") + "/files/";
/**
 * 文件上传接口
 * @return
 */
    @PostMapping("/upload")
    public Result upload(MultipartFile file){ //文件流的形式接受前端发来的文件
        if (file == null || file.isEmpty()) {
            return Result.error();
        }
        String originalFilename = file.getOriginalFilename(); // xxx.png
        if (originalFilename == null || originalFilename.isEmpty()) {
            return Result.error();
        }
        if(!FileUtil.isDirectory(filePath)){
            FileUtil.mkdir(filePath);
        }
        //提供文件存储的完整路径
        //给文件名加一个唯一的表示标识
        String fileName = System.currentTimeMillis() + originalFilename; //时间戳
        String realPath = filePath + fileName;
        try {
            FileUtil.writeBytes(file.getBytes(),realPath);
        } catch (IOException e) {
            e.printStackTrace();
            throw new CustomException("500","文件上传失败");
        }
        //返回一个网络连接
        //http://localhost:9090/files/upload
        String url = "http://localhost:9090/files/download/" + fileName;
        return result.success(url);
    }
```

postman测试文件上传：

![测试文件上传](/Users/ruixianli/Desktop/jie/project1的proceed screen/测试文件上传.png)

#### 文件下载：

```java
/**
 * 文件下载
 */
@GetMapping("/download/{fileName}")
public void download(@PathVariable String fileName, HttpServletResponse response){
    try {
        //文件名统一设置为utf-8
        response.addHeader("Content-Disposition","attachment;filename=" + URLEncoder.encode(fileName, StandardCharsets.UTF_8));
        response.setContentType("application/octet-stream");
        OutputStream os = response.getOutputStream();
        String realPath = filePath + fileName;
        //获取到文件的字节数组
        byte[] bytes = FileUtil.readBytes(realPath);
        os.write(bytes);
        os.flush();
        os.close();
    } catch (IOException e) {
        e.printStackTrace();
        throw new CustomException("500","文件下载失败");
    }
}
```

### 前端实现：

数据库添加完信息记得保存

在person.vue中加入 upload组件

```vue
<div style="width:100%;display: flex;justify-content: center;margin-bottom: 20px">
  <el-upload
      class="avatar-uploader"
      action="http://localhost:9090/files/upload"
      :show-file-list="false"
      :on-success="handleAvatarSuccess"
  >
    <img v-if="data.form.avatar" :src="data.form.avatar" class="avatar" />
    <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
  </el-upload>
  </div>
```

回调函数：

```vue
const handleAvatarSuccess = (res) => {
  console.log(res.data)
  data.form.avatar = res.data
}
```

Bug：在管理员处发现头像无法同步更新，发现是因为在person.vue中下面把update的管理员的接口写错了，写成了employee的

在表格中显示图片：

```vue
<el-table-column label="头像">
  <template #default="scope">
    <img v-if="scope.row.avatar" :src="scope.row.avatar" style="display: block; width: 40px; height: 40px; border-radius: 50%">
  </template>
</el-table-column>
```

在新增员工的表单中添加上传头像：

Employee.vue

```vue
<el-form-item label="头像">
      <el-upload
          action="http://localhost:9090/files/upload"
          list-type="picture"
          :on-success="handleAvatarSuccess"
      >
        <el-button type="primary">上传头像</el-button>
      </el-upload>
    </el-form-item>
定义回调函数
const handleAvatarSuccess = (res) => {
  console.log(res.data)
  data.form.avatar = res.data
}
```

管理员一模一样即可

## 实现富文本编辑器功能：

### wangEditor5插件

```markdown
cd vue-project
npm install @wangeditor/editor --save
npm install @wangeditor/editor-for-vue@next --save
```

安装后会在package.json中多出：

```json
"@wangeditor/editor": "^5.1.23",
"@wangeditor/editor-for-vue": "^5.1.12",
```

#### 新模块：文章管理

##### **静态的富文本页面**

Home.vue:

**引入依赖：安装并引入 @wangeditor/editor-for-vue 和相关样式。**

```vue
npm install 
import {onBeforeUnmount,ref,shallowRef} from "vue";
import '@wangeditor/editor/dist/css/style.css'
import { Editor, Toolbar } from '@wangeditor/editor-for-vue'
```

**定义状态和引用：使用 Vue 的组合式 API 定义响应式数据和引用。**

```vue
import { reactive, ref, shallowRef, onBeforeUnmount } from 'vue';

const editorRef = shallowRef(); // 编辑器实例 必须用shallowRef
const mode = 'default'; // 编辑器模式
const editorConfig = { MENU_CONF: {} }; // 编辑器配置

// 组件销毁时，也及时销毁编辑器，否则可能会造成内存泄漏
onBeforeUnmount(() => {
  const editor = editorRef.value;
  if (editor == null) return;
  editor.destroy();
});

// 记录editor实例
const handleCreated = (editor) => {
  editorRef.value = editor;
};
```

**定义对话框和表单：创建用于控制对话框显示的布尔值和表单数据。**

```vue
const data = reactive({
  formContentVisible: false, // 控制富文本编辑器对话框的显示
  form: {}, // 表单数据
  tablebox: [
    { id: 1, date: '2022-01-01', name: '张三', address: '北京', content: '<h1 style="color: red">哈哈哈</h1>' },
    // 其他数据...
  ],
  // 其他属性...
});
```

**编辑内容的方法：定义打开富文本编辑器对话框的方法，并深度拷贝当前行的数据。**

```vue
const editContent = (row) => {
  data.formContentVisible = true;
  data.form = JSON.parse(JSON.stringify(row)); // 深度拷贝，避免直接修改原数据
};
```

**保存内容的方法：定义保存方法，更新表格中的内容并关闭对话框。**

```vue
const save = () => {
  // 更新表格中的内容
  const index = data.tablebox.findIndex(item => item.id === data.form.id);
  if (index !== -1) {
    data.tablebox[index] = { ...data.form };
  }
  // 关闭对话框
  data.formContentVisible = false;
};
```

**模板部分：在模板中添加富文本编辑器和对话框。

```vue
<el-dialog v-model="data.formContentVisible" title="编辑内容" width="800">
  <div style="padding: 20px">
    <div>
      <Toolbar
        style="border-bottom: 1px solid #ccc"
        :editor="editorRef"
        :mode="mode"
      />
      <Editor
        style="height: 500px; overflow-y: hidden;"
        v-model="data.form.content"
        :mode="mode"
        :defaultConfig="editorConfig"
        @onCreated="handleCreated"
      />
    </div>
  </div>
  <template #footer>
    <div class="dialog-footer">
      <el-button @click="data.formContentVisible = false">取 消</el-button>
      <el-button type="primary" @click="save">保 存</el-button>
    </div>
  </template>
</el-dialog>

```

**表格列中的操作按钮：在表格的操作列中添加编辑富文本的按钮。**

```vue
<el-table-column label="操作">
  <template #default="scope">
    <el-button type="primary" @click="editContent(scope.row)">
      编辑富文本
    </el-button>

    <el-button size="small" type="primary" circle @click="edit(scope.row)">
      <el-icon><Edit /></el-icon>
    </el-button>

    <el-button size="small" type="danger" circle @click="del(scope.row.id)">
      <el-icon><Delete /></el-icon>
    </el-button>
  </template>
</el-table-column>

```

##### 真正的动态富文本：

###### 创建数据库表

```sql
CREATE TABLE `article` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID ',
  `title` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '标题',
  `img` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '封面',
  `description` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '简介',
  `content` longtext COLLATE utf8mb4_unicode_ci COMMENT '内容',
  `time` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '发布时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='文章信息\n';
```

###### 创建后端接口

创建实体类

```java
package com.example.entity;

public class Article {
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    private Integer id;
    private String title;
    private String img;
    private String description;
    private String content;
    private String  time;



}
```

写controller接口

```java
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
        public Result upadte(@RequestBody Article article){
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
```

写service方法

```java
package com.example.service;
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
```

写mapper

```java
package com.example.mapper;
import com.example.entity.Article;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ArticleMapper {

    List<Article> selectAll(Article article);

    @Select("select * from `article` where id = #{id}")
    Article selectById(Integer id);

    void insert(Article article);

    void updateById(Article article);

    @Delete("delete from `article` where id = #{id}")
    void deleteById(Integer id);
    

}
```

写mapper.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.example.mapper.ArticleMapper">

    <select id="selectAll" resultType="com.example.entity.Article">
        select * from article
        <where>
            <if test="title != null">name like concat ('%',#{title},'%')</if>
        </where>
        order by id desc
    </select>

    
    <insert id="insert" parameterType="com.example.entity.Article">
        insert into `article` (title,img,description,content,time)
        values (#{title},#{img},#{description},#{content},#{time})
    </insert>

    <update id="updateById" parameterType="com.example.entity.Article">
        update `article` set title=#{title},img=#{img},description=#{description},content=#{content},time=#{time}
        where id=#{id}
    </update>
</mapper>
```

###### 为什么写完后数据没写到数据库，页面不显示数据

这时候注意看一下复制粘贴过来的一些属性是不是没有更新改掉

将所有的admin换成article   把其他类似advatar等属性改成正确的

###### 前端页面：

Article.vue

```vue
<template>
  <div>
    <div class="card" style="margin-bottom: 5px">
      <el-input style="width: 240px; margin-right: 10px" v-model="data.title" placeholder="请输入标题查询" prefix-icon="Search"></el-input>
      <el-button type="primary" @click="load">查询</el-button>
      <el-button type="warning" @click="reset">重置</el-button>
    </div>

    <div class="card" style="margin-bottom: 5px">
      <el-button type="primary" @click="handleAdd">新 增</el-button>
      <el-button type="danger" @click="delBatch(data.ids)">批量删除</el-button>
<!--      <el-button type="info">导入</el-button>-->
<!--      <el-button type="success">导出</el-button>-->
    </div>

    <div class="card" style="margin-bottom: 5px">
      <el-table :data="data.tablebox" stripe @selection-change = "handleSelectChange">
        <el-table-column type="selection" width="55"/>
        <el-table-column label="标题" prop="title"/>
        <el-table-column label="封面">
          <template #default="scope">
            <el-image v-if="scope.row.img" :src="scope.row.img" :preview-src-list=[scope.row.img] preview-teleported style="display: block; width: 100px; height: 60px;"/>
          </template>
        </el-table-column>
        <el-table-column label="简介" prop="description" show-overflow-tooltip/>
        <el-table-column label="内容">
          <template #default="scope">
            <el-button type="primary" @click="view(scope.row.content)">查看内容</el-button>
          </template>
        </el-table-column>

        <el-table-column label="发布时间" prop="time"/>
        <el-table-column label="操作" width="120px">
          <template #default="scope">
            <el-button @click="handleUpdate(scope.row)" type="primary" :icon="Edit" circle></el-button>
            <el-button @click="del(scope.row.id)" type="danger" :icon="Delete" circle></el-button>
          </template>
          </el-table-column>
      </el-table>
      <div style="margin-top: 15px">
        <el-pagination
            @size-change="load"
            @current-change="load"
            v-model:current-page="data.pageNum"
            v-model:page-size="data.pageSize"
            :page-sizes="[5, 10, 15, 20]"
            background
            layout="total, sizes, prev, pager, next, jumper"
            :total="data.total"
        />
      </div>
    </div>

<el-dialog title="文章信息" v-model="data.formVisible" width="50%" destroy-on-close>
  <el-form  :model="data.form" style="padding-right: 40px;padding-top: 20px "label-width="80px">
    <el-form-item label="标题" prop="title"><el-input  v-model="data.form.title" autocomplete="off" placeholder="请输入标题"/></el-form-item>
    <el-form-item label="封面">
      <el-upload
          action="http://localhost:9090/files/wang/upload"
          list-type="picture"
          :on-success="handleImgSuccess"
      >
        <el-button type="primary">上传封面</el-button>
      </el-upload>
    </el-form-item>
    <el-form-item label="简介" prop="description">
      <el-input type="textarea" :rows="3" v-model="data.form.description" autocomplete="off" placeholder="请输入简介"/>
    </el-form-item>
    <el-form-item label="内容">
    <div style="border:1px solid #ccc;width:100%">
      <Toolbar
          style="border-bottom: 1px solid #ccc"
          :editor="editorRef"
          :mode="mode"
      />
      <Editor
          style="height: 500px; overflow-y:hidden;"
          v-model="data.form.content"
          :mode="mode"
          :defaultConfig="editorConfig"
          @onCreated="handleCreated"
      />
    </div>
    </el-form-item>
  </el-form>
  <template #footer>
    <div class="dialog-footer">
      <el-button @click="data.formVisible = false">取 消</el-button>
      <el-button type="primary" @click="save">保 存</el-button>
    </div>
  </template>
</el-dialog>
    <el-dialog title="内容" v-model="data.viewVisible" width="50%" :close-on-click-modal="false" destroy-on-close>
      <div class="editor-content-view" style="padding: 20px" v-html="data.content"></div>
      <template #footer>
        <span class="dialog-footer">
        <el-button @click="data.viewVisible = false">关 闭</el-button>
        </span>
      </template>
    </el-dialog>


  </div>
</template>

<script setup>
import {reactive,ref} from "vue";
import {Delete, Search,Edit} from "@element-plus/icons-vue";
import request from "@/utils/request.js";
import {ElMessage,ElMessageBox} from "element-plus";
import {onBeforeUnmount,shallowRef} from "vue";
import '@wangeditor/editor/dist/css/style.css'
import { Editor, Toolbar } from '@wangeditor/editor-for-vue'


const data=reactive({
  title:null,
  tablebox:[],
  pageNum:1,
  pageSize:5,
  total:0,
  formVisible:false,
  form:{},
  ids:[],
  viewVisible:false,
  content:null
})
const baseUrl ='http://localhost:9090'
const editorRef = shallowRef()//编辑器实例 必须用shallowRef
const mode = 'default'
const editorConfig = { MENU_CONF: {}}
//组件销毁时，也及时销毁编辑器，否则可能会造成内存泄漏
editorConfig.MENU_CONF['uploadImage']={
  server: baseUrl + '/files/wang/upload',
  fileName: 'file',
}
onBeforeUnmount(() => {
  const editor = editorRef.value
  if (editor == null) return
  editor.destroy()
})
//记录editor实例
const handleCreated = (editor) => {
  editorRef.value = editor
}

const view =(content)=> {
  data.content = content
  data.viewVisible = true
}

const handleImgSuccess = (res) => {
  console.log(res.data)
  data.form.img = res.data
}

const load =() => {
  request.get('article/selectPage',{
    params: {
      pageNum: data.pageNum,
      pageSize: data.pageSize,
      name:data.name
    }
  }).then(res => {
    data.tablebox = res.data.list,
        data.total=res.data.total
  })
}
load()
const reset =() => {
  data.name=null
  load()
}

const handleAdd =() =>{
  data.formVisible=true
  data.form={}
}
const save = () => {  //在一个报错方法里面做2个操作，一个是新增，一个是编辑

      data.form.id ? update() : add()

}

const add =() =>{
  request.post('article/add',data.form).then(res =>{      //新增的里面没有id
    if(res.code === '200'){
      data.formVisible=false
      ElMessage.success('操作成功')
      load()  //新增后一定要重新加载最新的数据
    }else {
      ElMessage.error(res.msg)
    }
  })
}

const update =() =>{
  request.put('article/update',data.form).then(res =>{   //编辑的对象里面包含id
    if(res.code === '200'){
      data.formVisible=false
      ElMessage.success('操作成功')
      load()  //更新后一定要重新加载最新的数据
    }else {
      ElMessage.error(res.msg)
    }
  })
}


const handleUpdate = (row) =>{
    data.form = JSON.parse(JSON.stringify(row)) ////深拷贝一个新的对象，用于编辑，这样就不会影响行对象
    data.formVisible=true
}

const del = (id) => {
  ElMessageBox.confirm('删除数据后无法恢复，您确认删除吗？', '删除确认', {type: 'warning'}).then(() => {
    request.delete('article/deleteById/' + id).then(res => {
      if (res.code === '200') {
        ElMessage.success('操作成功')
        load() //删除数据后一定要重新加载最新数据
      } else {
        ElMessage.error(res.msg)
      }
    })
  }).catch()
}

const handleSelectChange = (rows) => {  //返回所有选中的行对象数组
  //从选中的行数组里面取出所有行的id组成一个新的数组
  data.ids = rows.map(row => row.id)
  console.log(data.ids)
}

const delBatch = () => {
  if (data.ids.length === 0){
    ElMessage.warning('请选择要删除的数据')
    return
  }
  ElMessageBox.confirm('删除数据后无法恢复，您确认删除吗？', '删除确认', {type: 'warning'}).then(() => {
    request.delete('article/deleteBatch', {data: data.ids}).then(res => {
      if (res.code === '200') {
        ElMessage.success('操作成功')
        load() //删除数据后一定要重新加载最新数据
      } else {
        ElMessage.error(res.msg)
      }
    })
  }).catch()
}

</script>



```

富文本部分：

```vue
富文本部分：
  
<el-form-item label="封面">
      <el-upload
          action="http://localhost:9090/files/upload"
          list-type="picture"
          :on-success="handleImgSuccess"
      >
        <el-button type="primary">上传封面</el-button>
      </el-upload>
    </el-form-item>
  
<div style="border:1px solid #ccc;width:100%">
      <Toolbar
          style="border-bottom: 1px solid #ccc"
          :editor="editorRef"
          :mode="mode"
      />
      <Editor
          style="height: 500px; overflow-y:hidden;"
          v-model="data.form.content"
          :mode="mode"
          :defaultConfig="editorConfig"
          @onCreated="handleCreated"
      />
    </div>
            
            
</el-dialog>
    <el-dialog title="内容" v-model="data.viewVisible" width="50%" :close-on-click-modal="false" destroy-on-close>
      <div class="editor-content-view" style="padding: 20px" v-html="data.content"></div>
      <template #footer>
        <span class="dialog-footer">
        <el-button @click="data.viewVisible = false">关 闭</el-button>
        </span>
      </template>
    </el-dialog>
import {onBeforeUnmount,shallowRef} from "vue";
import '@wangeditor/editor/dist/css/style.css'
import { Editor, Toolbar } from '@wangeditor/editor-for-vue'
const baseUrl ='http://localhost:9090'
const editorRef = shallowRef()//编辑器实例 必须用shallowRef
const mode = 'default'
const editorConfig = { MENU_CONF: {}}
//组件销毁时，也及时销毁编辑器，否则可能会造成内存泄漏
editorConfig.MENU_CONF['uploadImage']={
  server: baseUrl + '/files/wang/upload',
  fileName: 'file',
}
onBeforeUnmount(() => {
  const editor = editorRef.value
  if (editor == null) return
  editor.destroy()
})
//记录editor实例
const handleCreated = (editor) => {
  editorRef.value = editor
}

const view =(content)=> {
  data.content = content
  data.viewVisible = true
}

const handleImgSuccess = (res) => {
  console.log(res.data)
  data.form.img = res.data
}
```



可以选择是否引入官方提供的view.css

表格中预览图片：

```vue
<el-image v-if="scope.row.img" :src="scope.row.img" :preview-src-list=[scope.row.img] preview-teleported style="display: block; width: 100px; height: 60px;"/>
```

###### 富文本实现：

富文本文件上传需定义接口：在filecontroller中

```java
@PostMapping("/wang/upload")
public Map<String,Object> wangEditorUpload(MultipartFile file){
    String originalFilename = file.getOriginalFilename(); // xxx.png
    if(!FileUtil.isDirectory(filePath)){
        FileUtil.mkdir(filePath);
    }
    //提供文件存储的完整路径
    //给文件名加一个唯一的表示标识
    String fileName = System.currentTimeMillis() +"_" + originalFilename; //时间戳
    String realPath = filePath + fileName;
    try {
        FileUtil.writeBytes(file.getBytes(),realPath);
    } catch (IOException e) {
        e.printStackTrace();
        throw new CustomException("500","文件上传失败");
    }
    //返回一个网络连接
    //http://localhost:9090/files/upload
    String url = "http://localhost:9090/files/download/" + fileName;
    Map<String,Object> resMap = new HashMap<>();
    List<Map<String,Object>> list = new ArrayList<>();
    Map<String,Object> urlMap = new HashMap<>();
    urlMap.put("url",url);
    list.add(urlMap);
    resMap.put("errno",0);
    resMap.put("data",list);
    return resMap;
}

```

###### 此处有bug：

富文本的图片无法从本地上传，前端返回data为null 但是检查了没有发现错误

## 数据批量导入导出功能：

###  部门相关的增删改查：

新建entity

```java
package com.example.entity;

/**
 * 部门信息
 */


public class Department  {
        private Integer id;
        private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
```

新建DepartmentController

```java
package com.example.controller;

import com.example.common.Result;
import com.example.entity.Department;
import com.example.service.DepartmentService;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Resource
    private DepartmentService departmentService;
    /**
     * 新增数据
     */
    @PostMapping("/add")
    public Result add(@RequestBody Department department){
        departmentService.add(department);
        return Result.success();
    }

    /**
     * 更新数据
     */
    @PutMapping("/update")
        public Result upadte(@RequestBody Department department){
        departmentService.update(department);
        return Result.success();
    }
    /**
     * 删除单个数据
     */
    @DeleteMapping("/deleteById/{id}")
    public Result deleteById(@PathVariable Integer id){
        departmentService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除多行数据
     */
    @DeleteMapping("/deleteBatch")
    public Result deleteBatch(@RequestBody List<Integer> ids){
            departmentService.deleteBatch(ids);
            return Result.success();
    }


    /**
     * 查询所有员工信息
     * @return
     */
    @GetMapping("/selectAll")
    public Result selectAll(Department department){
        List<Department> list = departmentService.selectAll(department);
        return Result.success(list);
    }


//查询单个数据：
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id){
        Department department = departmentService.selectById(id);
        return Result.success(department);
    }


//查询单个数据：
//    @GetMapping("/selectOne")
//    public Result selectOne(@RequestParam Integer id){
//        Department department = departmentService.selectById(id);
//        return Result.success(department);
//    }
//    @GetMapping("/selectList")
//    public Result selectList(Department department){
//        List<Department> list = departmentService.selectList(department);
//        return Result.success(list);
//    }
    /**
     * 分页查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/selectPage")
    public Result selectPage(Department department,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize){
        PageInfo<Department> pageInfo = departmentService.selectPage(department,pageNum,pageSize);
        return Result.success(pageInfo);
    }

}
```

新建DepartmentService

```java
package com.example.service;
import cn.hutool.core.util.StrUtil;
import com.example.entity.Account;
import com.example.entity.Department;
import com.example.exception.CustomException;
import com.example.mapper.DepartmentMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Resource
    private DepartmentMapper departmentMapper;



    public List<Department> selectAll(Department department) {
        return departmentMapper.selectAll(department);
    }

    public Department selectById(Integer id) {
        return departmentMapper.selectById(id);
    }

    public PageInfo<Department> selectPage(Department department,Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Department> lsit=departmentMapper.selectAll(department);
        return PageInfo.of(lsit);
    }

    
    public void add(Department department) {

            departmentMapper.insert(department);
    }


    public void update(Department department) {
        departmentMapper.updateById(department);
    }

    public void deleteById(Integer id) {
        departmentMapper.deleteById(id);
    }
    public void deleteBatch(List <Integer> ids) {
        for(Integer id:ids){
            this.deleteById(id);
        }
    }


}
```

新建DepartmentMapper

```java
package com.example.mapper;


import com.example.entity.Department;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DepartmentMapper {

    List<Department> selectAll(Department department);

    @Select("select * from `department` where id = #{id}")
    Department selectById(Integer id);

    void insert(Department department);

    void updateById(Department department);

    @Delete("delete from `department` where id = #{id}")
    void deleteById(Integer id);
    

}
```

新建mapper.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.example.mapper.DepartmentMapper">

    <select id="selectAll" resultType="com.example.entity.Department">
        select * from department
        <where>
            <if test="name != null">name like concat ('%',#{name},'%')</if>
        </where>
        order by id desc
    </select>

    
    <insert id="insert" parameterType="com.example.entity.Department">
        insert into `department` (name)
        values (#{name})
    </insert>

    <update id="updateById" parameterType="com.example.entity.Department">
        update `department` set name=#{name}
        where id=#{id}
    </update>
</mapper>
```

新建Department.vue

```vue
<template>
  <div>
    <div class="card" style="margin-bottom: 5px">
      <el-input style="width: 240px; margin-right: 10px" v-model="data.name"placeholder="请输入名称查询"prefix-icon="Search"></el-input>
      <el-button type="primary" @click="load">查询</el-button>
      <el-button type="warning" @click="reset">重置</el-button>
    </div>

    <div class="card" style="margin-bottom: 5px">
      <el-button type="primary" @click="handleAdd">新 增</el-button>
      <el-button type="danger" @click="delBatch(data.ids)">批量删除</el-button>
<!--      <el-button type="info">导入</el-button>-->
<!--      <el-button type="success">导出</el-button>-->
    </div>

    <div class="card" style="margin-bottom: 5px">
      <el-table :data="data.tablebox" stripe @selection-change = "handleSelectChange">
        <el-table-column type="selection" width="55"/>
        <el-table-column label="名称" prop="name"/>

        <el-table-column label="操作" width="120px">
          <template #default="scope">
            <el-button @click="handleUpdate(scope.row)" type="primary" :icon="Edit" circle></el-button>
            <el-button @click="del(scope.row.id)" type="danger" :icon="Delete" circle></el-button>
          </template>
          </el-table-column>
      </el-table>
      <div style="margin-top: 15px">
        <el-pagination
            @size-change="load"
            @current-change="load"
            v-model:current-page="data.pageNum"
            v-model:page-size="data.pageSize"
            :page-sizes="[5, 10, 15, 20]"
            background
            layout="total, sizes, prev, pager, next, jumper"
            :total="data.total"
        />
      </div>
    </div>

<el-dialog title="部门信息" v-model="data.formVisible" width="500" destroy-on-close>
  <el-form ref="formRef" :rules="data.rules" :model="data.form" style="padding-right: 40px;padding-top: 20px "label-width="80px">
    <el-form-item label="名称" prop="name"><el-input v-model="data.form.name" autocomplete="off" placeholder="请输入名称" /></el-form-item>
  </el-form>
  <template #footer>
    <div class="dialog-footer">
      <el-button @click="data.formVisible = false">取 消</el-button>
      <el-button type="primary" @click="save">保 存</el-button>
    </div>
  </template>
</el-dialog>
<

  </div>
</template>

<script setup>
import {reactive,ref} from "vue";
import {Delete, Search,Edit} from "@element-plus/icons-vue";
import request from "@/utils/request.js";
import {ElMessage,ElMessageBox} from "element-plus";


const data=reactive({
  name:null,
  tablebox:[],
  pageNum:1,
  pageSize:5,
  total:0,
  formVisible:false,
  form:{},
  ids:[],
  rules:{
    name:[
      {required:true,message:'请输入名称',trigger:'blur'},
    ],

  }
})

const formRef = ref()

const load =() => {
  request.get('department/selectPage',{
    params: {
      pageNum: data.pageNum,
      pageSize: data.pageSize,
      name:data.name
    }
  }).then(res => {
    data.tablebox = res.data.list,
        data.total=res.data.total
  })
}
load()

const reset =() => {
  data.name=null
  load()
}

const handleAdd =() =>{
  data.formVisible=true
  data.form={}
}
const save = () => {  //在一个报错方法里面做2个操作，一个是新增，一个是编辑
  formRef.value.validate((valid) => {
    if (valid) {
      data.form.id ? update() : add()
    }
  })
}

const add =() =>{
  request.post('department/add',data.form).then(res =>{      //新增的里面没有id
    if(res.code === '200'){
      data.formVisible=false
      ElMessage.success('操作成功')
      load()  //新增后一定要重新加载最新的数据
    }else {
      ElMessage.error(res.msg)
    }
  })
}

const update =() =>{
  request.put('department/update',data.form).then(res =>{   //编辑的对象里面包含id
    if(res.code === '200'){
      data.formVisible=false
      ElMessage.success('操作成功')
      load()  //更新后一定要重新加载最新的数据
    }else {
      ElMessage.error(res.msg)
    }
  })
}


const handleUpdate = (row) =>{
    data.form = JSON.parse(JSON.stringify(row)) ////深拷贝一个新的对象，用于编辑，这样就不会影响行对象
    data.formVisible=true
}

const del = (id) => {
  ElMessageBox.confirm('删除数据后无法恢复，您确认删除吗？', '删除确认', {type: 'warning'}).then(() => {
    request.delete('department/deleteById/' + id).then(res => {
      if (res.code === '200') {
        ElMessage.success('操作成功')
        load() //删除数据后一定要重新加载最新数据
      } else {
        ElMessage.error(res.msg)
      }
    })
  }).catch()
}

const handleSelectChange = (rows) => {  //返回所有选中的行对象数组
  //从选中的行数组里面取出所有行的id组成一个新的数组
  data.ids = rows.map(row => row.id)
  console.log(data.ids)
}

const delBatch = () => {
  if (data.ids.length === 0){
    ElMessage.warning('请选择要删除的数据')
    return
  }
  ElMessageBox.confirm('删除数据后无法恢复，您确认删除吗？', '删除确认', {type: 'warning'}).then(() => {
    request.delete('department/deleteBatch', {data: data.ids}).then(res => {
      if (res.code === '200') {
        ElMessage.success('操作成功')
        load() //删除数据后一定要重新加载最新数据
      } else {
        ElMessage.error(res.msg)
      }
    })
  }).catch()
}


</script>
```

### 关联员工：

在编辑中加一个部门

```vue
<el-form-item label="部门">
   <el-select style="width: 100%" v-model="data.form.departmentId">
        <el-option v-for="item in data.departmentList" :key="item.id" :label="item.name" :value="item.id" ></el-option>
      </el-select>
</el-form-item>
```

#### 关联查询：

在Employee.xml中坐关联查询找部门

```sql
<select id="selectAll" resultType="com.example.entity.Employee">
    select employee.*,department.name as departmentName from employee
    left join department on employee.department_id = department.id
    <where>
        <if test="name != null">employee.name like concat ('%',#{name},'%')</if>
    </where>
    order by employee.id desc
</select>
```

在Employee的实体类中新增departmentName字段

**修改完之后记得重启**

### 导出数据到Excel：

```vue
 <el-button type="success" @click="exportData">导出</el-button>
导出的方法：
const exportData = () => {
    //导出数据是通过流的形式下载 excel   下载文件是通过流  request请求是获得json数据
    //打开流的连接，浏览器会自动帮下载文件
    window.open("http://localhost:9090/employee/export")
}
```

在employee中新增接口：

```java
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
```

报错了：get failure  	

需要引入：处理文档的依赖

```xml
<dependency>
    <groupId>org.apache.poi</groupId>
    <artifactId>poi-ooxml</artifactId>
    <version>5.3.0</version>
</dependency>
```

发现我的密码也被导出来了！

有潜在bug 需要在controller的接口中添加：

```java
//默认的，未添加alias的属性也会写出，如果想只写出加了别名的字段，可以调用此方法排除
writer.setOnlyAlias(true);
```

此时只会导出员工账号,后**导出完整的excel**

### 从Excel导入数据：

添加接口

```java
@PostMapping("import")
public Result imp(MultipartFile file) throws Exception {
//1.拿到输入流 构建reader
InputStream inputStream = file.getInputStream();
ExcelReader reader = ExcelUtil.getReader(inputStream);
//2.读取excel里面的数据
List<Employee> employeeList = reader.readAll(Employee.class);
//3.写入List到数据库
for (Employee employee : employeeList){
    employeeService.add(employee);
}
return Result.success();
}
```

前端中加入文件上传的事件：

```vue
<el-upload
    style="display: inline-block;margin: 0 10px"
    action="http://localhost:9090/employee/import"
    :show-file-list="false"
    :on-success="importsuccess"
>
<el-button type="info">导入</el-button>
</el-upload>


const importsuccess=() =>{
  ElMessage.success('批量数据导入成功')
  load()
}
```

有bug 部门未导入 ，需要在数据库表中增加部门字段才行

或者部门显示实际上用的是id，但是excel表中使用了部门名称，所以没能显示，自己改下就好了



## 数据统计图表功能： 

####  Echarts

**到vue的目录下  npm i  echarts -S   会在package.json中看到**

引入onMounted

Data.vue

#### 静态的Echarts：

```vue
<template>
  <div>
    <!-- 为 ECharts 准备一个定义了宽高的 DOM -->
    <div id="main" style="width: 600px;height:400px;"></div>
    <div id="line" style="width: 600px;height: 400px"></div>

  </div>

</template>


<script setup>
import {reactive,onMounted} from "vue";
import * as echarts from "echarts"

const  data = reactive({
})
const option ={
  title: {
    text: 'ECharts 入门示例'
  },
  tooltip: {},
  legend: {
    data: ['销量','访问量']
  },
  xAxis: {
    data: ['衬衫', '羊毛衫', '雪纺衫', '裤子', '高跟鞋', '袜子']
  },
  yAxis: {},
  series: [
    {
      name: '销量',
      type: 'bar',
      data: [5, 20, 36, 10, 10, 20]
    },
    {
      data: [15, 23, 24, 28, 15, 17, 20],
      type: 'line',
      smooth: true,
      name: '访问量'
    },
  ]
}

const option1={
  title:{
    text:'ECharts 折线图'
  },
  xAxis: {
    type: 'category',
        data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
  },
  yAxis: {
    type: 'value'
  },
  series: [
    {
      data: [150, 230, 224, 218, 135, 147, 260],
      type: 'line',
      smooth: true,
      name: '访问量'
    },
    {
      data: [188, 260, 290, 28, 135, 147, 200],
      type: 'line',
      smooth: true,
      name: '销售额'
    }
  ]
}

//onMounted表示页面的所有元素都初始化完成
onMounted(()=>{
//基于准备好的dom，初始化echarts实例
  const myChart = echarts.init(document.getElementById('main'))
//使用刚指定的配置项和数据显示图标
  myChart.setOption(option)

  const myChart1 = echarts.init(document.getElementById('line'))
//使用刚指定的配置项和数据显示图标
  myChart1.setOption(option1)

})

</script>

<style scoped>

</style>
```

#### 动态Echarts：

##### 后端接口：

###### bar柱状图：员工数量

 webcontroller

**关于stream流**

**重难点在后端接口的逻辑实现：**

```java
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
```

前端逻辑：

```vue
onMounted(()=>{
//基于准备好的dom，初始化echarts实例
  const barChart = echarts.init(document.getElementById('bar'))
  request.get('/barData').then(res=>{
    barOption.xAxis.data = res.data.department //横轴数据
    barOption.series[0].data = res.data.count     //纵轴数据
    //使用刚指定的配置项和数据显示图标
    barChart.setOption(barOption)
    console.log(res)
  })
})
```

**柱状图设置不同的颜色：在data中添加**

```vue
itemStyle: {
  normal:{
    color: function () {
      return '#'+Math.floor(Math.random()*(256*256-19)).toString(16);
    }
  }
```

###### line折线图：文章发布时间

```java
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
```

此时需要新增有关文章的一系列接口 方法 和sql查询

分别在ArticleService和ArticelMapper中添加

###### pie饼图：各部门员工数量

```java
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
```

##### **前端页面：**

```vue
<template>
  <div>
   <div style="display: flex; grid-gap: 10px;justify-content: flex-start; margin-bottom:10px ">
     <div class="card" style="padding: 20px; width: 50%;height: 400px" id="bar"></div>
     <div class="card" style="padding: 20px; width: 50%;height: 400px" id="line"></div>
   </div>

    <div style="display: flex; grid-gap: 10px;margin-bottom:10px ">
      <div class="card" style="padding: 20px; width: calc(50% - 45px);height: 400px" id="pie"></div>
    </div>
<!--    另一种对齐的布局方式-->
<!--    <el-row :gutter="10">-->
<!--      <el-clo :span="12" style="margin-bottom: 10px">-->
<!--        <div class="card" style="padding: 20px; height: 400px" id="bar"></div>-->
<!--      </el-clo>-->
<!--      <el-clo :span="12" style="margin-bottom: 10px">-->
<!--        <div class="card" style="padding: 20px; height: 400px" id="line"></div>-->
<!--      </el-clo>-->
<!--      <el-clo :span="12" style="margin-bottom: 10px">-->
<!--        <div class="card" style="padding: 20px; height: 400px" id="pie"></div>-->
<!--      </el-clo>-->
<!--    </el-row>-->


  </div>
</template>


<script setup>
import {reactive,onMounted} from "vue";
import * as echarts from "echarts"
import request from "@/utils/request.js";

const  data = reactive({


})
const barOption ={
  title: {
    text: '各部门员工数量'
  },
  tooltip: {},
  legend: {
    trigger:'item'
  },
  xAxis: {
    data: []
  },
  yAxis: {},
  series: [
    {
      name: '人数',
      type: 'bar',
      data: [],
      itemStyle: {
        normal:{
          color: function () {
            return '#'+Math.floor(Math.random()*(256*256-19)).toString(16);
          }
        }
      }
    },
  ]
};
const lineOption ={
  title: {
    text: '近七天发布文章数量'
  },
  tooltip: {},
  legend: {
    trigger:'item'
  },
  xAxis: {
    data: []
  },
  yAxis: {},
  series: [
    {
      name: '发布数量',
      type: 'line',
      data: [],
      smooth:true
    },
  ]
};

const pieOption ={
  title: {
    text: '各部门员工数量比例图',
    left: 'center'
  },
  tooltip: {
    trigger: 'item'
  },
  legend: {
    orient: 'vertical',
    left: 'left'
  },
  series: [
    {
      name: '员工数量',
      type: 'pie',
      radius: '50%',
      data: [],
      center:['50%','60%'],
      label:{
        formatter: '{b}: {@2012} ({d}%)'
      },
      emphasis: {
        itemStyle: {
          shadowBlur: 10,
          shadowOffsetX: 0,
          shadowColor: 'rgba(0, 0, 0, 0.5)'
        }
      }
    }
  ]
}


onMounted(()=>{
//基于准备好的dom，初始化echarts实例
  const barChart = echarts.init(document.getElementById('bar'))
  request.get('/barData').then(res=>{
    barOption.xAxis.data = res.data.department //横轴数据
    barOption.series[0].data = res.data.count     //纵轴数据
    //使用刚指定的配置项和数据显示图标
    barChart.setOption(barOption)
    console.log(res)
  })
  const lineChart = echarts.init(document.getElementById('line'))
  request.get('/lineData').then(res=>{
    lineOption.xAxis.data = res.data.date
    lineOption.series[0].data = res.data.count
    lineChart.setOption(lineOption)
    console.log(res)
  })
  const pieChart = echarts.init(document.getElementById('pie'))
  request.get('/pieData').then(res=>{
    pieOption.series[0].data = res.data
    pieChart.setOption(pieOption)
    console.log(res)
  })

})

</script>

<style scoped>

</style>
```

# 项目二：











