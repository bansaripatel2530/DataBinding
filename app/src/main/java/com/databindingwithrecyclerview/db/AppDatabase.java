package com.databindingwithrecyclerview.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;




//@Database(entities = {RegisterSource.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract AppDao appDeo();

}
