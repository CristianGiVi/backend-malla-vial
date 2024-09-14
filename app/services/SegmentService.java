package services;

import models.Segment;
import models.Curb;
import models.Roadway;

import repository.SegmentRepository;
import repository.CurbRepository;
import repository.RoadwayRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class SegmentService {

    // Crear un nuevo segmento

    public Segment createSegment(Segment segment) {
        SegmentRepository.addSegment(segment);
        return segment;
    }

    // Obtener todos los segmentos

    public List<Segment> getAllSegments() {
        return SegmentRepository.getSegments();
    }

    // Obtener un segmento por su id relativa

    public Optional<Segment> getSegmentById(Long segmentId) {
        Long id = getIdBySegmentId(segmentId);
        return SegmentRepository.getSegments().stream()
                .filter(segment -> segment.getId().equals(id))
                .findFirst();
    }

    // Actualizar un segmento

    public Optional<Segment> updateSegment(Long segmentId, Segment updatedSegment) {
        Optional<Segment> segmentOpt = getSegmentById(segmentId);
        segmentOpt.ifPresent(segment -> {
            segment.setSegmentId(updatedSegment.getSegmentId());
            segment.setLength(updatedSegment.getLength());
            segment.setAddress(updatedSegment.getAddress());
        });
        return segmentOpt;
    }

    // Eliminar un segmento

    public boolean deleteSegment(Long segmentId) {
        Long id = getIdBySegmentId(segmentId);
        return SegmentRepository.getSegments().removeIf(segment -> segment.getId().equals(id));
    }

    // Obtener ID principal a partir de su id relativa

    public Long getIdBySegmentId(Long segmentId) {
        return SegmentRepository.getSegments().stream()
                .filter(segment -> segment.getSegmentId().equals(segmentId))
                .map(Segment::getId)
                .findFirst()
                .orElse(0L);
    }

    // Obtener los datos de un segmento, junto con la cantidad de bordillos y calzadas que tiene

    public Optional<HashMap<String, Object>> getSegmentdetails(Long segmentId) {

            Optional<Segment> segmentOpt = getSegmentById(segmentId);
            if (!segmentOpt.isPresent()) {
                return Optional.empty();
            }

            List<Roadway> roadways = RoadwayRepository.findRoadways(segmentId);
            List<Curb> curbs = CurbRepository.findCurbs(segmentId);


            HashMap<String, Object> segmentDetails = new HashMap<>();

            segmentDetails.put("segment", segmentOpt.get());
            segmentDetails.put("amountRoadways", roadways.size());
            segmentDetails.put("amountCurbs", curbs.size());

            return Optional.of(segmentDetails);
        }

}
