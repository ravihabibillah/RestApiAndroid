package com.example.modul8;

import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.modul8.Adapter.GetIdAdapter;
import com.example.modul8.Adapter.ItemAdapter;
import com.example.modul8.Model.get.DataItem;
import com.example.modul8.Model.get.GetResponse;
import com.example.modul8.Model.getId.DataId;
import com.example.modul8.Model.getId.GetIdResponse;
import com.example.modul8.Model.post.Data;
import com.example.modul8.Presenter.MainPresenter;
import com.example.modul8.Presenter.MainView;

import java.util.ArrayList;
import java.util.List;

/*
Nama :  M.Ravi Habibillah
NIM  :  123170039
*/
public class MainActivity extends AppCompatActivity implements MainView, ItemAdapter.OnAdapterClickListener, GetIdAdapter.OnAdapterClickListener {

    private RecyclerView recyclerView;
    private SearchView searchView;
    private ItemAdapter itemAdapter;
    private GetIdAdapter getIdAdapter;
    private MainPresenter presenter;
    private List<DataItem> list;
    private List<DataId> listId;
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = new ArrayList<>();                                       //  membuat list
        listId = new ArrayList<>();                                     //

        recyclerView = findViewById(R.id.rv_items);
        floatingActionButton = findViewById(R.id.fb_items);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newItemsDialog();                           //memberikan action ke floating button
            }
        });

        itemAdapter = new ItemAdapter(this, list, this);                //membuat itemAdapter Baru dengan memasukkan list item
        recyclerView.setLayoutManager(new LinearLayoutManager(this));           //membuat Layout
        recyclerView.setAdapter(itemAdapter);                                   // men set list yang ada di adapter kedalam recycleview

        presenter = new MainPresenter(this);                    //memanggil MainPresenter
        presenter.getAllItems();                                          //mengambil Semua data

        searchView = findViewById(R.id.search_bar);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {            // Memberikan action pada SearchView
            @Override
            public boolean onQueryTextSubmit(String query) {
                presenter.getByID(query);                       //Action Ketika menekan tombol Submit
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
//                presenter.getByID(newText);                   //Action ketika mengetik
                return false;
            }

        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {          //Action ketika SearchView di close / menekan tombol silang (X)
                recyclerView.setAdapter(itemAdapter);       // men set list yang ada di adapter kedalam recycleview
                presenter.getAllItems();                //menampilkan seluruh data
                return true;
            }
        });
    }

/*
Nama :  Nadia ...
NIM  :  1231700..
*/

    //menampilkan Dialog untuk memasukkan data
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

/*
Nama :  Ika Husni...
NIM  :  1231700...
*/
    // Action dan Pop Up Delete
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

/*
Nama :  Ramanda Walbari...
NIM  :  1231700....
*/
    // Action dan Pop Up Edit
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


/*
Nama :  Ramanda Walbari...
NIM  :  1231700...
*/
    //Action ketika Mobile ke Pause untuk sementara waktu
    protected void onResume() {
        super.onResume();
        presenter.getAllItems();
    }

    // Menset Action untuk Edit dan Delete
    @Override
    public void onClicked(String id, String name, String description, String key) {
        if (key.equalsIgnoreCase("edit")) {
            editDialog(id, name, description);
        } else {
            deleteDialog(id);
        }
    }


/*
Nama :  M.Ravi Habibillah
NIM  :  123170039
*/

    @Override
    public void getSucces(GetResponse list) {
        this.list.clear();
        this.list.addAll(list.getData());       // melakukan set data ke list
        itemAdapter.notifyDataSetChanged();
    }

    @Override
    public void setToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        presenter.getAllItems();
    }

    @Override
    public void onError(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();   //pesan error
    }

    @Override
    public void onFailure(String failureMessage) {
        Toast.makeText(this, failureMessage, Toast.LENGTH_LONG).show(); //pesan gagal
    }




    /*
    Nama :  M.Ravi Habibillah
    NIM  :  123170039
    */

    @Override
    public void getSuccess(GetIdResponse data) {    //jika sukses mendapatkan id

        this.listId.clear();        //membersihkan listId
        this.listId.add(data.getData());    //memasukka data ke listId
        itemAdapter.notifyDataSetChanged();

        getIdAdapter = new GetIdAdapter(this, this.listId, this);    //men set item Adapter dengan memasukkan listId
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(getIdAdapter);          //melakukan set ID ke adapter


    }


}
