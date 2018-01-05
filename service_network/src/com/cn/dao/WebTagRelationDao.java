package com.cn.dao;

import java.util.List;

import com.cn.bean.WebTagRelation;

/**
 * 
 * @author 徐新凯
 * @date 2017年4月25日 下午4:33:12
 * @description
 */
public interface WebTagRelationDao {

	/**
	 * 添加一个WebTagRelation
	 * 
	 * @param wtr
	 * @return
	 */
	public int addWebTagRelation(WebTagRelation wtr);

	/**
	 * 根据相似度获取WebTagRelation
	 * 
	 * @param weight
	 * @return
	 */
	public List<WebTagRelation> getWebTagRelationByWeight(String weight);
}
