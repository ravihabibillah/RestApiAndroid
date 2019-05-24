package com.example.modul8;

import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.modul8.Adapter.ItemAdapter;
import com.example.modul8.Model.get.DataItem;
import com.example.modul8.Model.get.GetResponse;
import com.example.modul8.Presenter.MainPresenter;
import com.example.modul8.Presenter.MainView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView, ItemAdapter.OnAdapterClickListener {

    private RecyclerView recyclerView;
    private ItemAdapter itemAdapter;
    private MainPresenter presenter;
    private List<DataItem> list;
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = new ArrayList<>();
        recyclerView = findViewById(R.id.rv_items);
        floatingActionButton = findViewById(R.id.fb_items);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newItemsDialog();
            }
        });

        itemAdapter = new ItemAdapter(this, list, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(itemAdapter);
        presenter = new MainPresenter(this);
        presenter.getAllItems();
    }

    private void newItemsDialog() {
        LayoutInflater factory = LayoutInflater.from(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Tambah Barang");

        final View textEntryView = factory.inflate(R.layout.text_entry, null);
        final EditText name = textEntryView.findViewById(R.id.edt_name);
        final EditText description = textEntryView.findViewById(R.id.edt_description);

        name.setHint("Nama Barang");
        description.setHint("Deskripsi");
        name.setText("", TextView.BufferType.EDITABLE);
        description.setText("", TextView.BufferType.EDITABLE);

        builder.setView(textEntryView);
        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (!name.getText().toString().equals("")) {
                    presenter.createItems(name.getText().toString(), description.getText().toString());
                } else {
                    Toast.makeText(MainActivity.this, "Masukkan Nama Barang", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("Batal", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    private void deleteDialog(final String id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Apakah kita Benar Akan Menghapus Item ini?");
        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                presenter.deleteItems(id);
            }
        });

        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    private void editDialog(final String id, final String name, final String description) {
        LayoutInflater factory = LayoutInflater.from(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Tambah Barang");

        final View textEntryView = factory.inflate(R.layout.text_entry, null);

        final EditText edtName = textEntryView.findViewById(R.id.edt_name);
        final EditText edtDescription = textEntryView.findViewById(R.id.edt_description);

        edtName.setText(name, TextView.BufferType.EDITABLE);
        edtDescription.setText(description, TextView.BufferType.EDITABLE);

        builder.setView(textEntryView);
        builder.setTitle("Update Barang");
        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                presenter.updateItems(id,edtName.getText().toString(),
                        edtDescription.getText().toString());
            }
        });

        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    protected void onResume() {
        super.onResume();
        presenter.getAllItems();
    }

    @Override
    public void onClicked(String id, String name, String description, String key) {
        if (key.equalsIgnoreCase("edit")) {
            editDialog(id, name, description);
        } else {
            deleteDialog(id);
        }
    }

    @Override
    public void getSucces(GetResponse list) {
        this.list.clear();
        this.list.addAll(list.getData());
        itemAdapter.notifyDataSetChanged();
    }

    @Override
    public void setToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        presenter.getAllItems();
    }

    @Override
    public void onError(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onFailure(String failureMessage) {
        Toast.makeText(this, failureMessage, Toast.LENGTH_LONG).show();
    }
}
