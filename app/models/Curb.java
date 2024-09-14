package models;

public class Curb {

    private static Long counter = 0L; 

    private Long id;
    private Long curbId;
    private Double length;
    private Long segmentId;

    public Curb(Long curbId, Double length, Long segmentId) {
        this.id = generateId();
        this.curbId= curbId;
        this.length = length;
        this.segmentId = segmentId;
    }

    private Long generateId() {
        return ++counter;
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

        public Long getCurbId() {
        return curbId;
    }

    public void setCurbId(Long curbId) {
        this.curbId = curbId;
    }
}
