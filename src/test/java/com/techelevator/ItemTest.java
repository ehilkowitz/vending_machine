package com.techelevator;

import com.techelevator.Inventory;
import com.techelevator.Item;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ItemTest {


    private Item itemTest;

    @Before
    public void setup(){
        itemTest = new Item("", "", "", 0);
    }
    @After
    public void teardown(){
        itemTest = null;
    }


    @Test
    public void test01_vendMessage_valid_chips(){
        String expected = "Crunch Crunch, Yum!";
        itemTest = new Item("Chips", "1.50", "Chip", 1);

        Assert.assertEquals(expected,itemTest.vendMessage());
    }

    @Test
    public void test01_vendMessage_valid_drinks(){
        String expected = "Glug Glug, Yum!";
        itemTest = new Item("Cola", "1.50", "Drink", 1);

        Assert.assertEquals(expected,itemTest.vendMessage());
    }

    @Test
    public void test01_vendMessage_valid_gum(){
        String expected = "Chew Chew, Yum!";
        itemTest = new Item("Gum", "1.50", "Gum", 1);

        Assert.assertEquals(expected,itemTest.vendMessage());
    }

    @Test
    public void test01_vendMessage_valid_candy(){
        String expected = "Munch Munch, Yum!";
        itemTest = new Item("Twix", "1.50", "Candy", 1);

        Assert.assertEquals(expected,itemTest.vendMessage());
    }

    @Test
    public void test01_vendMessage_blank(){
        String expected = "";
        itemTest = new Item("Cola", "1.50", "", 1);


        Assert.assertEquals(expected,itemTest.vendMessage());
    }

}
