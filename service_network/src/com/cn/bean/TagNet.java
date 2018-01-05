package com.cn.bean;

/**
 * 
 * @author 徐新凯
 * @date 2017年3月17日 下午10:17:24
 * @description
 */

@SuppressWarnings("serial")
public class TagNet implements java.io.Serializable {

	// Fields

	private int tag_id;
	private String tag_name;

	// Constructors

	/** default constructor */
	public TagNet() {
	}

	public TagNet(int tag_id, String tag_name) {
		super();
		this.tag_id = tag_id;
		this.tag_name = tag_name;
	}

	public int getTag_id() {
		return tag_id;
	}

	public void setTag_id(int tag_id) {
		this.tag_id = tag_id;
	}

	public String getTag_name() {
		return tag_name;
	}

	public void setTag_name(String tag_name) {
		this.tag_name = tag_name;
	}

}