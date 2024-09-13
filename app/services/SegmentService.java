package services;

import models.Segment;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SegmentService {

    private List<Segment> segments = new ArrayList<>();

    // Crear un nuevo segmento
    public Segment createSegment(Segment segment) {
        segments.add(segment);
        return segment;
    }

    // Obtener todos los segmentos
    public List<Segment> getAllSegments() {
        return segments;
    }

    // Obtener un segmento por ID
    public Optional<Segment> getSegmentById(Long id) {
        return segments.stream().filter(segment -> segment.getId().equals(id)).findFirst();
    }

    // Actualizar un segmento
    public Optional<Segment> updateSegment(Long id, Segment updatedSegment) {
        Optional<Segment> segmentOpt = getSegmentById(id);
        segmentOpt.ifPresent(segment -> {
            segment.setSegmentId(updatedSegment.getSegmentId());
            segment.setLength(updatedSegment.getLength());
            segment.setAddress(updatedSegment.getAddress());
        });
        return segmentOpt;
    }

    // Eliminar un segmento
    public boolean deleteSegment(Long id) {
        return segments.removeIf(segment -> segment.getId().equals(id));
    }
}
