package com.zulfadhlikhalid.mynotes;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NotesViewActivity extends AppCompatActivity {

    DatabaseHelper mDatabaseHelper;
    EditText updateNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_view);

        Intent intent=getIntent();
        final String udNote = intent.getStringExtra("addNote");

        //alt+enter
        updateNote = findViewById(R.id.txtUpdateNote);
        Button btnUpdate = findViewById(R.id.btnUpdate);
        Button btnDelete = findViewById(R.id.btnDelete);

        mDatabaseHelper = new DatabaseHelper(this);
        final Cursor data = mDatabaseHelper.getDataForUpdate(udNote);


        if(data != null){

            while (data.moveToNext())
            {

                updateNote.setText(data.getString(1));

            }
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String udNote = updateNote.getText().toString();


                mDatabaseHelper = new DatabaseHelper(NotesViewActivity.this);
                if(data != null && data.moveToFirst()){


                        updateNote.setText(data.getString(1));
                        String udId = data.getString(data.getColumnIndex("id"));

                    boolean updateData = mDatabaseHelper.updateData(udNote,udId);

                    Toast.makeText(getApplicationContext(),"Updated",Toast.LENGTH_SHORT).show();

                    Intent successUpdate = new Intent(NotesViewActivity.this,MainActivity.class);
                    startActivity(successUpdate);

                }data.close();

            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
    }

    public void showDialog(){
        AlertDialog.Builder builder= new AlertDialog.Builder(NotesViewActivity.this);

        builder.setTitle("Confirm");
        builder.setMessage("Are you sure you want to delete?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String udNote = updateNote.getText().toString();
                mDatabaseHelper = new DatabaseHelper(NotesViewActivity.this);
                boolean deleteData = mDatabaseHelper.deleteData(udNote);
                Intent deleteIntent = new Intent(NotesViewActivity.this,MainActivity.class);
                startActivity(deleteIntent);
                Toast.makeText(getApplicationContext(),"Delete Success",Toast.LENGTH_SHORT).show();

                dialog.dismiss();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }
}
