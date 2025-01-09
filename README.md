# 关于数据库说明
<font size=4>1. 建立数据库语句在文件夹下已给出</font><br>
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