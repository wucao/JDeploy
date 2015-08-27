package com.xxg.jdeploy.mapper;

import com.xxg.jdeploy.domain.JavaWebDeployInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface JavaWebDeployMapper {

	@Select("select uuid,name,final_name as finalName,url,context_path as contextPath,port from java_web_deploy")
	List<JavaWebDeployInfo> getList();

	@Select("select uuid,name,final_name as finalName,url,context_path as contextPath,port from java_web_deploy where uuid=#{uuid}")
	JavaWebDeployInfo getDetail(String uuid);

	@Insert("insert into java_web_deploy (uuid,name,final_name,url,context_path,port) values (#{uuid},#{name},#{finalName},#{url},#{contextPath},#{port})")
	void insert(JavaWebDeployInfo javaWebDeployInfo);

}