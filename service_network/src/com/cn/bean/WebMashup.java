package com.cn.bean;

/**
 * 
 * @author 徐新凯
 * @date 2017年3月17日 下午10:14:06
 * @description
 */
public class WebMashup {

	private int mashup_id;
	private String mashup_name;
	private String mashup_tags;
	private String mashup_apis;
	private float score;

	// Constructors

	/** default constructor */
	public WebMashup() {
	}

	public WebMashup(int mashup_id, String mashup_name, String mashup_tags,
			String mashup_apis) {
		super();
		this.mashup_id = mashup_id;
		this.mashup_name = mashup_name;
		this.mashup_tags = mashup_tags;
		this.mashup_apis = mashup_apis;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public int getMashup_id() {
		return mashup_id;
	}

	public void setMashup_id(int mashup_id) {
		this.mashup_id = mashup_id;
	}

	public String getMashup_name() {
		return mashup_name;
	}

	public void setMashup_name(String mashup_name) {
		this.mashup_name = mashup_name;
	}

	public String getMashup_tags() {
		return mashup_tags;
	}

	public void setMashup_tags(String mashup_tags) {
		this.mashup_tags = mashup_tags;
	}

	public String getMashup_apis() {
		return mashup_apis;
	}

	public void setMashup_apis(String mashup_apis) {
		this.mashup_apis = mashup_apis;
	}

}