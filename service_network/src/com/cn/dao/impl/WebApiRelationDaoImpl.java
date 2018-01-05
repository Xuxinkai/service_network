package com.cn.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.cn.bean.WebApiRelation;
import com.cn.dao.WebApiRelationDao;
import com.cn.util.JdbcSourceUtil;

/**
 * 
 * @author 徐新凯
 * @date 2017年4月25日 下午4:05:19
 * @description 对WebApiRelationDao的实现
 */
public class WebApiRelationDaoImpl implements WebApiRelationDao {

	@Override
	public int addWebApiRealtion(WebApiRelation war) {
		QueryRunner runner = JdbcSourceUtil.getQueryRunner();
		String sql = "insert into webapirelation values(?,?,?,?,?);";
		Object[] objs = new Object[] { war.getRelation_id(), war.getApi_one(),
				war.getApi_two(), war.getWeight(), war.getDesc() };
		int result = 0;
		try {
			result = runner.update(sql, objs);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public List<WebApiRelation> getWebApiRelationByWeight(String weight) {
		QueryRunner runner = JdbcSourceUtil.getQueryRunner();
		String sql = "select * from webapirelation where weight >= " + weight
				+ ";";
		List<WebApiRelation> listWAR = null;
		try {
			listWAR = runner.query(sql, new BeanListHandler<WebApiRelation>(
					WebApiRelation.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listWAR;
	}

}
