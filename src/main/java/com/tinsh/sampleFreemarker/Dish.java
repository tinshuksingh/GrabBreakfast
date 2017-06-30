package com.tinsh.sampleFreemarker;

public class Dish {
	
	String dishId;
	String dishName;
	String dishPrice;
	String dishDesc;
	public String getDishId() {
		return dishId;
	}
	public void setDishId(String dishId) {
		this.dishId = dishId;
	}
	public String getDishName() {
		return dishName;
	}
	public void setDishName(String dishName) {
		this.dishName = dishName;
	}
	public String getDishPrice() {
		return dishPrice;
	}
	public void setDishPrice(String dishPrice) {
		this.dishPrice = dishPrice;
	}
	public String getDishDesc() {
		return dishDesc;
	}
	public void setDishDesc(String dishDesc) {
		this.dishDesc = dishDesc;
	}
	@Override
	public String toString() {
		return "Dish [dishId=" + dishId + ", dishName=" + dishName + ", dishPrice=" + dishPrice + ", dishDesc="
				+ dishDesc + "]";
	}

}
