package com.foo.common.base.pojo;

import java.io.Serializable;

public class FooGenericSearch implements Serializable {
	private static final long serialVersionUID = 1L;
	protected int firstResult = -1; // -1 stands for unspecified

	protected int maxResults = -1; // -1 stands for unspecified
	private String countHql;
	private String queryHql;

	public String getCountHql() {
		return countHql;
	}

	public void setCountHql(String countHql) {
		this.countHql = countHql;
	}

	public String getQueryHql() {
		return queryHql;
	}

	public void setQueryHql(String queryHql) {
		this.queryHql = queryHql;
	}

	public int getFirstResult() {
		return firstResult;
	}

	public void setFirstResult(int firstResult) {
		this.firstResult = firstResult;
	}

	public int getMaxResults() {
		return maxResults;
	}

	public void setMaxResults(int maxResults) {
		this.maxResults = maxResults;
	}
}
