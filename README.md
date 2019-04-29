# shiro

#### 项目介绍
springboot2 + shiro + jwt 前后端分离权限认证。

当我们要把服务器做成无状态时（即服务器端不会保存session）,这里我们就可以用到JWT。

### 为什么使用JWT?
1.简洁(Compact): 可以通过URL，POST参数或者在HTTP header发送，因为数据量小，传输速度也很快。

2.自包含(Self-contained)：负载中包含了所有用户所需要的信息，避免了多次查询数据库。

3.安全(security): 与简单的JSON相比，XML和XML数字签名会引入复杂的安全漏洞。

### 认证原理
1.用户登陆之后，使用密码对账号进行签名生成并返回token并设置过期时间；

2.将token保存到本地，并且每次发送请求时都在header上携带token。

3.shiro过滤器拦截到请求并获取header中的token，并提交到自定义realm的doGetAuthenticationInfo方法。

4.通过jwt解码获取token中的用户名，并对token进行验证，对用户的权限进行校验。

5.请求中没有携带token访问接口，JWTFilter会默认为登录或游客访问，不需要鉴权。如果接口加入了@RequiresPermissions注解，而访问的时候也没有携带token，直接会返回无权限。

### 前端请求
* 前端登录成功后，每次请求都会携带token
```js
    //全局的ajax访问，处理ajax清求时异常
    $.ajaxSetup({
        headers: { // 默认添加请求头
                token: T //T：登录成功后返回的token，前端存储（localstorage/cookies）方便获取
        }
    });
```

### 运行环境
* JDK 1.8
* MySQL 5.7
* Nginx

### 快速开始

#### 下载项目
```git
git clone https://github.com/iathanasy/shiro.git
```
#### 导入项目 

使用自己的 IDE 导入, Eclipse 和 Intellij IDEA 均可.

#### 导入数据库

创建数据库`shiro`, 字符集选择 `utf8`, 排序规则选择 `utf8_general_ci`.

然后导入 `shiro.sql` 到数据库中.

#### 配置文件
* 打开修改(`resources/application.yml`) 配置文件
 ```yml
      # 环境 dev|test|prod
      profiles:
        active: dev
 ```
* 打开修改(`resources/application-dev.yml`) 配置文件
 ```yml
# Tomcat
server:
  port: 8081
# mysql
spring:
  datasource:
    druid:
      url: jdbc:mysql://127.0.0.1:3306/shiro?allowMultiQueries=true&useUnicode=true&characterEncoding=UTF-8&useSSL=false
      username: root
      password: root
 ```
 
#### 前端配置
* 下载 [Nginx](http://nginx.org/en/download.html)
* 打开配置文件`conf/nginx.conf`加入以下配置
```conf
server {
        listen       8001;
        server_name  localhost;

        #charset koi8-r;

        #access_log  logs/host.access.log  main;
		
		location / {
		    # 映射路径(当前项目的html)
            root   resources/html/;
			index  index.html;
            autoindex on; #开启浏览目录权限
        }
	}
```
* 修改`resources/html/static/js/baseConfig.js`
```js
/**系统配置后台 全局js*/
//后台 resources/application-dev.yml 文件中修改的 port
var baseUrl = "http://127.0.0.1:8081/";
```
* 保存启动 nginx

#### 启动项目
* 在完成了上述步骤后，找到 ShiroApplication 启动类, 启动即可.
#### 用户密码
* 超级管理员： 账号：root 密码：123456

* 普通管理员： 账号：admin 密码：123456

* 游客： 账号：guest  密码：123456

#### Druid监控
* 用户名：admin 密码：admin

#### 访问
* 访问地址为(nginx配置的)：[http://127.0.0.1:8001](http://127.0.0.1:8001)

#### 效果图
![登录](https://i.loli.net/2019/04/29/5cc6b37100286.png)
![首页](https://i.loli.net/2019/04/29/5cc6b3c5090c6.png)
![用户](https://i.loli.net/2019/04/29/5cc6b3c505f66.png)
![角色](https://i.loli.net/2019/04/29/5cc6b3c52eb30.png)
![菜单](https://i.loli.net/2019/04/29/5cc6b3c54971c.png)
![修改菜单](https://i.loli.net/2019/04/29/5cc6b3c54b9e5.png)
![Druid监控](https://i.loli.net/2019/04/29/5cc6b3c504415.png)
![日志](https://i.loli.net/2019/04/29/5cc6b3c523f6e.png)


