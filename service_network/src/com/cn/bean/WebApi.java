package com.cn.bean;

/**
 * 
 * @author 徐新凯
 * @date 2017年3月17日 下午10:13:05
 * @description
 */
public class WebApi {

	// Fields

	private int api_id;
	private String api_name;
	private String api_tags;
	private String api_category;
	private String api_url;
	private String api_protocol;
	private String api_comments;
	private String api_mashups;
	private float score;

	// Constructors

	/** default constructor */
	public WebApi() {
	}

	public WebApi(int api_id, String api_name, String api_tags,
			String api_category, String api_url, String api_protocol,
			String api_comments) {
		super();
		this.api_id = api_id;
		this.api_name = api_name;
		this.api_tags = api_tags;
		this.api_category = api_category;
		this.api_url = api_url;
		this.api_protocol = api_protocol;
		this.api_comments = api_comments;
	}

	public WebApi(int api_id, String api_name, String api_tags,
			String api_category, String api_url, String api_protocol,
			String api_comments, String api_mashups) {
		super();
		this.api_id = api_id;
		this.api_name = api_name;
		this.api_tags = api_tags;
		this.api_category = api_category;
		this.api_url = api_url;
		this.api_protocol = api_protocol;
		this.api_comments = api_comments;
		this.api_mashups = api_mashups;
	}

	public String getApi_mashups() {
		return api_mashups;
	}

	public void setApi_mashups(String api_mashups) {
		this.api_mashups = api_mashups;
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

	public String getApi_tags() {
		return api_tags;
	}

	public void setApi_tags(String api_tags) {
		this.api_tags = api_tags;
	}

	public String getApi_category() {
		return api_category;
	}

	public void setApi_category(String api_category) {
		this.api_category = api_category;
	}

	public String getApi_url() {
		return api_url;
	}

	public void setApi_url(String api_url) {
		this.api_url = api_url;
	}

	public String getApi_protocol() {
		return api_protocol;
	}

	public void setApi_protocol(String api_protocol) {
		this.api_protocol = api_protocol;
	}

	public String getApi_comments() {
		return api_comments;
	}

	public void setApi_comments(String api_comments) {
		this.api_comments = api_comments;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

}