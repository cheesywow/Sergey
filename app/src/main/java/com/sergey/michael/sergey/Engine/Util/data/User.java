package com.sergey.michael.sergey.Engine.Util.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity (tableName = "user")
public class User {
    @PrimaryKey
    private int uid;

    @ColumnInfo(name = "first_name")
    private String firstName;

    @ColumnInfo(name = "last_name")
    private String lastName;

    public void setUid(int uid){
        this.uid = uid;
    }

    public int getUid(){
        return this.uid;
    }

    public void setFirstName(String firstname){
        this.firstName = firstname;
    }

    public String getFirstName(){
        return this.firstName;
    }

    public void setLastName(String lastname){
        this.lastName = lastname;
    }

    public String getLastName(){
        return this.lastName;
    }

}