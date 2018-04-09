package com.example.root.shopping_cart_navigator.Controller;

import android.widget.ListView;

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
    private int[][] initialGrid;
    ShoppingMaze sm = new ShoppingMaze(5, 5);


    public ShoppingNavigateController() throws ParseException {
        ShoppingCart sc =new ShoppingCart();
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

    public ArrayList<ArrayList<int []>> calculatePath(){
        MazeSolver mz = new MazeSolver(5, 5, items.size());
        mz.gridBFS(0,0, shopGrid, mz.getMapArray());
        ArrayList<ArrayList<int[]>> path = mz.getPath();
//        System.out.println(path);
//        addPathToGrid(path);
        return path;

    }

    public void addPathToGrid(ArrayList<int[]> path){
//        System.out.println(Arrays.deepToString(path).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));
        shopGrid = sm.copyInitialGrid();
        for (int i = 0; i < path.size(); i++) {
            int xc = path.get(i)[0];
            int yc = path.get(i)[1];
            if(shopGrid[xc][yc] != 2){
                shopGrid[xc][yc] = 4;
            }
        }
    }


    public static void main(String[] args) throws ParseException {
        ShoppingNavigateController sn = new ShoppingNavigateController();
        sn.calculatePath();
        System.out.println(Arrays.deepToString(sn.getShopGrid()).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));

    }
}
