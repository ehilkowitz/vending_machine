package com.techelevator;

public class Candy extends Item implements Vendable{

    public String vendMessage(){
        return "Munch Munch, Yum!";
    }

    public Candy(String name, String price, String type, Integer quantity) {
        super(name, price, type, quantity);
    }
}
