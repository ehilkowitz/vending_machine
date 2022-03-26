package com.techelevator;

import java.math.BigDecimal;

public class Item {
    //PRIVATE VARIABLES
    private String name;
    private String price;
    private String type;
    private Integer quantity;



    //GETTERS & SETTERS
    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }
    public String getType() {
        return type;
    }
    public Integer getQuantity(){ return quantity; }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    //CONSTRUCTORS

    public Item(String name, String price, String type, Integer quantity) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.quantity = quantity;
    }


    //METHODS
    public String vendMessage(){
        //Display message depending on what product type
        return type.equals("Drink")? "Glug Glug, Yum!": type.equals("Candy")? "Munch Munch, Yum!": type.equals("Chip")? "Crunch Crunch, Yum!": type.equals("Gum")? "Chew Chew, Yum!": "" ;

    }
}
