package com.limetray.assignement.PanelView;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class ItemsPanel extends Parent {

	
	private Parent  root;
	
	public ItemsPanel() throws IOException{
		root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/Main.fxml")); 
		
	}
	
	public Parent getItemsPanel(){
		try{
			return root;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}
	
}
