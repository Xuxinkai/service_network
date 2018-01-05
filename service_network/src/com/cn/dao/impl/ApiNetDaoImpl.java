package com.cn.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.cn.bean.ApiNet;
import com.cn.dao.ApiNetDao;
import com.cn.util.JdbcSourceUtil;

/**
 * 
 * @author 徐新凯
 * @date 2017年3月18日 下午12:00:44
 * @description 对ApiNetDao的实现
 */
public class ApiNetDaoImpl implements ApiNetDao {

	@Override
	public List<ApiNet> getAllApi() {
		QueryRunner runner = JdbcSourceUtil.getQueryRunner();
		String sql = "select * from apinet;";
		List<ApiNet> listapi = null;
		try {
			listapi = runner.query(sql, new BeanListHandler<ApiNet>(
					ApiNet.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listapi;
	}

	@Override
	public ApiNet getApiNameById(int apiId) {
		QueryRunner runner = JdbcSourceUtil.getQueryRunner();
		String sql = "select * from apinet where api_id = ?;";
		ApiNet apiNet = null;
		Object[] obj = new Object[] { apiId };
		try {
			apiNet = runner.query(sql, new BeanHandler<ApiNet>(ApiNet.class),
					obj);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return apiNet;
	}

	@Override
	public List<ApiNet> getApiByRealtionEdgeWeight(String weight) {
		QueryRunner runner = JdbcSourceUtil.getQueryRunner();
		String sql = "select DISTINCT a.* from apinet a,apirelation ar where a.api_id in (ar.api_id_one,ar.api_id_two) and ar.edge>="
				+ weight + ";";
		List<ApiNet> listapi = null;
		try {
			listapi = runner.query(sql, new BeanListHandler<ApiNet>(
					ApiNet.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listapi;
	}

}
