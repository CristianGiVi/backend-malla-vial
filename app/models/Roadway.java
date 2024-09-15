package models;

public class Roadway {

    private static Long roadwayCounter = 0L; 

    private Long id;
    private Long roadwayId;
    private Double length;
    private Long segmentId;

    public Roadway(Long roadwayId, Double length, Long segmentId) {
        this.id = generateId();
        this.roadwayId = roadwayId;
        this.length = length;
        this.segmentId = segmentId;
    }

    private Long generateId() {
        return ++roadwayCounter;
    }

    public Long getSegmentId() {
        return segmentId;
    }

    public void setSegmentId(Long segmentId) {
        this.segmentId = segmentId;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public Long getRoadwayId() {
        return roadwayId;
    }

    public void setRoadwayId(Long roadwayId) {
        this.roadwayId = roadwayId;
    }
}
