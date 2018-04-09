package com.example.root.shopping_cart_navigator.Controller;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by root on 4/9/18.
 */

public class PathNavigateController {
    private Character currentDir = 'S';
    private int[] currentLoc =new  int[2];

    public Character getDirection(int[] newLoc) {
        if(newLoc[0] > currentLoc[0]){
            currentDir = 'S';
            return 'S';
        }else if(newLoc[0] < currentLoc[0]){
            currentDir = 'N';
            return 'N';
        }else if(newLoc[1] > currentLoc[1]){
            currentDir = 'E';
            return 'E';
        }else{
            currentDir = 'W';
            return 'W';
        }
    }

    public ArrayList<Map<Character, Integer>> getDirection(ArrayList<int[]> path){
        ArrayList<Character> directions = new ArrayList<>();
        Collections.reverse(path);
        currentLoc = path.get(0);
        for (int i = 1; i < path.size(); i++) {
            directions.add(getDirection(path.get(i)));
            currentLoc = path.get(i);
        }
        ArrayList<Map<Character, Integer>> steps = new ArrayList<>();
        steps = reduceDirectionList(directions);
        System.out.println(steps);
        return steps;
    }

    public ArrayList<Map<Character, Integer>> reduceDirectionList(ArrayList<Character> directions){
        ArrayList<Map<Character, Integer>> steps = new ArrayList<>();
        int currDrectionStepCount =1;
        Character tempDir = directions.get(0);

        for (int i = 1; i < directions.size(); i++) {
            Map<Character, Integer> step = new HashMap<>();

            if(directions.get(i) == tempDir){
                currDrectionStepCount++;
            } else{
                step.put(tempDir, currDrectionStepCount);
                steps.add(step);
                tempDir = directions.get(i);
                currDrectionStepCount = 1;
            }
        }
        Map<Character, Integer> step = new HashMap<>();

        step.put(tempDir, currDrectionStepCount);
        steps.add(step);
        System.out.println(steps);
        return steps;

    }

    public ArrayList<ArrayList<Map<Character, Integer>>> getDirectionList(ArrayList<ArrayList<int[]>> paths){
        ArrayList<ArrayList<Map<Character, Integer>>> dirList = new ArrayList<>();
        for (int i = 0; i < paths.size(); i++) {
            dirList.add(getDirection(paths.get(i)));
        }
        System.out.println(dirList);
        return dirList;
    }

    public static void main(String[] args) {
        ArrayList<int[]> testPath1 = new ArrayList<>();
        int[] tempA = {4,3};
        int[] tempB = {4,2};
        int[] tempC = {4,1};
        int[] tempD = {4,0};
        int[] tempG = {3,0};
        int[] tempE = {2,0};
        int[] tempEF = {1,0};
        testPath1.add(tempA);
        testPath1.add(tempB);
        testPath1.add(tempC);
        testPath1.add(tempD);
        testPath1.add(tempG);
        testPath1.add(tempE);
        testPath1.add(tempEF);

        ArrayList<int[]> testPath2 = new ArrayList<>();
        int[] temp1 = {1,4};
        int[] temp2 = {2,4};
        int[] temp3 = {3,4};
        int[] temp4 = {4,4};
        int[] temp5 = {4,3};

        testPath2.add(temp1);
        testPath2.add(temp2);
        testPath2.add(temp3);
        testPath2.add(temp4);
        testPath2.add(temp5);


        ArrayList<ArrayList<int[]>> paths = new ArrayList<>();
        paths.add(testPath1);
        paths.add(testPath2);

        PathNavigateController pn = new PathNavigateController();
        pn.getDirectionList(paths);
    }

}
