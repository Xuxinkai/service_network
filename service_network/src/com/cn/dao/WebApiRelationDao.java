package com.cn.dao;

import java.util.List;

import com.cn.bean.WebApiRelation;

/**
 * 
 * @author 徐新凯
 * @date 2017年4月25日 下午4:02:37
 * @description
 */
public interface WebApiRelationDao {

	/**
	 * 添加一个WebApiRelation
	 * 
	 * @param war
	 * @return
	 */
	public int addWebApiRealtion(WebApiRelation war);

	/**
	 * 根据给出的weight的值获得WebApiRelation
	 * 
	 * @param weight
	 * @return
	 */
	public List<WebApiRelation> getWebApiRelationByWeight(String weight);
}
