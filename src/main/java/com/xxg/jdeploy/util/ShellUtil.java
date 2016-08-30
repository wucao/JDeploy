package com.xxg.jdeploy.util;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteWatchdog;
import org.apache.commons.exec.PumpStreamHandler;
import org.springframework.util.StringUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by xxg on 15-8-18.
 */
public class ShellUtil {

	public static String exec(String... cmd) throws IOException {
		
		CommandLine cmdLine = CommandLine.parse(cmd[0]);
		for(int i = 1; i < cmd.length; i++) {
			if(StringUtils.hasText(cmd[i]))
				cmdLine.addArgument(cmd[i], false);
			else {
				cmdLine.addArgument("null", false);
			}
		}
		
		DefaultExecutor executor = new DefaultExecutor();

		// 防止抛出异常
		executor.setExitValues(null);

		// 命令执行的超时时间
		ExecuteWatchdog watchdog = new ExecuteWatchdog(600000);
		executor.setWatchdog(watchdog);

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PumpStreamHandler streamHandler = new PumpStreamHandler(outputStream);
		executor.setStreamHandler(streamHandler);

		executor.execute(cmdLine);

		return outputStream.toString();
	}

}
