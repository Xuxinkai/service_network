package com.cn.dao;

import java.util.List;

import com.cn.bean.WebMashup;

/**
 * 
 * @author 徐新凯
 * @date 2017年4月10日 下午4:20:43
 * @description
 */
public interface WebMashupDao {
	/**
	 * 根据关键词获取mashup,关键词为空的时候就是搜索所有的mashup
	 * 
	 * @param keyWord
	 * @return
	 */
	public List<WebMashup> getMashupByKeyWord(String keyWord);

	/**
	 * 根据mashup的id获取mashup的信息
	 * 
	 * @param mashup_id
	 * @return
	 */
	public WebMashup getMashupById(int mashup_id);

	/**
	 * 根据调用的api名字来获得所有mashup
	 * 
	 * @param apiName
	 * @return
	 */
	public List<WebMashup> getMashupByApiName(String apiName);

	/**
	 * 根据被标记的tag名字来获得所有mashup
	 * 
	 * @param tagName
	 * @return
	 */
	public List<WebMashup> getMashupByTagName(String tagName);

}
