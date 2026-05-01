public class Nodo<TipoDato> { // esta clase representa un nodo del arbol

    TipoDato dato; // aqui se guarda el valor del nodo
    Nodo<TipoDato> izquierda; // aqui se guarda el hijo izquierdo
    Nodo<TipoDato> derecha; // aqui se guarda el hijo derecho

    public Nodo(TipoDato dato) { // constructor del nodo
        this.dato = dato; // guardamos el dato que llega por parametro
        this.izquierda = null; // al crear el nodo no tiene hijo izquierdo
        this.derecha = null; // al crear el nodo no tiene hijo derecho
    }
}
