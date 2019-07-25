package com.example.dompet.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;


@Dao
public interface CatatanDao {
    @Insert void addnew(Catatan catatan);

    @Query("Select * from catatan where date = :date")
    LiveData<List<Catatan>> GetAllCatatan(String date);
}
