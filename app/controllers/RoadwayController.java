package controllers;

import models.Roadway;
import services.RoadwayService;
import play.mvc.*;
import play.libs.Json;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoadwayController extends Controller {

    private final RoadwayService roadwayService = new RoadwayService();

     /* 
        {
            "roadwayId": 101,
            "length": 250.5
        }
    */

    // Crear una nueva calzada

    public Result createRoadway(Http.Request request) {
        
        try {
            Roadway newRoadway = Json.fromJson(request.body().asJson(), Roadway.class);

            if (newRoadway.getRoadwayId() == null || newRoadway.getRoadwayId() <= 0) {
                return badRequest("Atributo roadwayId invalido");
            }
            if (newRoadway.getLength() == null || newRoadway.getLength() <= 0) {
                return badRequest("Atributo length invalido");
            }
            if (newRoadway.getSegmentId() == null || newRoadway.getSegmentId() <= 0) {
                return badRequest("Atributo segmentId invalido");
            }

            roadwayService.createRoadway(newRoadway);
            return ok("Calzada creada con exito");
        } catch (Exception e) {
            return badRequest("Error al crear la calzada");
        }
    }

    // Funcion para obtener todas las calzadas

    public Result getAllRoadways() {
        List<Roadway> roadways = roadwayService.getAllRoadways();
        return ok(Json.toJson(roadways));
    }


    // Obtener una calzada por su id relativa

    public Result getRoadwayById(Long id) {
        Optional<Roadway> roadway = roadwayService.getRoadwayById(id);
        return roadway.map(value -> ok(Json.toJson(value)))
                .orElseGet(() -> notFound("Roadway no encontrada"));
    }


    /* 
        {
            "roadwayId": 101,
            "length": 250.5
        }
    */

    // Actualizar una calzada

    public Result updateRoadway(Long id, Http.Request request) {
        try {
            Roadway newRoadway = Json.fromJson(request.body().asJson(), Roadway.class);

            if (newRoadway.getRoadwayId() == null || newRoadway.getRoadwayId() <= 0) {
                return badRequest("Atributo roadwayId invalido");
            }
            if (newRoadway.getLength() == null || newRoadway.getLength() <= 0) {
                return badRequest("Atributo length invalido");
            }

            Optional<Roadway> updated = roadwayService.updateRoadway(id, newRoadway);

            return updated.map(value -> ok("calzada editada con exito"))
                .orElseGet(() -> notFound("calzada no encontrada"));
        } catch (Exception e) {
            return badRequest("Error al editar la calzada");
        }

    }

    // Eliminar una calzada

    public Result deleteRoadway(Long id) {
        boolean deleted = roadwayService.deleteRoadway(id);
        return deleted ? ok("Calzada eliminada con exito") : notFound("Calzada no encontrada");
    }

    // Funcion para obtener una lista de calzadas que tengan como llave foranea la id relativa del segmento ingresado

    public Result getRoadwaysBySegmentId(Long segmentId) {
        List<Roadway> roadways = roadwayService.findRoadwaysBySegmentId(segmentId);
        return ok(Json.toJson(roadways));
    }
}
