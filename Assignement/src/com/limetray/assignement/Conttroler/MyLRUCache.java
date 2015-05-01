package com.limetray.assignement.Conttroler;

import java.util.HashMap;
import javafx.beans.property.SimpleDoubleProperty;
public class MyLRUCache {

	
	
	public HashMap<String, String> itemCache;
	public HashMap<String,String> itemPrice;
	public HashMap<String,String> numberOfItems;
	private final String ITEMS_LISTFILE = "items.properties";
	private final String ITEM_LISTS_KEY = "ItemList";
	private final String ITEM_VAT_VALUE = "VAT";
	private final String ITEM_SEPERATION_DELIMETER = ",";
	private volatile static MyLRUCache myLRUCache;
	private SimpleDoubleProperty vatValue;
	private FileUtil fileUtil;
	private MyLRUCache(){
		fileUtil = FileUtil.getFileUtil();
		itemCache = new HashMap<String, String>();
		itemPrice = new HashMap<String, String>();
		numberOfItems = new HashMap<String, String>();
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
	
	public HashMap<String, String> getItemCache() {
		
		return itemCache;
	}
	
	public HashMap<String, String> getItemPrice() {
		return itemPrice;
	}
	
	
	public HashMap<String, String> getNumberOfItems() {
		return numberOfItems;
	}

	public void loadDataFromFile(){
		try{
			String[] items = fileUtil.getObjectList(ITEM_LISTS_KEY, ITEMS_LISTFILE, ITEM_SEPERATION_DELIMETER);
			for(String itemName : items){
				itemCache.put(itemName, itemName);
				String[] itemP = fileUtil.getObjectList(itemName+"ItemPrice", ITEMS_LISTFILE,",");
				String[] noOfItems = fileUtil.getObjectList("NoOfItem"+itemName, ITEMS_LISTFILE,",");
				itemPrice.put(itemName+"ItemPrice",itemP[0]);
				numberOfItems.put("NoOfItem"+itemName, noOfItems[0]);
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
