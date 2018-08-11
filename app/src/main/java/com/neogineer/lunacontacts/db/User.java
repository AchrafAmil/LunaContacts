package com.neogineer.lunacontacts.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity
public class User {

    @SerializedName("id")
    @PrimaryKey
    public int id;

    @SerializedName("first_name")
    @ColumnInfo(name = "first_name")
    public String firstName;

    @SerializedName("last_name")
    @ColumnInfo(name = "last_name")
    public String lastName;

    @SerializedName("email")
    public String email;

    @SerializedName("gender")
    public String gender;

    @SerializedName("avatar")
    public String avatar;

    @Ignore
    public User(){}

    public User(int id, String firstName, String lastName, String email, String gender, String avatar) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName +" (id:"+id+")"+ "\n"
                + gender + "\n"
                + email + "\n"
                + "avatar: "+ avatar;
    }

    public String getFullName(){
        return firstName+ " "+ lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}