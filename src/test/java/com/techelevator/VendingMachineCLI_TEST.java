package com.techelevator;

import com.techelevator.Inventory;
import com.techelevator.VendingMachineCLI;
import com.techelevator.view.Menu;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
//FOR FUTURE - ADD IN MOCKITO TESTING
public class VendingMachineCLI_TEST {




    private VendingMachineCLI cliTester;
    private Inventory testInventory;
    private Menu testMenu;

    @Before
    public void setup() throws FileNotFoundException {
        cliTester = new VendingMachineCLI(testMenu, testInventory);
    }

    @After
    public void teardown(){
        cliTester = null;
    }



    //look into Mockito testing



}
