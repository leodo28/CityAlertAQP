
package com.example.tecsup.cityalertarequipa.Clases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListDataPump {
    public static HashMap<String, List<String>> getData(List<Cls_Persona> serenos) {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        for(int i=0;i<serenos.size();i++) {
            if(serenos.get(i).getActivo()==1){
            List<String> sereno = new ArrayList<String>();
            sereno.add("Geolocalizacion");
            sereno.add("Incidencias");
            sereno.add("Editar");
            sereno.add("Eliminar");
            expandableListDetail.put(serenos.get(i).getNombre()+" "+serenos.get(i).getApellido(), sereno);
            }
            else{
                List<String> sereno = new ArrayList<String>();
                expandableListDetail.put(serenos.get(i).getNombre()+" "+serenos.get(i).getApellido(), sereno);
            }
        }
        return expandableListDetail;
    }
}
