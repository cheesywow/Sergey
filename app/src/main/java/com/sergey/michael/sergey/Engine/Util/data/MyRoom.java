package com.sergey.michael.sergey.Engine.Util.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {User.class}, version = 1)
public abstract class MyRoom extends RoomDatabase{
    public abstract UserDao userDao();
}
