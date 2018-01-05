package com.cn.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.cn.bean.ApiRelation;
import com.cn.dao.ApiRelationDao;
import com.cn.util.JdbcSourceUtil;

/**
 * 
 * @author 徐新凯
 * @date 2017年3月17日 下午11:22:11
 * @description
 */
public class ApiRelationDaoImpl implements ApiRelationDao {

	@Override
	public List<ApiRelation> getAllApiRelation() {
		QueryRunner runner = JdbcSourceUtil.getQueryRunner();
		String sql = "select * from apirelation where edge>0.02;";
		List<ApiRelation> listApiRelation = null;
		try {
			listApiRelation = runner.query(sql,
					new BeanListHandler<ApiRelation>(ApiRelation.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listApiRelation;
	}

	@Override
	public List<ApiRelation> getApiRelationByFirstId(int apiId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiRelation getApiRelationByDoubleApi(int firstApiId, int secondApiId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ApiRelation> getApiRelationByEdgeWeight(String weight) {
		QueryRunner runner = JdbcSourceUtil.getQueryRunner();
		String sql = "select * from apirelation where edge >= " + weight + ";";
		List<ApiRelation> listApiRelation = null;
		try {
			listApiRelation = runner.query(sql,
					new BeanListHandler<ApiRelation>(ApiRelation.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listApiRelation;
	}

}
