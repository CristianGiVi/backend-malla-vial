package services;

import models.Segment;
import models.Curb;
import models.Roadway;

import repository.SegmentRepository;
import repository.CurbRepository;
import repository.RoadwayRepository;

import java.util.List;
import java.util.HashMap;
import java.util.Optional;
import java.util.stream.Collectors;

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
    
    Optional<Segment> segmentOpt = getSegmentById(segmentId);
    if (segmentOpt.isPresent()) {
        Long id = getIdBySegmentId(segmentId);
        List<Curb> curbs = CurbRepository.findCurbs(id);
        List<Roadway> roadways = RoadwayRepository.findRoadways(id);
        curbs.forEach(CurbRepository::removeCurb);
        roadways.forEach(RoadwayRepository::removeRoadway);
        return SegmentRepository.removeSegment(segmentOpt.get());
    }

    return false;
}

    // Obtener ID principal a partir de su id relativa

    public Long getIdBySegmentId(Long segmentId) {
        return SegmentRepository.getSegments().stream()
                .filter(segment -> segment.getSegmentId().equals(segmentId))
                .map(Segment::getId)
                .findFirst()
                .orElse(0L);
    }

    // Obtener los datos de los segmentos, junto con la cantidad de bordillos y calzadas que tiene

    public List<HashMap<String, Object>> getAllSegmentDetails() {
        List<Segment> segments = SegmentRepository.getSegments();

        return segments.stream().map(segment -> {
            HashMap<String, Object> segmentDetails = new HashMap<>();
            List<Roadway> roadways = RoadwayRepository.findRoadways(segment.getId());
            List<Curb> curbs = CurbRepository.findCurbs(segment.getId());

            segmentDetails.put("segment", segment);
            segmentDetails.put("amountRoadways", roadways.size());
            segmentDetails.put("amountCurbs", curbs.size());

            return segmentDetails;
        }).collect(Collectors.toList());
    }

    // Obtener los datos de un segmento, junto con la cantidad de bordillos y calzadas que tiene

     public Optional<HashMap<String, Object>> getSegmentdetails(Long segmentId) {
        Optional<Segment> segmentOpt = getSegmentById(segmentId);

        if (!segmentOpt.isPresent()) {
            return Optional.empty();
        }

        Long id = segmentOpt.get().getId();

        List<Roadway> roadways = RoadwayRepository.findRoadways(id);
        List<Curb> curbs = CurbRepository.findCurbs(id);
        HashMap<String, Object> segmentDetails = new HashMap<>();

        segmentDetails.put("segment", segmentOpt.get());
        segmentDetails.put("amountRoadways", roadways.size());
        segmentDetails.put("amountCurbs", curbs.size());

        return Optional.of(segmentDetails);
    }

}
