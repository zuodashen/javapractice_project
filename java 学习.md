# JAVA学习 

# 环境配置

下载配置JDK21   配置path 设置默认java_home   配置IDEA   配置： 配置maven![截屏2025-02-27 14.07.04](/Users/ruixianli/Library/Application Support/typora-user-images/截屏2025-02-27 14.07.04.png)

创建vue项目
![create vue](/Users/ruixianli/Desktop/jie/project1的proceed screen/create vue.png)



# 重点语法：

List map（hashmap） set 多线程反射 异常处理   动态代理   JVM虚拟机  泛型    垃圾回收机制

IOC AOP   sql查询 增删改查   多线程 高并发

### **1. List、Map、Set**

1. **List、Map、Set的区别是什么？**
   - **List**：有序集合，允许重复元素。
   - **Map**：键值对集合，键不允许重复。
   - **Set**：无序集合，不允许重复元素。
2. **ArrayList和LinkedList的区别是什么？**
   - **ArrayList**：基于数组，查询快（O(1)），增删慢（O(n)）。
   - **LinkedList**：基于链表，增删快（O(1)），查询慢（O(n)）。
3. **HashMap和TreeMap的区别是什么？**
   - **HashMap**：基于哈希表，无序，查询快（O(1)）。
   - **TreeMap**：基于红黑树，有序，查询慢（O(log n)）。

```java
// List示例
List<String> list = new ArrayList<>();
list.add("Java");
list.add("Python");
System.out.println(list.get(0)); // 输出: Java

// Map示例
Map<String, Integer> map = new HashMap<>();
map.put("Java", 1);
map.put("Python", 2);
System.out.println(map.get("Java")); // 输出: 1

// Set示例
Set<String> set = new HashSet<>();
set.add("Java");
set.add("Python");
set.add("Java"); // 重复元素不会被添加
System.out.println(set); // 输出: [Java, Python]
```



------

### **2. 多线程**

1. **如何创建线程？**
   - 继承`Thread`类或实现`Runnable`接口。
2. **synchronized关键字的作用是什么？**
   - 用于实现线程同步，确保同一时刻只有一个线程访问共享资源。
3. **volatile关键字的作用是什么？**
   - 保证变量的可见性，确保多个线程读取到的值是最新的。
4. **什么是线程池？为什么要用线程池？**
   - 线程池用于管理线程，避免频繁创建和销毁线程，提高性能。

```java
// 创建线程
class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("Thread is running");
    }
}
MyThread t1 = new MyThread();
t1.start();

// 线程同步
class Counter {
    private int count = 0;
    public synchronized void increment() {
        count++; // 线程安全的操作
    }
}
// 线程池
ExecutorService executor = Executors.newFixedThreadPool(10);
executor.submit(() -> System.out.println("Task executed"));
executor.shutdown();
```

------

### **3. 反射**

1. **什么是反射？反射的应用场景有哪些？**
   - 反射允许程序在运行时获取类的信息并操作类的属性和方法。
   - 应用场景：动态代理、框架设计（如Spring的IOC）。
2. **如何通过反射获取类的属性和方法？**
   - 使用`Class.forName()`获取类的`Class`对象，然后调用`getMethod()`或`getField()`。

```java
Class<?> clazz = Class.forName("java.lang.String");
Method method = clazz.getMethod("length");
String str = "Hello";
int length = (int) method.invoke(str);
System.out.println(length); // 输出: 5
```

------

### **4. 异常处理**

1. **Java中的异常分为哪几类？**
   - `Error`（系统错误，不可恢复）和`Exception`（程序异常，可恢复）。
2. **try-catch-finally的执行顺序是什么？**
   - `try` -> `catch` -> `finally`。
3. **什么是自定义异常？**
   - 继承`Exception`或`RuntimeException`，用于定义特定业务逻辑的异常。

```java
try {
    int result = 10 / 0; // 可能抛出ArithmeticException
} catch (ArithmeticException e) {
    System.out.println("Cannot divide by zero"); // 捕获异常
} finally {
    System.out.println("This will always execute"); // 无论是否异常都会执行
}
```

------

### **5. 动态代理**

1. **什么是动态代理？它的应用场景有哪些？**
   - 动态代理允许在运行时创建代理对象，常用于AOP（面向切面编程）。
2. **JDK动态代理和CGLIB动态代理的区别是什么？**
   - JDK动态代理基于接口，CGLIB基于继承。

```java
interface MyInterface {
    void doSomething();
}

class MyInvocationHandler implements InvocationHandler {
    private Object target;
    public MyInvocationHandler(Object target) {
        this.target = target;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Before method call"); // 前置逻辑
        Object result = method.invoke(target, args); // 调用目标方法
        System.out.println("After method call"); // 后置逻辑
        return result;
    }
}

MyInterface proxy = (MyInterface) Proxy.newProxyInstance(
    MyInterface.class.getClassLoader(),
    new Class<?>[] { MyInterface.class },
    new MyInvocationHandler(new MyInterfaceImpl())
);
proxy.doSomething();
```

------

### **6. JVM虚拟机**

1. **JVM的内存结构是怎样的？**
   - 方法区、堆、栈、本地方法栈、程序计数器。
2. **什么是垃圾回收？常见的垃圾回收算法有哪些？**
   - 垃圾回收是JVM自动管理内存的机制。
   - 常见算法：标记-清除、复制、标记-整理、分代收集。

```java
Object obj = new Object(); // 创建一个对象
obj = null; // 对象不再被引用
System.gc(); // 建议JVM进行垃圾回收
```

------

### **7. 泛型**

1. **什么是泛型？泛型的作用是什么？**
   - 泛型允许在编译时进行类型检查，避免类型转换错误。
2. **泛型擦除是什么？**
   - Java的泛型在编译后会擦除类型信息。

```java
class Box<T> {
    private T item;
    public void setItem(T item) {
        this.item = item;
    }
    public T getItem() {
        return item;
    }
}

Box<String> box = new Box<>();
box.setItem("Hello");
System.out.println(box.getItem()); // 输出: Hello
```

------

### **8. Spring的IOC和AOP**

1. **什么是IOC？什么是AOP？**
   - **IOC**：控制反转，Spring容器负责对象的创建和依赖注入。
   - **AOP**：面向切面编程，通过切面在方法执行前后插入逻辑。
2. **Spring如何实现依赖注入？**
   - 使用`@Autowired`注解或XML配置。

```java
@Component // 标记为Spring组件
class MyService {
    public void doSomething() {
        System.out.println("Doing something");
    }
}

@Aspect // 标记为切面
@Component
class LoggingAspect {
    @Before("execution(* com.example.MyService.doSomething(..))") // 前置通知
    public void logBefore() {
        System.out.println("Before method execution");
    }
}
```

------

### **9. SQL查询**

1. **如何优化SQL查询？**
   - 使用索引、避免`SELECT *`、减少子查询等。
2. **什么是索引？索引的作用是什么？**
   - 索引用于加速查询，但会增加插入、更新、删除的开销。

```java
CREATE INDEX idx_name ON users (name);
SELECT * FROM users WHERE name = 'Alice';
```

------

### **10. 多线程高并发**

1. **如何处理高并发场景？**
   - 使用线程池、并发集合、锁机制等。
2. **什么是死锁？如何避免死锁？**
   - 死锁是多个线程互相等待对方释放锁，导致程序无法继续执行。
   - 避免方法：按顺序获取锁、设置超时时间等。

```java
ExecutorService executor = Executors.newFixedThreadPool(10); // 创建线程池
executor.submit(() -> System.out.println("Task executed")); // 提交任务
executor.shutdown(); // 关闭线程池
```

### 11.父类能不能重写子类的静态方法？

父类不能重写子类的静态方法。静态方法属于类本身，而不是类的实例，因此它们不参与继承和多态。子类可以定义与父类同名的静态方法，但这实际上是隐藏（hide）了父类的静态方法，而不是重写（override）。只有实例方法可以被重写，且重写是基于运行时对象类型的。

### 项目创建

建项目->建立模块->建包->建类

下载插件通义千问



#### 导入模块：

file新建 从现存的源代码中添加，或者直接新建模块后再把代码 过来

#### 常用快捷键：

ctrl+alt+L 自动格式化为标准代码  ![截屏2025-02-26 15.47.26](/Users/ruixianli/Library/Application Support/typora-user-images/截屏2025-02-26 15.47.26.png)

设置自动导入包 在setting里面找auto 勾选 自动fly

： 直接出来for循环

mac中：command+b 可以快速进入该方法

mac中：shift+alt+enter 快速补全方法

windows中是 ctrl+b

mac中 command +N 是快速补全属性中的 set get方法   windows中是用 alt+insert 

mac中 command+alt+t快速补充 try catch

alt+enter键生成try catch

## 基础语法：

方法重载,return提前返回(卫语言风格)

### 数组：

静态初始化数组: 

```java
String[] names= {     }  String[] names={"小明","小红","小刚","小花","小章","小龙"};
```

动态初始化数组:

```java
double[] scores=new double[8];
```

二维数组：

```java
String[][] classroom = {
                {"张三","李四","王五"},
                {"赵六","孙七","周八"},
                {"吴九","郑十","冯十一"}
        };
int [][]arr=new int[3][4];
```

### 面向对象编程：

万物皆对象，谁的数据谁存储

对象是一种特殊的数据结构。对象是用类new出来的，有了类就可以创建出对象。

### 封装：

把数据和对数据的处理放到同一个类中去

### 类的基本语法：

#### 构造器：

不能写返回值类型，名称必须是类名

例如：Student s1 =new Student()

创建对象时，对象会去调用无参构造器

Student s2 = new Student("：")

创建对象时，对象会去调用有参构造器

无参数：

有参数：构造器可以重载

常见的应用场景：创建对象时，同时完成对对象成员变量初始化赋值



```java
public Student (Stgring name ,int age ,char sex){
  this.name =name ;
  this.age  =age ;
  this.sex =sex;
}
Student s1 = new Student ("xingxing",18,'男')
```

类默认自带无参构造器，但是写了有参就要写无参。

#### this关键字：

this就是一个变量，可以用在方法中，来拿到当前对象

```java
public class Student {
    public void print(){
        //哪个对象调用这个方法，this就拿到哪个对象
        System.out.println(this);
    }
}
```

this主要来解决变量名称冲突的问题：

```java
public void printHobby(String name){
    System.out.println(this.name + "喜欢" +name);
}

Student s2 = new Student();
        s2.name = "赵丽颖";
        s2.printHobby("唱歌");


```

用this.name防止与printhobby中的name冲突

#### 封装：

合理隐藏/合理暴露

如何合理隐藏:使用private修饰关键字成员变量

合理暴露：使用public修饰的get和set方法合理暴露

```java
private String name;
private int age;
private double chinese;
private double math;
public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
```

#### JavaBean:

是一种特殊类，也称作实体类

类中全部成员变量为私有，并提供公有的set 和 get   类中要求一个无参数的构造器

**实体类在实际开发中的应用场景：**

实体类的对象只负责数据存取，对数据的业务处理交给其他类的对象来完成

#### static关键字：

修饰成员变量和成员方法

##### static修饰成员变量：

有static修饰，属于类，在计算机中只有一份

无static修饰，属于每个对象的

同一个类中访问静态成员可以省略类名不写

![static关键字](/Users/ruixianli/Desktop/JavaPractice project/javapractice_project/javastudy_image/static关键字.png)

##### **static修饰方法：**

有static修饰的成员方法属于类，可以用**类名访问**：（静态方法）

例如：Student.printHelloworld();



无static修饰的成员方法属于对象 ，**实例方法**只能拿**对象**来访问

例如：

Student s1 = new Student();

S1.prinHelloworld();



如果这个方法只是为了做一个功能且不需要直接访问对象的数据，这个方法直接定义为静态方法

如果这个方法是对象的行为，需要访问对象的数据，这个方法必须定义为实例方法



**静态方法的应用：**
工具类中的方法都是一些静态方法，每个方法来完成一个功能，以便给开发人员直接使用

```java
package com.xingxing.staticDemo;


public class VerifyCodeUtil {

    public static String getCode(String[] args) {
        //写一个验证码程序
        String code = "";
        for (int i = 0; i < 4; i++) {
            int num = (int) (Math.random() * 10);
            code += num;
        }
        return code;
    }
}

//在其他的类中就可以直接应用
String Code = VerifyCodeUtil.getCode(4);
System.out.println(Code);
```

工具类没有创建对象的需求，建议将工具类中的构造器进行私有

例如在VerifyCodeutil中加入 :

private class VerifyCOdeUtil( ){       }

##### static注意事项：

静态方法中可以直接访问静态成员，不可以直接访问实例成员

```java
public class Test4 {
    //静态变量
    public static int count = 100;
    //静态方法
    public static void printhello(){
        System.out.println("hello");
    }
    //实例变量，属于对象的。
    private String name;
    public void run(){
        
    }
    //    静态方法中可以直接访问静态成员，不可以直接访问实例成员
    public static void print(){
        System.out.println(count);
        printhello();
//        System.out.println(name);//报错
//        run(); //报错
    }
```

实例方法中可以直接访问静态成员，也可以直接访问实例成员

```java
public void print2(){
    System.out.println(count);
    printhello();
    System.out.println(name);
    run();
}
```

实例方法中可以出现this关键字，静态方法中不可以出现this关键字

```java
public void print3(){
    System.out.println(this);
}
```

### 继承：

子类可以继承父类的非私有成员

子类对象其实是由子类和父类多张设计图共同创建的对象,所以子类对象是完整的

```java
public class People {
    private String name;
    private char sex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }
```

```java
public class Teacher extends People{
    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    private String skill;
```

```java
public class Test {
        public static void main(String[] args) {
            Teacher t = new Teacher();
            t.setName("张三");
            t.setSkill("java");
            t.setSex('男');
    }
}
```

#### 权限修饰符：

 private：只能本类

缺省：本类，同一个包中的类

protected：本类，同一个包中的类，子孙类中

public：任何位置

```java
package com.xingxing.extendsmodify;

public class Fu {
    private void privateMethod(){
    }
    void method(){

    }
    protected void protectedMethod(){

    }

    public void publicMethod(){

    }

    public static void main(String[] args) {
        Fu fu  = new Fu();
        fu.privateMethod();
        fu.method();
        fu.protectedMethod();
        fu.publicMethod();
    }

}
```

#### 继承后的特点：

单继承：

多层继承：一个类要么直接继承Object 要么默认继承Object

祖宗类：

就近原则：先子类局部范围找，然后子类成员范围找，然后父类成员范围找，如果父类范围还没找到就报错

出现重名时，会先使用本类中的，如果一定要访问父类就用**super**关键字

![单继承](/Users/ruixianli/Desktop/JavaPractice project/javapractice_project/javastudy_image/单继承.png)

#### 方法重写：

子类重写方法去覆盖父类的方法

子类重写父类的方法时，访问权限必须大于或者等于父类该方法的权限

重写的方法返回值类型，必须与被重写方法的返回值类型一样，或者范围更小

私有方法，静态方法不能被重写

```java
class Cat extends Animal{
    @Override //方法重写的校验注解，要求方法名称和形参列表必须与被重写的方法一致，否则报错！
    public void eat(){
        System.out.println("Cat eat");
    }
}
class Animal{
    public void eat(){
        System.out.println("Animal eat");
    }
}
```

重写的规范：声明不变，重新实现

**常见的应用场景：**

子类重写Object类的to String（）方法，以便返回对象的内容

直接输出对象， 默认会调用Object 的tostring方法，返回对象的地址信息！！但我们更想看到对象的内容信息

所以子类需要重写to String方法 可以command + N 找到toString直接生成方法即可

```java
class student {
  private String name;
  private char sex;
  private int age;
  
  @Override
  public String toString(){
    return "Student{name= "+ name +",sex= "+ sex + ",age = " + age + "}""
}
}
```

#### **子类构造器的特点：**

子类构造器都会先调用父类的无参数构造器，再执行自己的构造器 ，可以用super来调用父类的有参构造器super（）需要在第一行

子类构造器可以通过调用父类构造器，把对象中包含父类这部分的数据先初始化赋值，再回来把对象里包含子类这部分的数据也进行初始化赋值

![子类构造器](/Users/ruixianli/Desktop/JavaPractice project/javapractice_project/javastudy_image/子类构造器.png)

子类调用父类构造器的应用场景：
想在test中直接用new Teacher（ “” “” “”）要学会使用super（name，sex）

![子类构造器应用场景](/Users/ruixianli/Desktop/JavaPractice project/javapractice_project/javastudy_image/子类构造器应用场景.png)

私有属性可以被继承，但是不能被访问

##### this调用兄弟构造器：

![this调用兄弟构造器](/Users/ruixianli/Desktop/JavaPractice project/javapractice_project/javastudy_image/this调用兄弟构造器.png)



### 多态：

多态是在继承或者实现情况下的一种现象，表现为对象多态和行为多态

**方法：**编译看左边，运行看右边（编译先看父类是否有该方法，运行要看自己定义的该方法）

**成员变量：**编译看左边，运行也看左边

**多态的前提：**有继承或者实现关系，存在父类引用子类对象；存在方法重写

```java
Animal a1 = new Tortoise();
Animal a1 = new Wolf();
```

**多态的好处：**在多态形式下，右边对象是解耦合的，更便于扩展和维护

定义方法时，使用父类类型的形参，可以接受一切子类对象，扩展性更强，更便利。

```java
public static void go(Animal a){
  System.out.println("run")
	a.run();
}
参数用Animal 之后就可以在方法中写 wolf w=new Wolf();go(w);
```

多态的问题：多态不能调用子类的独有功能

多态下类型转换：用于解决无法调用子类独有功能的问题 

注意事项：

![多态强转的注意事项](/Users/ruixianli/Desktop/JavaPractice project/javapractice_project/javastudy_image/多态强转的注意事项.png)

强转前，java建议 使用**instanceof**关键字，判断当前对象的真实类型，再进行强转

p instanceof Student     

```java
  if(a  instanceof  Tortoise)     
     	Tortoise  t1 = (Tortoise) a 
```

###  final关键字：

可以修饰类，方法，变量

修饰类：该类被称为最终类，特点是不能被继承了       一般来修饰工具类

修饰方法：该方法被称为最终方法，特点是不能被重写了

修饰变量：该变量有且仅能被赋值一次    这个变量今后被称为常量，程序中不能修改了，常量名称建议全部大写

final修饰引用类型的变量，变量存储的地址不能被改变，但地址所指向对象的内容是可以被改变的。

**常量：**使用了static final修饰的成员变量就被称为常量    可以专门public常量 

程序编译后，常量会被“宏替换”：出现常量的地方全部会被替换成其记住的字面量，这样可以保证使用常量和直接用字面量的性能是一样的。

### **单例类：** 

单例设计模式： 确保每个类只能创建一个对象

  写法：把类的构造器私有，定义一个类静态变量记住类唯一的对象，定义一个静态类方法，返回这个类唯一对象

```java
public class A {
  private static A a =new A();
}
//私有构造器确保单例类，对外不能创建太多对象
private A (){
}     //否则可以创建多个对象 A a = new A(); A a2 = new A();
public static A getInstance(){
  return a ;
}
```

**单例有多种形式：**上面是饿汉式单例

懒汉式单例：把类的构造器私有，定义一个静态变量用于存储对象，提供一个静态方法，保证返回的是同一个对象

```java
public class B {
  private static B b;
  private B(){
  }
public static B getObject(){
  if(b==null){
    b = new B();
  }
  return b
}
  }
```

### 枚举类：

不可被继承，枚举类都是继承java.lang.Enum

枚举类第一行只能罗列枚举对象的名称，这些名称本质是常量，其构造器私有，对外不能创建对象

常见应用场景：信息标志 信息分类

```java
public enum Direction {
  UP,DOWN,LEFT,RIGHT
}

public static void move2(Direction direction){
  switch(direction){
    case DIrection.UP:
      System.out.println("向上移动");
       case DOWN:       //switch可以自动知道是否是enum
      System.out.println("向上移动");
  }
}
```

### 抽象类：

关键字：abstract  可以用来修饰类 成员方法 

**抽象类中不一定要有抽象方法，有抽象方法的类必须是抽象类**

**类有的成员：成员变量、方法、构造器，抽象类都可以有**

**抽象类最重要的特点：抽象类不能创建对象，仅作为一种特殊的父类，让子类继承并实现**

**一个类继承抽象类，必须重写完抽象类的全部抽象方法，否则这个类也必须定义成抽象类** ![抽象类](/Users/ruixianli/Desktop/JavaPractice project/javapractice_project/javastudy_image/抽象类.png)

**使用抽象类的好处：**

父类知道每个子类都要做某个行为，但是每个子类要做的情况不一样，父类就定义成抽象方法，交给子类去重写实现。这样设计的抽象类，就是为了更好的实现多态（编译看左边，运行看右边）

```java
Animal.java:
public class Dog extends Animal{
  public abstract void cry();
}
Cat.java:
public class Cat extends Animal{
  @Override
  public void cry() {
    System.cry.println("cat喵喵")
  }
}
Test.java:
public class Test{
  public static void main(String [] args){
    Animal a = new Cat();
    a.cry();
  }
}
```

**模版方法设计模式：**

 **提供一个方法作为完成某类的模版，模版方法封装了每个实现步骤，但允许子类提供**

![抽象类模版方法设计](/Users/ruixianli/Desktop/JavaPractice project/javapractice_project/javastudy_image/抽象类模版方法设计.png)

建议使用final关键字修饰模仿方法

### 接口：

Java提供了一个关键字interface 定义出接口，接口不能创建对象，接口是用来被类实现的，一个类可以实现多个接口，一般情况下implement接口 就要把接口中的方法全部重写，否则这个类必须定义成抽象类

JDK8之前接口只能定义常量和抽象方法

**接口的好处：**

弥补类单继承的不足，一个类同时可以实现多个接口，使类的角色更多，功能更强大

让程序可以面向接口编程，这样程序员就可以灵活方便的切换各种业务实现

实例：   

![接口例子](/Users/ruixianli/Desktop/JavaPractice project/javapractice_project/javastudy_image/接口例子.png)

**JDK8开始，接口新增三种方法：**

![接口新增方法](/Users/ruixianli/Desktop/JavaPractice project/javapractice_project/javastudy_image/接口新增方法.png)

**接口的注意事项：**

```java
接口与接口可以多继承
一个接口可以继承多个接口，如果多个接口存在方法签名冲突，则此时不支持多继承，也不支持多实现
签名冲突：
  interface A {
  void show();
}
interface B {
  String show();
}        String 和 void 冲突
  
一个类继承了父类，又同时实现了接口，如果父类中和接口中有同名的方法，实现类会优先用父类的

一个类实现了多个接口，如果多个接口中存在同名的默认方法，可以不冲突，这个类重写该方法即可
  
```

### 抽象类和接口的区别：

**相同点：**1/都是抽象形式，都可以有抽象方法，都不能创建对象   

2/ 都是派生子类形式：抽象类是被子类继承使用，接口是被实现类实现

3/ 继承抽象类或者实现接口都必须重写完他们的抽象方法，否则自己要成为抽象类或者报错

4/都能支持多态，都能实现解耦合

**不同点：**1/抽象类中可以定义类的全部普通成员，接口只能定义常量和抽象方法（JDK8新增的三种方式）

2/抽象类只能被类单继承，接口可以被类多实现

3/一个类继承抽象类就不能再继承其他类，一个类实现了接口还可以继承其他类或者实现其他接口

4/抽象类体现模版思想，更利于做父类，实现代码的附用性 

5.接口更适合做功能的解耦合：解耦合性更强更灵活

### 代码块：

 静态代码块：有static修饰，属于类，与类一起有限加载，自动执行一次

基本作用：可以完成对类的静态资源的初始化

实例代码块：每次创建对象时，执行实例代码块，并在构造器前执行

基本作用：和构造器一样，都是用来完成对象初始化的，例如：对实例变量进行初始化

### 内部类：

一个类定义在另一个类内部

**成员内部类：**

```java
public class Outer{
  public class Inner{
    private String name;
    //构造器
    public Inner(String name){
      this.name = name ;
    }
    public void show(){
      System.out.println("show")；
    }
  }
}
Outer.Inner oi = new Outer().new Inner();


```

成员内部类可以直接访问外部类的静态成员，也可以直接访问外部类的实例成员

成员内部类的实例方法中，可以直接拿到当前寄生的外部类对象：外部类名.this

**静态内部类：**

有static修饰的内部类，属于外部类**自己持有**，并不是外部类对象

```java
public class Outer{
  public static class Inner{
   
  }
}
创建对象；
Outer.Inner inner = new Outer.Inner();
inner.show();
```

静态内部类中可以直接访问外部类的静态成员，但是不可以直接访问外部类的实例成员（因为外部类无对象）

**局部内部类：**

定义在方法中，代码块中，构造器等执行体中

#### 匿名内部类：

是一种特殊等局部内部类，匿名指的是程序员不需要为这个类声明名字，默认有个隐藏的名字

本质就是一个子类，并且会立即创建出一个子类对象

```java
Animal.java
  public abstract class Animal {
    public abstract void cry();
  }
Test.java
  public class Test{
    public static void main(String [] args){
      Animal a = new Animal(){
        @Override
        public void cry() {
          Syetem.out.println("  ")
        }
      };
      a.cry();
    }
  }
```

**常见使用形式：**

   ```java
   public class void main(String [] args) {
     Swim s1 = new Swim(){     //实现形式
       @Override
       public void swimming (){
   			System.out.println(" 游泳  ")
       }
     };
     
     start (s1);
     swim s2= new Swim(){
       @Override
       public void swimming (){
   			System.out.println(" 2游泳  ")
       }
     };
     start(s2); 
   }
   public static void start (Swim s){
     System.out.println("开始");
     s.swimming();
     System.out.println("结束");
   }
   interface swim{
     void swimming();
   }
   ```

**使用场景：**

调用别人提供的方法实现需求时，这个方法正好可以让我们传输一个匿名内部类给对象使用

用于简化代码

### 函数式编程：

可以用于替代某些匿名内部类对象，从而让程序更简洁，可读性更好

lambda表达式只能替代函数式接口的匿名内部类（函数式接口：只有一个抽象方法的接口）

lambad简化comparator接口的匿名内部类：

```java
Arrays.sort(students, new Comparator<Student>()){
  @Override
  public int compare(Student o1 , Student o2){
    return 01.getAge()-02.getAge();
  }
}
简化为：
Arrays.sort(students, (Student o1 , Student o2) - >{
   return 01.getAge()-02.getAge();
})
```

**方法引用：**

简化lambda

静态方法引用：如果某个lambda表达式里只是调用一个静态方法，并且➡️前后的参数形式一致，就可以使用静态方法引用        类名：：静态方法

实例方法引用： 对象名：：实例方法

特定类型方法引用：如果某个lambda表达式里只是调用一个特定类型的实例方法，并且前面参数列表中的第一个参数是作为方法的主调，后面的所有参数都是作为该实例方法的入参的，则此时就可以使用特定方法的引用

```java
Arrays.sort(names,(o1 , o2)-> o1.compareToIgnoreCase(o2));
```

构造器引用：使用场景：如果某个lambda表达式里只是在创建对象，并且箭头前后参数情况一致，就可以使用构造器引用

### 常用API：

String代表字符串，它的对象可以封装字符串数据，并提供了很多方法完成对字符串的处理

String创建对象的区别：只要是以双引号“   ” 方式写出的字符串对象，会存储到字符串常量池，且相同内容的字符串只存储一份。   通过new方式创建字符串对象，每new一次都会产生一个新的对象放在堆内存中。

判断字符串内容，建议使用string方法提供的equals方法







### 异常：

运行时异常:

```java
public static void show(){
    int[] arr = {1,2,3};
    System.out.println(arr[3]);//运行时异常

    String str = null; //空指针异常
}
```

编译时异常

```java
public static void show2(){
   //编译时异常
    String str = "2024-07-08 11:12:13";
    SimpleDateFormat  sdf = new SimpleDateFormat("yyyy/MM-dd HH:mm:ss");
    Date date = parse(str);//编译时异常
    System.out.println(date);

}
```

#### **异常处理：**

throws

try catch

```java
try(){

}catch(){

e.printStackTrace();

}
```

异常的作用：

定位程序的bug信息

作为方法内部的一种特殊返回值，以便通知上层调用者，方法的执行问题

#### ![有关异常](/Users/ruixianli/Desktop/JavaPractice project/javapractice_project/javastudy_image/有关异常.png)自定义异常：

 自定义时编译异常：

```java
public class AgeIlleagalException extends Exception{
    public AgeIlleagalException(){

    }
    public AgeIlleagalException(String message){
        super(message);
    }
}
```

```java
package com.xingxing.exception;

public class TestExceptionDemo {
    public static void main(String[] args) {
        try {
            saveAge(100);
        } catch (AgeIlleagalException e) {
            throw new RuntimeException(e);
        }
    }
    public static void saveAge(int age) throws AgeIlleagalException {
        if(age<1||age>200){
            throw new AgeIlleagalException("年龄不合法");
        }
        else {
            System.out.println("保存年龄成功");
        }
    }
}
```

 自定义运行时异常：

```java
    public class IllegalRuntimeException extends RuntimeException{
        public IllegalRuntimeException(){

        }
        public IllegalRuntimeException(String message){
            super(message);
        }
    }
```

#### 异常的处理方案：

方案1:底层异常都抛出去给最外层调用者，最外层捕获异常，记录异常，响应合适信息给用户观看。

![异常处理](/Users/ruixianli/Desktop/JavaPractice project/javapractice_project/javastudy_image/异常处理.png)

方案2:最外层捕获异常后尝试重新修复

![尝试修复异常](/Users/ruixianli/Desktop/JavaPractice project/javapractice_project/javastudy_image/尝试修复异常.png)

### 泛型：

定义类 接口 方法时，同时声明了一个或者过个类型变量

称为泛型类，泛型接口，泛型方法，统称为泛型

**作用：**提供了在编译阶段约束所能操作的数据类型，并自动进行检查的能力。这样可以避免强制类型转换，及其可能出现的异常。

例如：当不使用泛型时，list中添加了字符串hello和整型100 但是这样容易出现错误。

```java
ArrayList list = new ArrayList();
list.add("hello");
list.add(100);
```

使用泛型：让arraylist统一类型例如：

```java
ArrayList<String> list = new ArrayList<String>();
```

#### 泛型类：

```java
public class MyArrayList<E> {

    private ArrayList list = new ArrayList();
    public boolean add(E e){
        list.add(e);
        return true;
    }
    public boolean remove(E e ){
        return list.remove(e);
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
```

#### 泛型接口：

```java
自己写泛型接口：
public interface Data<T> {
    void add(T t );
    void delete(T t);
    void update(T t);
    T query(int id);
}
```

```java
package com.xingxing.GenericityDemo3;

public class Teacher implements Data<Teacher> {
    @Override
    public void add(Teacher teacher) {
    }

    @Override
    public void delete(Teacher teacher) {
    }

    @Override
    public void update(Teacher teacher) {
    }

    @Override
    public Teacher query(int id) {
        return null;
    }
}
```

#### 泛型方法 通配符 上下限：

**泛型方法：**

```java
public class GenericDemo4 {
    public static void main(String[] args) {
        //目标：学会定义泛型方法，搞清楚作用

        String[] names = {"张三","李四","王五"};
        printArray(names);
        Student[] students = new Student[3];
        printArray(students);
    }
    public static <T> void printArray(T[] names){
    }
    public static <T> T getMax(T[] arr){ //可以避免强转，送进来什么类型，返回值就是什么类型
        return null;
    }

}
```

**通配符：**

就是“？”   可以在使用泛型的时候代表一切类型；E  T K V是在定义泛型的时候使用。

![通配符](/Users/ruixianli/Desktop/JavaPractice project/javapractice_project/javastudy_image/通配符.png)

**泛型的上下限：**

泛型上限：？ extends Car ： ？能接收的必须是Car或者其子类

泛型下限：？ super Car ： ？能接收的必须是Car或者其父类

```java
例如：
  public static void go(ArrayList<? extends Car> cars){  }
```

#### **泛型支持的类型：**

**泛型不支持基本数据类型，只能支持对象类型**

**泛型擦除**：泛型工作在编译阶段，等编译后泛型就没有用了，所以泛型在编译后都会被擦除,所有类型会恢复成object 类型，object是对象！不是基本数据类型

包装类：例如 int 是 Integer    char 是Character

手工包装：

自动装箱：基本数据类型的数据可以直接变成包装对象的数据，不需要额外做任何事情

自动拆箱：把包装类型的对象直接给基本类型的数据 例如：

```java
ArrayList<Integar> list = new ArayList<>();
list.add(123); //自动装箱
```

**包装类具备的其他功能：**

**把基本类型的数据转成字符串：**

```java
Integer i2 =j;
String rs2 = i2.toString()
```

**把字符串数值转换成对应的基本数据类型：**

```java
String str ="98";
int i1 = Integer.parseint(str);
```

为什么要有包装类：

为了万物皆对象，并且泛型和集合都不支持基本类型，支持包装类

### 集合框架：

集合是一种容器，用来装数据

Collection：单列集合

Map：双列集合



#### Collection：

单列集合

List<E>:ArrayList<E> LinkedList<E>  添加元素有序 可重复 有索引

Set<E>:HashSet<E>  TreeSet<E>  添加的元素无序 不重复 无索引  LinkedHashSet有序

**遍历方式：**

迭代遍历：iterator

```java
ArrayList <String> names = new ArrayListt<>();
names.add( );
Iterator<String> it = names.iterator();
while(it.hasNext()){
  String name = it .next();
  System.out.println(name);
}
```

  for  each遍历：

```java
for (String name : names){
  System.out.println(name);
}
```

Lambda表达式遍历：

```java
names.forEach(new Consumen<String>(){
  @Override
    public void accept(String s){
      System.out.println(s);
    }
  });


names.forEach(s - > System.out.println(s));

names.forEach(System.out::println)
```

**三种遍历方式的区别：**

**遍历集合的同时又存在增删集合元素的行为**时，可能出现业务异常，这种现象称之为并发修改异常问题。

解决办法：1/ 每次删完 i--  2/按索引倒着删除  3用迭代器删除（迭代器中自己的方法 it.remove()）

Iterator删除  用迭代器带的方法删除

```java
Iterator<String> it = list.iterator();
While(it.hasNext()){
	String name =it.next();
	if(name.contains(" ")){
       It.remove();
}
}
```

想要遍历集合并删除，在没有索引的情况下要用迭代器。

有索引可以for循环遍历 每次删完退一步 或者可以倒着遍历

#### List集合：

**有序，可重复，有索引**

```java
List<String> namse = new ArrayList<>();
names.add(" ");
names.add(2, " ");
names.remove(" ")
names.set
names.get
  遍历：
for(int i =0 ; i<names.size(); i++){
  System.out.println(names.get(i));
}
 Iterator<String> it = names.iterator();
shile(it.hasNext()){
  String name = it.next();
  System.out.prinln(name);
}
for(String name : names){
    System.out.println(name);
}
Lambda:
names.forEach(name -> {
    System.out.println(names);
})
```

ArrayList根据索引查询速度快，增删数据效率低



**ArrayList底层原理：**
ArrayList 是 Java 集合框架中的一个类，基于动态数组实现，支持动态扩容和随机访问。以下是其底层原理的详细说明：

1. 底层数据结构
ArrayList 使用一个 Object 类型的数组 elementData 来存储元素，数组大小随元素数量动态调整。
transient Object[] elementData; // 存储元素的数组
2. 初始容量
默认初始容量为 10。
可以通过构造函数指定初始容量。


3. 动态扩容
当数组容量不足时，ArrayList 会自动扩容，通常为原容量的 1.5 倍。
4. 添加元素
add(E e)：在末尾添加元素，时间复杂度为 O(1)。add(int index, E element)：在指定位置插入元素，需要移动后续元素，时间复杂度为 O(n)。

5. 删除元素
remove(int index)：删除指定位置的元素，需要移动后续元素，时间复杂度为 O(n)。

6. 随机访问
ArrayList 支持通过索引快速访问元素，时间复杂度为 O(1)
7. 线程安全性
ArrayList 不是线程安全的。多线程环境下应使用 Collections.synchronizedList 或 CopyOnWriteArrayList。
List<String> synchronizedList = Collections.synchronizedList(new ArrayList<>());
8. 总结
优点：随机访问快，尾部添加元素高效。

缺点：插入和删除元素较慢，非线程安全。

ArrayList 适用于频繁读取、少量写入的场景。

**LinkList底层原理：**

LinkList可以用来设计队列

LinkList可以用来设计栈

1. 底层数据结构
LinkedList 使用双向链表（Doubly Linked List）来存储元素。每个节点（Node）包含三个部分：
数据域：存储当前元素的值。
前驱指针：指向前一个节点。
后继指针：指向后一个节点。

2. 核心属性
LinkedList 有两个核心属性：
first：指向链表的头节点。
last：指向链表的尾节点。

3. 添加元素
（1）在链表尾部添加元素
通过 add(E e) 或 addLast(E e) 方法在链表尾部添加元素。
时间复杂度为 O(1)。
（2）在链表头部添加元素
通过 addFirst(E e) 方法在链表头部添加元素。
时间复杂度为 O(1)
（3）在指定位置插入元素
通过 add(int index, E element) 方法在指定位置插入元素。
需要先遍历链表找到指定位置，时间复杂度为 O(n)。

4. 删除元素
（1）删除头节点
通过 removeFirst() 方法删除头节点。
时间复杂度为 O(1)。
（2）删除尾节点
通过 removeLast() 方法删除尾节点。
时间复杂度为 O(1)。

​      （3）删除指定位置的元素
​       通过 remove(int index) 方法删除指定位置的元素。
​       需要先遍历链表找到指定位置，时间复杂度为 O(n)。

5. 随机访问
通过 get(int index) 方法获取指定位置的元素。
需要遍历链表找到指定位置，时间复杂度为 O(n)。

6. 线程安全性
LinkedList 不是线程安全的。多线程环境下应使用 Collections.synchronizedList 或 CopyOnWriteArrayList。
List<String> synchronizedList = Collections.synchronizedList(new LinkedList<>());
7. 总结
优点：插入和删除操作高效（尤其是头尾操作），适合频繁修改的场景。
缺点：随机访问性能较差，内存占用较高（每个节点需要额外存储前后指针）。
LinkedList 适合用于需要频繁插入和删除的场景（如实现队列或栈），而不适合需要频繁随机访问的场景。

#### Set集合：

Hashset:无序 不重复 无索引

LinkedHashSet: 有序 不重复 无索引

TreeSet:可排序 不重复 无索引

**底层原理：**

Hashset：

哈希值就是一个int类型的随机值，Java中每个对象都有一个哈希值

不同对象的哈希值大概率不想等，但也有可能会相等

基于哈希表存储数据，创建默认长度为16的数组

JDK8开始，当链表长度超过8，并且数组长度大于等于64时，自动将链表转化成红黑树。

JDK8开始的哈希表：数组+链表+红黑树

**Hashset集合元素的去重操作：**

在学生类中重写hashCode（）和equals（）自动生成即可

**LinkedHashSet集合的底层原理：**

基于数组链表红黑树实现，底层基于哈希表，但是，它的每个元素都额外的多了一个双联变的机制记录它前后元素的位置。

**TreeSet：**

对于自定义类型如Student，TreeSet默认是无法直接排序的

解决方案：

1、对象类实现了一个Comparable比较接口，重写compareTo方法，指定大小比较规则

2、public TreeSet （Comparator c)集合自带比较器Compartor对象，指定比较规则

#### Map集合：

双列数据

键不能重复，值可以重复

HashMap<K,V>:无序 不重复 无索引

LinkedHashMap<K,V> 有序 不重复 无索引

TreeMap<K,V> 按照大小默认升序排序  不重复   无索引

Map集合常用的方法：

map.put     map.size. map.isEmpty     map.clear   map.remove   map.containskKey   map.containsValue   map.keySet    map.get    

**Map集合的遍历方式：**

1/键找值：

```java
Map<Integer , String> map = new Hashmap<>();
map.put();
Set<String> keys =map.keySet();
for(String key : keys){
Integer value = map.get(key);
  System.out.println(key + " " + value);
}
```

2/键值对：

```java
Set<Map.Entry<String,Integer>> entries = map.entrySet();
for(Map.Entry<String , Integer> entry : entries){
  String key = entry.getKey();
  Integer value = entry.getValue();
    System.out.println(key + " " + value);
}
```

3/ lambda 遍历

map.forEach实现 匿名内部类

```java
 Map.forEach(new BiConsumen<String , integer>(){
@Override 
 public void accept (String key, integer value){
   System.out.println(key + "=" + value);
 }
});
```

JDK1.8 之前 `HashMap` 由数组+链表组成的，数组是 `HashMap` 的主体，链表则是主要为了解决哈希冲突而存在的（“拉链法”解决冲突）。JDK1.8 以后在解决哈希冲突时有了较大的变化，当链表长度大于阈值（默认为 8）（将链表转换成红黑树前会判断，如果当前数组的长度小于 64，那么会选择先进行数组扩容，而不是转换为红黑树）时，将链表转化为红黑树，以减少搜索时间。

HashMap集合的底层实现原理：

底层数据结构依然是基于哈希表实现的，只是每个键值对元素又额外的多了一个双链表的机制记录元素顺序

### Stream流：

可以用于操作简化集合或者数组的数据    结合了lambda表达式

步骤：先得到集合或者数组的Stream流  然后调用Stream流的方法对数据进行处理   再获取处理结果

 使用传统方案找出姓张的人，名字为3个字的，存入到一个新集合中去

```java
List<String> newlist = new ArrayList<>();
for(String name : list){
  if(name.startsWith("张")&&name.length()==3){
				newlist.add(name);
  }
}
```

使用stream流解决：

```java
List<String>newlist2 = list.stream().filter(s - > s.startsWith("张") .filter*s - > s.length()==3).collect(Collectors.toList())
```

**获取stream流：**

获取集合stream流：

```java
//collection
Collection <String> list = new ArrayList<>();
Stream<String> s1 = list.stream();
//map集合
Map<String , Integer> map = new Hashmap<>();
Stream<String> s2 = map.keySet().stream();
Stream<String> s3 = map.values().stream();
Stream<Map.Entry<String,Integer>> s4 = map.entrySet().Stream();
//获取数组流
String [] names = {   }
Stream<String> s5 = Arrays.stream(names);
//使用of（）拿数组stream流
Stream<String> s7 = Stream.of(" " " " " ")
```



**常用的中间方法**：filter    sorted   limit    skip     distinct     map     concat

```java
list.stream().filter(s -> s.startsWiteh("张")) && s.length() == 3).forEach(System.out::prinln);

scores.stream().sorted.forEach(System.out::println);//默认升序
scores.stream().sorted((21,s2)-> Double.compare(s2,21)).forEach(System.out.println);
//如果希望自定义对象能够去重复，重写对象的hashCode 和 equals 方法

//映射方法，把流上原来的数据拿出来变成新数据又放到流上去
scores.stream().map(s-> (s+10)).forEach(System.out.println);
//合并两个流为一个流
concat
```

**Stream终结方法：**

终结方法  forEach   count   max  min 

```java
Optional<Teacher> max = teachers.stream().max((t1,t2)-> Double.compare(t1.getSalary(), t2.getSalary()));
```

收集stream流：把stream流操作后的结果转回到集合或者数组中去返回

收集到Set集合      收集到数组中去   

收集到Map集合：键是老师名称，值是老师薪水 

```java
Map<String , Double> map =teachers.stream().collect (collectors.toMap(Tearcher::getName, Teacher :: getSalary));
```

### 多线程：

线程是一个程序内部的一条执行流程

消息通信，淘宝等都离不开多线程

#### **创建多线程：**

1⃣️ **继承Thread类**，此子类应该覆盖类Thread等run方法，然后可以分配和启动子类

```java
public class ThreadDemo1 {
    public static void main(String[] args) {
        //3.创建一个线程类对象
        MyThread Thread = new MyThread();
        //4.调用start方法开启线程
        Thread.start();
    }
}
    //1.定义一个子类继承Thread类成为一个线程类
        class MyThread extends Thread{
        //2.重写父类的run方法，编写线程要执行的代码
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName()+"-->"+i);
            }
        }
    }
```

优缺点：编码简单但是线程类已经继承Thread 无法继承其他类

启动线程必须调用start方法

2⃣️ **实现Runnable接口方式**

```java
public class ThreadDemo2 {
    public static void main(String[] args) {
        //目标：创建线程的方式二 Runnable
        //3 创建一个线程任务对象
        Runnable r = new MyRunnable();
        //4 创建一个线程对象，把任务对象传递到线程对象中
        Thread t = new Thread(r);
        //5 调用start方法开启线程
        t.start();
        for (int i = 0; i < 5; i++) {
            System.out.println("主线程执行了"+i);
        }
    }
}
//1 定义一个线程的任务类实现Runnable接口
class MyRunnable implements Runnable{
    //2 重写run方法，编写线程任务代码
    @Override
    public void run() {
        //线程任务
        for (int i = 0; i < 5; i++) {
            System.out.println("Runnable线程任务执行了"+i);
        }
    }
}
```

优缺点：任务类只是实现接口，可以继续继承其他类，实现其他接口，扩展性强 但是需要多一个Runnable对象

如果线程有执行结果是不能直接返回的

**匿名内部类写法：**

```java
public class ThreadDemo2_2 {
    public static void main(String[] args) {
        //目标：使用Runnable接口的匿名内部类来创建
        Runnable r = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    System.out.println("子线程执行了"+i);
                }
            }
        };
        Thread t1 = new Thread(r);
        t1.start();
        for (int i = 0; i < 5; i++) {
            System.out.println("主线程执行了"+i);
        }
    }
}
```

上述代码可以简化：

```java
new Thread(new Runnable() {
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("子线程2执行了"+i);
        }
    }
}).start();
```

函数式接口可以用lambda简化：

```java
new Thread(() ->{
    for (int i = 0; i < 5; i++) {
        System.out.println("子线程3执行了"+i);
    }
}).start();
```

3⃣️：**实现Callable接口**

前两种创建方式都存在一个问题，加入线程执行完毕后有一些数据需要返回，他们重写的run方法均不能直接返回结果

步骤：

定义一个类实现Callable接口，重写call方法，封装要做的事情，和要返回的数据，把Callable类型的对象封装成FutureTask

把线程任务对象交给Thread对象

调用Thread对象的start方法启动线程

线程执行完毕后，通过FutureTask对象的get方法去获取线程任务执行的结果

```java
package com.xingxing.ThreadCreate;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class ThreadDemo3 {
    public static void main(String[] args) {
        //目标：创建线程的方法三： 实现Callable接口

        //3 创建一个Callable接口的实现类对象
        Callable<String> c1 = new MyCallable(100);
        //4 把Callable对象封装成一个真正的线程任务对象FutureTask对象
        FutureTask<String> ft1 = new FutureTask<String>(c1);
        //5 把FutureTask对象作为参数传递到Thread的构造方法中，创建一个Thread对象
        Thread t = new Thread(ft1);
        //6 调用start方法开启线程
        t.start();
    }
}
        //1 定义一个实现类实现Callable接口
class MyCallable implements Callable<String>{
    private int n;
    public MyCallable(int n) {
        this.n = n;
    }
    //2实现call方法，编写线程任务代码
            public String call() throws Exception{
                int sum = 0;
                for (int i = 1; i <= n; i++) {
                    sum += i;
                }
                return "1到"+n+"的和为："+sum;
            }
}
```

优缺点：线程任务类只是实现接口，可以继续继承类和实现接口，扩展性强；可以在线程执行完毕后去获取线程执行的结果   但是编码稍微复杂一些



目标：搞清楚线程常用的方法

![多线程](/Users/ruixianli/Desktop/JavaPractice project/javapractice_project/javastudy_image/多线程.png)

#### **线程安全：**

多个线程同时操作同一个共享资源的时候，可能会出现业务安全问题

模拟场景：

![线程安全模拟](/Users/ruixianli/Desktop/JavaPractice project/javapractice_project/javastudy_image/线程安全模拟.png)

#### **线程同步：**

线程同步是线程安全问题的解决方案

让多个线程先后一次访问共享资源

方案： **加锁**

**同步代码块**：同步锁必须是同一把      synchronized写在方法里面，包裹代码块

不能让锁的范围太大，建议使用共享资源作为锁对象，对于**实例方法建议使用this**作为锁对象

对于静态方法建议使用字节码来作为锁对象



**同步方法：**

把访问共享资源的核心方法（public synchronized void drawMoney）给上锁，以此保证线程安全。

同步代码块锁的范围更小，同步方法锁的范围更大。同步方法的可读性更好



**lock锁：**

lock和unlock       unlock写在finally中

锁对象建议用final修饰，防止被篡改

```java
// 创建一个可重入锁
    private final Lock lock = new ReentrantLock();
    // 共享资源
    private int counter = 0;
    // 使用锁保护的方法
    public void increment() {
        lock.lock(); // 加锁
        try {
            counter++; // 操作共享资源
            System.out.println(Thread.currentThread().getName() + " 增加 counter 到: " + counter);
        } finally {
            lock.unlock(); // 解锁（确保锁一定会被释放）
        }
    }
```



#### 线程池：

线程池就是一个可以复用线程的技术

创建线程池：

1/使用ExecutorService的实现类ThreadPoolExecutor自创建一个线程池对象。

2/使用Executors（线程池工具类）调用方法返回不同的线程池对象

**ThreadPoolExecutor自创建一个线程池**

![线程池参数](/Users/ruixianli/Desktop/JavaPractice project/javapractice_project/javastudy_image/线程池参数.png)

线程池处理Runnable任务：使用ExecutorService的方法           void  execute



什么时候开始创建临时线程：新任务提交时发现核心线程都在忙，任务队列也满了，并且还可以创建临时线程，此时才会创建临时线程

什么时候会拒绝新任务：核心线程和临时线程都在忙，任务队列也满了，新的任务过来的时候才会开始拒绝新任务



处理Callable任务：使用ExecutorService方法     Future<T> submit(Callable<T>command)



**通过Executors工具类创建线程池：**

```java
ExecutorService pool = Executors.newFixedThreadPool(10);
```

Executors工具类是基于什么方式实现的线程池？     通过线程池ExecutorService的实现类：ThreadPoolExecutor



大型的并发系统环境中使用Executors如果不注意可能会出现系统风险



问AI：java中的线程池的核心线程数量和最大线程数量的配置公式是什么样的？



#### **并发和并行：**

**并发**是指多个任务在同一时间段内交替执行，但在任意时刻只有一个任务在执行。
并发是通过任务切换（Context Switching）实现的，给人一种多个任务同时运行的错觉。
适用于多任务共享资源的场景，例如多个线程访问共享变量。
目标是提高资源利用率，使多个任务能够高效地交替执行。
在单核CPU上，通过时间片轮转（Time Slicing）实现并发。
在多核CPU上，并发可以通过多线程实现，但线程可能运行在不同的核心上。



**并行**是指多个任务在同一时刻同时执行。
并行需要多核CPU的支持，每个核心可以独立执行一个任务。
适用于计算密集型任务，例如大规模数据处理、图像渲染等。
目标是提高任务执行速度，通过同时执行多个任务来缩短总时间。
在多核CPU上，通过将任务分配给不同的核心实现并行。
在Java中，可以使用 ForkJoinPool 或 Stream.parallel() 来实现并行任务。



### Java高级技术：

#### 单元测试：

针对最小的功能单元：方法，编写测试代码对其进行正确性测试    测试类中方法的正确性

Junit单元测试框架：

测试方法必须加上@Test注解

单元测试中某个方法测试失败不会影响到其他测试方法的测试



断言测试



#### 反射：

反射就是加载类， 并允许以编程的方式解剖类中的各种成分（成员变量 方法 构造器等）

1/ 反射第一步 加载类，获取类的字节码：Class对象

2/ 获取类的构造器：Constructor对象

3/ 获取类的成员变量 Field对象

4/  获取类的成员方法： Method对象



**1/ 反射第一步 加载类，获取类的字节码：Class对象**

获取Class对象的三种方式：

1 获取类本身 类.class     Class  c1 =  Student.class

2 获取类本身  Class c2 =  Class.forName("com.xingxing.reflect.Student")

3/获取类本身  对象.getClass()    Student  s = new Student   Class c3 = s.getClass();



**2/获取类的成分并对其进行操作**

![反射获取类的成分](/Users/ruixianli/Desktop/JavaPractice project/javapractice_project/javastudy_image/反射获取类的成分.png)

```java
package com.xingxing.reflectDemo;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.concurrent.Callable;

public class reflectDemo {

    @Test
    public void getClassInfo(){
        Class c1 = Dog.class;
        System.out.println(c1.getName());
        System.out.println(c1.getSimpleName());

    }
    @Test
    public void getConstructorInfo(){
        Class c1 = Dog.class;
        // 获取构造器对象
        Constructor[] cons = c1.getDeclaredConstructors();
        for (Constructor con : cons){
            System.out.println(con.getName()+"("+con.getParameterAnnotations()+")");
        }
        // 获取单个构造器
        Constructor con = c1.getDeclaredConstructor(); //无参构造器
        System.out.println(con.getName()+"("+con.getParameterAnnotations()+")");

        Constructor con = c1.getDeclaredConstructor(String.class,int.class); //有参构造器
        System.out.println(con.getName()+"("+con.getParameterAnnotations()+")");

    }
    //获取成员变量的对象并对其进行操作
    @Test
    public void getFieldInfo() throws Exception{
        Class c1 = Dog.class;
        Field[] fields = c1.getDeclaredFields();
        for (Field field :fields){
            System.out.println(field.getName()+"("+field.getType()+")");
        }
        //获取单个成员变量对象
        Field field = c1.getDeclaredField("hobby");
        System.out.println(field.getName()+"("+field.getType()+")" );

        Field field1 = c1.getDeclaredField("age");
        System.out.println(field1.getName()+"("+field1.getType()+")");
    }

}
```

**Class提供了从类中获取成员方法的API** 成员方法的作用依然是执行

![反射获取成员方法](/Users/ruixianli/Desktop/JavaPractice project/javapractice_project/javastudy_image/反射获取成员方法.png)

eat.invoke(d);//唤醒对象d的eat方法执行，相当于d.eat();

**class提供了从类中获取成员变量的方法：**

```java
//获取成员变量的目的依然是取值和赋值
Dog d = new Dog("边牧", 3);
field.setAccessible(true); //突破访问权限
field.set(d,"哈士奇");
System.out.println(d);
```



##### **反射的基本作用：**

可以得到一个类的全部成分然后操作，可以破坏封装性，反射可以绕过泛型的约束

适合做java的框架，基本上主流的框架都会基于反射设计出一些通用的功能。

```java
public class reflectDemo2 {
    public static void saveObject(String[] args) {
        PrintStream ps = new PrintStream(new FileOutputStream("")); //追加写进文件，不能覆盖
        Class c =obj.getClass();
        String simpleName = c.getSimpleName();
        ps.println(simpleName);
        Filed[] fileds = c.getDeclaredFields();
        for (Filed filed :fileds){
            String fieldName = filed.getName();
						field.setAccessible(true); //暴力反射
            object filedValue = field.get(obj);
            ps.println(fieldName+":"+filedValue);

        }
        ps.close(); 
    }
}
```

####  注解：

是JAVA代码中的特殊标记，比如@Override  @Test 作用是：让其他程序根据注解信息来决定怎么执行该程序

自定义注解：

```java
public @interface MyBook {
String name;
int age() default 18;
String[] address() ;
}

@Mybook(name=" ",age =18 ,address = {" " " " })

特殊注解：
public @interface A{
  String value();     //特殊属性value 在使用时如果只有一个value属性，value名称可以不写
}
```

注解本质是一个接口，Java中所有注解都是继承了Annotation接口的

@注解（    ）：其实就是一个实现类对象，实现了该注解以及Annotation接口



**元注解：**   注解注解的注解

@Target 声明被修饰的注解只能在哪些位置使用    约束注解的范围

@Retention 作用是声明注解的保留周期    SOURCE  CLASS RUNTIME（开发常用） 约束注解的存活范围



 **注解的解析：**就是判断类上，方法上，成员变量上是否存在注解，并把注解里的内容给解析出来

Class Method Field Constructor 都实现了AnnotatedElement接口

AnnotatedElement接口提供了解析注解的方法

![注解解析](/Users/ruixianli/Desktop/JavaPractice project/javapractice_project/javastudy_image/注解解析.png)

**注解的应用场景：**

实现junit框架

![注解应用](/Users/ruixianli/Desktop/JavaPractice project/javapractice_project/javastudy_image/注解应用.png)



#### 动态代理：

ProxyUtil.java:

```java
package com.xingxing.proxy;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyUtil {
    public static StarService createProxy(Star s){
        //参数一：用于执行用哪个类加载器去加载生成的代理类
        //参数二：代理类需要实现的接口
        //参数三：指定代理类需要如何去代理
        StarService proxy = (StarService) Proxy.newProxyInstance(ProxyUtil.class.getClassLoader(),
                s.getClass().getInterfaces(), new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        //用来声明代理对象要干的事情
                        String methodName = method.getName();
                        if("sing".equals(methodName)){
                            System.out.println("代理对象开始代理");
                        }else if("dance".equals(methodName)){
                            System.out.println("代理对象开始代理");
                        }
                        Object result =  method.invoke(s,args);
                        return result;
                    }

                });
        return proxy;
    }
}
```

Test.java:

```java
package com.xingxing.proxy;

public class Test {
    public static void main(String[] args) {

        Star star = new Star("章若楠 ");
        //创建一个专属代理对象
        StarService proxy = ProxyUtil.createProxy(star);
        proxy.sing("《我好想你》");
        System.out.println(proxy.dance());
    }
}
```

Star.java:

```java
package com.xingxing.proxy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Star implements StarService{
    private String name;


    @Override
    public void sing (String name ){
        System.out.println(this.name+"在唱歌");
    }
    @Override
    public String dance(){
        System.out.println(this.name+"在跳舞");
        return "thank you!!!";
    }
}
```

StarService.java:

```java
package com.xingxing.proxy;

public interface StarService {
    void sing(String name);
    String dance();
}
```

sing去找invoke  方法名sing给到method   name给到 args数组   然后到准备话筒    之后再在Object result = method.invoke真正触发sing   start



Java提供了Proxy API帮助我们创建代理 



**动态代理解决实际问题：**



## JVM :

### 内存区域详解：

**线程私有的：**

- 程序计数器
- 虚拟机栈
- 本地方法栈

**线程共享的：**

- 堆
- 方法区
- 直接内存 (非运行时数据区的一部分)

参考文档：javaguide的JVM

java对象的创建过程：

Step1:类加载检查

​	虚拟机遇到一条 new 指令时，首先将去检查这个指令的参数是否能在常量池中定位到这个类的符号引用，并且检查这个符号引用代表的类是否已被加载过、解析和初始化过。如果没有，那必须先执行相应的类加载过程。

Step2:分配内存
	在**类加载检查**通过后，接下来虚拟机将为新生对象**分配内存**。对象所需的内存大小在类加载完成后便可确定，为对象分配空间的任务等同于把一块确定大小的内存从 Java 堆中划分出来。**分配方式**有 **“指针碰撞”** 和 **“空闲列表”** 两种，**选择哪种分配方式由 Java 堆是否规整决定，而 Java 堆是否规整又由所采用的垃圾收集器是否带有压缩整理功能决定**。

Step3:初始化零值

​	内存分配完成后，虚拟机需要将分配到的内存空间都初始化为零值（不包括对象头），这一步操作保证了对象的实例字段在 Java 代码中可以不赋初始值就直接使用，程序能访问到这些字段的数据类型所对应的零值。

Step4:设置对象头

​	初始化零值完成之后，**虚拟机要对对象进行必要的设置**，例如这个对象是哪个类的实例、如何才能找到类的元数据信息、对象的哈希码、对象的 GC 分代年龄等信息。 **这些信息存放在对象头中。** 另外，根据虚拟机当前运行状态的不同，如是否启用偏向锁等，对象头会有不同的设置方式。

Step5：执行init方法：

​	在上面工作都完成之后，从虚拟机的视角来看，一个新的对象已经产生了，但从 Java 程序的视角来看，对象创建才刚开始，`<init>` 方法还没有执行，所有的字段都还为零。所以一般来说，执行 new 指令之后会接着执行 `<init>` 方法，把对象按照程序员的意愿进行初始化，这样一个真正可用的对象才算完全产生出来。

### 垃圾回收：

大多数情况下，对象在新生代中 Eden 区分配。当 Eden 区没有足够空间进行分配时，虚拟机将发起一次 Minor GC。Java 堆是垃圾收集器管理的主要区域，因此也被称作 **GC 堆**





#### 如何判断对象可以回收：

**引用计数法：**

给对象中添加一个引用计数器：

- 每当有一个地方引用它，计数器就加 1；
- 当引用失效，计数器就减 1；
- 任何时候计数器为 0 的对象就是不可能再被使用的。

这个方法实现简单，效率高，但是目前主流的虚拟机中并没有选择这个算法来管理内存，其最主要的原因是它很难解决对象之间循环引用的问题。

**可达性分析算法：**

这个算法的基本思想就是通过一系列的称为 **“GC Roots”** 的对象作为起点，从这些节点开始向下搜索，节点所走过的路径称为引用链，当一个对象到 GC Roots 没有任何引用链相连的话，则证明此对象是不可用的，需要被回收。

**哪些对象可以作为 GC Roots 呢？**

- 虚拟机栈(栈帧中的局部变量表)中引用的对象
- 本地方法栈(Native 方法)中引用的对象
- 方法区中类静态属性引用的对象
- 方法区中常量引用的对象
- 所有被同步锁持有的对象
- JNI（Java Native Interface）引用的对象





**四种引用：**

强引用就是 Java 中普通的对象，而软引用、弱引用、虚引用在 JDK 中定义的类分别是 `SoftReference`、`WeakReference`、`PhantomReference`。





#### 垃圾回收算法（垃圾收集算法）：

**标记清除算法**：标记-清除（Mark-and-Sweep）算法分为“标记（Mark）”和“清除（Sweep）”阶段：首先标记出所有不需要回收的对象，在标记完成后统一回收掉所有没有被标记的对象。

1. **效率问题**：标记和清除两个过程效率都不高。
2. **空间问题**：标记清除后会产生大量不连续的内存碎片。

**复制算法：**为了解决标记-清除算法的效率和内存碎片问题，复制（Copying）收集算法出现了。它可以将内存分为大小相同的两块，每次使用其中的一块。当这一块的内存使用完后，就将还存活的对象复制到另一块去，然后再把使用的空间一次清理掉。这样就使每次的内存回收都是对内存区间的一半进行回收。

**标记整理算法**：标记-整理（Mark-and-Compact）算法是根据老年代的特点提出的一种标记算法，标记过程仍然与“标记-清除”算法一样，但后续步骤不是直接对可回收对象回收，而是让所有存活的对象向一端移动，然后直接清理掉端边界以外的内存。

**分代收集算法：**当前虚拟机的垃圾收集都采用分代收集算法，这种算法没有什么新的思想，只是根据对象存活周期的不同将内存分为几块。一般将 Java 堆分为新生代和老年代，这样我们就可以根据各个年代的特点选择合适的垃圾收集算法。比如在新生代中，每次收集都会有大量对象死去，所以可以选择“复制”算法，只需要付出少量对象的复制成本就可以完成每次垃圾收集。而老年代的对象存活几率是比较高的，而且没有额外的空间对它进行分配担保，所以我们必须选择“标记-清除”或“标记-整理”算法进行垃圾收集。

#### 垃圾回收器（垃圾收集器）：

**Serial收集器：**它在进行垃圾收集工作的时候必须暂停其他所有的工作线程（ **"Stop The World"** ），直到它收集结束。**新生代采用标记-复制算法，老年代采用标记-整理算法。**它**简单而高效（与其他收集器的单线程相比）**。Serial 收集器由于没有线程交互的开销，自然可以获得很高的单线程收集效率。Serial 收集器对于运行在 Client 模式下的虚拟机来说是个不错的选择。

**ParNew收集器：**是 Serial 收集器的多线程版本，除了使用多线程进行垃圾收集外，其余行为（控制参数、收集算法、回收策略等等）和 Serial 收集器完全一样。

**Parallel Scavenge收集器：**

Parallel Scavenge 收集器关注点是吞吐量（高效率的利用 CPU）。CMS 等垃圾收集器的关注点更多的是用户线程的停顿时间（提高用户体验）。所谓吞吐量就是 CPU 中用于运行用户代码的时间与 CPU 总消耗时间的比值。 Parallel Scavenge 收集器提供了很多参数供用户找到最合适的停顿时间或最大吞吐量，如果对于收集器运作不太了解，手工优化存在困难的时候，使用 Parallel Scavenge 收集器配合自适应调节策略，把内存管理优化交给虚拟机去完成也是一个不错的选择。

**新生代采用标记-复制算法，老年代采用标记-整理算法。

**Serial Old收集器：**

**Serial 收集器的老年代版本**，它同样是一个单线程收集器。它主要有两大用途：一种用途是在 JDK1.5 以及以前的版本中与 Parallel Scavenge 收集器搭配使用，另一种用途是作为 CMS 收集器的后备方案

**Parallel Old收集器：**

**Parallel Scavenge 收集器的老年代版本**。使用多线程和“标记-整理”算法。在注重吞吐量以及 CPU 资源的场合，都可以优先考虑 Parallel Scavenge 收集器和 Parallel Old 收集器。



**CMS收集器：**

**CMS（Concurrent Mark Sweep）收集器是一种以获取最短回收停顿时间为目标的收集器。它非常符合在注重用户体验的应用上使用。**

**CMS（Concurrent Mark Sweep）收集器是 HotSpot 虚拟机第一款真正意义上的并发收集器，它第一次实现了让垃圾收集线程与用户线程（基本上）同时工作。**

从名字中的**Mark Sweep**这两个词可以看出，CMS 收集器是一种 **“标记-清除”算法**实现的，它的运作过程相比于前面几种垃圾收集器来说更加复杂一些。整个过程分为四个步骤：

- **初始标记：** 短暂停顿，标记直接与 root 相连的对象（根对象）；
- **并发标记：** 同时开启 GC 和用户线程，用一个闭包结构去记录可达对象。但在这个阶段结束，这个闭包结构并不能保证包含当前所有的可达对象。因为用户线程可能会不断的更新引用域，所以 GC 线程无法保证可达性分析的实时性。所以这个算法里会跟踪记录这些发生引用更新的地方。
- **重新标记：** 重新标记阶段就是为了修正并发标记期间因为用户程序继续运行而导致标记产生变动的那一部分对象的标记记录，这个阶段的停顿时间一般会比初始标记阶段的时间稍长，远远比并发标记阶段时间短
- **并发清除：** 开启用户线程，同时 GC 线程开始对未标记的区域做清扫。

缺点：

- **对 CPU 资源敏感；**
- **无法处理浮动垃圾；**
- **它使用的回收算法-“标记-清除”算法会导致收集结束时会有大量空间碎片产生。**

**CMS 垃圾回收器在 Java 9 中已经被标记为过时(deprecated)，并在 Java 14 中被移除。**



**G1收集器：**

**G1 (Garbage-First) 是一款面向服务器的垃圾收集器,主要针对配备多颗处理器及大容量内存的机器. 以极高概率满足 GC 停顿时间要求的同时,还具备高吞吐量性能特征。**

被视为 JDK1.7 中 HotSpot 虚拟机的一个重要进化特征。它具备以下特点：

- **并行与并发**：G1 能充分利用 CPU、多核环境下的硬件优势，使用多个 CPU（CPU 或者 CPU 核心）来缩短 Stop-The-World 停顿时间。部分其他收集器原本需要停顿 Java 线程执行的 GC 动作，G1 收集器仍然可以通过并发的方式让 java 程序继续执行。

- **分代收集**：虽然 G1 可以不需要其他收集器配合就能独立管理整个 GC 堆，但是还是保留了分代的概念。

- **空间整合**：与 CMS 的“标记-清除”算法不同，G1 从整体来看是基于“标记-整理”算法实现的收集器；从局部上来看是基于“标记-复制”算法实现的。

- **可预测的停顿**：这是 G1 相对于 CMS 的另一个大优势，降低停顿时间是 G1 和 CMS 共同的关注点，但 G1 除了追求低停顿外，还能建立可预测的停顿时间模型，能让使用者明确指定在一个长度为 M 毫秒的时间片段内，消耗在垃圾收集上的时间不得超过 N 毫秒。

  

G1 收集器的运作大致分为以下几个步骤：

- **初始标记**： 短暂停顿（Stop-The-World，STW），标记从 GC Roots 可直接引用的对象，即标记所有直接可达的活跃对象
- **并发标记**：与应用并发运行，标记所有可达对象。 这一阶段可能持续较长时间，取决于堆的大小和对象的数量。
- **最终标记**： 短暂停顿（STW），处理并发标记阶段结束后残留的少量未处理的引用变更。
- **筛选回收**：根据标记结果，选择回收价值高的区域，复制存活对象到新区域，回收旧区域内存。这一阶段包含一个或多个停顿（STW），具体取决于回收的复杂度。

**ZGC收集器：**

与 CMS 中的 ParNew 和 G1 类似，ZGC 也采用标记-复制算法，不过 ZGC 对该算法做了重大改进。

ZGC 可以将暂停时间控制在几毫秒以内，且暂停时间不受堆内存大小的影响，出现 Stop The World 的情况会更少，但代价是牺牲了一些吞吐量。ZGC 最大支持 16TB 的堆内存。

ZGC 在 Java11 中引入，处于试验阶段。经过多个版本的迭代，不断的完善和修复问题，ZGC 在 Java15 已经可以正式使用了。



#### 类加载器详解：

- 类加载过程：**加载->连接->初始化**。
- 连接过程又可分为三步：**验证->准备->解析**。

加载是类加载过程的第一步，主要完成下面 3 件事情：

1. 通过全类名获取定义此类的二进制字节流
2. 将字节流所代表的静态存储结构转换为方法区的运行时数据结构
3. 在内存中生成一个代表该类的 `Class` 对象，作为方法区这些数据的访问入口

## 









## JUC：

 **线程是进程划分成的更小的运行单位。线程和进程最大的不同在于基本上各进程是独立的，而各线程则不一定，因为同一进程中的线程极有可能会相互影响。线程执行开销小，但不利于资源的管理和保护；而进程正相反。**

**程序计数器为什么私有？**

程序计数器主要有下面两个作用：

1. 字节码解释器通过改变程序计数器来依次读取指令，从而实现代码的流程控制，如：顺序执行、选择、循环、异常处理。
2. 在多线程的情况下，程序计数器用于记录当前线程执行的位置，从而当线程被切换回来的时候能够知道该线程上次运行到哪儿了。

为什么虚拟机栈和本地方法栈是私有的？

- **虚拟机栈：** 每个 Java 方法在执行之前会创建一个栈帧用于存储局部变量表、操作数栈、常量池引用等信息。从方法调用直至执行完成的过程，就对应着一个栈帧在 Java 虚拟机栈中入栈和出栈的过程。
- **本地方法栈：** 和虚拟机栈所发挥的作用非常相似，区别是：**虚拟机栈为虚拟机执行 Java 方法 （也就是字节码）服务，而本地方法栈则为虚拟机使用到的 Native 方法服务。** 在 HotSpot 虚拟机中和 Java 虚拟机栈合二为一。

所以，为了**保证线程中的局部变量不被别的线程访问到**，虚拟机栈和本地方法栈是线程私有的。

**线程的生命周期和状态？**

NEW: 初始状态，线程被创建出来但没有被调用 `start()` 。

RUNNABLE: 运行状态，线程被调用了 `start()`等待运行的状态。

BLOCKED：阻塞状态，需要等待锁释放。

WAITING：等待状态，表示该线程需要等待其他线程做出一些特定动作（通知或中断）。

TIME_WAITING：超时等待状态，可以在指定的时间后自行返回而不是像 WAITING 那样一直等待。

TERMINATED：终止状态，表示该线程已经运行完毕。



**Threadsleep() 方法和 Objectwait() 方法对比:**

**共同点**：两者都可以暂停线程的执行。

**区别**：

- **`sleep()` 方法没有释放锁，而 `wait()` 方法释放了锁** 。
- `wait()` 通常被用于线程间交互/通信，`sleep()`通常被用于暂停执行。
- `wait()` 方法被调用后，线程不会自动苏醒，需要别的线程调用同一个对象上的 `notify()`或者 `notifyAll()` 方法。`sleep()`方法执行完成后，线程会自动苏醒，或者也可以使用 `wait(long timeout)` 超时后线程会自动苏醒。
- `sleep()` 是 `Thread` 类的静态本地方法，`wait()` 则是 `Object` 类的本地方法。为什么这样设计呢？下一个问题就会聊到。

**死锁：**

​	线程死锁描述的是这样一种情况：多个线程同时被阻塞，它们中的一个或者全部都在等待某个资源被释放。由于线程被无限期地阻塞，因此程序不可能正常终止。

符合产生死锁的四个必要条件：

1. **互斥条件**：该资源任意一个时刻只由一个线程占用。
2. **请求与保持条件**：一个线程因请求资源而阻塞时，对已获得的资源保持不放。
3. **不剥夺条件**：线程已获得的资源在未使用完之前不能被其他线程强行剥夺，只有自己使用完毕后才释放资源。
4. **循环等待条件**：若干线程之间形成一种头尾相接的循环等待资源关系。



**线程池处理任务的流程？**

如果当前运行的线程数小于核心线程数，那么就会新建一个线程来执行任务。

如果当前运行的线程数等于或大于核心线程数，但是小于最大线程数，那么就把该任务放入到任务队列里等待执行。

如果向任务队列投放任务失败（任务队列已经满了），但是当前运行的线程数是小于最大线程数的，就新建一个线程来执行任务。

如果当前运行的线程数已经等同于最大线程数了，新建线程将会使当前运行的线程超出最大线程数，那么当前任务会被拒绝，拒绝策略会调用`RejectedExecutionHandler.rejectedExecution()`方法。











## Redis：

数据库和缓存的一致性问题？

同时有大量请求时，数据库压力会变大，因此使用缓存

先更新库再更新缓存：不行

先更新缓存再更新库：不行

先删除缓存再更新库：不行

先更新库再删除缓存：有概率可以





























## SSM Springboot：

### SSM

整合spring MVC文档：https://mp.weixin.qq.com/s/SDxqGu_il3MUCTcN1EYrng

依赖文档   maven  pom.xml 配置

Resources:   springapplication.xml             mybatis-config.xml      管理数据库:database.properties

Mysql8.0需要时区配置

配置数据源交给spring去做



**图书增删改查项目文件夹：**

新建Entity：中新建实体类 Books  pom.xml中导入一下lombook可以偷懒（@Data @AllArgsConstructor @NoArgsCOnstructor 构造器和get set方法）

新建dao层写接口：BookMapper.interface     增删改查

```java
int addBook(Books books);
int deleteBook(Books books);
int updateBook(Books books);
Books queryBookById(int id);
List<Books> queryAllBook();
```

在同层新建mapper.xml    一个mapper对应一个接口  例子可见之前写的后台管理

写完接口后去写业务层：

service层写 BookService.interface 实现增删改查     写BookService的实现类BookService.impl      业务层调dao层



**spring层：**

**实现spring和mybatis的整合**：**（IOC）** 配置spring-dao.xml(关联数据库配置文件（database.properties） ，  数据库连接池（dbcp c3p0（自动化操作） druid  hikari），sqlSessionFactory )  在sqlSessionFactory中绑定Mybatis配置文件，配置dao接口扫描包，动态的实现了Dao接口可以注入到Spring容器中

**spring整合service层：**spring-service.xml.  扫描service下的包，将我们的所有业务类注入到spring，可以通过配置或者注解实现，声明事务配置注入数据源（AOP）

可以通过在springapplication.xml中导入整合spring-dao.xml和spring-service.xml

**spring MVC层：**

增加web application支持，配置web.xml（DisPatchServlet和乱码过滤）   新增spring-mvc.xml.  （注解驱动，静态资源过滤，扫描包，试图解析器）    



**编写controller层：** controller调用service层

学会使用junit进行排错测试



**运行流程：**

用户访问 /book/list，BookController 调用 BookService 查询所有图书。
BookService 调用 BookMapper 执行 SQL 查询。
查询结果返回给 Controller，Controller 将数据传递给视图（JSP）。
视图渲染后返回给用户



### Springboot：

**优点：**

快速构建一个Spring应该程序

嵌入的Tomcat、Jetty或者Undertow 无需部署WAR文件

提供starter POMs来简化Maven配置和减少版本冲突富哦带来的问题

无需配置XML--JavaConfig 无代码生成，开箱急用

springbootDemo:

Maven   包名(group)

可以看到pox.xml中的依赖已经配好   ： springdata-jpa操作数据库  springmvc  mysql连接驱动  单元测试

在user类中 加入@Table 和@entity注解 把类映射成表

**有一种方法可以自动添加数据库表：**

将user类映射为表   user类使用了jpa注解 使得快速创建数据库表

需要有jpa依赖 并且正确配置数据源

还需要在springapplication中配置

```yml
  jpa:
    hibernate:
#自动更新数据库表结构
      ddl-auto: update
      #格式化
    properties:
      hibernate.format_sql: true
      #显示sql
    show-sql: true
```

| `@Entity`                                             | 表示这个类是一个 JPA 实体，会映射到数据库表                  |
| ----------------------------------------------------- | ------------------------------------------------------------ |
| `@Table(name = "tb_user")`                            | 指定映射到数据库的表名（如果不指定，默认使用类名 `User` 作为表名） |
| `@Id`                                                 | 标记该字段是主键                                             |
| `@GeneratedValue(strategy = GenerationType.IDENTITY)` | 主键自增策略（适用于 MySQL 的 `AUTO_INCREMENT`）             |
| `@Column(name = "xxx")`                               | 指定字段映射到数据库的列名（如果不指定，默认使用属性名）     |

```java
package com.xingxing.springboot_demo.pojo;
import jakarta.persistence.*;
@Table(name = "tb_user")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "user_name")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
```



**实现简单增删改查：**

新增usercontroller来写接口：

```java
@RestController  可以使接口方法返回对象  转换成json文本
@RequestMapping("/user") //localhost:9090/user
@RequestBody 将传进来json文本转换成对象
在userservice中：
@Service   //spring的bean
在service中有了@Service注解后
就可以在controller中 用@Autowired注解 将其自动装配过来
@Repository   //数据访问层的Bean

```

controller中写了接口并调用service中的add方法，然后在service中写对应的方法

新增UserService

和IUserService的interface

如果使用JPA只需要在mapper层创建UserMapper接口来继承 CrudRepository即可 不要自己写sql

```java
public interface UserMapper extends CrudRepository<User,Integer> {
}
```

然后可以去到UserService 中通过@Autowired将 UserRepository（usermapper）自动装配进来

然后去调用mapper中的save方法    这里需要先用BeanUtils拷贝对象，把UserDto中user拷贝到userPojo



流程：测试当前controller中的接口，然后去请求业务逻辑层的add方法，再通过业务逻辑层的方法将Dto中的数据拷贝pojo对象中去，再通过save方法进行新增数据

```java
UserService.java：
@Service   //spring的bean
public class UserService implements IUserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public User add(UserDto user) {
        User userPojo = new User();

        BeanUtils.copyProperties(user,userPojo);

        return userMapper.save(userPojo);
    }
}
```

新建ResponseMessage：

```java
package com.xingxing.springboot_demo.pojo;

import org.springframework.http.HttpStatus;

public class ResponseMessage<T> {
    private Integer code;
    private String message;
    private T data;

    public ResponseMessage(Integer code,String message,T data){
        this.code = code;
        this.message = message;
        this.data = data;
    }
    //接口请求成功
    public static <T> ResponseMessage<T> success (T data){
        return new ResponseMessage<>(HttpStatus.OK.value(), "success", data);
    }


}
```

在Controller中调用ResponMessage.success

```java
@PostMapping("/add")     //URL: localhost:9090/user/add
public ResponseMessage<User> add(@RequestBody UserDto user){
    User userNew = userService.add(user);
    return ResponseMessage.success(userNew);
}
```

参数校验依赖：

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```

username不允许为空时，在UserDto中使用注解 @NotBlank（"用户名不能为空"）去除空格       //@NotEmpty

关于验证validation的注解可以自己再看看     然后再在需要验证参数的地方 UserController里面添加相应注解@Validated

**加入一个统一的异常处理**

GlobalExceptionHandlerAdvice.java

```java
package com.xingxing.springboot_demo.exception;

import com.xingxing.springboot_demo.pojo.ResponseMessage;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice   //统一处理
public class GlobalExceptionHandlerAdvice {

    Logger log = LoggerFactory.getLogger(GlobalExceptionHandlerAdvice.class);
    @ExceptionHandler({Exception.class}) //什么异常的统一处理
    public ResponseMessage handelerException(Exception e, HttpServletResponse request,HttpServletResponse response){
        //记录日志
        log.error("统一异常",e);
        return new ResponseMessage(500,"error",null);
    }
}
```

查询功能是一样的流程：

修改功能一样流程：但要在UserDto中加入id



















## Maven：

管理和构建java的工具 

依赖管理：无需手动导入jar包  只需在pom.xml中导入依赖即可   依赖具有传递性

<parent>标签下是springboot的父工程

统一项目结构：main和test

项目构建：标准跨平台的自动化项目构建方式

**关于springboot项目maven配置，可以参考 practice project中的maven配置**

生命周期：clean负责清理工作     default：核心工作 如 编译 测试 打包 安装 部署  site：生成报告，发布站点



## Web开发：

**SpringBootWeb:**

新建springboot项目时，勾选springweb相关依赖

定义HelloController类
```java
@RestController    //请求处理类
public class HelloController{
  @RequestMapping("/hello")    //请求hello地址将调用下面方法
  public String hello(){
		System.out.println("Hello World!");
    return "Hello World!";
  }
}
```

### HTTP协议：

超文本传输协议，规定了浏览器和服务器之间数据传输的规则

基于TCP协议：面向连接，安全      一次请求对应一次响应

HTTP协议是无状态协议，对于事务没有记忆能力

#### **HTTP请求数据格式：**

**1.请求行（Request Line）**

位于第一行，包含三个部分：

- **请求方法**：如 `GET`、`POST`、`PUT`、`DELETE` 等。
- **请求目标**：通常是URL的路径部分（如 `/index.html`）或完整URL（代理场景）。
- **HTTP版本**：如 `HTTP/1.1` 或 `HTTP/2`。

```
GET /api/users?id=123 HTTP/1.1
```

------

**2. 请求头（Request Headers）**

紧接请求行，以键值对形式传递附加信息，每行一个头字段。常见头字段包括：

- **Host**：服务器域名（必需，HTTP/1.1起强制）。
- **User-Agent**：客户端标识（如浏览器类型）。
- **Accept**：客户端接受的响应类型（如 `application/json`）。
- **Content-Type**：请求体的数据类型（如 `application/json`、`application/x-www-form-urlencoded`）。
- **Content-Length**：请求体的字节数（POST/PUT时需要）。
- **Authorization**：认证信息（如Bearer Token）。

**示例**：

```
Host: example.com
User-Agent: Mozilla/5.0
Accept: application/json
Content-Type: application/x-www-form-urlencoded
Content-Length: 27
```

------

**3. 空行（Empty Line）**

请求头结束后需有一个空行（`\r\n`），用于分隔头部和请求体。

------

**4. 请求体（Request Body）**

**可选部分**，通常用于 `POST`、`PUT` 等方法，传递表单数据、JSON、文件等。

- **表单数据**：

  ```
  username=admin&password=123
  ```

- **JSON数据**：

  ```
  {"username": "admin", "password": "123"}
  ```

- **文件上传**：使用 `multipart/form-data` 格式。

------

**完整HTTP请求示例**

**GET请求（无请求体）**

```
GET /search?q=http HTTP/1.1
Host: www.google.com
User-Agent: Mozilla/5.0
Accept: text/html
```

**POST请求（带JSON体）**

```
POST /api/login HTTP/1.1
Host: example.com
Content-Type: application/json
Content-Length: 42

{"username": "admin", "password": "123456"}
```

**POST表单提交**

```
POST /submit-form HTTP/1.1
Host: example.com
Content-Type: application/x-www-form-urlencoded
Content-Length: 27

username=admin&password=123
```

------

**注意事项**

1. **GET请求**的参数通常通过URL的查询字符串（`?key=value`）传递，无请求体。
2. **POST/PUT** 需明确指定 `Content-Type` 和 `Content-Length`。
3. **空格和换行**必须使用 `\r\n`（CRLF），不可省略空行。

#### HTTP响应格式：

**1. 状态行（Status Line）**

位于第一行，包含三个部分：

- **HTTP版本**：如 `HTTP/1.1` 或 `HTTP/2`。
- **状态码（Status Code）**：3位数字，表示请求结果（如 `200` 表示成功）。
- **状态文本（Status Text）**：对状态码的简短描述（如 `OK`）。

**示例**：

```
HTTP/1.1 200 OK
```

**常见状态码**

| 状态码 | 含义                    |
| :----- | :---------------------- |
| 200    | OK（请求成功）          |
| 301    | 永久重定向              |
| 404    | Not Found（资源不存在） |
| 500    | 服务器内部错误          |

------

**2. 响应头（Response Headers）**

紧接状态行，以键值对形式传递元数据，每行一个头字段。常见头字段包括：

- **Content-Type**：响应体的数据类型（如 `text/html`、`application/json`）。
- **Content-Length**：响应体的字节数。
- **Server**：服务器软件信息（如 `Apache/2.4.41`）。
- **Set-Cookie**：向客户端设置Cookie。
- **Cache-Control**：缓存控制（如 `max-age=3600`）。
- **Location**：重定向目标URL（用于3xx状态码）。

**示例**：

```
Content-Type: application/json
Content-Length: 29
Server: nginx/1.18.0
Cache-Control: no-cache
```

------

**3. 空行（Empty Line）**

响应头结束后需有一个空行（`\r\n`），用于分隔头部和响应体。

------

**4. 响应体（Response Body）**

包含服务器返回的实际数据，格式由 `Content-Type` 指定。

- **HTML响应**：

  ```
  <html><body>Hello World!</body></html>
  ```

- **JSON响应**：

  ```
  {"status": "success", "data": {}}
  ```

- **二进制数据**：如图片、文件下载等。

------

**完整HTTP响应示例**

**成功响应（JSON数据）**

```
HTTP/1.1 200 OK
Content-Type: application/json
Content-Length: 29
Server: nginx

{"status": "success", "data": {}}
```

**重定向响应（301）**

```
HTTP/1.1 301 Moved Permanently
Location: https://new.example.com
Content-Length: 0
```

**错误响应（404 Not Found）**

```
HTTP/1.1 404 Not Found
Content-Type: text/html
Content-Length: 48

<html><body>Page not found!</body></html>
```

------

**注意事项**

1. **状态码分类**：
   - `1xx`：信息性状态码（如 `101` 协议切换）。
   - `2xx`：成功（如 `200 OK`、`201 Created`）。
   - `3xx`：重定向（如 `301`、`302`）。
   - `4xx`：客户端错误（如 `400 Bad Request`、`403 Forbidden`）。
   - `5xx`：服务器错误（如 `500 Internal Server Error`）。
2. **空行必须存在**：头部和响应体之间需用 `\r\n` 分隔。
3. **Content-Type**：若未指定，客户端可能无法正确解析响应体（如JSON被当作文本显示）。

#### HTTP协议解析：

CORS（跨源资源共享）：通过特定头部实现跨域请求
CSP（内容安全策略）：限制资源加载来源
HSTS（HTTP严格传输安全）：强制使用HTTPS
SameSite Cookie：防止CSRF攻击

HTTPS = HTTP + SSL/TLS加密层，提供：
加密 - 防止数据被窃听
认证 - 验证通信对方身份
完整性保护 - 防止数据被篡改 

#### Tomcat：

轻量级Web服务器，支持servlet  和jsp等少量javaee规范

springbootweb：

内嵌tomcat服务器



请求响应：BS架构    CS架构  

### 前后端请求响应：

**获取请求参数：**

前后端分离开发通过postman来进行接口测试

 原始的web程序中获取请求参数，需要通过HttpServletRequest对象手动获取:

```java
@RequestMapping("/simpleParam")
public String simpleParam(HttpServletRequest request){
  String name = request.getParameter("name");
  return "OK";
}
```

在Springboot方式中使用简单参数：请求的参数名和controller中的形参变量名相同，定义形参即可接收参数（接收过程中会自动进行类型转换）

```java
@RequestMapping("/simpleParam")
public String simpleParam(String name, Integer age){
   System.out.prinln(name +":"+ age)
   return "OK"
}
```

简单参数：如果方法形参名称和请求参数名称不匹配，可以使用@RequestParam完成映射

Required 默认为true必须传递该参数

```java
@RequestMapping("/simpleParam")
public String simpleParam(@RequestParam(name = "name",required=false)String name, Integer age){
   System.out.prinln(name +":"+ age)
   return "OK"
}
```



**实体参数接收：**

**简单实体对象：请求参数名与形参对象属性名相同，定义POJO接收即可**

```java
创建实体类
public class User{
  private String name;
  private Integer age;
  
  getset方法和tostring方法
}

controller:
@RequestMapping("/simplePojo")
public String simplePojo(User user){
  System.out.prinln(user);
  return "OK";
}
```

复杂实体参数：

```java
public class User{
  private String name;
  private Integer age;
  private Address address;
  getset方法和tostring方法
}
public class Address{
  private String province;
  private String city;
}
controller:
@RequestMapping("/complexPojo")
public String complexPojo(User user){
  System.out.prinln(user);
  return "OK";
}
```

**数组集合参数：**

数组：

```java
@RequestMapping("/arrayParam")
public String arrayParam(String[] hobby){
  System.out.prinln(Arrays.toString(hobby));
  return "OK";
}
```

集合：

```java
@RequestMapping("/listParam")
public String listParam(@RequestParam List<String> hobby){
  System.out.prinln(hobby);
  return "OK";
}
```

日期参数：

```java
@RequestMapping("/dateParam")
public String dateParam(@DateTimeFormat(pattern="yyyy-MM-dd:ss")LocalDateTime updateTime){
  System.out.prinln(updateTime);
  return "OK";
}
```

Json参数：

JSON数据的键名与形参对象属性名相同，定义POJO类型形餐即可接收参数，需要使用@RequestBody标识

```java
@RequestMapping("/jsonParam")
public String jsonParam(@RequestBody User user){
  System.out.prinln(user); 
  return "OK";
}
//RequestBody将json转换为请求体
```

路径参数：

```java
 @RequestMapping("/path/{id}")
public String pathParam(@Pathvariable Integer id){
  System.out.prinln(id); 
  return "OK";
}
```

多个路径参数：

```java
@RequestMapping("/path/{id}/{name}")
public String pathParam2(@Pathvariable Integer id，@PathVariable String name){
  System.out.prinln(id); 
  System.out.prinln(name); 
  return "OK";
}
```



**响应：**

@RequestBody.  类型：方法注解 类注解      位置：Controller方法上  作用：将方法返回值直接响应，如果返回值类型是实体对象或集合，将会转换为JSON格式响应      

@RestContoller = @ResponseBody+@Controller

**为了使得前端人员拿到我的接口后，得到统一的返回数据：**

**统一响应结果：**使用result

```java
public class Result {
  private Integer code;
  private String msg;
  private Object data;
  get and set 方法
  有参无参构造器
  toString方法
  还有一些静态方法：
  public static Result success(Object data){
    return new Reulst(1,"success",data) 
  }  
  public static Result success(){
    return new Reulst(1,"success",null)
  }  
  public static Result error(String msg){
    return new Reulst(0,"msg",null)
  }  
  
}
```

导入统一响应的Result类之后，可以修改Controller中的方法类型为Result了

```java
@RequestMapping("/hello")
public Result hello(){
  System.out.println("Hello world~");
  return new Result(1,"success","Hello world~");
  return Result.success("Hello world~")
}


@RequestMapping("/getAddr")
public Result getAddr(){
  Address addr = new Address();
  addr.setProvince("广东");
  addr.setCity("深圳")；
  return Result.success(addr);
}
```

案例：



### **分层解耦(三层架构)：**

**三层架构：**复用性强，便于维护，利于拓展

Controller：接收请求 响应数据

Service：业务逻辑

Dao：数据访问

Dao:

```java
public interface EmpDao {
  public List<Emp> listEmp();
}
```

Daoimpl ：

```java
public class EmpDaoA implements EmpDao(){
  @Override
  public List<Emp> listEmp(){
    String file =  this.getClass().getClassLoader().getResource("emp.xml").getFile();
    System.out.println(file);
    List<Emp> empList = XmlParserUtils.parse(file,Emp.class);
    return empList;
  }
}
```

EmpService:

```java
public interface EmpService {
  public List<Emp> listEmp();
}
```

EmpServiceimpl:

```java
public Class EmpServiceA implements EmpService{
  private EmpDao empDao = new EmpDaoA();
  @Override
  public List<Emp> lsitEmp(){
  list<Emp> empList = empDao.listEmp();
    
  empList.stream().forEach(emp-> {
    String gender = emp.getGender();
    if("1".equals(gender)){
      emp.setGender("男 ");
    }
    else if ("2".equals(gender)){
      emp.setGender("女")
    }
  })
  }
}
```

Controller:

```java
@RestController
public class EmpController{
  private EmpService empService = new EmpServiceA();
  @RequestMapping("/listMap")
  public Result list(){
    List<Emp> empList = empService.listEmp();
    return Result.success(empList);
  }
}
```

软件设计原则：**高内聚低耦合**

内聚：软件中各个功能模块内部的功能联系

耦合：衡量软件中各个层或模块之间的依赖 关联的程度



#### **解耦：**

控制反转IOC：对象创建的控制权由程序自身转移到外部容器

依赖注入DI：容器为英语哦那个程序提供运行时，所依赖的资源，称之为依赖注入

Bean对象：IOC容器中创建管理的对象称之为Bean

将上述三层架构中的代码进行修改： 将Serivice层及Dao层的实现类交给IOC容器管理

在service和dao中添加注解 **@Component**  *使其加入IOC容器*     成为IOC容器的Bean

在service和controller中添加 **@Autowired**注解  进行自动装配



要把某个对象交给IOC容器管理，需要在对应的类上加上如下注解：

@Component  

**@Controller @Service @Repository**



**Bean组件扫描：**

**@ComponentScan**注解虽然没有显示配置，但是默认扫描当前包和子包   

一般情况下应该将所有放在启动类所在包中



如果有多个Service Bean 中添加了 @Service注解  有以下解决方案：

@Primary @Qualifier @Resource



















































## Mysql:

如何优化满Sql：参考javaguideMysql高性能优化

加索引，常用查询条件和连接条件的列上建立索引，利用覆盖索引，适当使用前缀索引，正确使用联合索引避免过多的列使用复合索引   更新频繁的列慎用索引

分解复杂查询，批量插入

避免select *     避免子查询使用JOIN代替      避免使用OR查询，使用UNION或UNION ALL代替     避免！=  或<>操作符，使用IN代替         避免使用%开头的LIKE查询     避免列上函数计算      



**约束：**非空约束      唯一约束    主键约束   默认约束   外键约束

修改表：alter table tb_emp add qq varchar(11) comment 'QQ ' 

修改：modify  change   删除： drop    修改表名：rename table tb_emp to emp



**DML：**

插入：insert into  更新：update set   删除：delete from where     Delete语句不能删除某个字段的值，如果要操作可以用update将该字段的值置为NULL

**DQL：**

关键字：select  

基本查询    select distinct        select 1 as 2 from 

条件查询  where   

聚合函数 不对null值计算  统计数量：count    min max avg sum

分组查询  group by.      select gender, count(*) from tb group by gender  select后为分组字段和聚合函数

分组之后的过滤需要加上having

排序查询  order by 默认升序

分页查询  limit   select * from tb limit 10，5     



**多表设计：**

一对多：一个部门对应多个员工    员工表添加外键，使得部门的主键id和员工的dept_id关联

一对一关系：在任意一方添加外键 关联另一方的主键    设置外键为唯一unique 添加unique字段唯一约束 一个用户对应一个身份证

多对多：建立第三张中间表，中间表至少要包含两个外键，分别关联两方主键



**多表查询：**

**内连接：**

隐式内连接   select tb1.name tb2.name from tb1,tb2 where tb1.id = tb2.id     

显式内连接：select tb1.name tb2.name from tb1 inner join tb2 on tb1.id = tb2.id     

可以给表起别名

**外连接**:

左外连接：完全包含左表的数据  select e.name , d.name from **tb_emp e** left join tb_dept d on e.dept_id =d.id

右外连接：

**子查询**:

标量子查询：select * from tb1 where dept_id = (select id from tb_dept where name = '教研部')

列子查询:select * from tb1 where dept_id in (select id from tb_dept where name='1'or name='2') 一列多行

行子查询:select * from tb_emp where (entrydate,job) = (select entrydate,job from tb_emp where name = '1')

表子查询：select e.*,d.name from (select * from tb_emp where entrydata > ' 2006-01-01') e , tb_dept d where e.dept_id = d.id;



**事务：**

默认MySQL的事务是默认提交的

开启事务：start transaction ； /begin；

提交事务：commit

回滚事务：rollback

事务的四大特性：

原子性 一致性  隔离性   持久性



**索引：**

提高搜索执行效率

create index idx_sku_sn on tb_sku(sn)

**介绍：**索引是帮助数据库高效获取数据的数据结构  

通过索引列对数据进行排序，降低数据排序的成本，降低CPU消耗

索引会占用存储空间，提高了查询效率但是也降低了insert update delete的效率

**索引结构：**默认是用B+Tree结构组织的索引    多路平衡搜索树

每一个节点可以存储多个key 所有的数据都存储在叶子节点，非叶子节点仅用于索引数据。叶子节点形成了一颗双向链表，便于数据的排序以及区间范围查询

操作语法：创建：create index  idx_tab1 on tab1(name)   查询：show index from tb1  删除：drop index idx_tab1 on tb1;

主键字段，在建表时，会自动创建主键索引       添加唯一约束时，数据库实际上会添加唯一索引



## **MyBatis：**

1 准备工作，创建springboot工程，数据库表 user 实体类User

2 引入Mybatis 的相关依赖，配置Mybatis

3 编写SQ L语句（注解/XML）

Mapper.interface

```java
@Mapper
public interface UserMapper{
  @Select("select * from user")
	public List<User> list();
}
```

Test：

```java
@SpringBootTest
class SpringbootMybatisQuickstartApplicationTest{
  @Autowired
  private UserMapper userMapper;
  @Test
  public void test(){
    List<User> userList = userMapper.list();
    userList.stream().forEach(user ->{
      System.out.println(user);
    });
  }
}
```



**IntelliJ IDEA中可以配置SQL提示，并且在DataBase中创建数据库连接**



**数据库连接池：**

是一个容器，负责分配 管理数据库连接          

优势：资源重用，提升系统响应速度，避免数据库连接遗漏

标准接口：DataSource     产品：C3P0 DBCP Druid Hikari

**Lombok：**

@Data 提供了更综合的生成代码功能    （@Getter + @Setter+@ToString + @EqualsAndHashCode）

@NoArgsConstructor 为实体类生成无参的构造器方法

@AllArgsConstructor 为实体类生成有参的构造器方法



### **MyBatis基于注解基础操作：**

```java
@Mapper
public interface EmpMapper {
  @Delete("delete from  emp where id =#{id}")
  public void delete(Integer id);
  
  //插入数据
  @Insert("insert into emp(username,name,gender,image,job)" + "values (#{username},#{name},#{gender},#{image},#{job})" )    //value中填写的是对象中的属性名字 需要用驼峰形式
  public void insert(Emp emp)；
  
   //更新修改
   @update("update emp set username = #{username} name= #{name} gender = #{gender} where id =#{id}")
   public void update(Emp emp);
  
  //查询操作 根据ID查询
  @select("select * from emp where id = #{id}")
  public Emp getById(Integer id)
    
    
    
}
```

预编译SQL 性能更高  更安全（防止SQL注入）

#{ } 执行SQL时 会将#{  } 替换为  ？ 生成预编译SQL，会自动设置参数值    使用时机：参数传递，都使用#{   }

${   } 拼接SQL 直接将参数拼接在SQL语句中，存在SQL注入问题  使用时机：如果对表名 列表进行动态设置时使用 



**SQL注入：**通过操作输入的数据来修改实现定义好的SQL语句，以达到执行代码对服务器进行攻击的方法

有关新增操作中的**主键返回**：需要添加

@Options（KeyProperty = "id",useGeneratedKeys = true )



**查询操作**中

数据封装，实体类属性名和数据库表查询返回的字段名一致，mybatis会自动封装   如果实体类属性名和数据库表查询返回的字段名不一致，不能自动封装

1⃣️可以给字段起别名

 ```java
  @Select("select id ,username,password,name,gender,image,job,entrydate,"+ "dept_id deptId,create_time createTime,update_time updateTime from emp where id = #{id}")
 public Emp getById(Integer id);
 ```

2⃣️通过@Results 注解手动映射封装

```java
@Results({
  @Result(column = "dept_id",property = "deptId"),
})
```

3⃣️开启Mybatis的驼峰命名自动映射开关  即从数据库字段名a_column映射到Java属性名aColumn 

在application.properties 中配置  mybatis.configuration.map-uderscore-to-camel-case = true



条件查询：

```java
@Select("select * from emp where name like '%${name}%' and gender = #{gender} ")
public List<Emp> list(String name, short gender,LocalDate begin,LocalDate=end);
// #不能出现在引号之内
concat字符串拼接函数解决    like concat('%',#{name},'%')

```



### Mybatis XML映射文件：

规范：XML映射文件的名称与Mapper接口名称一致，并且将XML映射文件和Mapper接口放置在相同包下（同包同名）       XML映射文件的namespace属性为Mapper接口全限定名一致       XML映射文件中的sql语句的id与Mapper接口中的方法名一致，并保持返回类型一致

```java
<mapper namespace = "com.sprintboot.example.mapper.EmpMapper">
	<select id="list" resultType = "com.sprintboot.example.entity.Emp">  
  // id和mapper.java中的方法名一致    resultType是单条记录所封装的类型 与实体类Emp一样
  select * from emp where name like concat('%',#{name},'%')
  </select>
  
</mapper>
```



**动态SQL：**

 动态sql标签    if   用于判断条件是否成立

```java
<if test = "name != null">
   name like concat('%',#{name},'%')
</if>
```

**<where></where>****标签**    判断其中包含的标签是否成立 例如<if>标签  会自动去除子句开头的AND或OR

  动态更新员工功能，如果更新时传递有值，则更新。如果更新没有传递值，则不更新，运用动态SQL

```java
//动态更新
 <update id="update">
   update emp
   <set> 
   <if test="username != null">username = #{username},</if>
       <if test = "name != null"> name = #{name}</if>
   </set>
   where id = #{id}
 </update>
```

<set>标签会自动删除多余的逗号



**<foreach>标签**

批量删除：collection :遍历的集合   item:遍历出来的元素   separator：分隔符  open：遍历开始前拼接的SQL片段   close：遍历结束后拼接的SQL片段

```java
Mapper.java
public void deleteByIds(Lsit<Integer> ids);
Mapper.xml
<delete id = "deleteByIds">
  delete from emp where id in
  <foreach collection="ids" item="id" separator="," open="(" close = ")">
  	#{id}
  </foreach>
</delete>
    
Test.java
@Test
public void testDeleteByIds(){
    List<Integer> ids = Arrays.asList(13,14,15);
    empMapper.deleteByIds(ids);
  }
```

**<sql>和<include>标签**

<sql> 标签用于定义可重用的 SQL 片段，可以在多个地方引用。这样可以避免重复编写相同的 SQL 片段，提高代码的可维护性。<include> 标签用于引用已定义的 <sql> 片段，通过 refid 属性引用 sql 片段的 id。 

```xml
<!-- 定义可重用的列名列表 -->
<sql id="userColumns">
    id, username, email, create_time
</sql>
<!-- 在查询中引用 -->
<select id="selectAllUsers" resultType="User">
    SELECT 
    <include refid="userColumns"/>
    FROM users
</select>

<select id="selectUserById" resultType="User">
    SELECT 
    <include refid="userColumns"/>
    FROM users
    WHERE id = #{id}
</select>
```



























## sql语句：

**CURD:**

插入语句：

```sql
#插入一行

INSERT INTO user
VALUES(10,'root','root','190446')

INSERT INTO user
VALUES(10,'root','root','190889'),(12,'user1','user1','root213')

#插入行的一部分
INSERT INTO user(username,password,email)
VALUES('admin','admin','190446542')

#插入查询出来的数据
INSERT INTO user(username)
SELECT name
FROM account;

#更新数据
UPDATE user 
SET username = 'robot',password = 'robot'
WHERE usernmae = 'robot'

#删除数据
#delete 语句用于删除表中的记录  truncate table 可以清空表，也就是删除所有行
DELETE FROM user
WHERE username = 'robot';
TRUNCATE TABLE user;

#查询数据

查询单列
SELECT prod_name
FROM products
查询多列：
SELECT prod_id,prod_name,prod_price
FROM products
查询所有列：
SELECT *
FROM products
查询不同的值：
SELECT DISTINCT 
vend_id from products;
限制查询结果：
SELECT * FROM mytable LIMIT 5;
SELECT * FROM mytable LIMIT 0,5;

#排序
ASC:升序 DESC：降序
SELECT * FROM products
ORDER BY prod)price DESC, prod_name ASC;

#分组
SELECT cust_name ,COUNT(cust_address) AS addr_num
FROM Customers GROUP BY cust_name;

SELECT cust_name , COUNT(cust_address) AS addr_num
FROM Customers GROUP BY cust_name
ORDER BY cust_name DESC;

having：用于对汇总的group by 结果进行过滤    一般都是和group by连用 

```

- 截取函数`SUBSTRING()`：截取字符串，`substring(str ,n ,m)`（n 表示起始截取位置，m 表示要截取的字符个数）表示返回字符串 str 从第 n 个字符开始截取 m 个字符；
- 拼接函数`CONCAT()`：将两个或多个字符串连接成一个字符串，select concat(A,B)：连接字符串 A 和 B。
- 大写函数 `UPPER()`：将指定字符串转换为大写。



```sql
返回 2020 年 1 月的所有订单的订单号（order_num）和订单日期（order_date），并按订单日期升序排序
SELECT order_num, order_date
FROM Orders
WHERE month(order_date) = '01' AND YEAR(order_date) = '2020'
ORDER BY order_date

SELECT order_num, order_date
FROM Orders
WHERE order_date LIKE '2020-01%'
ORDER BY order_date
```



`GROUP BY` 通常还涉及聚合`COUNT`，`MAX`，`SUM`，`AVG` 等。

`GROUP BY` 可以按一列或多列进行分组。

`GROUP BY` 按分组字段进行排序后，`ORDER BY` 可以以汇总字段来进行排序。

`WHERE`：过滤指定的行，后面不能加聚合函数（分组函数）。

`HAVING`：过滤分组，必须要与 `GROUP BY` 连用，不能单独使用。



**子查询：**

子查询是嵌套在较大查询中的 SQL 查询，也称内部查询或内部选择，包含子查询的语句也称为外部查询或外部选择。简单来说，子查询就是指将一个 `SELECT` 查询（子查询）的结果作为另一个 SQL 语句（主查询）的数据来源或者判断条件。

子查询常用在 `WHERE` 子句和 `FROM` 子句后边：

- 当用于 `WHERE` 子句时，根据不同的运算符，子查询可以返回单行单列、多行单列、单行多列数据。子查询就是要返回能够作为 WHERE 子句查询条件的值。
- 当用于 `FROM` 子句时，一般返回多行多列数据，相当于返回一张临时表，这样才符合 `FROM` 后面是表的规则。这种做法能够实现多表联合查询。

```sql
OrderItems` 表示订单商品表，含有字段订单号：`order_num`、订单价格：`item_price`；`Orders` 表代表订单信息表，含有顾客 `id：cust_id` 和订单号：`order_num
使用子查询，返回购买价格为 10 美元或以上产品的顾客列表，结果无需排序。
SELECT cust_id
FROM Orders
WHERE order_num IN (SELECT DISTINCT order_num
    FROM OrderItems
    where item_price >= 10)
    

编写 SQL 语句，使用子查询来确定哪些订单（在 OrderItems 中）购买了 prod_id 为 "BR01" 的产品，然后从 Orders 表中返回每个产品对应的顾客 ID（cust_id）和订单日期（order_date），按订购日期对结果进行升序排序。

# 写法 1：子查询
SELECT cust_id,order_date
FROM Orders
WHERE order_num IN
    (SELECT order_num
     FROM OrderItems
     WHERE prod_id = 'BR01' )
ORDER BY order_date;

# 写法 2: 连接表
SELECT b.cust_id, b.order_date
FROM OrderItems a,Orders b
WHERE a.order_num = b.order_num AND a.prod_id = 'BR01'
ORDER BY order_dat


三个表嵌套两次：
子查询
SELECT cust_email
FROM Customers
WHERE cust_id IN (SELECT cust_id
    FROM Orders
    WHERE order_num IN (SELECT order_num
        FROM OrderItems
        WHERE prod_id = 'BR01'))
内连接：
SELECT cust_email
FROM Customers
INNER JOIN Orders using(cust_id)
INNER JOIN OrderItems using(order_num)
WHERE OrderItems.prod_id = 'BR01'

编写 SQL 语句，从 Products 表中检索所有的产品名称（prod_name），以及名为 quant_sold 的计算列，其中包含所售产品的总数（在 OrderItems 表上使用子查询和 SUM(quantity) 检索）。
# 写法 1：子查询
SELECT p.prod_name, tb.quant_sold
FROM (SELECT prod_id, Sum(quantity) AS quant_sold
    FROM OrderItems
    GROUP BY prod_id) AS tb,
  Products p
WHERE tb.prod_id = p.prod_id

# 写法 2：连接表
SELECT p.prod_name, Sum(o.quantity) AS quant_sold
FROM Products p,
  OrderItems o
WHERE p.prod_id = o.prod_id
GROUP BY p.prod_name（这里不能用 p.prod_id，会报错）
```



**连接表：**

JOIN 是“连接”的意思，顾名思义，SQL JOIN 子句用于将两个或者多个表联合起来进行查询。

连接表时需要在每个表中选择一个字段，并对这些字段的值进行比较，值相同的两条记录将合并为一条。**连接表的本质就是将不同表的记录合并起来，形成一张新表。当然，这张新表只是临时的，它仅存在于本次查询期间**。

语法：

```sql
SELECT table1.column1, table2.column2...
FROM table1
JOIN table2
ON table1.common_column1 = table2.common_column2;
```

**`ON` 和 `WHERE` 的区别**：

- 连接表时，SQL 会根据连接条件生成一张新的临时表。`ON` 就是连接条件，它决定临时表的生成。
- `WHERE` 是在临时表生成以后，再对临时表中的数据进行过滤，生成最终的结果集，这个时候已经没有 JOIN-ON 了。

INNER JOIN 内连接（默认连接方式）只有当两个表都存在满足条件的记录时才会返回行。

LEFT JOIN / LEFT OUTER JOIN 左(外)连接返回左表中的所有行，即使右表中没有满足条件的行也是如此。

RIGHT JOIN / RIGHT OUTER JOIN 右(外)连接返回右表中的所有行，即使左表中没有满足条件的行也是如此。FULL JOIN / FULL OUTER JOIN 全(外)连接只要其中有一个表存在满足条件的记录，就返回行。

SELF JOIN将一个表连接到自身，就像该表是两个表一样。为了区分两个表，在 SQL 语句中需要至少重命名一个表。

CROSS JOIN交叉连接，从两个或者多个连接表中返回记录集的笛卡尔积。



```sql
除了返回顾客名称和订单号，返回 Customers 表中的顾客名称（cust_name）和 Orders 表中的相关订单号（order_num），添加第三列 OrderTotal，其中包含每个订单的总价，并按顾客名称再按订单号对结果进行升序排序。

# 简单的等连接语法
SELECT c.cust_name, o.order_num, SUM(quantity * item_price) AS OrderTotal
FROM Customers c,Orders o,OrderItems oi
WHERE c.cust_id = o.cust_id AND o.order_num = oi.order_num
GROUP BY c.cust_name, o.order_num
ORDER BY c.cust_name, o.order_num
```



**组合查询：**

`UNION` 运算符将两个或更多查询的结果组合起来，并生成一个结果集，其中包含来自 `UNION` 中参与查询的提取行。

`UNION` 基本规则：

- 所有查询的列数和列顺序必须相同。
- 每个查询中涉及表的列的数据类型必须相同或兼容。
- 通常返回的列名取自第一个查询。

默认地，`UNION` 操作符选取不同的值。如果允许重复的值，请使用 `UNION ALL`。

```
SELECT column_name(s) FROM table1
UNION ALL
SELECT column_name(s) FROM table2;
```

`UNION` 结果集中的列名总是等于 `UNION` 中第一个 `SELECT` 语句中的列名。

`JOIN` vs `UNION`：

- `JOIN` 中连接表的列可能不同，但在 `UNION` 中，所有查询的列数和列顺序必须相同。
- `UNION` 将查询之后的行放在一起（垂直放置），但 `JOIN` 将查询之后的列放在一起（水平放置），即它构成一个笛卡尔积。





# 开发小案例：

## 案例一：

**部门管理：**

controller:

```java
@Slf4j
@RestController
public class DeptController{
  @Autowired
  private DeptService deptService;
  @GetMapping("/depts")
  public Result list(){
    log.info("查询全部部门数据")；
      
    List<Dept> depList = deptService.list();
    return Result.success(deptList);
  }
  
  @DeleteMapping("/depts/{id}")
  public Result delete(@Pathvariable Integer id){
    log.info("根据id删除部门：{}",id);
    
    deptService.delete(id);
    return Result.success();
  }
  
  @PostMapping("/depts")
  public Result add(@RequestBody Dept dept){
    log.info("新增部门：{}",dept);
    deptService.add(dept);
    return Result.success();
  }
  
}
```

发现Get Delete 和 Post中有公共路径参数 depts   **此时可以将其抽取到类上面@RequestMapping("/depts")**

serviceimpl：

```java
@Service
public class DeptServiceImpl implements DeptService {
  @Autowired
  private DeptMapper deptMapper;
  
  @Override
  public List<Dept> list(){
    return deptaMapper.list();
  }
  
  @Override
  public void delete(Integer id){
    deptMapper.deleteById(id);
  }
  
  @Override
  public void add(Dept dept){
    dept.setCreatTime(LocalDateTime.now());
    //补全属性
    mapperService.insert(dept);
  }
}

```

Mapper:

```java
@Mapper
public interface DeptMapper{
  @Select ("select * from dept")
  List<Dept> list();
  
  @Delete("delete from depts where id =#{id}")
  void delteById (Integer id);
  
  @insert("insert into dept(name,Create_Time) Values (#{name},#{createTime})")
  void insert(Dept dept);
}
```

Result:

```java
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result{
  private Integer code;
  private String message;
  private Object data;
  public static Result success(){
    return new Result(1,"success",null);
  }
  public static Result (Object data){
    return new Result(1,"success",data);
  }
  public static Result error(String msg){
    return new Result(0,msg,null);
  }
}

```



**员工管理：**

分页查询： 

pojo新建实体类 pageBean

```java
@Data
@NoArgConstructor
@AllArgConstructor
public class PageBean{
  private Long total;
  private List rows;
}
```

empMapper:

```java
@Mapper
public interface EmpMapper{
  @Select("select count(*) from emp")
  public Long count();
  @Select("select * from emp limit #{start},#{pagesize}") //分页查询获取列表数据
  public List<emp> page(Integer start,Integer pageSize);
}
```

empController:

```java
@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController{
  @Autowired
  private EmpService empService;
  public Result page(@RequestParam (defaultvalue="1")Integer page,@RequestParam(defaultvalue="10")Integer pageSize){
    log.info("分页查询，参数:{},{}",page,pageSize);
    PageBean pagebean = empService.page(page,pageSize);
    return Result.success(pageBean);
  }
}
```

EmpService:

```java
@Service
public class EmpServiceImpl implements EmpService{
  @Autowired
  private EmpMapper empMapper;
  @Override
  public PageBean page(Integer page,Integer pagesize){
    //获取总记录数
    Long count = empMapper.count();
    //获取分页查询列表
    Integer start = (page - 1 ) * pagesize;
    List<Emp> empList = empMapper.page(start,pagesize);
    //封装PageBean对象
    PageBean pageBean = new PageBean(count,empList);
    returen pageBean;
  }
}
```

**分页查询插件：**pageHelper

引入依赖

```java
mapper:
@Select("select * from emp")
public List<Emp> list();
```

```java
EmpServiceImpl:
@Override
public PageBean page(Integer page,Integer pageSize){
  PageHelper.startPage(page,pagesize);
  
  List<Emp> empList = empMapper.list();
  Page<Emp> p =(page<Emp>) empList;
  
  PageBean pageBean = new PageBean(p.getTotal(),p.getresult());
  return pageBean;
}
```

```java
controller:和上面用sql实现分页的controller一样
@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController{
  @Autowired
  private EmpService empService;
  public Result page(@RequestParam (defaultvalue="1")Integer page,@RequestParam(defaultvalue="10")Integer pageSize){
    log.info("分页查询，参数:{},{}",page,pageSize);
    PageBean pagebean = empService.page(page,pageSize);
    return Result.success(pageBean);
  }
}
```

**条件分页查询：**

```java
@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController{
  @Autowired
  private EmpService empService;
  public Result page(@RequestParam (defaultvalue="1")Integer page,@RequestParam(defaultvalue="10")Integer pageSize,String name,short gender,@DateTimeFormat(pattern="yyyy-MM-dd")LocalDate begin,@DateTimeFormat(pattern="yyyy-MM-dd")LocalDate end){
    log.info("分页查询，参数:{},{},{},{},{},{}",page,pageSize,name,gender,begin,end);
    PageBean pagebean = empService.page(page,pageSize,name,gender,begin,end);
    return Result.success(pageBean);
  }
}
```

```java
service:
@Service
public class EmpServiceImpl implements EmpService{
  @Autowired
  private EmpMapper empMapper;
  @Override
  public PageBean page(Integer page,Integer pagesize,String name,Short gender,LocalDate begin,LocalDate end){
  PageHelper.startPage(page,pagesize);
  
  List<Emp> empList = empMapper.list(name,gender,begin,end);
  Page<Emp> p =(page<Emp>) empList;
  
  PageBean pageBean = new PageBean(p.getTotal(),p.getresult());
  return pageBean;
  }
}
```

```java
mapper:
@Select("select * from emp")
public List<Emp> list(String name,Short gender,LocalDate begin,LocalDate end);
```

动态sql使用 mapper.xml

```xml
<mapper namespace = com.xingxing.mapper.EmpMapper>
  <select id="list" resultType = "com.xingxing.pojo.Emp">
  select * from emp
  <where>
  	<if test="name ! = null and name != ' '" >
    	name like concat('%','#{name}','%')
  	</if>
  	<if test="gender != null">
      and gender = #{gender}
 	  </if>
  	<if test="begin!=null and end!=null">
      and entrydate between #{begin} and #{end}
 		</if>
  </where>
  </select>
 order by update_time desc
</mapper>
```



**删除员工：**

```java
controller:
@DeleteMapping("/emps/{ids}")
public Result delete(@PathVariable List<Integer> ids){
  log.info("批量删除,ids:{}",ids)
  return Result.success();
}
```

```java
serviceimpl:
@Override
public void delete(List<Integer> ids){
  empMapper.delete(ids);
}
```

```xml
mapper.xml:
<delete id="delete">
		delete from emp
    where id in
  <foreach collection="ids" item="id" separator="," open="(" close=")">
    #{id}
  </foreach> 
</delete>
```



























































# 面试准备题目：

**实现一个阻塞队列：**



**@Resource 和 @Autowired的区别：**

@Autowired默认是按照类型注入 基于spring框架 @Resource默认是按照名称注入 基于JDK



#{ } 执行SQL时 会将#{  } 替换为  ？ 生成预编译SQL，会自动设置参数值    使用时机：参数传递，都使用#{   }

${   } 拼接SQL 直接将参数拼接在SQL语句中，存在SQL注入问题  使用时机：如果对表名 列表进行动态设置时使用 















