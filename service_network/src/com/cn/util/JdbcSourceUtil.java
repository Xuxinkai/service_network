package com.cn.util;

import org.apache.commons.dbutils.QueryRunner;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 
 * @author 徐新凯
 * @date 2017年3月17日 下午10:41:44
 * @description 数据库连接帮助类
 */
public class JdbcSourceUtil {

	private static ComboPooledDataSource dataSource;

	static {
		dataSource = new ComboPooledDataSource();
	}

	public static QueryRunner getQueryRunner() {

		return new QueryRunner(dataSource);
	}
}
