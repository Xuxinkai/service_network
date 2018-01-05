package com.cn.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.cn.bean.DomainRelation;
import com.cn.dao.DomainRelationDao;
import com.cn.util.JdbcSourceUtil;

/**
 * 
 * @author 徐新凯
 * @date 2017年3月22日 下午4:14:00
 * @description
 */
public class DomainRelationDaoImpl implements DomainRelationDao {

	@Override
	public List<DomainRelation> getAllDomainRelation() {
		QueryRunner runner = JdbcSourceUtil.getQueryRunner();
		String sql = "select * from domainrelation;";
		List<DomainRelation> listDomainRelation = null;
		try {
			listDomainRelation = runner.query(sql,
					new BeanListHandler<DomainRelation>(DomainRelation.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listDomainRelation;
	}

	@Override
	public List<DomainRelation> getDomainRelationByEdgeWeight(String weight) {
		QueryRunner runner = JdbcSourceUtil.getQueryRunner();
		String sql = "select * from domainrelation where edge >= " + weight
				+ ";";
		List<DomainRelation> listDomainRelation = null;
		try {
			listDomainRelation = runner.query(sql,
					new BeanListHandler<DomainRelation>(DomainRelation.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listDomainRelation;
	}

	@Override
	public List<DomainRelation> getDomainRelationByFirstId(int domainId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DomainRelation getDomainRelationByDoubleDomain(int firstDomainId,
			int secondDomainId) {
		// TODO Auto-generated method stub
		return null;
	}

}
