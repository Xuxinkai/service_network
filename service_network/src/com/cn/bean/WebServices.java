package com.cn.bean;

/**
 * 
 * @author 徐新凯
 * @date 2017年3月17日 下午10:16:20
 * @description
 */

public class WebServices {

	// Fields

	private String wsname;
	private String summary;
	private String category;
	private String tags;
	private String protocols;
	private String dataformats;
	private String apihome;
	private String description;
	private String provider;
	private String signuprequire;
	private String developerkeyrequire;
	private String id;
	private String updated;
	private String wsdl;
	private String rating;

	// Constructors

	/** default constructor */
	public WebServices() {
	}

	/** minimal constructor */
	public WebServices(String wsname) {
		this.wsname = wsname;
	}

	/** full constructor */
	public WebServices(String wsname, String summary, String category,
			String tags, String protocols, String dataformats, String apihome,
			String description, String provider, String signuprequire,
			String developerkeyrequire, String id, String updated, String wsdl,
			String rating) {
		this.wsname = wsname;
		this.summary = summary;
		this.category = category;
		this.tags = tags;
		this.protocols = protocols;
		this.dataformats = dataformats;
		this.apihome = apihome;
		this.description = description;
		this.provider = provider;
		this.signuprequire = signuprequire;
		this.developerkeyrequire = developerkeyrequire;
		this.id = id;
		this.updated = updated;
		this.wsdl = wsdl;
		this.rating = rating;
	}

	// Property accessors

	public String getWsname() {
		return this.wsname;
	}

	public void setWsname(String wsname) {
		this.wsname = wsname;
	}

	public String getSummary() {
		return this.summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getTags() {
		return this.tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getProtocols() {
		return this.protocols;
	}

	public void setProtocols(String protocols) {
		this.protocols = protocols;
	}

	public String getDataformats() {
		return this.dataformats;
	}

	public void setDataformats(String dataformats) {
		this.dataformats = dataformats;
	}

	public String getApihome() {
		return this.apihome;
	}

	public void setApihome(String apihome) {
		this.apihome = apihome;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProvider() {
		return this.provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getSignuprequire() {
		return this.signuprequire;
	}

	public void setSignuprequire(String signuprequire) {
		this.signuprequire = signuprequire;
	}

	public String getDeveloperkeyrequire() {
		return this.developerkeyrequire;
	}

	public void setDeveloperkeyrequire(String developerkeyrequire) {
		this.developerkeyrequire = developerkeyrequire;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUpdated() {
		return this.updated;
	}

	public void setUpdated(String updated) {
		this.updated = updated;
	}

	public String getWsdl() {
		return this.wsdl;
	}

	public void setWsdl(String wsdl) {
		this.wsdl = wsdl;
	}

	public String getRating() {
		return this.rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

}