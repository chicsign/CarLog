package com.carlog.chicsign.carlog.model;

import com.carlog.chicsign.carlog.Interface.ICarLog;

public class CarLogModel implements ICarLog {

	private int scrapSeq;           // 스크랩 순번
	private String price;
	private String liter;

	public CarLogModel() {
		this.scrapSeq = 0;
		this.price = "";
		this.liter = "";
	}
	@Override
	protected Object clone() throws CloneNotSupportedException {

		CarLogModel carLogModel = new CarLogModel();
		carLogModel.setScrapSeq(scrapSeq);
		carLogModel.setLiter(liter);
		carLogModel.setPrice(price);

		return carLogModel;
	}
	public void setScrapSeq(int scrapSeq) {
		this.scrapSeq = scrapSeq;
	}
	public void setPrice(String price){
		this.price = price;
	}
	public void setLiter(String liter){
		this.liter = liter;
	}


	public int getScrapSeq() {
		return scrapSeq;
	}
	public String getPrice() {
		return price;
	}
	public String getLiter() {
		return liter;
	}
}