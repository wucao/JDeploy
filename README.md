# JDeploy
JDeploy自动化部署平台

JDeploy是Java + Shell实现的基于Linux系统的自动化、可视化的项目部署平台，能部署Java服务、Java Web项目，可以简化项目部署操作，无需繁琐的黑窗口SSH指令及Jenkins复杂的配置，只需要提供SVN/GIT地址即可部署项目。

![JDeploy](http://img.blog.csdn.net/20151020104553172)

![JDeploy](http://img.blog.csdn.net/20151020104628193)

![JDeploy](http://img.blog.csdn.net/20151020104709833)

![JDeploy](http://img.blog.csdn.net/20151020105031597)

## JDeploy自动化部署平台部署

服务器环境：Linux（不支持Windows），需要Java环境和Maven环境，支持`java`、`mvn`、`svn`、`git`命令。

### 创建数据库（MySQL）
运行SQL文件创建数据库：doc/sql.sql

### Shell脚本
JDeploy自动化部署平台是基于Shell脚本实现。

Java项目部署Shell脚本：doc/shell/javadeploy

Java Web项目部署Shell脚本：doc/shell/javawebdeploy

Shell脚本的目录位置需要配置在`config.properties`中。

### Jetty
JDeploy自动化部署平台Java Web项目部署是基于Jetty服务器的。

需要自行下载Jetty服务器，并在`config.properties`中配置Jetty目录下start.jar路径。

### 配置文件
配置文件：src/main/resources/config.properties

需要配置数据库连接信息、Shell脚本位置、Jetty位置、项目部署位置。

### 部署
以上配置完成后，将项目部署在Jetty/Tomcat服务器上即可。

### 登录JDeploy
部署成功后，默认登录用户名：admin，密码：1234abcd。JDeploy使用Spring Security登录验证，配置文件：src/main/resources/springSecurity.xml，可以根据部署需求灵活配置。可参考博客：http://xxgblog.com/2015/09/06/spring-security-start/

## Java项目部署
### 新建项目
在“Java项目部署”选项中点击“创建”按钮，进入创建页面。需要填写“项目名称”、“SVN/GIT地址”、“Maven profile”，例如：

项目名称：JDeploy测试Java项目

SVN地址：http://code.taobao.org/svn/jdploy_java_deploy_test/

Maven profile：Maven打包时使用的pom.xml中的profile，如果不需要可以为空

Maven module：使用Maven模块化项目时部署模块的模块名，非模块化项目可以为空

### 部署项目
在项目详情页面点击“部署”按钮，会从SVN中检出最新版本代码，并打包运行。

### 重启和停止
如果项目已经完成部署，可以通过点击“重启”或者“停止”按钮来重启或停止。

### 查看运行状态和显示日志
可在“运行状态”面板中查看程序运行状态，点击“查看日志”可以查看程序在控制台输入的日志。

### 对Java项目要求
DEMO：http://code.taobao.org/svn/jdploy_java_deploy_test/

1、使用Maven管理项目，通过`mvn clean package`可以打可运行的jar包。

2、jar包可以通过`java -jar xxx.jar`直接运行（包含依赖包，并配置好main方法所在Class）。

建议使用maven-shade-plugin插件：
```
<plugin>
	<groupId>org.apache.maven.plugins</groupId>
	<artifactId>maven-shade-plugin</artifactId>
	<version>2.3</version>
	<executions>
		<execution>
			<phase>package</phase>
			<goals>
				<goal>shade</goal>
			</goals>
			<configuration>
				<transformers>
					<transformer implementation="org.apache.maven.plugins.shade.resource.ManifestResourceTransformer">
						<mainClass>com.xxg.jdeploy.test.javadeploy.Main</mainClass>
					</transformer>
					<transformer implementation="org.apache.maven.plugins.shade.resource.AppendingTransformer">
						<resource>META-INF/spring.handlers</resource>
					</transformer>
					<transformer implementation="org.apache.maven.plugins.shade.resource.AppendingTransformer">
						<resource>META-INF/spring.schemas</resource>
					</transformer>
				</transformers>
			</configuration>
		</execution>
	</executions>
</plugin>
```

## Java Web项目部署
### 新建项目
在“Java Web项目部署”选项中点击“创建”按钮，进入创建页面。需要填写“项目名称”、“contextPath”、“端口号”、“SVN/GIT地址”、“Maven profile”，例如：

项目名称：JDeploy测试Java Web项目

contextPath：/jdploytest（也可以填写'/'）

端口号：8080

SVN地址：http://code.taobao.org/svn/jdploy_javaweb_deploy_test/

Maven profile：Maven打包时使用的pom.xml中的profile，如果不需要可以为空

Maven module：使用Maven模块化项目时部署模块的模块名，非模块化项目可以为空

### 部署项目
在项目详情页面点击“部署”按钮，会从SVN中检出最新版本代码，并打包运行。

### 重启和停止
如果项目已经完成部署，可以通过点击“重启”或者“停止”按钮来重启或停止。

### 查看运行状态和显示日志
可在“运行状态”面板中查看程序运行状态，点击“查看日志”可以查看程序在控制台输入的日志。可用浏览器访问http://ip:port/contextPath。

### 对Java Web项目要求
DEMO：http://code.taobao.org/svn/jdploy_javaweb_deploy_test/

使用Maven管理项目，通过`mvn clean package`可以打成war包。

## SVN/GIT配置用户密码
如果SVN需要用户密码访问，可以这样配置：http://code.taobao.org/svn/test/trunk --username yourusername --password yourpassword

如果GIT需要用户密码访问，可以这样配置：https://yourusername:yourpassword@git.coding.net/wucao/test.git
