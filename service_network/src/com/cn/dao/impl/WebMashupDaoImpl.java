package com.cn.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.cn.bean.WebMashup;
import com.cn.dao.WebMashupDao;
import com.cn.util.JdbcSourceUtil;

/**
 * 
 * @author 徐新凯
 * @date 2017年4月10日 下午4:24:04
 * @description 对WebMashupDao的实现
 */
public class WebMashupDaoImpl implements WebMashupDao {

	@Override
	public List<WebMashup> getMashupByKeyWord(String keyWord) {
		QueryRunner runner = JdbcSourceUtil.getQueryRunner();
		String sql1 = "select DISTINCT * from webmashup where mashup_name like '%"
				+ keyWord
				+ "%' or mashup_apis like '%"
				+ keyWord
				+ "%' or mashup_tags like '%" + keyWord + "%';";
		String sql2 = "select * from webmashup;";
		List<WebMashup> listMashup = null;
		try {
			if (keyWord == null || keyWord == "") {
				listMashup = runner.query(sql2, new BeanListHandler<WebMashup>(
						WebMashup.class));
			} else {
				listMashup = runner.query(sql1, new BeanListHandler<WebMashup>(
						WebMashup.class));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listMashup;
	}

	@Override
	public WebMashup getMashupById(int mashup_id) {
		QueryRunner runner = JdbcSourceUtil.getQueryRunner();
		String sql = "select  * from webmashup where mashup_id=" + mashup_id
				+ ";";
		WebMashup mashup = null;
		try {
			mashup = runner.query(sql, new BeanHandler<WebMashup>(
					WebMashup.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mashup;
	}

	@Override
	public List<WebMashup> getMashupByApiName(String apiName) {
		QueryRunner runner = JdbcSourceUtil.getQueryRunner();
		String sql = "select  * from webmashup where mashup_apis like \"%"
				+ apiName + "%\";";
		List<WebMashup> mashups = null;
		try {
			mashups = runner.query(sql, new BeanListHandler<WebMashup>(
					WebMashup.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mashups;
	}

	@Override
	public List<WebMashup> getMashupByTagName(String tagName) {
		QueryRunner runner = JdbcSourceUtil.getQueryRunner();
		String sql = "select  * from webmashup where mashup_tags like \"% " + tagName + " %\";";
		List<WebMashup> mashups = null;
		try {
			mashups = runner.query(sql, new BeanListHandler<WebMashup>(
					WebMashup.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mashups;
	}

}
