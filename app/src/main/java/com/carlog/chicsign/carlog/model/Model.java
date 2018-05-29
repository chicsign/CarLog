package com.carlog.chicsign.carlog.model;

import com.carlog.chicsign.carlog.Interface.FolderScrapModel;

public class Model implements FolderScrapModel {

	private int scrapSeq;           // 스크랩 순번
	private String price;
	private String liter;

	public Model() {
		this.scrapSeq = 0;
		this.price = "";
		this.liter = "";
	}
	@Override
	protected Object clone() throws CloneNotSupportedException {

		Model model = new Model();
		model.setScrapSeq(scrapSeq);
		model.setLiter(liter);
		model.setPrice(price);

		return model;
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