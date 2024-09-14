package services;

import models.Roadway;
import repository.RoadwayRepository;

import java.util.List;
import java.util.Optional;

public class RoadwayService {

    // Crear una nueva calzada

    public Roadway createRoadway(Roadway roadway) {
        RoadwayRepository.addRoadway(roadway);
        return roadway;
    }

    // Obtener todas las calzadas

    public List<Roadway> getAllRoadways() {
        return RoadwayRepository.getRoadways();
    }

    // Obtener una calzada por su id relativa

    public Optional<Roadway> getRoadwayById(Long roadwayId) {
        Long id = getIdByRoadwayId(roadwayId);
        return RoadwayRepository.getRoadways().stream()
                .filter(roadway -> roadway.getId().equals(id))
                .findFirst();
    }

    // Actualizar una calzada

    public Optional<Roadway> updateRoadway(Long roadwayId, Roadway updatedRoadway) {
        Optional<Roadway> roadwayOpt = getRoadwayById(roadwayId);
        roadwayOpt.ifPresent(roadway -> {
            roadway.setRoadwayId(updatedRoadway.getRoadwayId());
            roadway.setLength(updatedRoadway.getLength());
        });
        return roadwayOpt;
    }

    // Eliminar una calzada

    public boolean deleteRoadway(Long roadwayId) {
        Long id = getIdByRoadwayId(roadwayId);
        return RoadwayRepository.getRoadways().removeIf(roadway -> roadway.getId().equals(id));
    }

    // Obtener ID principal a partir de su id relativa

    public Long getIdByRoadwayId(Long roadwayId) {
        return RoadwayRepository.getRoadways().stream()
                .filter(roadway -> roadway.getRoadwayId().equals(roadwayId))
                .map(Roadway::getId)
                .findFirst()
                .orElse(0L);
    }

    // Hallar todos las calzadas que pertenecen a un mismo segmento

    public List<Roadway> findRoadwaysBySegmentId(Long segmentId) {
        return RoadwayRepository.findRoadways(segmentId);
    }
}
