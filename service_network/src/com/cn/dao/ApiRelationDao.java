package com.cn.dao;

import java.util.List;

import com.cn.bean.ApiRelation;

/**
 * 
 * @author 徐新凯
 * @date 2017年3月17日 下午11:22:20
 * @description
 */
public interface ApiRelationDao {
	/**
	 * 获取所有的api关系
	 */
	public List<ApiRelation> getAllApiRelation();

	/**
	 * 根据边值获取api关系
	 * 
	 * @return
	 */
	public List<ApiRelation> getApiRelationByEdgeWeight(String weight);

	/**
	 * 根据第一个api获取该api所有的关系类
	 */
	public List<ApiRelation> getApiRelationByFirstId(int apiId);

	/**
	 * 根据两个api获取他们两个api的关系类
	 */
	public ApiRelation getApiRelationByDoubleApi(int firstApiId, int secondApiId);
}
