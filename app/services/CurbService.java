package services;

import models.Curb;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CurbService {

    private List<Curb> curbs = new ArrayList<>();

    // Crear un nuevo bordillo
    public Curb createCurb(Curb curb) {
        curbs.add(curb);
        return curb;
    }

    // Obtener todos los bordillos
    public List<Curb> getAllCurbs() {
        return curbs;
    }

    // Obtener un bordillo por ID
    public Optional<Curb> getCurbById(Long id) {
        return curbs.stream().filter(curb -> curb.getId().equals(id)).findFirst();
    }

    // Actualizar un bordillo
    public Optional<Curb> updateCurb(Long id, Curb updatedCurb) {
        Optional<Curb> curbOpt = getCurbById(id);
        curbOpt.ifPresent(curb -> curb.setLength(updatedCurb.getLength()));
        return curbOpt;
    }

    // Eliminar un bordillo
    public boolean deleteCurb(Long id) {
        return curbs.removeIf(curb -> curb.getId().equals(id));
    }
}
