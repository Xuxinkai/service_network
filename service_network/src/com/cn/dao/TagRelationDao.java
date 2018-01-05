package com.cn.dao;

import java.util.List;

import com.cn.bean.TagRelation;

/**
 * 
 * @author 徐新凯
 * @date 2017年3月22日 下午3:15:21
 * @description
 */
public interface TagRelationDao {

	/**
	 * 获取所有的tag关系
	 */
	public List<TagRelation> getAllTagRelation();

	/**
	 * 根据边值获取tag关系
	 * 
	 * @return
	 */
	public List<TagRelation> getTagRelationByEdgeWeight(String weight);

	/**
	 * 根据第一个tag获取该tag所有的关系类
	 */
	public List<TagRelation> getTagRelationByFirstId(int tagId);

	/**
	 * 根据两个tag获取他们两个tag的关系类
	 */
	public TagRelation getTagRelationByDoubleTag(int firstTagId, int secondTagId);

}
