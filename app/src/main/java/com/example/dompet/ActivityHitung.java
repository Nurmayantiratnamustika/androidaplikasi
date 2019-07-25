package com.example.dompet;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ActivityHitung extends Fragment {

    private EditText edtPengeluaran, edtPendapatan;
    private Spinner spinnerWaktuku;
    private Button buttonHitung;
    public static SharedPreferences mSharedPreferences;
    public static final String KEY_SHARED_PREF_NAME = "Catatan";
    public static final String KEY_BMR = "Catatan_Needed";
    public static final String KEY_DATE = "Date";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mView = inflater.inflate(R.layout.activity_hitung, container, false);
        initiateComponent(mView);
        return mView;
    }
    private void initiateComponent(View mView) {
        edtPendapatan = mView.findViewById(R.id.edtPendapatan);
        edtPengeluaran = mView.findViewById(R.id.edtPengeluaran);
        spinnerWaktuku = mView.findViewById(R.id.spinnerWaktu);
        buttonHitung = mView.findViewById(R.id.buttonHitung);

        mSharedPreferences = getActivity().getSharedPreferences(KEY_SHARED_PREF_NAME, Context.MODE_PRIVATE);


        clickListener(mView);
    }

    private void clickListener(final View mView) {
        buttonHitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validation(edtPendapatan.getText().toString(), edtPengeluaran.getText().toString())) {
                    float hasilHitung = hitungUang(Integer.valueOf(edtPendapatan.getText().toString()),Integer.valueOf(edtPengeluaran.getText().toString()));

                    Spanned message = Html.fromHtml("Jumlah Uang Anda Sekarang ..... " + " <b>" + String.valueOf(hasilHitung) + " </b>");
                    BmrDialog(mView, "Perhitungan Keuangan!", message, hasilHitung);

                } else {
                    AttentionDialog(mView, "Attention!", "Semua Field Harus Diisi");
                }
            }
        });
    }

    private int hitungUang(int pendapatan, int pengeluaran) {
        int balance = 0;

        balance= pendapatan - pengeluaran;
        return balance;
    }

    private void AttentionDialog(View mView, String title, CharSequence message) {
        new AlertDialog.Builder(mView.getContext())
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                })
                .show();
    }
    private void BmrDialog(View mView, String title, CharSequence message, final float bmrResult) {
        new AlertDialog.Builder(mView.getContext())
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
                        mEditor.putFloat(KEY_BMR,bmrResult);

                        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                        mEditor.putString(KEY_DATE ,date);
                        mEditor.apply();

                        FragmentTransaction fragmentManager = getActivity().getSupportFragmentManager().beginTransaction();
                        fragmentManager.replace(R.id.fragment_container,new ActivityLogCatatan())
                                .addToBackStack(null)
                                .commit();
                    }
                })
                .setNegativeButton("Hitung Ulang", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .show();
    }

    private boolean validation(String pendapatan, String pengeluaran) {
        if ((pendapatan.trim().length() == 0) || (pengeluaran.trim().length() == 0)) {
            return false;
        } else return true;
    }


}

