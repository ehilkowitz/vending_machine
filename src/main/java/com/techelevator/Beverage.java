package com.techelevator;

public class Beverage extends Item implements Vendable{

    public String vendMessage(){
        return "Glug Glug, Yum!";
    }

    public Beverage(String name, String price, String type, Integer quantity) {
        super(name, price, type, quantity);
    }
}
