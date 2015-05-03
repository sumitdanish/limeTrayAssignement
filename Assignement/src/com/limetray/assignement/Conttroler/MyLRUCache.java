package com.limetray.assignement.Conttroler;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import javafx.beans.property.SimpleDoubleProperty;
public class MyLRUCache {

	
	
	private ConcurrentHashMap<String, String> itemCache;
	private ConcurrentHashMap<String, String> itemPrice;
	private ConcurrentHashMap<String, String> numberOfItems;
	private ConcurrentHashMap<String, String> numberOfItemsLeft;
	private ConcurrentHashMap<String, String> numberOfItemSell;
	private final String ITEMS_LISTFILE = "items.properties";
	private final String ITEM_LISTS_KEY = "ItemList";
	private final String ITEM_VAT_VALUE = "VAT";
	private final String ITEM_SEPERATION_DELIMETER = ",";
	private volatile static MyLRUCache myLRUCache;
	private SimpleDoubleProperty vatValue;
	private FileUtil fileUtil; 
	private MyLRUCache(){
		fileUtil = FileUtil.getFileUtil();
		itemCache = new ConcurrentHashMap<String, String>();
		itemPrice = new ConcurrentHashMap<String, String>();
		numberOfItems = new ConcurrentHashMap<String, String>();
		numberOfItemsLeft = new ConcurrentHashMap<String, String>();
		numberOfItemSell = new ConcurrentHashMap<String, String>();
		vatValue = new SimpleDoubleProperty();
		loadDataFromFile();
	}
	
	public static synchronized MyLRUCache getMyLRUCache(){
		try{
			if(myLRUCache == null){
				synchronized (MyLRUCache.class) {
					if(myLRUCache == null){
						myLRUCache = new MyLRUCache();
					}
				}
			}
			return myLRUCache;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}
	
	public ConcurrentHashMap<String, String> getItemCache() {
		return itemCache;
	}
	
	public ConcurrentHashMap<String, String> getItemPrice() {
		return itemPrice;
	}
	
	
	public ConcurrentHashMap<String, String> getNumberOfItems() {
		return numberOfItems;
	}

	
	
	public ConcurrentHashMap<String, String> getNumberOfItemsLeft() {
		return numberOfItemsLeft;
	}

	public ConcurrentHashMap<String, String> getNumberOfItemSell() {
		return numberOfItemSell;
	}

	public void loadDataFromFile(){
		try{
			String[] items = fileUtil.getObjectList(ITEM_LISTS_KEY, ITEMS_LISTFILE, ITEM_SEPERATION_DELIMETER);
			for(String itemName : items){
				itemCache.put(itemName, itemName);
				String[] itemP = fileUtil.getObjectList(itemName+"ItemPrice", ITEMS_LISTFILE,",");
				String[] noOfItems = fileUtil.getObjectList("NoOfItem"+itemName, ITEMS_LISTFILE,",");
				String[] noOfItemLeft = fileUtil.getObjectList("NoOfItemLeft"+itemName, ITEMS_LISTFILE,",");
				String[] noOfItemSell = fileUtil.getObjectList("NoOfItemSell"+itemName, ITEMS_LISTFILE,",");
				itemPrice.put(itemName+"ItemPrice",itemP[0]);
				numberOfItems.put("NoOfItem"+itemName, noOfItems[0]);
				numberOfItemsLeft.put("NoOfItemLeft"+itemName, noOfItemLeft[0]);
				numberOfItemSell.put("NoOfItemSell"+itemName, noOfItemSell[0]);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public SimpleDoubleProperty getVatValue(){
		try{
			String[] valPrice = fileUtil.getObjectList(ITEM_VAT_VALUE, ITEMS_LISTFILE,",");
			vatValue.set(Double.parseDouble(valPrice[0]));
			return vatValue;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null; 
	}
	
	
}
