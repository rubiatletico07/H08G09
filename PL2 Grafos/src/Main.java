import java.util.ArrayList;
public class Main {
    public static void main(String[] args) {
        // Lanzo las tres pruebas principales
        pruebaGrafoConectado();
        pruebaGrafoDisjunto();
        pruebaGrafoNobel();
    }
    private static void pruebaGrafoConectado() {
        System.out.println("Prueba grafo conectado");
        // Leo el JSON del grafo conectado
        DatosJSON datos = LectorJSON.leerArchivo("datos/grafo_conectado.json");
        // Creo el grafo y cargo los datos
        GrafoConocimiento grafo = new GrafoConocimiento();
        grafo.cargarDatos(datos);
        System.out.println();
        System.out.println("Tripletas cargadas:");
        grafo.mostrarTripletas();
        System.out.println();
        System.out.println("¿El grafo es disjunto?");
        System.out.println(grafo.esDisjunto());
        System.out.println();
        System.out.println("Camino minimo entre persona:Albert Einstein y lugar:Alemania:");
        // Pruebo el camino minimo
        ArrayList<String> camino = grafo.caminoMinimo(
                "persona:Albert Einstein",
                "lugar:Alemania"
        );
        mostrarCamino(camino);
        System.out.println();
        System.out.println("Camino minimo mostrando las relaciones:");
        // Ahora lo muestro tambien con las relaciones
        ArrayList<PasoCamino> pasos = grafo.caminoMinimoConRelaciones(
                "persona:Albert Einstein",
                "lugar:Alemania"
        );
        for (PasoCamino paso : pasos) {
            System.out.println(paso);
        }
        System.out.println();
    }
    private static void pruebaGrafoDisjunto() {
        System.out.println("prueba grafo disjunto");
        // Leo el JSON que tiene varias partes separadas
        DatosJSON datos = LectorJSON.leerArchivo("datos/grafo_disjunto.json");
        // Creo el grafo y cargo los datos
        GrafoConocimiento grafo = new GrafoConocimiento();
        grafo.cargarDatos(datos);
        System.out.println();
        System.out.println("Tripletas cargadas:");
        grafo.mostrarTripletas();
        System.out.println();
        System.out.println("¿El grafo es disjunto?");
        System.out.println(grafo.esDisjunto());
        System.out.println();
    }
    private static void pruebaGrafoNobel() {
        System.out.println("prueba de grafos de premios nobel");
        // Leo el JSON con los datos de Nobel
        DatosJSON datos = LectorJSON.leerArchivo("datos/nobel.json");
        // Creo el grafo de Nobel
        GrafoConocimiento grafo = new GrafoConocimiento();
        grafo.cargarDatos(datos);
        System.out.println();
        System.out.println("Tripletas cargadas:");
        grafo.mostrarTripletas();
        System.out.println();
        System.out.println("Fisicos famosos nacidos en la misma ciudad que Einstein:");
        // Busco los fisicos nacidos en la ciudad de Einstein
        ArrayList<String> fisicos = grafo.fisicosNacidosEnLaCiudadDeEinstein();
        if (fisicos.isEmpty()) {
            System.out.println("No se ha encontrado ningun fisico.");
        } else {
            for (String fisico : fisicos) {
                System.out.println("- " + fisico);
            }
        }
        System.out.println();
        System.out.println("Anadimos la tripleta de Antonio:");
        // Añado la tripleta nueva
        grafo.anadirTripleta(
                "persona:Antonio",
                "nace_en",
                "lugar:Villarrubia de los Caballeros"
        );
        System.out.println("<\"persona:Antonio\", \"nace_en\", \"lugar:Villarrubia de los Caballeros\">");
        System.out.println();
        System.out.println("Lugares de nacimiento de los premios Nobel:");
        // Saco los lugares de nacimiento de gente con Nobel
        ArrayList<String> lugares = grafo.lugaresNacimientoPremiosNobel();
        for (String lugar : lugares) {
            System.out.println("- " + lugar);
        }
        System.out.println();
        System.out.println("Tipos declarados en el JSON:");
        // Muestro los tipos que venian en el JSON
        ArrayList<String> tiposDeclarados = grafo.tiposDeNodosDeclarados();
        for (String tipo : tiposDeclarados) {
            System.out.println("- " + tipo);
        }
        System.out.println();
        System.out.println("Tipos encontrados en los nodos:");
        // Muestro los tipos segun el prefijo de los nodos
        ArrayList<String> tiposEncontrados = grafo.tiposDeNodosEncontrados();
        for (String tipo : tiposEncontrados) {
            System.out.println("- " + tipo);
        }
        System.out.println();
    }
    private static void mostrarCamino(ArrayList<String> camino) {
        // Si no hay camino, aviso
        if (camino.isEmpty()) {
            System.out.println("No existe camino.");
            return;
        }
        // Pinto el camino con flechas
        for (int i = 0; i < camino.size(); i++) {
            System.out.print(camino.get(i));
            if (i < camino.size() - 1) {
                System.out.print(" -> ");
            }
        }
        System.out.println();
    }
}