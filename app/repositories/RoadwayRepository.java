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

    // Método para obtener todas las calzadas cuyo segmentId sea igual a la id relativa del segmento al que pertenece

    public static List<Roadway> findRoadways(Long segmentId) {
        return roadways.stream()
                .filter(roadway -> roadway.getSegmentId().equals(segmentId)) // Filtrar por segmentId
                .collect(Collectors.toList()); // Recoger las calzadas que cumplan la condición
    }
}
