package com.prime.pojo.searchResults;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class AdvancedValues {
	public AdvancedValues() {
	}

	@SerializedName("availableFields")
	List<String> availableFields;

	@SerializedName("availableOperators")
	List<String> availableOperators;

	@SerializedName("currentTerms")
	List<String> currentTerms;

	@SerializedName("currentOperators")
	List<String> currentOperators;

	public void setAvailableFields(List<String> availableFields) {
		this.availableFields = availableFields;
	}

	public List<String> getAvailableFields() {
		return availableFields;
	}

	public void setAvailableOperators(List<String> availableOperators) {
		this.availableOperators = availableOperators;
	}

	public List<String> getAvailableOperators() {
		return availableOperators;
	}

	public void setCurrentTerms(List<String> currentTerms) {
		this.currentTerms = currentTerms;
	}

	public List<String> getCurrentTerms() {
		return currentTerms;
	}

	public void setCurrentOperators(List<String> currentOperators) {
		this.currentOperators = currentOperators;
	}

	public List<String> getCurrentOperators() {
		return currentOperators;
	}

}