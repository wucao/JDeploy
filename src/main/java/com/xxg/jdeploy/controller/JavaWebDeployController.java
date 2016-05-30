package com.xxg.jdeploy.controller;

import com.xxg.jdeploy.domain.JavaWebDeployInfo;
import com.xxg.jdeploy.service.JavaWebDeployService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.HtmlUtils;

import java.io.IOException;
import java.util.UUID;

/**
 * 线上环境的后台服务自动化部署
 * @author wucao
 */
@Controller
@RequestMapping("javawebdeploy")
public class JavaWebDeployController {
	
	@Autowired
	private JavaWebDeployService javaWebDeployService;

	/**
	 * 添加项目页面
	 */
	@RequestMapping(value = "new", method = RequestMethod.GET)
	public ModelAndView newService() {
		ModelAndView mv = new ModelAndView("javawebdeploy/new");
		return mv;
	}

	/**
	 * 添加项目请求
	 */
	@RequestMapping(value = "insert", method = RequestMethod.POST)
	public String insert(JavaWebDeployInfo javaWebDeployInfo) {
		String uuid = UUID.randomUUID().toString();
		javaWebDeployInfo.setUuid(uuid);
		javaWebDeployService.insert(javaWebDeployInfo);
		return "redirect:/javawebdeploy/detail/" + uuid;
	}

	/**
	 * 详情页面
	 */
	@RequestMapping(value = "detail/{uuid}", method = RequestMethod.GET)
	public ModelAndView detail(@PathVariable String uuid) {
		ModelAndView mv = new ModelAndView("javawebdeploy/detail");
		mv.addObject("detail", javaWebDeployService.getDetail(uuid));
		return mv;
	}

	/**
	 * ajax查看运行状态
	 */
	@ResponseBody
	@RequestMapping(value = "status", produces = "text/plain;charset=UTF-8", method = RequestMethod.POST)
	public String ajaxStatus(String uuid) throws IOException {
		return javaWebDeployService.getStatus(uuid);
	}
	
	/**
	 * ajax部署
	 */
	@ResponseBody
	@RequestMapping(value = "deploy", produces = "text/plain;charset=UTF-8", method = RequestMethod.POST)
	public String ajaxDeploy(String uuid) throws IOException {
		return HtmlUtils.htmlEscape(javaWebDeployService.deploy(uuid));
	}
	
	/**
	 * ajax重启
	 */
	@ResponseBody
	@RequestMapping(value = "restart", produces = "text/plain;charset=UTF-8", method = RequestMethod.POST)
	public String ajaxRestart(String uuid) throws IOException {
		return HtmlUtils.htmlEscape(javaWebDeployService.restart(uuid));
	}
	
	/**
	 * ajax停止
	 */
	@ResponseBody
	@RequestMapping(value = "stop", produces = "text/plain;charset=UTF-8", method = RequestMethod.POST)
	public String ajaxStop(String uuid) throws IOException {
		return HtmlUtils.htmlEscape(javaWebDeployService.stop(uuid));
	}

}
