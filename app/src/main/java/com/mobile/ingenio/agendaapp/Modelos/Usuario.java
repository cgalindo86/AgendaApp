package com.mobile.ingenio.agendaapp.Modelos;

import android.content.Context;
import android.util.Log;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by cgali on 4/07/2017.
 */

public class Usuario {

    public Usuario(){}

    public String getCodigo(Context context) {
        String nombre2="";
        try {

            FileInputStream fin = context.openFileInput("user_codigo.txt");
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
            io.printStackTrace();
        }
        return nombre2;
    }

    public void setCodigo(String nombre_alum, Context context) {
        try{
            String nombre2=nombre_alum;
            FileOutputStream fout1 = context.openFileOutput("user_codigo.txt",MODE_PRIVATE);
            fout1.write(nombre2.getBytes());
            fout1.close();
        } catch (IOException ioe){}
    }

    public String getLogin(Context context) {
        String nombre2="";
        try {

            FileInputStream fin = context.openFileInput("user_login.txt");
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
            //io.printStackTrace();
            nombre2 = "";
        }
        return nombre2;
    }

    public void setLogin(String nombre_alum, Context context) {
        try{
            String nombre2=nombre_alum;
            FileOutputStream fout1 = context.openFileOutput("user_login.txt",MODE_PRIVATE);
            fout1.write(nombre2.getBytes());
            fout1.close();
        } catch (IOException ioe){

        }
    }

    public String getNombre(Context context) {
        String grado2="";
        try {

            FileInputStream fin = context.openFileInput("user_nombre.txt");
            int size;
            String grado = "";

            while ((size = fin.read()) != -1) {
                grado += Character.toString((char) size);
            }
            grado2=grado;
            Log.d("grado dentro",grado2);
        } catch (IOException io) {
            io.printStackTrace();
        }
        return grado2;
    }

    public void setNombre(String nombre_alum, Context context) {
        try{
            String nombre2=nombre_alum;
            FileOutputStream fout1 = context.openFileOutput("user_nombre.txt",MODE_PRIVATE);
            fout1.write(nombre2.getBytes());
            fout1.close();
        } catch (IOException ioe){}
    }

    public String getEmail(Context context) {
        String grado2="";
        try {

            FileInputStream fin = context.openFileInput("user_email.txt");
            int size;
            String grado = "";

            while ((size = fin.read()) != -1) {
                grado += Character.toString((char) size);
            }
            grado2=grado;
            Log.d("grado dentro",grado2);
        } catch (IOException io) {
            grado2 = "";
        }
        return grado2;
    }

    public void setEmail(String nombre_alum, Context context) {
        try{
            String nombre2=nombre_alum;
            FileOutputStream fout1 = context.openFileOutput("user_email.txt",MODE_PRIVATE);
            fout1.write(nombre2.getBytes());
            fout1.close();
        } catch (IOException ioe){}
    }


    public String getMunicipio(Context context) {
        String nombre2="";
        try {

            FileInputStream fin = context.openFileInput("user_municipio.txt");
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
            io.printStackTrace();
        }
        return nombre2;
    }

    public void setMunicipio(String nombre_alum, Context context) {
        try{
            String nombre2=nombre_alum;
            FileOutputStream fout1 = context.openFileOutput("user_municipio.txt",MODE_PRIVATE);
            fout1.write(nombre2.getBytes());
            fout1.close();
        } catch (IOException ioe){}
    }


}
