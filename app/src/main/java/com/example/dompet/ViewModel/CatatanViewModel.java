package com.example.dompet.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.dompet.Repository.CatatanRepository;
import com.example.dompet.data.Catatan;

import java.util.List;

public class CatatanViewModel extends AndroidViewModel {

    private CatatanRepository catatanRepository;
    private LiveData<List<Catatan>> catatanList;

    public CatatanViewModel(@NonNull Application application) {
        super(application);

        catatanRepository =  new CatatanRepository(application);
    }

    public LiveData<List<Catatan>> getCatatanList(String date){
        return catatanList = catatanRepository.GetAllCaloriesByDate(date);
    }
    public  void  InsertCatatan(Catatan catatan){
        catatanRepository.InsertCatatan(catatan);
    }
}

