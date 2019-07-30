# shopping_be
使用springboot+springmvc+mybatis实现商城后端

使用shiro实现登录校验

### 2019-07-21

完成了基本框架的搭建：
- 整合了shiro实现登录
- 使用mybatis generator生成了基础的mapper文件
- 完成了一些util，包括密码MD5加密，返回类型Result的编写

### 2019-07-22
- 添加获取登录用户的util

### 2019-07-23
- 增加Log4j2支持
- 修改了druid的配置

### 2019-07-24

- 新增购物车完成部分功能

---
记录：rest接口使用json进行前后端交互，一定要添加@RequestBody注解

### 2019-07-25

- 购物车部分功能开发

---
记录：如果返回类型为list的话，mapper里使用resultType好像会有问题，使用resultMap要注意引用的是最上面声明的resultMap
