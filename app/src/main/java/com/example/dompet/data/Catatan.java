package com.example.dompet.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "catatan")
public class Catatan {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;

    @ColumnInfo(name = "jenis_catatan")
    private String jenisCatatan;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "date")
    private String date;

    @ColumnInfo(name = "jumlah")
    private String jumlah;

    public Catatan(String jenisCatatan,String name, String date, String jumlah) {
        this.jenisCatatan=jenisCatatan;
        this.name = name;
        this.date = date;
        this.jumlah = jumlah;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJenisCatatan(){
        return jenisCatatan;
    }

    public void setJenisCatatan(String jenisCatatan) {
        this.jenisCatatan = jenisCatatan;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }
}
