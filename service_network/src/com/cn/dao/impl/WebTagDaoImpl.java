package com.cn.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.cn.bean.WebTag;
import com.cn.dao.WebTagDao;
import com.cn.util.JdbcSourceUtil;

/**
 * 
 * @author 徐新凯
 * @date 2017年4月23日 下午9:11:22
 * @description
 */
public class WebTagDaoImpl implements WebTagDao {

	@Override
	public int addTag(WebTag tag) {
		QueryRunner runner = JdbcSourceUtil.getQueryRunner();
		String sql = "insert into webtag values(?,?,?,?);";
		Object[] objs = new Object[] { tag.getTag_id(), tag.getTag_name(),
				tag.getTag_apis(), tag.getTag_mashups() };
		int result = 0;
		try {
			result = runner.update(sql, objs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<WebTag> getAllTags() {
		QueryRunner runner = JdbcSourceUtil.getQueryRunner();
		String sql = "select * from webtag;";
		List<WebTag> listTag = null;
		try {
			listTag = runner.query(sql, new BeanListHandler<WebTag>(
					WebTag.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listTag;
	}

}
