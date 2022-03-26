package com.techelevator;

import java.math.BigDecimal;
import java.util.*;

public class Inventory {

    //PRIVATE VARIABLES
    private static final int STARTING_QUANTITY = 5;

    //Map of slot:item where slot is a string pulled from csv and item is Item object that pulls name, price, and type from csv
    //  Map<slot location, item>

    private Map<String, Item> inventoryMap = new HashMap<>();

    private Map<String, Integer> sales = new HashMap<>();



    //GETTERS & SETTERS

    public Map<String, Item> getInventoryMap() {
        return inventoryMap;
    }


    public Map<String, Integer> getSales(){
        return sales;
    }

    public void setInventoryMap(Map<String, Item> inventoryMap) {
        this.inventoryMap = inventoryMap;
    }

    //CONSTRUCTORS

    public Inventory(){

    }

    public Inventory(Map<String, Item> inventoryMap) {
        this.inventoryMap = inventoryMap;
    }


    //METHOD

    public void splitCSV (Scanner loadFile){
        //Map<String, Item> myMap= new HashMap<>();

        while (loadFile.hasNextLine()){
            //read each line of csv
            String thisLine = loadFile.nextLine();
            //split at | and put in array
            String[] thingsInLine = thisLine.split("\\|");

            //assign properties from array
            String slotLocation = thingsInLine[0];
            String itemName = thingsInLine[1];
            String itemPrice = thingsInLine[2];
            String itemType = thingsInLine[3];

            //make a new item object
            Item newItem = makeAnItemObject(itemName, itemPrice, itemType);
            //add item with slot location to inventory map
            inventoryMap.put(slotLocation, newItem);
            sales.put(itemName, 0);

        }


    }

    public Item makeAnItemObject (String name, String price, String type){


        return new Item(name, price, type, STARTING_QUANTITY);


    }
    //Create map of inventory with quantities remaining
    public List<String> displayItemsFromMap(){
        List<String> listOfMapEntries = new ArrayList<>();
        String strForList = "";
        for(Map.Entry<String, Item> entry : inventoryMap.entrySet()){
            strForList = entry.getKey() + "|" + entry.getValue().getName() + "| $" + entry.getValue().getPrice() + "| Quantity Remaining: " + entry.getValue().getQuantity();
            listOfMapEntries.add(strForList);
        }
        Collections.sort(listOfMapEntries);
        for(String myListEntry : listOfMapEntries){
            System.out.println(myListEntry);
        }
        return listOfMapEntries;
    }

    public void addToSalesCountMap(String itemName){
        if(sales.containsKey(itemName)){
            sales.put(itemName, sales.get(itemName) + 1);
        }
    }




}
