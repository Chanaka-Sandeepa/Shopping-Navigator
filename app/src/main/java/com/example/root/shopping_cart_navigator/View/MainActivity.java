package com.example.root.shopping_cart_navigator.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.root.shopping_cart_navigator.Controller.ShoppingNavigateController;
import com.example.root.shopping_cart_navigator.R;

import java.text.ParseException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ShoppingNavigateController sn = new ShoppingNavigateController();

    public MainActivity() throws ParseException {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView itemList = (ListView) findViewById(R.id.itemList);
        setItemList(itemList);
    }

    public void setItemList(ListView lv){
        ArrayList<String> list = sn.getItemList();
        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                list);
        lv.setAdapter(adapter);

    }

    public void getDirections(View view){
        Intent myIntent = new Intent(this, ShoppingNavigatorView.class);
        startActivity(myIntent);
    }
}
