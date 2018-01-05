package com.cn.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.cn.bean.WebTagRelation;
import com.cn.dao.WebTagRelationDao;
import com.cn.util.JdbcSourceUtil;

/**
 * 
 * @author 徐新凯
 * @date 2017年4月25日 下午4:35:48
 * @description 对WebTagRelationDao的实现
 */
public class WebTagRelationDaoImpl implements WebTagRelationDao {

	@Override
	public int addWebTagRelation(WebTagRelation wtr) {
		QueryRunner runner = JdbcSourceUtil.getQueryRunner();
		String sql = "insert into webtagrelation values(?,?,?,?,?);";
		Object[] objs = new Object[] { wtr.getRelation_id(), wtr.getTag_one(),
				wtr.getTag_two(), wtr.getWeight(), wtr.getDesc() };
		int result = 0;
		try {
			result = runner.update(sql, objs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<WebTagRelation> getWebTagRelationByWeight(String weight) {
		QueryRunner runner = JdbcSourceUtil.getQueryRunner();
		String sql = "select * from webtagrelation where weight >= " + weight
				+ ";";
		List<WebTagRelation> listWTR = null;
		try {
			listWTR = runner.query(sql, new BeanListHandler<WebTagRelation>(
					WebTagRelation.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listWTR;
	}

}
