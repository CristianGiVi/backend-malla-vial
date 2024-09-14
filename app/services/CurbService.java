package services;

import models.Curb;
import repository.CurbRepository;

import java.util.List;
import java.util.Optional;

public class CurbService {

    // Crear un nuevo bordillo

    public Curb createCurb(Curb curb) {
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
        Long id = getIdByCurbId(curbId);
        return CurbRepository.getCurbs().removeIf(curb -> curb.getId().equals(id));
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
        return CurbRepository.findCurbs(segmentId);
    }
}
