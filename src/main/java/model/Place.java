package model;

public class Place {
    private String id;
    private String name;
    private String description;
    private String[] coords;

    public Place() {
    }

    public Place(String id, String name, String description, String[] coords) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.coords = coords;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getCoords() {
        return coords;
    }

    public void setCoords(String[] coords) {
        this.coords = coords;
    }
}
