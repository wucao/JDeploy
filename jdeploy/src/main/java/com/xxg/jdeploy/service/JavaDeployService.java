package com.xxg.jdeploy.service;

import java.util.List;

import com.xxg.jdeploy.domain.JavaDeployInfo;
import com.xxg.jdeploy.mapper.JavaDeployMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JavaDeployService {
	
	@Autowired
	private JavaDeployMapper javaDeployMapper;

	public List<JavaDeployInfo> getList() {
		return javaDeployMapper.getList();
	}

	public JavaDeployInfo getDetail(String uuid) {
		return javaDeployMapper.getDetail(uuid);
	}

	public void insert(JavaDeployInfo javaDeployInfo) {
		javaDeployMapper.insert(javaDeployInfo);
	}

}
