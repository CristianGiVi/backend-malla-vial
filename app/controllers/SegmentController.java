package controllers;

import models.Segment;
import services.SegmentService;
import play.mvc.*;
import play.libs.Json;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SegmentController extends Controller {

    private final SegmentService segmentService = new SegmentService();


    /* 
    
    {
        "id": 1,
        "segmentId": 101,
        "length": 250.5,
        "address": "Carrera 16"
    }
    
    */

    // Crear un nuevo segmento
    public Result createSegment(Http.Request request) {
        Segment newSegment;
        try {
            newSegment = Json.fromJson(request.body().asJson(), Segment.class);

        // Validar los campos del segmento
        if (newSegment.getSegmentId() == null || newSegment.getSegmentId() <= 0) {
            return badRequest("Invalid segmentId: must be a positive number.");
        }
        if (newSegment.getLength() == null || newSegment.getLength() <= 0) {
            return badRequest("Invalid length: must be a positive number.");
        }
        if (newSegment.getAddress() == null || newSegment.getAddress().isEmpty()) {
            return badRequest("Invalid address: must not be empty.");
        }

            segmentService.createSegment(newSegment);
            return ok("Segment created successfully");
        } catch (Exception e) {
            return badRequest("Invalid JSON format: Unable to parse request body.");
        }
    }


    // Obtener todos los segmentos
    public Result getAllSegments() {
        List<Segment> segments = segmentService.getAllSegments();
        return ok(Json.toJson(segments));
    }

    // Obtener un segmento por ID
    public Result getSegmentById(Long id) {
        Optional<Segment> segment = segmentService.getSegmentById(id);
        return segment.map(value -> ok(Json.toJson(value))) 
                .orElseGet(() -> notFound("Segment not found"));
    }



    /*
    {
        "id": 1,
        "segmentId": 101,
        "length": 30.0,
        "address": "456 Elm Street"
    }
    */


    // Actualizar un segmento
    public Result updateSegment(Long id, Http.Request request) {

        // Intentar mapear el JSON a un objeto Segment
        Segment updatedSegment;
        try {
            updatedSegment = Json.fromJson(request.body().asJson(), Segment.class);

            if (updatedSegment.getSegmentId() == null || updatedSegment.getSegmentId() <= 0) {
                return badRequest("Invalid segmentId: must be a positive number.");
            }
            if (updatedSegment.getLength() == null || updatedSegment.getLength() <= 0) {
                return badRequest("Invalid length: must be a positive number.");
            }
            if (updatedSegment.getAddress() == null || updatedSegment.getAddress().isEmpty()) {
                return badRequest("Invalid address: must not be empty.");
            }


            Optional<Segment> updated = segmentService.updateSegment(id, updatedSegment);
            return updated.map(value -> ok("Segment updated successfully"))
                .orElseGet(() -> notFound("Segment not found"));
        } catch (Exception e) {
            return badRequest("Invalid JSON format: Unable to parse request body.");
        }

    }

    // Eliminar un segmento
    public Result deleteSegment(Long id) {
        boolean deleted = segmentService.deleteSegment(id);
        return deleted ? ok("Segment deleted successfully") : notFound("Segment not found");
    }
}
