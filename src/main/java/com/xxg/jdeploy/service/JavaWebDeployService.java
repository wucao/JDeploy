package com.xxg.jdeploy.service;

import com.xxg.jdeploy.domain.JavaWebDeployInfo;
import com.xxg.jdeploy.mapper.JavaWebDeployMapper;
import com.xxg.jdeploy.util.ShellUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.List;

@Service
public class JavaWebDeployService {
	
	@Autowired
	private JavaWebDeployMapper javaWebDeployMapper;

	@Value("${shell.javawebdeploy}")
	private String shellFileFolder;

	@Value("${javawebdeploy.basepath}")
	private String basePath;

	@Value("${javawebdeploy.jettypath}")
	private String jettyPath;

	public List<JavaWebDeployInfo> getList() {
		return javaWebDeployMapper.getList();
	}

	public JavaWebDeployInfo getDetail(String uuid) {
		return javaWebDeployMapper.getDetail(uuid);
	}

	public void insert(JavaWebDeployInfo javaWebDeployInfo) {
		javaWebDeployMapper.insert(javaWebDeployInfo);
	}

	public String getStatus(String uuid) throws IOException {
		JavaWebDeployInfo info = javaWebDeployMapper.getDetail(uuid);
		if(info != null) {
			String out = ShellUtil.exec("sh " + shellFileFolder + "/isrunning.sh " + info.getUuid());
			return String.valueOf(StringUtils.hasText(out) && out.contains("java -jar"));
		} else {
			return "false";
		}
	}

	public String deploy(String uuid) throws IOException {
		JavaWebDeployInfo info = javaWebDeployMapper.getDetail(uuid);
		if(info != null) {
			StringBuilder sb = new StringBuilder();

			// kill进程
			sb.append(ShellUtil.exec("sh " + shellFileFolder + "/kill.sh " + info.getUuid()));
			// 打包
			String contextPath = info.getContextPath();
			contextPath = contextPath.replace("/", "");
			if(contextPath.length() == 0) {
				contextPath = "root";
			}
			sb.append(ShellUtil.exec("sh " + shellFileFolder + "/package.sh " + info.getUuid() + " " + info.getUrl() + " " + info.getFinalName() + " " + contextPath + " " + jettyPath + " " + basePath));
			// 启动程序
			sb.append(ShellUtil.exec("sh " + shellFileFolder + "/start.sh " + info.getUuid() + " " + info.getPort() + " " + jettyPath + " " + basePath));

			return sb.toString();
		} else {
			return uuid + "对应的项目不存在！";
		}
	}

	public String restart(String uuid) throws IOException {

		JavaWebDeployInfo info = javaWebDeployMapper.getDetail(uuid);

		if(info != null) {
			StringBuilder sb = new StringBuilder();
			// kill进程
			sb.append(ShellUtil.exec("sh " + shellFileFolder + "/kill.sh " + info.getUuid()));
			// 启动程序
			sb.append(ShellUtil.exec("sh " + shellFileFolder + "/start.sh " + info.getUuid() + " " + info.getPort() + " " + jettyPath + " " + basePath));
			return sb.toString();
		} else {
			return uuid + "对应的项目不存在！";
		}
	}

	public String stop(String uuid) throws IOException {
		JavaWebDeployInfo info = javaWebDeployMapper.getDetail(uuid);
		if(info != null) {
			return ShellUtil.exec("sh " + shellFileFolder + "/kill.sh " + info.getUuid());
		} else {
			return uuid + "对应的项目不存在！";
		}
	}

	public String showLog(String uuid) throws IOException {
		JavaWebDeployInfo info = javaWebDeployMapper.getDetail(uuid);
		if(info != null) {
			return ShellUtil.exec("sh " + shellFileFolder + "/showlog.sh " + info.getUuid() + " " + basePath);
		} else {
			return uuid + "对应的项目不存在！";
		}
	}
}
