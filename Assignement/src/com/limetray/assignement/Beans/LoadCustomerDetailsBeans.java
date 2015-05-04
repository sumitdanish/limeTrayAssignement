package com.limetray.assignement.Beans;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class LoadCustomerDetailsBeans {

	
	
	 private SimpleIntegerProperty odSeqNo;
	 private SimpleStringProperty odCustomerName; 
	 private SimpleStringProperty odItemsName; 
	 private SimpleIntegerProperty odNumberOfItems;  
	 private SimpleDoubleProperty odPricePerItems;  
	 private SimpleDoubleProperty odTotalPrice;
	 private SimpleStringProperty odDateTime;
	 
	 
	 
	 
	 
	public LoadCustomerDetailsBeans(Integer odSeqNoC,
			String odCustomerNameC,
			String odItemsNameC,
			Integer odNumberOfItemsC,
			double odPricePerItemsC,
			double odTotalPriceC, String odDateTimeC) {
		super();
		this.odSeqNo =new SimpleIntegerProperty(odSeqNoC);
		this.odCustomerName = new SimpleStringProperty(odCustomerNameC);
		this.odItemsName = new SimpleStringProperty(odItemsNameC);
		this.odNumberOfItems = new SimpleIntegerProperty(odNumberOfItemsC);
		this.odPricePerItems = new SimpleDoubleProperty(odPricePerItemsC);
		this.odTotalPrice = new SimpleDoubleProperty(odTotalPriceC);
		this.odDateTime = new SimpleStringProperty(odDateTimeC);
	}
	public Integer getOdSeqNo() {
		return odSeqNo.get();
	}
	public void setOdSeqNo(Integer odSeqNoC) {
		this.odSeqNo.set(odSeqNoC);
	}
	public String getOdCustomerName() {
		return odCustomerName.get();
	}
	public void setOdCustomerName(String odCustomerNameC) {
		this.odCustomerName.set(odCustomerNameC);
	}
	public String getOdItemsName() {
		return odItemsName.get();
	}
	public void setOdItemsName(String odItemsNameC) {
		this.odItemsName.set(odItemsNameC);
	}
	public Integer getOdNumberOfItems() {
		return odNumberOfItems.get();
	}
	public void setOdNumberOfItems(Integer odNumberOfItemsC) {
		this.odNumberOfItems.set(odNumberOfItemsC);
	}
	public double getOdPricePerItems() {
		return odPricePerItems.get();
	}
	public void setOdPricePerItems(double odPricePerItemsC) {
		this.odPricePerItems.set(odPricePerItemsC);
	}
	public double getOdTotalPrice() {
		return odTotalPrice.get();
	}
	public void setOdTotalPrice(double odTotalPriceC) {
		this.odTotalPrice.set(odTotalPriceC);
	}
	public String getOdDateTime() {
		return odDateTime.get();
	}
	public void setOdDateTime(String odDateTimeC) {
		this.odDateTime.set(odDateTimeC);
	}
	
	    
	 
	
	
	
}
