package com.example.root.shopping_cart_navigator.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by root on 4/5/18.
 */

public class ShoppingMaze {
    private int dimX;
    private int dimY;
    private int[][] shopGrid;

    public ShoppingMaze(int dimX, int dimY) {
        this.dimX = dimX;
        this.dimY = dimY;
        shopGrid = new int[dimX][dimY];
        this.initGrid();

    }

    public void initGrid(){
        for (int i = 0; i < dimX ; i++) {
            for (int j = 0; j < dimY; j++) {
                if((j % 2 == 0) || (i==0) || (i== dimX-1)) {
                    shopGrid[i][j] = 0;
                }else{
                    shopGrid[i][j] = 1;
                }
            }
        }
    }

    public int[][] getShopGrid() {
        return shopGrid;
    }

    public void addItemstoMaze(List<Item> items){
        for (int i = 0; i < items.size(); i++) {
            int itemX = items.get(i).getPosition()[0];
            int itemY = items.get(i).getPosition()[1];
            shopGrid[itemX][itemY] = 2;
        }
        shopGrid[0][0] = 3;
    }

    public static void main(String[] args) {
        ShoppingMaze sm = new ShoppingMaze(5, 5);
        int[][] shopGrid = sm.getShopGrid();
        System.out.println(Arrays.deepToString(shopGrid).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));

    }
}
