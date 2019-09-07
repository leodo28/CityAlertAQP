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

import java.util.Date;
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
        hora.setText(new Date(incidencias.get(position).getFecha_creacion()).getHours()+":"+new Date(incidencias.get(position).getFecha_creacion()).getMinutes());
        TextView tipo = v.findViewById(R.id.tipo);
        tipo.setText(incidencias.get(position).getTipo_incidencia().getDescripcion());
        TextView sereno = v.findViewById(R.id.sereno);
        if(incidencias.get(position).getSereno()!=null){
        sereno.setText(incidencias.get(position).getSereno().getNombres()+" "
                +incidencias.get(position).getSereno().getApellidopaterno());}
                else{sereno.setText("-");}
        TextView estado = v.findViewById(R.id.estado);
        if(incidencias.get(position).isEstado()){estado.setText("Atendido");}
        else{estado.setText("No Atendido");}
        TextView fecha = v.findViewById(R.id.fecha);
        fecha.setText(new Date(incidencias.get(position).getFecha_creacion()).getDay()+"-"
                +new Date(incidencias.get(position).getFecha_creacion()).getMonth()+"-"
                +(new Date(incidencias.get(position).getFecha_creacion()).getYear()-100)+"");
        return v;
    }
}
