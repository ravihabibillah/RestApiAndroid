package com.example.modul8.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.modul8.Model.get.DataItem;
import com.example.modul8.R;

import java.util.List;

/*
Nama :  M.Ravi Habibillah
NIM  :  123170039
*/


//Adapter Untuk Melakukan set Data yang didapat melalui API ke RecycleView

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.Holder> {
    private Context context;
    private List<DataItem> list;
    private OnAdapterClickListener listener;

    public ItemAdapter(Context context, List<DataItem> list, OnAdapterClickListener listener){
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @Override
    public Holder onCreateViewHolder (ViewGroup parent,int position) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_items, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position){
        holder.bind(position, listener);
    }

    @Override
    public int getItemCount(){
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        private TextView tvName, tvDescription, tvId;
        private Button btnEdit, btnDelete;
        public Holder(View itemView){
            super(itemView);
            tvId = itemView.findViewById(R.id.tv_id);
            tvName = itemView.findViewById(R.id.tv_name);
            tvDescription = itemView.findViewById(R.id.tv_description);
            btnDelete = itemView.findViewById(R.id.btn_delete);
            btnEdit = itemView.findViewById(R.id.btn_edit);

        }

        public void bind(final int position, final OnAdapterClickListener listener){
            tvId.setText(String.valueOf(list.get(position).getId()));
            tvName.setText(String.valueOf(list.get(position).getName()));
            tvDescription.setText(String.valueOf(list.get(position).getDescription()));
            btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {listener.onClicked(String.valueOf(list.get(position).getId()),
                        list.get(position).getName(),
                        list.get(position).getDescription(),
                        "edit");

                }
            });

            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClicked(String.valueOf(list.get(position).getId()),
                            list.get(position).getName(),
                            list.get(position).getDescription(),
                            "delete");
                }
            });
        }
    }

    public interface OnAdapterClickListener {
        void onClicked(String id, String name, String description, String key);
    }
}
