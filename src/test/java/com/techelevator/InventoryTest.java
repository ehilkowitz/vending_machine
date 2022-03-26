package com.techelevator;

import com.techelevator.Inventory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InventoryTest {


    private Inventory invTest;

    @Before
    public void setup(){
        invTest = new Inventory();
    }
    @After
    public void teardown(){
        invTest = null;
    }
    //Test retrieval and display of inventoryMap
    //Test splitCSV method
    //  -make new Item
    //Test makeAnItemObject

    @Test
    public void test01_displayItemsMaps_valid(){
        String expected = "";

    }
    public void test02_blankCSV_produces_no_items(){

    }

    @Test
    public void test02_makeAnItemObject(){
        String name = "Twix";
        String price = "2.00";
        String type = "Candy";
        Item expectedItem = new Item(name, price, type, 5);
        Item actualItem = invTest.makeAnItemObject(name, price, type);
//        Assert.assertEquals(expectedItem, actualItem);
        //Assert.assertEquals(expectedItem, actualItem);
        Assert.assertSame(expectedItem.getPrice(),actualItem.getPrice());
        Assert.assertSame(expectedItem.getName(),actualItem.getName());
        Assert.assertSame(expectedItem.getType(),actualItem.getType());
        Assert.assertSame(5,actualItem.getQuantity());

    }

    @Test
    public void test03_makesListOfDisplayItems(){
        Item newTestItem = new Item("Potato Crisps", "3.05", "Chip",5);

        List<String> testList = new ArrayList<>();
        String testString = "A1|Potato Crisps| $3.05| Quantity Remaining: 5";
        testList.add(testString);

        Map<String, Item> testMap = new HashMap<>();
        testMap.put("A1", newTestItem);
        invTest.setInventoryMap(testMap);

        List<String> actualList = invTest.displayItemsFromMap();
        Assert.assertEquals(testList, actualList);

        //testMap.put("A1", newTestItem);




    }


}
