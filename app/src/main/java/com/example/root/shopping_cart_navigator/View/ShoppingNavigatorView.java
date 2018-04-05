package com.example.root.shopping_cart_navigator.View;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.root.shopping_cart_navigator.Controller.ShoppingNavigateController;
import com.example.root.shopping_cart_navigator.R;

import java.text.ParseException;

public class ShoppingNavigatorView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sopping_navigator_view);
        ShoppingNavigateController sn = null;
        ImageView image=(ImageView)findViewById(R.id.imageView1);

        try {
            sn = new ShoppingNavigateController();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        int[][] grid = sn.getShopGrid();
        Bitmap[] bitmaps = {
                BitmapFactory.decodeResource(getResources(), R.drawable.box),
                BitmapFactory.decodeResource(getResources(), R.drawable.lines),
                BitmapFactory.decodeResource(getResources(), R.drawable.plain_coin_2_1),
                BitmapFactory.decodeResource(getResources(), R.drawable.here)
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


}