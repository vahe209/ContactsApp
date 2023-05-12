package com.example.contactsapp;

import android.os.Parcel;
import android.os.Parcelable;



public class Item  implements Parcelable{
    private String name;
    private String phone;
    private int image;
    private int imageButton;


    public Item(String name, String phone, int image, int imageButton) {
        this.name = name;
        this.phone = phone;
        this.image = image;
       this.imageButton = imageButton;
    }


    protected Item(Parcel in) {
        name = in.readString();
        phone = in.readString();
        image = in.readInt();
        imageButton = in.readInt();
    }

    public int getImageButton() {
        return imageButton;
    }

    public void setImageButton(int imageButton) {
        this.imageButton = imageButton;
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(phone);
        parcel.writeInt(image);
        parcel.writeInt(imageButton);
    }
}