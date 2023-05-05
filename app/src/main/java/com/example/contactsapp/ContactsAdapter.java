package com.example.contactsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsViewHolder> {
    Context context;
    List<Item> items;


    public ContactsAdapter(Context context, ArrayList<Item> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public ContactsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ContactsViewHolder(LayoutInflater.from(context).inflate(R.layout.item, parent, false));
    }

    @Override
    public void onBindViewHolder(ContactsViewHolder holder, int position) {
        holder.nameView.setText((items.get(position).getName()));
        holder.phoneView.setText(items.get(position).getPhone());
        holder.imageView.setImageResource(items.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}


