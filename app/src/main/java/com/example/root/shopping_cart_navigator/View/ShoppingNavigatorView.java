package com.example.root.shopping_cart_navigator.View;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.root.shopping_cart_navigator.Controller.ShoppingNavigateController;
import com.example.root.shopping_cart_navigator.R;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;

public class ShoppingNavigatorView extends AppCompatActivity {
    ShoppingNavigateController sn =new ShoppingNavigateController();
    ArrayList<ArrayList<int[]>> paths;
    private int pathIndex =0;


    public ShoppingNavigatorView() throws ParseException {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sopping_navigator_view);

        RelativeLayout lay = (RelativeLayout) findViewById(R.id.activity_sopping_navigator_view);
        lay.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                try {
                    getPath();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });

        ShoppingNavigateController sn = null;
        try {
            sn = new ShoppingNavigateController();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        paths = sn.calculatePath();

        loadActivity(sn);
    }

    public void loadActivity(ShoppingNavigateController sn){

        ImageView image=(ImageView)findViewById(R.id.imageView1);

        int[][] grid = sn.getShopGrid();
        Bitmap[] bitmaps = {
                BitmapFactory.decodeResource(getResources(), R.drawable.box),
                BitmapFactory.decodeResource(getResources(), R.drawable.lines),
                BitmapFactory.decodeResource(getResources(), R.drawable.plain_coin_2_1),
                BitmapFactory.decodeResource(getResources(), R.drawable.here),
                BitmapFactory.decodeResource(getResources(), R.drawable.path),
        };


        // Chance the 480 and 320 to match the screen size of your device
        Maze maze = new Maze(bitmaps, grid, 5, 5, 100, 100);

        Bitmap bitMap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);  //creates bmp
        bitMap = bitMap.copy(bitMap.getConfig(), true);     //lets bmp to be mutable
        Canvas canvas = new Canvas(bitMap);                 //draw a canvas in defined bmp

        Paint paint = new Paint();
        // smooths
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(4.5f);
        // opacity
        //p.setAlpha(0x80); //
        maze.drawMaze(canvas, 0, 0);
        image.setImageBitmap(bitMap);
    }

    public void getPath() throws ParseException {
        if(pathIndex < paths.size()) {
            sn.addPathToGrid(paths.get(pathIndex));
            System.out.println(paths);
            System.out.println(Arrays.deepToString(sn.getShopGrid()).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));
            loadActivity(sn);
            pathIndex++;
        }else{
            System.out.println("Collected all the items");
        }
    }



}