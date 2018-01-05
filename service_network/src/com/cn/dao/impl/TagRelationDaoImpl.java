package com.cn.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.cn.bean.TagRelation;
import com.cn.dao.TagRelationDao;
import com.cn.util.JdbcSourceUtil;

/**
 * 
 * @author 徐新凯
 * @date 2017年3月22日 下午3:22:18
 * @description
 */
public class TagRelationDaoImpl implements TagRelationDao {

	@Override
	public List<TagRelation> getAllTagRelation() {
		QueryRunner runner = JdbcSourceUtil.getQueryRunner();
		String sql = "select * from tagrelation;";
		List<TagRelation> listrelation = null;
		try {
			listrelation = runner.query(sql, new BeanListHandler<TagRelation>(
					TagRelation.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listrelation;
	}

	@Override
	public List<TagRelation> getTagRelationByEdgeWeight(String weight) {
		QueryRunner runner = JdbcSourceUtil.getQueryRunner();
		String sql = "select * from tagrelation where edge>=" + weight + ";";
		List<TagRelation> listrelation = null;
		try {
			listrelation = runner.query(sql, new BeanListHandler<TagRelation>(
					TagRelation.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listrelation;
	}

	@Override
	public List<TagRelation> getTagRelationByFirstId(int tagId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TagRelation getTagRelationByDoubleTag(int firstTagId, int secondTagId) {
		// TODO Auto-generated method stub
		return null;
	}

}
