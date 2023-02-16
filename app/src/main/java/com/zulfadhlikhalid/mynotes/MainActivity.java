package com.zulfadhlikhalid.mynotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button toAddNote = findViewById(R.id.btnAddNotes);
        Button toListNote = findViewById(R.id.btnListNotes);

        //navigate to Add Notes Activity
        toAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextAddNotes = new Intent(MainActivity.this,AddNotesActivity.class);
                startActivity(nextAddNotes);
            }
        });

        //navigate to List Notes Activity
        toListNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextListNote = new Intent(MainActivity.this, ListNotesActivity.class);
                startActivity(nextListNote);
            }
        });
    }
}