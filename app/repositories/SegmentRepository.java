package repository;

import models.Segment;

import java.util.ArrayList;
import java.util.List;

public class SegmentRepository {

    // Lista estática de segmentos, actúa como una "base de datos" en memoria

    private static List<Segment> segments = new ArrayList<>();

    // Obtener todos los segmentos

    public static List<Segment> getSegments() {
        return segments;
    }

    // Agregar un nuevo segmento a la "base de datos"

    public static void addSegment(Segment segment) {
        segments.add(segment);
    }

    // Eliminar un segmento de la "base de datos"

    public static boolean removeSegment(Segment segment) {
        return segments.remove(segment);
    }
}
