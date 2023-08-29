package com.prime.pojo.searchResults;

import java.util.Date;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Pagination {

	public Pagination() {
	}

	@SerializedName("totalResults")
	int totalResults;

	@SerializedName("pageSize")
	int pageSize;

	@SerializedName("pageNumber")
	int pageNumber;

	@SerializedName("sortOrder")
	SortOrder sortOrder;

	@SerializedName("validPageSizes")
	Date validPageSizes;

	@SerializedName("sortOptions")
	List<SortOptions> sortOptions;

	public void setTotalResults(int totalResults) {
		this.totalResults = totalResults;
	}

	public int getTotalResults() {
		return totalResults;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setSortOrder(SortOrder sortOrder) {
		this.sortOrder = sortOrder;
	}

	public SortOrder getSortOrder() {
		return sortOrder;
	}

	public void setValidPageSizes(Date validPageSizes) {
		this.validPageSizes = validPageSizes;
	}

	public Date getValidPageSizes() {
		return validPageSizes;
	}

	public void setSortOptions(List<SortOptions> sortOptions) {
		this.sortOptions = sortOptions;
	}

	public List<SortOptions> getSortOptions() {
		return sortOptions;
	}

}