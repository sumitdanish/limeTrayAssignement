package com.limetray.assignement;
	

import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

import com.limetray.assignement.Conttroler.LoginController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.Scene;

import com.aquafx_project.*;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Rectangle ge = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
			double width = ge.getWidth();
			double hight = ge.getHeight();
			LoginController l = new LoginController();
			Parent  root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
			//primaryStage.initStyle(StageStyle.TRANSPARENT);
			AquaFx.style();
			Scene scene = new Scene(root);
			//scene.getStylesheets().add(getClass().getResource("/fxml/fx.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
