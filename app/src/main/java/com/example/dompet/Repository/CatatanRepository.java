package com.example.dompet.Repository;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.dompet.data.AppDatabase;
import com.example.dompet.data.Catatan;
import com.example.dompet.data.CatatanDao;

import java.util.List;

public class CatatanRepository {
    private AppDatabase database;
    private  static  CatatanDao catatanDao;


    public CatatanRepository(Context context) {
//        kita instance database
        database = AppDatabase.GetDatabase(context);
        catatanDao = database.catatanDao();


    }
    public  void InsertCatatan(Catatan catatan){
        new InsertCatatanAsynTask().execute(catatan);
    }

    private class InsertCatatanAsynTask extends AsyncTask<Catatan,Void,Void>{

        @Override
        protected Void doInBackground(Catatan... catatans) {
            catatanDao.addnew(catatans[0]);
            return null;
        }
    }
    public LiveData<List<Catatan>> GetAllCaloriesByDate(String date){
        return catatanDao.GetAllCatatan(date);
    }
}

