package com.example.dompet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.dompet.ViewModel.CatatanViewModel;
import com.example.dompet.adapter.RecyclerCatatanListAdapter;
import com.example.dompet.data.Catatan;

import java.util.List;

public class ActivityLogCatatan extends Fragment {

    private CatatanViewModel catatanViewModel;
    private Button mButtonAddCatatan;
    private RecyclerView mRecycleCatatan;
    private RecyclerCatatanListAdapter adapterCatatan;

    private TextView tvCatatanInfo;
    private SharedPreferences mSharedPreferences;
    private int bmr;
    private String date;



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.activity_log_catatan, container, false);
        initiateComponent(mView);
        return mView;
    }
    private void initiateComponent(View mView) {
        mButtonAddCatatan = mView.findViewById(R.id.buttonAddCatatan);
        tvCatatanInfo = mView.findViewById(R.id.tvCatatanInfo);

        mRecycleCatatan = mView.findViewById(R.id.recyclerCatatan);
        mRecycleCatatan.setLayoutManager(new LinearLayoutManager(getActivity()));

        mSharedPreferences = getActivity().getSharedPreferences(ActivityHitung.KEY_SHARED_PREF_NAME, Context.MODE_PRIVATE);
        bmr = (int) mSharedPreferences.getFloat(ActivityHitung.KEY_BMR,0);
        date = mSharedPreferences.getString(ActivityHitung.KEY_DATE,"");

        tvCatatanInfo.setText("Keuangan Anda : "+bmr);
        mButtonAddCatatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentManager = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentManager.replace(R.id.fragment_container,new Activity_AddCatatanFragment()).addToBackStack(null)
                        .commit();
            }
        });


        adapterCatatan = new RecyclerCatatanListAdapter(getActivity());
        mRecycleCatatan.setAdapter(adapterCatatan);
        //Todo 7 Menggunakan View Model untuk Mengeset adapter
        mRecycleCatatan.setLayoutManager(new LinearLayoutManager(getActivity()));
        catatanViewModel = ViewModelProviders.of(getActivity()).get(CatatanViewModel.class);
        catatanViewModel.getCatatanList(date).observe(getActivity(), new Observer<List<Catatan>>() {
            @Override
            public void onChanged(List<Catatan> catatans) {
                adapterCatatan.setCatatanList(catatans);
            }
        });

    }
}

