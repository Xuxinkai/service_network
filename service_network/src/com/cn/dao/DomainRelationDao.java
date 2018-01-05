package com.cn.dao;

import java.util.List;

import com.cn.bean.DomainRelation;

/**
 * 
 * @author 徐新凯
 * @date 2017年3月22日 下午4:12:00
 * @description
 */
public interface DomainRelationDao {
	/**
	 * 获取所有的domain关系
	 */
	public List<DomainRelation> getAllDomainRelation();

	/**
	 * 根据边值获取domain关系
	 * 
	 * @return
	 */
	public List<DomainRelation> getDomainRelationByEdgeWeight(String weight);

	/**
	 * 根据第一个domain获取该domain所有的关系类
	 */
	public List<DomainRelation> getDomainRelationByFirstId(int domainId);

	/**
	 * 根据两个domain获取他们两个domain的关系类
	 */
	public DomainRelation getDomainRelationByDoubleDomain(int firstDomainId,
			int secondDomainId);
}
