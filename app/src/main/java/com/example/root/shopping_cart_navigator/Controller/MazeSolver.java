package com.example.root.shopping_cart_navigator.Controller;

import com.example.root.shopping_cart_navigator.View.Maze;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;

/**
 * Created by root on 4/6/18.
 */

public class MazeSolver {

    public Map<Object, Object>[][] mapArray;
    private int dimX;
    private int dimY;
    private int cost = 0;
    int goalItemsCount;
    int collectedItemsCount = 0;
    ArrayList<ArrayList<int[]>> path = new ArrayList<ArrayList<int[]>>();

    public MazeSolver(int dimX, int dimY, int goalItemsCount) {
        this.goalItemsCount = goalItemsCount;
        initMapArray(dimX, dimY);
    }

    public void initMapArray(int x, int y){
        mapArray = new Map[x][y];
        this.dimX = x;
        this.dimY = y;
        for (int i = 0; i <x ; i++) {
            for (int j = 0; j <y ; j++) {
                Map<Object, Object> map = new HashMap<>();
                map.put("visited", false);
                map.put("cost", 1000);
                map.put("pred", null);
                mapArray[i][j] = map;
            }
        }
    }

    public int[][] copyInitialGrid(int[][] local_shopGrod, int[][] initialGrid){
        for(int i=0; i<initialGrid.length; i++) {
            for (int j = 0; j < initialGrid[i].length; j++)
                local_shopGrod[i][j] = initialGrid[i][j];
        }
        return local_shopGrod;
    }

    public Map<Object, Object>[][] getMapArray() {
        return mapArray;
    }

    public boolean gridBFS(int startX, int startY, int[][] shopGrid, Map<Object, Object>[][] solvedGrid){
        int[][] local_shopGrod = new int[shopGrid.length][shopGrid[0].length];
        local_shopGrod = copyInitialGrid(local_shopGrod, shopGrid);
        int[] pred = new int[2];
        pred[0]= startX;
        pred[1]= startY;
        if(shopGrid[startX][startY] == 2)
        {
            markMap(startX, startY, solvedGrid, true, cost, pred);
        }
        else {
            Queue<int[]> q=new LinkedList<int[]>();
            int[]start=pred;//Start Coordinates
            q.add(start);
            markMap(startX, startY, solvedGrid, true, 1000, pred);
            while(q.peek()!=null){
                int[] curr=q.poll();
                int pred_cost = (int)solvedGrid[curr[0]][curr[1]].get("cost");

                if(curr[0] < dimX-1){
                    int xc=curr[0]+1;
                    int yc=curr[1];
//                    if((boolean)solvedGrid[xc][yc].get("visited") == false || (int)solvedGrid[xc][yc].get("cost") > pred_cost+1) {

                        if (shopGrid[xc][yc] == 2)//Destination found
                        {
//                            if((boolean)solvedGrid[xc][yc].get("visited") == true){
//                                collectedItemsCount--;
//                            }
                            local_shopGrod[xc][yc] = 1;
                            System.out.println("found");
                            int curr_cost = pred_cost + 1;
                            markMap(xc, yc, solvedGrid, true, curr_cost, curr);
                            int[] temp = curr;
                            collectedItemsCount++;
                            path.add(backTrack(start, temp, solvedGrid));
                            if(collectedItemsCount == goalItemsCount){
                                return true;
                            }
                            gridBFS(curr[0], curr[1], local_shopGrod, solvedGrid);
                            break;
//                        return true;
                        } else if (shopGrid[xc][yc] == 0)//Movable. Can't return here again so setting it to 'B' now
                        {
                            //System.out.println(xc+" "+yc);
                            int[] temp = {xc, yc};
                            if(((int)solvedGrid[xc][yc].get("cost") > pred_cost+1) || (((int)solvedGrid[xc][yc].get("cost"))== 1000)){
                                int curr_cost = pred_cost + 1;
                                markMap(xc, yc, solvedGrid, true, curr_cost, curr);//now BLOCKED
                            }
                            if(collectedItemsCount == goalItemsCount){
                                return true;
                            }
                            q.add(temp);//Adding current coordinates to the queue
                        }
//                    }
                }
                if(curr[1] < dimY-1){
                    int xc=curr[0];
                    int yc=curr[1]+1;
//                    if((boolean)solvedGrid[xc][yc].get("visited") == false || (int)solvedGrid[xc][yc].get("cost") > pred_cost+1) {

                        if (shopGrid[xc][yc] == 2)//Destination found
                        {
//                            if((boolean)solvedGrid[xc][yc].get("visited") == true){
//                                collectedItemsCount--;
//                            }
                            local_shopGrod[xc][yc] = 1;
                            System.out.println("found");
                            int curr_cost = pred_cost + 1;
                            markMap(xc, yc, solvedGrid, true, curr_cost, curr);
                            int[] temp = curr;
                            collectedItemsCount++;
                            path.add(backTrack(start, temp, solvedGrid));
                            if(collectedItemsCount == goalItemsCount){
                                return true;
                            }
                            gridBFS(curr[0], curr[1], local_shopGrod, solvedGrid);
                            break;

//                        return true;
                        } else if (shopGrid[xc][yc] == 0)//Movable. Can't return here again so setting it to 'B' now
                        {
                            //System.out.println(xc+" "+yc);
                            int[] temp = {xc, yc};
                            if((int)solvedGrid[xc][yc].get("cost") > pred_cost+1 || (((int)solvedGrid[xc][yc].get("cost"))== 1000)){
                                int curr_cost = pred_cost + 1;
                                markMap(xc, yc, solvedGrid, true, curr_cost, curr);//now BLOCKED
                            }
                            if(collectedItemsCount == goalItemsCount){
                                return true;
                            }
                            q.add(temp);//Adding current coordinates to the queue
//                        }
                    }
                }
                if(curr[0] > 0){
                    int xc=curr[0]-1;
                    int yc=curr[1];
//                    if((boolean)solvedGrid[xc][yc].get("visited") == false || (int)solvedGrid[xc][yc].get("cost") > pred_cost+1){
                        if(shopGrid[xc][yc]==2)//Destination found
                        {
//                            if((boolean)solvedGrid[xc][yc].get("visited") == true){
//                                collectedItemsCount--;
//                            }
                            local_shopGrod[xc][yc] = 1;
                            System.out.println("found");
                            int curr_cost = pred_cost + 1;
                            markMap(xc, yc, solvedGrid, true, curr_cost, curr);
                            int[]temp=curr;
                            collectedItemsCount++;
                            path.add(backTrack(start, temp, solvedGrid));
                            if(collectedItemsCount == goalItemsCount){
                                return true;
                            }
                            gridBFS(curr[0], curr[1], local_shopGrod, solvedGrid);
                            break;

//                        return true;
                        }
                        else if(shopGrid[xc][yc]==0)//Movable. Can't return here again so setting it to 'B' now
                        {
                            //System.out.println(xc+" "+yc);
                            int[]temp={xc,yc};
                            if((int)solvedGrid[xc][yc].get("cost") > pred_cost+1 || (((int)solvedGrid[xc][yc].get("cost"))== 1000)){
                                int curr_cost = pred_cost + 1;
                                markMap(xc, yc, solvedGrid, true, curr_cost, curr);//now BLOCKED
                            }
                            if(collectedItemsCount == goalItemsCount){
                                return true;
                            }
                            q.add(temp);//Adding current coordinates to the queue
                        }
//                    }
                }
                if(curr[1] > 0){
                    int xc=curr[0];
                    int yc=curr[1]-1;
//                    if((boolean)solvedGrid[xc][yc].get("visited") == false || (int)solvedGrid[xc][yc].get("cost") > pred_cost+1) {

                        if (shopGrid[xc][yc] == 2)//Destination found
                        {
//                            if((boolean)solvedGrid[xc][yc].get("visited") == true){
//                                collectedItemsCount--;
//                            }
                            local_shopGrod[xc][yc] = 1;
                            System.out.println("found");
                            int curr_cost = pred_cost + 1;
                            markMap(xc, yc, solvedGrid, true, curr_cost, curr);
                            int[] temp = curr;
                            collectedItemsCount++;
                            path.add(backTrack(start, temp, solvedGrid));
                            if(collectedItemsCount == goalItemsCount){
                                return true;
                            }
                            gridBFS(curr[0], curr[1], local_shopGrod, solvedGrid);
                            break;

//                        return true;
                        } else if (shopGrid[xc][yc] == 0)//Movable. Can't return here again so setting it to 'B' now
                        {
                            //System.out.println(xc+" "+yc);
                            int[] temp = {xc, yc};
                            if((int)solvedGrid[xc][yc].get("cost") > pred_cost+1 || (((int)solvedGrid[xc][yc].get("cost"))== 1000)){
                                int curr_cost = pred_cost + 1;
                                markMap(xc, yc, solvedGrid, true, curr_cost, curr);//now BLOCKED
                            }
                            if(collectedItemsCount == goalItemsCount){
                                return true;
                            }
                            q.add(temp);//Adding current coordinates to the queue
                        }
//                    }
                }
            }
        }
        return false;
    }

    public ArrayList<int[]> backTrack(int[] start, int[] dest,Map<Object, Object>[][] solvedGrid){
        ArrayList<int[]> local_path = new ArrayList<int[]>();
        int[] curr =dest;
//        int cur_cost = (int)solvedGrid[dest[0]][dest[1]].get("cost");
        while (curr != start){
            local_path.add(curr);
            curr =(int[])solvedGrid[curr[0]][curr[1]].get("pred");
//            cur_cost = (int)solvedGrid[curr[0]][curr[1]].get("cost");
        }
        local_path.add(curr);

        return local_path;
    }

    public void markMap(int x, int y, Map<Object, Object>[][] solvedGrid, boolean visited, int cost, int[] pred){
        solvedGrid[x][y].put("visited", visited);
        solvedGrid[x][y].put("cost", cost);
        solvedGrid[x][y].put("pred", pred);
    }

    public ArrayList<ArrayList<int[]>> getPath() {
        return path;
    }

    public static void main(String[] args) {
//        MazeSolver mz = new MazeSolver(5, 5, 3);
//        Map<Object, Object>[][] mapArray = mz.getMapArray();
//        int[][] grid = {{0, 0, 0, 0, 0},
//                        {2, 1, 0, 1, 0},
//                        {0, 1, 2, 1, 0},
//                        {0, 1, 0, 1, 2},
//                        {0, 0, 0, 0, 0}};
//        boolean result =mz.gridBFS(0, 0, grid, mapArray);
//        mapArray = mz.getMapArray();
//        List<int[]> path = mz.getPath();
//        for (int i = 0; i < path.size(); i++) {
//            System.out.print(Arrays.toString(path.get(i)));
//        }

//        System.out.println(Arrays.deepToString(mapArray).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));


    }
}
