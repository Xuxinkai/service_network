package com.cn.bean;

/**
 * 
 * @author 徐新凯
 * @date 2017年3月17日 下午10:26:38
 * @description
 */

@SuppressWarnings("serial")
public class TagRelation implements java.io.Serializable {

	// Fields

	private int tag_id_one;
	private int tag_id_two;
	private String edge;
	private String desc;

	// Constructors

	/** default constructor */
	public TagRelation() {
	}

	public TagRelation(int tag_id_one, int tag_id_two, String edge, String desc) {
		super();
		this.tag_id_one = tag_id_one;
		this.tag_id_two = tag_id_two;
		this.edge = edge;
		this.desc = desc;
	}

	public int getTag_id_one() {
		return tag_id_one;
	}

	public void setTag_id_one(int tag_id_one) {
		this.tag_id_one = tag_id_one;
	}

	public int getTag_id_two() {
		return tag_id_two;
	}

	public void setTag_id_two(int tag_id_two) {
		this.tag_id_two = tag_id_two;
	}

	public String getEdge() {
		return this.edge;
	}

	public void setEdge(String edge) {
		this.edge = edge;
	}

	public String getDesc() {
		return this.desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}