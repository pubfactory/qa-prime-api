package com.prime.pojo.searchResults;

import com.google.gson.annotations.SerializedName;

public class Results {

	public Results() {
	}

	@SerializedName("uri")
	String uri;

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getUri() {
		return uri;
	}

}