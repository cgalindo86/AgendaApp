package com.mobile.ingenio.agendaapp.Controladores;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;


import com.mobile.ingenio.agendaapp.MainActivity;
import com.mobile.ingenio.agendaapp.Modelos.Delegado;
import com.mobile.ingenio.agendaapp.Modelos.Departamentos;
import com.mobile.ingenio.agendaapp.Modelos.Eventos;
import com.mobile.ingenio.agendaapp.Modelos.Imagenes;
import com.mobile.ingenio.agendaapp.Modelos.Jefe;
import com.mobile.ingenio.agendaapp.Modelos.Municipios;
import com.mobile.ingenio.agendaapp.Modelos.Usuario;
import com.mobile.ingenio.agendaapp.R;
import com.mobile.ingenio.agendaapp.Servicios.Conexion;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by cgali on 4/07/2017.
 */


public class InicioSesion extends Activity { //implements GoogleApiClient.OnConnectionFailedListener{

    private Button btnSignIn;
    /*private Button btnSignOut;
    private Button btnRevoke;
    private Button btnEmpezar;*/
    private TextView txtNombre;
    private TextView txtEmail;
    private ImageView imagen;

    Boolean ev;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio_sesion);

        Bitmap bitmap;
        bitmap = BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.fondo);
        Imagenes imagenes = new Imagenes();
        ScrollView i = (ScrollView) findViewById(R.id.fondo);
        bitmap = imagenes.redimensionar(bitmap,500,500);
        Drawable d = new BitmapDrawable(getResources(), bitmap);
        i.setBackground(d);

        Bitmap bitmap2;
        bitmap2 = BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.logo);

        ImageView i2 = (ImageView) findViewById(R.id.logo);
        i2.setImageBitmap(imagenes.redimensionar(bitmap2,200,200));

        btnSignIn = (Button)findViewById(R.id.sign_in_button2);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText e = (EditText)findViewById(R.id.dni);
                String id = e.getText().toString();
                EditText e2 = (EditText)findViewById(R.id.codigo);
                String id2 = e2.getText().toString();

                id2 = getMD5(id2);
                //EditText p = (EditText)findViewById(R.id.pass);
                //String pass = p.getText().toString();
                Conexion conexion = new Conexion();
                String ruta = conexion.getUrl(getApplicationContext())+"/wservices.php?accion2=0&usuario="+id+"&password="+id2;
                Log.d("ruta",ruta);
                LeeUsuario lee = new LeeUsuario();
                lee.execute(ruta);

            }
        });


    }

    public class LeeUsuario extends AsyncTask<String,Void,String> {

        ProgressDialog progressDoalog;

        @Override
        protected void onPreExecute() {
            progressDoalog = new ProgressDialog(InicioSesion.this);
            progressDoalog.setMax(100);
            progressDoalog.setMessage("Leyendo....");
            progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDoalog.show();
            // TODO Auto-generated method stub
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            // TODO Auto-generated method stub
            String dni = params[0];
            Boolean ev = DatAlumno(getAlumno(dni));
            return ev+"";
        }

        @Override
        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            progressDoalog.dismiss();
            if (result.equals("true")){
                Conexion conexion = new Conexion();
                String ruta = conexion.getUrl(getApplicationContext())+"/wservices.php?accion2=1";
                LeeUsuario2 lee = new LeeUsuario2();
                lee.execute("1#"+ruta);
                String ruta2 = conexion.getUrl(getApplicationContext())+"/wservices.php?accion2=2";
                LeeUsuario2 lee2 = new LeeUsuario2();
                lee2.execute("2#"+ruta2);
                String ruta3 = conexion.getUrl(getApplicationContext())+"/wservices.php?accion2=3";
                LeeUsuario2 lee3 = new LeeUsuario2();
                lee3.execute("3#"+ruta3);
                String ruta4 = conexion.getUrl(getApplicationContext())+"/wservices.php?accion2=4";
                LeeUsuario2 lee4 = new LeeUsuario2();
                lee4.execute("4#"+ruta4);
                String ruta5 = conexion.getUrl(getApplicationContext())+"/wservices.php?accion2=5";
                LeeUsuario2 lee5 = new LeeUsuario2();
                lee5.execute("5#"+ruta5);
                Toast.makeText(getApplicationContext(), "INICIO CORRECTO", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(InicioSesion.this,MainActivity.class);
                //startActivity(i);
            } else {
                Toast.makeText(getApplicationContext(), "DEBE INICIAR SESIÓN CON UN RIA VÁLIDO", Toast.LENGTH_SHORT).show();
            }
        }

    }

    public class LeeUsuario2 extends AsyncTask<String,Void,String> {

        ProgressDialog progressDoalog;

        @Override
        protected void onPreExecute() {
            progressDoalog = new ProgressDialog(InicioSesion.this);
            progressDoalog.setMax(100);
            progressDoalog.setMessage("Leyendo....");
            progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDoalog.show();
            // TODO Auto-generated method stub
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            // TODO Auto-generated method stub
            String dni = params[0];
            String p[] = dni.split("#");
            String ev = getAlumno(p[1]);
            return p[0]+"="+ev;
        }

        @Override
        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            progressDoalog.dismiss();
            String p[] = result.split("=");
            Log.d("pcero",p[0]);
            if(p[0].equals("1")){
                Log.d("eventos",p[1]);
                Eventos eventos = new Eventos();
                eventos.setEvento(getApplicationContext(),p[1]);
            } else if(p[0].equals("2")){
                Log.d("departamentos",p[1]);
                Departamentos departamentos = new Departamentos();
                departamentos.setDepartamentos(getApplicationContext(),p[1]);
            } else if(p[0].equals("3")){
                Log.d("municipios",p[1]);
                Municipios municipios = new Municipios();
                municipios.setMunicipios(getApplicationContext(),p[1]);
            } else if(p[0].equals("4")){
                Log.d("jefe",p[1]);
                Jefe jefe = new Jefe();
                jefe.setJefes(getApplicationContext(),p[1]);

            } else if(p[0].equals("5")){
                Log.d("delegado",p[1]);
                Delegado delegado = new Delegado();
                delegado.setDelegados(getApplicationContext(),p[1]);
                Intent i = new Intent(InicioSesion.this, MainActivity.class);
                startActivity(i);
            }
        }

    }

    public String getAlumno(String entrada) {
        URL alumUrl = null;
        //Class<java.net.URL> aClass = java.net.URL.class;
        String url2="";
        try{
            alumUrl = new URL(entrada);
            HttpURLConnection conn = (HttpURLConnection) alumUrl.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            byte[] array = new byte[1000]; // buffer temporal de lectura.
            StringBuffer out = new StringBuffer();
            byte[] b = new byte[4096];
            for (int n; (n = is.read(b)) != -1;) {
                out.append(new String(b, 0, n, "UTF-8"));
            }
            String pot=new String(out.toString().getBytes("UTF-8"));
            url2=pot;
        }catch(IOException ex){
            ex.printStackTrace();
        }
        Log.d("Consulta",url2);
        return url2;
    }


    public Boolean DatAlumno(String datos2){
        Boolean mensaje;
        String datosServ[]=datos2.split("#");

        if(datosServ[0].equals("si")){
            Log.d("Serv1",datosServ[0]);
            Log.d("Serv1",datosServ[1]);
            Log.d("Serv1",datosServ[2]);
            Log.d("Serv1",datosServ[3]);
            Log.d("Serv1",datosServ[4]);


            Usuario al = new Usuario();
            Context context=getApplicationContext();

            al.setCodigo(datosServ[1],context);
            al.setLogin(datosServ[2],context);
            al.setNombre(datosServ[3],context);
            al.setEmail(datosServ[4],context);
            al.setMunicipio(datosServ[5],context);

            mensaje = true;
        } else {
            mensaje = false;
        }
        return  mensaje;
    }

    public static String getMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);

            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}