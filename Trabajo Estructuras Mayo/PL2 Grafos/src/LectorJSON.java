import com.google.gson.Gson;
import java.io.FileReader;
import java.io.IOException;
public class LectorJSON {
    public static DatosJSON leerArchivo(String ruta) {
        // Creo el objeto Gson para leer el JSON
        Gson gson = new Gson();
        try {
            // Abro el archivo que le paso por ruta
            FileReader lector = new FileReader(ruta);
            // Gson convierte el JSON en un objeto DatosJSON
            DatosJSON datos = gson.fromJson(lector, DatosJSON.class);
            // Cierro el lector porque ya no hace falta
            lector.close();
            return datos;
        } catch (IOException e) {
            // Si falla, saco el error por pantalla
            System.out.println("Error leyendo el archivo: " + ruta);
            System.out.println(e.getMessage());
            return null;
        }
    }
}