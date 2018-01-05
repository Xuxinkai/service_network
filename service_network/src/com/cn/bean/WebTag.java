package com.cn.bean;

/**
 * 
 * @author 徐新凯
 * @date 2017年4月23日 下午9:00:34
 * @description
 */
public class WebTag {
	int tag_id;
	String tag_name;
	String tag_apis;
	String tag_mashups;

	public WebTag() {
	}

	public WebTag(int tag_id, String tag_name, String tag_apis,
			String tag_mashups) {
		super();
		this.tag_id = tag_id;
		this.tag_name = tag_name;
		this.tag_apis = tag_apis;
		this.tag_mashups = tag_mashups;
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

	public String getTag_apis() {
		return tag_apis;
	}

	public void setTag_apis(String tag_apis) {
		this.tag_apis = tag_apis;
	}

	public String getTag_mashups() {
		return tag_mashups;
	}

	public void setTag_mashups(String tag_mashups) {
		this.tag_mashups = tag_mashups;
	}

}
