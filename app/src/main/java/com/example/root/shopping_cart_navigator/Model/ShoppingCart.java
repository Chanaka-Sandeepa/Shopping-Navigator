package com.example.root.shopping_cart_navigator.Model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by root on 4/5/18.
 */

public class ShoppingCart {
    List<Item> items = new ArrayList<Item>();

    public List<Item> getItems() throws ParseException {


        Item item1 = new Item();
        item1.setCategory("shirt");
        item1.setName("formal");
        item1.setPrice(3000);
        String sDate = "2018-01-31";
        Date date = new SimpleDateFormat("yyyy-mm-dd").parse(sDate);
        item1.setDateOfPurchace(date);
        item1.setPosition(new int[]{1,0});
        items.add(item1);

        Item item2 = new Item();
        item2.setCategory("shirt");
        item2.setName("casual");
        item2.setPrice(3000);
        item2.setDateOfPurchace(date);
        item2.setPosition(new int[]{2,2});
        items.add(item2);

        Item item3 = new Item();
        item3.setCategory("mobile");
        item3.setName("smart phone");
        item3.setPrice(3000);
        item3.setDateOfPurchace(date);
        item3.setPosition(new int[]{3,4});
        items.add(item3);

        return items;


    }
}
