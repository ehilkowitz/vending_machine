package com.techelevator;

public class Chip extends Item implements Vendable{

    public String vendMessage(){
        return "Crunch Crunch, Yum!";
    }

    public Chip(String name, String price, String type, Integer quantity) {
        super(name, price, type, quantity);
    }


}
