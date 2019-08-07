package com.mobile.ingenio.agendaapp.Controladores;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.Toast;

//import com.mobile.ingenio.agendaapp;
import com.mobile.ingenio.agendaapp.MainActivity;
import com.mobile.ingenio.agendaapp.Modelos.Delegado;
import com.mobile.ingenio.agendaapp.Modelos.Departamentos;
import com.mobile.ingenio.agendaapp.Modelos.Eventos;
import com.mobile.ingenio.agendaapp.Modelos.Jefe;
import com.mobile.ingenio.agendaapp.Modelos.Municipios;
import com.mobile.ingenio.agendaapp.Modelos.Usuario;
import com.mobile.ingenio.agendaapp.R;
import com.mobile.ingenio.agendaapp.Servicios.Conexion;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Control2 extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private FragmentTabHost tabHost;
    String ntitulo,nfecha,nhoraInicio,nhoraFin,njefe,ndelegado,ndepartamento,nmunicipio,nnombre,ndireccion,ntelefono,nid,nnumpersonas;
    String fechaActual;
    String msillas,nsillas,msonido,nsonido,mtarima,ntarima,mcarpa,ncarpa,mvideobeam,nvideobeam;
    String mpantalla,npantalla,martista,nartista,mavanzada,navanzada,mtransporte,ntrasnporte,mobservaciones;

    public final Calendar c = Calendar.getInstance();
    int anio = c.get(Calendar.YEAR);
    int mes = c.get(Calendar.MONTH);
    int dia = c.get(Calendar.DAY_OF_MONTH);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.socios);


        tabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        tabHost.setup(this,
                getSupportFragmentManager(), android.R.id.tabcontent);
        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("Registrar agenda"),
                Tab3.class, null);
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("Logistica"),
                Tab4.class, null);



        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {

            }
        });

        if(mes<10){ fechaActual = ""+anio+"-0"+mes;} else { fechaActual = ""+anio+"-"+mes; }
        if(dia<10){ fechaActual = fechaActual+"-0"+dia;} else { fechaActual = fechaActual+"-"+dia;}



    }

    // Método de la interfaz Actualizar


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_control, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.guardar) {
            Eventos eventos1 = new Eventos();
            String al[] = eventos1.getEventoAlmacenadoX(getApplicationContext()).split("#");
            if(al.length>=12){
                if(al[0]!=null){ nid = al[0]; } else { nid=""; }
                if(al[1]!=null){ ntitulo = al[1]; } else { ntitulo=""; }
                if(al[2]!=null){ nfecha = al[2]; } else { nfecha=""+fechaActual; }
                if(al[3]!=null){ nhoraInicio = al[3]; } else { nhoraInicio="00:00:00"; }
                if(al[4]!=null){ nhoraFin = al[4]; } else { nhoraFin="00:00:00"; }
                if(al[5]!=null){ njefe = al[5]; } else { njefe="0"; }
                if(al[6]!=null){ ndelegado = al[6]; } else { ndelegado="0"; }
                if(al[7]!=null){ ndepartamento = al[7]; } else { ndepartamento=""; }
                if(al[8]!=null){ nmunicipio = al[8]; } else { nmunicipio=""; }
                if(al[9]!=null){ nnombre = al[9]; } else { nnombre=""; }
                if(al[10]!=null){ ndireccion = al[10]; } else { ndireccion=""; }
                if(al[11]!=null){ ntelefono = al[11]; } else { ntelefono=""; }
                if(al[12]!=null){ nnumpersonas = al[12]; } else { nnumpersonas="0"; }
            } else {
                if(ntitulo.equals("")){ ntitulo="sin info"; }
                if(nfecha.equals("")){ nfecha=""+fechaActual; }
                if(nhoraInicio.equals("")){ nhoraInicio="00:00:00"; }
                if(nhoraFin.equals("")){ nhoraFin="00:00:00"; }
                if(njefe.equals("")){ njefe="0"; }
                if(ndelegado.equals("")){ ndelegado="0"; }
                if(ndepartamento.equals("")){ ndepartamento="sin info"; }
                if(nmunicipio.equals("")){ nmunicipio="sin info"; }
                if(nnombre.equals("")){ nnombre="sin info"; }
                if(ndireccion.equals("")){ ndireccion="sin info"; }
                if(ntelefono.equals("")){ ntelefono="sin info"; }
                if(nnumpersonas.equals("")){ nnumpersonas="0"; }
            }


            Eventos eventos2 = new Eventos();
            String al2[] = eventos2.getEventoAlmacenadoX2(getApplicationContext()).split("#");
            /*
            * String msillas,nsillas,msonido,nsonido,mtarima,ntarima,mcarpa,ncarpa,mvideobeam,nvideobeam;
    String mpantalla,npantalla,martista,nartista,mavanzada,navanzada,mtransporte,ntrasnporte,mobservaciones;
            * */
            if(al2.length>=18) {
                msillas = al2[0];
                if (msillas.equals("")) {
                    msillas = "0";
                }
                nsillas = al2[1];
                if (nsillas.equals("")) {
                    nsillas = "0";
                }
                msonido = al2[2];
                if (msonido.equals("")) {
                    msonido = "0";
                }
                nsonido = al2[3];
                if (nsonido.equals("")) {
                    nsonido = "0";
                }
                mtarima = al2[4];
                if (mtarima.equals("")) {
                    mtarima = "0";
                }
                ntarima = al2[5];
                if (ntarima.equals("")) {
                    ntarima = "0";
                }
                mcarpa = al2[6];
                if (mcarpa.equals("")) {
                    mcarpa = "0";
                }
                ncarpa = al2[7];
                if (ncarpa.equals("")) {
                    ncarpa = "0";
                }
                mvideobeam = al2[8];
                if (mvideobeam.equals("")) {
                    mvideobeam = "0";
                }
                nvideobeam = al2[9];
                if (nvideobeam.equals("")) {
                    nvideobeam = "0";
                }
                mpantalla = al2[10];
                if (mpantalla.equals("")) {
                    mpantalla = "0";
                }
                npantalla = al2[11];
                if (npantalla.equals("")) {
                    npantalla = "0";
                }
                martista = al2[12];
                if (martista.equals("")) {
                    martista = "0";
                }
                nartista = al2[13];
                if (nartista.equals("")) {
                    nartista = "0";
                }
                mavanzada = al2[14];
                if (mavanzada.equals("")) {
                    mavanzada = "0";
                }
                navanzada = al2[15];
                if (navanzada.equals("")) {
                    navanzada = "0";
                }
                mtransporte = al2[16];
                if (mtransporte.equals("")) {
                    mtransporte = "0";
                }
                ntrasnporte = al2[17];
                if (ntrasnporte.equals("")) {
                    ntrasnporte = "0";
                }
                mobservaciones = al2[18];
                if (mobservaciones.equals("")) {
                    mobservaciones = "0";
                }
            } else {
                msillas = "0";
                nsillas = "0";
                msonido = "0";
                nsonido = "0";
                mtarima = "0";
                ntarima = "0";
                mcarpa = "0";
                ncarpa = "0";
                mvideobeam = "0";
                nvideobeam = "0";
                mpantalla = "0";
                npantalla = "0";
                martista = "0";
                nartista = "0";
                mavanzada = "0";
                navanzada = "0";
                mtransporte = "0";
                ntrasnporte = "0";
                mobservaciones = "0";

            }

            ConnectivityManager connMgr = (ConnectivityManager)
                    getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) {
                if(!njefe.equals("0") && !ndelegado.equals("0")){
                    Conexion conexion = new Conexion();
                    String ruta = conexion.getUrl(getApplicationContext())+"/wservices.php?accion=1";
                    EnvioData envioData = new EnvioData();
                    envioData.execute(ruta);
                } else {
                    Toast.makeText(getApplicationContext(),"INDIQUE JEFE Y DELEGADO",Toast.LENGTH_LONG).show();
                }

            } else {

            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class EnvioData extends AsyncTask<String, Integer, String> {

        ProgressDialog progressDoalog;

        @Override
        protected void onPreExecute() {
            progressDoalog = new ProgressDialog(Control2.this);
            progressDoalog.setMax(100);
            progressDoalog.setMessage("Enviando datos al servidor....");
            progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDoalog.show();
            // TODO Auto-generated method stub
            super.onPreExecute();
        }

        protected String doInBackground(String... params) {
            BufferedReader in = null;

            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(params[0]);

            try {
                //String ntitulo,nfecha,nhoraInicio,nhoraFin,njefe,ndelegado,
                // ndepartamento,nmunicipio,nnombre,ndireccion,ntelefono;
                //
                Usuario usuario = new Usuario();
                String minombre = usuario.getNombre(getApplicationContext());
                String micod = usuario.getCodigo(getApplicationContext());
                List<BasicNameValuePair> postValues = new ArrayList<>(2);
                postValues.add(new BasicNameValuePair("accion", "1"));
                postValues.add(new BasicNameValuePair("idCambio", nid));
                postValues.add(new BasicNameValuePair("titulo", ntitulo));
                postValues.add(new BasicNameValuePair("fecha", nfecha));
                postValues.add(new BasicNameValuePair("horaI", nhoraInicio));
                postValues.add(new BasicNameValuePair("horaF", nhoraFin));
                postValues.add(new BasicNameValuePair("jefe", njefe));
                postValues.add(new BasicNameValuePair("delegado", ndelegado));
                postValues.add(new BasicNameValuePair("departamento", ndepartamento));
                postValues.add(new BasicNameValuePair("municipio", nmunicipio));
                postValues.add(new BasicNameValuePair("nombre", nnombre));
                postValues.add(new BasicNameValuePair("direccion", ndireccion));
                postValues.add(new BasicNameValuePair("telefono", ntelefono));
                postValues.add(new BasicNameValuePair("registrador", minombre));
                postValues.add(new BasicNameValuePair("registradorCod", micod));
                postValues.add(new BasicNameValuePair("fechaRegistro", fechaActual));
                postValues.add(new BasicNameValuePair("numpersonas", nnumpersonas));
                postValues.add(new BasicNameValuePair("msillas", msillas));
                postValues.add(new BasicNameValuePair("nsillas", nsillas));
                postValues.add(new BasicNameValuePair("msonido", nsonido));
                postValues.add(new BasicNameValuePair("nsonido", nsonido));
                postValues.add(new BasicNameValuePair("mtarima", mtarima));
                postValues.add(new BasicNameValuePair("ntarima", ntarima));
                postValues.add(new BasicNameValuePair("mcarpa", mcarpa));
                postValues.add(new BasicNameValuePair("ncarpa", ncarpa));
                postValues.add(new BasicNameValuePair("mvideobeam", mvideobeam));
                postValues.add(new BasicNameValuePair("nvideobeam", nvideobeam));
                postValues.add(new BasicNameValuePair("mpantalla", mpantalla));
                postValues.add(new BasicNameValuePair("npantalla", npantalla));
                postValues.add(new BasicNameValuePair("martista", martista));
                postValues.add(new BasicNameValuePair("nartista", nartista));
                postValues.add(new BasicNameValuePair("mavanzada", mavanzada));
                postValues.add(new BasicNameValuePair("navanzada", navanzada));
                postValues.add(new BasicNameValuePair("mtransporte", mtransporte));
                postValues.add(new BasicNameValuePair("ntransporte", ntrasnporte));
                postValues.add(new BasicNameValuePair("mobservaciones", mobservaciones));
                //httppost.setEntity(new UrlEncodedFormEntity(postValues));
                httppost.setEntity(new UrlEncodedFormEntity(postValues, "UTF-8"));
                //Hace la petición
                HttpResponse response = httpClient.execute(httppost);
                Log.d("eee1", response.getStatusLine().toString());

                //Obtengo el contenido de la respuesta en formato InputStream Buffer y la paso a formato String
                in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                StringBuffer sb = new StringBuffer("");
                String line = "";
                String NL = System.getProperty("line.separator");
                while ((line = in.readLine()) != null) {
                    sb.append(line + NL);
                }
                in.close();

                return sb.toString();

            } catch (Exception e) {
                return "Exception happened: " + e.getMessage();
            } finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        protected void onProgressUpdate(Integer... progress) {
            //Se obtiene el progreso de la peticion
            Log.w("eee2","Indicador de progreso " + progress[0].toString());
        }

        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Log.d("result",result);
            progressDoalog.dismiss();
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
        }
    }

    public class LeeUsuario2 extends AsyncTask<String,Void,String> {

        ProgressDialog progressDoalog;

        @Override
        protected void onPreExecute() {
            progressDoalog = new ProgressDialog(Control2.this);
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
                Intent i = new Intent(Control2.this, MainActivity.class);
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

}
