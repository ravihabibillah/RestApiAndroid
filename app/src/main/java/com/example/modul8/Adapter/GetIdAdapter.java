package com.example.modul8.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.modul8.Model.getId.DataId;
import com.example.modul8.R;

import java.util.List;

/*
Nama :  Nadia Stef...
NIM  :  1231700...
*/

//Adapter Untuk Melakukan set Data yang didapat melalui API ke RecycleView
public class GetIdAdapter extends RecyclerView.Adapter<GetIdAdapter.Holder> {

    private Context context;
    private List<DataId> listId;
    private OnAdapterClickListener listener;

    public GetIdAdapter(Context context, List<DataId> listId, OnAdapterClickListener listener) {
        this.context = context;
        this.listId = listId;
        this.listener = listener;
    }

    @NonNull
    @Override
    public GetIdAdapter.Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.layout_items, viewGroup, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GetIdAdapter.Holder holder, final int i) {
        holder.bind(i, listener);
    }

    @Override
    public int getItemCount() {
        return listId.size();
    }

    public interface OnAdapterClickListener {
        void onClicked(String id, String name, String description, String key);
    }
    /*
Nama :  Ika Husni...
NIM  :  1231700...
*/
    public class Holder extends RecyclerView.ViewHolder {

        private TextView tvName, tvDescription, tvId;
        private Button btnEdit, btnDelete;

        public Holder(View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tv_id);
            tvName = itemView.findViewById(R.id.tv_name);
            tvDescription = itemView.findViewById(R.id.tv_description);
            btnDelete = itemView.findViewById(R.id.btn_delete);
            btnEdit = itemView.findViewById(R.id.btn_edit);

        }

        public void bind(final int position, final OnAdapterClickListener listener) {
            tvId.setText(String.valueOf(listId.get(position).getId()));
            tvName.setText(String.valueOf(listId.get(position).getName()));
            tvDescription.setText(String.valueOf(listId.get(position).getDescription()));
            btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClicked(String.valueOf(listId.get(position).getId()),
                            listId.get(position).getName(),
                            listId.get(position).getDescription(),
                            "edit");

                }
            });

            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClicked(String.valueOf(listId.get(position).getId()),
                            listId.get(position).getName(),
                            listId.get(position).getDescription(),
                            "delete");
                }
            });
        }
    }
}
