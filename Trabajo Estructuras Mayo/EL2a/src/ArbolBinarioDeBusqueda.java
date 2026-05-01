import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ArbolBinarioDeBusqueda<TipoDato extends Comparable<TipoDato>> { // arbol binario de busqueda generico

    protected Nodo<TipoDato> raiz; // este atributo guarda la raiz del arbol

    public ArbolBinarioDeBusqueda() { // constructor para crear un arbol vacio
        this.raiz = null; // al principio el arbol no tiene nodos
    }

    protected ArbolBinarioDeBusqueda(Nodo<TipoDato> raiz) { // constructor protegido para crear subarboles
        this.raiz = raiz; // la raiz del nuevo arbol sera el nodo que nos pasan
    }

    public boolean isVacio() { // metodo para saber si el arbol esta vacio
        return raiz == null; // si la raiz es null, no hay ningun nodo
    }

    public TipoDato getDatoRaiz() { // metodo para obtener el dato de la raiz
        if (raiz == null) { // si el arbol esta vacio no hay raiz
            return null; // devolvemos null porque no existe dato
        }

        return raiz.dato; // devolvemos el dato guardado en la raiz
    }

    public void add(TipoDato dato) { // metodo para insertar un dato en el arbol
        if (dato == null) { // no insertamos datos nulos porque no se pueden comparar bien
            return; // salimos del metodo
        }

        Nodo<TipoDato> nuevo = new Nodo<TipoDato>(dato); // creamos el nuevo nodo con el dato

        if (raiz == null) { // si el arbol esta vacio
            raiz = nuevo; // el nuevo nodo se convierte en la raiz
            return; // terminamos porque ya hemos insertado
        }

        Nodo<TipoDato> actual = raiz; // empezamos a buscar desde la raiz
        Nodo<TipoDato> padre = null; // este nodo guardara el padre del nodo actual

        while (actual != null) { // seguimos bajando mientras haya nodo actual
            padre = actual; // antes de bajar, guardamos el nodo actual como padre

            int comparacion = dato.compareTo(actual.dato); // comparamos el dato nuevo con el dato actual

            if (comparacion < 0) { // si el dato nuevo es menor
                actual = actual.izquierda; // bajamos por la izquierda
            } else if (comparacion > 0) { // si el dato nuevo es mayor
                actual = actual.derecha; // bajamos por la derecha
            } else { // si el dato ya esta en el arbol
                return; // no lo repetimos y salimos
            }
        }

        if (dato.compareTo(padre.dato) < 0) { // si el dato nuevo es menor que el padre
            padre.izquierda = nuevo; // lo colocamos como hijo izquierdo
        } else { // si el dato nuevo es mayor que el padre
            padre.derecha = nuevo; // lo colocamos como hijo derecho
        }
    }

    public int getGrado() { // metodo que calcula el grado del arbol
        return getGradoRecursivo(raiz); // llamamos al metodo recursivo desde la raiz
    }

    private int getGradoRecursivo(Nodo<TipoDato> nodo) { // metodo auxiliar para calcular el grado
        if (nodo == null) { // si no hay nodo
            return 0; // su grado es 0
        }

        int hijos = contarHijos(nodo); // contamos cuantos hijos tiene este nodo
        int gradoIzquierda = getGradoRecursivo(nodo.izquierda); // calculamos el grado maximo de la izquierda
        int gradoDerecha = getGradoRecursivo(nodo.derecha); // calculamos el grado maximo de la derecha

        return Math.max(hijos, Math.max(gradoIzquierda, gradoDerecha)); // devolvemos el mayor grado encontrado
    }

    private int contarHijos(Nodo<TipoDato> nodo) { // metodo auxiliar para contar hijos de un nodo
        int hijos = 0; // empezamos con 0 hijos

        if (nodo.izquierda != null) { // si tiene hijo izquierdo
            hijos++; // sumamos 1 hijo
        }

        if (nodo.derecha != null) { // si tiene hijo derecho
            hijos++; // sumamos 1 hijo
        }

        return hijos; // devolvemos cuantos hijos tiene
    }

    public int getAltura() { // metodo que calcula la altura del arbol
        return getAlturaRecursiva(raiz); // llamamos al metodo recursivo desde la raiz
    }

    private int getAlturaRecursiva(Nodo<TipoDato> nodo) { // metodo auxiliar para calcular la altura
        if (nodo == null) { // si no hay nodo
            return -1; // la altura del arbol vacio la tomamos como -1
        }

        int alturaIzquierda = getAlturaRecursiva(nodo.izquierda); // calculamos la altura de la izquierda
        int alturaDerecha = getAlturaRecursiva(nodo.derecha); // calculamos la altura de la derecha

        return 1 + Math.max(alturaIzquierda, alturaDerecha); // sumamos 1 por el salto hasta el hijo mas profundo
    }

    public List<TipoDato> getListaDatosNivel(int nivel) { // metodo que devuelve los datos de un nivel concreto
        List<TipoDato> lista = new ArrayList<>(); // creamos una lista vacia para guardar los datos
        getListaDatosNivelRecursivo(raiz, nivel, 0, lista); // buscamos los nodos que estan en ese nivel
        return lista; // devolvemos la lista con los datos encontrados
    }

    private void getListaDatosNivelRecursivo(Nodo<TipoDato> nodo, int nivelBuscado, int nivelActual, List<TipoDato> lista) { // metodo auxiliar para buscar por nivel
        if (nodo == null) { // si no hay nodo
            return; // no hacemos nada
        }

        if (nivelActual == nivelBuscado) { // si hemos llegado al nivel que buscabamos
            lista.add(nodo.dato); // añadimos el dato de este nodo
            return; // no hace falta bajar mas desde aqui
        }

        getListaDatosNivelRecursivo(nodo.izquierda, nivelBuscado, nivelActual + 1, lista); // seguimos buscando por la izquierda
        getListaDatosNivelRecursivo(nodo.derecha, nivelBuscado, nivelActual + 1, lista); // seguimos buscando por la derecha
    }

    public boolean isArbolHomogeneo() { // metodo que comprueba si el arbol es homogeneo
        int grado = getGrado(); // primero calculamos el grado maximo del arbol
        return isArbolHomogeneoRecursivo(raiz, grado); // comprobamos todos los nodos
    }

    private boolean isArbolHomogeneoRecursivo(Nodo<TipoDato> nodo, int grado) { // metodo auxiliar para comprobar si es homogeneo
        if (nodo == null) { // si no hay nodo
            return true; // no rompe la condicion
        }

        int hijos = contarHijos(nodo); // contamos los hijos de este nodo

        if (hijos != 0 && hijos != grado) { // un nodo interno debe tener el numero de hijos del grado del arbol
            return false; // si tiene otro numero de hijos, no es homogeneo
        }

        return isArbolHomogeneoRecursivo(nodo.izquierda, grado)
                && isArbolHomogeneoRecursivo(nodo.derecha, grado); // comprobamos los dos subarboles
    }

    public boolean isArbolCompleto() { // metodo que comprueba si todas las hojas estan al mismo nivel
        int altura = getAltura(); // calculamos la altura maxima del arbol
        return isArbolCompletoRecursivo(raiz, 0, altura); // miramos si todas las hojas estan en esa altura
    }

    private boolean isArbolCompletoRecursivo(Nodo<TipoDato> nodo, int nivelActual, int alturaTotal) { // metodo auxiliar para comprobar si es completo
        if (nodo == null) { // si no hay nodo
            return true; // no rompe la condicion
        }

        if (nodo.izquierda == null && nodo.derecha == null) { // si el nodo es hoja
            return nivelActual == alturaTotal; // debe estar en el ultimo nivel
        }

        return isArbolCompletoRecursivo(nodo.izquierda, nivelActual + 1, alturaTotal)
                && isArbolCompletoRecursivo(nodo.derecha, nivelActual + 1, alturaTotal); // comprobamos los dos lados
    }

    public boolean isArbolCasiCompleto() { // metodo que comprueba si el arbol esta lleno de izquierda a derecha
        if (raiz == null) { // si el arbol esta vacio
            return true; // lo consideramos casi completo
        }

        Queue<Nodo<TipoDato>> cola = new LinkedList<>(); // usamos una cola para recorrer por niveles
        cola.add(raiz); // empezamos metiendo la raiz
        boolean encontradoHueco = false; // esto indica si ya hemos encontrado un hueco antes

        while (!cola.isEmpty()) { // mientras queden nodos por revisar
            Nodo<TipoDato> actual = cola.poll(); // sacamos el siguiente nodo de la cola

            if (actual.izquierda != null) { // si hay hijo izquierdo
                if (encontradoHueco) { // si ya habia aparecido un hueco antes
                    return false; // entonces no esta completo de izquierda a derecha
                }
                cola.add(actual.izquierda); // añadimos el hijo izquierdo a la cola
            } else { // si no hay hijo izquierdo
                encontradoHueco = true; // marcamos que ya ha aparecido un hueco
            }

            if (actual.derecha != null) { // si hay hijo derecho
                if (encontradoHueco) { // si antes habia aparecido un hueco
                    return false; // no puede haber un nodo despues de un hueco
                }
                cola.add(actual.derecha); // añadimos el hijo derecho a la cola
            } else { // si no hay hijo derecho
                encontradoHueco = true; // marcamos que ya ha aparecido un hueco
            }
        }

        return true; // si no hemos encontrado errores, es casi completo
    }

    public List<TipoDato> getCamino(TipoDato dato) { // metodo que devuelve el camino hasta un dato
        List<TipoDato> camino = new ArrayList<>(); // creamos la lista donde guardaremos el camino

        if (dato == null) { // si el dato a buscar es null
            return camino; // devolvemos camino vacio
        }

        Nodo<TipoDato> actual = raiz; // empezamos desde la raiz

        while (actual != null) { // bajamos por el arbol mientras haya nodo
            camino.add(actual.dato); // añadimos el nodo actual al camino

            int comparacion = dato.compareTo(actual.dato); // comparamos el dato buscado con el actual

            if (comparacion == 0) { // si lo hemos encontrado
                return camino; // devolvemos el camino completo
            } else if (comparacion < 0) { // si el dato buscado es menor
                actual = actual.izquierda; // bajamos por la izquierda
            } else { // si el dato buscado es mayor
                actual = actual.derecha; // bajamos por la derecha
            }
        }

        camino.clear(); // si no encontramos el dato, vaciamos la lista
        return camino; // devolvemos lista vacia
    }

    public ArbolBinarioDeBusqueda<TipoDato> getSubArbolIzquierda() { // metodo que devuelve el subarbol izquierdo
        if (raiz == null) { // si el arbol esta vacio
            return new ArbolBinarioDeBusqueda<TipoDato>(); // devolvemos un arbol vacio
        }

        return new ArbolBinarioDeBusqueda<TipoDato>(raiz.izquierda); // devolvemos un arbol cuya raiz es el hijo izquierdo
    }

    public ArbolBinarioDeBusqueda<TipoDato> getSubArbolDerecha() { // metodo que devuelve el subarbol derecho
        if (raiz == null) { // si el arbol esta vacio
            return new ArbolBinarioDeBusqueda<TipoDato>(); // devolvemos un arbol vacio
        }

        return new ArbolBinarioDeBusqueda<TipoDato>(raiz.derecha); // devolvemos un arbol cuya raiz es el hijo derecho
    }

    public List<TipoDato> getListaPreOrden() { // metodo que devuelve los datos en preorden
        List<TipoDato> lista = new ArrayList<>(); // creamos una lista vacia
        preOrdenRecursivo(raiz, lista); // rellenamos la lista usando preorden
        return lista; // devolvemos la lista
    }

    private void preOrdenRecursivo(Nodo<TipoDato> nodo, List<TipoDato> lista) { // recorrido preorden: raiz, izquierda, derecha
        if (nodo == null) { // si no hay nodo
            return; // no hacemos nada
        }

        lista.add(nodo.dato); // primero añadimos la raiz
        preOrdenRecursivo(nodo.izquierda, lista); // despues recorremos la izquierda
        preOrdenRecursivo(nodo.derecha, lista); // por ultimo recorremos la derecha
    }

    public List<TipoDato> getListaPostOrden() { // metodo que devuelve los datos en postorden
        List<TipoDato> lista = new ArrayList<>(); // creamos una lista vacia
        postOrdenRecursivo(raiz, lista); // rellenamos la lista usando postorden
        return lista; // devolvemos la lista
    }

    private void postOrdenRecursivo(Nodo<TipoDato> nodo, List<TipoDato> lista) { // recorrido postorden: izquierda, derecha, raiz
        if (nodo == null) { // si no hay nodo
            return; // no hacemos nada
        }

        postOrdenRecursivo(nodo.izquierda, lista); // primero recorremos la izquierda
        postOrdenRecursivo(nodo.derecha, lista); // despues recorremos la derecha
        lista.add(nodo.dato); // al final añadimos la raiz
    }

    public List<TipoDato> getListaOrdenCentral() { // metodo que devuelve los datos en orden central
        List<TipoDato> lista = new ArrayList<>(); // creamos una lista vacia
        ordenCentralRecursivo(raiz, lista); // rellenamos la lista usando orden central
        return lista; // devolvemos la lista
    }

    private void ordenCentralRecursivo(Nodo<TipoDato> nodo, List<TipoDato> lista) { // recorrido orden central: izquierda, raiz, derecha
        if (nodo == null) { // si no hay nodo
            return; // no hacemos nada
        }

        ordenCentralRecursivo(nodo.izquierda, lista); // primero recorremos la izquierda
        lista.add(nodo.dato); // despues añadimos la raiz
        ordenCentralRecursivo(nodo.derecha, lista); // al final recorremos la derecha
    }
}
