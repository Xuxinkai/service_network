package com.cn.bean;

/**
 * 
 * @author 徐新凯
 * @date 2017年3月17日 下午10:22:27
 * @description
 */

@SuppressWarnings("serial")
public class DomainNet implements java.io.Serializable {

	// Fields

	private int domain_id;
	private String domain_name;

	// Constructors

	/** default constructor */
	public DomainNet() {
	}

	public DomainNet(int domain_id, String domain_name) {
		super();
		this.domain_id = domain_id;
		this.domain_name = domain_name;
	}

	public int getDomain_id() {
		return domain_id;
	}

	public void setDomain_id(int domain_id) {
		this.domain_id = domain_id;
	}

	public String getDomain_name() {
		return domain_name;
	}

	public void setDomain_name(String domain_name) {
		this.domain_name = domain_name;
	}

}