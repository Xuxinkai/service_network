package com.cn.bean;

/**
 * 
 * @author 徐新凯
 * @date 2017年4月24日 上午11:55:52
 * @description 
 *              WebMashup的相似度，分三种情况:a=1,b=0(只考虑api)或a=0,b=1(只考虑tag)或0《a《1,0《b《1,a
 *              +b=1(api和tag都考虑);
 */
public class WebMashupRelation {
	/**
	 * 关系id
	 */
	private int relation_id;
	/**
	 * 第一个mashup的id
	 */
	private int mashup_one;
	/**
	 * 第一个mashup的name
	 */
	private String mashup_one_name;
	/**
	 * 第二个mashup的id
	 */
	private int mashup_two;
	/**
	 * 第二个mashup的name
	 */
	private String mashup_two_name;
	/**
	 * 权值
	 */
	private String weight;
	/**
	 * 描述 写明是哪一种情况的
	 * 1-0代表a=1,b=0(只考虑api)，，0-1代表a=0,b=1(只考虑tag)，，，0.5-0.5或其他(api和tag都考虑)
	 */
	private String desc;

	public WebMashupRelation() {
	}

	
	public WebMashupRelation(int relation_id, int mashup_one,
			String mashup_one_name, int mashup_two, String mashup_two_name,
			String weight, String desc) {
		super();
		this.relation_id = relation_id;
		this.mashup_one = mashup_one;
		this.mashup_one_name = mashup_one_name;
		this.mashup_two = mashup_two;
		this.mashup_two_name = mashup_two_name;
		this.weight = weight;
		this.desc = desc;
	}


	public String getMashup_one_name() {
		return mashup_one_name;
	}


	public void setMashup_one_name(String mashup_one_name) {
		this.mashup_one_name = mashup_one_name;
	}


	public String getMashup_two_name() {
		return mashup_two_name;
	}


	public void setMashup_two_name(String mashup_two_name) {
		this.mashup_two_name = mashup_two_name;
	}


	public int getRelation_id() {
		return relation_id;
	}

	public void setRelation_id(int relation_id) {
		this.relation_id = relation_id;
	}

	public int getMashup_one() {
		return mashup_one;
	}

	public void setMashup_one(int mashup_one) {
		this.mashup_one = mashup_one;
	}

	public int getMashup_two() {
		return mashup_two;
	}

	public void setMashup_two(int mashup_two) {
		this.mashup_two = mashup_two;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
