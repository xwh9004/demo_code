package com.demo.truck.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author demo
 * @date 2017年7月6日
 */
public class QueryPageBase<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6216616710184446162L;

	private Integer start;

	private Integer length;

	private String orderBy;

	private String sort;

	private List<T> resultList = new ArrayList<T>();

	private Integer totalRows = 0;

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	public Integer getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(Integer totalRows) {
		this.totalRows = totalRows;
	}
	
	

}
