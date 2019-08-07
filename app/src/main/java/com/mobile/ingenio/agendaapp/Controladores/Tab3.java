package com.mobile.ingenio.agendaapp.Controladores;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

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
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static android.support.v4.content.ContextCompat.getSystemService;

public class Tab3 extends Fragment {

    View view;
    String jefeElegido,delegadoElegido,departamentoElegido,municipioElegido;
    EditText nomEvento,fechaE,horaI,horaF,nombreOrg,direvento,televento,num_personas;
    TextView municipio,departamento,jefe,delegado;
    Spinner jefes,delegados,municipios,departamentos;
    int codJefe=0,codDelegado=0,codDepartamento,codMunicipio;
    ImageView ibObtenerFecha,ib_Obtener_hora1,ib_Obtener_hora2;
    private static final String DOS_PUNTOS = ":";
    public final Calendar c = Calendar.getInstance();

    final int hora = c.get(Calendar.HOUR_OF_DAY);
    final int minuto = c.get(Calendar.MINUTE);
    private static final String CERO = "0";

    int ubicar;
    int anio = c.get(Calendar.YEAR);
    int mes = c.get(Calendar.MONTH);
    int dia = c.get(Calendar.DAY_OF_MONTH);
    String ntitulo,nfecha,nhoraInicio,nhoraFin,njefe,ndelegado,ndepartamento,nmunicipio,nnombre,ndireccion,ntelefono,nid,nnumpersonas;
    String fechaActual;
    Button guardar;


    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState) {
        view = layoutInflater.inflate(R.layout.edicion, container, false);

        if(mes<10){ fechaActual = ""+anio+"-0"+mes;} else { fechaActual = ""+anio+"-"+mes; }
        if(dia<10){ fechaActual = fechaActual+"-0"+dia;} else { fechaActual = fechaActual+"-"+dia;}
        //String fechaActual = ""+anio+"-"+mes+"-"+dia;
        Log.d("fechaActual",fechaActual);

        nomEvento = (EditText) view.findViewById(R.id.nomevento);
        fechaE = (EditText) view.findViewById(R.id.fechaE);
        horaI = (EditText) view.findViewById(R.id.horaI);
        horaF = (EditText) view.findViewById(R.id.horaF);
        nombreOrg = (EditText) view.findViewById(R.id.nombreOrg);
        direvento = (EditText) view.findViewById(R.id.direvento);
        televento = (EditText) view.findViewById(R.id.televento);
        num_personas = (EditText) view.findViewById(R.id.num_personas);
        departamento = (TextView) view.findViewById(R.id.departamento);
        municipio = (TextView) view.findViewById(R.id.municipio);
        jefe = (TextView) view.findViewById(R.id.tjefe);
        delegado = (TextView) view.findViewById(R.id.tdelegado);
        guardar = (Button) view.findViewById(R.id.addEvento);

        final String sjefes2[] = ("Cambiar jefe#"+ConvertJefe()).split("#");
        final String cjefes2[] = ("Cambiar jefe#"+ConvertCodJefe()).split("#");

        final String sdelagdos2[] = ("Cambiar delegado#"+ConvertDelegado()).split("#");
        final String cdelagdos2[] = ("Cambiar delegado#"+ConvertCodDelegado()).split("#");

        jefes = (Spinner) view.findViewById(R.id.jefe);
        ArrayAdapter<String> adapter2;
        adapter2 = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,sjefes2);
        jefes.setAdapter(adapter2);
        jefes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id)
            {
                if(pos!=0){
                    jefe.setText(sjefes2[pos]);
                    codJefe = Integer.parseInt(cjefes2[pos]);
                    jefeElegido = sjefes2[pos];
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        delegados = (Spinner) view.findViewById(R.id.delegado);
        ArrayAdapter<String> adapter3;
        adapter3 = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,sdelagdos2);
        delegados.setAdapter(adapter3);
        delegados.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id)
            {
                if(pos!=0){
                    delegado.setText(sdelagdos2[pos]);
                    codDelegado = Integer.parseInt(cdelagdos2[pos]);
                    delegadoElegido = sdelagdos2[pos];
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        departamentos = (Spinner) view.findViewById(R.id.ddepartamento);
        municipios = (Spinner) view.findViewById(R.id.dmunicipio);

        final String sdepartamentos[] = ("Cambiar Departamento#"+ConvertDepartamentos()).split("#");

        ArrayAdapter<String> adapter4;
        adapter4 = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,sdepartamentos);
        departamentos.setAdapter(adapter4);
        departamentos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id)
            {
                if(pos!=0){
                    departamento.setText(sdepartamentos[pos]);
                    codDepartamento = pos;
                    departamentoElegido = sdepartamentos[pos];
                    SpinnerMunicipio(codDepartamento);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });



        ibObtenerFecha = (ImageView) view.findViewById(R.id.ib_obtener_fecha);
        ibObtenerFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.ib_obtener_fecha:
                        obtenerFecha();
                        break;
                }
            }
        });

        ib_Obtener_hora1 = (ImageView) view.findViewById(R.id.ib_obtener_hora1);
        ib_Obtener_hora1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.ib_obtener_hora1:
                        ubicar=1;
                        obtenerHora();
                        break;
                }
            }
        });

        ib_Obtener_hora2 = (ImageView) view.findViewById(R.id.ib_obtener_hora2);
        ib_Obtener_hora2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.ib_obtener_hora2:
                        ubicar=2;
                        obtenerHora();
                        break;
                }
            }
        });

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"Hecho!",Toast.LENGTH_LONG).show();
                ntitulo = nomEvento.getText().toString();
                nfecha = fechaE.getText().toString();
                nhoraInicio = horaI.getText().toString();
                nhoraFin = horaF.getText().toString();
                njefe = codJefe + "";
                ndelegado = codDelegado + "";
                ndepartamento = codDepartamento + "";
                nmunicipio = codMunicipio + "";
                nnombre = nombreOrg.getText().toString();
                ndireccion = direvento.getText().toString();
                ntelefono = televento.getText().toString();
                nnumpersonas = num_personas.getText().toString();

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

                ConnectivityManager connMgr = (ConnectivityManager)
                        getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {
                    if(codJefe!=0 && codDelegado!=0){
                        String alm = nid+"#"+ntitulo+"#"+nfecha+"#"+nhoraInicio+"#"+nhoraFin+"#"+
                                njefe+"#"+ndelegado+"#"+ndepartamento+"#"+nmunicipio+"#"+nnombre+"#"+ndireccion+
                                "#"+ntelefono+"#"+nnumpersonas+"#";
                        Eventos eventos1 = new Eventos();
                        eventos1.setEventoAlmacenadoX(getActivity(),alm);
                    } else {
                        Toast.makeText(getActivity(),"INDIQUE JEFE Y DELEGADO",Toast.LENGTH_LONG).show();
                    }

                } else {
                    String alm = nid+"#"+ntitulo+"#"+nfecha+"#"+nhoraInicio+"#"+nhoraFin+"#"+
                            njefe+"#"+ndelegado+"#"+ndepartamento+"#"+nmunicipio+"#"+nnombre+"#"+ndireccion+
                            "#"+ntelefono+"#%";
                    Eventos eventos1 = new Eventos();
                    eventos1.setEventoAlmacenadoEd(getActivity(),alm);
                }
            }
        });
        
        return view;
    }

    private String Municipio(String data){
        String resp = "";
        Municipios municipios = new Municipios();
        String d[] = municipios.getMunicipios(getActivity()).split("%");
        int n = d.length;

        for(int i=0; i<n; i++){
            String e[] = d[i].split("#");
            if(e[0].equals(data)){
                resp = e[1];
                codMunicipio = Integer.parseInt(e[0]);
            }
        }
        return resp;
    }

    private String Departamento(String data){
        String resp = "";
        Municipios municipios = new Municipios();
        String d[] = municipios.getMunicipios(getActivity()).split("%");
        int n = d.length;

        for(int i=0; i<n; i++){
            String e[] = d[i].split("#");
            if(e[0].equals(data)){
                Log.d("muni",e[2]);
                resp = e[2];

            }
        }

        Departamentos departamentos = new Departamentos();
        String f[] = departamentos.getDepartamentos(getActivity()).split("%");
        int n2 = f.length;

        for(int i=0; i<n2; i++){
            String g[] = f[i].split("#");
            if(g[0].equals(resp)){
                Log.d("depa",g[1]);
                resp = g[1];
                codDepartamento = Integer.parseInt(g[0]);
            }
        }

        return resp;
    }

    private String Usuarios(String data){
        String resp = "";
        Jefe jefe = new Jefe();
        String d[] = jefe.getJefes(getActivity()).split("%");
        int n = d.length;

        for(int i=0; i<n; i++){
            String e[] = d[i].split("#");
            if(e[0].equals(data)){
                resp = e[1];
            }
        }
        return resp;
    }

    private String ConvertJefe(){
        String resp = "";
        Jefe jefe = new Jefe();
        String d[] = jefe.getJefes(getActivity()).split("%");
        int n = d.length;

        for(int i=0; i<n; i++){
            String e[] = d[i].split("#");
            resp = resp + e[1] + "#";

        }
        return resp;
    }

    private String ConvertCodJefe(){
        String resp = "";
        Jefe jefe = new Jefe();
        String d[] = jefe.getJefes(getActivity()).split("%");
        int n = d.length;

        for(int i=0; i<n; i++){
            String e[] = d[i].split("#");
            resp = resp + e[0] + "#";

        }
        return resp;
    }

    private String ConvertDelegado(){
        String resp = "";
        Delegado delegado = new Delegado();
        String d[] = delegado.getDelegados(getActivity()).split("%");
        int n = d.length;

        for(int i=0; i<n; i++){
            String e[] = d[i].split("#");
            resp = resp + e[1] + "#";

        }
        return resp;
    }

    private String ConvertCodDelegado(){
        String resp = "";
        Delegado delegado = new Delegado();
        String d[] = delegado.getDelegados(getActivity()).split("%");
        int n = d.length;

        for(int i=0; i<n; i++){
            String e[] = d[i].split("#");
            resp = resp + e[0] + "#";

        }
        return resp;
    }

    private String ConvertDepartamentos(){
        String resp = "";
        Departamentos departamentos = new Departamentos();
        String d[] = departamentos.getDepartamentos(getActivity()).split("%");
        int n = d.length;

        for(int i=0; i<n; i++){
            String e[] = d[i].split("#");
            resp = resp + e[1] + "#";

        }
        return resp;
    }

    private String ConvertCodDepartamentos(){
        String resp = "";
        Departamentos departamentos = new Departamentos();
        String d[] = departamentos.getDepartamentos(getActivity()).split("%");
        int n = d.length;

        for(int i=0; i<n; i++){
            String e[] = d[i].split("#");
            resp = resp + e[0] + "#";

        }
        return resp;
    }

    private String ConvertMunicipios(String dep){
        Log.d("dep2",dep);
        String resp = "";
        Municipios municipios = new Municipios();
        String d[] = municipios.getMunicipios(getActivity()).split("%");
        int n = d.length;

        for(int i=0; i<n; i++){
            String e[] = d[i].split("#");

            if(dep.equals(e[2])){
                Log.d("muni",d[i]);
                resp = resp + e[1] + "#";
            }
        }
        if(resp.equals("")){ resp = resp + departamentoElegido+"#";}
        return resp;
    }

    private String ConvertCodMunicipios(String dep){
        Log.d("dep2",dep);
        String resp = "";
        Municipios municipios = new Municipios();
        String d[] = municipios.getMunicipios(getActivity()).split("%");
        int n = d.length;

        for(int i=0; i<n; i++){
            String e[] = d[i].split("#");

            if(dep.equals(e[2])){
                Log.d("muni",d[i]);
                resp = resp + e[0] + "#";
            }
        }
        if(resp.equals("")){ resp = resp + codDepartamento+"#";}
        return resp;
    }

    void SpinnerMunicipio(int codDepartamento){
        Log.d("dep1",codDepartamento+"");
        final String smunicipios[] = ("Cambiar Municipio#"+ConvertMunicipios(codDepartamento+"")).split("#");
        final String codmunicipios[] = ("Cambiar Municipio#"+ConvertCodMunicipios(codDepartamento+"")).split("#");

        ArrayAdapter<String> adapter4;
        adapter4 = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,smunicipios);
        municipios.setAdapter(adapter4);
        municipios.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id)
            {
                if(pos!=0){
                    municipio.setText(smunicipios[pos]);
                    String code = codmunicipios[pos];
                    codMunicipio = Integer.parseInt(code);
                    municipioElegido = smunicipios[pos];
                    //SpinnerMunicipio(codDepartamento);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
    }

    private void obtenerFecha(){
        DatePickerDialog recogerFecha = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                //Esta variable lo que realiza es aumentar en uno el mes ya que comienza desde 0 = enero
                final int mesActual = month + 1;
                //Formateo el día obtenido: antepone el 0 si son menores de 10
                String diaFormateado = (dayOfMonth < 10)? CERO + String.valueOf(dayOfMonth):String.valueOf(dayOfMonth);
                //Formateo el mes obtenido: antepone el 0 si son menores de 10
                String mesFormateado = (mesActual < 10)? CERO + String.valueOf(mesActual):String.valueOf(mesActual);
                //Muestro la fecha con el formato deseado
                //fechaE.setText(diaFormateado + BARRA + mesFormateado + BARRA + year);
                fechaE.setText(year+"-"+mesFormateado+"-"+diaFormateado);

            }
            //Estos valores deben ir en ese orden, de lo contrario no mostrara la fecha actual
            /**
             *También puede cargar los valores que usted desee
             */
        },anio, mes, dia);
        //Muestro el widget
        recogerFecha.show();

    }

    private void obtenerHora(){
        TimePickerDialog recogerHora = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                //Formateo el hora obtenido: antepone el 0 si son menores de 10
                String horaFormateada =  (hourOfDay < 10)? String.valueOf(CERO + hourOfDay) : String.valueOf(hourOfDay);
                //Formateo el minuto obtenido: antepone el 0 si son menores de 10
                String minutoFormateado = (minute < 10)? String.valueOf(CERO + minute):String.valueOf(minute);
                //Obtengo el valor a.m. o p.m., dependiendo de la selección del usuario
                String AM_PM;
                if(hourOfDay < 12) {
                    AM_PM = "a.m.";
                } else {
                    AM_PM = "p.m.";
                }

                switch (ubicar){
                    case 1:
                        horaI.setText(horaFormateada + DOS_PUNTOS + minutoFormateado  + DOS_PUNTOS + "00");
                        break;
                    case 2:
                        horaF.setText(horaFormateada + DOS_PUNTOS + minutoFormateado  + DOS_PUNTOS + "00");
                        break;
                }

            }

        }, hora, minuto, false);

        recogerHora.show();
    }

    private class EnvioData extends AsyncTask<String, Integer, String> {

        ProgressDialog progressDoalog;

        @Override
        protected void onPreExecute() {
            progressDoalog = new ProgressDialog(getActivity());
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
                String minombre = usuario.getNombre(getActivity());
                String micod = usuario.getCodigo(getActivity());
                List<BasicNameValuePair> postValues = new ArrayList<>(2);
                postValues.add(new BasicNameValuePair("accion", "1"));
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
                postValues.add(new BasicNameValuePair("fechaRegistra", fechaActual));
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
            /*Conexion conexion = new Conexion();
            String ruta = conexion.getUrl(getActivity())+"/wservices.php?accion2=1";
            LeeUsuario2 lee = new LeeUsuario2();
            lee.execute("1#"+ruta);
            String ruta2 = conexion.getUrl(getActivity())+"/wservices.php?accion2=2";
            LeeUsuario2 lee2 = new LeeUsuario2();
            lee2.execute("2#"+ruta2);
            String ruta3 = conexion.getUrl(getActivity())+"/wservices.php?accion2=3";
            LeeUsuario2 lee3 = new LeeUsuario2();
            lee3.execute("3#"+ruta3);
            String ruta4 = conexion.getUrl(getActivity())+"/wservices.php?accion2=4";
            LeeUsuario2 lee4 = new LeeUsuario2();
            lee4.execute("4#"+ruta4);
            String ruta5 = conexion.getUrl(getActivity())+"/wservices.php?accion2=5";
            LeeUsuario2 lee5 = new LeeUsuario2();
            lee5.execute("5#"+ruta5);*/
        }
    }

    public class LeeUsuario2 extends AsyncTask<String,Void,String> {

        ProgressDialog progressDoalog;

        @Override
        protected void onPreExecute() {
            progressDoalog = new ProgressDialog(getActivity());
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
                eventos.setEvento(getActivity(),p[1]);
            } else if(p[0].equals("2")){
                Log.d("departamentos",p[1]);
                Departamentos departamentos = new Departamentos();
                departamentos.setDepartamentos(getActivity(),p[1]);
            } else if(p[0].equals("3")){
                Log.d("municipios",p[1]);
                Municipios municipios = new Municipios();
                municipios.setMunicipios(getActivity(),p[1]);
            } else if(p[0].equals("4")){
                Log.d("jefe",p[1]);
                Jefe jefe = new Jefe();
                jefe.setJefes(getActivity(),p[1]);

            } else if(p[0].equals("5")){
                Log.d("delegado",p[1]);
                Delegado delegado = new Delegado();
                delegado.setDelegados(getActivity(),p[1]);
                Intent i = new Intent(getActivity(), MainActivity.class);
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
