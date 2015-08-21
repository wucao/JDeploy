package com.xxg.jdeploy.util;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by xxg on 15-8-18.
 */
public class ShellUtil {

    public static String exec(String cmd) throws IOException {
        Process process = Runtime.getRuntime().exec(cmd);
        InputStream inputStream = process.getInputStream();
        InputStream errorStream = process.getErrorStream();
        try {
            String err = IOUtils.toString(errorStream, "UTF-8");
            String out = IOUtils.toString(inputStream, "UTF-8");
            return err + "\n" + out;
        } finally {
            inputStream.close();
            errorStream.close();
        }
    }

}
