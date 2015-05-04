package com.limetray.assignement.Beans;

import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ItemsBeans {

	private SimpleIntegerProperty seqNo;
	private SimpleStringProperty itemNameCo;
	private SimpleDoubleProperty pricePerItemCo;
	private SimpleIntegerProperty noOfItemCo;
	private SimpleDoubleProperty totalPriceCo;
	private SimpleStringProperty dateTime;
	private SimpleDoubleProperty totalPriceInAllItems;
	
	
	public ItemsBeans(int seqNoC,
			String itemName, double itemPrice,
			int noOfItems, double totalPrice,
			String dateC,double totalPriceInAllItemsC) {
		super();
		this.seqNo = new SimpleIntegerProperty(seqNoC);
		this.itemNameCo = new SimpleStringProperty(itemName);
		this.pricePerItemCo = new SimpleDoubleProperty(itemPrice);
		this.noOfItemCo = new SimpleIntegerProperty(noOfItems);
		this.totalPriceCo = new SimpleDoubleProperty(totalPrice);
		this.dateTime = new SimpleStringProperty(dateC);
		this.totalPriceInAllItems = new SimpleDoubleProperty(totalPriceInAllItemsC);
	}



	public Double getTotalPriceInAllItems() {
		return totalPriceInAllItems.get();
	}



	public void setTotalPriceInAllItems(Double totalPriceInAllItemsC) {
		totalPriceInAllItems.set(totalPriceInAllItemsC);
	}



	public Integer getSeqNo() {
		return seqNo.get();
	}



	public void setSeqNo(Integer seqNoC) {
		seqNo.set(seqNoC);
	}



	public String getItemNameCo() {
		return itemNameCo.get();
	}



	public void setItemNameCo(String itemName) {
		itemNameCo.set(itemName);
	}



	public Double getPricePerItemCo() {
		return pricePerItemCo.get();
	}



	public void setPricePerItemCo(Double pricePerItem) {
		pricePerItemCo.set(pricePerItem);
	}



	public Integer getNoOfItemCo() {
		return noOfItemCo.get();
	}



	public void setNoOfItemCo(Integer noOfItem) {
		noOfItemCo.set(noOfItem);
	}



	public Double getTotalPriceCo() {
		return totalPriceCo.get();
	}



	public void setTotalPriceCo(Double totalPrice) {
		totalPriceCo.set(totalPrice);
	}



	public String getDateTime() {
		return dateTime.get();
	}



	public void setDateTime(String dateC) {
		dateTime.set(dateC);
	}

	

	
	
	
	
	
	
}
