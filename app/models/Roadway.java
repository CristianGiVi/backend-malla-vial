package models;

public class Roadway {

    private Long id;
    private Double length;

    // Constructor
    public Roadway(Long id, Double length) {
        this.id = id;
        this.length = length;
    }

    // Getters and Setters
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
}
