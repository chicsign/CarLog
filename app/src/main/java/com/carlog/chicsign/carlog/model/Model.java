package com.carlog.chicsign.carlog.model;

public class Model {

	private String price;
	private String liter;

	public Model(String price , String liter) {
		this.price = price;
		this.liter = liter;
	}
	
	public String getPrice() {
		return price;
	}
	public String getLiter() {
		return liter;
	}
}