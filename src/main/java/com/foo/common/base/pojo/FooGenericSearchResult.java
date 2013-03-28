package com.foo.common.base.pojo;

import java.io.Serializable;
import java.util.List;

public class FooGenericSearchResult implements Serializable {
	private static final long serialVersionUID = 1L;

	protected List result;
	protected int totalCount = -1;

	/**
	 * The results of the search.
	 */
	public List getResult() {
		return result;
	}

	/**
	 * The results of the search.
	 */
	public void setResult(List results) {
		this.result = results;
	}

	/**
	 * The total number of results that would have been returned if no
	 * maxResults had been specified. (-1 means unspecified.)
	 */
	public int getTotalCount() {
		return totalCount;
	}

	/**
	 * The total number of results that would have been returned if no
	 * maxResults had been specified. (-1 means unspecified.)
	 */
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
}
