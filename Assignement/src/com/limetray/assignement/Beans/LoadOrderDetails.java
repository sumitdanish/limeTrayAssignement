/**
 * SU915198
 * 6:13:42 PM
 * 2015
 */
package com.limetray.assignement.Beans;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * @author SU915198
 *
 */
public class LoadOrderDetails {

	
	private SimpleIntegerProperty ohSeqNo;
    private SimpleStringProperty ohItemsName;
    private SimpleIntegerProperty ohTotalItems;
    private SimpleIntegerProperty ohTotalSellItems;
    private SimpleIntegerProperty ohTotalLeftItems;
	private SimpleDoubleProperty odPricePerItem;	
	private SimpleDoubleProperty olddPricePerItem;
    
    
    
    
    
    
    
    
    
	public LoadOrderDetails(Integer ohSeqNoC,
			String ohItemsNameC,
			Integer ohTotalItemsC,
			Integer ohTotalSellItemsC,
			Integer ohTotalLeftItemsC,
			double odPricePerItemC) {
		super();
		this.ohSeqNo = new SimpleIntegerProperty(ohSeqNoC);
		this.ohItemsName = new SimpleStringProperty(ohItemsNameC);
		this.ohTotalItems = new SimpleIntegerProperty(ohTotalItemsC);
		this.ohTotalSellItems =new SimpleIntegerProperty(ohTotalSellItemsC);
		this.ohTotalLeftItems =new SimpleIntegerProperty(ohTotalLeftItemsC);
		this.odPricePerItem = new SimpleDoubleProperty(odPricePerItemC);
		this.olddPricePerItem = new SimpleDoubleProperty(odPricePerItemC);;
	}
	public Integer getOhSeqNo() {
		return ohSeqNo.get();
	}
	public void setOhSeqNo(Integer ohSeqNoC) {
		this.ohSeqNo.set(ohSeqNoC);
	}
	public String getOhItemsName() {
		return ohItemsName.get();
	}
	public void setOhItemsName(String ohItemsNameC) {
		this.ohItemsName.set(ohItemsNameC);
	}
	public Integer getOhTotalItems() {
		return ohTotalItems.get();
	}
	public void setOhTotalItems(Integer ohTotalItemsC) {
		this.ohTotalItems.set(ohTotalItemsC);
	}
	public Integer getOhTotalSellItems() {
		return ohTotalSellItems.get();
	}
	public void setOhTotalSellItems(Integer ohTotalSellItemsC) {
		this.ohTotalSellItems.set(ohTotalSellItemsC);
	}
	public Integer getOhTotalLeftItems() {
		return ohTotalLeftItems.get();
	}
	public void setOhTotalLeftItems(Integer ohTotalLeftItemsC) {
		this.ohTotalLeftItems.set(ohTotalLeftItemsC);
	}
	public Double getOdPricePerItem() {
		return odPricePerItem.get();
	}
	public void setOdPricePerItem(double odPricePerItemC) {
		this.odPricePerItem.set(odPricePerItemC);
	}
	
    
    public SimpleDoubleProperty oldPriceProperty(){
    	return olddPricePerItem;
    }
    
	
	
	
}
