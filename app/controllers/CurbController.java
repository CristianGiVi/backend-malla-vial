package controllers;

import models.Curb;
import services.CurbService;
import play.mvc.*;
import play.libs.Json;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CurbController extends Controller {

    private final CurbService curbService = new CurbService();


    /* 
       {
            "curbId": 101,
            "length": 250.5,
            "segmentId": 1
        }  
    
    */

    // Funcion para crear un nuevo bordillo

    public Result createCurb(Http.Request request) {
        try {
            Curb newCurb = Json.fromJson(request.body().asJson(), Curb.class);
            
            if (newCurb.getCurbId() == null || newCurb.getCurbId() <= 0) {
                return badRequest("Atributo curbId invalido");
            }
            if (newCurb.getLength() == null || newCurb.getLength() <= 0) {
                return badRequest("Atributo length invalido");
            }
            if (newCurb.getSegmentId() == null || newCurb.getSegmentId() <= 0) {
                return badRequest("Atributo segmentId invalido");
            }

            curbService.createCurb(newCurb);
            return ok("Bordillo creado con exito");
        } catch (Exception e) {
            return badRequest("Error al crear el bordillo");
        }
    }

    // Funcion para obtener todos los bordillos

    public Result getAllCurbs() {
        List<Curb> curbs = curbService.getAllCurbs();
        return ok(Json.toJson(curbs));
    }

    // Funcion para obtener un bordillo por su ID relativa

    public Result getCurbById(Long id) {
        Optional<Curb> curb = curbService.getCurbById(id);
        return curb.map(value -> ok(value.toString()))
                .orElseGet(() -> notFound("Bordillo no encontrado"));
    }

    /* 
        {
            "curbId": 101,
            "length": 250.5
        }   
    */

    // Funcion para actualizar un bordillo

    public Result updateCurb(Long id, Http.Request request) {

        try {
            Curb newCurb = Json.fromJson(request.body().asJson(), Curb.class);

            if (newCurb.getCurbId() == null || newCurb.getCurbId() <= 0) {
                return badRequest("Atributo curbId invalido");
            }
            if (newCurb.getLength() == null || newCurb.getLength() <= 0) {
                return badRequest("Atributo length invalido");
            }

            Optional<Curb> updated = curbService.updateCurb(id, newCurb);

            return updated.map(value -> ok("Bordillo editado con exito"))
                .orElseGet(() -> notFound("Bordillo no encontrado"));
        } catch (Exception e) {
            return badRequest("Error al editar el bordillo");
        }
    }

    // Funcion para eliminar un bordillo

    public Result deleteCurb(Long id) {
        boolean deleted = curbService.deleteCurb(id);
        return deleted ? ok("Bordillo eliminado con exito") : notFound("Bordillo no encontrado");
    }

    // Funcion para obtener una lista de bordillos que tengan como llave foranea la id relativa del segmento ingresado

    public Result getCurbsBySegmentId(Long segmentId) {
        List<Curb> curbs = curbService.findCurbsBySegmentId(segmentId);
        return ok(Json.toJson(curbs));
    }

}
