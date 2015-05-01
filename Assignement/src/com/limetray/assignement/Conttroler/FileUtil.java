package com.limetray.assignement.Conttroler;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class FileUtil {

	private volatile static FileUtil fileUtil;
	
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
}
