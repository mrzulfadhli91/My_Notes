package com.zulfadhlikhalid.mynotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddNotesActivity extends AppCompatActivity {

    DatabaseHelper mDatabaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notes);

        Button saveNote = findViewById(R.id.btnSave);
        Button cancelSaveNote= findViewById(R.id.btnCancel);
        final EditText noteArea = findViewById(R.id.txtNoteArea);

        saveNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String addNote = noteArea.getText().toString();

                mDatabaseHelper = new DatabaseHelper(AddNotesActivity.this);
                boolean InsertData = mDatabaseHelper.addData(addNote);

                Toast.makeText(AddNotesActivity.this, "Notes Added", Toast.LENGTH_SHORT).show();

                Intent nextToAddProduct = new Intent(AddNotesActivity.this,MainActivity.class);
                startActivity(nextToAddProduct);
            }
        });

        cancelSaveNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cancel = new Intent(AddNotesActivity.this,MainActivity.class);
                startActivity(cancel);
            }
        });
    }
}