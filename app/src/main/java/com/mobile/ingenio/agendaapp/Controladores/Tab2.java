package com.mobile.ingenio.agendaapp.Controladores;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mobile.ingenio.agendaapp.Modelos.Eventos;
import com.mobile.ingenio.agendaapp.R;

public class Tab2 extends Fragment {

    View view;
    EditText csillas,dsillas,csonido,dsonido,ctarima,dtarima,ccarpa,dcarpa,cvideobeam,dvideobeam;
    EditText cpantalla,dpantalla,cartista,dartista,cavanzada,davanzada,ctransporte,dtransporte,observaciones;
    Button guardar;
    String msillas,nsillas,msonido,nsonido,mtarima,ntarima,mcarpa,ncarpa,mvideobeam,nvideobeam;
    String mpantalla,npantalla,martista,nartista,mavanzada,navanzada,mtransporte,ntrasnporte,mobservaciones;

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState) {
        view = layoutInflater.inflate(R.layout.logistica, container, false);

        csillas = (EditText) view.findViewById(R.id.csillas);
        dsillas = (EditText) view.findViewById(R.id.dsillas);
        csonido = (EditText) view.findViewById(R.id.csonido);
        dsonido = (EditText) view.findViewById(R.id.dsonido);
        ctarima = (EditText) view.findViewById(R.id.ctarima);
        dtarima = (EditText) view.findViewById(R.id.dtarima);
        ccarpa = (EditText) view.findViewById(R.id.ccarpa);
        dcarpa = (EditText) view.findViewById(R.id.dcarpa);
        cvideobeam = (EditText) view.findViewById(R.id.cvideobeam);
        dvideobeam = (EditText) view.findViewById(R.id.dvideobeam);
        cpantalla = (EditText) view.findViewById(R.id.cpantalla);
        dpantalla = (EditText) view.findViewById(R.id.dpantalla);
        cartista = (EditText) view.findViewById(R.id.cartista);
        dartista = (EditText) view.findViewById(R.id.dartista);
        cavanzada = (EditText) view.findViewById(R.id.cavanzada);
        davanzada = (EditText) view.findViewById(R.id.davanzada);
        ctransporte = (EditText) view.findViewById(R.id.ctransporte);
        dtransporte = (EditText) view.findViewById(R.id.dtransporte);
        observaciones = (EditText) view.findViewById(R.id.observaciones);

        Eventos eventos = new Eventos();
        Log.d("vvvv",eventos.getEventoElegido(getActivity()));
        String even[] = eventos.getEventoElegido(getActivity()).split("#");
        if(even.length>15){
            csillas.setText(even[15]);
            dsillas.setText(even[16]);
            csonido.setText(even[17]);
            dsonido.setText(even[18]);
            ctarima.setText(even[19]);
            dtarima.setText(even[20]);
            ccarpa.setText(even[21]);
            dcarpa.setText(even[22]);
            cvideobeam.setText(even[23]);
            dvideobeam.setText(even[24]);
            cpantalla.setText(even[25]);
            dpantalla.setText(even[26]);
            cartista.setText(even[27]);
            dartista.setText(even[28]);
            cavanzada.setText(even[29]);
            davanzada.setText(even[30]);
            ctransporte.setText(even[31]);
            dtransporte.setText(even[32]);
            observaciones.setText(even[33]);

        }

        msillas = csillas.getText().toString();
        nsillas = dsillas.getText().toString();
        msonido = csonido.getText().toString();
        nsonido = dsonido.getText().toString();
        mtarima = ctarima.getText().toString();
        ntarima = dtarima.getText().toString();
        mcarpa = ccarpa.getText().toString();
        ncarpa = dcarpa.getText().toString();
        mvideobeam = cvideobeam.getText().toString();
        nvideobeam = dvideobeam.getText().toString();
        mpantalla = cpantalla.getText().toString();
        npantalla = dpantalla.getText().toString();
        martista = cartista.getText().toString();
        nartista = dartista.getText().toString();
        mavanzada = cavanzada.getText().toString();
        navanzada = davanzada.getText().toString();
        mtransporte = ctransporte.getText().toString();
        ntrasnporte = dtransporte.getText().toString();
        mobservaciones = observaciones.getText().toString();

        ConnectivityManager connMgr = (ConnectivityManager)
                getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            String alm = msillas + "#" + nsillas + "#" + msonido + "#" + nsonido + "#" + mtarima + "#" +
                    ntarima + "#" + mcarpa + "#" + ncarpa + "#" + mvideobeam + "#" + nvideobeam + "#" +
                    mpantalla + "#" + npantalla + "#" +
                    martista + "#" + nartista + "#" + mavanzada + "#" + navanzada + "#" +
                    mtransporte + "#" + ntrasnporte + "#" + mobservaciones + "#";
            Eventos eventos1 = new Eventos();
            eventos1.setEventoAlmacenadoX2(getActivity(), alm);

        } else {
            String alm = msillas + "#" + nsillas + "#" + msonido + "#" + nsonido + "#" + mtarima + "#" +
                    ntarima + "#" + mcarpa + "#" + ncarpa + "#" + mvideobeam + "#" + nvideobeam + "#" +
                    mpantalla + "#" + npantalla + "#" + martista + "#" + nartista + "#" +
                    martista + "#" + nartista + "#" + mavanzada + "#" + navanzada + "#" +
                    mtransporte + "#" + ntrasnporte + "#" + mobservaciones + "#%";
            Eventos eventos1 = new Eventos();
            eventos1.setEventoAlmacenadoIn(getActivity(), alm);
        }


        guardar = (Button) view.findViewById(R.id.addEvento);
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"Hecho!",Toast.LENGTH_LONG).show();
                msillas = csillas.getText().toString();
                nsillas = dsillas.getText().toString();
                msonido = csonido.getText().toString();
                nsonido = dsonido.getText().toString();
                mtarima = ctarima.getText().toString();
                ntarima = dtarima.getText().toString();
                mcarpa = ccarpa.getText().toString();
                ncarpa = dcarpa.getText().toString();
                mvideobeam = cvideobeam.getText().toString();
                nvideobeam = dvideobeam.getText().toString();
                mpantalla = cpantalla.getText().toString();
                npantalla = dpantalla.getText().toString();
                martista = cartista.getText().toString();
                nartista = dartista.getText().toString();
                mavanzada = cavanzada.getText().toString();
                navanzada = davanzada.getText().toString();
                mtransporte = ctransporte.getText().toString();
                ntrasnporte = dtransporte.getText().toString();
                mobservaciones = observaciones.getText().toString();

                ConnectivityManager connMgr = (ConnectivityManager)
                        getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {
                    String alm = msillas + "#" + nsillas + "#" + msonido + "#" + nsonido + "#" + mtarima + "#" +
                            ntarima + "#" + mcarpa + "#" + ncarpa + "#" + mvideobeam + "#" + nvideobeam + "#" +
                            mpantalla + "#" + npantalla + "#" + martista + "#" + nartista + "#" +
                            martista + "#" + nartista + "#" + mavanzada + "#" + navanzada + "#" +
                            mtransporte + "#" + ntrasnporte + "#" + mobservaciones + "#%";
                    Eventos eventos1 = new Eventos();
                    eventos1.setEventoAlmacenadoX2(getActivity(), alm);

                } else {
                    String alm = msillas + "#" + nsillas + "#" + msonido + "#" + nsonido + "#" + mtarima + "#" +
                            ntarima + "#" + mcarpa + "#" + ncarpa + "#" + mvideobeam + "#" + nvideobeam + "#" +
                            mpantalla + "#" + npantalla + "#" + martista + "#" + nartista + "#" +
                            martista + "#" + nartista + "#" + mavanzada + "#" + navanzada + "#" +
                            mtransporte + "#" + ntrasnporte + "#" + mobservaciones + "#%";
                    Eventos eventos1 = new Eventos();
                    eventos1.setEventoAlmacenadoIn(getActivity(), alm);
                }
            }
        });

        return view;
    }
}
