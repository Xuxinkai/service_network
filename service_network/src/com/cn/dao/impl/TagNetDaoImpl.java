package com.cn.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.cn.bean.TagNet;
import com.cn.dao.TagNetDao;
import com.cn.util.JdbcSourceUtil;

public class TagNetDaoImpl implements TagNetDao {

	@Override
	public List<TagNet> getAllTag() {
		QueryRunner runner = JdbcSourceUtil.getQueryRunner();
		String sql = "select * from tagnet;";
		List<TagNet> listtag = null;

		try {
			listtag = runner.query(sql, new BeanListHandler<TagNet>(
					TagNet.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listtag;
	}

	@Override
	public List<TagNet> getTagByRealtionEdgeWeight(String weight) {
		QueryRunner runner = JdbcSourceUtil.getQueryRunner();
		String sql = "select DISTINCT t.* from tagnet t,tagrelation tr where t.tag_id in (tr.tag_id_one,tr.tag_id_two) and tr.edge >= "
				+ weight + " ;";
		List<TagNet> listtag = null;

		try {
			listtag = runner.query(sql, new BeanListHandler<TagNet>(
					TagNet.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listtag;
	}

	@Override
	public TagNet getTagNameById(int tagId) {
		QueryRunner runner = JdbcSourceUtil.getQueryRunner();
		String sql = "select * from tagnet where tag_id = ?";
		Object[] obj = new Object[] { tagId };
		TagNet tagNet = null;
		try {
			tagNet = runner.query(sql, new BeanHandler<TagNet>(TagNet.class),
					obj);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tagNet;
	}

}
