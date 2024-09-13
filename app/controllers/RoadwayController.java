package controllers;

import models.Roadway;
import play.mvc.Controller;
import play.mvc.Result;
import services.RoadwayService;

import java.util.List;
import java.util.Optional;

public class RoadwayController extends Controller {

    private final RoadwayService roadwayService = new RoadwayService();

    // Crear una nueva calzada
    public Result createRoadway(Long id, Double length) {
        Roadway newRoadway = new Roadway(id, length);
        roadwayService.createRoadway(newRoadway);
        return ok("Roadway created successfully");
    }

    // Obtener todas las calzadas
    public Result getAllRoadways() {
        List<Roadway> roadways = roadwayService.getAllRoadways();
        return ok(roadways.toString());
    }

    // Obtener una calzada por ID
    public Result getRoadwayById(Long id) {
        Optional<Roadway> roadway = roadwayService.getRoadwayById(id);
        return roadway.map(value -> ok(value.toString()))
                .orElseGet(() -> notFound("Roadway not found"));
    }

    // Actualizar una calzada
    public Result updateRoadway(Long id, Double length) {
        Roadway updatedRoadway = new Roadway(id, length);
        Optional<Roadway> updated = roadwayService.updateRoadway(id, updatedRoadway);
        return updated.map(value -> ok("Roadway updated successfully"))
                .orElseGet(() -> notFound("Roadway not found"));
    }

    // Eliminar una calzada
    public Result deleteRoadway(Long id) {
        boolean deleted = roadwayService.deleteRoadway(id);
        return deleted ? ok("Roadway deleted successfully") : notFound("Roadway not found");
    }
}
