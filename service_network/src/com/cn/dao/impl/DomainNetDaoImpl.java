package com.cn.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.cn.bean.DomainNet;
import com.cn.dao.DomainNetDao;
import com.cn.util.JdbcSourceUtil;

public class DomainNetDaoImpl implements DomainNetDao {

	@Override
	public List<DomainNet> getAllDomain() {
		QueryRunner runner = JdbcSourceUtil.getQueryRunner();
		String sql = "select * from domainnet;";
		List<DomainNet> listdomain = null;
		try {
			listdomain = runner.query(sql, new BeanListHandler<DomainNet>(
					DomainNet.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listdomain;
	}

	@Override
	public List<DomainNet> getDomainByRealtionEdgeWeight(String weight) {
		QueryRunner runner = JdbcSourceUtil.getQueryRunner();
		String sql = "select DISTINCT d.* from domainnet d,domainrelation dr where d.domain_id in (dr.domain_id_one,dr.domain_id_two ) and dr.edge >= "
				+ weight + ";";
		List<DomainNet> listdomain = null;
		try {
			listdomain = runner.query(sql, new BeanListHandler<DomainNet>(
					DomainNet.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listdomain;
	}

	@Override
	public DomainNet getDomainNameById(int domainId) {
		QueryRunner runner = JdbcSourceUtil.getQueryRunner();
		String sql = "select * from domainnet where domain_id = ?;";
		Object[] obj = new Object[] { domainId };
		DomainNet domain = null;
		try {
			domain = runner.query(sql, new BeanHandler<DomainNet>(
					DomainNet.class), obj);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return domain;
	}

}
