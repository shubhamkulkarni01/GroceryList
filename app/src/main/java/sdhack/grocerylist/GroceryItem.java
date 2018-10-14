package sdhack.grocerylist;

import java.util.Date;

public class GroceryItem {

    public String name;
    public double price;
    public int quantity;
    public Date previousPurchase, predicted;
    public boolean prediction = false;

    public GroceryItem(String name, double price, int quantity){
        this.name = name;
        this.price = price;
        this.quantity=quantity;
    }

    @Override
    public boolean equals(Object obj) {
        return this.name.equals(((GroceryItem)obj).name);
    }

    public GroceryItem incrementQuantity(){
        quantity++;
        return this;
    }
}
