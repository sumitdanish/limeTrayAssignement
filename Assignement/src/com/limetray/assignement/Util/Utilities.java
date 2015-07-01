package com.limetray.assignement.Util;

import java.text.DateFormat;
import java.text.DecimalFormat;
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
	
	 public String floatToStringConversion(double totalPrice){
	    	try{
	    		DecimalFormat df2 = new DecimalFormat("00.00");
	    		float totalPriceInFloat = (float) totalPrice;
	    		String totalPriceFromFloatToString = df2.format(totalPriceInFloat);
	    		totalPriceFromFloatToString = totalPriceFromFloatToString.substring(totalPriceFromFloatToString.indexOf('.')+1).length() == 1?
	    									  (totalPriceFromFloatToString.substring(0,totalPriceFromFloatToString.indexOf('.'))+"."+
	    											  totalPriceFromFloatToString.substring(totalPriceFromFloatToString.indexOf('.')+1)+String .valueOf('0')):
	    										  totalPriceFromFloatToString;
	    		return totalPriceFromFloatToString;
	    		
	    	}catch(Exception ex){
	    		ex.printStackTrace();
	    	}
	    	return null;
	    }
	
}
