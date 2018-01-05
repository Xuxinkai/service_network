package com.cn.dao;

import java.util.List;

import com.cn.bean.TagNet;

/**
 * 
 * @author 徐新凯
 * @date 2017年3月22日 下午2:52:50
 * @description
 */
public interface TagNetDao {
	/**
	 * 获取所有的tag.net
	 */
	public List<TagNet> getAllTag();

	/**
	 * 根据相似度值来获取tag
	 * 
	 * @param weight
	 * @return
	 */
	public List<TagNet> getTagByRealtionEdgeWeight(String weight);

	/**
	 * 根据tag 的id获取tag的名字
	 */
	public TagNet getTagNameById(int tagId);
}
