package com.accAyo.serverProjectDemo.framwork.util;

import org.apache.poi.ss.formula.functions.T;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ResultFilter<E> implements Serializable {

	private static final long serialVersionUID = -2199614767549628572L;

	private final static int DEFAULT_NAVIGATOR_SIZE = 5;

	private int currentPage;

	private int pageSize;
	
	

	private int totalCount;

	private boolean haveNextPage;

	private boolean havePrePage;

	private int navigatorSize;
	
	//gt add
	private int pageCount;
	//开始页码数
	//private int startPage;
	//结束页码数
	//private int endPage;
	//显示多少页
	private int showLength=10;
	
	
	private List items;

	public ResultFilter(int totalCount, int pageSize, int currentPage) {
		this(totalCount, pageSize, currentPage, DEFAULT_NAVIGATOR_SIZE);
	}

	public ResultFilter(int totalCount, int pageSize, int currentPage,
			int navigatorSize) {
		this.totalCount = totalCount;
		this.pageSize = pageSize;
		this.currentPage = currentPage;
		this.navigatorSize = navigatorSize;
	}

	public ResultFilter(List list, int pageSize, int currentPage) {
		this(list, pageSize, currentPage, DEFAULT_NAVIGATOR_SIZE);
	}

    public ResultFilter(List list, int pageSize, int currentPage, int navigatorSize) {
        this(list.size(), pageSize, currentPage, navigatorSize);
        this.init(list);
    }

	public void init(List list) {
		int startIndex = (this.currentPage - 1) * this.pageSize;
		int endIndex = (this.currentPage * this.pageSize);
		endIndex = endIndex <= list.size() ? endIndex : list.size();

        List tempList = new ArrayList<T>();
        for (int i =  startIndex; i < endIndex; i++) {
            tempList.add(list.get(i));
		}
		this.items = tempList;
	}

	public int getPageCount() {
		int pageCount = 0;
		if (pageSize != 0) {
			pageCount = totalCount / pageSize;
			if (totalCount % pageSize != 0) {
				pageCount++;
			}
		}
		return pageCount;
	}

	public int getCurrentPage() {
		currentPage = currentPage < getPageCount() ? currentPage
				: getPageCount();
		currentPage = currentPage < 1 ? 1 : currentPage;

		return currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public boolean getHaveNextPage() {
		haveNextPage = false;
		if ((getPageCount() > 1) && (getPageCount() > getCurrentPage())) {
			haveNextPage = true;
		}
		return haveNextPage;
	}

	public boolean getHavePrePage() {
		havePrePage = false;
		if ((getPageCount() > 1) && (currentPage > 1)) {
			havePrePage = true;
		}
		return havePrePage;
	}

	public int getNext(){
		int next=this.getCurrentPage();
		if ((getPageCount() > 1) && (getPageCount() > getCurrentPage())) {
			next++;
		}
		return next;
	}
	public int getLast(){
		int last = this.getCurrentPage();
		if ((getPageCount() > 1) && (currentPage > 1)) {
			last--;
		}
		return last;
	}
	private int getNavigatorIndex(boolean isBegin) {
		int beginNavigatorIndex = getCurrentPage() - navigatorSize / 2;
		int endNavigatorIndex = getCurrentPage() + navigatorSize / 2;
		beginNavigatorIndex = beginNavigatorIndex < 1 ? 1 : beginNavigatorIndex;
		endNavigatorIndex = endNavigatorIndex < getPageCount() ? endNavigatorIndex
				: getPageCount();
		while ((endNavigatorIndex - beginNavigatorIndex) < navigatorSize
				&& (beginNavigatorIndex != 1 || endNavigatorIndex != getPageCount())) {
			if (beginNavigatorIndex > 1) {
				beginNavigatorIndex--;
			}
			else if (endNavigatorIndex < getPageCount()) {
				endNavigatorIndex++;
			}
		}

		if (isBegin) {
			return beginNavigatorIndex;
		}
		else {
			return endNavigatorIndex;
		}
	}

	public int getBeginNavigatorIndex() {
		return getNavigatorIndex(true);
	}

	public int getEndNavigatorIndex() {
		return getNavigatorIndex(false);
	}

	public List<E> getItems() {
		return items;
	}

	public void setItems(List<E> items) {
		this.items = items;
	}
 
	public int getStartPage() {
		int startPage=1;
		if(this.getPageCount()==0)
			return 1;
		startPage = this.getCurrentPage()-showLength/2;
		
		if(startPage<=0)
			startPage=1;
		return startPage;
	}

	/*public void setStartPage(int startPage) {
		this.startPage = startPage;
	}*/

	public int getEndPage() {
		int endPage=0;
		if(this.getStartPage()+showLength-1>this.getPageCount())
			endPage=getPageCount();
		else
			endPage=this.getStartPage()+showLength-1;
		return endPage;
	}

	/*public void setEndPage(int endPage) {
		this.endPage = endPage;
	}*/

	public int getShowLength() {
		return showLength;
	}

	public void setShowLength(int showLength) {
		this.showLength = showLength;
	}
	
	

}