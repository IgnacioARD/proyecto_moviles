package com.example.proyecto_moviles;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.GridView;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class FoodList extends AppCompatActivity {

    GridView gridView;
    ArrayList<Food> list;
    FoodListAdapter adapter = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_list_activity);

        gridView = (GridView) findViewById(R.id.gridView);
        list = new ArrayList<>();
        adapter = new FoodListAdapter(this, R.layout.food_items, list);
        gridView.setAdapter(adapter);

        //get all data from sqlite
        Cursor cursor = MainActivity.sqLiteHelper.getData("SELECT * FROM FOOD");
        list.clear();
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String price = cursor.getString(2);
            byte[] image = cursor.getBlob(3);

            list.add(new Food(name, price, image,id));
        }
        adapter.notifyDataSetChanged();
    }
}
