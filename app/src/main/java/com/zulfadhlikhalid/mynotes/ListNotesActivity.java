package com.zulfadhlikhalid.mynotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListNotesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_notes);

        final ListView lvProductList = findViewById(R.id.lvNotes);
        final DatabaseHelper mDatabaseHelper = new DatabaseHelper(this);

        final Cursor data = mDatabaseHelper.getData();
        ArrayList<String> listData = new ArrayList<>();

        while (data.moveToNext()){
            listData.add(data.getString(1));
        }

        ListAdapter adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,listData);
        lvProductList.setAdapter(adapter);

        lvProductList.setClickable(true);
        lvProductList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Object o = lvProductList.getItemAtPosition(position);

                Intent nextUpdateProduct = new Intent(ListNotesActivity.this,NotesViewActivity.class);
                nextUpdateProduct.putExtra("addNote",o.toString());
                startActivity(nextUpdateProduct);
            }
        });

    }
}