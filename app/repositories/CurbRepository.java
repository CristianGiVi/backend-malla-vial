package repository;

import models.Curb;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CurbRepository {

    // Lista estática de bordillos, actúa como una "base de datos" en memoria

    private static List<Curb> curbs = new ArrayList<>();

    // Obtener todos los bordillos

    public static List<Curb> getCurbs() {
        return curbs;
    }

    // Agregar un nuevo bordillo a la "base de datos"

    public static void addCurb(Curb curb) {
        curbs.add(curb);
    }

    // Eliminar un bordillo de la "base de datos"

    public static boolean removeCurb(Curb curb) {
        return curbs.remove(curb);
    }

    // Método para obtener todas las calzadas cuyo segmentId sea igual a la id relativa del segmento al que pertenece

    public static List<Curb> findCurbs(Long segmentId) {
        return curbs.stream()
                .filter(curb -> curb.getSegmentId().equals(segmentId)) 
                .collect(Collectors.toList());
    }
}
