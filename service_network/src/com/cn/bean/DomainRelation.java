package com.cn.bean;

/**
 * 
 * @author 徐新凯
 * @date 2017年3月17日 下午10:26:55
 * @description
 */

@SuppressWarnings("serial")
public class DomainRelation implements java.io.Serializable {

	// Fields

	private int domain_id_one;
	private int domain_id_two;
	private String edge;
	private String desc;

	// Constructors

	/** default constructor */
	public DomainRelation() {
	}

	public DomainRelation(int domain_id_one, int domain_id_two, String edge,
			String desc) {
		super();
		this.domain_id_one = domain_id_one;
		this.domain_id_two = domain_id_two;
		this.edge = edge;
		this.desc = desc;
	}

	public int getDomain_id_one() {
		return domain_id_one;
	}

	public void setDomain_id_one(int domain_id_one) {
		this.domain_id_one = domain_id_one;
	}

	public int getDomain_id_two() {
		return domain_id_two;
	}

	public void setDomain_id_two(int domain_id_two) {
		this.domain_id_two = domain_id_two;
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