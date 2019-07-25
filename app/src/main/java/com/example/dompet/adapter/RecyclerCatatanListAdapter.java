package com.example.dompet.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dompet.R;
import com.example.dompet.data.Catatan;

import java.util.Date;
import java.util.List;

public class RecyclerCatatanListAdapter extends RecyclerView.Adapter<RecyclerCatatanListAdapter.MyViewHolder> {
    private final LayoutInflater mInflater;
    private List<Catatan> mcatatanList;

    public RecyclerCatatanListAdapter(Context context){
        mInflater = LayoutInflater.from(context);
    }

    public void setCatatanList(List<Catatan> catatanList) {
        mcatatanList = catatanList;

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View catatanItemView =mInflater.inflate(R.layout.item_catatan,parent,false);

        return  new MyViewHolder(catatanItemView);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Catatan currentCatatan =  mcatatanList.get(position);
        holder.tvJenisCatatan.setText(currentCatatan.getJenisCatatan());
        holder.tvNama.setText(currentCatatan.getName());
        holder.tvJumlah.setText(currentCatatan.getJumlah());
    }


    @Override
    public int getItemCount() {
        return mcatatanList==null? 0:mcatatanList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvJenisCatatan,tvNama,tvJumlah;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvJenisCatatan=itemView.findViewById(R.id.tvJenisCatatan);
            tvNama = itemView.findViewById(R.id.txv_name);
            tvJumlah= itemView.findViewById(R.id.txv_jumlah);
        }
    }
}
