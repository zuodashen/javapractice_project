#### IntelliJ IDEA安装：

安装教育优惠版，通过学信网认证获得

#### java环境配置

JDK23安装包下载网址：https://www.oracle.com/java 

通过vim打开 zshrc文件 ，并进行相应配置

java下载在最外层资源库中的java中

```mark
打开终端：
vim ~/.zshrc
填入下面设置：
# 设置 JAVA_HOME 环境变量
export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk-17.jdk/Contents/Home
# 将 Java 的可执行文件路径添加到系统的 PATH 环境变量中
export PATH=$JAVA_HOME/bin:$PATH
后输入：使其生效
source ~/.zshrc 
```

#### Nodejs安装

![nodejs安装](/Users/ruihangli/Desktop/工具安装包/nodejs安装.png)

安装网址：https://nodejs.org/

#### Mysql安装

安装网址：https://dev.mysql.com/downloads

```markdown
vim ~/.zshrc
export PATH="/usr/local/mysql/bin:$PATH"
source ~/.zshrc
mysql -u root -p
exit//退出
```

#### navicat安装

如果仍然显示已经损坏，那么打开系统偏好设置，找到隐私与安全性，选择仍要打开即可

Mac 允许任何来源下载

csdn下载链接：https://dongyayun.blog.csdn.net/article/details/86692680?fromshare=blogdetail&sharetype=blogdetail&sharerId=86692680&sharerefer=PC&sharesource=Handsomeha&sharefrom=from_link

参考博客：**https://blog.csdn.net/LuShang7054/article/details/127855602**

**终端输入：sudo spctl --master-disable**

#### 显示隐藏的资源库：

command + shift +   .



**Windows系统：****https://blog.csdn.net/weixin_50670076/article/details/136350060?fromshare=blogdetail&sharetype=blogdetail&sharerId=136350060&sharerefer=PC&sharesource=Handsomeha&sharefrom=from_link**

#### maven配置：

```markdown
打开终端后：输入vim.~/.zshrc 添加以下内容
export MAVEN_HOME=/Library/Java/apache-maven-3.9.9
export PATH=$PATH:$MAVEN_HOME/bin
后输入：           使其生效
source ~/.zshrc 
```

打开 Maven 安装目录下的 `conf` 文件夹，找到 `settings.xml` 文件。配置阿里云镜像:

在conf的settings.xml中加入：

```markdown
<mirror>
      <id>nexus-aliyun</id>
      <mirrorOf>central</mirrorOf>
      <name>Nexus aliyun</name>
      <url>https://maven.aliyun.com/nexus/content/groups/public</url>
</mirror>
```

![image-20250309145528508](/Users/ruihangli/Library/Application Support/typora-user-images/image-20250309145528508.png)

之后就是在IntelliJ IDEA中配置maven

千万别把zshrc中的path写错了



#### typoraWindows破解版：

https://blog.csdn.net/qq_33499889/article/details/104646413?fromshare=blogdetail&sharetype=blogdetail&sharerId=104646413&sharerefer=PC&sharesource=Handsomeha&sharefrom=from_link



#### 推送project 到 github：

##### 1、在本地项目中初始化 Git 仓库（如果尚未初始化）

```markdown
打开终端，使用 cd 命令导航到项目所在的目录打开终端，使用 cd 命令导航到项目所在的目录
cd /path/to/your/project
初始化 Git 仓库：
git init
将项目文件添加到 Git 仓库并提交
将项目中的所有文件添加到暂存区：
git add .
提交暂存区的文件到本地仓库，并添加描述信息：
git commit -m "Initial commit"
```

##### 2、将本地仓库与 GitHub 远程仓库关联并推送

```markdown
获取在第 1 步中创建的 GitHub 仓库的 URL
git remote add origin https://github.com/your_username/your_repository.git
将本地仓库的内容推送到 GitHub 远程仓库：
git push -u origin main
```

推送到远程仓库的时候如果报错了，有可能是因为你的分支叫master  这时候可以把master修改为main

git branch可以查看当前分支

通过： 

```Markdwon
 git branch -m master main
```

这时候如果出现报错：很有可能是因为，从 2021 年 8 月 13 日起，GitHub 移除了对密码认证的支持，因此你不能再使用用户名和密码来进行 HTTPS 认证推送代码了

接下来可以通过配置秘钥来实现

##### 3、配置 SSH 密钥

```BASH
在终端中执行以下命令来生成 SSH 密钥：
ssh-keygen -t rsa -b 4096 -C "your_email@example.com"
生成的密钥文件默认位于 ~/.ssh 目录下。
```

添加密钥到github：

将公钥文件（通常是 `~/.ssh/id_rsa.pub`）的内容复制。然后登录 GitHub，点击右上角头像，选择 “Settings”，在左侧菜单中选择 “SSH and GPG keys”，点击 “New SSH key”，将复制的公钥内容粘贴到 “Key” 字段中，为密钥设置一个标题，最后点击 “Add SSH key”。

```BASH
使用以下命令将远程仓库的 URL 从 HTTPS 切换为 SSH 格式：
git remote set-url origin git@github.com:zuodashen/javapractice_project.git
```

再次尝试推送即可：

```BASH
git push -u origin main
```

##### 4、在另一台设备上git pull和clone

需要现在另一台设备上也新生成密钥并添加到github上面，添加ssh的过程和上面相同

测试SSH连接：

```BASH
ssh -T git@github.com
```

接下来就可以 git clone ‘对应的url’    

##### 多设备项目实时更新：

**本地设备修改项目内容**

当你在某一台设备上对项目文件进行了修改、添加或删除操作后，需要将这些更改提交到本地仓库，然后推送到 GitHub 远程仓库。以下是具体步骤：

**查看文件状态**：使用 `git status` 命令查看哪些文件被修改、添加或删除了。

```bash
git status**添加更改到暂存区**：

使用 `git add` 命令将你想要提交的文件添加到暂存区。如果你想添加所有更改的文件，可以使用以下命令：
```

```bash
git add .
```

**提交更改到本地仓库**：使用 `git commit` 命令将暂存区的更改提交到本地仓库，并添加有意义的提交信息，描述本次更改的内容。

```bash
git commit -m "描述本次更改的内容，例如：添加了新的功能模块"
```

**推送到远程仓库**：使用 `git push` 命令将本地仓库的更改推送到 GitHub 远程仓库。

```bash
git push origin main
```

这里的 `origin` 是远程仓库的默认名称，`main` 是主分支的名称。如果你的项目使用其他分支名称，需要相应地进行修改。

**另一台设备更新项目内容**

在另一台设备上，为了获取最新的项目内容，你需要从 GitHub 远程仓库拉取最新的更改。以下是具体步骤：

**切换到项目目录**：使用 `cd` 命令进入克隆项目的目录。

```bash
cd javapractice_project
```

**拉取远程仓库的最新更改**：使用 `git pull` 命令从 GitHub 远程仓库拉取最新的更改，并合并到本地仓库。

```bash
git pull origin main
```

这样，另一台设备上的项目内容就会更新为最新状态。



