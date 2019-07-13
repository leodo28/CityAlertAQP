package com.example.tecsup.cityalertarequipa.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tecsup.cityalertarequipa.Clases.Cls_Incidencia;
import com.example.tecsup.cityalertarequipa.R;

import java.util.List;

public class AdapterIncidencia extends BaseAdapter {

    public List<Cls_Incidencia> incidencias;
    Context context;
    int layout;

    public AdapterIncidencia(List<Cls_Incidencia> incidencias, Context context, int layout) {
        this.incidencias = incidencias;
        this.context = context;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return incidencias.size();
    }

    @Override
    public Object getItem(int position) {
        return incidencias.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        LayoutInflater lv = LayoutInflater.from(context);
        v= lv.inflate(layout,null);
        TextView hora = v.findViewById(R.id.hora);
        hora.setText(incidencias.get(position).getHora());
        TextView tipo = v.findViewById(R.id.tipo);
        tipo.setText(incidencias.get(position).getTipo());
        TextView sereno = v.findViewById(R.id.sereno);
        sereno.setText(incidencias.get(position).getSereno().getNombre()+" "
                +incidencias.get(position).getSereno().getApellido());
        TextView estado = v.findViewById(R.id.estado);
        estado.setText(incidencias.get(position).getEstado());
        return v;
    }
}
