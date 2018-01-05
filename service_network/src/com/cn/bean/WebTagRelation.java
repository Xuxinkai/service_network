package com.cn.bean;

/**
 * 
 * @author 徐新凯
 * @date 2017年4月25日 下午3:53:28
 * @description 
 *              tag的相似度实体类，分三种情况:a=1,b=0(只考虑api)或a=0,b=1(只考虑mashup)或0《a《1,0《b《1,a
 *              +b=1(api和mashup都考虑);
 */
public class WebTagRelation {
	/**
	 * 关系id
	 */
	private int relation_id;
	/**
	 * 第一个tag的id
	 */
	private int tag_one;
	/**
	 * 第二个tag的id
	 */
	private int tag_two;
	/**
	 * 权值
	 */
	private String weight;
	/**
	 * 描述 写明是哪一种情况的 1-0代表a=1,b=0(只考虑api)，，0-1代表a=0,b=1(只考虑mashup)，，，0.5-0.5或其他(
	 * api和mashup都考虑)
	 */
	private String desc;

	public WebTagRelation() {
	}

	public WebTagRelation(int relation_id, int tag_one, int tag_two,
			String weight, String desc) {
		super();
		this.relation_id = relation_id;
		this.tag_one = tag_one;
		this.tag_two = tag_two;
		this.weight = weight;
		this.desc = desc;
	}

	public int getRelation_id() {
		return relation_id;
	}

	public void setRelation_id(int relation_id) {
		this.relation_id = relation_id;
	}

	public int getTag_one() {
		return tag_one;
	}

	public void setTag_one(int tag_one) {
		this.tag_one = tag_one;
	}

	public int getTag_two() {
		return tag_two;
	}

	public void setTag_two(int tag_two) {
		this.tag_two = tag_two;
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
