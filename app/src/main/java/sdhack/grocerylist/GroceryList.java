package sdhack.grocerylist;

import java.util.ArrayList;

public class GroceryList extends ArrayList<GroceryItem> {
    public void update(GroceryItem item){
        get(indexOf(item)).incrementQuantity();
    }
}
