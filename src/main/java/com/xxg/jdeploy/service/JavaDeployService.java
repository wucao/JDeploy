package com.xxg.jdeploy.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.xxg.jdeploy.domain.JavaDeployInfo;
import com.xxg.jdeploy.mapper.JavaDeployMapper;
import com.xxg.jdeploy.util.ShellUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class JavaDeployService {
	
	@Autowired
	private JavaDeployMapper javaDeployMapper;

	@Value("${shell.javadeploy}")
	private String shellFileFolder;

	@Value("${javadeploy.basepath}")
	private String basePath;

	public List<JavaDeployInfo> getList() {
		return javaDeployMapper.getList();
	}

	public JavaDeployInfo getDetail(String uuid) {
		return javaDeployMapper.getDetail(uuid);
	}

	public void insert(JavaDeployInfo javaDeployInfo) {
		javaDeployMapper.insert(javaDeployInfo);
	}

	public String getStatus(String uuid) throws IOException {
		JavaDeployInfo info = javaDeployMapper.getDetail(uuid);
		if(info != null) {
			String out = ShellUtil.exec("sh " + shellFileFolder + "/isrunning.sh " + info.getUuid());
			return String.valueOf(StringUtils.hasText(out) && out.contains("java -jar"));
		} else {
			return "false";
		}
	}

	public String deploy(String uuid) throws IOException {
		JavaDeployInfo info = javaDeployMapper.getDetail(uuid);
		if(info != null) {
			StringBuilder sb = new StringBuilder();

			// kill进程
			sb.append(ShellUtil.exec("sh " + shellFileFolder + "/kill.sh " + info.getUuid()));
			// 打包
			String[] cmdArray = {"sh", shellFileFolder + "/package.sh", info.getUuid(), info.getUrl(), basePath, String.valueOf(info.getType()), info.getProfile(), info.getBranch()};
			sb.append(ShellUtil.exec(cmdArray));
			String finalName = getFinalName(info.getUuid());
			if(finalName != null) {
				String module = "";
				if(StringUtils.hasText(info.getModule())) {
					module = module + '/';
				}
				// 启动程序
				sb.append(ShellUtil.exec("sh " + shellFileFolder + "/start.sh " + info.getUuid() + " " + finalName + " " + basePath + " " + module));
			} else {
				sb.append("打包失败");
			}

			return sb.toString();
		} else {
			return uuid + "对应的项目不存在！";
		}
	}

	public String restart(String uuid) throws IOException {

		JavaDeployInfo info = javaDeployMapper.getDetail(uuid);

		if(info != null) {
			StringBuilder sb = new StringBuilder();
			// kill进程
			sb.append(ShellUtil.exec("sh " + shellFileFolder + "/kill.sh " + info.getUuid()));
			String finalName = getFinalName(info.getUuid());
			if(finalName != null) {
				String module = "";
				if(StringUtils.hasText(info.getModule())) {
					module = module + '/';
				}
				// 启动程序
				sb.append(ShellUtil.exec("sh " + shellFileFolder + "/start.sh " + info.getUuid() + " " + finalName + " " + basePath + " " + module));
			} else {
				sb.append("找不到程序包，请重新部署");
			}
			return sb.toString();
		} else {
			return uuid + "对应的项目不存在！";
		}
	}

	public String stop(String uuid) throws IOException {
		JavaDeployInfo info = javaDeployMapper.getDetail(uuid);
		if(info != null) {
			return ShellUtil.exec("sh " + shellFileFolder + "/kill.sh " + info.getUuid());
		} else {
			return uuid + "对应的项目不存在！";
		}
	}

	public String showLog(String uuid) throws IOException {
		JavaDeployInfo info = javaDeployMapper.getDetail(uuid);
		if(info != null) {
			return "sh " + shellFileFolder + "/showlog.sh " + info.getUuid() + " " + basePath;
		} else {
			return "echo \"对应的项目不存在！\"";
		}
	}
	
	private String getFinalName(String uuid) {
		File dir = new File(basePath + "/" + uuid + "/target");
		File[] files = dir.listFiles();
		
		String fileName = null;
		long size = 0;
		for(File file : files) {
			String name = file.getName();
			if(file.isFile() && name.endsWith(".jar")) {
				if(file.length() > size) {
					fileName = name;
					size = file.length();
				}
			}
		}
		return fileName;
	}
}
