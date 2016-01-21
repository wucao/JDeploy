package com.xxg.jdeploy.domain;

/**
 * Created by xxg on 15-8-26.
 */
public class JavaWebDeployInfo {

    private String uuid;
    private String name;
    private int type;
    private String url;
    private String contextPath;
    private int port;

    public String getUuid() {
        return uuid;
    }

    public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContextPath() {
        return contextPath;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
