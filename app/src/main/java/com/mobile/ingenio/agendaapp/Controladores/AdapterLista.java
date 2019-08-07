package com.mobile.ingenio.agendaapp.Controladores;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobile.ingenio.agendaapp.R;

import java.util.ArrayList;

public class AdapterLista extends BaseAdapter {

    protected Activity activity;
    protected ArrayList<Evento> items;

    public AdapterLista(Activity activity, ArrayList arrayList) {
        this.activity = activity;
        this.items = arrayList;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (convertView==null){
            LayoutInflater ly = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = ly.inflate(R.layout.evento,null);
        }

        Evento evento = items.get(position);

        TextView t = (TextView) v.findViewById(R.id.fecha);
        t.setText(evento.getFecha());
        TextView t2 = (TextView) v.findViewById(R.id.hora);
        t2.setText(evento.getHoraI()+" - "+evento.getHoraF());
        TextView t3 = (TextView) v.findViewById(R.id.titulo);
        t3.setText(evento.getTitulo());
        TextView t5 = (TextView) v.findViewById(R.id.departamento);
        t5.setText("Lugar: "+evento.getDepartamento()+" - "+evento.getMunicipio());
        TextView t6 = (TextView) v.findViewById(R.id.direccion);
        t6.setText("Dirección: "+evento.getDireccion());
        TextView t7 = (TextView) v.findViewById(R.id.telefono);
        t7.setText("Teléfono: "+evento.getTelefono());
        TextView t8 = (TextView) v.findViewById(R.id.nombre);
        t8.setText("Jefe: "+evento.getJefe()+"\nDelegado: "+evento.getDelegado()+
                "\nOrganizador: "+evento.getNombre());
        ImageView i = (ImageView) v.findViewById(R.id.colorido);
        i.setBackgroundColor(evento.getColor());


        return v;
    }
}
