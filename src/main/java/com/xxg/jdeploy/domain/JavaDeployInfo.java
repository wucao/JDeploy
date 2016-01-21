package com.xxg.jdeploy.domain;

/**
 * Created by xxg on 15-8-20.
 */
public class JavaDeployInfo {

    private String uuid;
    private String name;
    private int type;
    private String url;

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
}
