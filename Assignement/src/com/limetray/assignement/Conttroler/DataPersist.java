package com.limetray.assignement.Conttroler;

import com.limetray.assignement.Beans.ItemsBeans;

import javafx.collections.ObservableList;

public class DataPersist {

	
	private volatile static DataPersist dataPerist;
	private FileUtil fileUtil;
	private DataPersist(){
		
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
	
	
	public void saveData(ObservableList<ItemsBeans> itemsBeans,String fileName){
		try{
			fileUtil.saveRecord(itemsBeans, fileName);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	
}
