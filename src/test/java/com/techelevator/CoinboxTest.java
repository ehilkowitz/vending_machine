package com.techelevator;

import com.techelevator.Coinbox;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.junit.*;


import java.math.BigDecimal;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
    public class CoinboxTest {

    private BigDecimal moneyFed;
    private BigDecimal balance;
    private Coinbox coinTest;
    private BigDecimal expected;

    @Before
    public void setup() {

        coinTest = new Coinbox();
    }

    @After
    public void teardown() {
        coinTest = null;
    }


    @Test
    public void test01_CoinboxONE_valid() {

        coinTest.addOneToBalance();
        expected = (new BigDecimal("1.00"));
        Assert.assertEquals(expected, coinTest.getBalance());
    }

    @Test
    public void test02_CoinboxFIVE_valid() {
        coinTest.addFiveToBalance();
        expected = new BigDecimal("5.00");
        Assert.assertEquals(expected, coinTest.getBalance());

    }

    @Test
    public void test03_CoinboxTEN() {
        coinTest.addTenToBalance();
        expected = new BigDecimal("10.00");
        Assert.assertEquals(expected, coinTest.getBalance());

    }

    @Test
    public void test04_doTransactionTest() {

        //balance = new BigDecimal("10.00");
        BigDecimal expectedBDbalance = new BigDecimal("8.50");
        coinTest.addTenToBalance();
        String itemPriceBD = "1.50";

        Assert.assertEquals(expectedBDbalance, coinTest.doTransaction(itemPriceBD));

    }


    @Test //Testing the makeChange method which is void?
    public void test05_makeChange_with_zero_to_return() {

        String expected = "Your Change: 0 quarters, 0 dimes, 0 nickles.";

        Assert.assertEquals(expected, coinTest.makeChange());

    }

    @Test //Testing the makeChange method which is void?
    public void test06_makeChange_with_zero_nickles_zero_dimes_to_return() {
        coinTest.addOneToBalance();
        String expected = "Your Change: 4 quarters, 0 dimes, 0 nickles.";

        Assert.assertEquals(expected, coinTest.makeChange());

    }

    @Test //Testing the makeChange method which is void?
    public void test07_makeChange_with_zero_quarters_to_return() {
        coinTest.addOneToBalance();
        coinTest.doTransaction("0.85");
        String expected = "Your Change: 0 quarters, 1 dimes, 1 nickles.";

        Assert.assertEquals(expected, coinTest.makeChange());

    }

}









