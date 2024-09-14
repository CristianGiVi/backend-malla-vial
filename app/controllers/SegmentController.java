package controllers;

import models.Segment;
import services.SegmentService;
import play.mvc.*;
import play.libs.Json;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class SegmentController extends Controller {

    private final SegmentService segmentService = new SegmentService();

    /* 
        {
            "segmentId": 101,
            "length": 250.5,
            "address": "Carrera 16"
        }
    */

    // Crear un nuevo segmento

    public Result createSegment(Http.Request request) {
        
        try {
             Segment newSegment = Json.fromJson(request.body().asJson(), Segment.class);

        if (newSegment.getSegmentId() == null || newSegment.getSegmentId() <= 0) {
            return badRequest("Atributo segmentId invalido");
        }
        if (newSegment.getLength() == null || newSegment.getLength() <= 0) {
            return badRequest("Atributo length invalido");
        }
        if (newSegment.getAddress() == null || newSegment.getAddress().isEmpty()) {
            return badRequest("Atributo address invalido");
        }

            segmentService.createSegment(newSegment);
            return ok("Segmento creado con exito");
        } catch (Exception e) {
            return badRequest("Error al crear el segmento");
        }
    }


    // Obtener todos los segmentos

    public Result getAllSegments() {
        List<Segment> segments = segmentService.getAllSegments();
        return ok(Json.toJson(segments));
    }

    // Obtener un segmento por su id relativa

    public Result getSegmentById(Long id) {
        Optional<Segment> segment = segmentService.getSegmentById(id);
        return segment.map(value -> ok(Json.toJson(value))) 
                .orElseGet(() -> notFound("Segmento no encontrado"));
    }

    /*
        {
            "segmentId": 101,
            "length": 30.0,
            "address": "456 Elm Street"
        }
    */

    // Actualizar un segmento

    public Result updateSegment(Long id, Http.Request request) {
        try {
            Segment updatedSegment = Json.fromJson(request.body().asJson(), Segment.class);

            if (updatedSegment.getSegmentId() == null || updatedSegment.getSegmentId() <= 0) {
                return badRequest("Atributo segmentId invalido.");
            }
            if (updatedSegment.getLength() == null || updatedSegment.getLength() <= 0) {
                return badRequest("Atributo length invalido");
            }
            if (updatedSegment.getAddress() == null || updatedSegment.getAddress().isEmpty()) {
                return badRequest("Atributo address invalido");
            }

            Optional<Segment> updated = segmentService.updateSegment(id, updatedSegment);

            return updated.map(value -> ok("Segmento editado con exito"))
                .orElseGet(() -> notFound("Segmento no encontrado"));
        } catch (Exception e) {
            return badRequest("Error al editar el segmento.");
        }

    }

    // Eliminar un segmento

    public Result deleteSegment(Long id) {
        boolean deleted = segmentService.deleteSegment(id);
        return deleted ? ok("Segmento eliminado con exito") : notFound("Segmento no encontrado");
    }

    // Obtener todos los detalles de los segmentos

    public Result getAllSegmentDetails() {
        List<HashMap<String, Object>> segmentsWithDetails = segmentService.getAllSegmentDetails();
        return ok(Json.toJson(segmentsWithDetails));
    }
}
