package com.limetray.assignement.Conttroler;

import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import com.limetray.assignement.Beans.ItemsBeans;
import com.limetray.assignement.Util.Utilities;

/**
 * FXML Controller class
 *
 * @author ADMIN
 */
public class ItemsController implements Initializable {
    @FXML
    private Color x2;
    @FXML
    private Font x1;
    @FXML
    private ComboBox<String> itemsList;
    @FXML
    private Label pricePerItems;
    @FXML
    private ComboBox<Integer> numberOfItems;
    @FXML
    private Label totalPrice;
    @FXML
    private Button addDetail;
    @FXML
    private TableView<ItemsBeans> itemsDetailsList = new TableView<ItemsBeans>();
    @FXML
    private Button savedItems;
    @FXML
    private Color x4;
    @FXML
    private Insets x5;
    @FXML
    private Color x3;
    @FXML
    private TextField customerName;
    @FXML
    private Button cancelOrder;
    @FXML
    private Label successLabel;
    @FXML
    private ProgressIndicator indicator;
    @FXML
    private Label totalVatAmount;
    @FXML
    private Label vatLabel;
    @FXML
    private Label vatVal;
    
    
    @FXML
    private Label selectedItem;
    @FXML
    private Label leftItem;
    @FXML
    private Label numberOfLeftItem;
    @FXML
    private Label numberOfSellItems;
    @FXML
    private Label sellItems;
    
    @FXML
    private Label calculateVat;
    @FXML
    private Label totalPriceAfterVat;
    @FXML
    private Label amount;
    @FXML
    private TableColumn<ItemsBeans, Integer> seqNo;
    @FXML
    private TableColumn<ItemsBeans, String> itemNameCo;
    @FXML
    private TableColumn<ItemsBeans, Integer> noOfItemCo;
    @FXML
    private TableColumn<ItemsBeans, Double> pricePerItemCo;
    @FXML
    private TableColumn<ItemsBeans, Double> totalPriceCo;
    @FXML
    private TableColumn<ItemsBeans, String> dateTime;
    @FXML
    private ContextMenu deleteARowFromTable;
    private double totalCalculatedPricePerOrder;
    private double totalCalculatedPrice;
    private int leftItemCount = 0;
    private int sellItemCount = 0 ;
    /**
     * Initializes the controller class.
     */
    MyLRUCache lruCache;
    private ConcurrentHashMap<String, String> itemList;
    private ConcurrentHashMap<String, String> itemPrice;
    private ConcurrentHashMap<String, String> numberOfItem;
    private ConcurrentHashMap<String, String> numberOfItemsLeft;
    private ConcurrentHashMap<String, String> numberOfItemSell;
    ObservableList<ItemsBeans> tableData = FXCollections.observableArrayList();
    private int count = 0;
    private Utilities utilities;
    private DataPersist persist;
    private final String RECORD_FILE_NAME = "customerRecord.properties";
    private final String ITEMS_LISTFILE = "items.properties";
    ObservableList<ItemsBeans> item;
    private boolean isCommited;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	try{
    		init();
    		initComponentValue();
    		initTableView();
    		
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    }    

   
	@FXML
    private void addDetailAction(ActionEvent event) {
    	String itemName = itemsList.getSelectionModel().getSelectedItem();
    	Integer noOfItems = numberOfItems.getSelectionModel().getSelectedItem(); 
    	if((itemName != null && !itemName.equals("null")) && (noOfItems != null && noOfItems != 0)){
    		count++;
    		ItemsBeans itemDetail = new ItemsBeans(count, itemName, (double)Double.parseDouble(pricePerItems.getText()), noOfItems, (double)Double.parseDouble(totalPrice.getText()),utilities.dateToStringUtil(new Date()));
    		tableData.add(itemDetail);
    		itemsDetailsList.setItems(tableData);
    		getNumberOfItemsLeft().put("NoOfItemLeft"+itemName, numberOfLeftItem.getText());
			Integer temp = Integer.parseInt((String)getNumberOfItemSell().get("NoOfItemSell"+itemName));
			getNumberOfItemSell().put("NoOfItemSell"+itemName, String.valueOf(noOfItems+temp));
    	}
    	
    }

    @FXML
    private void savedItemsAction(ActionEvent event) {
    	try{
    		totalCalculatedPrice = 0.0;
    		totalCalculatedPricePerOrder = 0.0;
    		if(totalPriceAfterVat.getText() != null && !totalPriceAfterVat.getText().equals(null)){
    			calculateVat.setText("VAT Amount : "+utilities.floatToStringConversion((((float)Double.parseDouble(totalPriceAfterVat.getText())*
						((float)Double.parseDouble(vatVal.getText().substring(vatVal.getText().indexOf(':')+1).trim())))/100)));
    			totalCalculatedPrice = ((float)Double.parseDouble(totalPriceAfterVat.getText())+
						((float)Double.parseDouble(calculateVat.getText().substring(calculateVat.getText().indexOf(':')+1).trim())));
    			amount.setText("Payble : "+utilities.floatToStringConversion(totalCalculatedPrice));
    		}
    		item = FXCollections.observableArrayList();
    		item = itemsDetailsList.getItems();
    		persist.saveData(item, RECORD_FILE_NAME,customerName.getText());
    		commitItemDetails(item);
    		isCommited = true;
    		successLabel.setText("Order is saved ! ");
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    }

    @FXML
    private void cancelOrderAction(ActionEvent event) {
    	try{
    		item = itemsDetailsList.getItems();
    		if(!isCommited && !item.isEmpty()){
    			rollbackItemsDetails(item);
        	}else{
            	tableData = FXCollections.observableArrayList();
            	itemsDetailsList.setItems(tableData);
            	//itemsDetailsList = null;
            	initTableView();
            	erraseValue();
            	count = 0;
        	}
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	
    }
    @FXML
    private void selectItemName(ActionEvent event) {
    	try{
    		String itemName = itemsList.getSelectionModel().getSelectedItem();
    		if(itemName != null && !itemName.equals("null")){
    			numberOfItems.setItems(null);
    			numberOfSellItems.setText(null);
    			pricePerItems.setText(null);
    			numberOfLeftItem.setText(null);
    			selectedItem.setText(itemName);
    			ObservableList<Integer> numOfItemList = FXCollections.observableArrayList();
    			for(int count = 1; count <= Integer.parseInt(getNumberOfItem().get("NoOfItem"+itemName));count++){
    				numOfItemList.add(count);
    			}
    			numberOfItems.setItems(numOfItemList);
    			pricePerItems.setText(utilities.floatToStringConversion((float)Double.parseDouble((String)getItemPrice().get(itemName+"ItemPrice"))));
    			numOfItemList = null;
    		}
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    }

    @FXML
    private void selectItemPrice(ActionEvent event) {
    	try{
    		String itemName = itemsList.getSelectionModel().getSelectedItem();
    		Integer noOfItems = numberOfItems.getSelectionModel().getSelectedItem();  
    		if(itemName != null && !itemName.equals("") && noOfItems != null && noOfItems != 0){
    			totalPrice.setText(utilities.floatToStringConversion((Double.parseDouble(pricePerItems.getText())*noOfItems)));
    			sellItemCount+=noOfItems+((numberOfSellItems.getText() !=null && !numberOfSellItems.getText().equals(""))? Integer.parseInt(numberOfSellItems.getText()) : 0);
    			numberOfSellItems.setText(String.valueOf(noOfItems));
    			sellItems.setText(String.valueOf(sellItemCount));
    			totalCalculatedPricePerOrder+=(totalPrice.getText() != null && !totalPrice.getText().equals("")) ? Double.parseDouble(totalPrice.getText()) : 0.0;
    			totalPriceAfterVat.setText(utilities.floatToStringConversion(totalCalculatedPricePerOrder));
    			numberOfLeftItem.setText(String.valueOf((Integer.parseInt((String)getNumberOfItemsLeft().get("NoOfItemLeft"+itemName)) - (noOfItems))));
    			//getNumberOfItem().put("NoOfItem"+itemName, numberOfLeftItem.getText());
    			
    			leftItem.setText(itemName);
    		}
    		
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    }
    public void init(){
    	try{
    		lruCache = MyLRUCache.getMyLRUCache();
    		utilities = Utilities.getUtilClass();
    		persist = DataPersist.getDataPersist();
    		
    		itemList = new ConcurrentHashMap<String, String>();
    		numberOfItem = new ConcurrentHashMap<String, String>();
    		itemList = lruCache.getItemCache();
    		numberOfItem = lruCache.getNumberOfItems();
    		itemPrice = lruCache.getItemPrice();
    		numberOfItemsLeft = lruCache.getNumberOfItemsLeft();
    		numberOfItemSell = lruCache.getNumberOfItemSell();
    		
    		
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    }

    
    public void initComponentValue(){
    	try{
    		ObservableList<String> listOfItems = FXCollections.observableArrayList();
    		for(Map.Entry<String, String> itemMap : getItemList().entrySet()){
    			String val = (String)itemMap.getValue();
    			listOfItems.add(val);
    		}
    		itemsList.setItems(listOfItems);
    		vatVal.setText("VAT (%) : "+utilities.floatToStringConversion((float)lruCache.getVatValue().get()));
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    }
    
    public void initTableView(){
    	try{
    		seqNo.setCellValueFactory(new PropertyValueFactory<ItemsBeans,Integer>("seqNo"));
    		itemNameCo.setCellValueFactory(new PropertyValueFactory<ItemsBeans,String>("itemNameCo"));
    		noOfItemCo.setCellValueFactory(new PropertyValueFactory<ItemsBeans,Integer>("noOfItemCo"));
    		pricePerItemCo.setCellValueFactory(new PropertyValueFactory<ItemsBeans,Double>("pricePerItemCo"));
    		totalPriceCo.setCellValueFactory(new PropertyValueFactory<ItemsBeans,Double>("totalPriceCo"));
    		dateTime.setCellValueFactory(new PropertyValueFactory<ItemsBeans,String>("dateTime"));
    		itemsDetailsList.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    		//itemsDetailsList.setItems(null);
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    }
    
    
    @FXML
    private void deleteARowFromTableEvent(ActionEvent event) {
    	try{
    		
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    }
    
    public void erraseValue(){
    	try{
    		successLabel.setText(null);
    		selectedItem.setText(null);
    		leftItem.setText(null);
    		numberOfLeftItem.setText(null);
    		sellItems.setText(null);
    		numberOfSellItems.setText(null);
    		calculateVat.setText(null);
    		amount.setText(null);
    		totalPriceAfterVat.setText(null);
    		totalVatAmount.setText(null);
    		customerName.setText(null);
    		pricePerItems.setText(null);
    		totalPrice.setText(null);
    		count = 0;
    		totalCalculatedPricePerOrder = 0;
    		totalCalculatedPrice = 0;
    		leftItemCount = 0;
    		sellItemCount = 0 ;
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    }
    
    
    
    public void rollbackItemsDetails(ObservableList<ItemsBeans> items){
    	try{
    		for(ItemsBeans item : items){
    			Integer toLeftItems = Integer.parseInt((String)getNumberOfItemsLeft().get("NoOfItemLeft"+item.getItemNameCo()))+item.getNoOfItemCo();
    			getNumberOfItemsLeft().put("NoOfItemLeft"+item.getItemNameCo(), String.valueOf(toLeftItems));
    			Integer temp = Integer.parseInt((String)getNumberOfItemSell().get("NoOfItemSell"+item.getItemNameCo()));
    			getNumberOfItemSell().put("NoOfItemSell"+item.getItemNameCo(), String.valueOf(temp-item.getNoOfItemCo()));
    			Integer noOfIt = Integer.parseInt((String)getNumberOfItemsLeft().get("NoOfItemLeft"+item.getItemNameCo()))+Integer.parseInt((String)getNumberOfItemSell().get("NoOfItemSell"+item.getItemNameCo()));
    			getNumberOfItem().put(item.getItemNameCo(),String.valueOf(noOfIt));
    			
    			toLeftItems = Integer.parseInt((String)getNumberOfItemsLeft().get("NoOfItemLeft"+item.getItemNameCo()));
    			temp = Integer.parseInt((String)getNumberOfItemSell().get("NoOfItemSell"+item.getItemNameCo()));
    			noOfIt = Integer.parseInt((String)getNumberOfItem().get(item.getItemNameCo()));
    			System.out.println("Item Name > "+item.getItemNameCo()+" > "+"Total No. Items : "+noOfIt+" :: Sell Items > "+temp+" > left Items > "+toLeftItems);
    		}
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    }
    
    public void commitItemDetails(ObservableList<ItemsBeans> items){
    	try{
    		persist.updateKey(ITEMS_LISTFILE, items);
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    }
    
    
    
	public ConcurrentHashMap<String, String> getItemList() {
		return itemList;
	}

	public ConcurrentHashMap<String, String> getItemPrice() {
		return itemPrice;
	}

	public ConcurrentHashMap<String, String> getNumberOfItem() {
		return numberOfItem;
	}


	public ConcurrentHashMap<String, String> getNumberOfItemsLeft() {
		return numberOfItemsLeft;
	}


	public ConcurrentHashMap<String, String> getNumberOfItemSell() {
		return numberOfItemSell;
	}

	
	

    
}
