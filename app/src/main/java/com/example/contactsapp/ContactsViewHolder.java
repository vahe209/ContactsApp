package com.example.contactsapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ContactsViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
    TextView nameView, phoneView;


    public ContactsViewHolder(View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imageView);
        nameView = itemView.findViewById(R.id.textViewName);
        phoneView = itemView.findViewById(R.id.textViewNumber);
    }
}
