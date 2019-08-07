package com.mobile.ingenio.agendaapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;
import com.mobile.ingenio.agendaapp.Controladores.AdapterLista;
import com.mobile.ingenio.agendaapp.Controladores.Control;
import com.mobile.ingenio.agendaapp.Controladores.Control2;
import com.mobile.ingenio.agendaapp.Controladores.Edicion;
import com.mobile.ingenio.agendaapp.Controladores.Evento;
import com.mobile.ingenio.agendaapp.Controladores.InicioSesion;
import com.mobile.ingenio.agendaapp.Controladores.Insercion;
import com.mobile.ingenio.agendaapp.Modelos.Delegado;
import com.mobile.ingenio.agendaapp.Modelos.Departamentos;
import com.mobile.ingenio.agendaapp.Modelos.Eventos;
import com.mobile.ingenio.agendaapp.Modelos.Jefe;
import com.mobile.ingenio.agendaapp.Modelos.Municipios;
import com.mobile.ingenio.agendaapp.Modelos.Usuario;
import com.mobile.ingenio.agendaapp.Servicios.Conexion;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    CompactCalendarView compactCalendarView;
    TextView t,nombre;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM - yyyy", Locale.getDefault());
    private SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
    private SimpleDateFormat dateFormat3 = new SimpleDateFormat("MM", Locale.getDefault());
    long epoch3;
    String anio="",mes="",dia="",nmensaje,titulo;
    final ArrayList<Evento> arrayList = new ArrayList<Evento>();
    ImageView addi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Conexion conexion = new Conexion();
        if(conexion.getUrl(getApplicationContext()).equals("")){ conexion.setUrl(getApplicationContext());}

        Usuario usuario = new Usuario();

        if(usuario.getCodigo(getApplicationContext()).equals("")) {
            Intent i2 = new Intent(MainActivity.this, InicioSesion.class);
            startActivity(i2);
        } else {
            nombre = (TextView) findViewById(R.id.nombre);
            nombre.setText(usuario.getNombre(getApplicationContext()));
            String micolor = Colores(Integer.parseInt(usuario.getCodigo(getApplicationContext())));
            nombre.setTextColor(Color.parseColor(micolor));
            String fechaActual = ""+anio+"-"+mes+"-"+dia;
            Lista2(BuscaEvento(fechaActual));
        }
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        t = (TextView) findViewById(R.id.textoMes);
        compactCalendarView = (CompactCalendarView) findViewById(R.id.calendar1);
        compactCalendarView.setUseThreeLetterAbbreviation(true);
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM - yyyy", Locale.getDefault());
        //mes = dateFormat3.format(date.getTime());
        dia = Dia(dateFormat2.format(date.getTime()));
        Log.d("dia",dia);
        mes = Mes(dateFormat2.format(date.getTime()));
        Log.d("mes",mes);
        anio = Anio(dateFormat2.format(date.getTime()));
        Log.d("anio",anio);
        t.setText((sdf.format(date.getTime())).toUpperCase());



        compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                dia = Dia(dateFormat2.format(dateClicked));
                Log.d("dia",dia);
                mes = Mes(dateFormat2.format(dateClicked));
                Log.d("mes",mes);
                anio = Anio(dateFormat2.format(dateClicked));
                Log.d("anio",anio);
                String nfecha = anio+"-"+mes+"-"+dia;
                Log.d("v",BuscaEvento(nfecha));
                Lista(BuscaEvento(nfecha));
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                dia = Dia(dateFormat2.format(firstDayOfNewMonth));
                Log.d("dia",dia);
                mes = Mes(dateFormat2.format(firstDayOfNewMonth));
                Log.d("mes",mes);
                anio = Anio(dateFormat2.format(firstDayOfNewMonth));
                Log.d("anio",anio);
                t.setText((dateFormat.format(firstDayOfNewMonth)).toUpperCase());
            }
        });

        Eventos eventos = new Eventos();
        String mieven = eventos.getEvento(getApplicationContext());
        Eventos(mieven);

        addi = (ImageView) findViewById(R.id.adicionar);
        addi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Control2.class);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.sincronizar) {
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private String Dia(String fecha){
        String ff[] = fecha.split("-");
        dia = ff[0];
        return dia;
    }

    private String Mes(String fecha){
        String ff[] = fecha.split("-");
        mes = ff[1];
        return mes;
    }

    private String Anio(String fecha){
        String ff[] = fecha.split("-");
        anio = ff[2];
        return anio;
    }

    public String Colores(int i2){
        String micorazon="";
        int i = i2%8;
        if(i==3){
            micorazon = "#00ff00";
        } else
        if(i==4){
            micorazon = "#8080ff";//E45D50
        }
        else
        if(i==9){
            micorazon = "#8080ff";//9E4C6E
        } else
        if(i==10){
            micorazon = "#008040";//EE9D26
        } else
        if(i==11){
            micorazon = "#ff80ff";//4CCEDE
        } else
        if(i==12){
            micorazon = "#ff0000";//AA60BF
        } else
        if(i==13){
            micorazon = "#800000";//717ACF
        } else
        if(i==15){
            micorazon = "#000000";
        }else
        if(i==16){
            micorazon = "#800040";//AA60BF
        } else
        if(i==17){
            micorazon = "#808000";//717ACF
        } else
        if(i==22){
            micorazon = "#ff8000";
        } else
        if(i==24){
            micorazon = "#ff8000";
        } else {
            micorazon = "#2B8BCA";
        }

        return micorazon;
    }


    public String Colores2(int i2){
        String micorazon="";
        int i = i2%8;
        if(i==1){
            micorazon = "#A7D4EE";
        } else
        if(i==2){
            micorazon = "#E45D50";//E45D50
        }
        else
        if(i==3){
            micorazon = "#9E4C6E";//9E4C6E
        } else
        if(i==4){
            micorazon = "#EE9D26";//EE9D26
        } else
        if(i==5){
            micorazon = "#4CCEDE";//4CCEDE
        } else
        if(i==6){
            micorazon = "#AA60BF";//AA60BF
        } else
        if(i==7){
            micorazon = "#717ACF";//717ACF
        } else
        if(i==0){
            micorazon = "#F3D01C";
        }

        return micorazon;
    }

    void Eventos(String eventos){
        //String ev[] = {"12/10/2018 20:00:00","12/11/2018 20:00:00","12/12/2018 20:00:00","12/14/2018 20:00:00"};

        String valu[] = eventos.split("%");


        int val = valu.length;
        for (int i = 0; i < val; i++) {
            String matriz[] = valu[i].split("#");
            //int val2 = matriz.length;
            if(valu[i].contains("#")) {
                //String col = Colores(i);
                try {
                    epoch3 = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").parse(matriz[11]).getTime();

                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Event event = new Event(Color.parseColor(Colores2(i)), epoch3, "Evento de prueba");
                compactCalendarView.addEvent(event);

            }
        }
    }

    private String BuscaEvento(String nfecha){
        String nuevo="";
        Eventos eventos = new Eventos();
        String d[] = eventos.getEvento(getApplicationContext()).split("%");
        int nd = d.length;

        for(int i=0; i<nd; i++){
            String e[] = d[i].split("#");
            if(nfecha.equals(e[1])){
                nuevo = nuevo+d[i]+"%";
                Log.d("nuevo",nuevo);
            }
        }
        return nuevo;
    }

    private void Lista(String mensaje){
        nmensaje = mensaje;
        final ListView lista = (ListView) findViewById(R.id.mi_lista3);
        final ArrayList<Evento> arrayList = new ArrayList<Evento>();

        Eventos eventos = new Eventos();
        String d[] = mensaje.split("%");
        int nd = d.length;

        for(int i=0; i<nd; i++){
            String matriz[] = d[i].split("#");
            if(matriz.length>13){
                String elcolor = Colores(Integer.parseInt(matriz[7]));
                int ucolor = Color.parseColor(elcolor);
                String municipio = Municipio(matriz[6]);
                String departamento = Departamento(matriz[6]);
                String jefe = Usuarios(matriz[12]);
                String delegado = Usuarios(matriz[13]);
                Evento evento = new Evento(matriz[1], matriz[2], matriz[3], matriz[4], matriz[5],
                        departamento, municipio, matriz[7],  matriz[8], matriz[9], matriz[10],
                        jefe, delegado, matriz[13],ucolor);
                arrayList.add(evento);
            } else {
                Toast.makeText(getApplicationContext(),"No hay eventos en esta fecha",Toast.LENGTH_LONG).show();
                Intent i2 = new Intent(MainActivity.this, Control2.class);
                startActivity(i2);
            }

        }

        AdapterLista adapterLista = new AdapterLista(this,arrayList);

        lista.setAdapter(adapterLista);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final int pos = position;
                Log.d("pos",position+"");

                String d[] = nmensaje.split("%");
                String e[] = d[pos].split("#");
                Log.d("d[]",d[pos]);
                Usuario usuario = new Usuario();
                String micod = usuario.getCodigo(getApplicationContext());
                Log.d("e[7]",e[7]);
                Log.d("micod",micod);
                if(e[7].equals(micod)){
                    Log.d("elegido",d[pos]);
                    Eventos eventos1 = new Eventos();
                    eventos1.setEventoElegido(getApplicationContext(),d[pos]);

                    String even[] = d[pos].split("#");
                    String nn="0";
                    if(even[14].equals("") || even[14].equals(" ")){ nn="0";} else { nn=even[14]; }

                    Log.d("nn",nn);
                    titulo = even[4]; Log.d("titulo",titulo);
                    String alm = even[0] + "#" + even[4] + "#" + even[1] + "#" + even[2] + "#" + even[3] + "#" +
                            even[12] + "#" + even[13] + "#" + even[6] + "#" + even[6] + "#" + even[9] + "#" + even[5] +
                            "#" + even[10] + "#" + nn + "#";
                    String alm2 = even[15] + "#" + even[16] + "#" + even[17] + "#" + even[18] + "#" + even[19] + "#" +
                            even[20] + "#" + even[21] + "#" + even[22] + "#" + even[23] + "#" + even[24] + "#" +
                            even[25] + "#" + even[26] + "#" + even[27] + "#" + even[28] + "#" +
                            even[29] + "#" + even[30] + "#" + even[31] + "#" + even[32] + "#" +
                            even[33] + "#";

                    eventos1.setEventoAlmacenadoX(getApplicationContext(), alm);
                    eventos1.setEventoAlmacenadoX2(getApplicationContext(), alm2);

                    Intent i = new Intent(MainActivity.this, Control.class);
                    startActivity(i);
                } else if(titulo.equals("** Candidato no disponible  **")) {
                    Toast.makeText(getApplicationContext(),"No puede editar este evento",Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(),"No puede editar este evento",Toast.LENGTH_LONG).show();
                }


            }
        });
    }

    private void Lista2(String mensaje){
        nmensaje = mensaje;
        final ListView lista = (ListView) findViewById(R.id.mi_lista3);
        final ArrayList<Evento> arrayList = new ArrayList<Evento>();

        Eventos eventos = new Eventos();
        String d[] = mensaje.split("%");
        int nd = d.length;

        for(int i=0; i<nd; i++){
            String matriz[] = d[i].split("#");
            if(matriz.length>13){
                String elcolor = Colores(Integer.parseInt(matriz[7]));
                int ucolor = Color.parseColor(elcolor);
                String municipio = Municipio(matriz[6]);
                String departamento = Departamento(matriz[6]);
                String jefe = Usuarios(matriz[12]);
                String delegado = Usuarios(matriz[13]);
                Evento evento = new Evento(matriz[1], matriz[2], matriz[3], matriz[4], matriz[5],
                        departamento, municipio, matriz[7],  matriz[8], matriz[9], matriz[10],
                        jefe, delegado, matriz[13],ucolor);
                arrayList.add(evento);
            } else {
                Toast.makeText(getApplicationContext(),"No hay eventos en esta fecha",Toast.LENGTH_LONG).show();
                Intent i2 = new Intent(MainActivity.this, Control2.class);
                //startActivity(i2);
            }

        }

        AdapterLista adapterLista = new AdapterLista(this,arrayList);

        lista.setAdapter(adapterLista);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final int pos = position;
                Log.d("pos",position+"");

                String d[] = nmensaje.split("%");
                String e[] = d[pos].split("#");
                Log.d("d[]",d[pos]);
                Usuario usuario = new Usuario();
                String micod = usuario.getCodigo(getApplicationContext());
                Log.d("e[7]",e[7]);
                Log.d("micod",micod);
                if(e[7].equals(micod)){
                    Eventos eventos1 = new Eventos();
                    eventos1.setEventoElegido(getApplicationContext(),d[pos]);
                    Intent i = new Intent(MainActivity.this, Edicion.class);
                    startActivity(i);
                } else {
                    Toast.makeText(getApplicationContext(),"No puede editar este evento",Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    public class LeeUsuario2 extends AsyncTask<String,Void,String> {

        ProgressDialog progressDoalog;

        @Override
        protected void onPreExecute() {
            progressDoalog = new ProgressDialog(MainActivity.this);
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
                Intent i = new Intent(MainActivity.this, MainActivity.class);
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

    private String Municipio(String data){
        String resp = "";
        Municipios municipios = new Municipios();
        String d[] = municipios.getMunicipios(getApplicationContext()).split("%");
        int n = d.length;

        for(int i=0; i<n; i++){
            String e[] = d[i].split("#");
            if(e[0].equals(data)){
                resp = e[1];
            }
        }
        return resp;
    }

    private String Departamento(String data){
        String resp = "";
        Municipios municipios = new Municipios();
        String d[] = municipios.getMunicipios(getApplicationContext()).split("%");
        int n = d.length;

        for(int i=0; i<n; i++){
            String e[] = d[i].split("#");
            if(e[0].equals(data)){
                Log.d("muni",e[2]);
                resp = e[2];
            }
        }

        Departamentos departamentos = new Departamentos();
        String f[] = departamentos.getDepartamentos(getApplicationContext()).split("%");
        int n2 = f.length;

        for(int i=0; i<n2; i++){
            String g[] = f[i].split("#");
            if(g[0].equals(resp)){
                Log.d("depa",g[1]);
                resp = g[1];
            }
        }

        return resp;
    }

    private String Usuarios(String data){
        String resp = "";
        Jefe jefe = new Jefe();
        String d[] = jefe.getJefes(getApplicationContext()).split("%");
        int n = d.length;

        for(int i=0; i<n; i++){
            String e[] = d[i].split("#");
            if(e[0].equals(data)){
                resp = e[1];
            }
        }
        return resp;
    }

}
