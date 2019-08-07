package com.mobile.ingenio.agendaapp.Modelos;

import android.content.Context;
import android.util.Log;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Eventos {

    String evento;

    public Eventos() {
    }

    public String getEvento(Context context) {
        String nombre2="";
        try {

            FileInputStream fin = context.openFileInput("evento.txt");
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

    public void setEvento(Context context, String info) {
        try{
            String nombre2=info;
            FileOutputStream fout1 = context.openFileOutput("evento.txt",Context.MODE_PRIVATE);
            fout1.write(nombre2.getBytes());
            fout1.close();
        } catch (IOException ioe){}
    }

    public String getEventoElegido(Context context) {
        String nombre2="";
        try {

            FileInputStream fin = context.openFileInput("evento_master"+".txt");
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

    public void setEventoElegido(Context context, String info) {
        try{
            String nombre2=info;
            FileOutputStream fout1 = context.openFileOutput("evento_master"+".txt",Context.MODE_PRIVATE);
            fout1.write(nombre2.getBytes());
            fout1.close();
        } catch (IOException ioe){}
    }

    public String getEventoAlmacenadoEd(Context context) {
        String nombre2="";
        try {

            FileInputStream fin = context.openFileInput("evento_almacenado_ed.txt");
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

    public void setEventoAlmacenadoEd(Context context, String info) {
        try{
            String nombre2=info;
            FileOutputStream fout1 = context.openFileOutput("evento_almacenado_ed.txt",Context.MODE_APPEND);
            fout1.write(nombre2.getBytes());
            fout1.close();
        } catch (IOException ioe){}
    }

    public void setearEventoAlmacenadoEd(Context context, String info) {
        try{
            String nombre2=info;
            FileOutputStream fout1 = context.openFileOutput("evento_almacenado_ed.txt",Context.MODE_PRIVATE);
            fout1.write(nombre2.getBytes());
            fout1.close();
        } catch (IOException ioe){}
    }

    public String getEventoAlmacenadoIn(Context context) {
        String nombre2="";
        try {

            FileInputStream fin = context.openFileInput("evento_almacenado_in.txt");
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

    public void setEventoAlmacenadoIn(Context context, String info) {
        try{
            String nombre2=info;
            FileOutputStream fout1 = context.openFileOutput("evento_almacenado_in.txt",Context.MODE_APPEND);
            fout1.write(nombre2.getBytes());
            fout1.close();
        } catch (IOException ioe){}
    }

    public void setearEventoAlmacenadoIn(Context context, String info) {
        try{
            String nombre2=info;
            FileOutputStream fout1 = context.openFileOutput("evento_almacenado_in.txt",Context.MODE_PRIVATE);
            fout1.write(nombre2.getBytes());
            fout1.close();
        } catch (IOException ioe){}
    }

    public String getEventoAlmacenadoX(Context context) {
        String nombre2="";
        try {

            FileInputStream fin = context.openFileInput("evento_almacenado_x.txt");
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

    public void setEventoAlmacenadoX(Context context, String info) {
        try{
            String nombre2=info;
            FileOutputStream fout1 = context.openFileOutput("evento_almacenado_x.txt",Context.MODE_PRIVATE);
            fout1.write(nombre2.getBytes());
            fout1.close();
        } catch (IOException ioe){}
    }

    public String getEventoAlmacenadoX2(Context context) {
        String nombre2="";
        try {

            FileInputStream fin = context.openFileInput("evento_almacenado_x2.txt");
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

    public void setEventoAlmacenadoX2(Context context, String info) {
        try{
            String nombre2=info;
            FileOutputStream fout1 = context.openFileOutput("evento_almacenado_x2.txt",Context.MODE_PRIVATE);
            fout1.write(nombre2.getBytes());
            fout1.close();
        } catch (IOException ioe){}
    }


}
