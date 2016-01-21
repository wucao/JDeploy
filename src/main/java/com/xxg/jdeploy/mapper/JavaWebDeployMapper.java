package com.xxg.jdeploy.mapper;

import com.xxg.jdeploy.domain.JavaWebDeployInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface JavaWebDeployMapper {

	@Select("select uuid,name,url,context_path as contextPath,port,type from java_web_deploy")
	List<JavaWebDeployInfo> getList();

	@Select("select uuid,name,url,context_path as contextPath,port,type from java_web_deploy where uuid=#{uuid}")
	JavaWebDeployInfo getDetail(String uuid);

	@Insert("insert into java_web_deploy (uuid,name,url,context_path,port,type) values (#{uuid},#{name},#{url},#{contextPath},#{port},#{type})")
	void insert(JavaWebDeployInfo javaWebDeployInfo);

}