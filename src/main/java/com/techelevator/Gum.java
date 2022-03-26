package com.techelevator;

public class Gum extends Item implements Vendable{

    public String vendMessage(){
        return "Chew Chew, Yum!";
    }

    public Gum(String name, String price, String type, Integer quantity) {
        super(name, price, type, quantity);
    }
}
