## Web-FancyFramework 项目说明

#### 项目描述
  该项目旨在利用 Spring + SpringMVC + mybatis 构建一个简单易用、具有高可扩展性和高可维护性特点的通用 Web 项目框架，进一步降低 Web 项目的开发和维护难度。

#### 项目构建
  Maven + DynamicWebProject + JUnit

#### 项目结构
   文件和路径 | 功能
   -----------|-----------
   target\ | Target files contains compiled class files and so on
   src\ | Java source code, test code, resources and webapp files
   pom.xml | Maven configuration file
   .classpath | Project class path entry
   .project | Project description file
   .settings\ | Setting files of eclipse IDE
   
#### 包结构
   包名 | 功能
   -----------|-----------
   aspects\ | 所有切面，包括异常log切面和业务服务log切面
   constants\ | 所有常量
   interceptors\ | 所有拦截器，包括头检查拦截器，认证拦截器和前向定向拦截器
   controllers\ | 所有后端控制器
   services\ | 所有业务服务类
   daos\ | 所有数据访问类
   core\ | 核心类，包括异常统一处理器和应用上下文监听器
   entities\ | 所有实体类
   factories\ | 所有实体类的静态工厂类
   exceptions\ | 所有异常类
   utils\ | 所有的通用工具类
   helpers\ | 所有的项目内辅助类
