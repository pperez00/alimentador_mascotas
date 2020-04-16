package com.example.alimentadorv10;

import android.content.Context;
import android.widget.Toast;

public class Util {

    public static void mensaje(Context context, String mensaje){
        Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show();
    }
}
