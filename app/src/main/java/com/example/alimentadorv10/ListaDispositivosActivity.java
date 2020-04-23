package com.example.alimentadorv10;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.hp.bluetoothjhr.BluetoothJhr;

public class ListaDispositivosActivity extends AppCompatActivity {

    ListView listaDispositivos;
    ImageButton conectar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_dispositivos);
        listaDispositivos = findViewById(R.id.lvDispositivos);
        conectar = findViewById(R.id.btnBluetooth);

    }

    public void conectar(View v){
        final BluetoothJhr bluetoothJhr = new BluetoothJhr(this, listaDispositivos);
        bluetoothJhr.EncenderBluetooth();
        listaDispositivos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                bluetoothJhr.Disp_Seleccionado(view, position, MainActivity.class);
            }
        });
    }



}
