package com.example.contactsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactsViewHolder> {

    private Context context;
    private List<Item> items;
    static OnItemClickListener mListener;
    public interface OnItemClickListener {
        void onDeleteClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public ContactsAdapter(Context context, ArrayList<Item> items) {
        this.context = context;
        this.items = items;
    }


    @Override
    public ContactsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        ContactsViewHolder cvh = new ContactsViewHolder(v, mListener);
        return cvh;
    }

    @Override
    public void onBindViewHolder(ContactsViewHolder holder, int position) {
        holder.nameView.setText((items.get(position).getName()));
        holder.phoneView.setText(items.get(position).getPhone());
        holder.imageView.setImageResource(items.get(position).getImage());
        holder.imageButton.setImageResource(items.get(position).getImageButton());

    }


    @Override
    public int getItemCount() {
        return items.size();
    }


    public class ContactsViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView nameView, phoneView;
        ImageButton imageButton;


        public ContactsViewHolder(View itemView, final OnItemClickListener listener){
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            nameView = itemView.findViewById(R.id.textViewName);
            phoneView = itemView.findViewById(R.id.textViewNumber);
            imageButton = itemView.findViewById(R.id.imageButton);
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onDeleteClick(position);
                        }
                    }
                }
            });

        }
    }
}


