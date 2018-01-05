package com.cn.bean;

/**
 * 
 * @author 徐新凯
 * @date 2017年4月25日 下午3:57:09
 * @description 
 *              api的相似度实体类，分三种情况:a=1,b=0(只考虑tag)或a=0,b=1(只考虑mashup)或0《a《1,0《b《1,a
 *              +b=1(tag和mashup都考虑);
 */
public class WebApiRelation {
	/**
	 * 关系的id
	 */
	private int relation_id;
	/**
	 * 第一个api的id
	 */
	private int api_one;
	/**
	 * 第一个api的name
	 */
	private String api_one_name;
	/**
	 * 第二个api的id
	 */
	private int api_two;
	/**
	 * 第二个api的name
	 */
	private String api_two_name;
	/**
	 * 权值
	 */
	private String weight;
	/**
	 * 描述 写明是哪一种情况的 1-0代表a=1,b=0(只考虑tag)，，0-1代表a=0,b=1(只考虑mashup)，，，0.5-0.5或其他(
	 * tag和mashup都考虑)
	 */
	private String desc;

	public WebApiRelation() {
	}

	public WebApiRelation(int relation_id, int api_one, int api_two,
			String weight, String desc) {
		super();
		this.relation_id = relation_id;
		this.api_one = api_one;
		this.api_two = api_two;
		this.weight = weight;
		this.desc = desc;
	}

	public WebApiRelation(int relation_id, int api_one, String api_one_name,
			int api_two, String api_two_name, String weight, String desc) {
		super();
		this.relation_id = relation_id;
		this.api_one = api_one;
		this.api_one_name = api_one_name;
		this.api_two = api_two;
		this.api_two_name = api_two_name;
		this.weight = weight;
		this.desc = desc;
	}

	public String getApi_one_name() {
		return api_one_name;
	}

	public void setApi_one_name(String api_one_name) {
		this.api_one_name = api_one_name;
	}

	public String getApi_two_name() {
		return api_two_name;
	}

	public void setApi_two_name(String api_two_name) {
		this.api_two_name = api_two_name;
	}

	public int getRelation_id() {
		return relation_id;
	}

	public void setRelation_id(int relation_id) {
		this.relation_id = relation_id;
	}

	public int getApi_one() {
		return api_one;
	}

	public void setApi_one(int api_one) {
		this.api_one = api_one;
	}

	public int getApi_two() {
		return api_two;
	}

	public void setApi_two(int api_two) {
		this.api_two = api_two;
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
