package com.cn.dao;

import java.util.List;

import com.cn.bean.WebApi;

/**
 * 
 * @author 徐新凯
 * @date 2017年4月10日 下午4:04:32
 * @description
 */
public interface WebApiDao {
	/**
	 * 根据关键词获取API信息,当关键词为空的时候是查询所有的api
	 * 
	 * @param keyWord
	 * @return WebApi List集合
	 */
	public List<WebApi> getWebApiByKeyWord(String keyWord);

	/**
	 * 根据api的id获取api的信息
	 * 
	 * @param api_id
	 * @return WebApi 实体
	 */
	public WebApi getWebApiById(int api_id);

	/**
	 * 根据被标记的tag的名字来获取所有的api
	 * 
	 * @param tagName
	 * @return
	 */
	public List<WebApi> getApiByTagName(String tagName);

	/**
	 * 根据api的id更新mashups字段的值
	 * 
	 * @param mashups
	 * @param apiId
	 * @return
	 */
	public int updateMashupsByApiId(String mashups, int apiId);
}
