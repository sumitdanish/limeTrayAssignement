package com.limetray.assignement.Conttroler;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import com.limetray.assignement.Beans.ItemsBeans;
import com.limetray.assignement.Util.Utilities;

import javafx.collections.ObservableList;

public class FileUtil {

	private volatile static FileUtil fileUtil;
	private Utilities utilties;
	
	private FileUtil(){
		
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
			return itemList;
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}
	
	
	
	public void saveRecord(ObservableList<ItemsBeans> itemsBeans,String fileName) {
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
			System.out.println(sb.toString());
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public Properties getPropertiesFile(String fileName){
		try{
			Properties pro = new Properties();
			File file = new File(fileName);
			InputStream in = new FileInputStream(file);
			pro.load(in);
			return pro;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}
}
