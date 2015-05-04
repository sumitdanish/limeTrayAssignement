package com.limetray.assignement.Conttroler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

import com.limetray.assignement.Beans.ItemsBeans;
import com.limetray.assignement.Beans.LoadCustomerDetailsBeans;
import com.limetray.assignement.Beans.LoadOrderDetails;
import com.limetray.assignement.Util.Utilities;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FileUtil {

	private volatile static FileUtil fileUtil;
	private Utilities utilties;
	private MyLRUCache lrucache;
	private  String ITEM_LISTS_KEY = "ItemList";
	private FileUtil(){
		utilties = Utilities.getUtilClass();
	}
	
	public static synchronized FileUtil getFileUtil(){
		try{
			if(fileUtil == null){
				synchronized (FileUtil.class) {
					if(fileUtil == null){
						fileUtil = new FileUtil();
					}
				}
			}
			return fileUtil;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}
	
	public String[] getObjectList(String key,String fileName,String delimeter){
		try{
			Properties pro = new Properties();
			InputStream in = null;
			File file = new File(fileName);
			in =new FileInputStream(file);
			pro.load(in);
			String[]  itemList = (pro.get(key)).toString().split(delimeter);
			in.close();
			
			return itemList;
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}
	
	
	
	public void saveRecord(ObservableList<ItemsBeans> itemsBeans,String fileName,String customerName) {
		try{
			Properties pro = getPropertiesFile(fileName);
			StringBuilder sb = new StringBuilder();
			for(ItemsBeans item : itemsBeans){
				sb = sb.append(item.getItemNameCo()).append(",").
						append(item.getNoOfItemCo()).append(",").
						append(utilties.floatToStringConversion(item.getPricePerItemCo())).append(",").
						append(utilties.floatToStringConversion(item.getTotalPriceCo())).append(",").
						append(item.getDateTime()).append("#");
			}
			FileOutputStream out = new FileOutputStream(fileName);
			pro.setProperty(customerName,sb.toString());
			pro.store(out,null);
			out.close();
			
			//System.out.println(sb.toString());
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	private Properties getPropertiesFile(String fileName){
		try{
			Properties pro = new Properties();
			File file = new File(fileName);
			InputStream in = new FileInputStream(file);
			pro.load(in);
			in.close();
			return pro;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}
	
	
	public void updateKey(String fileName,ObservableList<ItemsBeans> items){
		try{
			Properties pro = getPropertiesFile(fileName);
			for(ItemsBeans item : items){
				FileOutputStream out = new FileOutputStream(fileName);	
				lrucache = MyLRUCache.getMyLRUCache();
    			pro.setProperty("NoOfItemLeft"+item.getItemNameCo(), (String)lrucache.getNumberOfItemsLeft().get("NoOfItemLeft"+item.getItemNameCo()));
    			pro.setProperty("NoOfItemSell"+item.getItemNameCo(), (String)lrucache.getNumberOfItemSell().get("NoOfItemSell"+item.getItemNameCo()));
    			pro.store(out,null);
    			out.close();
    		}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	
	
	public void updatePrice(String fileName,ObservableList<LoadOrderDetails> items){
		try{
			Properties pro = getPropertiesFile(fileName);
			for(LoadOrderDetails item : items){
				FileOutputStream out = new FileOutputStream(fileName);	
				lrucache = MyLRUCache.getMyLRUCache();
    			pro.setProperty(item.getOhItemsName()+"ItemPrice", utilties.floatToStringConversion(item.getOdPricePerItem()));
    			pro.store(out,null);
    			out.close();
    		}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	
	
	public ObservableList<LoadOrderDetails> loadOrderDetails(String fileName){
		try{
			ObservableList<LoadOrderDetails> loadOrderDetails = FXCollections.observableArrayList();
			Properties pro = getPropertiesFile(fileName);
			String[] itemsList = getObjectList(ITEM_LISTS_KEY, fileName,",");
			
			Set<Object> key = pro.keySet();
			int count = 0;
			for(String item : itemsList){
				
				Integer ohSeqNo = 0;
			    String ohItemsName;
			    Integer ohTotalItems = 0;
			    Integer ohTotalSellItems = 0;
			    Integer ohTotalLeftItems = 0;
				double odPricePerItem = 0;	
				count++;
				ohItemsName = item;
				ohSeqNo = count;
				if(pro.containsKey("NoOfItem"+item)){
					
					ohTotalItems=Integer.parseInt(pro.get("NoOfItem"+item).toString().trim());
				}
				if(pro.containsKey("NoOfItemLeft"+item)){
					ohTotalLeftItems=Integer.parseInt(pro.get("NoOfItemLeft"+item).toString().trim());
				}
				if(pro.containsKey("NoOfItemSell"+item)){
					ohTotalSellItems=Integer.parseInt(pro.get("NoOfItemSell"+item).toString().trim());
				}
				if(pro.containsKey(item+"ItemPrice")){
					odPricePerItem=Double.parseDouble(pro.get(item+"ItemPrice").toString().trim());
				}
				LoadOrderDetails loadOrderDetail = new LoadOrderDetails(ohSeqNo, ohItemsName, ohTotalItems, ohTotalSellItems, ohTotalLeftItems, odPricePerItem);
				loadOrderDetails.add(loadOrderDetail);
			}
			return loadOrderDetails;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}
	
	
	
	
	
	
	
	public ObservableList<LoadCustomerDetailsBeans> loadCustomerDetails(String fileName){
		try{
			ObservableList<LoadCustomerDetailsBeans> loadCutomerOrderDetails = FXCollections.observableArrayList();
			Properties pro = getPropertiesFile(fileName);
			Set<Object> key = pro.keySet();
			int count = 0;
			for(Object keyObject : key){
				String value = getKeyValue(pro, keyObject.toString()).toString();
				String[] splitValue = value.split("#");
				for(String val : splitValue){
					String[]  splitIndividualRecord = val.split(",");
					count++;
					LoadCustomerDetailsBeans loadDetails = new LoadCustomerDetailsBeans(count, 
																						keyObject.toString().trim(), 
																						splitIndividualRecord[0].toString().trim(), 
																						Integer.parseInt(splitIndividualRecord[1].toString().trim()),
																						Double.parseDouble(splitIndividualRecord[2].toString().trim()), 
																						Double.parseDouble(splitIndividualRecord[3].toString().trim()), 
																						splitIndividualRecord[4].toString().trim());
					loadCutomerOrderDetails.add(loadDetails);
					
				}
			}
			count= 0;
			return loadCutomerOrderDetails;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}
	
	private Object getKeyValue(Properties pro,String key){
		try{
			return pro.get(key);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}
	
}
