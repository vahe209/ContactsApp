package com.example.contactsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText textName, textPhone;
    Button btn,
            btn_delete;
    ImageButton btn_next_activity;
    List<Item> items = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(MainActivity.this, ContactsActivity.class);
        
        btn = findViewById(R.id.button);
        textName = (EditText) findViewById(R.id.EditName);
        textPhone = (EditText) findViewById(R.id.EditPhone);
        btn_next_activity = findViewById(R.id.btn_next_activity);
        btn_delete = findViewById(R.id.btn_delete);
        
        btn_delete.setOnClickListener(v -> delContact());
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = textName.getText().toString();
                String phone = textPhone.getText().toString();
                items.add(new Item(name, phone, R.drawable.icon));
            }
        });

        btn_next_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putParcelableArrayListExtra("item", (ArrayList) items);
                startActivity(intent);
            }
        });

    }

    private void delContact() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("contacts");
        editor.apply();
    }

    @Override
    protected void onStop() {
        super.onStop();
        items = new ArrayList<>();

    }
}
