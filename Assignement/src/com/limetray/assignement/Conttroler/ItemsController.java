package com.limetray.assignement.Conttroler;

import java.net.URL;
import java.util.Date;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;

import javafx.beans.property.ReadOnlyDoubleWrapper;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Callback;
import javafx.util.StringConverter;

import com.limetray.assignement.Beans.ItemsBeans;
import com.limetray.assignement.Beans.LoadCustomerDetailsBeans;
import com.limetray.assignement.Beans.LoadOrderDetails;
import com.limetray.assignement.Util.Utilities;

/**
 * FXML Controller class
 *
 * @author SUMMIT
 */
public class ItemsController implements Initializable {
	
	
	
	
	/*
	 * 
	 *	Definition of all the component of the UI  
	 *  
	 */
	
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
    private Label updateLabel;
    
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
    
    
    
    /*
     * Definition for the Tab Pane 
     * We have three tab in that tab pane 
     * 1. ADD New Order (Tab for the taking the order from the customer) 
     * 2. Customer order (Tab for list of the customer)
     * 3. Order history (Tab for the list of the items available in the store)
     * 
     */
    
    
    @FXML
    private TabPane tabPane;
    @FXML
    private Tab orderDetails;
    @FXML
    private Tab loadItems;
    
    
    /*
     * 
     * Table definition for the items details .
     * E.g when we are taking the order from the customer
     * the at the click event we are adding the items in the 
     * table . so this is the definition for that table; 
     * Corresponding to that table six column definition
     * 
     */
    
    
    @FXML
    private TableView<ItemsBeans> itemsDetailsList = new TableView<ItemsBeans>();
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
    
    
    
    
    /*
     *	Table definition for the list of the customer who buy the items from the store
     *  We are showing this details in second tab.
     *  Corresponding to that table we have seven column definition
     * 
     * 
     * 
     */
    
    
    
    
    @FXML
    private TableView<LoadCustomerDetailsBeans> orderDetailsTable;
    @FXML
    private TableColumn<LoadCustomerDetailsBeans, Integer> odSeqNo;
    @FXML
    private TableColumn<LoadCustomerDetailsBeans, String> odCustomerName;
    @FXML
    private TableColumn<LoadCustomerDetailsBeans, String> odItemsName;
    @FXML
    private TableColumn<LoadCustomerDetailsBeans, Integer> odNumberOfItems;
    @FXML
    private TableColumn<LoadCustomerDetailsBeans, Double> odPricePerItems;
    @FXML
    private TableColumn<LoadCustomerDetailsBeans, Double> odTotalPrice;
    @FXML
    private TableColumn<LoadCustomerDetailsBeans, Double> odDateTime;
  
    
    
    
    /*
     * Table definition for the detail of the list of the item available in the store
     * Details are :
     * 	1. Item name
     * 	2. Total number of item
     *  3. Total number of the left item
     *  4. Total number of the sell item
     *  5. Price/Item
     * Corresponding to that table we have six column definition
     * 
     * 
     */
    
    
    
    
    @FXML
    private TableView<LoadOrderDetails> orderHistoryDetails = new TableView<LoadOrderDetails>();
    @FXML
    private TableColumn<LoadOrderDetails,Integer> ohSeqNo;
    @FXML
    private TableColumn<LoadOrderDetails, String> ohItemsName;
    @FXML
    private TableColumn<LoadOrderDetails, Integer> ohTotalItems;
    @FXML
    private TableColumn<LoadOrderDetails, Integer> ohTotalSellItems;
    @FXML
    private TableColumn<LoadOrderDetails, Integer> ohTotalLeftItems;
    @FXML
    private TableColumn<LoadOrderDetails, Double> odPricePerItem;
    
    
    
    
    
    
    /*
     * Entity definition for the total price per single order
     *  
     */
    private double totalCalculatedPricePerOrder;
    
    
    /*
     * Entity definition for the total price/order 
     * 
     */
    
    
    private double totalCalculatedPrice;
    
    /*
     * Entity definition for the total item sell in single order
     * 
     * 
     */
    
    private int sellItemCount = 0 ;
    /**
     * Initializes the controller class.
     */
    
    /*
     * 
     * Definition of the temporary cache.
     * this cache will load the data from data source
     * e.g item details, customer details etc.
     * the cache will alive till the application will active in to system.
     * 
     * 
     * */
    MyLRUCache lruCache;
    
    
    /*
     * 
     * Definition for the different table (e.g HashTable in the cache) in the cache.
     * 1. Item list 2. Item price 3. Number of items.. etc
     * All these details coming from file (properties file )
     * 
     * */
    private ConcurrentHashMap<String, String> itemList;
    private ConcurrentHashMap<String, String> itemPrice;
    private ConcurrentHashMap<String, String> numberOfItem;
    private ConcurrentHashMap<String, String> numberOfItemsLeft;
    private ConcurrentHashMap<String, String> numberOfItemSell;
    
    
    
    ObservableList<ItemsBeans> tableData = FXCollections.observableArrayList();
    ObservableList<LoadOrderDetails> renderOrderDetailsInTable ;
    ObservableList<LoadCustomerDetailsBeans> renderCustomerDetailsInTable = FXCollections.observableArrayList();
    private int count = 0;
    
    
    
    /*
     * 
     * Definition for the utilities class
     * Utilities are 
     * 1. Converting float to string
     * 2. Converting date to string 
     * 
     */
    private Utilities utilities;
    
    private DataPersist persist;
    private final String RECORD_FILE_NAME = "DataSource/customerRecord.properties";
    private final String ITEMS_LISTFILE = "DataSource/items.properties";
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

   
    
    /*
     * This method will add the order detail in to table in every click event or in every new order
     * this method will invoke from the first tab 
     * 
     */
    
    
    
    
	@FXML
    private void addDetailAction(ActionEvent event) {
    	String itemName = itemsList.getSelectionModel().getSelectedItem();
    	Integer noOfItems = numberOfItems.getSelectionModel().getSelectedItem(); 
    	if((itemName != null && !itemName.equals("null")) && (noOfItems != null && noOfItems != 0)){
    		count++;
    		ItemsBeans itemDetail = new ItemsBeans(count, itemName, (double)Double.parseDouble(pricePerItems.getText()), noOfItems, (double)Double.parseDouble(totalPrice.getText()),utilities.dateToStringUtil(new Date()),0.0);
    		tableData.add(itemDetail);
    		
    		/*This line will add every single order into grid this table is into first tab*/ 
    		itemsDetailsList.setItems(tableData);
    		
    		/*
    		 * This line will maintain the temporary cache details for item
    		 * 1. how may item is sell and how many item is left after complete
    		 *  single order or total number of order given by customer
    		 * 
    		 * */
    		getNumberOfItemsLeft().put("NoOfItemLeft"+itemName, numberOfLeftItem.getText());
			Integer temp = Integer.parseInt((String)getNumberOfItemSell().get("NoOfItemSell"+itemName));
			getNumberOfItemSell().put("NoOfItemSell"+itemName, String.valueOf(noOfItems+temp));
    	}
        
    }

	
	/*
	 * 
	 * The work of this method is to commit the order details for customer.
	 *  and also maintain the number of available items in the store
	 *  1. Number of item left
	 *  2. number of item sell
	 *  
	 * 
	 * */
	
	
	
	
    @FXML
    private void savedItemsAction(ActionEvent event) {
    	try{
    		totalCalculatedPrice = 0.0;
    		totalCalculatedPricePerOrder = 0.0;
    		if(totalPriceAfterVat.getText() != null && !totalPriceAfterVat.getText().equals(null)){
    			/*
    			 * Here we are calculating the vat amount of the total price 
    			 *  
    			 */
    			
    			calculateVat.setText("VAT Amount : "+utilities.floatToStringConversion((((float)Double.parseDouble(totalPriceAfterVat.getText())*
						((float)Double.parseDouble(vatVal.getText().substring(vatVal.getText().indexOf(':')+1).trim())))/100)));
    			
    			/*
    			 * Here we are calculating total amount to be paind by customer including the VAT amount 
    			 */
    			totalCalculatedPrice = ((float)Double.parseDouble(totalPriceAfterVat.getText())+
						((float)Double.parseDouble(calculateVat.getText().substring(calculateVat.getText().indexOf(':')+1).trim())));
    			amount.setText("Payble : "+utilities.floatToStringConversion(totalCalculatedPrice));
    		}
    		
    		/* In this line we are reading the complete data from the table (Table which is in the first tab)
    		 * and storing in to file.
    		 * 
    		 * */
    		item = FXCollections.observableArrayList();
    		item = itemsDetailsList.getItems();
    		persist.saveData(item, RECORD_FILE_NAME,customerName.getText());
    		
    		/*
    		 * after succesfuly storing the order details of the customer, we are storing the details
    		 * (Details will be like, how many item is left/sell/total number of item
    		 *  after storing the details into file) of the order in to file
    		 *   
    		 */
    		commitItemDetails(item);
    		isCommited = true;
    		successLabel.setText("Order is saved ! ");
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    }

    
    /*
     * 
     * This function will cancel the order 
     * 
     */
    
    @FXML
    private void cancelOrderAction(ActionEvent event) {
    	try{
    		item = itemsDetailsList.getItems();
    		if(!isCommited && !item.isEmpty()){
    			rollbackItemsDetails(item);
        	}else{
            	tableData = FXCollections.observableArrayList();
            	itemsDetailsList.setItems(tableData);
            	initTableView();
            	erraseValue();
            	count = 0;
        	}
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	
    }
    
    /*
     * In this line we are selecting the item name from combobox.when we are taking the order from customer 
     * 
     */
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
    			
    			/*
    			 * In this line we are showing the price of the item.
    			 * when user will select the item. the price will get display according to that
    			 * 
    			 * */
    			numberOfItems.setItems(numOfItemList);
    			
    			/*
    			 * In this line we are converting double to string for two decimal places.
    			 * 
    			 * */
    			pricePerItems.setText(utilities.floatToStringConversion((float)Double.parseDouble((String)getItemPrice().get(itemName+"ItemPrice"))));
    			numOfItemList = null;
    		}
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    }

    
    /*
     * Here we are selecting the number of item from combobox. which should be placed once in a order
     * after selecting the number of item. we are calculating the price of total item.
     * 
     */
    
    
    @FXML
    private void selectItemPrice(ActionEvent event) {
    	try{
    		String itemName = itemsList.getSelectionModel().getSelectedItem();
    		Integer noOfItems = numberOfItems.getSelectionModel().getSelectedItem();  
    		if(itemName != null && !itemName.equals("") && noOfItems != null && noOfItems != 0){
    			
    			/*
    			 * Here we are converting the float to string for displaying the price till two decimal place. 
    			 * 
    			 */
    			
    			
    			
    			totalPrice.setText(utilities.floatToStringConversion((Double.parseDouble(pricePerItems.getText())*noOfItems)));
    			
    			/*
    			 * Here we are calculating the total number of item to be order/sell at time or per customer
    			 * 
    			 * */
    			
    			sellItemCount+=noOfItems+((numberOfSellItems.getText() !=null && !numberOfSellItems.getText().equals(""))? Integer.parseInt(numberOfSellItems.getText()) : 0);
    			numberOfSellItems.setText(String.valueOf(noOfItems));
    			sellItems.setText(String.valueOf(sellItemCount));
    			
    			
    			/*
    			 * Here we are calculating the price per order. more than one order can be made by a customer
    			 * and every order by customer we are displaying int to table (which is in first tab) 
    			 * 
    			 */
    			
    			
    			
    			totalCalculatedPricePerOrder+=(totalPrice.getText() != null && !totalPrice.getText().equals("")) ? Double.parseDouble(totalPrice.getText()) : 0.0;
    			totalPriceAfterVat.setText(utilities.floatToStringConversion(totalCalculatedPricePerOrder));
    			
    			
    			/*
    			 *  Here we are maintaining the total number of a particular item is left in to store.
    			 *   
    			 * 
    			 */
    			numberOfLeftItem.setText(String.valueOf((Integer.parseInt((String)getNumberOfItemsLeft().get("NoOfItemLeft"+itemName)) - (noOfItems))));
    			leftItem.setText(itemName);
    		}
    		
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    }
    
    
    
    /*
     * Here we are reading the details of the item from temp cache 
     * 
     */
    
    
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
    		tabPane.getSelectionModel().selectedItemProperty().addListener(tabPaneChangeListener());
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
    
    
    
    
    public void initOrderDetailsTableView(){
    	try{
    		  orderHistoryDetails.setEditable(true);
    		  ohSeqNo.setCellValueFactory(new PropertyValueFactory<LoadOrderDetails, Integer>("ohSeqNo"));
    		  ohItemsName.setCellValueFactory(new PropertyValueFactory<LoadOrderDetails, String>("ohItemsName"));
    		  ohTotalItems.setCellValueFactory(new PropertyValueFactory<LoadOrderDetails, Integer>("ohTotalItems"));
    		  ohTotalSellItems.setCellValueFactory(new PropertyValueFactory<LoadOrderDetails, Integer>("ohTotalSellItems"));
    		  ohTotalLeftItems.setCellValueFactory(new PropertyValueFactory<LoadOrderDetails, Integer>("ohTotalLeftItems"));
    		  odPricePerItem.setCellValueFactory(new PropertyValueFactory<LoadOrderDetails, Double>("odPricePerItem"));
    		  
    		  
    		  /*
    		   * Here we are making one column (price) editable in table which is in third tab.
    		   * 
    		   */
    		  odPricePerItem.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Double>() {
				@Override
				public Double fromString(String string) {
					return Double.parseDouble(string);
				}
				@Override
				public String toString(Double object) {
					return utilities.floatToStringConversion(object);
				}
			}));
    		  odPricePerItem.setOnEditCommit(
    				    new EventHandler<CellEditEvent<LoadOrderDetails, Double>>() {
    				        @Override
    				        public void handle(CellEditEvent<LoadOrderDetails, Double> t) {
    				            ((LoadOrderDetails) t.getTableView().getItems().get(
    				                t.getTablePosition().getRow())
    				                ).setOdPricePerItem(t.getNewValue());
    				        }
    				    }
    				);
    		  orderHistoryDetails.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    }
    
    
    
    public void initCustomerDetailsView(){
    	try{
    		    odSeqNo.setCellValueFactory(new PropertyValueFactory<LoadCustomerDetailsBeans, Integer>("odSeqNo"));
    		    odCustomerName.setCellValueFactory(new PropertyValueFactory<LoadCustomerDetailsBeans, String>("odCustomerName"));
    		    odItemsName.setCellValueFactory(new PropertyValueFactory<LoadCustomerDetailsBeans, String>("odItemsName"));
    		    odNumberOfItems.setCellValueFactory(new PropertyValueFactory<LoadCustomerDetailsBeans, Integer>("odNumberOfItems"));
    		    odPricePerItems.setCellValueFactory(new PropertyValueFactory<LoadCustomerDetailsBeans, Double>("odPricePerItems"));
    		    odTotalPrice.setCellValueFactory(new PropertyValueFactory<LoadCustomerDetailsBeans, Double>("odTotalPrice"));
    		    odDateTime.setCellValueFactory(new PropertyValueFactory<LoadCustomerDetailsBeans, Double>("odDateTime"));
    		    orderDetailsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    		    
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
    
    
    @FXML
    private void updatePriceOfItems(ActionEvent event){
    	try{
    		renderOrderDetailsInTable = FXCollections.observableArrayList();
    		renderOrderDetailsInTable = orderHistoryDetails.getItems();
    		persist.updatePrice(ITEMS_LISTFILE, renderOrderDetailsInTable);
    		updateLabel.setText("Price is update !");
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
    		
    		sellItemCount = 0 ;
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    }
    
    
    
    /*
     * Here we are rollbacking the item details.
     * suppose some customer cancel the order then we want to maintain the details of the order into cache.
     * 
     * */
    
    
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
    
    
    
    /*
     * Here we are loading the items details from the file showing into third tab
     * 
     * 
     */
    
   
    public void loadOrderDetals(){
    	try{
    		initOrderDetailsTableView();
    		ObservableList<LoadOrderDetails> loadOrder = FXCollections.observableArrayList();
    		loadOrder = persist.loadOrderDetails(ITEMS_LISTFILE);
    		orderHistoryDetails.setItems(loadOrder);
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    }
    
    
    /*
     * Here we are loading customer details from the file and displaying into 2nd tab
     * 
     * */
    
    public void loadCustomerOrderDetails(){
    	try{
    		initCustomerDetailsView();
    		ObservableList<LoadCustomerDetailsBeans> loadCustomerOrders = FXCollections.observableArrayList();
    		loadCustomerOrders = persist.loadCustomerDetails(RECORD_FILE_NAME);
    		orderDetailsTable.setItems(loadCustomerOrders);
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    }
    
   
    /*
     * 
     * Here when we will select the tab then on select event we are
     *  loading the details i.e either customer details or items details
     * 
     * */
    
    public ChangeListener<Tab> tabPaneChangeListener(){
    	try{
    		ChangeListener<Tab> changeLisener = new ChangeListener<Tab>() {
				
				@Override
				public void changed(ObservableValue<? extends Tab> observable,
						Tab oldValue, Tab newValue) {
					if(newValue == orderDetails){
						loadCustomerOrderDetails();
					}else if(newValue == loadItems){
						updateLabel.setText("");
						loadOrderDetals();
					}
				}
			};
			return changeLisener;
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	return null;
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
