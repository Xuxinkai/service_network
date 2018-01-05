package com.cn.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.cn.bean.WebApi;
import com.cn.dao.WebApiDao;
import com.cn.util.JdbcSourceUtil;

public class WebApiDaoImpl implements WebApiDao {

	@Override
	public List<WebApi> getWebApiByKeyWord(String keyWord) {
		QueryRunner runner = JdbcSourceUtil.getQueryRunner();
		String sql1 = "select DISTINCT * from webapi where api_name like '%"
				+ keyWord + "%' or api_tags like '%" + keyWord
				+ "%' or api_comments like '%" + keyWord
				+ "%' or api_category like '%" + keyWord + "%';";
		String sql2 = "select * from webapi;";
		List<WebApi> apiList = null;
		try {
			if (keyWord == null || keyWord == "") {
				apiList = runner.query(sql2, new BeanListHandler<WebApi>(
						WebApi.class));
			} else {
				apiList = runner.query(sql1, new BeanListHandler<WebApi>(
						WebApi.class));
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return apiList;
	}

	@Override
	public WebApi getWebApiById(int api_id) {
		QueryRunner runner = JdbcSourceUtil.getQueryRunner();
		String sql = "select * from webapi where api_id=?; ";
		Object[] obj = new Object[] { api_id };
		WebApi api = null;
		try {
			api = runner.query(sql, new BeanHandler<WebApi>(WebApi.class), obj);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return api;
	}

	@Override
	public List<WebApi> getApiByTagName(String tagName) {
		QueryRunner runner = JdbcSourceUtil.getQueryRunner();
		String sql = "select * from webapi where api_tags  like \"% " + tagName + " %\";";
		List<WebApi> listApi = null;
		try {
			listApi = runner.query(sql, new BeanListHandler<WebApi>(WebApi.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listApi;
	}

	@Override
	public int updateMashupsByApiId(String mashups, int apiId) {
		QueryRunner runner = JdbcSourceUtil.getQueryRunner();
		String sql = "update webapi set webapi.api_mashups = ? where  webapi.api_id = ?; ";
		Object[] objs = new Object[] { mashups, apiId };
		int result = 0;
		try {
			result = runner.update(sql, objs);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

}
