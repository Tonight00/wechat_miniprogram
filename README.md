​	本项目实现了基于微信小程序的ATM自动存取系统,并采用了前后端分离的设计思想。现有功能为：注册、登录、存钱、取钱。 

### 一、项目目录

```c++
├─backstage 
│  ├─src
│  │  ├─dao   // 数据库访问
│  │  └─servlet // 服务器响应请求
│  └─web
│      └─WEB-INF
│          └─lib // jar包
├─foreground
│  ├─dist
│  ├─images
│  ├─pages
│  │  ├─img
│  │  ├─index // 页面
│  │  └─logs
│  └─utils
└─sql // 数据库配置
```

### 二、项目效果图

![image-20200413100940184](C:\Users\联想\AppData\Roaming\Typora\typora-user-images\image-20200413100940184.png)

### 三、活动图

​								![image-20200413101032738](C:\Users\联想\AppData\Roaming\Typora\typora-user-images\image-20200413101032738.png)

### 四、项目架构

前端：微信小程序 调试基础库 2.10.3 

后端： javaEE 7 + mysql 8.0.18 + servlet 

服务器软件：tomcat 8.5.50

操作系统：win10

---

##### 部署步骤：

	1. 打开sql文件夹，并执行tableconfig中的sql语句，完成db2数据库和相关表的创建。
 	2. 在**微信开发工具**上创建项目，并清空所有工程文件，然后导入foreground目录下的所有文件
 	3. 创建javaweb项目，并用backstage目录下的文件进行对应位置文件替换。
 	4. 运行java程序
 	5. 在微信小程序界面，点击注册按钮，注册一个用户，注意身份证号为9位，手机号为11位。
 	6. 如果注册成功，则说明部署成功！！ 

​		









