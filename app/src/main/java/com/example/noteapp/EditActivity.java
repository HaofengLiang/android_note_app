package com.example.noteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;

public class EditActivity extends AppCompatActivity {
    private int noteNum;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        editText = findViewById(R.id.editText);
        noteNum = getIntent().getIntExtra("noteNum", MainActivity.notes.size());
        if (noteNum < MainActivity.notes.size()) {
            editText.setText(MainActivity.notes.get(noteNum));
        }

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String text = editable.toString();
                if (noteNum >= MainActivity.notes.size()) {
                    MainActivity.notes.add(text);
                } else {
                    MainActivity.notes.set(noteNum, text);
                }
                SharedPreferences sharedPreferences = getSharedPreferences("com.example.noteapp", Context.MODE_PRIVATE);
                try {
                    sharedPreferences.edit().putString("notes", ObjectSerializer.serialize(MainActivity.notes)).apply();
                    MainActivity.adapter.notifyDataSetChanged();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
