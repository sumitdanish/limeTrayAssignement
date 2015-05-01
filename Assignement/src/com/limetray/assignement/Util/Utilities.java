package com.limetray.assignement.Util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utilities {

	
	
	private volatile static Utilities utilities;
	private Utilities(){
		
	}
	
	public static synchronized Utilities getUtilClass(){
		try{
			if(utilities == null){
				synchronized (Utilities.class) {
					if(utilities == null){
						utilities = new Utilities();
					}
				}
			}
			return utilities;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}
	
	
	public String dateToStringUtil(Date date){
		try{
			String forMat = "MM/dd/yyyy HH:mm:ss";
			DateFormat dateFormat = new SimpleDateFormat(forMat);
			return dateFormat.format(date);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}
	
	
}
