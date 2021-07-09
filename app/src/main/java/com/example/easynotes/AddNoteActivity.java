package com.example.easynotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

public class AddNoteActivity extends AppCompatActivity {

    public static final String EXTRA_TITLE = "com.example.easynotes.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION = "com.example.easynotes.EXTRA_DESCRIPTION";
    public static final String EXTRA_PRIORITY = "com.example.easynotes.EXTRA_PRIORITY";


    private EditText titleEditText;
    private EditText descriptionEditText;
    private NumberPicker priorityNumberPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        titleEditText = findViewById(R.id.editText_title);
        descriptionEditText = findViewById(R.id.editText_description);
        priorityNumberPicker = findViewById(R.id.numberPicker_priority);

        priorityNumberPicker.setMinValue(1);
        priorityNumberPicker.setMaxValue(5);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Add Note");

    }

    private void saveNote() {
        String title = titleEditText.getText().toString();
        String description = descriptionEditText.getText().toString();
        int priority = priorityNumberPicker.getValue();

        if(title.trim().isEmpty() || description.trim().isEmpty()){
            Toast.makeText(this, "Please, Enter a title and a description", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent noteInfo = new Intent();
        noteInfo.putExtra(EXTRA_TITLE, title);
        noteInfo.putExtra(EXTRA_DESCRIPTION, description);
        noteInfo.putExtra(EXTRA_PRIORITY, priority);

        setResult(RESULT_OK, noteInfo);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.save_note:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}