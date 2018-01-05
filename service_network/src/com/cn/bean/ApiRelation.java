package com.cn.bean;

/**
 * 
 * @author 徐新凯
 * @date 2017年3月17日 下午10:18:39
 * @description
 */

@SuppressWarnings("serial")
public class ApiRelation implements java.io.Serializable {

	private int api_id_one;
	private int api_id_two;
	private String edge;
	private String desc;

	public ApiRelation() {

	}

	public ApiRelation(int api_id_one, int api_id_two, String edge, String desc) {
		super();
		this.api_id_one = api_id_one;
		this.api_id_two = api_id_two;
		this.edge = edge;
		this.desc = desc;
	}

	public int getApi_id_one() {
		return api_id_one;
	}

	public void setApi_id_one(int api_id_one) {
		this.api_id_one = api_id_one;
	}

	public int getApi_id_two() {
		return api_id_two;
	}

	public void setApi_id_two(int api_id_two) {
		this.api_id_two = api_id_two;
	}

	public String getEdge() {
		return edge;
	}

	public void setEdge(String edge) {
		this.edge = edge;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}