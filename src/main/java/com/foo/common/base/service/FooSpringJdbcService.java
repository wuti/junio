package com.foo.common.base.service;

import java.util.List;
import java.util.Map;

public interface FooSpringJdbcService {

	public int queryForInt(String sql);

	/**
	 * Do not use generic type here, as we can't indicate the return type.
	 * 
	 * @author Steve
	 * @param sql
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List queryForList(String sql);

	public Map<String, Object> queryForMap(String sql);

	public void executeSql(String sql);
	
	public int[] batchUpdate(List<String> sql);
}
