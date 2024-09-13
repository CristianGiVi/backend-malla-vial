package services;

import models.Roadway;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoadwayService {

    private List<Roadway> roadways = new ArrayList<>();

    // Crear una nueva calzada
    public Roadway createRoadway(Roadway roadway) {
        roadways.add(roadway);
        return roadway;
    }

    // Obtener todas las calzadas
    public List<Roadway> getAllRoadways() {
        return roadways;
    }

    // Obtener una calzada por ID
    public Optional<Roadway> getRoadwayById(Long id) {
        return roadways.stream().filter(roadway -> roadway.getId().equals(id)).findFirst();
    }

    // Actualizar una calzada
    public Optional<Roadway> updateRoadway(Long id, Roadway updatedRoadway) {
        Optional<Roadway> roadwayOpt = getRoadwayById(id);
        roadwayOpt.ifPresent(roadway -> roadway.setLength(updatedRoadway.getLength()));
        return roadwayOpt;
    }

    // Eliminar una calzada
    public boolean deleteRoadway(Long id) {
        return roadways.removeIf(roadway -> roadway.getId().equals(id));
    }
}
