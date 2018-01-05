package com.cn.dao;

import java.util.List;

import com.cn.bean.WebMashupRelation;

/**
 * 
 * @author 徐新凯
 * @date 2017年4月25日 下午4:02:28
 * @description
 */
public interface WebMashupRelationDao {

	/**
	 * 添加一个WebMashupRelation
	 * 
	 * @param wmr
	 * @return
	 */
	public int addWebMashupRelation(WebMashupRelation wmr);

	/**
	 * 根据权值获得WebMashupRelation
	 * 
	 * @param weight
	 * @return
	 */
	public List<WebMashupRelation> getWebMashupRelationByWeight(String weight);
}
