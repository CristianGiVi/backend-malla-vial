package repository;

import models.Roadway;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RoadwayRepository {

    // Lista estática de calzadas, actúa como una "base de datos" en memoria

    private static List<Roadway> roadways = new ArrayList<>();

    // Obtener todas las calzadas

    public static List<Roadway> getRoadways() {
        return roadways;
    }

    // Agregar una nueva calzada a la "base de datos"

    public static void addRoadway(Roadway roadway) {
        roadways.add(roadway);
    }

    // Eliminar una calzada de la "base de datos"

    public static boolean removeRoadway(Roadway roadway) {
        return roadways.remove(roadway);
    }

    // Método para obtener todas las calzadas cuya clave foranea segmentId sea igual a la id del segmento al que pertenece

    public static List<Roadway> findRoadways(Long id) {
        return roadways.stream()
                .filter(roadway -> roadway.getSegmentId().equals(id))
                .collect(Collectors.toList());
    }
}
