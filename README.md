# RhineChat
### websocket聊天室，支持群聊，单聊，聊天页面仿微信，聊天记录已实现保存数据库。

# 结构设计
### 技术架构：springboot，mybatis，websocket，thymeleaf
### 数据库：mysql

# 运行方式
#### 前提：连接mysql数据库，导入resources文件夹下sql脚本：chat.sql。
#### 1.使用idea打开本项目，然后选择自己的maven仓库（可默认），修改数据库连接信息。
#### 2.运行项目启动类，等待项目启动完成。
#### 3.打开浏览器，访问 http://localhost:9999/chat
#### 4.默认账号：kongzi，mengzi，lisi，zhangsan    
#### 5.密码均为：123
#### 注：如要测试可打开多个浏览器登录不同账号。本项目未实现新消息提醒，必须点到与对方聊天的窗口才能看到新信息。

# 运行截图
### 注册
![image](https://github.com/RhineDream/RhineChat/blob/master/images/1.png)
### 登录
![image](https://github.com/RhineDream/RhineChat/blob/master/images/2.png)
### 群聊
![image](https://github.com/RhineDream/RhineChat/blob/master/images/3.png)
![image](https://github.com/RhineDream/RhineChat/blob/master/images/4.png)
### 单聊
![image](https://github.com/RhineDream/RhineChat/blob/master/images/5.png)
![image](https://github.com/RhineDream/RhineChat/blob/master/images/6.png)


