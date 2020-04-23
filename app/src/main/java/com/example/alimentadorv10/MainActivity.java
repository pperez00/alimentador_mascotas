package com.example.alimentadorv10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.hp.bluetoothjhr.BluetoothJhr;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    EditText almuerzo, cena;
    ImageButton enviar;
    private Reloj horaAlmuerzo, horaCena;
    BluetoothJhr bt;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        almuerzo = findViewById(R.id.etAlmuerzo);
        cena = findViewById(R.id.etCena);
        enviar = findViewById(R.id.btnSend);
        bt = new BluetoothJhr(ListaDispositivosActivity.class, this);

        almuerzo.setOnClickListener(this);
        cena.setOnClickListener(this);

        SharedPreferences preferences = getSharedPreferences("datos", Context.MODE_PRIVATE);
        almuerzo.setText(preferences.getString("almuerzo", ""));
        cena.setText(preferences.getString("cena", ""));

    }

    @Override
    public void onResume() {
        super.onResume();
        bt.ConectaBluetooth();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.etAlmuerzo:
                horaAlmuerzo = new Reloj(this);
                horaAlmuerzo.obtenerHora(almuerzo);
                break;
            case R.id.etCena:
                horaCena = new Reloj(this);
                horaCena.obtenerHora(cena);
                break;
            case R.id.btnSend:
                String mAlmuerzo = almuerzo.getText().toString();
                String mCena = cena.getText().toString();
                bt.Tx(mAlmuerzo);
                bt.Tx(mCena);
                break;
        }
    }





    @Override
    public void onSaveInstanceState(Bundle hora) {
        hora.putString("almuerzo", String.valueOf(almuerzo.getText()));
        hora.putString("cena", String.valueOf(cena.getText()));
        super.onSaveInstanceState(hora);
    }

    @Override
    public void onRestoreInstanceState(Bundle hora){
        super.onRestoreInstanceState(hora);
        almuerzo.setText(hora.getString("almuerzo"));
        cena.setText(hora.getString("cena"));
    }

    @Override
    public void onPause() {
        SharedPreferences preferencias = getSharedPreferences("datos", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferencias.edit();
        editor.putString("almuerzo", almuerzo.getText().toString());
        editor.putString("cena", cena.getText().toString());
        editor.commit();
        bt.CierraConexion();
        super.onPause();
    }



}
