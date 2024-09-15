package services;

import models.Roadway;
import models.Segment;
import repository.RoadwayRepository;
import repository.SegmentRepository;

import java.util.List;
import java.util.Optional;

public class RoadwayService {

    // Crear una nueva calzada

    public Roadway createRoadway(Roadway roadway) {
        Long id = getIdBySegmentId(roadway.getSegmentId());
        roadway.setSegmentId(id);
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
        Optional<Roadway> roadwayOpt = getRoadwayById(roadwayId);
        if (roadwayOpt.isPresent()) {
            return RoadwayRepository.removeRoadway(roadwayOpt.get());
        }
        return false;

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
        Long id = getIdBySegmentId(segmentId);
        return RoadwayRepository.findRoadways(id);
    }

    public Long getIdBySegmentId(Long segmentId) {
        return SegmentRepository.getSegments().stream()
                .filter(segment -> segment.getSegmentId().equals(segmentId))
                .map(Segment::getId)
                .findFirst()
                .orElse(0L);
    }
}
