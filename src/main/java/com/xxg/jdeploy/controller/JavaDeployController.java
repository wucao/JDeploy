package com.xxg.jdeploy.controller;

import com.xxg.jdeploy.domain.JavaDeployInfo;
import com.xxg.jdeploy.service.JavaDeployService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

/**
 * 线上环境的后台服务自动化部署
 * @author wucao
 */
@Controller
@RequestMapping("javadeploy")
public class JavaDeployController {
	
	@Autowired
	private JavaDeployService javaDeployService;

	/**
	 * 添加项目页面
	 */
	@RequestMapping(value = "new", method = RequestMethod.GET)
	public ModelAndView newService() {
		ModelAndView mv = new ModelAndView("javadeploy/new");
		return mv;
	}

	/**
	 * 添加项目请求
	 */
	@RequestMapping(value = "insert", method = RequestMethod.POST)
	public String insert(JavaDeployInfo javaDeployInfo, HttpServletRequest request) {
		String uuid = UUID.randomUUID().toString();
		javaDeployInfo.setUuid(uuid);
		javaDeployService.insert(javaDeployInfo);
		return "redirect:" + request.getContextPath() + "/javadeploy/detail/" + uuid;
	}

	/**
	 * 详情页面
	 */
	@RequestMapping(value = "detail/{uuid}", method = RequestMethod.GET)
	public ModelAndView detail(@PathVariable String uuid) {
		ModelAndView mv = new ModelAndView("javadeploy/detail");
		mv.addObject("detail", javaDeployService.getDetail(uuid));
		return mv;
	}

	/**
	 * ajax查看运行状态
	 */
	@ResponseBody
	@RequestMapping(value = "status", produces = "text/plain;charset=UTF-8", method = RequestMethod.POST)
	public String ajaxStatus(String uuid) throws IOException {
		return javaDeployService.getStatus(uuid);
	}
	
	/**
	 * ajax部署
	 */
	@ResponseBody
	@RequestMapping(value = "deploy", produces = "text/plain;charset=UTF-8", method = RequestMethod.POST)
	public String ajaxDeploy(String uuid) throws IOException {
		return javaDeployService.deploy(uuid);
	}
	
	/**
	 * ajax重启
	 */
	@ResponseBody
	@RequestMapping(value = "restart", produces = "text/plain;charset=UTF-8", method = RequestMethod.POST)
	public String ajaxRestart(String uuid) throws IOException {
		return javaDeployService.restart(uuid);
	}
	
	/**
	 * ajax停止
	 */
	@ResponseBody
	@RequestMapping(value = "stop", produces = "text/plain;charset=UTF-8", method = RequestMethod.POST)
	public String ajaxStop(String uuid) throws IOException {
		return javaDeployService.stop(uuid);
	}

	/**
	 * ajax查看日志
	 */
	@ResponseBody
	@RequestMapping(value = "log", produces = "text/plain;charset=UTF-8", method = RequestMethod.POST)
	public String ajaxShowLog(String uuid) throws IOException {
		return javaDeployService.showLog(uuid);
	}

}
