package com.example.alimentadorv10;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText almuerzo, cena;
    ImageButton conectar, enviar;
    private MiBT bt;

    private Reloj horaAlmuerzo, horaCena;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        almuerzo = findViewById(R.id.etAlmuerzo);
        cena = findViewById(R.id.etCena);
        conectar = findViewById(R.id.btnBluetooth);
        enviar = findViewById(R.id.btnSend);

        almuerzo.setOnClickListener(this);
        cena.setOnClickListener(this);

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
            case R.id.btnBluetooth:
                bt = new MiBT();
                bt.encender(this);
                break;
            case R.id.btnSend:
                Util.mensaje(this, "falta");
                break;
        }
    }



}
