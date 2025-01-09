# 项目说明
## 1、项目建立初衷
<font size=4>&emsp;&emsp;学校前端开发实验：通过开发一个简单的应用程序，掌握前端开发技术与主流框架 VUE3、并能使用Element Plus 组件优化界面，以及进行前后端数据的交互。能够将交互设计原则应用到前端页面设计中，体现创新性、友好性和以人为本等因素。在实验中能够通过分析图书及网络文献资料，调研并学习先进技术、培养自主学习能力。</font><br>
## 2、项目内容
<font size=4>&emsp;&emsp;基于sping + vue仓库管理系统web项目。用于记录仓库内各个资源的信息及数量。该项目可以添加删除用户，可添加管理员用户，对仓库及物品完成基本的增删改查。前端vue + element-plus，后端springboot + myhabits。数据库mysql</font><br>

<br>
<br>

# 环境相关
<font size = 4> &emsp;前端运行环境：Node.js </font><br>
<font size = 4> &emsp;后端运行环境：Jre 17、Mysql</font><br>
<font size = 4> &emsp;集成开发环境（IDE）：Intellij IDEA</font><br>

<br>
<br>

# 关于数据库说明
<font size=4>1. 建立数据库语句在仓库中已给出已给出</font><br><br>
<font size=4>2. 数据库的连接</font><br>
<font size=3>&emsp;&emsp;后端连接数据库，需要更改连接的用户名和密码</font><br>
<font size=3>&emsp;&emsp;修改位置在项目：WMS_program/WMS_BackEnd/src/main/resources/application.yml文件中</font><br>
<font size=3>&emsp;&emsp;yml文件内容应为：</font><br>
```
spring:
  application:
    name: springboot
  datasource:
    url: jdbc:mysql://localhost:3306/wm?serverTimezone=GMT%2B8&characterEncoding=utf-8
    username: （待填入连接用户名）
    password: （待填入连接密码）
    driver-class-name: com.mysql.cj.jdbc.Driver
```

<br>
<br>

# 项目运行步骤
+ <font size = 5>step 1 数据库部分</font><br>
<font size = 3>运行给出的sql语句，完成上面对数据库连接的修改</font><br>

+ <font size = 5>step 2 后端部分</font><br>
<font size = 3>编译并运行```\WMS_program\WMS_BackEnd\src\main\java\com\example\spring```路径下的```Application.java```</font><br>

+ <font size = 5>step 3 前端部分</font><br>
<font size = 3>自行安装node.js，pnpm包管理器</font><br>
<font size = 3>通过命令行，在```\WMS_program\WMS_FrontEnd```路径下依次运行下述命令</font>
```
npm install
pnpm run dev
```

<br>
<br>

# 编者的话
<font size=4>&emsp;&emsp;本项目前端和后端代码中普遍缺少注释，十分不应该。</font><br>