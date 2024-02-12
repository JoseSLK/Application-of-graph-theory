package model;

public class Place {
    private String id;
    private String name;
    private String description;
    private String[] coords;

    /**
     * Constructor por defecto de la clase Place.
     */
    public Place() {
    }

    /**
     * Constructor de la clase Place que inicializa todos los atributos.
     *
     * @param id          Identificador único del lugar.
     * @param name        Nombre del lugar.
     * @param description Descripción del lugar.
     * @param coords      Coordenadas del lugar.
     */
    public Place(String id, String name, String description, String[] coords) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.coords = coords;
    }

    /**
     * Método getter para el atributo id.
     *
     * @return El identificador único del lugar.
     */
    public String getId() {
        return id;
    }

    /**
     * Método setter para el atributo id.
     *
     * @param id El identificador único del lugar.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Método getter para el atributo name.
     *
     * @return El nombre del lugar.
     */
    public String getName() {
        return name;
    }

    /**
     * Método setter para el atributo name.
     *
     * @param name El nombre del lugar.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Método getter para el atributo description.
     *
     * @return La descripción del lugar.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Método setter para el atributo description.
     *
     * @param description La descripción del lugar.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Método getter para el atributo coords.
     *
     * @return Las coordenadas del lugar.
     */
    public String[] getCoords() {
        return coords;
    }

    /**
     * Método setter para el atributo coords.
     *
     * @param coords Las coordenadas del lugar.
     */
    public void setCoords(String[] coords) {
        this.coords = coords;
    }
}
