package com.example.googlemap;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class NoteActivity extends AppCompatActivity {

    private TextView noteText;
    private Button note_btn;
    private DBHelper db;
    private String keyValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        db = new DBHelper(this);
        initComponent();

        Intent intent = getIntent();
        String note = intent.getStringExtra("note");
        keyValue = intent.getStringExtra("name");


        noteText.setText(note);
        note_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String note = noteText.getText().toString().trim();
                db.updateNote(keyValue,note);
                Intent intent = new Intent(getApplicationContext(), PlanActivity.class);
                startActivity(intent);
                finish();


            }
        });


    }
    public void initComponent(){

        noteText = (TextView) findViewById(R.id.NoteText);
        note_btn = (Button) findViewById(R.id.note_button);
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString(keyValue,noteText.getText().toString());
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        noteText.setText(savedInstanceState.getString(keyValue));
    }

}
