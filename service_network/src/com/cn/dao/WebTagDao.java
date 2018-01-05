package com.cn.dao;

import java.util.List;

import com.cn.bean.WebTag;

/**
 * 
 * @author 徐新凯
 * @date 2017年4月23日 下午9:10:47
 * @description
 */
public interface WebTagDao {

	/**
	 * 添加tag参数
	 */
	public int addTag(WebTag tag);

	/**
	 * 获得所有的tag
	 * 
	 * @return
	 */
	public List<WebTag> getAllTags();
}
