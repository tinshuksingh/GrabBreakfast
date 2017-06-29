package com.tinsh.sampleFreemarker;

public class Restaurant {

	String restId;
	String restName;
	public String getRestId() {
		return restId;
	}
	public void setRestId(String restId) {
		this.restId = restId;
	}
	public String getRestName() {
		return restName;
	}
	public void setRestName(String restName) {
		this.restName = restName;
	}
	@Override
	public String toString() {
		return "Restaurant [restId=" + restId + ", restName=" + restName + "]";
	}
	
}
