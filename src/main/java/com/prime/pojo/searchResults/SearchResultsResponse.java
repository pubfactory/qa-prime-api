package com.prime.pojo.searchResults;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class SearchResultsResponse {

	@SerializedName("results")
	private List<Results> results;

	@SerializedName("pagination")
	private Pagination pagination;

	@SerializedName("parameters")
	private List<String> parameters;

	@SerializedName("filters")
	private List<Filters> filters;

	public SearchResultsResponse() {

	}

	public void setResults(List<Results> results) {
		this.results = results;
	}

	public List<Results> getResults() {
		return results;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public Pagination getPagination() {
		return pagination;
	}

	public void setParameters(List<String> parameters) {
		this.parameters = parameters;
	}

	public List<String> getParameters() {
		return parameters;
	}

	public void setFilters(List<Filters> filters) {
		this.filters = filters;
	}

	public List<Filters> getFilters() {
		return filters;
	}

}