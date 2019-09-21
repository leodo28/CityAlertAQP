package com.example.tecsup.cityalertarequipa.Adaptadores;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.tecsup.cityalertarequipa.Actividades_Supervisor.Act_Editar_Perfil_Supervisor;
import com.example.tecsup.cityalertarequipa.Actividades_Supervisor.Act_Incidencia_Supervisor;
import com.example.tecsup.cityalertarequipa.Actividades_Supervisor.Act_Ubicacion_Supervisor;
import com.example.tecsup.cityalertarequipa.Clases.Cls_Incidencia;
import com.example.tecsup.cityalertarequipa.Clases.Cls_Persona;
import com.example.tecsup.cityalertarequipa.R;


public class CustomExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> expandableListTitle;
    private List<Cls_Persona> serenos;
    private HashMap<String, List<String>> expandableListDetail;
    List<Cls_Persona> serenos_list = new ArrayList<Cls_Persona>();
    List<Cls_Incidencia> incidencias = new ArrayList<>();
    Cls_Persona sup;

    public CustomExpandableListAdapter(Context context, List<Cls_Persona> serenos,
                                       HashMap<String, List<String>> expandableListDetail,
                                       Cls_Persona sup,List<Cls_Persona> serenos_list,List<Cls_Incidencia> incidencias) {
        this.context = context;
        this.expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
        this.serenos=serenos;
        this.expandableListDetail = expandableListDetail;
        this.sup = sup;
        this.serenos_list = serenos_list;
        this.incidencias = incidencias;
    }

    @Override
    public Object getChild(int listPosition, int expandedListPosition) {
        return this.expandableListDetail.get(this.expandableListTitle.get(listPosition))
                .get(expandedListPosition);
    }

    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        return expandedListPosition;
    }

    @Override
    public View getChildView(final int listPosition, final int expandedListPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final String expandedListText = (String) getChild(listPosition, expandedListPosition);

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_item, null);
        }
        TextView expandedListTextView = (TextView) convertView
                .findViewById(R.id.expandedListItem);
        expandedListTextView.setText(expandedListText);
        expandedListTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((String)getChild(listPosition,expandedListPosition)).equals("Geolocalizacion")){
                    Intent i = new Intent(context,Act_Ubicacion_Supervisor.class);
                    i.putExtra("supervisor",sup);
                    i.putExtra("serenos", (Serializable) serenos_list);
                    i.putExtra("sereno_selec", expandedListPosition);
                    i.putExtra("incidencias", (Serializable) incidencias);
                    context.startActivity(i);
                }
                if(((String)getChild(listPosition,expandedListPosition)).equals("Incidencias")){
                    Intent i = new Intent(context,Act_Incidencia_Supervisor.class);
                    i.putExtra("supervisor",sup);
                    i.putExtra("serenos", (Serializable) serenos_list);
                    i.putExtra("incidencias", (Serializable) incidencias);
                    context.startActivity(i);
                }
                if(((String)getChild(listPosition,expandedListPosition)).equals("Editar")){
                    Intent i = new Intent(context,Act_Editar_Perfil_Supervisor.class);
                    i.putExtra("supervisor",sup);
                    i.putExtra("serenos", (Serializable) serenos_list);
                    i.putExtra("incidencias", (Serializable) incidencias);
                    context.startActivity(i);
                }
            }
        });


        return convertView;
    }

    @Override
    public int getChildrenCount(int listPosition) {
        return this.expandableListDetail.get(this.expandableListTitle.get(listPosition))
                .size();
    }

    @Override
    public Object getGroup(int listPosition) {
        return this.expandableListTitle.get(listPosition);
    }

    @Override
    public int getGroupCount() {
        return this.expandableListTitle.size();
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }

    @Override
    public View getGroupView(int listPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String listTitle = (String) getGroup(listPosition);
        String actividad="Desactivo";
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_group, null);

        }
        for(int i=0;i<serenos.size();i++){
            if(listTitle.equals(serenos.get(i).getNombres()+" "+serenos.get(i).getApellidopaterno())){
             if(serenos.get(i).isEstado()) {actividad="Activo";}
             }
        }
        TextView listTitleTextView = (TextView) convertView
                .findViewById(R.id.listTitle);
        listTitleTextView.setText(listTitle);
        TextView activo = (TextView) convertView
                .findViewById(R.id.activo);
        activo.setText(actividad);
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }
}
