package com.cn.bean;

/**
 * 
 * @author 徐新凯
 * @date 2017年3月17日 下午10:20:44
 * @description
 */

@SuppressWarnings("serial")
public class ApiNet implements java.io.Serializable {

	// Fields

	private int api_id;
	private String api_name;

	public ApiNet() {
	}

	public ApiNet(int api_id, String api_name) {
		super();
		this.api_id = api_id;
		this.api_name = api_name;
	}

	public int getApi_id() {
		return api_id;
	}

	public void setApi_id(int api_id) {
		this.api_id = api_id;
	}

	public String getApi_name() {
		return api_name;
	}

	public void setApi_name(String api_name) {
		this.api_name = api_name;
	}

}