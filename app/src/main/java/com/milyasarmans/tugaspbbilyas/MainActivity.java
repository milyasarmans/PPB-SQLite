package com.milyasarmans.tugaspbbilyas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText xnim;
    EditText xnama;
    EditText xumur;
    EditText xipk;
    EditText xalamat;
    Button tblTambah;
    Button tblTampil;
    Button tblHapus;
    Button tblUbah;
    DatabaseHelper BantuDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BantuDb = new DatabaseHelper(this);
        xnim = (EditText) findViewById(R.id.xnim);
        xnama = (EditText) findViewById(R.id.xnama);
        xumur = (EditText) findViewById(R.id.xumur);
        xipk = (EditText) findViewById(R.id.xipk);
        xalamat = (EditText) findViewById(R.id.xalamat);
        tblTambah = (Button) findViewById(R.id.tblTambah);
        tblHapus = (Button) findViewById(R.id.tblHapus);
        tblUbah = (Button) findViewById(R.id.tblUbah);
        tblTampil = (Button) findViewById(R.id.tblTampil);
        viewAll();

        tblTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean IsInserted = BantuDb.insertData(xnim.getText().toString(), xnama.getText().toString(), xumur.getText().toString(), xipk.getText().toString(), xalamat.getText().toString());

                if (IsInserted == true) {
                    Toast.makeText(MainActivity.this, "Data Tersimpan", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Gagal Menyimpan Data", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tblHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int IsDeleted = BantuDb.deleteData(xnim.getText().toString());

                if (IsDeleted == 1) {
                    Toast.makeText(MainActivity.this, "Data Terhapus", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Gagal Menghapus Data", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tblUbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Boolean IsUpdated = BantuDb.ubahData(xnim.getText().toString(), xnama.getText().toString(), xumur.getText().toString(), xipk.getText().toString(), xalamat.getText().toString());
                if(IsUpdated==true)
                    Toast.makeText(MainActivity.this, "Data Terupdate", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Gagal Update Data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void viewAll(){
        tblTampil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = BantuDb.getAllData();
                if(res.getCount()==0){
                    showMessage("Error", "Tidak Ditemukan");
                    return;
                }
                else{
                    StringBuffer buffer = new StringBuffer();
                    while(res.moveToNext()){
                        buffer.append("NIM        : "+res.getString(0)+"\n");
                        buffer.append("Nama     : "+res.getString(1)+"\n");
                        buffer.append("Umur      : "+res.getString(2)+"\n");
                        buffer.append("IPK          : "+res.getString(3)+"\n");
                        buffer.append("Alamat   : "+res.getString(4)+"\n"+"\n");
                    }
                    showMessage("Mahasiswa  : ",buffer.toString());
                }
            }
        });
    }

    public void showMessage(String judul, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setCancelable(true);
        builder.setTitle(judul);
        builder.setMessage(Message);
        builder.show();
    }
}