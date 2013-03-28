package com.foo.common.base.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foo.common.base.dao.FooGenericDao;
import com.foo.common.base.pojo.FooGenericSearch;
import com.foo.common.base.pojo.FooGenericSearchResult;
import com.foo.common.base.pojo.FooGenericTransactionModel;

@Service
public class FooGenericServiceImpl<T> implements FooGenericService<T> {
	protected FooGenericDao<T, String> fooGenericDao;
	protected Logger logger = LoggerFactory
			.getLogger(this.getClass().getName());

	/**
	 * Inject your dao in your own serviceImpl like:
	 * 
	 * setFooGenericDao(fooDao);
	 * 
	 */
	@Autowired
	public void setFooGenericDao(FooGenericDao<T, String> fooGenericDao) {
		this.fooGenericDao = fooGenericDao;
	}

	public FooGenericDao<T, String> getFooGenericDao() {
		return this.fooGenericDao;
	}

	public Serializable save(Object entity) {
		return fooGenericDao.save(entity);
	}

	public boolean[] save(T... entities) {
		return null;
	}

	/**
	 * 拼接查询条件的泛型方法
	 * 
	 * An immutable java.util.Map containing parameter names as keys and
	 * parameter values as map values. The keys in the parameter map are of type
	 * String. The values in the parameter map are of type String array.
	 * 
	 * @author Steve
	 * @since August,2011
	 * 
	 */
	public String getSqlFromRequestMap(
			@SuppressWarnings("rawtypes") Map requestMap,
			FooGenericSearch search) {
		String clause;
		String clauseValue;
		for (Object keyObject : requestMap.keySet()) {
			clause = String.valueOf(keyObject);
			// This operation is safe, view j2ee5 API please.
			clauseValue = ((String[]) requestMap.get(clause))[0].toString();

			// 暂时不使用datatables列表控件，因此注释点一下搜索以及排序功能
			// else if (clause.indexOf("sSearch") != -1) {// 增加搜索功能 add by colin
			// // 2011-12-11
			// List<String> aColumns = this.getSeachColumn(requestMap);
			// // Preconditions.checkNotNull(aColumns);
			// if (aColumns != null && aColumns.size() > 0) {
			// /**
			// * 此searchClass还可以通过com.googlecode.genericdao.dao.
			// * DAOUtil从参数中获取; 形如:Class<T> persistentClass = (Class<T>)
			// * DAOUtil.getTypeArguments(GenericDAOImpl.class,
			// * this.getClass()).get(0);
			// *
			// */
			//
			// Class<?> rootClass = search.getSearchClass();
			// if (rootClass != null) {
			// // List<Filter> filters = new ArrayList<Filter>();
			// Filter[] filters = new Filter[aColumns.size() * 2];
			// Field[] fields = rootClass.getDeclaredFields();
			// List<String> stringList = new ArrayList<String>();
			// for (int m = 0; m < fields.length; m++) {
			// //
			// logger.info("fields["+m+"]="+field.getName()+"---fieldType="+field.getType().getName());
			// if (String.class.getName().equals(
			// fields[m].getType().getName())) {// 只有字段类型为STRING时，才添加对应的LIKE
			// stringList.add(fields[m].getName());
			// }
			// }
			// int count = 0;
			// for (int i = 0; i < aColumns.size(); i++) {
			// String property = StaticMethod
			// .nullObject2String(aColumns.get(i));
			// if (stringList.contains(property)) {
			// count++;
			// // 注：直接使用search.addFilterLike,会将多个属性使用and连接起来，而原意是用or
			// // search.addFilterLike(property, "%" +
			// // clauseValue.trim() + "%");
			// // filters.add(Filter.like(property, "%" +
			// // clauseValue.trim() + "%"));
			// filters[i] = Filter.like(property, "%"
			// + clauseValue.trim() + "%");
			// if ("true".equals(StaticMethod
			// .nullObject2String(StaticMethod
			// .getValue(requestMap,
			// "bSearchable_" + i)))
			// && !"".equals(StaticMethod.nullObject2String(StaticMethod
			// .getValue(requestMap,
			// "sSearch_" + i)))) {
			// filters[aColumns.size() * 2 - i - 1] = Filter
			// .like(property,
			// "%" + clauseValue.trim()
			// + "%");
			// // filters.add(Filter.like(property, "%" +
			// // clauseValue.trim() + "%"));
			// }
			// }
			// }
			// Filter[] s = new Filter[count];
			// for (int u = 0; u < filters.length; u++) {
			// for (int p = 0; p < s.length; p++) {
			// if (filters[u] != null
			// && !"".equals(filters[u])) {
			// if (s[p] == null) {
			// s[p] = filters[u];
			// break;
			// }
			// }
			// }
			// }
			//
			// search.addFilter(Filter.or(s));
			// }
			// }
			// } else if (clause.indexOf("iSortCol_0") != -1) {// 增加排序功能 add by
			// // colin 2011-12-11
			// Sort sort = new Sort();
			// List<String> aColumns = this.getSeachColumn(requestMap);
			// // Preconditions.checkNotNull(aColumns);
			// if (aColumns != null && aColumns.size() > 0) {
			// int iSortingCols = StaticMethod.nullObject2int(StaticMethod
			// .getValue(requestMap, "iSortingCols"));
			// if (iSortingCols > 0) {
			// for (int i = 0; i < iSortingCols; i++) {
			// int index = StaticMethod
			// .nullObject2int(StaticMethod.getValue(
			// requestMap, "iSortCol_" + i));
			// if ("true".equals(StaticMethod
			// .nullObject2String(StaticMethod.getValue(
			// requestMap, "bSortable_" + index)))) {
			// String property = StaticMethod
			// .nullObject2String(aColumns.get(index));
			// if (!"".equals(property)) {
			// sort.setProperty(property);
			// }
			// String order = StaticMethod
			// .nullObject2String(StaticMethod
			// .getValue(requestMap,
			// "sSortDir_" + i));
			// if ("desc".equals(order)) {
			// sort.setDesc(true);
			// } else {
			// sort.setDesc(false);
			// }
			// }
			// search.addSort(sort);
			// }
			// }
			// }
			// }

		}
		logger.info("search=" + search);
		return "";
	}

	public List<String> getSeachColumn(
			@SuppressWarnings("rawtypes") Map requestMap) {
		String clause;
		String clauseValue;
		List<String> listSearch = new ArrayList<String>();
		for (Object keyObject : requestMap.keySet()) {
			clause = String.valueOf(keyObject);
			if (clause.indexOf("mDataProp") != -1) {
				clauseValue = ((String[]) requestMap.get(clause))[0].toString();
				listSearch.add(clauseValue);
			}
		}
		return listSearch;
	}

	@Override
	public void doInTransaction(
			FooGenericTransactionModel fooGenericTransactionModel) {
		Session session = fooGenericDao.getSession();
		Transaction tx = session.getTransaction();

		// See:http://docs.jboss.org/hibernate/orm/4.1/manual/en-US/html/ch13.html#transactions-demarcation-nonmanaged
		try {
			fooGenericTransactionModel.execute();
			// tx.setTimeout(200);
		} catch (RuntimeException e) {
			e.printStackTrace();
			throw e;
		} finally {
			// session.close();
		}
	}

	@Override
	public List<T> search(FooGenericSearch search) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T searchUnique(FooGenericSearch search) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int count(FooGenericSearch search) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public FooGenericSearchResult searchAndCount(
			FooGenericSearch fooGenericSearch) {
		return fooGenericDao.searchAndCount(fooGenericSearch);
	}

	@Override
	public T find(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T[] find(String... ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T getReference(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T[] getReferences(String... ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(T entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void remove(T... entities) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove(List<T> entities) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean removeById(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeByIds(String... ids) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<T> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAttached(T entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void refresh(T... entities) {
		// TODO Auto-generated method stub

	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Object object) {
		fooGenericDao.delete(object);
	}

	@Override
	public void delete(String entityName, Object object) {
		fooGenericDao.delete(entityName, object);
	}

}
