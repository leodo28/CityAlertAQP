
package com.example.tecsup.city_alert_aqp_ale.Clases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListDataPump {
    public static HashMap<String, List<String>> getData(List<Cls_Persona> serenos) {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        for(int i=0;i<serenos.size();i++) {
            if(serenos.get(i).isEstado()){
            List<String> sereno = new ArrayList<String>();
            sereno.add("Geolocalizacion");
            sereno.add("Incidencias");
            sereno.add("Editar");
            sereno.add("Eliminar");
            expandableListDetail.put(serenos.get(i).getNombres()+" "+serenos.get(i).getApellidopaterno(), sereno);
            }
            else{
                List<String> sereno = new ArrayList<String>();
                expandableListDetail.put(serenos.get(i).getNombres()+" "+serenos.get(i).getApellidopaterno() , sereno);
            }
        }
        return expandableListDetail;
    }
}
