import java.util.ArrayList;
public class DatosJSON {
    // Aqui se guardan los tipos que vienen en el JSON
    private ArrayList<String> tipos;
    // Aqui se guardan las tripletas del JSON
    private ArrayList<Tripleta> tripletas;
    public DatosJSON() {
        // Inicializo las listas para que no sean null
        tipos = new ArrayList<>();
        tripletas = new ArrayList<>();
    }
    public ArrayList<String> getTipos() {
        return tipos;
    }
    public ArrayList<Tripleta> getTripletas() {
        return tripletas;
    }
}