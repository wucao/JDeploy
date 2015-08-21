package com.xxg.jdeploy.controller;

import com.xxg.jdeploy.domain.JavaDeployInfo;
import com.xxg.jdeploy.service.JavaDeployService;
import com.xxg.jdeploy.util.ShellUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
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

	private String shellFileFolder = "doc/shell/javadeploy";
	
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
		JavaDeployInfo info = javaDeployService.getDetail(uuid);
		if(info != null) {
			String out = ShellUtil.exec("sh " + shellFileFolder + "/isrunning.sh " + info.getUuid());
			return String.valueOf(StringUtils.hasText(out) && out.contains("java -jar"));
		} else {
			return "false";
		}
	}
	
	/**
	 * ajax部署
	 */
	@ResponseBody
	@RequestMapping(value = "deploy", produces = "text/plain;charset=UTF-8", method = RequestMethod.POST)
	public String ajaxDeploy(String uuid) throws IOException {

		JavaDeployInfo info = javaDeployService.getDetail(uuid);
		if(info != null) {
			StringBuilder sb = new StringBuilder();

			// kill进程
			sb.append(ShellUtil.exec("sh " + shellFileFolder + "/kill.sh " + info.getUuid()));
			// 打包
			sb.append(ShellUtil.exec("sh " + shellFileFolder + "/package.sh " + info.getUuid() + " " + info.getUrl()));
			// 启动程序
			sb.append(ShellUtil.exec("sh " + shellFileFolder + "/start.sh " + info.getUuid() + " " + info.getFinalName()));

			return sb.toString();
		} else {
			return uuid + "对应的项目不存在！";
		}
	}
	
	/**
	 * ajax重启
	 */
	@ResponseBody
	@RequestMapping(value = "restart", produces = "text/plain;charset=UTF-8", method = RequestMethod.POST)
	public String ajaxRestart(String uuid) throws IOException {

		JavaDeployInfo info = javaDeployService.getDetail(uuid);

		if(info != null) {
			StringBuilder sb = new StringBuilder();
			// kill进程
			sb.append(ShellUtil.exec("sh " + shellFileFolder + "/kill.sh " + info.getUuid()));
			// 启动程序
			sb.append(ShellUtil.exec("sh " + shellFileFolder + "/start.sh " + info.getUuid() + " " + info.getFinalName()));
			return sb.toString();
		} else {
			return uuid + "对应的项目不存在！";
		}
	}
	
	/**
	 * ajax停止
	 */
	@ResponseBody
	@RequestMapping(value = "stop", produces = "text/plain;charset=UTF-8", method = RequestMethod.POST)
	public String ajaxStop(String uuid) throws IOException {

		JavaDeployInfo info = javaDeployService.getDetail(uuid);
		if(info != null) {
			StringBuilder sb = new StringBuilder();
			return ShellUtil.exec("sh " + shellFileFolder + "/kill.sh " + info.getUuid());
		} else {
			return uuid + "对应的项目不存在！";
		}
	}

	/**
	 * ajax查看日志
	 */
	@ResponseBody
	@RequestMapping(value = "log", produces = "text/plain;charset=UTF-8", method = RequestMethod.POST)
	public String ajaxShowLog(String uuid) throws IOException {
		JavaDeployInfo info = javaDeployService.getDetail(uuid);
		if(info != null) {
			return ShellUtil.exec("sh " + shellFileFolder + "/showlog.sh " + info.getUuid());
		} else {
			return uuid + "对应的项目不存在！";
		}
	}

}
