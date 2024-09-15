package services;

import models.Curb;
import models.Segment;
import repository.CurbRepository;
import repository.SegmentRepository;

import java.util.List;
import java.util.Optional;

public class CurbService {

    // Crear un nuevo bordillo

    public Curb createCurb(Curb curb) {
        Long id = getIdBySegmentId(curb.getSegmentId());
        curb.setSegmentId(id);
        CurbRepository.addCurb(curb);
        return curb;
    }

    // Obtener todos los bordillos

    public List<Curb> getAllCurbs() {
        return CurbRepository.getCurbs();
    }

    // Obtener un bordillo por su id relativa

    public Optional<Curb> getCurbById(Long curbId) {
        Long id = getIdByCurbId(curbId);
        return CurbRepository.getCurbs().stream()
                .filter(curb -> curb.getId().equals(id))
                .findFirst();
    }

    // Actualizar un bordillo

    public Optional<Curb> updateCurb(Long curbId, Curb updatedCurb) {
        Optional<Curb> curbOpt = getCurbById(curbId);
        curbOpt.ifPresent(curb -> {
            curb.setCurbId(updatedCurb.getCurbId());
            curb.setLength(updatedCurb.getLength());
        });
        return curbOpt;
    }

    // Eliminar un bordillo

    public boolean deleteCurb(Long curbId) {
       Optional<Curb> curbOpt = getCurbById(curbId);
        if (curbOpt.isPresent()) {
        return CurbRepository.removeCurb(curbOpt.get());
        }
        return false;
    }

    // Obtener ID principal a partir de su id relativa

    public Long getIdByCurbId(Long curbId) {
        return CurbRepository.getCurbs().stream()
                .filter(curb -> curb.getCurbId().equals(curbId))
                .map(Curb::getId)
                .findFirst()
                .orElse(0L);
    }

    // Hallar todos los bordillos que pertenecen a un mismo segmento

    public List<Curb> findCurbsBySegmentId(Long segmentId) {
        Long id = getIdBySegmentId(segmentId);
        return CurbRepository.findCurbs(id);
    }

    public Long getIdBySegmentId(Long segmentId) {
        return SegmentRepository.getSegments().stream()
                .filter(segment -> segment.getSegmentId().equals(segmentId))
                .map(Segment::getId)
                .findFirst()
                .orElse(0L);
    }
    
}
