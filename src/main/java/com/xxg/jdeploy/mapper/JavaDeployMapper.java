package com.xxg.jdeploy.mapper;

import java.util.List;

import com.xxg.jdeploy.domain.JavaDeployInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface JavaDeployMapper {

	@Select("select uuid,name,url from java_deploy")
	List<JavaDeployInfo> getList();

	@Select("select uuid,name,url from java_deploy where uuid=#{uuid}")
	JavaDeployInfo getDetail(String uuid);

	@Insert("insert into java_deploy (uuid,name,url) values (#{uuid},#{name},#{url})")
	void insert(JavaDeployInfo javaDeployInfo);

}