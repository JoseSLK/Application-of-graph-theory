package controller;

import model.Place;
import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author JoseSLK
 * La clase RouteManager gestiona la creación y gestión de rutas en un grafo ponderado de lugares.
 * Utiliza la librería JGraphT para modelar el grafo y calcular la ruta más corta utilizando el algoritmo de Dijkstra.
 */
public class RouteManager {

    /**
     * Constructor de la clase RouteManager. Configura el grafo y establece las rutas iniciales.
     */
    public RouteManager() {
        setUp();
        setRoutes();
    }

    /**
     * Grafo que representa los lugares y las rutas entre ellos.
     */
    Graph<Place, DefaultWeightedEdge> graph = new DefaultDirectedWeightedGraph<>(DefaultWeightedEdge.class);

    /**
     * Agrega un lugar al grafo.
     *
     * @param name        Nombre del lugar.
     * @param id          Identificador único del lugar.
     * @param description Descripción del lugar.
     * @param coords      Coordenadas del lugar.
     * @return true si el lugar se agregó correctamente al grafo, false en caso contrario.
     */
    public boolean addPlace(String name, String id, String description, String[] coords){
        return graph.addVertex(new Place(id, name, description, coords));
    }

    /**
     * Busca un vértice en el grafo por su identificador único.
     *
     * @param id Identificador único del lugar.
     * @return El lugar correspondiente al identificador, o null si no se encuentra.
     */
    public Place findVertex(String id){
        for(Place place: graph.vertexSet()){
            if(place.getId().equals(id)){
                return place;
            }

        }
        return null;
    }

    /**
     * Método para agregar una ruta entre dos lugares en el grafo.
     *
     * @param idFrom   El ID del lugar de origen.
     * @param idTo     El ID del lugar de destino.
     * @param directed Si es verdadero, se agrega un borde en la dirección opuesta, haciendo que la ruta sea bidireccional.
     *                 Si es falso, la ruta será unidireccional.
     * @param weight   El peso del borde, que podría representar la distancia o el costo entre los dos lugares.
     *
     * @return Verdadero si la ruta se agregó con éxito. Falso si no se pudo agregar la ruta, por ejemplo,
     *         si los lugares de origen o destino no existen en el grafo, o si ya existe un borde entre ellos.
     */
    public boolean addRoute(String idFrom, String idTo, boolean directed, int weight) {
        Place placeFrom = findVertex(idFrom);
        Place placeTo = findVertex(idTo);

        if (placeFrom != null && placeTo != null) {
            DefaultWeightedEdge edge = graph.addEdge(placeFrom, placeTo);

            if (edge != null) {
                graph.setEdgeWeight(edge, weight);
                if (directed) {
                    DefaultWeightedEdge reverseEdge = graph.addEdge(placeTo, placeFrom);
                    if (reverseEdge != null) {
                        graph.setEdgeWeight(reverseEdge, weight);
                    }
                }
                return true;
            }
        }
        return false;
    }



    /**
     * Calcula la ruta más corta entre dos lugares.
     *
     * @param idFrom Identificador del lugar de origen.
     * @param idTo   Identificador del lugar de destino.
     * @return Lista de nombres de lugares que forman la ruta más corta, o null si no hay ruta disponible.
     */
    public List<String> shortestRoute(String idFrom, String idTo){
        List<Place> vertexList = new ArrayList<>();
        List<String> route = new ArrayList<>();

        Place placeFrom = findVertex(idFrom);
        Place placeTo = findVertex(idTo);

        if (placeFrom != null && placeTo != null) {
            DijkstraShortestPath<Place, DefaultWeightedEdge> dijkstra = new DijkstraShortestPath<>(graph);
            GraphPath<Place, DefaultWeightedEdge> shortPath = dijkstra.getPath(placeFrom, placeTo);

            if (shortPath != null) {
                vertexList = shortPath.getVertexList();
                for (Place place : vertexList) {
                    route.add(place.getName());
                }
            }
        }
        return route.isEmpty() ? null : route;
    }

    /**
     * Obtiene un mapa de los lugares disponibles en el grafo.
     *
     * @return Mapa que asigna nombres de lugares a sus identificadores únicos.
     */
    public HashMap<String, String> getPlaces(){
        HashMap<String, String> names = new HashMap<>();
        for (Place place: graph.vertexSet()){
            names.put(place.getName(), place.getId());
        }
        return names;
    }

    /**
     * Configura los lugares y las conexiones iniciales en el grafo.
     */
    public void setUp() {
        String[] coords = {"1235", "37462"};

        //A
        addPlace("Plaza de la villa", "1234", "Una hermosa plaza arbolada donde los visitantes pueden relajarse y disfrutar de la naturaleza", coords);
        //B
        addPlace("Plaza Seis de Septiembre", "1874", "Un espacio público con jardines y áreas de juego para toda la familia", coords);
        //C
        addPlace("Plaza de Mercado", "8904", "Un lugar vibrante y concurrido donde puedes encontrar una amplia variedad de productos frescos", coords);
        //D
        addPlace("Colegio Gustavo Jimenez", "8023", "Una institución educativa comprometida con la excelencia académica y el desarrollo integral de los estudiantes", coords);
        //E
        addPlace("Parque Conchucua", "0934", "Un parque tranquilo con senderos para caminar y áreas verdes ideales para disfrutar de un día al aire libre", coords);
        //F
        addPlace("UPTC - Seccional Sogamoso", "7321", "Una prestigiosa universidad pública que ofrece una amplia gama de programas académicos y oportunidades de investigación", coords);
        //G
        addPlace("Barrio Mochaca", "7653", "Una comunidad vibrante y diversa conocida por su ambiente acogedor y sus festivales culturales", coords);
        //H
        addPlace("Acerías Paz del Río", "8752", "Una importante empresa dedicada a la producción de acero con un fuerte compromiso con la sostenibilidad y la responsabilidad social", coords);
        //I
        addPlace("Parque Recreacional del Norte", "6948", "Un espacio de recreación y entretenimiento para toda la familia, con áreas verdes, juegos y actividades al aire libre", coords);

    }

    public void setRoutes(){
        //AB
        addRoute("1234", "1874", true, 30);
        //BC
        addRoute("1874", "8904", true, 10);
        //CD
        addRoute("8904", "8023", true, 10);
        //AE
        addRoute("1234", "0934", false, 70);
        //BF
        addRoute("1874", "7321", false, 80);
        //CG
        addRoute("8904", "7653", false, 85);
        //DH
        addRoute("8023", "8752", false, 30);
        //EF
        addRoute("0934", "7321", true, 5);
        //FG
        addRoute("7321", "7653", true, 13);
        //GH
        addRoute("7653", "8752", true, 25);
        //GI
        addRoute("7653", "6948", false, 30);

    }

}
