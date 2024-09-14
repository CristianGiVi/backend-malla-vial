package models;

public class Segment {

    private static Long counter = 0L; 

    private Long id;
    private Long segmentId;
    private Double length;
    private String address;

    public Segment(Long segmentId, Double length, String address) {
        this.id = generateId();
        this.segmentId = segmentId;
        this.length = length;
        this.address = address;
    }

    private Long generateId() {
        return ++counter;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSegmentId() {
        return segmentId;
    }

    public void setSegmentId(Long segmentId) {
        this.segmentId = segmentId;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
