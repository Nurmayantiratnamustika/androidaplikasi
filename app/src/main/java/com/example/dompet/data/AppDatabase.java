package com.example.dompet.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

    @Database(entities = {Catatan.class}, version = 1, exportSchema = false)
    public abstract class AppDatabase extends RoomDatabase {
        private static AppDatabase Instance;
        private static final String DATABASE_NAME = "CatatanDatabase";

        public abstract CatatanDao catatanDao();

        public static AppDatabase GetDatabase(Context context) {
            if (Instance == null) {
                synchronized (AppDatabase.class) {
                    if (Instance == null) {
                        Instance = Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME).build();
                    }
                }
            }
            return Instance;
        }
    }
