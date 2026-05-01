import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
public class GrafoConocimiento {
    // Aqui guardo todas las tripletas del grafo
    private ArrayList<Tripleta> tripletas;
    // Aqui guardo todos los nodos sin repetir
    private HashSet<String> nodos;
    // Aqui guardo los tipos que venian en el JSON
    private HashSet<String> tipos;
    public GrafoConocimiento() {
        // Inicializo las estructuras
        tripletas = new ArrayList<>();
        nodos = new HashSet<>();
        tipos = new HashSet<>();
    }
    public void cargarDatos(DatosJSON datos) {
        // Si no se ha podido leer el JSON no hago nada
        if (datos == null) {
            System.out.println("No se han cargado datos porque el JSON es null.");
            return;
        }
        // Meto los tipos del JSON
        if (datos.getTipos() != null) {
            tipos.addAll(datos.getTipos());
        }
        // Meto las tripletas una a una
        if (datos.getTripletas() != null) {
            for (Tripleta t : datos.getTripletas()) {
                anadirTripleta(t);
            }
        }
    }
    public void anadirTripleta(Tripleta tripleta) {
        // Por si acaso llega una tripleta vacia
        if (tripleta == null) {
            return;
        }
        // Guardo la tripleta
        tripletas.add(tripleta);
        // El sujeto y el objeto son nodos del grafo
        nodos.add(tripleta.getS());
        nodos.add(tripleta.getO());
    }
    public void anadirTripleta(String sujeto, String predicado, String objeto) {
        // Creo la tripleta y la meto al grafo
        Tripleta nueva = new Tripleta(sujeto, predicado, objeto);
        anadirTripleta(nueva);
    }
    public void mostrarTripletas() {
        // Muestro todas las tripletas
        for (Tripleta t : tripletas) {
            System.out.println(t);
        }
    }
    public ArrayList<Tripleta> getTripletas() {
        return tripletas;
    }
    public HashSet<String> getNodos() {
        return nodos;
    }
    public ArrayList<String> caminoMinimo(String origen, String destino) {
        // Aqui se guardara el camino final
        ArrayList<String> camino = new ArrayList<>();
        // Si algun nodo no existe, no hay camino
        if (!nodos.contains(origen) || !nodos.contains(destino)) {
            return camino;
        }
        // Cola para hacer BFS
        Queue<String> cola = new LinkedList<>();
        // Para no visitar lo mismo mil veces
        HashSet<String> visitados = new HashSet<>();
        // Para saber de donde viene cada nodo
        HashMap<String, String> anterior = new HashMap<>();
        // Empiezo por el pincipio
        cola.add(origen);
        visitados.add(origen);
        while (!cola.isEmpty()) {
            String actual = cola.poll();
            // Si llego al destino, paro
            if (actual.equals(destino)) {
                break;
            }
            // Saco vecinos del nodo actual
            ArrayList<String> vecinos = obtenerVecinosNoDirigido(actual);
            for (String vecino : vecinos) {
                // Si no lo he visitado lo meto
                if (!visitados.contains(vecino)) {
                    visitados.add(vecino);
                    anterior.put(vecino, actual);
                    cola.add(vecino);
                }
            }
        }
        // Si no se llego al destino devuelvo lista vacia
        if (!visitados.contains(destino)) {
            return camino;
        }
        // Reconstruyo el camino desde el final hacia atras
        String actual = destino;
        while (actual != null) {
            camino.add(actual);
            actual = anterior.get(actual);
        }
        // Como estaba al reves le doy la vuelta
        Collections.reverse(camino);
        return camino;
    }
    public ArrayList<PasoCamino> caminoMinimoConRelaciones(String origen, String destino) {
        // Aqui guardo el camino pero con los predicados
        ArrayList<PasoCamino> resultado = new ArrayList<>();
        // Primero saco el camino normal
        ArrayList<String> camino = caminoMinimo(origen, destino);
        // Si tiene menos de 2 nodos, no hay pasos
        if (camino.size() < 2) {
            return resultado;
        }
        // Miro cada pareja de nodos del camino
        for (int i = 0; i < camino.size() - 1; i++) {
            String desde = camino.get(i);
            String hasta = camino.get(i + 1);
            // Busco que relacion habia entre esos dos nodos
            String predicado = buscarPredicadoEntreNodos(desde, hasta);
            resultado.add(new PasoCamino(desde, predicado, hasta));
        }
        return resultado;
    }
    private String buscarPredicadoEntreNodos(String a, String b) {
        // Busco una tripleta que conecte esos dos nodos
        for (Tripleta t : tripletas) {
            if (t.getS().equals(a) && t.getO().equals(b)) {
                return t.getP();
            }
            // Tambien lo miro al reves porque para caminos uso no dirigido
            if (t.getS().equals(b) && t.getO().equals(a)) {
                return t.getP();
            }
        }
        return "relacion_desconocida";
    }
    private ArrayList<String> obtenerVecinosNoDirigido(String nodo) {
        ArrayList<String> vecinos = new ArrayList<>();
        // Recorro todas las tripletas para ver quien esta conectado
        for (Tripleta t : tripletas) {
            if (t.getS().equals(nodo)) {
                vecinos.add(t.getO());
            }
            // Tambien permito ir al reves
            if (t.getO().equals(nodo)) {
                vecinos.add(t.getS());
            }
        }
        return vecinos;
    }
    public boolean esDisjunto() {
        // Un grafo vacio no lo tomo como disjunto
        if (nodos.isEmpty()) {
            return false;
        }
        HashSet<String> visitados = new HashSet<>();
        // Cojo un nodo cualquiera para empezar
        String primero = nodos.iterator().next();
        // Recorro lo que pueda desde ese nodo
        recorrerDesde(primero, visitados);
        // Si no he visitado todos hay partes separadas
        return visitados.size() != nodos.size();
    }
    private void recorrerDesde(String nodo, HashSet<String> visitados) {
        // Marco el nodo como visitado
        visitados.add(nodo);
        // Miro sus vecinos
        ArrayList<String> vecinos = obtenerVecinosNoDirigido(nodo);
        for (String vecino : vecinos) {
            // Si no estaba visitado, sigo por ahi
            if (!visitados.contains(vecino)) {
                recorrerDesde(vecino, visitados);
            }
        }
    }
    public ArrayList<String> fisicosNacidosEnLaCiudadDeEinstein() {
        ArrayList<String> resultado = new ArrayList<>();
        // Primero busco donde nacio Einstein
        String ciudadEinstein = obtenerObjeto("persona:Albert Einstein", "nace_en");
        // Si no esta ese dato, no puedo hacer la consulta
        if (ciudadEinstein == null) {
            return resultado;
        }
        // Recorro todos los nodos
        for (String nodo : nodos) {
            // Solo me interesan personas
            if (!nodo.startsWith("persona:")) {
                continue;
            }
            // No queremos devolver al propio Einstein
            if (nodo.equals("persona:Albert Einstein")) {
                continue;
            }
            // Tiene que ser fisico y nacer en la misma ciudad
            if (esFisico(nodo) && naceEn(nodo, ciudadEinstein)) {
                resultado.add(nodo);
            }
        }
        return resultado;
    }
    private boolean esFisico(String persona) {
        // Miro si tiene profesion de fisico
        return existeTripleta(persona, "profesion", "profesion:Fisico");
    }
    private boolean naceEn(String persona, String lugar) {
        // Miro si la persona nacio en ese lugar
        return existeTripleta(persona, "nace_en", lugar);
    }
   public ArrayList<String> lugaresNacimientoPremiosNobel() {
        ArrayList<String> lugares = new ArrayList<>();
        // Recorro todos los nodos del grafo
        for (String nodo : nodos) {
            // Solo me interesan personas
            if (!nodo.startsWith("persona:")) {
                continue;
            }
            // Primero compruebo que tenga Nobel
            if (esPremioNobel(nodo)) {
                // Si tiene Nobel, busco donde nacio
                String lugar = obtenerObjeto(nodo, "nace_en");
                // Lo meto si existe y no estaba ya
                if (lugar != null && !lugares.contains(lugar)) {
                    lugares.add(lugar);
                }
            }
        }
        return lugares;
    }
    private boolean esPremioNobel(String persona) {
        // Busco una tripleta de premio Nobel
        for (Tripleta t : tripletas) {
            if (t.getS().equals(persona)
                    && t.getP().equals("premio")
                    && t.getO().startsWith("premio:Nobel")) {
                return true;
            }
        }
        return false;
    }
public ArrayList<String> tiposDeNodosDeclarados() {
        ArrayList<String> lista = new ArrayList<>();
        // Meto los tipos que venian declarados en el JSON
        for (String tipo : tipos) {
            if (tipo != null && !tipo.equals("") && !lista.contains(tipo)) {
                lista.add(tipo);
            }
        }
        return lista;
    }
    public ArrayList<String> tiposDeNodosEncontrados() {
        ArrayList<String> encontrados = new ArrayList<>();
        // Miro los prefijos tipo persona: o lugar:
        for (String nodo : nodos) {
            if (nodo.contains(":")) {
                String tipo = nodo.substring(0, nodo.indexOf(":"));
                if (!encontrados.contains(tipo)) {
                    encontrados.add(tipo);
                }
            }
        }
        return encontrados;
    }
   public boolean existeTripleta(String sujeto, String predicado, String objeto) {
        // Recorro todas las tripletas buscando una igual
        for (Tripleta t : tripletas) {
            if (t.getS().equals(sujeto)
                    && t.getP().equals(predicado)
                    && t.getO().equals(objeto)) {
                return true;
            }
        }
        return false;
    }
    private String obtenerObjeto(String sujeto, String predicado) {
        // Devuelve el objeto de la primera tripleta que coincida
        for (Tripleta t : tripletas) {
            if (t.getS().equals(sujeto) && t.getP().equals(predicado)) {
                return t.getO();
            }
        }
        return null;
    }
}