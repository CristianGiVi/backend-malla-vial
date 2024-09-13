package models;

public class Segment {

    private Long id;
    private Long segmentId;
    private Double length;
    private String address;

    // Constructor
    public Segment(Long id, Long segmentId, Double length, String address) {
        this.id = id;
        this.segmentId = segmentId;
        this.length = length;
        this.address = address;
    }

    // Getters and Setters
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
