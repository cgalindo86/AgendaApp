package com.mobile.ingenio.agendaapp.Servicios;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by cgali on 26/06/2017.
 */

public class Conexion {


    public Conexion(){

    }



    public String getUrl(Context context) {
        String url2="";
        try {

            FileInputStream fin = context.openFileInput("conexion_url.txt");
            int size;
            String url = "";

            while ((size = fin.read()) != -1) {
                url += Character.toString((char) size);
            }
            url2=url;
            //Log.d("url dentro",url);
        } catch (IOException io) {
            io.printStackTrace();
        }
        //Log.d("url2",url2);
        return url2;
    }

    public void setUrl(Context context) {

        try{
            //Log.d("url recibida",url);
            String url2="http://188.166.19.157/servicios";
            FileOutputStream fout1 = context.openFileOutput("conexion_url.txt",MODE_PRIVATE);
            fout1.write(url2.getBytes());
            fout1.close();
        } catch (IOException ioe){}
    }




}
