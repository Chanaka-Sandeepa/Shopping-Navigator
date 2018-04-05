package com.example.root.shopping_cart_navigator.Controller;

import com.example.root.shopping_cart_navigator.Model.Item;
import com.example.root.shopping_cart_navigator.Model.ShoppingCart;
import com.example.root.shopping_cart_navigator.Model.ShoppingMaze;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by root on 4/5/18.
 */

public class ShoppingNavigateController {

    List<Item> items;
    private int[][] shopGrid;

    public ShoppingNavigateController() throws ParseException {
        ShoppingCart sc =new ShoppingCart();
        ShoppingMaze sm = new ShoppingMaze(5, 5);
        items = sc.getItems();
        sm.addItemstoMaze(items);
        shopGrid = sm.getShopGrid();
        System.out.println(Arrays.deepToString(shopGrid).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));
    }

    public int[][] getShopGrid() {
        return shopGrid;
    }

    public ArrayList<String> getItemList(){
        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < items.size(); i++) {
            String item = items.get(i).getCategory() + "("+ items.get(i).getName() + ")" + " - " + items.get(i).getPrice();
            list.add(item);
        }
        return list;
    }

    public static void main(String[] args) throws ParseException {
        ShoppingNavigateController sn = new ShoppingNavigateController();
    }
}
