package controllers;

import models.Curb;
import play.mvc.Controller;
import play.mvc.Result;
import services.CurbService;

import java.util.List;
import java.util.Optional;

public class CurbController extends Controller {

    private final CurbService curbService = new CurbService();

    // Crear un nuevo bordillo
    public Result createCurb(Long id, Double length) {
        Curb newCurb = new Curb(id, length);
        curbService.createCurb(newCurb);
        return ok("Curb created successfully");
    }

    // Obtener todos los bordillos
    public Result getAllCurbs() {
        List<Curb> curbs = curbService.getAllCurbs();
        return ok(curbs.toString());
    }

    // Obtener un bordillo por ID
    public Result getCurbById(Long id) {
        Optional<Curb> curb = curbService.getCurbById(id);
        return curb.map(value -> ok(value.toString()))
                .orElseGet(() -> notFound("Curb not found"));
    }

    // Actualizar un bordillo
    public Result updateCurb(Long id, Double length) {
        Curb updatedCurb = new Curb(id, length);
        Optional<Curb> updated = curbService.updateCurb(id, updatedCurb);
        return updated.map(value -> ok("Curb updated successfully"))
                .orElseGet(() -> notFound("Curb not found"));
    }

    // Eliminar un bordillo
    public Result deleteCurb(Long id) {
        boolean deleted = curbService.deleteCurb(id);
        return deleted ? ok("Curb deleted successfully") : notFound("Curb not found");
    }
}
