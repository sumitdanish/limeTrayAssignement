package com.limetray.assignement.Conttroler;

import com.limetray.assignement.Beans.ItemsBeans;

import javafx.collections.ObservableList;

public class DataPersist {

	
	private volatile static DataPersist dataPerist;
	private FileUtil fileUtil;
	private DataPersist(){
		fileUtil = FileUtil.getFileUtil();
	}
	public static synchronized DataPersist getDataPersist(){
		try{
			if(dataPerist == null){
				synchronized (DataPersist.class) {
					if(dataPerist == null){
						dataPerist = new DataPersist();
					}
				}
			}
			return dataPerist;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}
	
	
	public void saveData(ObservableList<ItemsBeans> itemsBeans,String fileName,String customerName){
		try{
			fileUtil.saveRecord(itemsBeans, fileName,customerName);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void updateKey(String fileName,ObservableList<ItemsBeans> items){
		try{
			fileUtil.updateKey(fileName,items);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
}
