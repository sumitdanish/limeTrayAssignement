package com.limetray.assignement.Conttroler;

import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.net.URL;
import java.util.ResourceBundle;

import com.limetray.assignement.PanelView.ItemsPanel;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ADMIN
 */
public class LoginController implements Initializable {
    @FXML
    private Font x1;
    @FXML
    private PasswordField password;
    @FXML
    private TextField emailId;
    @FXML
    private Button signInButton;
    @FXML
    private Font x2;
    @FXML
    private Button cancelButton;
    @FXML
    private ProgressIndicator sinInPprogressIndicator;
    @FXML
    private Label errorLabel;
    
    final Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    public Exception validateLogin(ActionEvent event) throws Exception {
    	try{
    		Rectangle ge = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
			double width = ge.getWidth();
			double hight = ge.getHeight();
    		Stage stage = null;
    		stage = (Stage)signInButton.getScene().getWindow();
    		Parent  root = new ItemsPanel().getItemsPanel();
    		
    		Scene sc = new Scene(root,width/2,hight/2);
    		stage.setScene(sc);
    		stage.setX(0);
    		stage.setY(0);
    		stage.show();
    		return null;
    	}catch(Exception ex){
    		throw ex;
    	}
    }

    @FXML
    private void conntrolerMethod(ActionEvent event) {
    }
    
}
