package com.mobile.ingenio.agendaapp.Modelos;

import android.content.Context;
import android.util.Log;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Delegado {

    public Delegado() {
    }

    public String getDelegados(Context context) {
        String nombre2="";
        try {

            FileInputStream fin = context.openFileInput("delegados.txt");
            int size;
            byte[] array = new byte[1000]; // buffer temporal de lectura.
            StringBuffer out = new StringBuffer();
            byte[] b = new byte[4096];
            for (int n; (n = fin.read(b)) != -1;) {
                out.append(new String(b, 0, n, "UTF-8"));
            }
            String pot=new String(out.toString().getBytes("UTF-8"));
            nombre2=pot;
            Log.d("nombre dentro",nombre2);
        } catch (IOException io) {
            nombre2 = "";
        }
        return nombre2;

    }

    public void setDelegados(Context context, String info) {
        try{
            String nombre2=info;
            FileOutputStream fout1 = context.openFileOutput("delegados.txt",Context.MODE_APPEND);
            fout1.write(nombre2.getBytes());
            fout1.close();
        } catch (IOException ioe){}
    }

}
