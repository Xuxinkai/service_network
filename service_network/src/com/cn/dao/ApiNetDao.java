package com.cn.dao;

import java.util.List;

import com.cn.bean.ApiNet;

/**
 * 
 * @author 徐新凯
 * @date 2017年3月17日 下午10:32:16
 * @description
 */
public interface ApiNetDao {
	/**
	 * 获取所有的api.net
	 */
	public List<ApiNet> getAllApi();

	/**
	 * 根据相似度值来获取api
	 * 
	 * @param weight
	 * @return
	 */
	public List<ApiNet> getApiByRealtionEdgeWeight(String weight);

	/**
	 * 根据api 的id获取api的名字
	 */
	public ApiNet getApiNameById(int apiId);

}
