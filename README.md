# JDeploy
JDeploy自动化部署平台

## Java项目部署
### 新建项目
> 在“Java项目部署”选项中点击“创建”按钮，进入创建页面。需要填写“项目名称”、“finalName”、“SVN地址”，例如：
> 项目名称：JDeploy测试Java项目
> finalName：javadeploy
> SVN地址：http://code.taobao.org/svn/jdploy_java_deploy_test/
> 注：finalName需要填写部署的项目pom.xml中配置的finalName，如`<finalName>javadeploy</finalName>`

### 部署项目
在项目详情页面点击“部署”按钮，会从SVN中检出最新版本代码，并打包运行。

### 重启和停止
如果项目已经完成部署，可以通过点击“重启”或者“停止”按钮来重启或停止。

### 查看运行状态和显示日志
可在“运行状态”面板中查看程序运行状态，点击“显示日志”可以显示程序在控制台输入的日志。

### 对Java项目要求
> DEMO：http://code.taobao.org/svn/jdploy_java_deploy_test/
> 1、使用Maven管理项目，通过`mvn clean package`可以打可运行的jar包，创建项目时填写的finalName和pom.xml中的finalName一致
> 2、jar包可以通过`java -jar xxx.jar`直接运行（包含依赖包，并配置好main方法所在Class）
> 建议使用maven-shade-plugin插件：
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
