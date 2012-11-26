package com.foo.common.base.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class FooSpringJdbcServiceImpl implements FooSpringJdbcService {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public int queryForInt(String sql) {
		return this.jdbcTemplate.queryForInt(sql);
	}

	@SuppressWarnings("rawtypes")
	public List queryForList(String sql) {
		return this.jdbcTemplate.queryForList(sql);
	}

	public Map<String, Object> queryForMap(String sql) {
		return this.jdbcTemplate.queryForMap(sql);
	}

	public void executeSql(String sql) {
		this.jdbcTemplate.execute(sql);
	}

	public int[] batchUpdate(List<String> sql) {
		String[] batchsql = (String[]) sql.toArray(new String[sql.size()]);
		return this.jdbcTemplate.batchUpdate(batchsql);
	}
}
