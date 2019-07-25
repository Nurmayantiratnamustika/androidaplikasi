package com.example.dompet;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.dompet.ViewModel.CatatanViewModel;
import com.example.dompet.adapter.RecyclerCatatanListAdapter;
import com.example.dompet.data.Catatan;

import java.util.ArrayList;
import java.util.List;

public class Activity_AddCatatanFragment extends Fragment {

    private Spinner spinnerCatatan;
    private EditText edtName;
    private EditText edtJumlah;
    private Button buttonSaveCatatan;
    private SharedPreferences mSharedPreferences;
    private CatatanViewModel catatanViewModel;
    private String date;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mView = inflater.inflate(R.layout.activity__catatan, container, false);
        initiateComponent(mView);
        return mView;
    }


    private void initiateComponent(View mView) {
        spinnerCatatan = mView.findViewById(R.id.spinnerJenis);
        edtName = mView.findViewById(R.id.edt_name);
        edtJumlah = mView.findViewById(R.id.edt_jumlah);
        buttonSaveCatatan = mView.findViewById(R.id.btn_save);

        mSharedPreferences = getActivity().getSharedPreferences(ActivityHitung.KEY_SHARED_PREF_NAME, Context.MODE_PRIVATE);
        date = mSharedPreferences.getString(ActivityHitung.KEY_DATE, "");

        buttonSaveCatatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View mView) {
                String jenisCatatan = spinnerCatatan.getSelectedItem().toString();
                String nama = edtName.getText().toString();
                String jumlah = edtJumlah.getText().toString();
                String tanggal = date;

                if (Validation(nama,jumlah)) {
                    Catatan catatan = new Catatan(jenisCatatan, nama, tanggal, jumlah);
                    //Todo 6 Memanggil method yang ada di viem model untuk menginsert Db
                    catatanViewModel = ViewModelProviders.of(getActivity()).get(CatatanViewModel.class);
                    catatanViewModel.InsertCatatan(catatan);

                    DialogSave(mView, "Tersimpan!!", "Data Berhasil Disimpan. Apakah Anda Akan Memasukkan Data Lagi?");
                } else {
                    new AlertDialog.Builder(mView.getContext())
                            .setTitle("Attention!!")
                            .setMessage("Pastikan Semua Data Terisi")
                            .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                }
                            }).show();
                }

            }
        });
    }


    private boolean Validation(String nama,String jumlah) {
        if ((nama.trim().length() > 0) && (jumlah.trim().length() > 0)) {
            return true;
        } else return false;
    }
    private void DialogSave(View mView, String title, String message) {
        new AlertDialog.Builder(mView.getContext())
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("Tambah Catatan", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        spinnerCatatan.setSelection(0);
                        edtName.setText("");
                        edtJumlah.setText("");
                    }
                }).setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                FragmentTransaction fragmentManager = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentManager.replace(R.id.fragment_container, new ActivityLogCatatan())
                        .addToBackStack(null)
                        .commit();
            }
        })
                .show();
    }
}
