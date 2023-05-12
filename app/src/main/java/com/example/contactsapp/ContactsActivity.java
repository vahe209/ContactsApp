package com.example.contactsapp;

import static com.example.contactsapp.R.drawable.delete;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ContactsActivity extends AppCompatActivity implements AddContactDialog.DialogListener {
    private ArrayList<Item> itemArrayList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ContactsAdapter adapter;
    private ImageButton imageButton2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        loadData();
        buildRecyclerView();
        imageButton2 = (ImageButton) findViewById(R.id.imageButton2);
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

    }

    private void openDialog() {

        AddContactDialog dialog = new AddContactDialog();
        dialog.show(getSupportFragmentManager(), "Dialog");
    }

    @Override
    public void applyContact(String name, String phone) {
        recyclerView = findViewById(R.id.rv);
        itemArrayList.add(new Item(name, phone, R.drawable.icon, delete));
        adapter = new ContactsAdapter(getApplicationContext(), itemArrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(ContactsActivity.this));
        recyclerView.setAdapter(adapter);
    }

    public void buildRecyclerView() {
        recyclerView = findViewById(R.id.rv);

        Intent intent = getIntent();
        ArrayList<Item> items = new ArrayList<>(intent.getParcelableArrayListExtra("item"));

        Button btnPrev = findViewById(R.id.btn_prev);
        btnPrev.setOnClickListener(v -> goBack());
        adapter = new ContactsAdapter(getApplicationContext(), itemArrayList);

        itemArrayList.addAll(items);
        recyclerView.setLayoutManager(new LinearLayoutManager(ContactsActivity.this));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new ContactsAdapter.OnItemClickListener() {
            @Override
            public void onDeleteClick(int position) {
                removeItem(position);
            }
        });
    }


    public void removeItem(int position) {
        itemArrayList.remove(position);
        adapter.notifyItemRemoved(position);
    }


    private void goBack() {
        onBackPressed();
    }

    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(itemArrayList);
        editor.putString("contacts", json);
        editor.apply();
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("contacts", null);
        Type type = new TypeToken<ArrayList<Item>>() {
        }.getType();
        itemArrayList = gson.fromJson(json, type);
        if (itemArrayList == null) {
            itemArrayList = new ArrayList<>();
        }
    }


    @Override
    protected void onStop() {
        super.onStop();
        saveData();
    }


}


