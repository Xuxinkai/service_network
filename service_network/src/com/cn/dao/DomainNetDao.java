package com.cn.dao;

import java.util.List;

import com.cn.bean.DomainNet;

/**
 * 
 * @author 徐新凯
 * @date 2017年3月22日 下午3:46:27
 * @description
 */
public interface DomainNetDao {

	/**
	 * 获取所有的domain.net
	 */
	public List<DomainNet> getAllDomain();

	/**
	 * 根据相似度值来获取domain
	 * 
	 * @param weight
	 * @return
	 */
	public List<DomainNet> getDomainByRealtionEdgeWeight(String weight);

	/**
	 * 根据domain 的id获取domain的名字
	 */
	public DomainNet getDomainNameById(int domainId);
}
