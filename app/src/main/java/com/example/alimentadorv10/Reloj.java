package com.example.alimentadorv10;

import android.app.TimePickerDialog;
import android.content.Context;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

public class Reloj {
    private static final String CERO = "0";
    private static final String DOS_PUNTOS = ":";
    private int h, m;
    //Calendario para obtener fecha & hora
    private final Calendar c = Calendar.getInstance();
    private Context context;

    //Variables para obtener la hora hora actual
    final int hora = c.get(Calendar.HOUR_OF_DAY);
    final int minuto = c.get(Calendar.MINUTE);

    public Reloj(Context c){
        this.context = c;
    }

    public void obtenerHora(final EditText et){
        TimePickerDialog recogerHora = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
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
                h = hourOfDay;
                m = minute;
            }
            //Estos valores deben ir en ese orden
            //Al colocar en false se muestra en formato 12 horas y true en formato 24 horas
            //Pero el sistema devuelve la hora en formato 24 horas
        }, hora, minuto, false);

        recogerHora.show();
    }

    public int getHora(){
        return h;
    }
    public int getMinuto(){
        return m;
    }
}
