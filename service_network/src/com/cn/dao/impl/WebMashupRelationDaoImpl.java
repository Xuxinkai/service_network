package com.cn.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.cn.bean.WebMashupRelation;
import com.cn.dao.WebMashupRelationDao;
import com.cn.util.JdbcSourceUtil;

/**
 * 
 * @author 徐新凯
 * @date 2017年4月24日 下午12:07:17
 * @description 对WebMashupRelationDao的实现
 */
public class WebMashupRelationDaoImpl implements WebMashupRelationDao {

	@Override
	public int addWebMashupRelation(WebMashupRelation wmr) {
		QueryRunner runner = JdbcSourceUtil.getQueryRunner();
		String sql = "insert into webmashuprelation values(?,?,?,?,?);";
		Object[] objs = new Object[] { wmr.getRelation_id(),
				wmr.getMashup_one(), wmr.getMashup_two(), wmr.getWeight(),
				wmr.getDesc() };
		int result = 0;
		try {
			result = runner.update(sql, objs);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public List<WebMashupRelation> getWebMashupRelationByWeight(String weight) {
		QueryRunner runner = JdbcSourceUtil.getQueryRunner();
		String sql = "select * from webmashuprelation where weight >= "
				+ weight + ";";
		List<WebMashupRelation> listWMR = null;
		try {
			listWMR = runner.query(sql, new BeanListHandler<WebMashupRelation>(
					WebMashupRelation.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listWMR;
	}

}
