package com.xxg.jdeploy.mapper;

import java.util.List;

import com.xxg.jdeploy.domain.JavaDeployInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface JavaDeployMapper {

	@Select("select uuid,name,url,type from java_deploy")
	List<JavaDeployInfo> getList();

	@Select("select uuid,name,url,type,profile from java_deploy where uuid=#{uuid}")
	JavaDeployInfo getDetail(String uuid);

	@Insert("insert into java_deploy (uuid,name,url,type,profile) values (#{uuid},#{name},#{url},#{type},#{profile})")
	void insert(JavaDeployInfo javaDeployInfo);

}