package com.example.alimentadorv10;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int REQUEST_ENABLE_BT = 1;
    EditText almuerzo, cena;
    ImageButton conectar, enviar;
    private static final String CERO = "0";
    private static final String DOS_PUNTOS = ":";
    BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    //Calendario para obtener fecha & hora
    public final Calendar c = Calendar.getInstance();

    //Variables para obtener la hora hora
    final int hora = c.get(Calendar.HOUR_OF_DAY);
    final int minuto = c.get(Calendar.MINUTE);




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
                obtenerHora(almuerzo);
                break;
            case R.id.etCena:
                obtenerHora(cena);
                break;
            case R.id.btnBluetooth:
                encender();
                break;
            case R.id.btnSend:
                mensaje("falta");
                break;
        }
    }

    private void encender(){

        if (bluetoothAdapter == null) {
            // Device doesn't support Bluetooth
            mensaje("No hay bluetooth");
        }

        if (!bluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }

    }


    private void obtenerHora(final EditText et){
        TimePickerDialog recogerHora = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                //Formateo la hora obtenida: antepone el 0 si son menores de 10
                String horaFormateada =  (hourOfDay < 10)? (CERO + hourOfDay) : String.valueOf(hourOfDay);
                //Formateo el minuto obtenido: antepone el 0 si son menores de 10
                String minutoFormateado = (minute < 10)? (CERO + minute):String.valueOf(minute);
                //Obtengo el valor a.m. o p.m., dependiendo de la selección del usuario
                String AM_PM;
                if(hourOfDay < 12) {
                    AM_PM = "a.m.";
                } else {
                    AM_PM = "p.m.";
                }
                //Muestro la hora con el formato deseado
                et.setText(horaFormateada + DOS_PUNTOS + minutoFormateado + " " + AM_PM);
            }
            //Estos valores deben ir en ese orden
            //Al colocar en false se muestra en formato 12 horas y true en formato 24 horas
            //Pero el sistema devuelve la hora en formato 24 horas
        }, hora, minuto, false);

        recogerHora.show();
    }

    public void mensaje(String mensaje){
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }




}
