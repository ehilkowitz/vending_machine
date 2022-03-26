package com.techelevator;

import java.math.BigDecimal;

public class Coinbox {
    

    //CLASS VARIABLES
    private BigDecimal moneyFed;

    private BigDecimal balance;

    private static final BigDecimal QUARTER_BD = new BigDecimal("0.25");
    private static final BigDecimal DIME_BD = new BigDecimal("0.10");
    private static final BigDecimal NICKEL_BD = new BigDecimal("0.05");

    //private BigDecimal change;

    public static final BigDecimal ONE = new BigDecimal("1.00");
    public static final BigDecimal FIVE = new BigDecimal("5.00");
    public static final BigDecimal TEN = new BigDecimal("10.00");

    //GETTERS & SETTERS


    public BigDecimal getMoneyFed() {
        return moneyFed;
    }

    public BigDecimal getBalance() {
        return balance;
    }


    //CONSTRUCTORS
    public Coinbox (){
        this.balance = new BigDecimal("0.00");
    }

    public Coinbox (BigDecimal moneyFed, BigDecimal balance){
        this.balance = balance;

        this.moneyFed = moneyFed;
    }

    //METHODS
    public BigDecimal doTransaction(String itemPrice){
        //
        BigDecimal itemPriceBD = new BigDecimal(itemPrice);
        balance = balance.subtract(itemPriceBD);
        return balance;
    }

    //ADDING TO CUSTOMER BALANCE
    public void addOneToBalance(){balance = balance.add(Coinbox.ONE);}

    public void addFiveToBalance(){balance = balance.add(Coinbox.FIVE);}

    public void addTenToBalance(){balance = balance.add(Coinbox.TEN);}

    // MAKING CHANGE FOR CUSTOMER
    public String makeChange(){

        System.out.println("Balance: $" + balance);
        BigDecimal numberOfQuartersInChangeBD = balance.divide(QUARTER_BD);

        long longQuartersInChange = numberOfQuartersInChangeBD.longValue();

        BigDecimal notQuarters = (numberOfQuartersInChangeBD.subtract(BigDecimal.valueOf(longQuartersInChange)).multiply(QUARTER_BD));

        BigDecimal dimesInChange = notQuarters.divide(DIME_BD);

        long longDimesInChange = dimesInChange.longValue();

        BigDecimal notDimes = (dimesInChange.subtract(BigDecimal.valueOf(longDimesInChange)).multiply(DIME_BD));

        BigDecimal nicklesInChange = notDimes.divide(NICKEL_BD);
        long longNicklesInChange = nicklesInChange.longValue();

        String output = "Your Change: " + longQuartersInChange + " quarters, " + longDimesInChange + " dimes, " + longNicklesInChange + " nickles.";
        System.out.println(output);
        setBalanceToZero();
        return output;

    }
    //AFTER CHANGE IS PROVIDE CUSTOMER BALANCE IS SET TO ZERO
    private void setBalanceToZero(){
        balance = BigDecimal.ZERO;

    }

}
